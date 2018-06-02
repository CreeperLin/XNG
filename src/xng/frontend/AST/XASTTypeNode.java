package xng.frontend.AST;

import xng.frontend.Symbol.SrcPos;
import xng.frontend.Symbol.SymbolType;

public class XASTTypeNode extends XASTBaseNode{
    public String className;
    public int dim;

    public XASTTypeNode(SrcPos ctx, XASTNodeID _t, int _d, String _n){
        super(ctx,_t);
        dim = _d;
        className = _n;
    }

}