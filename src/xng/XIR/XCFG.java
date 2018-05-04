package xng.XIR;

import java.util.HashSet;
import java.util.Vector;

public class XCFG {
//    Vector<XCFGNode> nodes = new Vector<>();
    HashSet<XCFGNode> nodes = new HashSet<>();
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

    public void print(){
        if (entryNode == null) return;
        System.out.println("XCFG print:");
        entryNode.printGraph();
    }

}
