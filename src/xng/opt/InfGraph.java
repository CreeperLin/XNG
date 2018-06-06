package xng.opt;

import xng.XIR.XCFGNode;
import xng.XIR.XIRInst;
import xng.XIR.XIRInstAddr;
import xng.XIR.XIRProcInfo;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Vector;

public class InfGraph {

    public HashMap<Integer,RegInfo> map = new HashMap<>();
    private HashSet<Integer> visitFlag = new HashSet<>();

    private void makeGraph(XCFGNode node) {
        if (visitFlag.contains(node.nodeID)) return;
        visitFlag.add(node.nodeID);
        System.out.println("making:"+node.nodeID);
        HashSet<Integer> liveNow = new HashSet<>();
        XIRInst last = null;
        for (int i = node.instList.size()-1; i >= 0; i--) {
            XIRInst inst = node.instList.get(i);
            if (last == null) liveNow.addAll(inst.info.LiveOutState);
            last = inst;
            System.out.println("check:"+inst+' '+liveNow);
            if (inst.op == XIRInst.opType.op_mov && inst.oprList.get(1).type == XIRInstAddr.addrType.a_reg) {
                liveNow.removeAll(inst.info.useReg);
            }
            if (inst.info.defReg!=0) {
                liveNow.forEach(t->{
                    addEdge(inst.info.defReg,t);
                });
            }
            liveNow.remove(inst.info.defReg);
            liveNow.addAll(inst.info.useReg);

//            Vector<Integer> tmp = new Vector<>(inst.info.LiveInState);
//            System.out.println(tmp);
//            for (int i1 = 0; i1 < tmp.size(); i1++) {
//                Integer u = tmp.get(i1);
//                for (int i2 = 1 + i1; i2 < tmp.size(); i2++) {
//                    Integer v = tmp.get(i2);
//                    addEdge(u,v);
//                }
//            }
        }
        node.nextNode.forEach(this::makeGraph);
    }

    public InfGraph(XIRProcInfo proc) {
        map.clear();
        makeGraph(proc.entryNode);
    }

    public RegInfo getNode(int t) {
        if(map.containsKey(t)) {
            return map.get(t);
        }
        RegInfo i = new RegInfo();
        map.put(t,i);
        return i;
    }

    public void addEdge(int u, int v) {
        if (u==v) return;
        System.out.println("InfGraph:add:"+u+' '+v);
        getNode(u).toNodes.add(v);
        getNode(v).toNodes.add(u);
    }

    public void removeEdge(int u, int v) {
        getNode(u).toNodes.remove(v);
        getNode(v).toNodes.remove(u);
    }

    public void print() {
        System.out.println("InfGraph:");
        map.forEach((i,j)->{
            System.out.print(i+"->[");
            j.toNodes.forEach(t->{
                System.out.print(t+" ");
            });
            System.out.println("]");
        });
    }
}
