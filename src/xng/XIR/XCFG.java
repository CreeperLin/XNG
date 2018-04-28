package xng.XIR;

import java.util.Vector;

public class XCFG {
    Vector<XCFGNode> nodes = new Vector<>();

    public XCFG(){}

    public void addNode(XCFGNode _n){
        nodes.add(_n);
    }

    public XCFGNode addNode(){
        int id = nodes.size();
        XCFGNode n = new XCFGNode(id);
        nodes.add(n);
        return n;
    }
}
