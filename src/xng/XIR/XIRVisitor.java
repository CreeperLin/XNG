package xng.XIR;

import xng.XIR.XCFG;
import xng.XIR.XCFGNode;

public interface XIRVisitor {

    void visitXCFG(XCFG cfg);
    void visitXCFGNode(XCFGNode node);

}
