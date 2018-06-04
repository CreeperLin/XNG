package xng.opt;

import xng.XIR.XCFG;
import xng.XIR.XCFGNode;
import xng.XIR.XIRInst;
import xng.XIR.XIRProcInfo;

import java.util.HashSet;

public class DataFlowAnalyzer {
    private XCFG cfg;
    private HashSet<Integer> visitFlag = new HashSet<>();

    public DataFlowAnalyzer(XCFG _cfg){
        cfg = _cfg;
        visitXCFG();
    }

    private void visitXCFG(){
        System.out.println("Data Analysis begin");
        for (XIRProcInfo i : cfg.Proc) {
            visitXCFGNode(i.entryNode);
        }
    }

    private void visitXCFGNode(XCFGNode node) {
        if (visitFlag.contains(node.nodeID)) return;
        visitFlag.add(node.nodeID);
        System.out.println("back analyzing "+node.nodeID);
        node.instList.forEach(this::visitXIRInst);
        node.nextNode.forEach(this::visitXCFGNode);
    }

    private void visitXIRInst(XIRInst inst){

    }
}
