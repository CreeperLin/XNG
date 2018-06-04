package xng.XIR;

import java.util.*;

public class XCFG {
//    Vector<XCFGNode> nodes = new Vector<>();
    private HashSet<XCFGNode> nodes = new HashSet<>();
    public Vector<XIRProcInfo> Proc = new Vector<>();
    public Vector<XIRData> dataList = new Vector<>();
    public XCFGNode entryNode = null;

    public XCFG(){}

    public void addNode(XCFGNode _n){
        nodes.add(_n);
    }

    public XCFGNode addNode(){
        int id = nodes.size();
        XCFGNode n = new XCFGNode(id);
        nodes.add(n);
        System.out.println("XCFG:add node:"+id);
        return n;
    }

    public XCFGNode getNode(int id){
        for (XCFGNode i : nodes) {
            if (i.nodeID == id) {
                return i;
            }
        }
        return null;
    }

    public XCFGNode mergeNode(XCFGNode n1, XCFGNode n2){
//        XCFGNode newNode = addNode();
        System.out.println("XCFG:merging "+n1.nodeID+' '+n2.nodeID);
        n1.nextNode.clear();
        n1.nextNode.addAll(n2.nextNode);
//        n1.prevNode.addAll(n2.prevNode);
//        n2.prevNode.clear();
//        n2.prevNode.addAll(n1.prevNode);
        n1.instList.addAll(n2.instList);
        for (XCFGNode i : n2.nextNode) {
            int idx = i.prevNode.indexOf(n2);
            if (idx!=-1) i.prevNode.set(idx,n1);
        }
        for (XCFGNode i : n2.prevNode) {
            if (i.nodeID!=n1.nodeID){
                int idx = i.nextNode.indexOf(n2);
                if (idx!=-1) i.nextNode.set(idx,n1);
                n1.prevNode.add(i);
            }
        }
//        nodes.remove(n1);
        nodes.remove(n2);
        return n1;
    }

    public void print(){
        if (entryNode == null) return;
        System.out.println("### XCFG print:"+entryNode.nodeID);
        HashSet<Integer> visitFlag = new HashSet<>();
        ArrayDeque<XCFGNode> q = new ArrayDeque<>();
        q.add(entryNode);
        Proc.forEach(i-> q.add(i.entryNode));
        while(!q.isEmpty()){
            XCFGNode cur = q.peek();
            if (cur==null||visitFlag.contains(cur.nodeID)){
                q.pop();
                continue;
            }
            visitFlag.add(cur.nodeID);
            System.out.println(cur);
            q.addAll(cur.nextNode);
        }
    }

}
