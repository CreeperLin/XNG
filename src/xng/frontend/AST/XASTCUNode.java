package xng.frontend.AST;

import java.util.Vector;

public class XASTCUNode extends XASTBaseNode {
    public Vector<XASTStmtNode> declList;
    public XASTCUNode(){
        super(XASTNodeID.cu_root);
        declList = new Vector<XASTStmtNode>();
    }
    public void add(XASTStmtNode node){
        declList.add(node);
    }
}
