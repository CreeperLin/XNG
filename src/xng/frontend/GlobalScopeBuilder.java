package xng.frontend;

import org.antlr.v4.runtime.tree.ParseTree;
import xng.common.XCompileError;
import xng.frontend.AST.*;
import xng.frontend.Symbol.ScopedSymbolTable;
import xng.frontend.Symbol.SymbolType;

public class GlobalScopeBuilder extends XASTBaseVisitor implements XASTVisitor {
    ScopedSymbolTable SST;
    XCompileError ce;
    String curClassName = null;

    public GlobalScopeBuilder(ScopedSymbolTable _S, XCompileError _c){
        SST = _S;
        ce = _c;
    }

    String getScopeName(String name, boolean con){
        return (curClassName==null?"":curClassName+'.')+(con?"__init__":name);
    }

    @Override
    public void visitCUNode(XASTCUNode node) {
        System.out.println("Global Scope Builder begin:");
        SST.push_scope("global");
        SST.regSymbol("size",new SymbolType(SymbolType.typType.FUNC,null,0),0);
        SST.regSymbol("println",new SymbolType(SymbolType.typType.FUNC,null,0),0);
        SST.regSymbol("print",new SymbolType(SymbolType.typType.FUNC,null,0),0);
        SST.regSymbol("getString",new SymbolType(SymbolType.typType.FUNC,null,0),0);
        SST.regSymbol("getInt",new SymbolType(SymbolType.typType.FUNC,null,0),0);
        SST.regSymbol("toString",new SymbolType(SymbolType.typType.FUNC,null,0),0);
        SST.regSymbol("string.length",new SymbolType(SymbolType.typType.FUNC,null,0),0);
        SST.regSymbol("string.substring",new SymbolType(SymbolType.typType.FUNC,null,0),0);
        SST.regSymbol("string.parseInt",new SymbolType(SymbolType.typType.FUNC,null,0),0);
        SST.regSymbol("string.ord",new SymbolType(SymbolType.typType.FUNC,null,0),0);

        node.declList.forEach(this::visitStmt);
    }

    @Override
    public void visitClassDeclNode(XASTClassDeclNode node) {
        if (SST.regSymbol(node.name, new SymbolType(SymbolType.typType.CLASS, node.name, 0), 0)){
            ce.add(XCompileError.ceType.ce_redef,"class:"+node.name,node);
        }
        curClassName=node.name;
        System.out.println("curClass:"+curClassName);
        node.stmtList.forEach(this::visitStmt);
        System.out.println("exit Class");
        curClassName=null;
    }

    @Override
    public void visitFuncDeclNode(XASTFuncDeclNode node) {
        if (node.isConstructor){
            System.out.println("found constructor:");
            if (node.name.equals(curClassName)){
                if (SST.regSymbol(getScopeName(node.name, true), new SymbolType(SymbolType.typType.FUNC, node.name, 0), 0)) {
                    ce.add(XCompileError.ceType.ce_default,"unexpected error",node);
                }
            } else {
                ce.add(XCompileError.ceType.ce_invalid_constructor,"unidentical identifier:"+curClassName,node);
            }
        } else {
            if (SST.regSymbol(getScopeName(node.name, false), new SymbolType(SymbolType.typType.FUNC, node.name, 0), 0)){
                ce.add(XCompileError.ceType.ce_redef,"func:"+node.name,node);
            }
        }
        visitStmt(node.paramList);
    }

    @Override
    public void visitVarDeclNode(XASTVarDeclNode node) {
        if (SST.regSymbol(getScopeName(node.name, false), new SymbolType(node.type), 0)) {
            ce.add(XCompileError.ceType.ce_redef,"var:"+node.name,node);
        }
    }

    @Override
    public void visitStmtNode(XASTStmtNode node) {
        switch (node.nodeID){
            case s_plist:

                break;
        }
    }
}
