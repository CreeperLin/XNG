package xng.frontend;

import xng.XIR.XCFG;
import xng.XIR.XCFGNode;
import xng.XIR.XIRInst;
import xng.XIR.XIRInstAddr;
import xng.frontend.AST.*;
import xng.frontend.Symbol.ScopedSymbolTable;

import static java.lang.System.out;

public class XIRGenerator extends XASTBaseVisitor implements XASTVisitor {

    private XCFG cfg;
    private ScopedSymbolTable SST;
//    private XCFGNode curNode = null;

    public XIRGenerator(XCFG _cfg,ScopedSymbolTable _sst){
        cfg = _cfg;
        SST = _sst;
    }

    public void visitCUNode(XASTCUNode node){
        out.println("XIRGen begin");
//        curNode = cfg.addNode();
        node.declList.forEach(this::visitStmt);
    }
    public void visitClassDeclNode(XASTClassDeclNode node){
        out.println("Class:"+node.name);
        node.stmtList.forEach(this::visitStmt);
    }
    public void visitFuncDeclNode(XASTFuncDeclNode node){
        out.println("Function:"+node.name);
        visitTypeNode(node.retType);
        visitStmt(node.paramList);
        visitStmt(node.funcBody);
        node.startNode = node.funcBody.startNode;
        node.endNode = node.funcBody.endNode;
        if (node.name.equals("main")){
            cfg.entryNode = node.startNode;
        }
    }
    public void visitVarDeclNode(XASTVarDeclNode node){
        out.println("VarDecl:"+node.name);
        visitTypeNode(node.type);
        visitExpr(node.initExpr);
        XCFGNode curNode = cfg.addNode();
        XIRInst mv_inst = new XIRInst(XIRInst.opType.op_mov);
        XIRInstAddr mv_reg = XIRInstAddr.newRegAddr();
        mv_inst.oprList.add(mv_reg);
        if (node.initExpr.instAddr != null){
            curNode.addInst(node.initExpr.instList);
            mv_inst.oprList.add(node.initExpr.instAddr);
        } else {
            mv_inst.oprList.add(new XIRInstAddr(XIRInstAddr.addrType.a_imm,0,0));
        }
        curNode.addInst(mv_inst);
        node.startNode = node.endNode = curNode;
    }
    public void visitTypeNode(XASTTypeNode node){
        out.println("Type:"+node.nodeID.toString()+":"+(node.className==null?"":node.className)+"("+node.dim+")");
    }

    public void visitStmtNode(XASTStmtNode node){
        out.println("Statment:"+node.nodeID.toString()+":"+node.pos);
        if (node.stmtList!=null) node.stmtList.forEach(this::visitStmt);
        switch(node.nodeID){
            case s_block: {
                if (node.stmtList.isEmpty()){
                    node.startNode = node.endNode = cfg.addNode();
                    break;
                }
                for (XASTStmtNode i : node.stmtList){
                    if (i instanceof XASTExprNode) {
                        XCFGNode n = cfg.addNode();
                        n.addInst(((XASTExprNode)i).instList);
                        i.startNode = i.endNode = n;
                    }
                }
                XCFGNode lastNode = node.stmtList.firstElement().endNode;
                for (int i = 1;i < node.stmtList.size(); ++i){
                    lastNode.linkTo(node.stmtList.elementAt(i).startNode);
                    lastNode = node.stmtList.elementAt(i).endNode;
                }
                node.startNode = node.stmtList.firstElement().startNode;
                node.endNode = node.stmtList.lastElement().endNode;
                break;
            }
            case s_break: {
                XCFGNode curNode = cfg.addNode();
                XIRInst j_inst = new XIRInst(XIRInst.opType.op_jcc);
                curNode.addInst(j_inst);
                node.startNode = node.endNode = curNode;
                break;
            }
            case s_ret: {
                XCFGNode curNode = cfg.addNode();
                curNode.addInst(((XASTExprNode) node.stmtList.elementAt(0)).instList);
                curNode.addInst(new XIRInst(XIRInst.opType.op_ret));
                node.startNode = node.endNode = curNode;
                break;
            }
            case s_cont: {
                XCFGNode curNode = cfg.addNode();
                XIRInst j_inst = new XIRInst(XIRInst.opType.op_jcc);
                curNode.addInst(j_inst);
                node.startNode = node.endNode = curNode;
                break;
            }
            case s_plist:
                break;
            case s_for: {
                XCFGNode curNode = cfg.addNode();
                curNode.addInst(((XASTExprNode)node.stmtList.elementAt(0)).instList);
                XCFGNode condNode = cfg.addNode();
                curNode.linkTo(condNode);
                condNode.addInst(((XASTExprNode)node.stmtList.elementAt(1)).instList);
                XIRInst cc_inst = new XIRInst(XIRInst.opType.op_jcc);
                cc_inst.oprList.add(new XIRInstAddr(XIRInstAddr.addrType.a_imm,0,0));
                cc_inst.oprList.add(((XASTExprNode) node.stmtList.elementAt(1)).instAddr);
                condNode.addInst(cc_inst);
                condNode.linkTo(node.stmtList.elementAt(3).startNode);
                XCFGNode stepNode = cfg.addNode();
                node.stmtList.elementAt(3).endNode.linkTo(stepNode);
                stepNode.addInst(((XASTExprNode)node.stmtList.elementAt(2)).instList);
                stepNode.linkTo(condNode);
                node.startNode = curNode;
                node.endNode = cfg.addNode();
                condNode.linkTo(node.endNode);
                break;
            }
            case s_while: {
                XCFGNode curNode = cfg.addNode();
                curNode.addInst(((XASTExprNode) node.stmtList.elementAt(0)).instList);
                XIRInst cc_inst = new XIRInst(XIRInst.opType.op_jcc);
                cc_inst.oprList.add(new XIRInstAddr(XIRInstAddr.addrType.a_imm,0,0));
                cc_inst.oprList.add(((XASTExprNode) node.stmtList.elementAt(0)).instAddr);
                curNode.addInst(cc_inst);
                curNode.linkTo(node.stmtList.elementAt(1).startNode);
                XCFGNode newNode = cfg.addNode();
                node.stmtList.elementAt(1).endNode.linkTo(newNode);
                node.startNode = curNode;
                node.endNode = newNode;
                break;
            }
            case s_if: {
                XCFGNode curNode = cfg.addNode();
                curNode.addInst(((XASTExprNode)node.stmtList.elementAt(0)).instList);
                XIRInst if_inst = new XIRInst(XIRInst.opType.op_jcc);
                if_inst.oprList.add(new XIRInstAddr(XIRInstAddr.addrType.a_imm,0,0));
                if_inst.oprList.add(((XASTExprNode)node.stmtList.elementAt(0)).instAddr);
                curNode.addInst(if_inst);
                curNode.linkTo(node.stmtList.elementAt(1).startNode);
                XCFGNode newNode = cfg.addNode();
                node.stmtList.elementAt(1).endNode.linkTo(newNode);
                if (node.stmtList.size()>2){
                    curNode.linkTo(node.stmtList.elementAt(2).startNode);
                    node.stmtList.elementAt(2).endNode.linkTo(newNode);
                } else {
                    curNode.linkTo(newNode);
                }
                node.startNode = curNode;
                node.endNode = newNode;
                break;
            }
            case s_none:
                break;
            default:
                return;
        }

    }
    public void visitExprNode(XASTExprNode node){
        out.println("Expression:"+node.nodeID.toString()+":"+node.pos);
        if(node.exprList==null) return;
        node.exprList.forEach(this::visitExpr);
        node.exprList.forEach(i -> node.instList.addAll(i.instList));
        XIRInst.opType type = null;
        switch (node.nodeID){
            case e_none:
                break;
            case e_add:
                type = XIRInst.opType.op_add;
                break;
            case e_asgn: {
                type = XIRInst.opType.op_mov;
                XIRInst inst = new XIRInst(type);
                inst.oprList.add(node.exprList.elementAt(0).instAddr);
                inst.oprList.add(node.exprList.elementAt(1).instAddr);
                node.instList.add(inst);
                node.instAddr = node.exprList.elementAt(0).instAddr;
                System.out.println(inst);
                return;
            }
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
            case e_lor:
                type = XIRInst.opType.op_or;
                break;
            case e_lt:
                type = XIRInst.opType.op_lt;
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
            case e_list:
            case e_mem:
                type = XIRInst.opType.op_none;
                break;
            case e_new:
                return;
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
            default:
                return;
        }
        XIRInst inst = new XIRInst(type);
        node.instAddr = XIRInstAddr.newRegAddr();
        inst.oprList.add(node.instAddr);
        node.exprList.forEach(i->inst.oprList.add(i.instAddr));
        node.instList.add(inst);
        System.out.println(inst);
    }
    public void visitPrimNode(XASTPrimNode node){
        out.print("Primary:"+node.nodeID.toString()+":");
        switch (node.nodeID){
            case p_id:
                out.println(node.strLiteral);
                node.instAddr = XIRInstAddr.newRegAddr();
                break;
            case p_lit_bool:
                out.println(node.intLiteral);
                node.instAddr = new XIRInstAddr(XIRInstAddr.addrType.a_imm,node.intLiteral,0);
                break;
            case p_lit_int:
                out.println(node.intLiteral);
                node.instAddr = new XIRInstAddr(XIRInstAddr.addrType.a_imm,node.intLiteral,0);
                break;
            case p_lit_null:
                node.instAddr = new XIRInstAddr(XIRInstAddr.addrType.a_imm,0,0);
                break;
            case p_lit_str:
                out.println(node.strLiteral);
//                node.instAddr = new XIRInstAddr(XIRInstAddr.addrType.a_imm,node.intLiteral,0);
                break;
            case p_this:
                break;
            default:
        }
    }
    public void visitCreatorNode(XASTCreatorNode node){
        out.println("Creator:");
        if (node.exprList!=null) node.exprList.forEach(this::visitExpr);
        visitTypeNode(node.ctype);
    }

}
