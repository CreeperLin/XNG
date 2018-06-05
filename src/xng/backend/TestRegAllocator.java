package xng.backend;

import xng.XIR.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Vector;

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
        Vector<XIRInstAddr> oprList = inst.oprList;
        for (int i1 = 0; i1 < oprList.size(); i1++) {
            XIRInstAddr i = oprList.get(i1);
            if (i.type == XIRInstAddr.addrType.a_reg && i.lit1>0) {
                i.copy(XIRInstAddr.newStackAddr(8));
            }
        }
    }

}
