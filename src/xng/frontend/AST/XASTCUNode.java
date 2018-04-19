package xng.frontend.AST;

import java.util.Vector;

public class XASTCUNode extends XASTBaseNode {
    public Vector<XASTStmtNode> declList;
    public XASTCUNode(){
        super(null,XASTNodeID.cu_root);
        declList = new Vector<>();
    }
    public void add(XASTStmtNode node){
        declList.add(node);
    }
}
