package xng.frontend.AST;

import org.antlr.v4.runtime.ParserRuleContext;
import xng.frontend.Symbol.SrcPos;
import xng.frontend.XASTVisitor;

public abstract class XASTBaseNode {
    public XASTNodeID nodeID;
    public SrcPos pos;

    XASTBaseNode(SrcPos ctx,XASTNodeID _n){
        nodeID = _n;
        pos = ctx;
        System.out.println("XASTBaseNode:"+_n.toString());
    }

}
