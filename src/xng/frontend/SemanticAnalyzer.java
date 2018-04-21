package xng.frontend;

import xng.common.XCompileError;
import xng.frontend.AST.*;
import xng.frontend.Symbol.*;

import java.util.HashMap;

public class SemanticAnalyzer extends XASTBaseVisitor implements XASTVisitor{

    ScopedSymbolTable SST;
    XCompileError ce;
    String curClassName = null;
    boolean isLoop = false;
    HashMap<Integer,SymbolType> exprTypeTable = new HashMap<>();
    HashMap<Integer,Boolean> exprLvTable = new HashMap<>();

    public SemanticAnalyzer(ScopedSymbolTable _S, XCompileError _ce){
        SST = _S;
        ce = _ce;
    }

    String getScopeName(String name, boolean con){
        return (curClassName==null?"":curClassName+'.')+(con?"__init__":name);
    }

    void declExprType(XASTExprNode node){
        if (node == null || node.type != null) return;
        if (node instanceof XASTPrimNode && node.nodeID!=XASTNodeID.p_expr) {
            declPrimType((XASTPrimNode) node);
            return;
        }
        if (node.exprList != null) return;
        node.exprList.forEach(this::declExprType);
        switch (node.nodeID){
            case e_sub:
            case e_div:
            case e_mod:
            case e_mult:
            case e_inc_p:
            case e_inc_s:
            case e_dec_p:
            case e_dec_s:
            case e_pos:
            case e_neg:
            case e_shl:
            case e_shr:
            case e_band:
            case e_bneg:
            case e_bor:
            case e_bxor:
            case e_add:
            case e_ge:
            case e_gt:
            case e_le:
            case e_lt:
            case e_ne:
                if (node.exprList.elementAt(0).type.equals(SymbolType.intType)
                        &&node.exprList.elementAt(1).type.equals(SymbolType.intType)){
                    node.type = SymbolType.intType;
                }
                break;
            case e_asgn:
                node.type = node.exprList.elementAt(0).type;
                break;
            case e_eq:
                break;
            case e_call:
                break;
            case e_creator:
                break;
            case e_idx:
                break;
            case e_land:
            case e_lor:
                break;
            case e_list:
                break;
            case e_mem:
                break;
            case e_new:
                break;
            case e_not:
                break;
            case e_none:
                break;
            default:
        }
    }

    void declPrimType(XASTPrimNode node){
        switch (node.nodeID){
            case p_lit_int:
                node.type = SymbolType.intType;
                break;
            case p_lit_bool:
                node.type = SymbolType.boolType;
                break;
            case p_lit_null:
                node.type = SymbolType.boolType;
                break;
            case p_lit_str:
                node.type = SymbolType.strType;
                break;
            case p_this:
                node.type = new SymbolType(SymbolType.typType.CLASS,curClassName,0);
                break;
            case p_id:
                node.type = SST.findSymbol(node.strLiteral).type;
                if (node.type == null) {
                    ce.add(XCompileError.ceType.ce_nodecl,"prim:"+node.strLiteral,node);
                }
                break;
        }
    }

    boolean checkLv(XASTExprNode node){
        if (node == null || node.type != null) return false;
        if (node instanceof XASTPrimNode && node.nodeID!=XASTNodeID.p_expr) {
            return node.nodeID==XASTNodeID.p_id;
        }
        switch (node.nodeID){
            case e_none:
        }
        return false;
    }

    boolean assertExprType(XASTExprNode node, SymbolType type){
        return true;
    }

    public void visitCUNode(XASTCUNode node){
        System.out.println("Semantic Analyze begin");
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
        assertExprType(node.initExpr,new SymbolType(node.type));
        if (SST.symTableStack.size()>1 && SST.regSymbol(getScopeName(node.name, false), new SymbolType(node.type), 0)) {
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
            case s_ret:
                break;
            case s_cont:
//                break;
            case s_break:
                if (!isLoop) {
                    ce.add(XCompileError.ceType.ce_outofloop,node.nodeID.toString(),node);
                }
                break;
            case s_plist:
                break;
            case s_for:
                assertExprType((XASTExprNode)node.stmtList.elementAt(1),SymbolType.boolType);
                isLoop = true;
                node.stmtList.forEach(this::visitStmt);
                isLoop = false;
                return;
            case s_while:
                assertExprType((XASTExprNode)node.stmtList.elementAt(0),SymbolType.boolType);
                isLoop = true;
                node.stmtList.forEach(this::visitStmt);
                isLoop = false;
                return;
            case s_if:
                assertExprType((XASTExprNode)node.stmtList.elementAt(0),SymbolType.boolType);
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
                assertExprType(node.exprList.elementAt(0),SymbolType.funcType);
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
                assertExprType(node.exprList.elementAt(0),SymbolType.classType);
                assertExprType(node.exprList.elementAt(0),SymbolType.strType);
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
        }
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
