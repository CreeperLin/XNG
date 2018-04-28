package xng.frontend.AST;

import org.antlr.v4.runtime.ParserRuleContext;
import xng.XIR.XIRInst;
import xng.XIR.XIRInstAddr;
import xng.frontend.Symbol.SrcPos;
import xng.frontend.Symbol.SymbolType;

import java.util.Vector;

public class XASTExprNode extends XASTStmtNode{

    public Vector<XASTExprNode> exprList;

    public SymbolType type = null;

    public XIRInstAddr instAddr = null;
    public Vector<XIRInst> instList = new Vector<>();

    public XASTExprNode() {
        super(new SrcPos(1,0),XASTNodeID.e_none,new Vector<>());
    }

    public XASTExprNode(SrcPos ctx, XASTNodeID _e, Vector<XASTExprNode> _l){
        super(ctx, _e,null);
        exprList = _l;
    }

    public boolean isEmpty(){return nodeID==XASTNodeID.e_none;}

}
