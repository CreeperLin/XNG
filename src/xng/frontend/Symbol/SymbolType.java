package xng.frontend.Symbol;

import xng.frontend.AST.XASTTypeNode;

import java.util.Vector;

public class SymbolType {
    static public SymbolType boolType = new SymbolType(typType.BOOL,null,0);
    static public SymbolType intType = new SymbolType(typType.INT,null,0);
    static public SymbolType strType = new SymbolType(typType.STR,null,0);
    static public SymbolType voidType = new SymbolType(typType.VOID,null,0);
    static public SymbolType nullType = new SymbolType(typType.NULL,null,0);
    static public SymbolType funcType = new SymbolType(typType.FUNC,null,0);
    static public SymbolType classType = new SymbolType(typType.CLASS,null,0);

    public enum typType{
        VOID,INT,STR,BOOL,CLASS,FUNC,NULL
    }
    public typType declType;
    public String className;
    public int arrayDim;
    public Vector<SymbolType> typeList = null;

    public SymbolType(typType _t,String _n,int _d){
        declType = _t;
        className = _n;
        arrayDim = _d;
    }

    public SymbolType(typType _t,String _n,int _d,Vector<SymbolType> _v){
        declType = _t;
        className = _n;
        arrayDim = _d;
        typeList = _v;
    }

    public SymbolType(Vector<SymbolType> _v){
        declType = typType.FUNC;
        className = null;
        arrayDim = 0;
        typeList = _v;
    }

    public SymbolType(XASTTypeNode node){
        switch (node.nodeID){
            case t_void:
                declType = typType.VOID;
                break;
            case t_int:
                declType = typType.INT;
                break;
            case t_bool:
                declType = typType.BOOL;
                break;
            case t_class:
                declType = typType.CLASS;
                break;
            case t_str:
                declType = typType.STR;
                break;
        }
        className = node.className;
        arrayDim = node.dim;
    }

    public SymbolType getDerefType(){
        assert this.arrayDim > 1;
        return new SymbolType(this.declType,this.className,this.arrayDim-1);
    }

    public boolean equals(SymbolType _t){
        if (_t == null) return false;
        if (declType == typType.NULL) return _t.arrayDim>0 || _t.declType == typType.NULL || _t.declType==typType.CLASS;
        if (_t.declType==typType.NULL) return arrayDim>0 || declType==typType.CLASS;
        return (arrayDim==_t.arrayDim)&&(declType==_t.declType)&&((className==null)?_t.className==null:className.equals(_t.className));
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (typeList!=null) typeList.forEach(i-> sb.append(i).append(" "));
        return "(" + declType.toString() +((typeList==null)?"":("{ "+sb.toString()+"}")) +((className==null)?"":("("+className+")")) +"["+ arrayDim + "])";
    }
}
