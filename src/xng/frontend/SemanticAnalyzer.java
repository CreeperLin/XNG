package xng.frontend;

import xng.common.XCompileError;
import xng.frontend.AST.*;
import xng.frontend.Symbol.ScopedSymbolTable;
import xng.frontend.Symbol.SymbolID;
import xng.frontend.Symbol.SymbolType;

import java.util.HashMap;
import java.util.Vector;
import java.util.stream.Collectors;

import static java.lang.System.out;

public class SemanticAnalyzer extends XASTBaseVisitor implements XASTVisitor{

    private ScopedSymbolTable SST;
    private XCompileError ce;
    private String curClassName = null;
    private String curFuncName = null;
    private StringBuilder curMemName = new StringBuilder();
    private SymbolType curFuncType = null;
    private int loopCount = 0;
    private boolean isRet = false;
    private HashMap<Integer,SymbolType> exprTypeTable = new HashMap<>();
    private HashMap<Integer,Boolean> exprLvTable = new HashMap<>();

    public SemanticAnalyzer(ScopedSymbolTable _S, XCompileError _ce){
        SST = _S;
        ce = _ce;
    }

    private String getScopeName(String name){
        StringBuilder sb = new StringBuilder();
        if (curClassName!=null) sb.append(curClassName).append('.');
//        else if (curFuncName!=null) sb.append(curFuncName).append('.');
        sb.append(name);
        return sb.toString();
    }

    private boolean checkLv(XASTExprNode node){
        if (node == null || node.type == null) return false;
        out.println("chkLv:"+node.nodeID.toString());
        if (node instanceof XASTPrimNode) {
            if (node.nodeID!=XASTNodeID.p_id) return false;
            return node.type.declType != SymbolType.typType.FUNC;
        }
        switch (node.nodeID){
            case e_idx:
                return true;
            case e_mem:
                return checkLv(node.exprList.elementAt(1));
            case e_dec_p:
            case e_inc_p:
                return true;
//            case e_dec_s:
//            case e_inc_s:
        }
        return false;
    }

    private int checkFuncParam(Vector<SymbolType> fparams, SymbolType ftype){
        out.println("func param len:"+fparams.size()+"/"+ftype.typeList.size());
        if (fparams.size()!=ftype.typeList.size()-1){
            return -1;
        }
        for (int i=0;i<fparams.size();++i){
            out.println("dbg param:"+fparams.elementAt(i)+" "+ftype.typeList.elementAt(i+1));
            if (fparams.elementAt(i)==null || !fparams.elementAt(i).equals(ftype.typeList.elementAt(i+1))){
                return i+1;
            }
        }
        return 0;
    }

    private boolean assertExprType(XASTExprNode node, SymbolType type){
        if (node == null) return false;
        if (node.type == null) return false;
        out.println("assertExprType:"+node.type+" "+type);
        return node.type.equals(type);
    }

    private boolean assertExprType(XASTExprNode node, SymbolType.typType type){
        if (node == null) return false;
        if (node.type == null) return false;
        out.println("assertExprType:"+node.type+" "+type.toString());
        return node.type.declType == type;
    }

    public void visitCUNode(XASTCUNode node){
        out.println("Semantic Analyze begin");
        node.declList.forEach(this::visitStmt);
        SST.pop_scope();
    }
    public void visitClassDeclNode(XASTClassDeclNode node){
        curClassName=node.name;
        out.println("curClass:"+curClassName);
        node.stmtList.forEach(this::visitStmt);
        out.println("exit Class:"+curClassName);
        curClassName=null;
    }
    public void visitFuncDeclNode(XASTFuncDeclNode node){
        visitTypeNode(node.retType);
        curFuncName = node.name;
        out.println("curFunc:"+curFuncName);
        curFuncType = new SymbolType(node.retType);
        SST.push_scope(node.name);
        SST.regSymbol(curFuncName,SST.findSymbol(getScopeName(node.isConstructor?"__init__":curFuncName)));
        visitStmtNode(node.paramList);
        isRet = false;
        node.funcBody.stmtList.forEach(this::visitStmt);
        out.println("exitFunc:"+curFuncName);
        if (!isRet && curFuncType.declType!=SymbolType.typType.VOID) {
            ce.add(XCompileError.ceType.cw_noreturn,curFuncName,node,false);
        }
        curFuncName = null;
        curFuncType = null;
        SST.pop_scope();
    }
    public void visitVarDeclNode(XASTVarDeclNode node){
        visitTypeNode(node.type);
        if (node.initExpr != null) {
            visitExpr(node.initExpr);
            if (!node.initExpr.isEmpty() && !assertExprType(node.initExpr,new SymbolType(node.type))){
                ce.add(XCompileError.ceType.ce_type,"vardecl init:"+node.name,node);
            }
        }
        if (curClassName == null || curFuncName != null) {
            SST.regSymbol(getScopeName(node.name), new SymbolType(node.type),curClassName,curFuncName,node);
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
                if (curFuncName==null) {
                    ce.add(XCompileError.ceType.ce_type,"no return",node);
                }
                visitStmt(node.stmtList.elementAt(0));
                if (node.stmtList.elementAt(0).nodeID == XASTNodeID.e_none) {
                    if (!curFuncType.equals(SymbolType.voidType)){
                        ce.add(XCompileError.ceType.ce_type,"return void:"+curFuncName,node.stmtList.elementAt(0));
                    }
                } else {
                    if (!assertExprType((XASTExprNode)node.stmtList.elementAt(0),curFuncType)) {
                        ce.add(XCompileError.ceType.ce_type,"return:"+curFuncName,node.stmtList.elementAt(0));
                    }
                }
                isRet = true;
                return;
            case s_cont:
            case s_break:
                if (loopCount==0) {
                    ce.add(XCompileError.ceType.ce_outofloop,node.nodeID.toString(),node);
                }
                break;
            case s_plist:
                break;
            case s_for:
                ++loopCount;
                node.stmtList.forEach(this::visitStmt);
                --loopCount;
                if (!node.stmtList.elementAt(1).isEmpty() && !assertExprType((XASTExprNode)node.stmtList.elementAt(1),SymbolType.boolType)){
                    ce.add(XCompileError.ceType.ce_type,"for",node.stmtList.elementAt(1));
                }
                return;
            case s_while:
                ++loopCount;
                node.stmtList.forEach(this::visitStmt);
                --loopCount;
                if (!assertExprType((XASTExprNode)node.stmtList.elementAt(0),SymbolType.boolType)){
                    ce.add(XCompileError.ceType.ce_type,"while",node.stmtList.elementAt(0));
                }
                return;
            case s_if:
                if (node.stmtList!=null) {
                    node.stmtList.forEach(this::visitStmt);
                    if (!assertExprType((XASTExprNode) node.stmtList.elementAt(0), SymbolType.boolType)) {
                        ce.add(XCompileError.ceType.ce_type, "if:" + ((XASTExprNode) node.stmtList.elementAt(0)).type, node.stmtList.elementAt(0));
                    }
                }
                return;
            case s_none:
                return;
            default:
                return;
        }
        if (node.stmtList!=null) node.stmtList.forEach(this::visitStmt);
    }
    public void visitExprNode(XASTExprNode node){
        if (node == null||node.exprList==null) return;
        if (node.nodeID!=XASTNodeID.e_mem) node.exprList.forEach(this::visitExpr);
        switch (node.nodeID){
            case e_add:
                if (assertExprType(node.exprList.elementAt(0),SymbolType.strType)&&
                        assertExprType(node.exprList.elementAt(1),SymbolType.strType)){
                    node.type = SymbolType.strType;
                    out.println("str expr:"+node.nodeID.toString());
                } else if (assertExprType(node.exprList.elementAt(0),SymbolType.intType)&&
                    assertExprType(node.exprList.elementAt(1),SymbolType.intType)){
                    node.type = SymbolType.intType;
                    out.println("int expr:"+node.nodeID.toString());
                } else {
                    ce.add(XCompileError.ceType.ce_type,node.nodeID.toString(),node);
                }
                break;
            case e_ge:
            case e_gt:
            case e_le:
            case e_lt:
                if ((assertExprType(node.exprList.elementAt(0),SymbolType.strType)&&
                    assertExprType(node.exprList.elementAt(1),SymbolType.strType))||
                        (assertExprType(node.exprList.elementAt(0),SymbolType.intType)&&
                        assertExprType(node.exprList.elementAt(1),SymbolType.intType))){
                    node.type = SymbolType.boolType;
                    out.println("expr:"+node.nodeID.toString());
                } else {
                    ce.add(XCompileError.ceType.ce_type,node.nodeID.toString(),node);
                }
                break;
            case e_sub:
            case e_div:
            case e_mod:
            case e_mult:
            case e_shl:
            case e_shr:
            case e_band:
            case e_bor:
            case e_bxor:
                if (assertExprType(node.exprList.elementAt(0),SymbolType.intType)&&
                        assertExprType(node.exprList.elementAt(1),SymbolType.intType)){
                    node.type = SymbolType.intType;
                    out.println("int expr:"+node.nodeID.toString());
                } else {
                    ce.add(XCompileError.ceType.ce_type,node.nodeID.toString()+":"+node.exprList.firstElement().type,node);
                }
                break;
            case e_bneg:
                if (assertExprType(node.exprList.elementAt(0),SymbolType.intType)){
                    node.type = SymbolType.intType;
                    out.println("int expr:"+node.nodeID.toString());
                } else {
                    ce.add(XCompileError.ceType.ce_type,node.nodeID.toString()+":"+node.exprList.firstElement().type,node);
                }
                break;
            case e_inc_s:
            case e_dec_s:
            case e_inc_p:
            case e_dec_p:
                if (!checkLv(node.exprList.firstElement())) {
                        ce.add(XCompileError.ceType.ce_lvalue, node.nodeID.toString(), node);
                }
            case e_pos:
            case e_neg:
                if (assertExprType(node.exprList.elementAt(0),SymbolType.intType)){
                    node.type = SymbolType.intType;
                } else {
                    ce.add(XCompileError.ceType.ce_type,node.nodeID.toString(),node);
                }
                break;
            case e_asgn:
                out.println("dbg:assign:"+node.exprList.elementAt(0).type+"/"+node.exprList.elementAt(1).type);
                if (!checkLv(node.exprList.elementAt(0))) {
                    ce.add(XCompileError.ceType.ce_lvalue,"lv",node.exprList.elementAt(0));
                } else if (node.exprList.elementAt(0).type.equals(node.exprList.elementAt(1).type)){
                    node.type = node.exprList.elementAt(0).type;
                    out.println("assign expr:"+node.type.declType.toString());
                } else if (node.exprList.elementAt(0).type.arrayDim>0
                        || assertExprType(node.exprList.elementAt(0),SymbolType.typType.CLASS)){
                    if (assertExprType(node.exprList.elementAt(1),SymbolType.nullType)){
                        out.println("assign null expr:"+node.type.declType.toString());
                        node.type = SymbolType.nullType;
                    } else {
                        ce.add(XCompileError.ceType.ce_type,"ref:"+node.nodeID.toString(),node);
                    }
                } else {
                    ce.add(XCompileError.ceType.ce_type,"var:"+node.nodeID.toString(),node);
                }
                break;
            case e_call:
                if (!assertExprType(node.exprList.elementAt(0),SymbolType.typType.FUNC)) {
                    ce.add(XCompileError.ceType.ce_type,node.nodeID.toString(),node.exprList.elementAt(0));
                } else {
                    Vector<SymbolType> fparams = new Vector<>();
                    if (node.exprList.size()>1){
                        fparams = node.exprList.elementAt(1).exprList.stream().map(i -> i.type).collect(Collectors.toCollection(Vector::new));
                    }
                    SymbolType ftype = node.exprList.elementAt(0).type;
                    int t = checkFuncParam(fparams,ftype);
                    if (t==0) {
                        node.type = ftype.typeList.elementAt(0);
                        node.toNode = node.exprList.firstElement().toNode;
                    } else if (t==-1){
                        ce.add(XCompileError.ceType.ce_type,"call param len:"+fparams.size()+"/"+(ftype.typeList.size()-1),node);
                    } else {
                        ce.add(XCompileError.ceType.ce_type,"call param retType:"+fparams.elementAt(t-1),node.exprList.elementAt(1).exprList.elementAt(t-1));
                    }
                }
                break;
            case e_idx:
                if (node.exprList.elementAt(0).type.arrayDim==0) {
                    ce.add(XCompileError.ceType.ce_type,node.nodeID.toString()+" dim",node.exprList.elementAt(0));
                } else if (!assertExprType(node.exprList.elementAt(1),SymbolType.intType)){
                    ce.add(XCompileError.ceType.ce_type,node.nodeID.toString()+" idx",node.exprList.elementAt(1));
                } else {
                    out.println("idx expr:");
                    node.type = node.exprList.elementAt(0).type.getDerefType();
                }
                break;
            case e_eq:
            case e_ne:
                if (node.exprList.elementAt(0).type.equals(node.exprList.elementAt(1).type)){
                    out.println("equal expr:");
                    node.type = SymbolType.boolType;
                } else if (node.exprList.elementAt(0).type.declType==SymbolType.typType.NULL
                        ||node.exprList.elementAt(1).type.declType==SymbolType.typType.NULL){
                    XASTExprNode nonNullExpr = node.exprList.elementAt(0);
                    if (nonNullExpr.type.declType==SymbolType.typType.NULL) nonNullExpr = node.exprList.elementAt(1);
                    if (nonNullExpr.type.arrayDim>0
                            || assertExprType(nonNullExpr,SymbolType.typType.CLASS)) {
                        node.type = SymbolType.boolType;
                    }
                    else{
                        ce.add(XCompileError.ceType.ce_type,node.nodeID.toString()+" null",node);
                    }
                } else {
                    ce.add(XCompileError.ceType.ce_type,node.nodeID.toString()+" type",node);
                }
                break;
            case e_land:
            case e_lor:
                if (assertExprType(node.exprList.elementAt(0),SymbolType.boolType)&&
                        assertExprType(node.exprList.elementAt(1),SymbolType.boolType)){
                    node.type = SymbolType.boolType;
                    out.println("logic expr:"+node.nodeID.toString());
                } else {
                    ce.add(XCompileError.ceType.ce_type,node.nodeID.toString(),node);
                }
                break;
            case e_not:
                if (assertExprType(node.exprList.elementAt(0),SymbolType.boolType)){
                    node.type = SymbolType.boolType;
                    out.println("logic expr:"+node.nodeID.toString());
                } else {
                    ce.add(XCompileError.ceType.ce_type,node.nodeID.toString(),node);
                }
                break;
            case e_list:
                break;
            case e_mem:
                visitExpr(node.exprList.elementAt(0));
                if (node.exprList.elementAt(0).type.arrayDim > 0) {
                    out.println("dbg:array mem");
//                    curMemName.append("_array.");
                } else if (node.exprList.elementAt(0).type != null && assertExprType(node.exprList.elementAt(0), SymbolType.typType.CLASS)) {
                    curMemName.append(node.exprList.elementAt(0).type.className).append('.');
                } else if (assertExprType(node.exprList.elementAt(0),SymbolType.strType)) {
                    curMemName.append("string.");
                } else {
                    ce.add(XCompileError.ceType.ce_nodecl,"not class:"+node.exprList.elementAt(0).type.declType.toString(),node);
                    break;
                }
                out.println("dbg:mem:"+curMemName+' '+node.exprList.elementAt(0).type+' '+node.exprList.elementAt(0).instAddr);
                if (node.exprList.elementAt(1).nodeID != XASTNodeID.e_mem && node.exprList.elementAt(1).nodeID != XASTNodeID.p_id){
                    ce.add(XCompileError.ceType.ce_type,"mem:"+node.exprList.elementAt(1).nodeID.toString(),node.exprList.elementAt(1));
                    curMemName = new StringBuilder();
                } else {
                    visitExpr(node.exprList.elementAt(1));
                    node.type=node.exprList.elementAt(1).type;
                    node.instAddr = node.exprList.elementAt(1).instAddr;
                    node.toNode = node.exprList.elementAt(1).toNode;
                    out.println("DBG:mem:"+node.type+' '+node.instAddr+' '+((node.toNode!=null)?node.toNode.nodeID:""));
                }
                break;
            case e_new:
                node.type = node.exprList.elementAt(0).type;
                break;
            case e_none:
                break;
            default:
        }
    }
    public void visitPrimNode(XASTPrimNode node){
        switch (node.nodeID){
            case p_lit_int:
                node.type = SymbolType.intType;
                out.println("prim int:"+node.intLiteral);
                break;
            case p_lit_bool:
                node.type = SymbolType.boolType;
                out.println("prim bool:"+node.intLiteral);
                break;
            case p_lit_null:
                node.type = SymbolType.nullType;
                out.println("prim str:"+node.intLiteral);
                break;
            case p_lit_str:
                out.println("prim str:"+node.strLiteral);
                node.type = SymbolType.strType;
                break;
            case p_this:
                if (curClassName == null) {
                    ce.add(XCompileError.ceType.ce_outofclass,"this",node);
                } else {
                    out.println("prim this:"+curClassName);
                    node.type = new SymbolType(SymbolType.typType.CLASS,curClassName,0);
                }
                break;
            case p_id: {
                String finalName;
                if (curMemName.length()==0){
                    finalName = getScopeName(node.strLiteral);
                } else {
                    finalName = curMemName.append(node.strLiteral).toString();
                    curMemName = new StringBuilder();
                }
                SymbolID sym = SST.findSymbol(finalName);
                if (sym == null) sym = SST.findSymbol(node.strLiteral);
                if (sym == null) {
                    ce.add(XCompileError.ceType.ce_nodecl, "prim:" + finalName, node);
                    node.type = null;
                } else {
                    node.type = sym.type;
                    node.instAddr = sym.reg;
                    node.toNode = sym.startNode;
                    out.println("prim id:" + finalName + ":" + node.type);
                }
                break;
            }
        }
    }

    public void visitCreatorNode(XASTCreatorNode node){
        if (node.ctype.dim > 0){
            boolean empty = false;
            for (XASTExprNode i : node.exprList) {
                visitExpr(i);
                if (i.isEmpty()) {
                    empty = true;
                } else if (empty || !i.type.equals(SymbolType.intType)) {
                    ce.add(XCompileError.ceType.ce_type, "creator:idx:" + i.type, i);
                }
            }
            node.type = new SymbolType(node.ctype);
        } else if (node.ctype.className!=null) {
            SymbolID classSym = SST.findSymbol(node.ctype.className);
            if (classSym == null) {
                ce.add(XCompileError.ceType.ce_nodecl,"class creator:"+node.ctype.className,node);
                return;
            }
            if (node.exprList != null) {
                Vector<SymbolType> plist = new Vector<>();
                node.exprList.forEach(i->{
                    visitExpr(i);
                    plist.add(i.type);
                });
                boolean matched = false;
                for (SymbolType t : classSym.type.typeList){
                    int r = checkFuncParam(plist,t);
                    if (r==0) {
                        matched = true;
                        node.hasConstructor = true;
                        break;
                    }
                }
                if (!matched && !node.exprList.isEmpty()) {
                    ce.add(XCompileError.ceType.ce_type,"class constructor",node);
                }
            }
            node.type = classSym.type;
        }
        out.println("creator:"+node.ctype.nodeID.toString()+":"+node.type);
    }

}
