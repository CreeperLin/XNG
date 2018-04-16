package xng.frontend.AST;

import java.util.Vector;

public class XASTExprNode extends XASTStmtNode{

    public Vector<XASTExprNode> exprList;

    XASTExprNode(){}

    public XASTExprNode(XASTNodeID _e,Vector<XASTExprNode> _l){
        super(_e,null);
        exprList = _l;
    }

}
