package xng.frontend.AST;

import xng.frontend.Symbol.SrcPos;

import java.util.Vector;

public class XASTCUNode extends XASTBaseNode {
    public Vector<XASTStmtNode> declList;
    public XASTCUNode(SrcPos ctx){
        super(ctx,XASTNodeID.cu_root);
        declList = new Vector<>();
    }
    public void add(XASTStmtNode node){
        declList.add(node);
    }
}
