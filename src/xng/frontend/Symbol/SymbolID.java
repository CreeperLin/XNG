package xng.frontend.Symbol;

public class SymbolID {
    enum symType {
        sym_none,sym_var,sym_func,sym_class
    }

    symType type;
    Integer id;
    Integer tag;
    SymbolID(symType _t,Integer _i, Integer _tg){
        tag = _tg;
        type = _t;
        id = _i;
    }

}

