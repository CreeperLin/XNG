package xng.frontend;

import xng.XIR.XCFG;
import xng.XIR.XIRInst;
import xng.XIR.XIRInstAddr;
import xng.XIR.XCFGNode;
import xng.frontend.AST.*;
import xng.frontend.Symbol.ScopedSymbolTable;

import java.util.Stack;
import java.util.Vector;

import static java.lang.System.out;

public class XIRGenerator extends XASTBaseVisitor implements XASTVisitor {

    private XCFG cfg;
    private ScopedSymbolTable SST;
    private Stack<XCFGNode> curLoopBreakNode = new Stack<>();
    private Stack<XCFGNode> curLoopContNode = new Stack<>();
    private XCFGNode curFuncRetNode = null;

    private XIRInstAddr curFuncRetAddr = null;

    public XIRGenerator(XCFG _cfg,ScopedSymbolTable _sst){
        cfg = _cfg;
        SST = _sst;
    }

    public void visitCUNode(XASTCUNode node){
        out.println("XIRGen begin");
        node.declList.forEach(this::visitStmt);
    }
    public void visitClassDeclNode(XASTClassDeclNode node){
        out.println("Class:"+node.name);
        node.stmtList.forEach(this::visitStmt);
    }
    public void visitFuncDeclNode(XASTFuncDeclNode node){
        curFuncRetNode = cfg.addNode();
        out.println("Function:"+node.name);
        visitTypeNode(node.retType);
        visitStmt(node.paramList);
        if (node.paramList!=null) {
            Vector<XASTStmtNode> stmtList = node.paramList.stmtList;
            int sz = stmtList.size();
            for (int i1 = 0; i1 < sz; i1++) {
                XASTStmtNode i = stmtList.get(i1);
                XIRInst param_inst = node.startNode.addInst(XIRInst.opType.op_rpara);
                param_inst.oprList.add(((XASTVarDeclNode) i).reg);
                param_inst.oprList.add(XIRInstAddr.newImmAddr(sz-i1-1, 0));
            }
        }
        visitStmt(node.funcBody);
        node.startNode.linkTo(node.funcBody.startNode);
        if (node.funcBody.endNode != null) node.funcBody.endNode.linkTo(curFuncRetNode);
        node.endNode = curFuncRetNode;
        curFuncRetNode = null;
        cfg.globalNodes.add(node.startNode);
        if (node.name.equals("main")){
            cfg.entryNode = node.startNode;
            node.startNode.name = "main";
        }
    }
    public void visitVarDeclNode(XASTVarDeclNode node){
        out.println("VarDecl:"+node.name);
        visitTypeNode(node.type);
        visitExpr(node.initExpr);
        XCFGNode curNode = cfg.addNode();
        curNode.addInst(node.initExpr.instList);
        XIRInst mv_inst = curNode.addInst(XIRInst.opType.op_mov);
        XIRInstAddr mv_reg = node.reg;
        mv_inst.oprList.add(mv_reg);
        if (node.initExpr.instAddr != null){
            mv_inst.oprList.add(node.initExpr.instAddr);
        } else {
            mv_inst.oprList.add(XIRInstAddr.newImmAddr(0,0));
        }
        node.startNode = node.endNode = curNode;
    }
    public void visitTypeNode(XASTTypeNode node){
        out.println("Type:"+node.nodeID.toString()+":"+(node.className==null?"":node.className)+"("+node.dim+")");
    }

    private void wrapExprNode(XASTStmtNode node){
        if(!(node instanceof XASTExprNode) || node.startNode!=null) return;
        XCFGNode n = cfg.addNode();
        node.startNode = node.endNode = n;
        n.instList.addAll(((XASTExprNode)node).instList);
    }

    public void visitStmtNode(XASTStmtNode node){
        out.println("Statment:"+node.nodeID.toString()+":"+node.pos);
        if (node.nodeID != XASTNodeID.s_for && node.nodeID != XASTNodeID.s_while) {
            if (node.stmtList!=null) node.stmtList.forEach(this::visitStmt);
        }
        switch(node.nodeID){
            case s_block: {
                if (node.stmtList == null || node.stmtList.isEmpty()){
                    node.startNode = node.endNode = cfg.addNode();
                    break;
                }
                for (XASTStmtNode i : node.stmtList){
                    if (i instanceof XASTExprNode) {
                        XCFGNode n = cfg.addNode();
                        n.addInst(((XASTExprNode)i).instList);
                        i.startNode = i.endNode=n;
//                        Vector<XIRInst> l = new Vector<>();
//                        for (XIRInst inst : ((XASTExprNode)i).instList){
//                            l.add(inst);
//                            if (inst.op==XIRInst.opType.op_call){
//                                n.instList.addAll(l);
//                                l.clear();
//                                n.linkTo(cfg.getNode(inst.oprList.get(0).lit1));
//                                n = cfg.addNode();
//                                i.endNode.linkTo(n);
//                                i.endNode=n;
//                            }
//                        }
//                        if(!l.isEmpty()){
//                            n.instList.addAll(l);
//                        }
                    }
                }
                XCFGNode lastNode = node.stmtList.firstElement().endNode;
                for (int i = 1;i < node.stmtList.size(); ++i){
                    if (lastNode!=null) lastNode.linkTo(node.stmtList.elementAt(i).startNode);
                    lastNode = node.stmtList.elementAt(i).endNode;
                }
                node.startNode = node.stmtList.firstElement().startNode;
                node.endNode = node.stmtList.lastElement().endNode;
                break;
            }
            case s_cont:
            case s_break: {
                XCFGNode curNode = cfg.addNode();
                XIRInst j_inst = curNode.addInst(XIRInst.opType.op_jcc);
//                j_inst.oprList.add(XIRInstAddr.newImmAddr(0,0));
                XCFGNode toNode = (node.nodeID == XASTNodeID.s_cont) ? curLoopContNode.peek() : curLoopBreakNode.peek();
                j_inst.oprList.add(XIRInstAddr.newJumpAddr(toNode));
                curNode.linkTo(toNode);
                node.startNode = curNode;
                node.endNode = null;
                break;
            }
            case s_ret: {
                XCFGNode curNode = cfg.addNode();
                curNode.addInst(((XASTExprNode) node.stmtList.elementAt(0)).instList);
                XIRInst mv_inst = curNode.addInst(XIRInst.opType.op_mov);
                mv_inst.oprList.add(XIRInstAddr.newRegAddr(-2));
                mv_inst.oprList.add(((XASTExprNode) node.stmtList.elementAt(0)).instAddr);
                curNode.addInst(XIRInst.opType.op_ret);
//                curNode.linkTo(curFuncRetNode);
                node.startNode = curNode;
                node.endNode = curNode;
//                node.endNode = null;
                break;
            }
            case s_plist:
                break;
            case s_for: {
                XCFGNode curNode = cfg.addNode();
                XCFGNode condNode = cfg.addNode();
                XCFGNode stepNode = cfg.addNode();
                node.endNode = cfg.addNode();
                curLoopContNode.push(stepNode);
                curLoopBreakNode.push(node.endNode);
                if (node.stmtList!=null) node.stmtList.forEach(this::visitStmt);
                curNode.addInst(((XASTExprNode)node.stmtList.elementAt(0)).instList);
                curNode.linkTo(condNode);
                condNode.addInst(((XASTExprNode)node.stmtList.elementAt(1)).instList);
                XIRInst cc_inst = condNode.addInst(XIRInst.opType.op_jcc);
//                cc_inst.oprList.add(((XASTExprNode) node.stmtList.elementAt(1)).instAddr);
                cc_inst.oprList.add(XIRInstAddr.newJumpAddr(node.endNode));
                condNode.linkTo(node.endNode);
                wrapExprNode(node.stmtList.elementAt(3));
                condNode.linkTo(node.stmtList.elementAt(3).startNode);
                if (node.stmtList.elementAt(3).endNode != null)
                    node.stmtList.elementAt(3).endNode.linkTo(stepNode);
                stepNode.addInst(((XASTExprNode)node.stmtList.elementAt(2)).instList);
                stepNode.linkTo(condNode);
                node.startNode = curNode;
                curLoopContNode.pop();
                curLoopBreakNode.pop();
                break;
            }
            case s_while: {
                XCFGNode curNode = cfg.addNode();
                XCFGNode newNode = cfg.addNode();
                curLoopContNode.push(curNode);
                curLoopBreakNode.push(newNode);
                if (node.stmtList!=null) node.stmtList.forEach(this::visitStmt);
                curNode.addInst(((XASTExprNode) node.stmtList.elementAt(0)).instList);
                XIRInst cc_inst = curNode.addInst(XIRInst.opType.op_jcc);
//                cc_inst.oprList.add(((XASTExprNode) node.stmtList.elementAt(0)).instAddr);
                cc_inst.oprList.add(XIRInstAddr.newJumpAddr(newNode));
                curNode.linkTo(newNode);
                wrapExprNode(node.stmtList.elementAt(1));
                curNode.linkTo(node.stmtList.elementAt(1).startNode);
                if (node.stmtList.elementAt(1).endNode!=null) {
                    node.stmtList.elementAt(1).endNode.linkTo(curNode);
//                    XIRInst j_inst = node.stmtList.elementAt(1).endNode.addInst(XIRInst.opType.op_jmp);
//                    j_inst.oprList.add(XIRInstAddr.newJumpAddr(curNode));
                }
                node.startNode = curNode;
                node.endNode = newNode;
                curLoopContNode.pop();
                curLoopBreakNode.pop();
                break;
            }
            case s_if: {
                XCFGNode curNode = cfg.addNode();
                curNode.addInst(((XASTExprNode)node.stmtList.elementAt(0)).instList);
                if (node.stmtList.elementAt(0) instanceof XASTPrimNode){
                    XIRInst cmp_inst = curNode.addInst(XIRInst.opType.op_eq);
                    cmp_inst.oprList.add(((XASTExprNode)node.stmtList.elementAt(0)).instAddr);
                    cmp_inst.oprList.add(XIRInstAddr.newImmAddr(1,0));
                }
                XIRInst if_inst = curNode.addInst(XIRInst.opType.op_jcc);
                XCFGNode newNode = cfg.addNode();
//                for (int i = node.stmtList.size()-1; i>= 1; --i) {
                if (node.stmtList.size()<3){
                    curNode.linkTo(newNode);
                    if_inst.oprList.add(XIRInstAddr.newJumpAddr(newNode));
                } else if_inst.oprList.add(XIRInstAddr.newJumpAddr(node.stmtList.elementAt(1).startNode));
                for (int i=1; i < node.stmtList.size(); ++i) {
                    curNode.linkTo(node.stmtList.elementAt(i).startNode);
                    if (node.stmtList.elementAt(i).endNode != null){
                        node.stmtList.elementAt(i).endNode.linkTo(newNode);
                    }
                }
                node.startNode = curNode;
                node.endNode = newNode;
                break;
            }
            case s_none:
                break;
            default:
        }

    }
    public void visitExprNode(XASTExprNode node){
        out.println("Expression:"+node.nodeID.toString()+":"+node.pos);
        if(node.exprList==null) return;
        if (node.nodeID != XASTNodeID.e_call) {
            node.exprList.forEach(this::visitExpr);
            node.exprList.forEach(i -> node.instList.addAll(i.instList));
        }
        XIRInst.opType type;
        switch (node.nodeID){
            case e_none:
                return;
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
            case e_eq: {
                type = XIRInst.opType.op_eq;
                break;
            }
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
            case e_call: {
                type = XIRInst.opType.op_call;
                if (node.exprList.size()>1){
                    Vector<XASTExprNode> exprList = node.exprList.elementAt(1).exprList;
                    for (int i1 = exprList.size()-1; i1 >= 0; --i1) {
                        XASTExprNode i = exprList.get(i1);
                        visitExpr(i);
                        XIRInst param_inst = new XIRInst(XIRInst.opType.op_wpara);
                        param_inst.oprList.add(i.instAddr);
//                        XIRInst param_inst = new XIRInst(XIRInst.opType.op_mov);
//                        param_inst.oprList.add(XIRInstAddr.newRegAddr(-4));
//                        param_inst.oprList.add(i.instAddr);
                        node.instList.addAll(i.instList);
                        node.instList.add(param_inst);
                    }
                }
                XIRInst call_inst = new XIRInst(type);
//                call_inst.oprList.add(node.exprList.firstElement().instAddr);
                call_inst.oprList.add(XIRInstAddr.newJumpAddr(node.toNode));
                node.instList.add(call_inst);
//                XIRInst mv_inst = new XIRInst(XIRInst.opType.op_mov);
//                XIRInstAddr ret_addr = XIRInstAddr.newRegAddr();
//                mv_inst.oprList.add(ret_addr);
//                mv_inst.oprList.add(XIRInstAddr.newRegAddr(-2));
//                node.instList.add(mv_inst);
                node.instAddr = XIRInstAddr.newRegAddr(-2);
                return;
            }
            case e_dec_p: {
                XIRInst dec_inst = new XIRInst(XIRInst.opType.op_dec);
                dec_inst.oprList.add(node.exprList.elementAt(0).instAddr);
                node.instList.add(dec_inst);
                node.instAddr = node.exprList.elementAt(0).instAddr;
                return;
            }
            case e_dec_s: {
                XIRInst mv_inst = new XIRInst(XIRInst.opType.op_mov);
                node.instAddr = XIRInstAddr.newRegAddr();
                mv_inst.oprList.add(node.instAddr);
                mv_inst.oprList.add(node.exprList.elementAt(0).instAddr);
                node.instList.add(mv_inst);

                XIRInst dec_inst = new XIRInst(XIRInst.opType.op_dec);
                dec_inst.oprList.add(node.exprList.elementAt(0).instAddr);
                node.instList.add(dec_inst);
                return;
            }
            case e_div:
                type = XIRInst.opType.op_div;
                break;
            case e_ge:
                type = XIRInst.opType.op_ge;
                break;
            case e_gt:
                type = XIRInst.opType.op_gt;
                break;
            case e_idx: {
                XIRInst add_inst = new XIRInst(XIRInst.opType.op_add);
                XIRInstAddr idx_addr = XIRInstAddr.newRegAddr();
                add_inst.oprList.add(idx_addr);
                node.exprList.forEach(i->add_inst.oprList.add(i.instAddr));

                XIRInst mv_inst = new XIRInst(XIRInst.opType.op_mov);
                node.instAddr = XIRInstAddr.newRegAddr();
                mv_inst.oprList.add(node.instAddr);
                mv_inst.oprList.add(idx_addr);
                node.instList.add(mv_inst);
                return;
            }
            case e_inc_p:{
                XIRInst inc_inst = new XIRInst(XIRInst.opType.op_inc);
                inc_inst.oprList.add(node.exprList.elementAt(0).instAddr);
                node.instList.add(inc_inst);
                node.instAddr = node.exprList.elementAt(0).instAddr;
                return;
            }
            case e_inc_s: {
                XIRInst mv_inst = new XIRInst(XIRInst.opType.op_mov);
                node.instAddr = XIRInstAddr.newRegAddr();
                mv_inst.oprList.add(node.instAddr);
                mv_inst.oprList.add(node.exprList.elementAt(0).instAddr);
                node.instList.add(mv_inst);

                XIRInst inc_inst = new XIRInst(XIRInst.opType.op_inc);
                inc_inst.oprList.add(node.exprList.elementAt(0).instAddr);
                node.instList.add(inc_inst);
                return;
            }
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
                return;
            case e_mem: {
                type = XIRInst.opType.op_mov;
                XIRInst mv_inst = new XIRInst(type);
                XIRInstAddr tmp_addr = XIRInstAddr.newRegAddr();
                mv_inst.oprList.add(tmp_addr);
                mv_inst.oprList.add(XIRInstAddr.newMemAddr(node.exprList.elementAt(0).instAddr,node.exprList.elementAt(1).instAddr));
                node.instList.add(mv_inst);
                node.instAddr = XIRInstAddr.newMemAddr(tmp_addr);
                return;
            }
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
                break;
            case p_lit_bool:
                out.println(node.intLiteral);
                node.instAddr = XIRInstAddr.newImmAddr(node.intLiteral,0);
                break;
            case p_lit_int:
                out.println(node.intLiteral);
                node.instAddr = XIRInstAddr.newImmAddr(node.intLiteral,0);
                break;
            case p_lit_null:
                node.instAddr = XIRInstAddr.newImmAddr(0,0);
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
//        if (node.ctype.dim > 0){
//
//        } else {
//
//        }
    }

}
