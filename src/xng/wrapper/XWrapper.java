package xng.wrapper;

import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RecognitionException;
import xng.XIR.XCFG;
import xng.antlr.MxLexer;
import xng.antlr.MxParser;
import xng.antlr.XNGErrorListener;
import xng.antlr.XNGVisitor;
import xng.common.XCompileError;
import xng.common.XException;
import xng.frontend.AST.XASTCUNode;
import xng.frontend.GlobalScopeBuilder;
import xng.frontend.SemanticAnalyzer;
import xng.frontend.Symbol.ScopedSymbolTable;
import xng.frontend.Symbol.SrcPos;
import xng.frontend.XASTPrinter;
import xng.frontend.XIRGenerator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

import static java.lang.System.err;
import static java.lang.System.out;

public class XWrapper {

    private static void checkError(XCompileError ce) throws Exception{
        if (ce != null && ce.errorCount>0){
            ce.print();
            fatalError();
        }
    }

    private static void fatalError() throws Exception {
        err.println("XNG:fatal error");
        throw new Exception("XNG:fatal error");
//        exit(1);
    }

    public static void main(String[] args) throws Exception {
        XParameter params;
        try {
            params = new XParameter(args);
        } catch (XException e){
            fatalError();
            return;
        }
        ANTLRFileStream input;
        String srcPath = params.srcPath.elementAt(0);
        Vector<String> srcLines = new Vector<>();
        try {
            input = new ANTLRFileStream(srcPath);
            BufferedReader br = new BufferedReader(new FileReader(srcPath));
            String t;
            while((t=br.readLine())!=null){
                srcLines.add(t);
            }
        } catch (IOException e) {
            e.printStackTrace();
            fatalError();
            return;
        }
        XCompileError compileError = new XCompileError(srcLines);
        MxParser.CompilationUnitContext tree=null;
        MxParser parser=null;
        try {
            MxLexer lexer = new MxLexer(input);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            parser = new MxParser(tokens);
            XNGErrorListener listener = new XNGErrorListener(compileError);
            parser.removeErrorListeners();
            parser.addErrorListener(listener);
            tree = parser.compilationUnit();
        } catch (RecognitionException e){
            compileError.add(XCompileError.ceType.ce_syntax, e.toString(), new SrcPos(e.getOffendingToken()), true);
        }

        checkError(compileError);
//        out.println(tree.toStringTree(parser));

        XASTCUNode prog = new XNGVisitor(parser).visitCompilationUnit(tree);

        XASTPrinter printer = new XASTPrinter();
        printer.visitCUNode(prog);

        ScopedSymbolTable SST = new ScopedSymbolTable();
        new GlobalScopeBuilder(SST,compileError).visitCUNode(prog);
        new SemanticAnalyzer(SST,compileError).visitCUNode(prog);
        checkError(compileError);

        XCFG cfg = new XCFG();
        new XIRGenerator(cfg).visitCUNode(prog);

        out.println("XNG:end");
    }
}
