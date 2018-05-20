package xng.frontend.Symbol;

import xng.XIR.XCFG;
import xng.XIR.XCFGNode;
import xng.XIR.XIRInstAddr;
import xng.common.XCompileError;
import xng.frontend.AST.*;
import xng.wrapper.XParameter;

import java.util.Stack;
import java.util.TreeMap;

import static java.lang.System.out;

public class ScopedSymbolTable {
    public class SymbolScope {
        TreeMap<String,SymbolID> symTable;

        String scopeName;

        SymbolScope(String nm) {
            symTable = new TreeMap<>();
            scopeName = nm;
        }

    }

    public Stack<SymbolScope> symTableStack;
    private Integer symCount=0;
    private XCompileError ce;
    private XCFG cfg;

    public ScopedSymbolTable(XCFG _cfg, XCompileError _ce) {
        symTableStack = new Stack<>();
        ce = _ce;
        cfg = _cfg;
    }

    public void pop_scope(){
        symTableStack.pop();
        out.println("SST:pop scope: cur:"+symTableStack.size());
    }

    public void push_scope(String scopeName){
        symTableStack.push(new SymbolScope(scopeName));
        out.println("SST:push scope:"+scopeName+" cur:"+symTableStack.size());
    }

    public int getSymTypeMemSize(SymbolType type){
        switch (type.declType){
            case INT:
            case BOOL:
            case NULL:
            case STR:
                return 4;
            case FUNC:
            case VOID:
                return 0;
            case CLASS:
                return findSymbol(type.className).tag;
        }
        return 0;
    }

    public void regSymbol(String str, SymbolID sym) {
        if (sym == null) return;
        symTableStack.peek().symTable.put(str,sym);
    }

    public SymbolID regSymbol(String str, SymbolType type, Integer tag, XASTBaseNode node) {
        return regSymbol(str,type,tag,null,null,node);
    }

    public SymbolID regSymbol(String name, SymbolType type, Integer tag, String curClassName, String curFuncName, XASTBaseNode node){
//        if (symTableStack.peek().symTable.containsKey(name) || symTableStack.elementAt(0).symTable.containsKey(name)) {
        if (symTableStack.peek().symTable.containsKey(name)) {
            ce.add(XCompileError.ceType.ce_redef,type+" "+name,node);
            return null;
        }
        if (XParameter.isWarningAll && findSymbol(name)!=null) {
            ce.add(XCompileError.ceType.cw_shadow,name,node,false);
        }
        SymbolID sym = new SymbolID(type,++symCount,tag);
        if (type.declType == SymbolType.typType.FUNC){
            if (symTableStack.size() == 1) {
                sym.startNode = cfg.addNode();
                sym.startNode.name = "_FUNC"+name;
                sym.reg = XIRInstAddr.newRegAddr();
            }
        } else if (node instanceof XASTVarDeclNode) {
            if (curFuncName != null) {
                SymbolID funcSym;
                if ((funcSym=findSymbol(curFuncName))==null){
                    ce.add(XCompileError.ceType.ce_nodecl,curClassName,node);
                } else ((XASTVarDeclNode) node).reg = sym.reg = XIRInstAddr.newStackAddr(getSymTypeMemSize(type));
            } else if (curClassName != null){
                SymbolID classSym;
                if ((classSym=findSymbol(curClassName))==null){
                    ce.add(XCompileError.ceType.ce_nodecl,curClassName,node);
                } else {
                    sym.tag = classSym.tag;
                    ((XASTVarDeclNode)node).reg = sym.reg = XIRInstAddr.newImmAddr(-sym.tag,0);
                    classSym.tag += 4;
                }
            } else {
                ((XASTVarDeclNode)node).reg = sym.reg = XIRInstAddr.newStaticAddr(name,getSymTypeMemSize(type));
            }
        }
        symTableStack.peek().symTable.put(name,sym);
        out.println("sym reg:"+symTableStack.peek().scopeName+":"+name+":"+type+' '+sym.reg+' '+sym.tag+":"+symCount);
        return sym;
    }

    public SymbolID findSymbol(String str){
        out.print("sym find:"+str);
        for (int i = symTableStack.size() - 1; i >= 0; --i) {
            SymbolScope t = symTableStack.elementAt(i);
            if (t.symTable.containsKey(str)) {
                out.println(" found:" + t.scopeName + ":" + t.symTable.get(str).type + ":" + t.symTable.get(str).id);
                return t.symTable.get(str);
            }
        }
        out.println(" not found");
        return null;
    }

}


