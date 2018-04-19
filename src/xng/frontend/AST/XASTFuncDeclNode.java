package xng.frontend.AST;

import org.antlr.v4.runtime.ParserRuleContext;
import xng.frontend.Symbol.SrcPos;

import java.util.Vector;

public class XASTFuncDeclNode extends XASTStmtNode{
    public String name;
    public boolean isConstructor;
    public XASTTypeNode type;
    public XASTStmtNode paramList;
    public XASTStmtNode funcBody;

    public XASTFuncDeclNode(SrcPos ctx, String _n, XASTTypeNode _t, XASTStmtNode _p, XASTStmtNode _b, boolean _c){
        super(ctx,XASTNodeID.s_funcdecl,null);
        name = _n;
        type = _t;
        paramList = _p;
        funcBody = _b;
        isConstructor = _c;
    }
}
