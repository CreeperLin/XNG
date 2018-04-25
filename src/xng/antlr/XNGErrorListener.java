package xng.antlr;

import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import xng.common.XCompileError;
import xng.frontend.Symbol.SrcPos;

public class XNGErrorListener extends BaseErrorListener {
    private XCompileError ce;

    public XNGErrorListener(XCompileError _ce) {
        ce = _ce;
    }

    @Override
    public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {
        super.syntaxError(recognizer, offendingSymbol, line, charPositionInLine, msg, e);
        ce.add(XCompileError.ceType.ce_syntax, msg, new SrcPos(e.getOffendingToken()), true);
    }
}
