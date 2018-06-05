package xng.backend;

import xng.XIR.*;

import java.util.HashMap;
import java.util.HashSet;

public class TestRegAllocator {
    private XCFG cfg;
    private int curStackPt = 8;
    private HashSet<Integer> visitFlag = new HashSet<>();

    public TestRegAllocator(XCFG _cfg){
        cfg = _cfg;
        visitXCFG();
    }

    private void visitXCFG(){
        System.out.println("Test Reg Allocation begin");
        for (XIRProcInfo i : cfg.Proc) {
            visitXCFGNode(i.entryNode);
        }
    }

    private void visitXCFGNode(XCFGNode node) {
        if (visitFlag.contains(node.nodeID)) return;
        visitFlag.add(node.nodeID);
        System.out.println("allocating reg:"+node.nodeID);
        node.instList.forEach(this::visitXIRInst);
        node.nextNode.forEach(this::visitXCFGNode);
    }

    private void visitXIRInst(XIRInst inst){
        for (XIRInstAddr i : inst.oprList) {

            if (i.type == XIRInstAddr.addrType.a_stack) {
                int t = i.lit2;
                int id = i.lit4;
                int base = curStackPt;
                System.out.println("StackAllocator:"+id+" "+base+" "+i.lit3);
                i.type = XIRInstAddr.addrType.a_mem;
                i.lit1 = -1;
                i.lit2 = 0;
                i.lit4 = -base - i.lit3;
                i.lit3 = 0;
            }
        }
    }

}
