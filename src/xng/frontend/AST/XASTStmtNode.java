package xng.frontend.AST;

import org.antlr.v4.runtime.ParserRuleContext;
import xng.frontend.Symbol.SrcPos;

import java.util.Vector;

public class XASTStmtNode extends XASTBaseNode{

    public Vector<XASTStmtNode> stmtList;

    public XASTStmtNode(){
        super(null,XASTNodeID.s_none);
        stmtList = null;
    }

    public XASTStmtNode(SrcPos ctx, XASTNodeID type, Vector<XASTStmtNode> list){
        super(ctx,type);
        stmtList = list;
    }
}
