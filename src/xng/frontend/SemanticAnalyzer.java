package xng.frontend;

import xng.common.XCompileError;
import xng.frontend.AST.*;
import xng.frontend.Symbol.*;

public class SemanticAnalyzer extends XASTBaseVisitor implements XASTVisitor{

    ScopedSymbolTable SST;
    XCompileError ce;
    String curClassName = null;

    public SemanticAnalyzer(ScopedSymbolTable _S, XCompileError _ce){
        SST = _S;
        ce = _ce;
    }

    String getScopeName(String name, boolean con){
        return (curClassName==null?"":curClassName+'.')+(con?"__init__":name);
    }

    boolean assertExprType(XASTExprNode node, SymbolType type){
        return true;
    }

    public void visitCUNode(XASTCUNode node){
        System.out.println("Semantic Analyze begin");
//        SST.push_scope("global");
        node.declList.forEach(this::visitStmt);
        SST.pop_scope();
    }
    public void visitClassDeclNode(XASTClassDeclNode node){
        curClassName=node.name;
        System.out.println("curClass:"+curClassName);
        node.stmtList.forEach(this::visitStmt);
        System.out.println("exit Class");
        curClassName=null;
    }
    public void visitFuncDeclNode(XASTFuncDeclNode node){
        SST.push_scope(Integer.toString(node.hashCode()));
        visitStmtNode(node.paramList);
        node.funcBody.stmtList.forEach(this::visitStmt);
        SST.pop_scope();
    }
    public void visitVarDeclNode(XASTVarDeclNode node){
        visitTypeNode(node.type);
        visitExprNode(node.initExpr);
        if (SST.symTableStack.size()>1&& SST.regSymbol(getScopeName(node.name, false), new SymbolType(node.type), 0)) {
            ce.add(XCompileError.ceType.ce_redef,"var:"+node.name,node);
        }
    }
    public void visitTypeNode(XASTTypeNode node){
        switch (node.nodeID){
            case t_void:
                if (node.dim>0){
                    ce.add(XCompileError.ceType.ce_invalid_type,"void",node);
                }
                break;
            case t_int:
                break;
            case t_bool:
                break;
            case t_class:
                SymbolID t = SST.findSymbol(node.className);
                if (t==null || t.type.declType!=SymbolType.typType.CLASS) {
                    ce.add(XCompileError.ceType.ce_invalid_type,node.className,node);
                }
                break;
            case t_str:
                break;
        }
    }
    public void visitStmtNode(XASTStmtNode node){
        if (node==null) return;
        switch (node.nodeID){
            case s_block:
                SST.push_scope(Integer.toString(node.hashCode()));
                if (node.stmtList!=null) node.stmtList.forEach(this::visitStmt);
                SST.pop_scope();
                return;
            case s_break:
                break;
            case s_ret:
                break;
            case s_cont:
                break;
            case s_plist:
                break;
            case s_for:
                SST.push_scope(Integer.toString(node.hashCode()));
                if (node.stmtList!=null) node.stmtList.forEach(this::visitStmt);
                SST.pop_scope();
                return;
            case s_while:
                break;
            case s_if:
                break;
            case s_none:
                return;
            default:
                return;
        }
        if (node.stmtList!=null) node.stmtList.forEach(this::visitStmt);
    }
    public void visitExprNode(XASTExprNode node){
        if (node == null) return;
        if (node.exprList==null) return;
        node.exprList.forEach(this::visitExpr);
        switch (node.nodeID){
            case e_add:
                if (assertExprType(node.exprList.elementAt(0),SymbolType.intType)&&
                        assertExprType(node.exprList.elementAt(1),SymbolType.intType)){
                }
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
                break;
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
        if (node.exprList!=null) node.exprList.forEach(this::visitExpr);
    }
    public void visitPrimNode(XASTPrimNode node){
        switch (node.nodeID){
            case p_id:
                if (SST.findSymbol(getScopeName(node.strLiteral,false))==null){
                    ce.add(XCompileError.ceType.ce_nodecl,node.strLiteral,node);
                }
                break;
            case p_lit_bool:
                break;
            case p_lit_int:
                break;
            case p_lit_null:
                break;
            case p_lit_str:
                break;
            case p_this:
                break;
            case p_expr:
                break;
            default:
        }
    }
    public void visitCreatorNode(XASTCreatorNode node){

    }

}
