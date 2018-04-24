package xng.frontend.Symbol;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.TerminalNode;

public class SrcPos {
    public int startLine;
    public int startColumn;
    public int endLine;
    public int endColumn;

    public SrcPos(int _l, int _c){
        startLine = endLine = _l;
        startColumn = endColumn = _c;
    }

    public SrcPos(int _l, int _c, int _el, int _ec){
        startLine = _l;
        startColumn = _c;
        endLine = _el;
        endColumn = _ec;
    }

    public SrcPos(ParserRuleContext ctx){
        startLine = ctx.start.getLine();
        startColumn = ctx.start.getCharPositionInLine();
        endLine = ctx.stop.getLine();
        endColumn = ctx.stop.getCharPositionInLine();
    }

    public SrcPos(Token tk){
        startLine = endLine = tk.getLine();
        startColumn = tk.getCharPositionInLine();
        endColumn = startColumn + tk.getText().length();
    }

    public SrcPos(TerminalNode node){
        this(node.getSymbol());
    }

    @Override
    public String toString() {
        return "line "+startLine+":"+startColumn
                + ((startLine==endLine&&startColumn==endColumn)?"":" to line "+endLine+":"+endColumn);
    }
}
