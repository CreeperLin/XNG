package xng.frontend;

import xng.common.XException;
import xng.frontend.AST.*;

public class XASTBaseVisitor implements XASTVisitor {
    @Override public void visitCUNode(XASTCUNode node)  {}
    @Override public void visitClassDeclNode(XASTClassDeclNode node) {}
    @Override public void visitFuncDeclNode(XASTFuncDeclNode node) {}
    @Override public void visitVarDeclNode(XASTVarDeclNode node) {}
    @Override public void visitTypeNode(XASTTypeNode node) {}
    @Override public void visitStmtNode(XASTStmtNode node) {}
    @Override public void visitExprNode(XASTExprNode node) {}
    @Override public void visitPrimNode(XASTPrimNode node) {}
    @Override public void visitCreatorNode(XASTCreatorNode node) {}

    public void visitStmt(XASTStmtNode node) {
        if(node==null) return;
        if (node instanceof XASTClassDeclNode) visitClassDeclNode((XASTClassDeclNode)node);
        else if (node instanceof XASTFuncDeclNode) visitFuncDeclNode((XASTFuncDeclNode)node);
        else if (node instanceof XASTVarDeclNode) visitVarDeclNode((XASTVarDeclNode)node);
        else if (node instanceof XASTExprNode) visitExpr((XASTExprNode)node);
        else visitStmtNode(node);
//        System.out.println("debug:stmt:"+node.nodeID.toString());
    }

    public void visitExpr(XASTExprNode node) {
        if (node==null) return;
        if (node instanceof XASTPrimNode) visitPrimNode((XASTPrimNode)node);
        if (node instanceof XASTCreatorNode) visitCreatorNode((XASTCreatorNode)node);
        else visitExprNode(node);
//        System.out.println("debug:expr:"+node.nodeID.toString());
    }
}
