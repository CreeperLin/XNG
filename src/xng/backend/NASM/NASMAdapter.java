package xng.backend.NASM;

import xng.XIR.*;

import java.util.HashSet;
import java.util.Vector;

public class NASMAdapter {

    private XCFG cfg;
    private int curStackPt = 8;
    private HashSet<Integer> visitFlag = new HashSet<>();

    public NASMAdapter(XCFG _cfg){
        cfg = _cfg;
        visitXCFG();
    }

    private void visitXCFG(){
        System.out.println("NASM Adaptation begin");
        for (XIRProcInfo i : cfg.Proc) {
            visitXCFGNode(i.entryNode);
        }
    }

    private void visitXCFGNode(XCFGNode node) {
        if (visitFlag.contains(node.nodeID)) return;
        visitFlag.add(node.nodeID);
        System.out.println("adapting:"+node.nodeID);
        Vector<XIRInst> newList = new Vector<>();
        for (XIRInst inst : node.instList) {
            if (inst.oprList.size() == 3) {
                XIRInstAddr opr1 = inst.oprList.get(0);
                XIRInstAddr opr2 = inst.oprList.get(1);
                XIRInstAddr opr3 = inst.oprList.get(2);
                if (opr1.equals(opr2)) {
                    newList.add(new XIRInst(inst.op, opr1, opr3));
                } else if (opr1.equals(opr3)) {
                    if (XIRInst.isOpCommute(inst.op)) {
                        newList.add(new XIRInst(inst.op, opr1, opr3));
                    } else {
                        XIRInstAddr tmp_addr = XIRInstAddr.newRegAddr();
                        newList.add(new XIRInst(XIRInst.opType.op_mov, tmp_addr, opr2));
                        newList.add(new XIRInst(inst.op, tmp_addr, opr3));
                        newList.add(new XIRInst(XIRInst.opType.op_mov, opr1, tmp_addr));
                    }
                } else {
                    newList.add(new XIRInst(XIRInst.opType.op_mov, opr1, opr2));
                    newList.add(new XIRInst(inst.op, opr1, opr3));
                }
            } else {
                newList.add(inst);
            }
        }
        node.instList = newList;
        node.nextNode.forEach(this::visitXCFGNode);
    }

    private void visitXIRInst(XIRInst inst){

    }
}
