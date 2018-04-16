package xng.antlr;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.Stack;
import java.util.TreeMap;

import static java.lang.System.err;
import static java.lang.System.out;

public class XNGListener extends MxBaseListener {
    private MxParser t;

    enum symType {
        s_none,s_var,s_func,s_class
    }

    public class SymbolID {
        symType type;
        Integer id;
        Integer tag;
        SymbolID(symType _t,Integer _i, Integer _tg){
            tag = _tg;
            type = _t;
            id = _i;
        }
    }

    public class SymbolScope {
        TreeMap<String,SymbolID> symTable;
        String scopeName;

        SymbolScope(String nm) {
            symTable = new TreeMap<>();
            scopeName = nm;
        }

    }

    public class ScopedSymbolTable {
        Stack<SymbolScope> symTableStack;
        Integer symCount=1;
        ScopedSymbolTable(){
            symTableStack = new Stack<>();
//            symTableStack.removeAllElements();
            symTableStack.push(new SymbolScope("global"));
        }

        void pop_scope(){
            symTableStack.pop();
            out.println("SST:pop scope: cur:"+symTableStack.size());
        }

        void push_scope(String scopeName){
            symTableStack.push(new SymbolScope(scopeName));
            out.println("SST:push scope:"+scopeName+" cur:"+symTableStack.size());
        }

        boolean regSymbol(String str, symType type, Integer tag){
            out.println("sym:"+str);
            if (findSymbol(str)>0) {
                err.println("error: redifinition of " + str);
//                exit(1);
                return false;
            }
//            out.println("symbol not found:"+str);
            symTableStack.peek().symTable.put(str,new SymbolID(type,symCount++,tag));
            return true;
        }

        int findSymbol(String str){
            final int[] t = {0};
            symTableStack.forEach(i -> {
                if (i.symTable.containsKey(str)) {
                    t[0] = i.symTable.get(str).id;
                }
            });
            return t[0];
        }
    }

    ScopedSymbolTable SST = new ScopedSymbolTable();
    private String curClassName;

    private int curVarType;

    public XNGListener(MxParser _t){
        t=_t;
    }



    @Override public void enterCompilationUnit(MxParser.CompilationUnitContext ctx) {
        out.println("compilation begin");
    }

    @Override public void exitCompilationUnit(MxParser.CompilationUnitContext ctx) {

        out.println("compilation terminated");
    }

    @Override public void enterClassDecl(MxParser.ClassDeclContext ctx) {
        String className = ctx.Identifier().toString();
        out.println("class name:" +className);
        SST.regSymbol(className, symType.s_class, 0);
        curClassName = className;
    }

    @Override public void exitClassDecl(MxParser.ClassDeclContext ctx) {

    }

    @Override public void enterClassBodyDecl(MxParser.ClassBodyDeclContext ctx) {

    }

    @Override public void exitClassBodyDecl(MxParser.ClassBodyDeclContext ctx) { }

    @Override public void enterConstructorDecl(MxParser.ConstructorDeclContext ctx) {

    }

    @Override public void exitConstructorDecl(MxParser.ConstructorDeclContext ctx) { }

    @Override public void enterFuncDecl(MxParser.FuncDeclContext ctx) {
        String funcName = ctx.Identifier().getSymbol().getText();
        SST.regSymbol(funcName,symType.s_func,0);
    }

    @Override public void exitFuncDecl(MxParser.FuncDeclContext ctx) {

    }

    @Override public void enterParamDecl(MxParser.ParamDeclContext ctx) {

    }

    @Override public void exitParamDecl(MxParser.ParamDeclContext ctx) { }

    @Override public void enterBlock(MxParser.BlockContext ctx) {
        SST.push_scope(Integer.toString(ctx.hashCode()));
    }

    @Override public void exitBlock(MxParser.BlockContext ctx) {
        SST.pop_scope();
    }

    @Override public void enterBlockStat(MxParser.BlockStatContext ctx) {

    }

    @Override public void exitBlockStat(MxParser.BlockStatContext ctx) { }


    @Override public void enterVarDeclStat(MxParser.VarDeclStatContext ctx) {

    }

    @Override public void exitVarDeclStat(MxParser.VarDeclStatContext ctx) { }

    @Override public void enterExprList(MxParser.ExprListContext ctx) {
    }

    @Override public void exitExprList(MxParser.ExprListContext ctx) { }


    @Override public void enterPrimary(MxParser.PrimaryContext ctx) {

    }

    @Override public void exitPrimary(MxParser.PrimaryContext ctx) { }

    @Override public void enterCreator(MxParser.CreatorContext ctx) {

    }

    @Override public void exitCreator(MxParser.CreatorContext ctx) { }

    @Override public void enterArrayInit(MxParser.ArrayInitContext ctx) {

    }

    @Override public void exitArrayInit(MxParser.ArrayInitContext ctx) { }

    @Override public void enterClassInit(MxParser.ClassInitContext ctx) {

    }

    @Override public void exitClassInit(MxParser.ClassInitContext ctx) { }

//    @Override public void enterVarDecls(MxParser.VarDeclsContext ctx) {

//    }

//    @Override public void exitVarDecls(MxParser.VarDeclsContext ctx) { }

    @Override public void enterVarDecl(MxParser.VarDeclContext ctx) {
        String varName = ctx.Identifier().getSymbol().getText();
//        out.println("var decl:"+varName);
        SST.regSymbol(varName,symType.s_var,curVarType);
    }

    @Override public void exitVarDecl(MxParser.VarDeclContext ctx) { }

    @Override public void enterType(MxParser.TypeContext ctx) {
        if (ctx.classType() == null) {
            switch (ctx.primType().getText()){
                case "int":
                    curVarType = -1;
                    break;
                case "bool":
                    curVarType = -2;
                    break;
                case "string":
                    curVarType = -3;
                    break;
                default:
                    err.println("error: unexpected type decl");
            }
        } else {
            int classTypeNum = SST.findSymbol(ctx.classType().getText());
            if (classTypeNum > 0) {
                curVarType = classTypeNum;
            } else {
                err.println("error: unknown class type");
            }
        }
//        String primtypeName = ctx.primType().getText();
//        String classtypeName = ctx.classType().getText();
//        out.println("type:"+primtypeName+" "+classtypeName);
    }

    @Override public void exitType(MxParser.TypeContext ctx) { }

    @Override public void enterClassType(MxParser.ClassTypeContext ctx) {

    }

    @Override public void exitClassType(MxParser.ClassTypeContext ctx) { }

    @Override public void enterPrimType(MxParser.PrimTypeContext ctx) {

    }

    @Override public void exitPrimType(MxParser.PrimTypeContext ctx) { }

    @Override public void enterLiteral(MxParser.LiteralContext ctx) {

    }

    @Override public void exitLiteral(MxParser.LiteralContext ctx) { }

    @Override public void enterBoolLiteral(MxParser.BoolLiteralContext ctx) {

    }

    @Override public void exitBoolLiteral(MxParser.BoolLiteralContext ctx) { }

    @Override public void enterIntLiteral(MxParser.IntLiteralContext ctx) {
        int a = Integer.parseInt(ctx.getText());
    }

    @Override public void exitIntLiteral(MxParser.IntLiteralContext ctx) {

    }

    @Override public void enterStrLiteral(MxParser.StrLiteralContext ctx) {
    }

    @Override public void exitStrLiteral(MxParser.StrLiteralContext ctx) {

    }


    @Override public void enterEveryRule(ParserRuleContext ctx) {
        out.println("info:"+t.getRuleNames()[ctx.getRuleIndex()]+' '+ctx.getText());
//        out.println("info:"+ctx.toInfoString(t));
    }

    @Override public void exitEveryRule(ParserRuleContext ctx) { }

    @Override public void visitTerminal(TerminalNode node) { }

    @Override public void visitErrorNode(ErrorNode node) {
        err.println("error"+node.toString());
    }
}
