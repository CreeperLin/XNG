package xng.opt;

import xng.XIR.XCFG;
import xng.XIR.XCFGNode;
import xng.XIR.XIRInst;
import xng.XIR.XIRInstAddr;

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
        for (XCFGNode globalNode : cfg.globalNodes) {
            visitXCFGNode(globalNode);
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
        VarInfo.constPropagate(inst);
        for (XIRInstAddr i : inst.oprList) {
            if (i.info.type == VarInfo.valType.v_const) {
                i.type = XIRInstAddr.addrType.a_imm;
                i.lit1 = i.info.constValue;
                i.addr1 = i.addr2 = null;
            }
        }
    }
}
