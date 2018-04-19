package xng.frontend.AST;

import org.antlr.v4.runtime.ParserRuleContext;
import xng.frontend.Symbol.SrcPos;

import java.util.Vector;

public class XASTPrimNode extends XASTExprNode {
    public int intLiteral;
    public String strLiteral;
    public XASTPrimNode(SrcPos ctx, XASTNodeID _t, int _i, String _s){
        super(ctx,_t, null);
        intLiteral = _i;
        strLiteral = _s;
    }

    public XASTPrimNode(SrcPos ctx, XASTNodeID _t, Vector<XASTExprNode> _v){
        super(ctx, _t, _v);
        intLiteral = 0;
        strLiteral = null;
    }
}
