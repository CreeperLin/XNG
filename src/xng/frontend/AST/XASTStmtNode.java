package xng.frontend.AST;

import org.antlr.v4.runtime.ParserRuleContext;
import xng.XIR.XCFGNode;
import xng.frontend.Symbol.SrcPos;

import java.util.Vector;

public class XASTStmtNode extends XASTBaseNode{

    public Vector<XASTStmtNode> stmtList;
    public XCFGNode startNode = null;
    public XCFGNode endNode = null;

    public XASTStmtNode(){
        super(new SrcPos(1,0),XASTNodeID.s_none);
        stmtList = new Vector<>();
    }

    public XASTStmtNode(SrcPos ctx, XASTNodeID type, Vector<XASTStmtNode> list){
        super(ctx,type);
        stmtList = list;
    }

    public boolean isEmpty(){return nodeID==XASTNodeID.s_none;}
}
