package xng.frontend.AST;

import xng.frontend.Symbol.SrcPos;

public class XASTFuncDeclNode extends XASTStmtNode{
    public String name;
    public boolean isConstructor;
    public XASTTypeNode retType;
    public XASTStmtNode paramList;
    public XASTStmtNode funcBody;

    public XASTFuncDeclNode(SrcPos ctx, String _n, XASTTypeNode _t, XASTStmtNode _p, XASTStmtNode _b, boolean _c){
        super(ctx,XASTNodeID.s_funcdecl,null);
        name = _n;
        retType = _t;
        paramList = _p;
        funcBody = _b;
        isConstructor = _c;
    }
}
