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
    private static int curChrom = 4;
    HashSet<Integer> colorSet = new HashSet<>();

    public GlobalRegAllocator(XCFG _cfg){
        cfg = _cfg;
        for (int i=1;i<=curChrom;++i){
            colorSet.add(i);
        }
        visitXCFG();
    }

    class RegInfoPair {
        public int reg;
        public RegInfo info;

        public RegInfoPair(int _t, RegInfo _r) {
            reg = _t;
            info = _r;
        }
    }

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

    private static float spillMetric(RegInfo info) {
//        System.out.println("spill:"+info.varInfo+' '+info.getDeg());
        return (float)(25+info.varInfo.getRefCount()) / info.getDeg();
    }

    private static Comparator<RegInfoPair> regComparator = new Comparator<RegInfoPair>() {
        @Override
        public int compare(RegInfoPair regInfoPair, RegInfoPair t1) {
            float t = (spillMetric(t1.info) - spillMetric(regInfoPair.info));
            if (t<0) return -1;
            if (t>0) return 1;
            return 0;
        }
    };

    private void visitInfGraph() {
        Stack<Integer> delNodes = new Stack<>();
        PriorityQueue<RegInfoPair> delQueue = new PriorityQueue<>(regComparator);
        for (Map.Entry<Integer, RegInfo> entry : inf.map.entrySet()) {
            int n = entry.getKey();
            RegInfo r = entry.getValue();
            delQueue.add(new RegInfoPair(n,r));
        }
        while (!delQueue.isEmpty()) {
            RegInfoPair pair = delQueue.poll();
            int reg = pair.reg;
            System.out.println("queue:"+reg+' '+pair.info.getDeg()+' '+pair.info.varInfo+' '+spillMetric(pair.info));
            inf.getNode(reg).enabled = true;
            if (!colorNode(reg)) {
                System.out.println("color failed:"+reg);
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
