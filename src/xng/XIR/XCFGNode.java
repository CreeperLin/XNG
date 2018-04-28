package xng.XIR;

import java.util.Vector;

public class XCFGNode {

    int nodeID;
    Vector<XIRInst> instList = new Vector<>();
    Vector<XCFGNode> prevNodeId = new Vector<>();
    Vector<XCFGNode> nextNodeId = new Vector<>();

    public XCFGNode(int _nodeID) {
        nodeID = _nodeID;
    }

    public void addInst(XIRInst _inst){
        instList.add(_inst);
    }

    public void linkTo(XCFGNode _n){
        for (XCFGNode i:nextNodeId){
            if (i.nodeID==_n.nodeID) {
                System.err.println("XCFGNode:error:duplicate link"+i+"/"+nodeID);
                return;
            }
        }
        nextNodeId.add(_n);
        _n.linkFrom(this);
    }

    public void linkFrom(XCFGNode _n){
        for (XCFGNode i:prevNodeId){
            if (i.nodeID==_n.nodeID) {
                System.err.println("XCFGNode:error:duplicate link"+i+"/"+nodeID);
                return;
            }
        }
        prevNodeId.add(_n);
        _n.linkTo(this);
    }

}
