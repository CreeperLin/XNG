package xng.frontend.AST;

import java.util.Vector;

public class XASTStmtNode extends XASTBaseNode{

    public Vector<XASTStmtNode> stmtList;

    public XASTStmtNode(){
        super(XASTNodeID.s_none);
        stmtList = null;
    }

    public XASTStmtNode(XASTNodeID type, Vector<XASTStmtNode> list){
        super(type);
        stmtList = list;
    }
}
