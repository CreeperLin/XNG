package xng.frontend;

import xng.frontend.AST.*;
import xng.frontend.Symbol.ScopedSymbolTable;

public class SemanticAnalyzer implements XASTVisitor{

    ScopedSymbolTable SST = new ScopedSymbolTable();

    public void visitCUNode(XASTCUNode node){
        node.declList.forEach(this::visitStmtNode);
    }
    public void visitClassDeclNode(XASTClassDeclNode node){

    }
    public void visitFuncDeclNode(XASTFuncDeclNode node){

    }
    public void visitVarDeclNode(XASTVarDeclNode node){

    }
    public void visitTypeNode(XASTTypeNode node){

    }
    public void visitStmtNode(XASTStmtNode node){

    }
    public void visitExprNode(XASTExprNode node){

    }
    public void visitPrimNode(XASTPrimNode node){

    }
    public void visitCreatorNode(XASTCreatorNode node){

    }

}
