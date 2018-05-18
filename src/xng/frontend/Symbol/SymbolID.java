package xng.frontend.Symbol;

import xng.XIR.XCFGNode;
import xng.XIR.XIRInstAddr;

public class SymbolID {

    public SymbolType type;
    public Integer id;
    public Integer tag;
    public XIRInstAddr reg = null;
    public XCFGNode startNode = null;

    public SymbolID(SymbolType _t,Integer _i, Integer _tg){
        tag = _tg;
        type = _t;
        id = _i;
    }

}

