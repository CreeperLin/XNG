package xng.frontend.Symbol;

import xng.XIR.XCFG;
import xng.XIR.XCFGNode;
import xng.XIR.XIRData;
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

    public void regSymbol(String str, SymbolID sym) {
        if (sym == null) return;
        symTableStack.peek().symTable.put(str,sym);
    }

    public SymbolID regSymbol(String str, SymbolType type, XASTBaseNode node) {
        return regSymbol(str,type,null,null,node);
    }

    public SymbolID regSymbol(String name, SymbolType type, String curClassName, String curFuncName, XASTBaseNode node){
//        if (symTableStack.peek().symTable.containsKey(name) || symTableStack.elementAt(0).symTable.containsKey(name)) {
        if (symTableStack.peek().symTable.containsKey(name)) {
            ce.add(XCompileError.ceType.ce_redef,type+" "+name,node);
            return null;
        }
        if (XParameter.isWarningAll && findSymbol(name)!=null) {
            ce.add(XCompileError.ceType.cw_shadow,name,node,false);
        }
        SymbolID sym = new SymbolID(type,++symCount);
        if (type.declType == SymbolType.typType.FUNC){
            if (symTableStack.size() == 1) {
                sym.startNode = cfg.addNode();
                if (curClassName==null) sym.startNode.name = "_func_"+name;
                else if (curClassName.equals("_lib_")) {
                    if (name.startsWith("string")){
                        sym.startNode.name = "_lib_str_"+name.substring(7);
                    } else sym.startNode.name = "_lib_"+name;
                }
                else sym.startNode.name = "_class_"+name;
//                sym.reg = XIRInstAddr.newRegAddr();
            }
        } else if (node instanceof XASTVarDeclNode) {
            if (curFuncName != null) {
//                SymbolID funcSym;
//                if ((funcSym=findSymbol(curFuncName))==null){
//                    ce.add(XCompileError.ceType.ce_nodecl,curClassName,node);
//                } else
//                if (type.declType == SymbolType.typType.CLASS){
//                    ((XASTVarDeclNode) node).reg = sym.reg = XIRInstAddr.newRegAddr();
//                } else ((XASTVarDeclNode) node).reg = sym.reg = XIRInstAddr.newRegAddr();
                ((XASTVarDeclNode) node).reg = sym.reg = XIRInstAddr.newRegAddr();
            } else if (curClassName != null){
                SymbolID classSym;
                if ((classSym=findSymbol(curClassName))==null){
                    ce.add(XCompileError.ceType.ce_nodecl,curClassName,node);
                } else {
                    int sz = classSym.type.getMemSize();
//                    System.out.println("SST:dbg:"+sz);
                    ((XASTVarDeclNode)node).reg = sym.reg = XIRInstAddr.newImmAddr(sz,0);
                    classSym.type.classMemSize += type.getMemSize();
                }
            } else {
                int size = type.getMemSize();
                String varName = "_v_"+name;
                ((XASTVarDeclNode)node).reg = sym.reg = XIRInstAddr.newMemAddr(XIRInstAddr.newStaticAddr(varName,size),XIRInstAddr.newImmAddr(0,0),0,0);
            }
        }
        symTableStack.peek().symTable.put(name,sym);
        out.println("sym reg:"+symTableStack.peek().scopeName+":"+name+":"+type+' '+sym.reg+":"+symCount);
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


