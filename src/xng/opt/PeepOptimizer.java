package xng.opt;

import xng.XIR.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Vector;

public class PeepOptimizer {
    private XCFG cfg;
    private HashSet<Integer> visitFlag = new HashSet<>();

    public PeepOptimizer(XCFG _cfg){
        cfg = _cfg;
        visitXCFG();
    }

    private void visitXCFG(){
        System.out.println("Peep opt begin");
        for (XIRProcInfo i : cfg.Proc) {
            visitXCFGNode(i.entryNode);
        }
    }

    private void visitXCFGNode(XCFGNode node) {
        if (visitFlag.contains(node.nodeID)) return;
        visitFlag.add(node.nodeID);
        System.out.println("peep:"+node.nodeID);
        Vector<XIRInst> newList = new Vector<>();
        Vector<XIRInst> instList = node.instList;
        for (int i = 0; i < instList.size(); i++) {
            XIRInst inst = instList.get(i);
            XIRInst prevInst = (i==0)?null:instList.get(i-1);
            XIRInst nextInst = (i+1<instList.size())?instList.get(i+1):null;
            XIRInst nnInst = (i+2<instList.size())?instList.get(i+2):null;
            if (inst.op == XIRInst.opType.op_mov && nextInst != null && nnInst != null) {
                XIRInstAddr opr1 = inst.oprList.get(0);
                XIRInstAddr opr2 = inst.oprList.get(1);
                if (nextInst.oprList.size()>0 && nextInst.oprList.get(0).equals(opr1)
                        && nnInst.op==XIRInst.opType.op_mov
                        && nnInst.oprList.get(0).equals(opr2)
                        && nnInst.oprList.get(1).equals(opr1)) {
                    nextInst.oprList.set(0,opr2);
                    System.out.println("Peep:redundant temp:"+nextInst);
                    newList.add(nextInst);
                    i+=2;
                    continue;
                }
            }
            newList.add(inst);
        }
        node.instList = newList;
        node.nextNode.forEach(this::visitXCFGNode);
    }

    private void visitXIRInstAddr(XIRInstAddr opr) {
        if (opr==null) return;
    }

    private void visitXIRInst(XIRInst.opType op, XIRInstAddr opr1, XIRInstAddr opr2){

    }
}
