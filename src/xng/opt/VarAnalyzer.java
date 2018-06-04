package xng.opt;

import xng.XIR.*;

import java.util.HashSet;

public class VarAnalyzer {
    private XCFG cfg;
    private HashSet<Integer> visitFlag = new HashSet<>();

    public VarAnalyzer(XCFG _cfg){
        cfg = _cfg;
        visitXCFG();
    }

    private void visitXCFG(){
        System.out.println("Var Analysis begin");
        for (XIRProcInfo i : cfg.Proc) {
            visitXCFGNode(i.entryNode);
        }
    }

    private void visitXCFGNode(XCFGNode node) {
        if (visitFlag.contains(node.nodeID)) return;
        visitFlag.add(node.nodeID);
        System.out.println("analyzing "+node.nodeID);
        node.instList.forEach(this::visitXIRInst);
        node.nextNode.forEach(this::visitXCFGNode);
    }

    private void visitXIRInst(XIRInst inst){
        VarInfo inf1 = inst.oprList.size() > 0 ? inst.oprList.get(0).info : null;
        VarInfo inf2 = inst.oprList.size() > 1 ? inst.oprList.get(1).info : null;
        VarInfo inf3 = inst.oprList.size() > 2 ? inst.oprList.get(2).info : null;
        VarInfo.constPropagate(inst.op,inf1,inf2,inf3);
        switch (inst.op) {
            case op_mov:
                ++inf1.writeCount;
                ++inf2.readCount;
                break;
            case op_wpara:
            case op_push:
                ++inf1.readCount;
                break;
            case op_pop:
            case op_rpara:
            case op_inc:
            case op_dec:
                ++inf1.writeCount;
                break;
            case op_eq:
            case op_ne:
            case op_lt:
            case op_ge:
            case op_gt:
            case op_le:
                ++inf2.readCount;
                ++inf3.readCount;
                break;
            case op_ret:
                ++inf1.readCount;
                break;
            case op_not:
            case op_neg:
            case op_pos:
                ++inf1.writeCount;
                ++inf2.readCount;
                break;
            case op_add:
            case op_sub:
            case op_mult:
            case op_or:
            case op_xor:
            case op_and:
            case op_shl:
            case op_shr:
            case op_mod:
            case op_div:
                ++inf1.writeCount;
                ++inf2.readCount;
                ++inf3.readCount;
                break;
        }
        for (XIRInstAddr i : inst.oprList) {
            if (i.info.type == VarInfo.valType.v_const) {
                i.type = XIRInstAddr.addrType.a_imm;
                i.lit1 = i.info.constValue;
                i.addr1 = i.addr2 = null;
            }
        }
    }
}
