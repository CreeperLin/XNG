package xng.frontend.AST;

import java.util.Vector;

public class XASTPrimNode extends XASTExprNode {
    public int intLiteral;
    public String strLiteral;
    public XASTPrimNode(XASTNodeID _t, int _i, String _s){
        super(_t, null);
        intLiteral = _i;
        strLiteral = _s;
    }

    public XASTPrimNode(XASTNodeID _t, Vector<XASTExprNode> _v){
        super(_t, _v);
        intLiteral = 0;
        strLiteral = null;
    }
}
