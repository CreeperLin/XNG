package xng.antlr;

import xng.frontend.AST.*;
import xng.frontend.Symbol.SrcPos;

import java.util.Vector;
import java.util.stream.Collectors;

import static java.lang.System.err;
import static java.lang.System.out;

public class XNGVisitor extends MxBaseVisitor<XASTBaseNode>{
    private MxParser t;

    public XNGVisitor(MxParser _t){
        t = _t;
    }

    @Override public XASTCUNode visitCompilationUnit(MxParser.CompilationUnitContext ctx) {
        if (ctx==null) return null;
        out.println("begin AST Building");
        return new XASTCUNode(new SrcPos(ctx),
                ctx.progSection().stream().map(this::visitProgSection).collect(Collectors.toCollection(Vector::new)));
    }

    @Override public XASTStmtNode visitProgSection(MxParser.ProgSectionContext ctx) {
        if (ctx.classDecl() != null) return visitClassDecl(ctx.classDecl());
        if (ctx.varDeclStat() != null) return visitVarDeclStat(ctx.varDeclStat());
        if (ctx.funcDecl()!= null) return visitFuncDecl(ctx.funcDecl());
        return null;
    }

    @Override public XASTStmtNode visitClassDecl(MxParser.ClassDeclContext ctx) {
        if (ctx==null) return null;
        String curClassName = ctx.Identifier().toString();
        return new XASTClassDeclNode(new SrcPos(ctx.Identifier().getSymbol()),curClassName,
                ctx.classBodyDecl().stream().map(this::visitClassBodyDecl).collect(Collectors.toCollection(Vector::new)));
    }

    @Override public XASTStmtNode visitClassBodyDecl(MxParser.ClassBodyDeclContext ctx) {
        if (ctx==null) return null;
        return (XASTStmtNode) visitChildren(ctx);
    }

    @Override public XASTFuncDeclNode visitConstructorDecl(MxParser.ConstructorDeclContext ctx) {
        if (ctx==null) return null;
        String funcName = ctx.Identifier().getSymbol().getText();
        return new XASTFuncDeclNode(new SrcPos(ctx),funcName,
                new XASTTypeNode(null,XASTNodeID.t_void,0,null),
                (ctx.paramDecl()==null ? null : visitParamDecl(ctx.paramDecl())),
                visitBlock(ctx.block()),true);
    }

    @Override public XASTFuncDeclNode visitFuncDecl(MxParser.FuncDeclContext ctx) {
        if (ctx==null) return null;
        String funcName = ctx.Identifier().getSymbol().getText();
        return new XASTFuncDeclNode(new SrcPos(ctx),funcName,
                visitType(ctx.type()),
                (ctx.paramDecl()==null ? null : visitParamDecl(ctx.paramDecl())),
                visitBlock(ctx.block()),false);
    }

    @Override public XASTStmtNode visitParamDecl(MxParser.ParamDeclContext ctx) {
        if (ctx==null) return null;
        return new XASTStmtNode(new SrcPos(ctx),XASTNodeID.s_plist,
                ctx.varDecl().stream().map(this::visitVarDecl).collect(Collectors.toCollection(Vector::new)));
    }

    @Override public XASTStmtNode visitBlock(MxParser.BlockContext ctx) {
        if (ctx==null) return null;
        return new XASTStmtNode(new SrcPos(ctx),XASTNodeID.s_block,
                ctx.blockStat().stream().map(this::visitBlockStat).collect(Collectors.toCollection(Vector::new)));
    }

    @Override public XASTStmtNode visitBlockStat(MxParser.BlockStatContext ctx) {
        if (ctx==null) return null;
        return (ctx.block()!=null?visitBlock(ctx.block()):
                (ctx.statement()!=null?visitStatement(ctx.statement()):
                        ctx.varDeclStat()!=null?visitVarDeclStat(ctx.varDeclStat()):null));
    }

    @Override public XASTStmtNode visitStatement(MxParser.StatementContext ctx) {
        if (ctx==null) return new XASTStmtNode();
        if (ctx.key == null){
            if (ctx.block() != null) {
                return visitBlock(ctx.block());
            }
            if (ctx.colon != null){
                return new XASTStmtNode();
            }
            return visitExpression(ctx.expr);
        }
        String keyword = ctx.key.getText();
        XASTNodeID type;
        Vector<XASTStmtNode> v = new Vector<>();
        switch (keyword){
            case "if":
                type = XASTNodeID.s_if;
                v.add(visitExpression(ctx.cond));
                ctx.statement().stream().map(this::visitStatement).forEach(v::add);
                break;
            case "for":
                type = XASTNodeID.s_for;
                v.add(visitExpression(ctx.init));
                v.add(visitExpression(ctx.cond));
                v.add(visitExprList(ctx.step));
                v.add(visitStatement(ctx.body));
                break;
            case "while":
                type = XASTNodeID.s_while;
                v.add(visitExpression(ctx.cond));
                v.add(visitStatement(ctx.body));
                break;
            case "break":
                type = XASTNodeID.s_break;
                break;
            case "return":
                type = XASTNodeID.s_ret;
                v.add(visitExpression(ctx.ret));
                break;
            case "continue":
                type = XASTNodeID.s_cont;
                break;
            default:
                type = XASTNodeID.s_none;
                err.println("error:unknown keyword"+keyword);
        }
        return new XASTStmtNode(new SrcPos(ctx),type,v);
    }

    @Override public XASTExprNode visitExpression(MxParser.ExpressionContext ctx) {
        if (ctx==null) return new XASTExprNode();
        if (ctx.primary() != null){
            if (ctx.primary().expression()!=null) return visitExpression(ctx.primary().expression());
            return visitPrimary(ctx.primary());
        }
        XASTNodeID type;
        switch (ctx.op.getText()){
            case "+":
                type = ctx.getChildCount() == 3 ? XASTNodeID.e_add : XASTNodeID.e_pos;
                break;
            case "-":
                type = ctx.getChildCount() == 3 ? XASTNodeID.e_sub : XASTNodeID.e_neg;
                break;
            case "*":
                type = XASTNodeID.e_mult;
                break;
            case "/":
                type = XASTNodeID.e_div;
                break;
            case "%":
                type = XASTNodeID.e_mod;
                break;
            case ".":
                type = XASTNodeID.e_mem;
                break;
            case "[":
                type = XASTNodeID.e_idx;
                break;
            case "(":
                type = XASTNodeID.e_call;
                Vector<XASTExprNode> _v = new Vector<>();
                _v.add(visitExpression(ctx.expression(0)));
                if (ctx.exprList()!=null) _v.add(visitExprList(ctx.exprList()));
                return new XASTExprNode(new SrcPos(ctx),type,_v);
            case "++":
                if (ctx.getChild(1).equals(ctx.expression(0))){
                    type = XASTNodeID.e_inc_p;
                } else if (ctx.getChild(0).equals(ctx.expression(0))){
                    type = XASTNodeID.e_inc_s;
                } else {
                    type = XASTNodeID.e_none;
                    err.println("error:expr++:assertion failed");
                }
                break;
            case "--":
                if (ctx.getChild(1).equals(ctx.expression(0))){
                    type = XASTNodeID.e_dec_p;
                } else if (ctx.getChild(0).equals(ctx.expression(0))){
                    type = XASTNodeID.e_dec_s;
                } else {
                    type = XASTNodeID.e_none;
                    err.println("error:expr--:assertion failed");
                }
                break;
            case "new":
                type = XASTNodeID.e_new;
                Vector<XASTExprNode> v = new Vector<>();
                v.add((XASTExprNode)visitCreator(ctx.creator()));
                return new XASTExprNode(new SrcPos(ctx),type,v);
            case ">>":
                type = XASTNodeID.e_shr;
                break;
            case "<<":
                type = XASTNodeID.e_shl;
                break;
            case ">=":
                type = XASTNodeID.e_ge;
                break;
            case "<=":
                type = XASTNodeID.e_le;
                break;
            case ">":
                type = XASTNodeID.e_gt;
                break;
            case "<":
                type = XASTNodeID.e_lt;
                break;
            case "!=":
                type = XASTNodeID.e_ne;
                break;
            case "==":
                type = XASTNodeID.e_eq;
                break;
            case "!":
                type = XASTNodeID.e_not;
                break;
            case "~":
                type = XASTNodeID.e_bneg;
                break;
            case "^":
                type = XASTNodeID.e_bxor;
                break;
            case "&":
                type = XASTNodeID.e_band;
                break;
            case "|":
                type = XASTNodeID.e_bor;
                break;
            case "&&":
                type = XASTNodeID.e_land;
                break;
            case "||":
                type = XASTNodeID.e_lor;
                break;
            case "=":
                type = XASTNodeID.e_asgn;
                break;
            default:
                type = XASTNodeID.e_none;
                err.println("error:expr:unidentified op");
        }
        return new XASTExprNode(new SrcPos(ctx),type,
                ctx.expression().stream().map(this::visitExpression).collect(Collectors.toCollection(Vector::new)));
    }

    @Override public XASTVarDeclNode visitVarDeclStat(MxParser.VarDeclStatContext ctx) {
        if (ctx==null) return null;
        return visitVarDecl(ctx.varDecl());
    }

    @Override public XASTExprNode visitExprList(MxParser.ExprListContext ctx) {
        if (ctx==null) return new XASTExprNode();
        return new XASTExprNode(new SrcPos(ctx),XASTNodeID.e_list,
                ctx.expression().stream().map(this::visitExpression).collect(Collectors.toCollection(Vector::new)));
    }

    @Override public XASTPrimNode visitPrimary(MxParser.PrimaryContext ctx) {
        if (ctx.isthis != null) return new XASTPrimNode(new SrcPos(ctx),XASTNodeID.p_this, 0, null);
//        if (ctx.expression() != null) {
//            Vector<XASTExprNode> _v = new Vector<>();
//            _v.add(visitExpression(ctx.expression()));
//            return new XASTPrimNode(new SrcPos(ctx),XASTNodeID.p_expr, _v);
//        }
        if (ctx.Identifier() != null) return new XASTPrimNode(new SrcPos(ctx),XASTNodeID.p_id, 0, ctx.Identifier().getSymbol().getText());
        if (ctx.literal() != null) return visitLiteral(ctx.literal());
        err.println("error:prim:unknown primary retType");
        return null;
    }

    @Override public XASTBaseNode visitCreator(MxParser.CreatorContext ctx) {
        if (ctx.classInit()!=null) return new XASTCreatorNode(new SrcPos(ctx),visitNonArrayType(ctx.nonArrayType()),visitClassInit(ctx.classInit()));
        if (ctx.arrayInit()!=null){
            XASTTypeNode type = visitNonArrayType(ctx.nonArrayType());
            XASTExprNode expr = visitArrayInit(ctx.arrayInit());
            type.dim = ctx.arrayInit().arrayDimInit().size();
            return new XASTCreatorNode(new SrcPos(ctx),type,expr);
        }
        return new XASTCreatorNode(new SrcPos(ctx),visitNonArrayType(ctx.nonArrayType()),null);
    }

    @Override public XASTExprNode visitArrayInit(MxParser.ArrayInitContext ctx) {
        Vector<XASTExprNode> v = ctx.arrayDimInit().stream().map(this::visitArrayDimInit).collect(Collectors.toCollection(Vector::new));
//        for (int i = 0; i<ctx.LB().size()-ctx.expression().size(); ++i){
//            v.add(new XASTExprNode());
//        }
        return new XASTExprNode(new SrcPos(ctx),XASTNodeID.e_list,v);
    }

    @Override
    public XASTExprNode visitArrayDimInit(MxParser.ArrayDimInitContext ctx) {
        return visitExpression(ctx.expression());
    }

    @Override public XASTExprNode visitClassInit(MxParser.ClassInitContext ctx) {
        return visitExprList(ctx.exprList());
    }

    @Override public XASTVarDeclNode visitVarDecl(MxParser.VarDeclContext ctx) {
        if (ctx==null) return null;
        String varName = ctx.Identifier().getSymbol().getText();
//        out.println("varDecl: "+varName);
        return new XASTVarDeclNode(new SrcPos(ctx.Identifier().getSymbol()),varName, visitType(ctx.type()), visitExpression(ctx.expression()));
    }

    @Override public XASTTypeNode visitType(MxParser.TypeContext ctx) {
        if (ctx==null) return new XASTTypeNode(null,XASTNodeID.t_void,0,null);
        XASTTypeNode t = visitNonArrayType(ctx.nonArrayType());
        t.pos = new SrcPos(ctx);
        if (ctx.LB()!=null) t.dim = ctx.LB().size();
        return t;
    }
    @Override public XASTTypeNode visitNonArrayType(MxParser.NonArrayTypeContext ctx) {
        XASTNodeID type = null;
        String className = null;
        if (ctx.classType()!=null) {
//            out.println("class retType");
            type = XASTNodeID.t_class;
            className = ctx.classType().Identifier().getSymbol().getText();
        } else if (ctx.primType()!=null) {
//            out.println("prim retType");
            switch (ctx.primType().getText()) {
                case "bool":
                    type = XASTNodeID.t_bool;
                    break;
                case "int":
                    type = XASTNodeID.t_int;
                    break;
                case "string":
                    type = XASTNodeID.t_str;
                    break;
                default:
                    type = XASTNodeID.t_void;
                    err.println("error:unknown primitive retType"+ctx.primType().getText());
            }
            className = null;

        }
        return new XASTTypeNode(null,type,0,className);
    }

    @Override public XASTBaseNode visitClassType(MxParser.ClassTypeContext ctx) { return visitChildren(ctx); }

    @Override public XASTBaseNode visitPrimType(MxParser.PrimTypeContext ctx) { return visitChildren(ctx); }

    @Override public XASTPrimNode visitLiteral(MxParser.LiteralContext ctx) {
        if (ctx.BoolLiteral() != null){
            String t = ctx.BoolLiteral().getSymbol().getText();
            return new XASTPrimNode(new SrcPos(ctx.BoolLiteral()),XASTNodeID.p_lit_bool, (t.equals("false") ? 0 : (t.equals("true") ?1:-1)), null);
        }
        if (ctx.StrLiteral() != null){
            String t = ctx.StrLiteral().getSymbol().getText();
//            out.println("strLiteral:\""+t+"\"");
            return new XASTPrimNode(new SrcPos(ctx.StrLiteral()),XASTNodeID.p_lit_str, 0, t);
        }
        if (ctx.IntLiteral() != null){
            int t = Integer.parseInt(ctx.IntLiteral().getSymbol().getText());
//            out.println("intLiteral:"+t);
            return new XASTPrimNode(new SrcPos(ctx.IntLiteral()),XASTNodeID.p_lit_int, t, null);
        }
        if (ctx.NullLiteral() != null) {
            return new XASTPrimNode(new SrcPos(ctx.NullLiteral()), XASTNodeID.p_lit_null, 0, null);
        }
        return null;
    }

}
