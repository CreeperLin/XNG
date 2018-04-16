package xng.frontend.AST;

public class XASTVarDeclNode extends XASTStmtNode{
    public String name;
    public XASTTypeNode type;
    public XASTExprNode initExpr;

    public XASTVarDeclNode(String _n, XASTTypeNode _t, XASTExprNode _e){
        super(XASTNodeID.s_vardecl,null);
        name = _n;
        type = _t;
        initExpr = _e;
    }
}
