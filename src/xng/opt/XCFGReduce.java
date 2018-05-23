package xng.opt;

import xng.XIR.XCFG;
import xng.XIR.XCFGNode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Stack;

public class XCFGReduce {

    private XCFG cfg;
    private HashSet<Integer> visitFlag = new HashSet<>();
    private Stack<XCFGNode> visitStack = new Stack<>();

    public XCFGReduce(XCFG _cfg){
        cfg = _cfg;
        visitXCFG();
    }

    private void visitXCFG(){
        System.out.println("XCFGReduce begin");
        cfg.globalNodes.forEach(visitStack::push);
        while (!visitStack.empty()) {
            XCFGNode curNode = visitStack.pop();
            visitXCFGNode(curNode);
        }
    }

    private void visitXCFGNode(XCFGNode node) {
        if (node == null || visitFlag.contains(node.nodeID)) return;
        visitFlag.add(node.nodeID);
        System.out.println("reducing "+node);
        while (node.nextNode.size()==1 && (node.nextNode.firstElement().prevNode.size()==1
                || node.instList.isEmpty())){
            visitFlag.add(node.nextNode.firstElement().nodeID);
            cfg.mergeNode(node,node.nextNode.firstElement());
        }
        node.nextNode.forEach(visitStack::push);
//        System.out.println("reduced "+node.nodeID);
    }
}
