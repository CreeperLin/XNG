package xng.frontend;

import xng.frontend.AST.*;

import static java.lang.System.*;

public class XASTPrinter extends XASTBaseVisitor implements XASTVisitor {

    private int indentLevel = 0;

    private void print(String str){
        int t=indentLevel;
        while (t-->1){
            out.print(" |  ");
        }
        if (t>=0) out.print(" |--");
        out.print(str);
    }

    public void visitCUNode(XASTCUNode node){
        print("AST begin:\nCompilationUnit:\n");
        ++indentLevel;
        node.declList.forEach(this::visitStmt);
        --indentLevel;
    }
    public void visitClassDeclNode(XASTClassDeclNode node){
        print("Class:"+node.name+":\n");
        ++indentLevel;
        node.stmtList.forEach(this::visitStmt);
        --indentLevel;
    }
    public void visitFuncDeclNode(XASTFuncDeclNode node){
        print("Function:"+node.name+":\n");
        ++indentLevel;
        visitTypeNode(node.retType);
        visitStmt(node.paramList);
        visitStmt(node.funcBody);
        --indentLevel;
    }
    public void visitVarDeclNode(XASTVarDeclNode node){
        print("VarDecl:"+node.name+":\n");
        ++indentLevel;
        visitTypeNode(node.type);
        visitExpr(node.initExpr);
        --indentLevel;
    }
    public void visitTypeNode(XASTTypeNode node){
        print("Type:"+node.nodeID.toString()+":"+(node.className==null?"":node.className)+"("+node.dim+")\n");
    }

    public void visitStmtNode(XASTStmtNode node){
        switch(node.nodeID){
            case s_block:
                break;
            case s_break:
                break;
            case s_ret:
                break;
            case s_cont:
                break;
            case s_plist:
                break;
            case s_for:
                break;
            case s_while:
                break;
            case s_if:
                break;
            case s_none:
                break;
            default:
                return;
        }
        print("Statment:"+node.nodeID.toString()+":"+node.pos+":\n");
        ++indentLevel;
        if (node.stmtList!=null) node.stmtList.forEach(this::visitStmt);
        --indentLevel;
    }
    public void visitExprNode(XASTExprNode node){
        switch (node.nodeID){
            case e_add:
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
        print("Expression:"+node.nodeID.toString()+":"+node.pos+":\n");
        ++indentLevel;
        if (node.exprList != null) node.exprList.forEach(this::visitExpr);
        --indentLevel;
    }
    public void visitPrimNode(XASTPrimNode node){
        print("Primary:"+node.nodeID.toString()+":");
        switch (node.nodeID){
            case p_id:
                out.print(node.strLiteral);
                break;
            case p_lit_bool:
                out.print(node.intLiteral);
                break;
            case p_lit_int:
                out.print(node.intLiteral);
                break;
            case p_lit_null:
                break;
            case p_lit_str:
                out.print(node.strLiteral);
                break;
            case p_this:
                break;
            default:
        }
        out.print('\n');
    }
    public void visitCreatorNode(XASTCreatorNode node){
        print("Creator:\n");
        ++indentLevel;
        visitTypeNode(node.ctype);
        if (node.exprList!=null) node.exprList.forEach(this::visitExpr);
        --indentLevel;
    }

}
