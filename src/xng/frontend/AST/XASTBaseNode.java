package xng.frontend.AST;

import xng.frontend.XASTVisitor;

public abstract class XASTBaseNode {
    public XASTNodeID nodeID;

    XASTBaseNode(XASTNodeID _n){
        nodeID = _n;
        System.out.println("XASTBaseNode:"+_n.toString());
    }

}
