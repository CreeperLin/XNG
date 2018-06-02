package xng.frontend.AST;

import org.antlr.v4.runtime.ParserRuleContext;
import xng.frontend.Symbol.SrcPos;

import java.util.Vector;

public class XASTCreatorNode extends XASTExprNode {

    public XASTTypeNode ctype;
    public boolean hasConstructor = false;

    public XASTCreatorNode(SrcPos ctx, XASTTypeNode _t, XASTExprNode _n){
        super(ctx, XASTNodeID.e_creator, (_n == null) ? null : _n.exprList);
        ctype = _t;
    }

}
