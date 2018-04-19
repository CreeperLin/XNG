package xng.frontend.AST;

import org.antlr.v4.runtime.ParserRuleContext;
import xng.frontend.Symbol.SrcPos;
import xng.frontend.Symbol.SymbolType;

import java.util.Vector;

public class XASTExprNode extends XASTStmtNode{

    public Vector<XASTExprNode> exprList;
    public SymbolType type;

    XASTExprNode(){}

    public XASTExprNode(SrcPos ctx, XASTNodeID _e, Vector<XASTExprNode> _l){
        super(ctx, _e,null);
        exprList = _l;
    }

}
