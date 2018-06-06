package xng.backend;

import xng.XIR.*;
import xng.opt.InfGraph;
import xng.opt.RegInfo;

import java.util.*;

public class GlobalRegAllocator {
    private XCFG cfg;
    private HashSet<Integer> visitFlag = new HashSet<>();
    private InfGraph inf;
    private int maxColor = 5;
    private int curChrom = 4;
//    private int curColor = 1;
    HashSet<Integer> colorSet = new HashSet<>();

    public GlobalRegAllocator(XCFG _cfg){
        cfg = _cfg;
        for (int i=1;i<=curChrom;++i){
            colorSet.add(i);
        }
        visitXCFG();
    }

//    private int getNextColor() {
//        if (curColor>curChrom) curColor = 1;
//        return curColor++;
//    }

    private boolean colorNode(int n) {
        RegInfo r = inf.getNode(n);
        HashSet<Integer> col = new HashSet<>(colorSet);
        for (Integer i : r.toNodes) {
            RegInfo r1 = inf.getNode(i);
            if (r1.enabled && r1.color!=0) col.remove(r1.color);
        }
        if (col.isEmpty()) {
            r.color = -1;
            return false;
        }
        r.color = col.iterator().next();
        System.out.println("colored:"+n+' '+r.color);
        return true;
    }

    private void visitInfGraph() {
        Stack<Integer> delNodes = new Stack<>();
//        PriorityQueue<Map.Entry<Integer, RegInfo>> delQueue = new PriorityQueue<>(Comparator.comparing((i,j)->{
//            i.getValue()
//        }));

        boolean isEmpty = false;
        boolean del = false;
        int chr = curChrom;
        while (!isEmpty) {
            isEmpty = true;
            for (Map.Entry<Integer, RegInfo> entry : inf.map.entrySet()) {
                int n = entry.getKey();
                RegInfo r = entry.getValue();
                if (r.enabled) {
                    int deg = r.getDeg();
                    if (deg>chr) {
                        System.out.println("push:"+n+' '+deg);
                        delNodes.push(n);
                        del = true;
                        r.enabled = false;
                    } else isEmpty = false;
                }
            }
            if (!del) {
                --chr;
            }
            del = false;
        }
        while (!delNodes.empty()) {
            int n = delNodes.pop();
            inf.getNode(n).enabled = true;
//            System.out.println("coloring:"+n);
            if (!colorNode(n)) {
                System.out.println("color failed:"+n);

            }
        }
    }

    private void visitXCFG(){
        System.out.println("Global Reg Allocation begin");
        for (XIRProcInfo i : cfg.Proc) {
            inf = new InfGraph(i);
            inf.print();
            visitInfGraph();
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
                int col = inf.getNode(i.lit1).color;
                if (col >= 0) {
                    i.copy(XIRInstAddr.newColRegAddr(col));
                }
                else {
                    i.copy(XIRInstAddr.newStackAddr(8));
                }
            }
        }
    }
}
