package xng.XIR;

import java.util.HashSet;
import java.util.Vector;

public class XCFGNode {

    public int nodeID;
    public String name = null;
    public Vector<XIRInst> instList = new Vector<>();
    public Vector<XCFGNode> prevNode = new Vector<>();
    public Vector<XCFGNode> nextNode = new Vector<>();

    public XCFGNode(int _nodeID) {
        nodeID = _nodeID;
        name = "_L" + Integer.toString(nodeID);
    }

    public XIRInst addInst(XIRInst.opType type) {
        XIRInst i = new XIRInst(type);
        instList.add(i);
        return i;
    }

    public void addInst(XIRInst _inst) {
        if (_inst!=null) instList.add(_inst);
        System.out.println(_inst);
    }

    public void addInst(Vector<XIRInst> _v){
        instList.addAll(_v);
        _v.forEach(System.out::println);
    }

    public void linkTo(XCFGNode _n){
        assert _n != null;
        for (XCFGNode i: nextNode){
            if (i.nodeID==_n.nodeID) {
//                System.out.println("XCFGNode:error:duplicate link "+nodeID+"->"+_n.nodeID);
                return;
            }
        }
        nextNode.add(_n);
        System.out.println("XCFGNode:link to:"+nodeID+"->"+_n.nodeID);
        _n.linkFrom(this);
    }

    public void linkFrom(XCFGNode _n){
        assert _n != null;
        for (XCFGNode i: prevNode){
            if (i.nodeID==_n.nodeID) {
//                System.out.println("XCFGNode:error:duplicate link "+nodeID+"<-"+_n.nodeID);
                return;
            }
        }
        prevNode.add(_n);
        System.out.println("XCFGNode:link from:"+_n.nodeID+"->"+nodeID);
        _n.linkTo(this);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("XCFGNode:");
        sb.append(nodeID).append("\n|----\n|Prev:\n|");
        prevNode.forEach(i-> sb.append(i.nodeID).append(' '));
        sb.append("\n|Next:\n|");
        nextNode.forEach(i-> sb.append(i.nodeID).append(' '));
        sb.append("\n|-----\n|Inst:\n|");
        instList.forEach(i-> sb.append(i).append("\n|"));
        return sb.toString();
    }
//
//    public void printGraph() {
//        System.out.println(this);
//        nextNode.forEach(XCFGNode::printGraph);
//    }
}
