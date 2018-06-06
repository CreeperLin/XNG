package xng.XIR;

import java.util.Vector;

public class XIRProcInfo {
    public XCFGNode entryNode;
    public XCFGNode exitNode;
    public int stackSize = 0;
    public String name;
    public boolean isCaller = false;
    public boolean isCallee = false;
    public int paramCount = 0;
    public Vector<Integer> calleeSavedReg = new Vector<>();

    public XIRProcInfo(XCFGNode en, XCFGNode ex) {
        entryNode = en;
        exitNode = ex;
        name = en.name;
    }

}
