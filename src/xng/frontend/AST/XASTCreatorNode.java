package xng.frontend.AST;

import java.util.Vector;

public class XASTCreatorNode extends XASTExprNode {

    public XASTTypeNode type;

    public XASTCreatorNode(XASTTypeNode _t, XASTExprNode _n){
        super(XASTNodeID.e_creator,_n.exprList);
        type = _t;
    }

}
