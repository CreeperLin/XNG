package xng.backend;

import xng.XIR.*;

import java.util.HashMap;
import java.util.HashSet;

public class StackAllocator {

    private XCFG cfg;
    int curStackPt = 4;
    private HashSet<Integer> visitFlag = new HashSet<>();
    private HashMap<Integer,Integer> basePtMap = new HashMap<>();
//    private HashSet<XIRInstAddr> globalVar = new HashSet<>();

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
//        System.out.println("allocating "+node);
        node.instList.forEach(this::visitXIRInst);
        node.nextNode.forEach(this::visitXCFGNode);
    }

    private void visitXIRInst(XIRInst inst){
        for (XIRInstAddr i : inst.oprList) {
            if (i.type == XIRInstAddr.addrType.a_stack) {
                int t = i.lit2;
                int id = i.lit4;
                int base = curStackPt;
                if (id!=0) {
                    if (!basePtMap.containsKey(id)){
                        basePtMap.put(id,base);
                        curStackPt += t;
                    } else base = basePtMap.get(id);
                } else {
                    curStackPt += t;
                    System.out.println("dbg:"+curStackPt);
                }
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
