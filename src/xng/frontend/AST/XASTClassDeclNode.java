package xng.frontend.AST;

import java.util.Vector;

public class XASTClassDeclNode extends XASTStmtNode {
    public String name;
    public Vector<XASTStmtNode> memberList;

    public XASTClassDeclNode(String _n, Vector<XASTStmtNode> _m){
        super(XASTNodeID.s_classdecl,null);
        name = _n;
        memberList = _m;
    }
}
