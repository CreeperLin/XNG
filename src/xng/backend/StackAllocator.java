package xng.backend;

import xng.XIR.XCFG;
import xng.XIR.XCFGNode;
import xng.XIR.XIRInst;
import xng.XIR.XIRInstAddr;

import java.util.HashMap;
import java.util.HashSet;

public class StackAllocator {

    private XCFG cfg;
    int curStackPt = 4;
    private HashSet<Integer> visitFlag = new HashSet<>();

    public StackAllocator(XCFG _cfg){
        cfg = _cfg;
        visitXCFG();
    }

    private void visitXCFG(){
        System.out.println("StackAllocation begin");
        for (XCFGNode globalNode : cfg.globalNodes) {
            visitXCFGNode(globalNode);
            curStackPt = 4;
        }
    }

    private void visitXCFGNode(XCFGNode node) {
        if (visitFlag.contains(node.nodeID)) return;
        visitFlag.add(node.nodeID);
        System.out.println("allocating "+node);
        node.instList.forEach(this::visitXIRInst);
        node.nextNode.forEach(this::visitXCFGNode);
    }

    private void visitXIRInst(XIRInst inst){
        for (XIRInstAddr i : inst.oprList) {
            if (i.type == XIRInstAddr.addrType.a_stack) {
                int t = i.lit2;
                i.type = XIRInstAddr.addrType.a_mem;
                i.lit1 = -1;
                i.lit2 = 0;
                i.lit3 = 0;
                i.lit4 = -curStackPt;
                curStackPt += t;
            }
        }
    }
}
