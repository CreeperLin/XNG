package xng.frontend;

import xng.frontend.AST.*;

public interface XASTVisitor {

    void visitCUNode(XASTCUNode node);
    void visitClassDeclNode(XASTClassDeclNode node);
    void visitFuncDeclNode(XASTFuncDeclNode node);
    void visitVarDeclNode(XASTVarDeclNode node);
    void visitTypeNode(XASTTypeNode node);
    void visitStmtNode(XASTStmtNode node);
    void visitExprNode(XASTExprNode node);
    void visitPrimNode(XASTPrimNode node);
    void visitCreatorNode(XASTCreatorNode node);
}
