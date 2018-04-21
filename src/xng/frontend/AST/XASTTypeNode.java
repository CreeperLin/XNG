package xng.frontend.AST;

import org.antlr.v4.runtime.ParserRuleContext;
import xng.frontend.Symbol.SrcPos;

public class XASTTypeNode extends XASTBaseNode{
    public String className;
    public boolean isArray;
    public int dim;

    public XASTTypeNode(SrcPos ctx, XASTNodeID _t, int _d, String _n){
        super(ctx,_t);
        dim = _d;
        className = _n;
        isArray = (dim>0);
    }

}