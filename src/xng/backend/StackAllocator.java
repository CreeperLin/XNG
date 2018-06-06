package xng.backend;

import xng.XIR.*;

import java.util.HashMap;
import java.util.HashSet;

public class StackAllocator {

    private XCFG cfg;
    private int curStackPt = 8;
    private HashSet<Integer> visitFlag = new HashSet<>();
//    private HashMap<Integer,Integer> basePtMap = new HashMap<>();
//    private HashSet<XIRInstAddr> globalVar = new HashSet<>();

    public StackAllocator(XCFG _cfg){
        cfg = _cfg;
        visitXCFG();
    }

    private void visitXCFG(){
        System.out.println("StackAllocation begin");
        for (XIRProcInfo i : cfg.Proc) {
            visitXCFGNode(i.entryNode);
            i.stackSize = curStackPt;
            curStackPt = 8;
        }
    }

    private void visitXCFGNode(XCFGNode node) {
        if (visitFlag.contains(node.nodeID)) return;
        visitFlag.add(node.nodeID);
        System.out.println("allocating "+node.nodeID);
        node.instList.forEach(this::visitXIRInst);
        node.nextNode.forEach(this::visitXCFGNode);
    }

    private void visitXIRInst(XIRInst inst){
        for (XIRInstAddr i : inst.oprList) {
            if (i.type == XIRInstAddr.addrType.a_stack) {
                int t = i.lit2;
                int base = curStackPt;
                curStackPt += t;
                System.out.println("StackAllocator:"+base);
                i.type = XIRInstAddr.addrType.a_mem;
                i.lit1 = -1;
                i.lit2 = 0;
                i.lit4 = -base;
                i.lit3 = 0;
                i.addr1 = null;
                i.addr2 = null;
            }
        }
    }
}
