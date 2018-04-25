package xng.frontend.AST;

import xng.frontend.Symbol.SrcPos;

public abstract class XASTBaseNode {
    public XASTNodeID nodeID;
    public SrcPos pos;

    XASTBaseNode(SrcPos ctx,XASTNodeID _n){
        nodeID = _n;
        pos = ctx;
//        System.out.println("XASTBaseNode:"+_n.toString());
    }

}
