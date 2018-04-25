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
    private Integer symCount=0;
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
//        if (symTableStack.peek().symTable.containsKey(str) || symTableStack.elementAt(0).symTable.containsKey(str)) {
        if (symTableStack.peek().symTable.containsKey(str)) {
            return true;
        }
//        if (findSymbol(str)>0) {
//            return false;
//        }
        symTableStack.peek().symTable.put(str,new SymbolID(type,++symCount,tag));
        out.println("sym reg:"+symTableStack.peek().scopeName+":"+str+":"+type+":"+symCount);
        return false;
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


