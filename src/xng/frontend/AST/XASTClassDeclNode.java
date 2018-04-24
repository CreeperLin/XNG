package xng.frontend.AST;

import org.antlr.v4.runtime.ParserRuleContext;
import xng.frontend.Symbol.SrcPos;

import java.util.Vector;

public class XASTClassDeclNode extends XASTStmtNode {
    public String name;

    public XASTClassDeclNode(SrcPos ctx, String _n, Vector<XASTStmtNode> _m){
        super(ctx, XASTNodeID.s_classdecl,_m);
        name = _n;
    }
}
