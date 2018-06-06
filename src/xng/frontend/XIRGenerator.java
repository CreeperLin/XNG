package xng.frontend;

import xng.XIR.*;
import xng.frontend.AST.*;
import xng.frontend.Symbol.SymbolType;

import java.util.*;

import static java.lang.System.out;

public class XIRGenerator extends XASTBaseVisitor implements XASTVisitor {

    private XCFG cfg;
    private Stack<XCFGNode> curLoopBreakNode = new Stack<>();
    private Stack<XCFGNode> curLoopContNode = new Stack<>();
    private XCFGNode curFuncRetNode = null;
    private int strLiteralCount = 0;
    private String curFuncName = null;
    private String curClassName = null;

    private XIRInstAddr curFuncRetAddr = null;
    private XIRInstAddr curThisAddr = null;

    private XCFGNode curStaticInit;
//    private XCFGNode curFuncNode;
    private XIRProcInfo curProc;

    private HashMap<String,Integer> stringLiteralMap = new HashMap<>();

    public XIRGenerator(XCFG _cfg){
        cfg = _cfg;
    }

    public void visitCUNode(XASTCUNode node){
        out.println("XIRGen begin");
        XCFGNode staticInit;
        curStaticInit = staticInit = cfg.addNode();
        staticInit.name = "_static_init";
        for (XASTStmtNode n : node.declList) {
            visitStmt(n);
        }
        if (curStaticInit.name.equals("_static_init")) return;
        curStaticInit.instList.add(new XIRInst(XIRInst.opType.op_ret));
        XIRProcInfo staticInitProc = new XIRProcInfo(staticInit,curStaticInit);
        staticInitProc.isCallee = false;
        staticInitProc.isCaller = true;
        cfg.Proc.add(staticInitProc);
        XIRInst call_inst = new XIRInst(XIRInst.opType.op_call);
        call_inst.oprList.add(XIRInstAddr.newJumpAddr(staticInit));
        cfg.entryNode.instList.insertElementAt(call_inst,0);
    }
    public void visitClassDeclNode(XASTClassDeclNode node){
        out.println("Class:"+node.name);
        curClassName = node.name;
        node.stmtList.forEach(this::visitStmt);
        curClassName = null;
    }
    public void visitFuncDeclNode(XASTFuncDeclNode node){
//        curFuncNode = node.startNode;
        curFuncRetNode = cfg.addNode();
        curFuncRetNode.addInst(XIRInst.opType.op_ret);
        node.endNode = curFuncRetNode;
        curProc = new XIRProcInfo(node.startNode,node.endNode);
        cfg.Proc.add(curProc);
        out.println("Function:"+node.name);
        curFuncName = node.name;
        visitTypeNode(node.retType);
        visitStmt(node.paramList);
        int param_idx = 0;
        if (node.isMember){
            XIRInst param_inst = node.startNode.addInst(XIRInst.opType.op_rpara);
            curThisAddr = XIRInstAddr.newRegAddr(); //stack
//            curThisAddr = XIRInstAddr.newStackAddr(8); //stack
            param_inst.oprList.add(curThisAddr);
            param_inst.oprList.add(XIRInstAddr.newImmAddr(param_idx++, 0));
        }
        if (node.paramList!=null) {
            for (XASTStmtNode i : node.paramList.stmtList) {
                XIRInst param_inst = node.startNode.addInst(XIRInst.opType.op_rpara);
//                ((XASTVarDeclNode)i).reg.copy(XIRInstAddr.newStackAddr(8));
                param_inst.oprList.add(((XASTVarDeclNode) i).reg);
                param_inst.oprList.add(XIRInstAddr.newImmAddr(param_idx++, 0));
            }
        }
        curProc.paramCount = param_idx;
        visitStmt(node.funcBody);
        node.startNode.linkTo(node.funcBody.startNode);
        XCFGNode bodyEnd = node.funcBody.endNode;
        if (bodyEnd != null) {
            bodyEnd.linkTo(curFuncRetNode);
            System.out.println("Function:bodyEnd:"+node.name+":"+bodyEnd.nodeID);
//            if (bodyEnd.instList.isEmpty() || bodyEnd.instList.lastElement().op != XIRInst.opType.op_ret)
//                curFuncRetNode.addInst(XIRInst.opType.op_ret);
        }
        if (node.name.equals("main")){
            cfg.entryNode = node.startNode;
            node.startNode.name = "main";
        }
        curFuncRetNode = null;
        curThisAddr = null;
        curFuncName = null;
        curProc = null;
    }

    private void genBoolVarNode(XIRInstAddr addr, XASTExprNode expr, XCFGNode endNode) {
        XCFGNode tn = cfg.addNode();
        XCFGNode fn = cfg.addNode();
        XIRInst ti = tn.addInst(XIRInst.opType.op_mov);
        ti.oprList.add(addr);
        ti.oprList.add(XIRInstAddr.newImmAddr(1,0));
        XIRInst fi = fn.addInst(XIRInst.opType.op_mov);
        fi.oprList.add(addr);
        fi.oprList.add(XIRInstAddr.newImmAddr(0,0));
        visitLogicalExprNode(expr,tn,fn);
        tn.linkTo(endNode);
        fn.linkTo(endNode);
    }

    public void visitVarDeclNode(XASTVarDeclNode node){
        out.println("VarDecl:"+node.name);
        visitTypeNode(node.type);
        if (SymbolType.boolType.equals(node.initExpr.type) && (!(node.initExpr instanceof XASTPrimNode))) {
            node.endNode = cfg.addNode();
            genBoolVarNode(node.reg,node.initExpr,node.endNode);
            node.startNode = node.initExpr.startNode;
//            return;
        } else {
            visitExpr(node.initExpr);
            if (node.initExpr.instAddr != null){
                XIRInst mv_inst = new XIRInst(XIRInst.opType.op_mov);
                XIRInstAddr mv_reg = node.reg;
                mv_inst.oprList.add(mv_reg);
                mv_inst.oprList.add(node.initExpr.instAddr);
                node.initExpr.instList.add(mv_inst);
            }
            XCFGNode curNode = cfg.addNode();
            curNode.addInst(node.initExpr.instList);
            node.startNode = node.endNode = curNode;
        }
        if (curFuncName == null && curClassName == null) {
            String varName = "_v_" + node.name;
//            System.out.println("global:"+varName);
            int size = node.reg.addr1.lit1;
            if (node.initExpr.nodeID == XASTNodeID.p_lit_int || node.initExpr.nodeID == XASTNodeID.p_lit_bool) {
                cfg.dataList.add(new XIRData(varName,((XASTPrimNode)node.initExpr).intLiteral,true));
            } else {
                if (!node.initExpr.isEmpty()) {
                    curStaticInit.linkTo(node.startNode);
                    curStaticInit = node.endNode;
                }
                cfg.dataList.add(new XIRData(varName,size,false));
            }
        }
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
        switch(node.nodeID){
            case s_block: {
                if (node.stmtList == null || node.stmtList.isEmpty()){
                    node.startNode = node.endNode = cfg.addNode();
                    break;
                }
                node.stmtList.forEach(this::visitStmt);
                for (XASTStmtNode i : node.stmtList){
                    if (i instanceof XASTExprNode) {
                        if (i.startNode != null) continue;
                        XCFGNode n = cfg.addNode();
                        n.addInst(((XASTExprNode)i).instList);
                        i.startNode = i.endNode = n;
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
                XIRInst j_inst = curNode.addInst(XIRInst.opType.op_jmp);
//                j_inst.oprList.add(XIRInstAddr.newImmAddr(0,0));
                XCFGNode toNode = (node.nodeID == XASTNodeID.s_cont) ? curLoopContNode.peek() : curLoopBreakNode.peek();
                j_inst.oprList.add(XIRInstAddr.newJumpAddr(toNode));
                curNode.linkTo(toNode);
                node.startNode = curNode;
                node.endNode = null;
                break;
            }
            case s_ret: {
                if (node.stmtList!=null) {
                    XASTExprNode expr = (XASTExprNode)node.stmtList.get(0);
                    if (SymbolType.boolType.equals(expr.type)
                            && (!(expr instanceof XASTPrimNode))) {
                        XCFGNode curNode = cfg.addNode();
//                        curNode.addInst(XIRInst.opType.op_ret);
                        genBoolVarNode(XIRInstAddr.newRegAddr(-2),expr,curNode);
                        node.startNode = expr.startNode;
                        curNode.linkTo(curFuncRetNode);
//                        node.endNode = curNode;
                        node.endNode = null;
                        return;
                    }
                    node.stmtList.forEach(this::visitStmt);
                }
                XCFGNode curNode = cfg.addNode();
                curNode.addInst(((XASTExprNode) node.stmtList.elementAt(0)).instList);
                if (((XASTExprNode) node.stmtList.elementAt(0)).instAddr != null) {
                    XIRInst mv_inst = curNode.addInst(XIRInst.opType.op_mov);
                    mv_inst.oprList.add(XIRInstAddr.newRegAddr(-2));
                    mv_inst.oprList.add(((XASTExprNode) node.stmtList.elementAt(0)).instAddr);
                }
//                curNode.addInst(XIRInst.opType.op_ret);
                node.startNode = curNode;
//                node.endNode = curNode;
                curNode.linkTo(curFuncRetNode);
                node.endNode = null;
                break;
            }
            case s_plist:
                if (node.stmtList!=null) node.stmtList.forEach(this::visitStmt);
                break;
            case s_for: {
                if (node.stmtList==null) break;
                XCFGNode curNode = cfg.addNode();
                XCFGNode stepNode = cfg.addNode();
                node.endNode = cfg.addNode();
                curLoopContNode.push(stepNode);
                curLoopBreakNode.push(node.endNode);
                Vector<XASTStmtNode> stmtList = node.stmtList;
                for (int i = 0; i < stmtList.size(); i++) {
                    if (i==1) continue;
                    XASTStmtNode xastStmtNode = stmtList.get(i);
                    visitStmt(xastStmtNode);
                }
                wrapExprNode(node.stmtList.elementAt(3));
                curNode.addInst(((XASTExprNode)node.stmtList.elementAt(0)).instList);
                visitLogicalExprNode((XASTExprNode)node.stmtList.get(1),node.stmtList.get(3).startNode,node.endNode);
                curNode.linkTo(node.stmtList.get(1).startNode);
                if (node.stmtList.elementAt(3).endNode != null)
                    node.stmtList.elementAt(3).endNode.linkTo(stepNode);
                stepNode.addInst(((XASTExprNode)node.stmtList.elementAt(2)).instList);
                stepNode.linkTo(node.stmtList.get(1).startNode);
                node.startNode = curNode;
                curLoopContNode.pop();
                curLoopBreakNode.pop();
                break;
            }
            case s_while: {
                if (node.stmtList==null) break;
                XCFGNode newNode = cfg.addNode();
                XCFGNode contNode = cfg.addNode();
                curLoopContNode.push(contNode);
                curLoopBreakNode.push(newNode);
                visitStmt(node.stmtList.get(1));
                wrapExprNode(node.stmtList.get(1));
                visitLogicalExprNode((XASTExprNode)node.stmtList.get(0),node.stmtList.get(1).startNode,newNode);
                XCFGNode condNode = node.stmtList.get(0).startNode;
                if (node.stmtList.elementAt(1).endNode!=null) {
                    node.stmtList.elementAt(1).endNode.linkTo(condNode);
                }
                contNode.linkTo(condNode);
                node.startNode = condNode;
                node.endNode = newNode;
                curLoopContNode.pop();
                curLoopBreakNode.pop();
                break;
            }
            case s_if: {
                XCFGNode newNode = cfg.addNode();
                XCFGNode falseNode;
                for (int i = node.stmtList.size() - 1; i >= 1; --i) {
                    XASTStmtNode i1 = node.stmtList.get(i);
                    visitStmt(i1);
                    wrapExprNode(i1);
                    if (i1.endNode != null) i1.endNode.linkTo(newNode);
                }
                if (node.stmtList.size()<3){
                    falseNode = newNode;
                } else{
                    falseNode = node.stmtList.get(2).startNode;
                }
                visitLogicalExprNode((XASTExprNode)node.stmtList.get(0),node.stmtList.get(1).startNode,falseNode);
                node.startNode = node.stmtList.get(0).startNode;
                node.endNode = newNode;
                break;
            }
            case s_none:
                node.startNode = node.endNode = cfg.addNode();
                break;
            default:
        }

    }

    private void visitLogicalExprNode(XASTExprNode node, XCFGNode trueNode, XCFGNode falseNode) {
        System.out.println("Logical:"+node.nodeID+' '+trueNode.nodeID+' '+falseNode.nodeID);
        if (node.isEmpty()) {
            XCFGNode n = node.startNode = node.endNode = cfg.addNode();
            n.linkTo(trueNode);
            return;
        }
        switch (node.nodeID) {
            case p_lit_bool: {
                XCFGNode n = node.startNode = node.endNode = cfg.addNode();
                if (((XASTPrimNode)node).intLiteral == 1) {
                    n.linkTo(trueNode);
                } else {
                    n.linkTo(falseNode);
                }
                return;
            }
            case e_land: {
                visitLogicalExprNode(node.exprList.get(1), trueNode, falseNode);
                visitLogicalExprNode(node.exprList.get(0), node.exprList.get(1).startNode, falseNode);
                node.startNode = node.exprList.get(0).startNode;
                break;
            }
            case e_lor: {
                visitLogicalExprNode(node.exprList.get(1), trueNode, falseNode);
                visitLogicalExprNode(node.exprList.get(0), trueNode, node.exprList.get(1).startNode);
                node.startNode = node.exprList.get(0).startNode;
//                XCFGNode n = node.startNode = cfg.addNode();
//                n.addInst(node.exprList.get(0).instList);
                break;
            }
            case e_not: {
                visitLogicalExprNode(node.exprList.get(0), falseNode, trueNode);
                node.startNode = node.exprList.get(0).startNode;
//                XCFGNode n = node.startNode = cfg.addNode();
//                n.addInst(node.exprList.get(0).instList);
                break;
            }
            default: {
                visitExpr(node);
                XCFGNode n = node.startNode = node.endNode = cfg.addNode();
                n.addInst(node.instList);
                if (n.instList.isEmpty() || !XIRInst.opType.isCompare(n.instList.lastElement().op)){
                    XIRInst cmp_inst = n.addInst(XIRInst.opType.op_eq);
                    cmp_inst.oprList.add(node.instAddr);
                    cmp_inst.oprList.add(XIRInstAddr.newImmAddr(1,0));
                }
                XIRInst j_inst = n.addInst(XIRInst.opType.op_jcc);
                j_inst.oprList.add(XIRInstAddr.newJumpAddr(falseNode));
                n.linkTo(falseNode);
                n.linkTo(trueNode);
            }
        }

    }

    public void visitExprNode(XASTExprNode node){
        out.println("Expression:"+node.nodeID.toString()+":"+node.pos);
        if(node.exprList==null) return;
        if (node.nodeID != XASTNodeID.e_call && node.nodeID != XASTNodeID.e_asgn && node.nodeID != XASTNodeID.e_mem) {
            node.exprList.forEach(this::visitExpr);
            node.exprList.forEach(i -> node.instList.addAll(i.instList));
        }
        XIRInst.opType type = XIRInst.opType.op_none;
        switch (node.nodeID){
            case e_none:
                return;
            case e_add: {
                if (node.type.equals(SymbolType.strType)) {
                    XIRInst p1_inst = new XIRInst(XIRInst.opType.op_wpara);
                    p1_inst.oprList.add(node.exprList.get(0).instAddr);
                    p1_inst.oprList.add(XIRInstAddr.newImmAddr(0,0));
                    XIRInst p2_inst = new XIRInst(XIRInst.opType.op_wpara);
                    p2_inst.oprList.add(node.exprList.get(1).instAddr);
                    p2_inst.oprList.add(XIRInstAddr.newImmAddr(1,0));
                    node.instList.add(p1_inst);
                    node.instList.add(p2_inst);
                    XIRInst call_inst = new XIRInst(XIRInst.opType.op_call);
                    call_inst.oprList.add(XIRInstAddr.newJumpAddr("_lib_strcat"));
                    node.instList.add(call_inst);
                    XIRInst mv_inst = new XIRInst(XIRInst.opType.op_mov);
                    XIRInstAddr ret_addr = XIRInstAddr.newRegAddr();
                    mv_inst.oprList.add(ret_addr);
                    mv_inst.oprList.add(XIRInstAddr.newRegAddr(-2));
                    node.instList.add(mv_inst);
                    node.instAddr = ret_addr;
                    return;
                } else {
                    type = XIRInst.opType.op_add;
                }
                break;
            }
            case e_asgn: {
                type = XIRInst.opType.op_mov;
                if (SymbolType.boolType.equals(node.exprList.get(1).type)
                        && (!(node.exprList.get(1) instanceof XASTPrimNode))) {
                    System.out.println("dbg:logical assign");
                    visitExpr(node.exprList.get(0));
                    node.startNode = cfg.addNode();
                    node.endNode = cfg.addNode();
                    node.startNode.addInst(node.exprList.get(0).instList);
                    genBoolVarNode(node.exprList.get(0).instAddr,node.exprList.get(1),node.endNode);
                    node.startNode.linkTo(node.exprList.get(1).startNode);
                } else {
                    node.exprList.forEach(this::visitExpr);
                    node.exprList.forEach(i -> node.instList.addAll(i.instList));
                    XIRInst inst = new XIRInst(type);
                    inst.oprList.add(node.exprList.elementAt(0).instAddr);
                    inst.oprList.add(node.exprList.elementAt(1).instAddr);
                    node.instList.add(inst);
                    System.out.println(inst);
                }
                node.instAddr = node.exprList.elementAt(0).instAddr;
                return;
            }
            case e_ne:
            case e_le:
            case e_lt:
            case e_ge:
            case e_gt:
            case e_eq: {
                switch (node.nodeID){
                    case e_ne:
                        type = XIRInst.opType.op_ne;
                        break;
                    case e_eq:
                        type = XIRInst.opType.op_eq;
                        break;
                    case e_ge:
                        type = XIRInst.opType.op_ge;
                        break;
                    case e_gt:
                        type = XIRInst.opType.op_gt;
                        break;
                    case e_le:
                        type = XIRInst.opType.op_le;
                        break;
                    case e_lt:
                        type = XIRInst.opType.op_lt;
                        break;
                }
                if (node.exprList.get(0).type.equals(SymbolType.strType)){
                    XIRInst p1_inst = new XIRInst(XIRInst.opType.op_wpara);
                    p1_inst.oprList.add(node.exprList.get(0).instAddr);
                    p1_inst.oprList.add(XIRInstAddr.newImmAddr(0,0));
                    XIRInst p2_inst = new XIRInst(XIRInst.opType.op_wpara);
                    p2_inst.oprList.add(node.exprList.get(1).instAddr);
                    p2_inst.oprList.add(XIRInstAddr.newImmAddr(1,0));
                    node.instList.add(p1_inst);
                    node.instList.add(p2_inst);
                    XIRInst call_inst = new XIRInst(XIRInst.opType.op_call);
                    call_inst.oprList.add(XIRInstAddr.newJumpAddr("_lib_strcmp"));
                    node.instList.add(call_inst);
                    XIRInst cmp_inst = new XIRInst(type);
                    cmp_inst.oprList.add(XIRInstAddr.newRegAddr(-2));
                    cmp_inst.oprList.add(XIRInstAddr.newImmAddr(0,0));
                    node.instList.add(cmp_inst);
                    node.instAddr = XIRInstAddr.newRegAddr(-2);
                    return;
                } else {
                    XIRInst inst = new XIRInst(type);
                    node.exprList.forEach(i->inst.oprList.add(i.instAddr));
                    node.instList.add(inst);
                    node.instAddr = XIRInstAddr.newRegAddr();
                    return;
                }
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
                visitExpr(node.exprList.get(0));
                node.instList.addAll(node.exprList.get(0).instList);
                if (node.toNode.name.startsWith("_lib_array")) {
                    XIRInst mv_inst = new XIRInst(XIRInst.opType.op_mov);
                    node.instAddr = XIRInstAddr.newRegAddr();
                    mv_inst.oprList.add(node.instAddr);
                    mv_inst.oprList.add(XIRInstAddr.newMemAddr(node.exprList.get(0).exprList.get(0).instAddr,XIRInstAddr.newImmAddr(0,0),0,0));
                    node.instList.add(mv_inst);
                    return;
                }
                if (curProc!=null) curProc.isCaller = true;
                if (node.toNode.name.startsWith("_lib_print")){
                    boolean isLine = node.toNode.name.equals("_lib_println");
                    Stack<XASTExprNode> exprStack = new Stack<>();
                    XASTExprNode param = node.exprList.get(1).exprList.get(0);
                    visitExpr(param);
                    exprStack.push(node.exprList.get(1).exprList.get(0));
                    while (!exprStack.isEmpty()) {
                        XASTExprNode expr = exprStack.pop();
                        if (expr.nodeID == XASTNodeID.e_call && expr.toNode.name.equals("_lib_toString")) {
                            XASTExprNode p = expr.exprList.get(1).exprList.get(0);
                            node.instList.addAll(p.instList);
                            XIRInst param_inst = new XIRInst(XIRInst.opType.op_wpara);
                            param_inst.oprList.add(p.instAddr);
                            param_inst.oprList.add(XIRInstAddr.newImmAddr(0,0));
                            node.instList.add(param_inst);
                            XIRInst call_inst = new XIRInst(type);
                            if (isLine && exprStack.empty()) {
                                call_inst.oprList.add(XIRInstAddr.newJumpAddr("_lib_printlnInt"));
                                isLine = false;
                            } else {
                                call_inst.oprList.add(XIRInstAddr.newJumpAddr("_lib_printInt"));
                            }
                            node.instList.add(call_inst);
                        } else if (expr.nodeID == XASTNodeID.e_add) {
                            Vector<XASTExprNode> exprList = expr.exprList;
                            for (int i = exprList.size() - 1; i >= 0; i--) {
                                XASTExprNode xastExprNode = exprList.get(i);
                                exprStack.push(xastExprNode);
                            }
                        } else {
                            node.instList.addAll(expr.instList);
                            XIRInst param_inst = new XIRInst(XIRInst.opType.op_wpara);
                            param_inst.oprList.add(expr.instAddr);
                            param_inst.oprList.add(XIRInstAddr.newImmAddr(0,0));
                            node.instList.add(param_inst);
                            XIRInst call_inst = new XIRInst(type);
                            if (isLine&&exprStack.empty()) {
                                call_inst.oprList.add(XIRInstAddr.newJumpAddr("_lib_println"));
                                isLine = false;
                            } else {
                                call_inst.oprList.add(XIRInstAddr.newJumpAddr("_lib_print"));
                            }
                            node.instList.add(call_inst);
                        }
                    }
                    return;
                }
                int t = 0;
                XIRInstAddr ptAddr = null;
                if (node.toNode.name.startsWith("_class")) {
                    t = 1;
                    ptAddr = (curThisAddr == null || node.exprList.get(0).nodeID == XASTNodeID.e_mem) ? node.exprList.get(0).exprList.get(0).instAddr : curThisAddr;
                } else if (node.toNode.name.startsWith("_lib_str")) {
                    t = 1;
                    ptAddr = node.exprList.get(0).exprList.get(0).instAddr;
                }
                if (node.exprList.size() > 1) {
                    Vector<XASTExprNode> exprList = node.exprList.elementAt(1).exprList;
                    for (XASTExprNode xastExprNode : exprList) {
                        visitExpr(xastExprNode);
                        node.instList.addAll(xastExprNode.instList);
                    }
                    for (int i1 = exprList.size() - 1; i1 >= 0; --i1) {
                        XASTExprNode i = exprList.get(i1);
//                        visitExpr(i);
                        XIRInst param_inst = new XIRInst(XIRInst.opType.op_wpara);
                        param_inst.oprList.add(i.instAddr);
                        param_inst.oprList.add(XIRInstAddr.newImmAddr(i1 + t, 0));
                        node.instList.add(param_inst);
                    }
                }
                if (t == 1) {
                    XIRInst param_inst = new XIRInst(XIRInst.opType.op_wpara);
                    param_inst.oprList.add(ptAddr);
                    param_inst.oprList.add(XIRInstAddr.newImmAddr(0, 0));
                    node.instList.add(param_inst);
                }
                XIRProcInfo callee = cfg.getProc(node.toNode.name);
                if (callee!=null) callee.isCallee = true;
                XIRInst call_inst = new XIRInst(type);
                call_inst.oprList.add(XIRInstAddr.newJumpAddr(node.toNode));
                node.instList.add(call_inst);
                XIRInst mv_inst = new XIRInst(XIRInst.opType.op_mov);
                XIRInstAddr ret_addr = XIRInstAddr.newRegAddr();
                mv_inst.oprList.add(ret_addr);
                mv_inst.oprList.add(XIRInstAddr.newRegAddr(-2));
                node.instList.add(mv_inst);
                node.instAddr = ret_addr;
//                node.instAddr = XIRInstAddr.newRegAddr(-2);
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
            case e_idx: {
                node.instAddr = XIRInstAddr.newMemAddr(node.exprList.get(0).instAddr,node.exprList.get(1).instAddr,8,8);
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
            case e_lor:
            case e_not:
                System.out.println("dbg:logical expr");
//                XCFGNode tn = cfg.addNode();
//                XCFGNode fn = cfg.addNode();
//                visitLogicalExprNode(node,tn,fn);
                break;
            case e_mod:
                type = XIRInst.opType.op_mod;
                break;
            case e_mult:
                type = XIRInst.opType.op_mult;
                break;
            case e_neg:
                type = XIRInst.opType.op_neg;
                break;
            case e_list:
                return;
            case e_mem: {
                visitExpr(node.exprList.get(0));
                node.instList.addAll(node.exprList.get(0).instList);
                if (node.exprList.get(1).instAddr!=null && node.exprList.get(1).instAddr.type == XIRInstAddr.addrType.a_imm){
                    XIRInstAddr ofs_addr = XIRInstAddr.newMemAddr(node.exprList.elementAt(0).instAddr,XIRInstAddr.newImmAddr(0,0),8,node.exprList.elementAt(1).instAddr.lit1);
                    node.instAddr = ofs_addr;
                } else {
                    visitExpr(node.exprList.get(1));
                    node.instList.addAll(node.exprList.get(1).instList);
                    System.out.println("XIRGen:mem non field");
                }
//                type = XIRInst.opType.op_mov;
//                XIRInst mv_inst = new XIRInst(type);
//                XIRInstAddr tmp_addr = XIRInstAddr.newRegAddr();
//                mv_inst.oprList.add(tmp_addr);
//                mv_inst.oprList.add(ofs_addr);
//                node.instList.add(mv_inst);
                return;
            }
            case e_new:
                node.instAddr = node.exprList.get(0).instAddr;
                return;
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
                if (curThisAddr != null && node.instAddr !=null && node.instAddr.type == XIRInstAddr.addrType.a_imm){
                    out.println("found member id:"+node.strLiteral);
                    node.instAddr = XIRInstAddr.newMemAddr(curThisAddr,XIRInstAddr.newImmAddr(0,0),8,node.instAddr.lit1);
                }
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
                out.println("null");
                node.instAddr = XIRInstAddr.newImmAddr(0,0);
                break;
            case p_lit_str:
                out.println(node.strLiteral);
                String lit = node.strLiteral;
                int t;
                String name;
                if (stringLiteralMap.containsKey(lit)) {
                    t = stringLiteralMap.get(lit);
                    name = "_str_" +Integer.toString(t);
                } else {
                    t = ++strLiteralCount;
                    stringLiteralMap.put(lit,t);
                    name = "_str_" +Integer.toString(t);
                    cfg.dataList.add(new XIRData(name,lit));
                }
                node.instAddr = XIRInstAddr.newStaticAddr(name,lit.length());
                break;
            case p_this:
                node.instAddr = curThisAddr;
                out.println(node.instAddr);
                break;
            default:
        }
    }
    public void visitCreatorNode(XASTCreatorNode node){
        out.println("Creator:");
        if (node.exprList!=null) node.exprList.forEach(this::visitExpr);
        visitTypeNode(node.ctype);
        if (node.ctype.dim > 0){
//            if (node.idim == 1){
//                node.instList.addAll(node.exprList.get(0).instList);
//                XIRInst call_inst = new XIRInst(XIRInst.opType.op_call);
//                call_inst.oprList.add(XIRInstAddr.newJumpAddr("_lib_alloc"));
//                node.instList.add(call_inst);
//                node.instAddr = XIRInstAddr.newRegAddr(-2);
//            }
            XIRInstAddr st_addr = XIRInstAddr.newStackAddr(8);
            XIRInstAddr tmp_addr = st_addr;
            int idim = 0;
            for (XASTExprNode i : node.exprList) {
                if (i.isEmpty()) break;
                ++idim;
                node.instList.addAll(i.instList);
                XIRInst mv_inst = new XIRInst(XIRInst.opType.op_mov);
                mv_inst.oprList.add(tmp_addr);
                mv_inst.oprList.add(i.instAddr);
                node.instList.add(mv_inst);
                tmp_addr = XIRInstAddr.newStackAddr(8);
            }
            {
                XIRInstAddr tmp_reg = XIRInstAddr.newRegAddr(-4);
                XIRInst lea_inst = new XIRInst(XIRInst.opType.op_lea);
                lea_inst.oprList.add(tmp_reg);
                lea_inst.oprList.add(st_addr);
                node.instList.add(lea_inst);
                XIRInst para_inst = new XIRInst(XIRInst.opType.op_wpara);
                para_inst.oprList.add(tmp_reg);
                para_inst.oprList.add(XIRInstAddr.newImmAddr(1, 0));
                node.instList.add(para_inst);
            }
            {
                XIRInst para_inst = new XIRInst(XIRInst.opType.op_wpara);
                para_inst.oprList.add(XIRInstAddr.newImmAddr(idim,0));
                para_inst.oprList.add(XIRInstAddr.newImmAddr(0, 0));
                node.instList.add(para_inst);
            }
            XIRInst call_inst = new XIRInst(XIRInst.opType.op_call);
            call_inst.oprList.add(XIRInstAddr.newJumpAddr("_lib_alloc"));
            node.instList.add(call_inst);
            node.instAddr = XIRInstAddr.newRegAddr(-2);
        } else {
            XIRInst para_inst = new XIRInst(XIRInst.opType.op_wpara);
            System.out.println("XIRGen:dbg:"+node.type.getMemSize());
            para_inst.oprList.add(XIRInstAddr.newImmAddr(node.type.classMemSize,0));
            para_inst.oprList.add(XIRInstAddr.newImmAddr(0,0));
            node.instList.add(para_inst);
            XIRInst m_inst = new XIRInst(XIRInst.opType.op_call);
            m_inst.oprList.add(XIRInstAddr.newJumpAddr("malloc"));
            node.instList.add(m_inst);
            if (node.hasConstructor){
                node.instAddr = XIRInstAddr.newRegAddr();
                XIRInst mv_inst = new XIRInst(XIRInst.opType.op_mov);
                mv_inst.oprList.add(node.instAddr);
                mv_inst.oprList.add(XIRInstAddr.newRegAddr(-2));
                node.instList.add(mv_inst);
                XIRInst this_inst = new XIRInst(XIRInst.opType.op_wpara);
                this_inst.oprList.add(XIRInstAddr.newRegAddr(-2));
                this_inst.oprList.add(XIRInstAddr.newImmAddr(0,0));
                node.instList.add(this_inst);
                XIRInst call_inst = new XIRInst(XIRInst.opType.op_call);
                call_inst.oprList.add(XIRInstAddr.newJumpAddr("_class_"+node.ctype.className+".__init__"));
                node.instList.add(call_inst);
            } else {
                node.instAddr = XIRInstAddr.newRegAddr(-2);
            }
        }
    }

}
