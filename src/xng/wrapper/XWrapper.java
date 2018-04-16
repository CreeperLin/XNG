package xng.wrapper;

import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RecognitionException;
import xng.antlr.MxLexer;
import xng.antlr.MxParser;
import xng.antlr.XNGVisitor;
import xng.common.XException;
import xng.frontend.AST.XASTCUNode;
import xng.frontend.XASTPrinter;

import java.io.IOException;

import static java.lang.System.err;
import static java.lang.System.out;

public class XWrapper {
    public static void main(String[] args){
        XParameter params;
        try {
            params = new XParameter(args);
        } catch (XException e){
            err.println("XNG:fatal error");
            return;
        }
        ANTLRFileStream input = null;
        String srcPath = params.srcPath.elementAt(0);
        out.println("src filename:" + srcPath);
        try {
            input = new ANTLRFileStream(srcPath);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        MxParser.CompilationUnitContext tree;
        MxParser parser;
        try {
            MxLexer lexer = new MxLexer(input);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            parser = new MxParser(tokens);
            tree = parser.compilationUnit();
        } catch (RecognitionException e){
            err.println("XNG:fatal error");
            return;
        }
        out.println(tree.toStringTree(parser));

//        XNGListener extractor = new XNGListener(parser);
//        ParseTreeWalker.DEFAULT.walk(extractor,tree);

        XNGVisitor ASTExtractor = new XNGVisitor(parser);
        XASTCUNode prog = ASTExtractor.visitCompilationUnit(tree);

        XASTPrinter printer = new XASTPrinter();
        printer.visitCUNode(prog);
    }
}
