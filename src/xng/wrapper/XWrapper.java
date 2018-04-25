package xng.wrapper;

import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RecognitionException;
import xng.antlr.MxLexer;
import xng.antlr.MxParser;
import xng.antlr.XNGVisitor;
import xng.common.XCompileError;
import xng.common.XException;
import xng.frontend.AST.XASTCUNode;
import xng.frontend.GlobalScopeBuilder;
import xng.frontend.SemanticAnalyzer;
import xng.frontend.Symbol.ScopedSymbolTable;
import xng.frontend.Symbol.SrcPos;
import xng.frontend.XASTPrinter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

import static java.lang.System.err;
import static java.lang.System.out;

public class XWrapper {
    private static void fatalError(XCompileError ce) throws Exception {
        if (ce != null) ce.print();
        err.println("XNG:fatal error");
        throw new Exception("XNG:fatal error");
//        exit(1);
    }

    public static void main(String[] args) throws Exception {
        XParameter params;
        try {
            params = new XParameter(args);
        } catch (XException e){
            fatalError(null);
            return;
        }
        ANTLRFileStream input;
        String srcPath = params.srcPath.elementAt(0);
        out.println("src filename:" + srcPath);
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
            fatalError(null);
            return;
        }
        XCompileError compileError = new XCompileError(srcLines);
        MxParser.CompilationUnitContext tree;
        MxParser parser;
        try {
            MxLexer lexer = new MxLexer(input);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            parser = new MxParser(tokens);
            tree = parser.compilationUnit();
        } catch (RecognitionException e){
            compileError.add(XCompileError.ceType.ce_syntax, e.toString(), new SrcPos(e.getOffendingToken()), true);
            fatalError(compileError);
            return;
        }
//        out.println(tree.toStringTree(parser));

        XNGVisitor ASTExtractor = new XNGVisitor(parser);
        XASTCUNode prog = ASTExtractor.visitCompilationUnit(tree);

        XASTPrinter printer = new XASTPrinter();
        printer.visitCUNode(prog);

        ScopedSymbolTable SST = new ScopedSymbolTable();
        new GlobalScopeBuilder(SST,compileError).visitCUNode(prog);
        new SemanticAnalyzer(SST,compileError).visitCUNode(prog);
        if (compileError.errorCount>0){
            fatalError(compileError);
            return;
        }
        out.println("XNG:end");
    }
}
