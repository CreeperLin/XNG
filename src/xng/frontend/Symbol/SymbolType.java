package xng.frontend.Symbol;

import xng.frontend.AST.XASTTypeNode;

public class SymbolType {
    static public SymbolType boolType = new SymbolType(typType.BOOL,null,0);
    static public SymbolType intType = new SymbolType(typType.INT,null,0);
    static public SymbolType strType = new SymbolType(typType.STR,null,0);
    static public SymbolType voidType = new SymbolType(typType.VOID,null,0);
    static public SymbolType nullType = new SymbolType(typType.NULL,null,0);
    class ArrayType extends SymbolType{
        ArrayType(){
            super(null,null,0);
        }
    }

    public enum typType{
        VOID,INT,STR,BOOL,CLASS,FUNC,NULL
    }
    public typType declType;
    public String className;
    public int arrayDim;

    public SymbolType(typType _t,String _n,int _d){
        declType = _t;
        className = _n;
        arrayDim = _d;
    }

    public SymbolType(XASTTypeNode node){
        switch (node.nodeID){
            case t_void:
                declType = typType.VOID;
            case t_int:
                declType = typType.INT;
            case t_bool:
                declType = typType.BOOL;
            case t_class:
                declType = typType.CLASS;
            case t_str:
                declType = typType.STR;
        }
        className = node.className;
        arrayDim = node.dim;
    }


    public boolean equals(SymbolType _t){
        return (arrayDim==_t.arrayDim)&&(declType==_t.declType)&&(className.equals(_t.className));
    }
}
