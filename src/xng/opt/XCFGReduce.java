package xng.opt;

import xng.XIR.XCFG;
import xng.XIR.XCFGNode;

import java.util.HashMap;
import java.util.HashSet;

public class XCFGReduce {

    private XCFG cfg;
    private HashSet<Integer> visitFlag = new HashSet<>();

    public XCFGReduce(XCFG _cfg){
        cfg = _cfg;
        visitXCFG();
    }

    private void visitXCFG(){
        System.out.println("XCFGReduce begin");
        cfg.globalNodes.forEach(this::visitXCFGNode);
    }

    private void visitXCFGNode(XCFGNode node) {
        if (node == null || visitFlag.contains(node.nodeID)) return;
        visitFlag.add(node.nodeID);
        System.out.println("reducing "+node);
        while ((node.nextNode.size()==1 && node.nextNode.firstElement().prevNode.size()==1)
                || node.instList.isEmpty()){
            cfg.mergeNode(node,node.nextNode.firstElement());
        }
        node.nextNode.forEach(this::visitXCFGNode);
    }
}
