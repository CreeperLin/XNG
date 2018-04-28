package xng.frontend;

import xng.XIR.XCFG;
import xng.XIR.XCFGNode;
import xng.XIR.XIRInst;
import xng.XIR.XIRInstAddr;
import xng.frontend.AST.*;

import static java.lang.System.out;

public class XIRGenerator extends XASTBaseVisitor implements XASTVisitor {

    private XCFG cfg;
    private XCFGNode curNode = null;

    public XIRGenerator(XCFG _cfg){
        cfg = _cfg;
    }

    public void visitCUNode(XASTCUNode node){
        out.println("XIRGen begin\n");
        node.declList.forEach(this::visitStmt);
    }
    public void visitClassDeclNode(XASTClassDeclNode node){
        out.println("Class:"+node.name+":\n");
        node.stmtList.forEach(this::visitStmt);
    }
    public void visitFuncDeclNode(XASTFuncDeclNode node){
        out.println("Function:"+node.name+":\n");
        visitTypeNode(node.retType);
        visitStmt(node.paramList);
        visitStmt(node.funcBody);
    }
    public void visitVarDeclNode(XASTVarDeclNode node){
        out.println("VarDecl:"+node.name+":\n");
        visitTypeNode(node.type);
        visitExpr(node.initExpr);
    }
    public void visitTypeNode(XASTTypeNode node){
        out.println("Type:"+node.nodeID.toString()+":"+(node.className==null?"":node.className)+"("+node.dim+")\n");
    }

    public void visitStmtNode(XASTStmtNode node){
        if (node.stmtList!=null) node.stmtList.forEach(this::visitStmt);
        XCFGNode cfgNode = cfg.addNode();
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
        out.println("Statment:"+node.nodeID.toString()+":"+node.pos+":\n");
    }
    public void visitExprNode(XASTExprNode node){
        if(node.exprList!=null) node.exprList.forEach(this::visitExpr);
        XIRInst.opType type;
        switch (node.nodeID){
            case e_add:
                type = XIRInst.opType.op_add;
                XIRInst inst = new XIRInst(type);
                node.instAddr = XIRInstAddr.newRegAddr();
                inst.oprList.add(node.instAddr);
                inst.oprList.add(node.exprList.elementAt(0).instAddr);
                inst.oprList.add(node.exprList.elementAt(1).instAddr);
                node.instList.add(inst);
                break;
            case e_asgn:
                type = XIRInst.opType.op_mov;
                break;
            case e_eq:
                type = XIRInst.opType.op_eq;
                break;
            case e_band:
                type = XIRInst.opType.op_and;
                break;
            case e_bneg:
                type = XIRInst.opType.op_neg;
                break;
            case e_bor:
                type = XIRInst.opType.op_or;
                break;
            case e_bxor:
                type = XIRInst.opType.op_xor;
                break;
            case e_call:
                type = XIRInst.opType.op_call;
                break;
            case e_creator:
                break;
            case e_dec_p:
            case e_dec_s:
                type = XIRInst.opType.op_dec;
                break;
            case e_div:
                type = XIRInst.opType.op_div;
                break;
            case e_ge:
                type = XIRInst.opType.op_ge;
                break;
            case e_gt:
                type = XIRInst.opType.op_gt;
                break;
            case e_idx:
                type = XIRInst.opType.op_add;
                break;
            case e_inc_p:
            case e_inc_s:
                type = XIRInst.opType.op_inc;
                break;
            case e_land:
                type = XIRInst.opType.op_and;
                break;
            case e_le:
                type = XIRInst.opType.op_le;
                break;
            case e_list:
                break;
            case e_lor:
                type = XIRInst.opType.op_or;
                break;
            case e_lt:
                type = XIRInst.opType.op_lt;
                break;
            case e_mem:
                break;
            case e_mod:
                type = XIRInst.opType.op_mod;
                break;
            case e_mult:
                type = XIRInst.opType.op_mult;
                break;
            case e_ne:
                type = XIRInst.opType.op_ne;
                break;
            case e_neg:
                type = XIRInst.opType.op_neg;
                break;
            case e_new:
                break;
            case e_not:
                type = XIRInst.opType.op_not;
                break;
            case e_pos:
                type = XIRInst.opType.op_pos;
                break;
            case e_shl:
                type = XIRInst.opType.op_shl;
                break;
            case e_shr:
                type = XIRInst.opType.op_shr;
                break;
            case e_sub:
                type = XIRInst.opType.op_sub;
                break;
            case e_none:
                break;
            default:
                return;
        }
        out.println("Expression:"+node.nodeID.toString()+":"+node.pos+":\n");
    }
    public void visitPrimNode(XASTPrimNode node){
        out.println("Primary:"+node.nodeID.toString()+":");
        switch (node.nodeID){
            case p_id:
                out.println(node.strLiteral);
                break;
            case p_lit_bool:
                out.println(node.intLiteral);
                break;
            case p_lit_int:
                out.println(node.intLiteral);
                break;
            case p_lit_null:
                break;
            case p_lit_str:
                out.println(node.strLiteral);
                break;
            case p_this:
                break;
            default:
        }
        out.println('\n');
    }
    public void visitCreatorNode(XASTCreatorNode node){
        out.println("Creator:\n");
        if (node.exprList!=null) node.exprList.forEach(this::visitExpr);
        visitTypeNode(node.ctype);
    }

}
