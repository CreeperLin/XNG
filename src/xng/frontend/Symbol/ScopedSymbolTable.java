package xng.frontend.Symbol;

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
    Integer symCount=1;
    public ScopedSymbolTable(){
        symTableStack = new Stack<>();
    }

    public void pop_scope(){
        symTableStack.pop();
        out.println("SST:pop scope: cur:"+symTableStack.size());
    }

    public void push_scope(String scopeName){
        symTableStack.push(new SymbolScope(scopeName));
        out.println("SST:push scope:"+scopeName+" cur:"+symTableStack.size());
    }

    public boolean regSymbol(String str, SymbolType type, Integer tag){
        if (symTableStack.peek().symTable.containsKey(str)) {
            return true;
        }
//        if (findSymbol(str)>0) {
//            return false;
//        }
        symTableStack.peek().symTable.put(str,new SymbolID(type,symCount++,tag));
        out.println("reg sym:"+symTableStack.peek().scopeName+":"+str);
        return false;
    }

    public SymbolID findSymbol(String str){
        for (SymbolScope i : symTableStack) {
            if (i.symTable.containsKey(str)) {
                return i.symTable.get(str);
            }
        }
        return null;
    }

}


