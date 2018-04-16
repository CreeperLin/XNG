package xng.frontend.AST;

public class XASTTypeNode extends XASTBaseNode{
    public String className;
    public boolean isArray;
    public int dim;

    public XASTTypeNode(XASTNodeID _t, int _d, String _n){
        super(_t);
        dim = _d;
        className = _n;
        isArray = (dim>0);
    }

}
