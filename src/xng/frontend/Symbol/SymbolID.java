package xng.frontend.Symbol;

import xng.XIR.XCFGNode;
import xng.XIR.XIRInstAddr;

public class SymbolID {

    public SymbolType type;
    public Integer id;
    public XIRInstAddr reg = null;
    public XCFGNode startNode = null;

    public SymbolID(SymbolType _t,Integer _i){
        type = _t;
        id = _i;
    }

}

