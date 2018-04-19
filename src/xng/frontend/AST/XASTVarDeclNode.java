package xng.frontend.AST;

import org.antlr.v4.runtime.ParserRuleContext;
import xng.frontend.Symbol.SrcPos;

public class XASTVarDeclNode extends XASTStmtNode{
    public String name;
    public XASTTypeNode type;
    public XASTExprNode initExpr;

    public XASTVarDeclNode(SrcPos ctx, String _n, XASTTypeNode _t, XASTExprNode _e){
        super(ctx ,XASTNodeID.s_vardecl,null);
        name = _n;
        type = _t;
        initExpr = _e;
    }
}
