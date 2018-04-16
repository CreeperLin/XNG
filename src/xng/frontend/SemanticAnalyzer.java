package xng.frontend;

import xng.common.XException;
import xng.frontend.AST.*;
import xng.frontend.Symbol.*;

import java.util.Vector;

public class SemanticAnalyzer implements XASTVisitor{

    ScopedSymbolTable SST = new ScopedSymbolTable();

    public SemanticAnalyzer(){
    }

    boolean assertExprType(XASTExprNode node, XASTTypeNode type){
        return true;
    }

    public void visitCUNode(XASTCUNode node){
        System.out.println("Semantic Analyze begin");
        SST.push_scope("global");
        node.declList.forEach(this::visitStmtNode);
        SST.pop_scope();
    }
    public void visitClassDeclNode(XASTClassDeclNode node){
        try {
            SST.regSymbol(node.name,SymbolID.symType.sym_class,0);
        } catch (XException e) {
//            e.printStackTrace();
        }
        node.memberList.forEach(this::visitStmtNode);
    }
    public void visitFuncDeclNode(XASTFuncDeclNode node){
        try {
            SST.regSymbol(node.name,SymbolID.symType.sym_func,0);
        } catch (XException e) {
//            e.printStackTrace();
        }
        visitStmtNode(node.funcBody);
    }
    public void visitVarDeclNode(XASTVarDeclNode node){
        try {
            SST.regSymbol(node.name,SymbolID.symType.sym_var,0);
        } catch (XException e) {
//            e.printStackTrace();
        }
    }
    public void visitTypeNode(XASTTypeNode node){

    }
    public void visitStmtNode(XASTStmtNode node){
        if(node==null) return;
        if (node instanceof XASTClassDeclNode){
            visitClassDeclNode((XASTClassDeclNode)node);
            return;
        } else if (node instanceof XASTFuncDeclNode){
            visitFuncDeclNode((XASTFuncDeclNode)node);
            return;
        } else if (node instanceof XASTVarDeclNode) {
            visitVarDeclNode((XASTVarDeclNode)node);
            return;
        } else if (node instanceof XASTExprNode) {
            visitExprNode((XASTExprNode)node);
            return;
        }
        switch (node.nodeID){
            case s_block:
                SST.push_scope(Integer.toString(node.hashCode()));
                if (node.stmtList!=null) node.stmtList.forEach(this::visitStmtNode);
                SST.pop_scope();
                return;
        }
        if (node.stmtList!=null) node.stmtList.forEach(this::visitStmtNode);
    }
    public void visitExprNode(XASTExprNode node){
        if(node==null) return;
        if (node instanceof XASTPrimNode) {
            visitPrimNode((XASTPrimNode)node);
            return;
        }
        Vector<XASTTypeNode> chkType = new Vector<>();
        switch (node.nodeID){
            case e_add:
                chkType.add(new XASTTypeNode(XASTNodeID.t_int,0,null));
                break;
            case e_asgn:
                break;
            case e_eq:
                break;
            case e_band:
                break;
            case e_bneg:
                break;
            case e_bor:
                break;
            case e_bxor:
                break;
            case e_call:
                break;
            case e_creator:
                break;
            case e_dec_p:
                break;
            case e_dec_s:
                break;
            case e_div:
                break;
            case e_ge:
                break;
            case e_gt:
                break;
            case e_idx:
                break;
            case e_inc_p:
                break;
            case e_inc_s:
                break;
            case e_land:
                break;
            case e_le:
                break;
            case e_list:
                break;
            case e_lor:
                break;
            case e_lt:
                break;
            case e_mem:
                break;
            case e_mod:
                break;
            case e_mult:
                break;
            case e_ne:
                break;
            case e_neg:
                break;
            case e_new:
                visitCreatorNode((XASTCreatorNode)node.exprList.elementAt(0));
                return;
            case e_not:
                break;
            case e_pos:
                break;
            case e_shl:
                break;
            case e_shr:
                break;
            case e_sub:
                break;
            case e_none:
                break;
            default:
                return;
        }
        if (node.exprList!=null) node.exprList.forEach(this::visitExprNode);
    }
    public void visitPrimNode(XASTPrimNode node){

    }
    public void visitCreatorNode(XASTCreatorNode node){

    }

}
