package xng.frontend;

import xng.common.XCompileError;
import xng.frontend.AST.*;
import xng.frontend.Symbol.ScopedSymbolTable;
import xng.frontend.Symbol.SymbolID;
import xng.frontend.Symbol.SymbolType;

import java.util.Vector;

public class GlobalScopeBuilder extends XASTBaseVisitor implements XASTVisitor {
    private ScopedSymbolTable SST;
    private XCompileError ce;
    private String curClassName = null;
    private String curFuncName = null;
    private Vector<SymbolType> funcParams = new Vector<>();

    public GlobalScopeBuilder(ScopedSymbolTable _S, XCompileError _c){
        SST = _S;
        ce = _c;
    }

    private String getScopeName(String name, boolean con){
        return (curClassName==null?"":curClassName+'.')+(con?"__init__":name);
    }

    @Override
    public void visitCUNode(XASTCUNode node) {
        System.out.println("Global Scope Builder begin:");
        SST.push_scope("global");
        Vector<SymbolType> plist = new Vector<>();
        plist.add(SymbolType.intType);
        SST.regSymbol("size",new SymbolType(new Vector<>(plist)),0);
        plist.clear();
        plist.add(SymbolType.voidType);
        plist.add(SymbolType.strType);
        SST.regSymbol("println",new SymbolType(new Vector<>(plist)),0);
        SST.regSymbol("print",new SymbolType(new Vector<>(plist)),0);
        plist.clear();
        plist.add(SymbolType.strType);
        SST.regSymbol("getString",new SymbolType(new Vector<>(plist)),0);
        plist.clear();
        plist.add(SymbolType.intType);
        SST.regSymbol("getInt",new SymbolType(new Vector<>(plist)),0);
        plist.clear();
        plist.add(SymbolType.strType);
        plist.add(SymbolType.intType);
        SST.regSymbol("toString",new SymbolType(new Vector<>(plist)),0);
        plist.clear();
        plist.add(SymbolType.intType);
        SST.regSymbol("string.length",new SymbolType(new Vector<>(plist)),0);
        plist.clear();
        plist.add(SymbolType.strType);
        plist.add(SymbolType.intType);
        plist.add(SymbolType.intType);
        SST.regSymbol("string.substring",new SymbolType(new Vector<>(plist)),0);
        plist.clear();
        plist.add(SymbolType.intType);
        SST.regSymbol("string.parseInt",new SymbolType(new Vector<>(plist)),0);
        plist.clear();
        plist.add(SymbolType.intType);
        plist.add(SymbolType.intType);
        SST.regSymbol("string.ord",new SymbolType(new Vector<>(plist)),0);
        node.declList.forEach(this::visitStmt);

        SymbolID symMain = SST.findSymbol("main");
        if (symMain == null) {
            ce.add(XCompileError.ceType.ce_nodecl,"main",node);
        } else if (!symMain.type.typeList.elementAt(0).equals(SymbolType.intType)) {
            ce.add(XCompileError.ceType.ce_type,"main ret type",node);
        }
    }

    @Override
    public void visitClassDeclNode(XASTClassDeclNode node) {
        if (SST.regSymbol(node.name, new SymbolType(SymbolType.typType.CLASS, node.name, 0,new Vector<>()), 0)){
            ce.add(XCompileError.ceType.ce_redef,"class:"+node.name,node);
        }
        curClassName=node.name;
        System.out.println("classdecl:"+curClassName);
        node.stmtList.forEach(this::visitStmt);
        curClassName=null;
    }

    @Override
    public void visitFuncDeclNode(XASTFuncDeclNode node) {
        funcParams.clear();
        curFuncName = node.name;
        funcParams.add(new SymbolType(node.retType));
        visitStmt(node.paramList);
        SymbolType type = new SymbolType(new Vector<>(funcParams));
        curFuncName = null;
        System.out.println("funcdecl:"+node.name+":params:"+type+" ret:"+node.retType.nodeID.toString());
        if (node.isConstructor) {
            System.out.println("found constructor:");
            if (node.name.equals(curClassName)){
                if (SST.regSymbol(getScopeName(node.name, true), type, 0)) {
                    ce.add(XCompileError.ceType.ce_default,"unexpected error",node);
                }
                SymbolID curClass = SST.findSymbol(curClassName);
                curClass.type.typeList.add(type);
            } else {
                ce.add(XCompileError.ceType.ce_invalid_constructor,"unidentical identifier:"+curClassName,node);
            }
        } else {
            if (SST.regSymbol(getScopeName(node.name, false), type, 0)){
                ce.add(XCompileError.ceType.ce_redef,"func:"+node.name,node);
            }
        }
    }

    @Override
    public void visitVarDeclNode(XASTVarDeclNode node) {
        SymbolType type = new SymbolType(node.type);
        System.out.println("vardecl:"+node.name+":"+node.type.nodeID.toString()+":"+type);
        if (curFuncName != null) {
            funcParams.add(type);
            System.out.println("func param:"+funcParams.size()+":"+curFuncName+":"+node.name+":"+type);
        }
//        else if (SST.regSymbol(getScopeName(node.name, false), type, 0)) {
//            ce.add(XCompileError.ceType.ce_redef,"var:"+node.name,node);
//        }
    }

    @Override
    public void visitStmtNode(XASTStmtNode node) {
        node.stmtList.forEach(this::visitStmt);
    }
}
