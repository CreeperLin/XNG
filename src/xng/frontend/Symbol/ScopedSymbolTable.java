package xng.frontend.Symbol;

import xng.common.XException;

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
    Stack<SymbolScope> symTableStack;
    Integer symCount=1;
    public ScopedSymbolTable(){
        symTableStack = new Stack<>();
        symTableStack.push(new SymbolScope("global"));
    }

    void pop_scope(){
        symTableStack.pop();
        out.println("SST:pop scope: cur:"+symTableStack.size());
    }

    void push_scope(String scopeName){
        symTableStack.push(new SymbolScope(scopeName));
        out.println("SST:push scope:"+scopeName+" cur:"+symTableStack.size());
    }

    boolean regSymbol(String str, SymbolID.symType type, Integer tag) throws XException {
        out.println("sym:"+str);
        if (findSymbol(str)>0) {
            throw new XException(XException.exType.compile_error,"error: redifinition of " + str);
        }
        symTableStack.peek().symTable.put(str,new SymbolID(type,symCount++,tag));
        return true;
    }

    int findSymbol(String str){
        final int[] t = {0};
        symTableStack.forEach(i -> {
            if (i.symTable.containsKey(str)) {
                t[0] = i.symTable.get(str).id;
            }
        });
        return t[0];
    }
}


