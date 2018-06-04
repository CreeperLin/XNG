package xng.XIR;

public class XIRProcInfo {
    public XCFGNode entryNode;
    public XCFGNode exitNode;
    public int stackSize = 0;
    public boolean isGlobal = false;
    public boolean isCaller = false;
    public boolean isCallee = false;

    public XIRProcInfo(XCFGNode en, XCFGNode ex) {
        entryNode = en;
        exitNode = ex;
    }

}
