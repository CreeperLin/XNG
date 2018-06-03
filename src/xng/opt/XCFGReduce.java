package xng.opt;

import xng.XIR.XCFG;
import xng.XIR.XCFGNode;
import xng.XIR.XIRInst;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Stack;
import java.util.Vector;

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
        System.out.println("reducing "+node.nodeID);
        while (node.nextNode.size()==1 && (node.nextNode.firstElement().prevNode.size()==1
                || (node.instList.isEmpty()&&!node.isGlobal))){
            Vector<XIRInst> instList = node.instList;
            for (int i1 = 0; i1 < instList.size(); i1++) {
                XIRInst i = instList.get(i1);
                if (i.op == XIRInst.opType.op_jmp) {
                    instList.remove(i1);
                    break;
                }
            }
            visitFlag.add(node.nextNode.firstElement().nodeID);
            cfg.mergeNode(node,node.nextNode.firstElement());
        }
        node.nextNode.forEach(visitStack::push);
//        System.out.println("reduced "+node.nodeID);
    }
}
