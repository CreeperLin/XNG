package xng.antlr;

import xng.frontend.AST.*;

import java.util.Vector;
import java.util.stream.Collectors;

import static java.lang.System.*;

public class XNGVisitor extends MxBaseVisitor<XASTBaseNode>{
    private MxParser t;

    public XNGVisitor(MxParser _t){
        t = _t;
    }

    @Override public XASTCUNode visitCompilationUnit(MxParser.CompilationUnitContext ctx) {
        if (ctx==null) return null;
        out.println("compilation begin");
        XASTCUNode prog = new XASTCUNode();
        ctx.varDeclStat().stream().map(this::visitVarDeclStat).forEach(prog::add);
        ctx.classDecl().stream().map(this::visitClassDecl).forEach(prog::add);
        ctx.funcDecl().stream().map(this::visitFuncDecl).forEach(prog::add);
        out.println("compilation terminated");
        return prog;
    }

    @Override public XASTStmtNode visitClassDecl(MxParser.ClassDeclContext ctx) {
        if (ctx==null) return null;
        String curClassName = ctx.Identifier().toString();
        out.println("class name:" + curClassName);
        return new XASTClassDeclNode(curClassName,
                ctx.classBodyDecl().stream().map(this::visitClassBodyDecl).collect(Collectors.toCollection(Vector::new)));
    }

    @Override public XASTStmtNode visitClassBodyDecl(MxParser.ClassBodyDeclContext ctx) {
        if (ctx==null) return null;
        return (XASTStmtNode) visitChildren(ctx);
    }

    @Override public XASTFuncDeclNode visitConstructorDecl(MxParser.ConstructorDeclContext ctx) {
        if (ctx==null) return null;
        String funcName = ctx.Identifier().getSymbol().getText();
        return new XASTFuncDeclNode(funcName,
                new XASTTypeNode(XASTNodeID.t_void,0,null),
                (ctx.paramDecl()==null ? null : visitParamDecl(ctx.paramDecl())),
                visitBlock(ctx.block()),false);
    }

    @Override public XASTFuncDeclNode visitFuncDecl(MxParser.FuncDeclContext ctx) {
        if (ctx==null) return null;
        String funcName = ctx.Identifier().getSymbol().getText();
        return new XASTFuncDeclNode(funcName,
                visitType(ctx.type()),
                (ctx.paramDecl()==null ? null : visitParamDecl(ctx.paramDecl())),
                visitBlock(ctx.block()),true);
    }

    @Override public XASTStmtNode visitParamDecl(MxParser.ParamDeclContext ctx) {
        if (ctx==null) return null;
        return new XASTStmtNode(XASTNodeID.s_plist,
                ctx.varDecl().stream().map(this::visitVarDecl).collect(Collectors.toCollection(Vector::new)));
    }

    @Override public XASTStmtNode visitBlock(MxParser.BlockContext ctx) {
        if (ctx==null) return null;
        return new XASTStmtNode(XASTNodeID.s_block,
                ctx.blockStat().stream().map(this::visitBlockStat).collect(Collectors.toCollection(Vector::new)));
    }

    @Override public XASTStmtNode visitBlockStat(MxParser.BlockStatContext ctx) {
        if (ctx==null) return null;
        return (ctx.block()!=null?visitBlock(ctx.block()):
                (ctx.statement()!=null?visitStatement(ctx.statement()):
                        ctx.varDeclStat()!=null?visitVarDeclStat(ctx.varDeclStat()):null));
    }

    @Override public XASTStmtNode visitStatement(MxParser.StatementContext ctx) {
        if (ctx==null) return null;
        if (ctx.key == null){
            if (ctx.block() != null) {
                return visitBlock(ctx.block());
            }
            return visitExpression(ctx.expression());
        }
        String keyword = ctx.key.getText();
        XASTNodeID type;
        Vector<XASTStmtNode> v = new Vector<>();
        switch (keyword){
            case "if":
                type = XASTNodeID.s_if;
                v.add(visitExpression(ctx.expression()));
                ctx.statement().stream().map(this::visitStatement).forEach(v::add);
                break;
            case "for":
                type = XASTNodeID.s_for;
                v.add((ctx.init==null)?new XASTStmtNode():visitVarDecl(ctx.init));
                v.add((ctx.cond==null)?new XASTStmtNode():visitExpression(ctx.cond));
                v.add((ctx.step==null)?new XASTStmtNode():visitExprList(ctx.step));
                v.add((ctx.body==null)?new XASTStmtNode():visitStatement(ctx.body));
                break;
            case "while":
                type = XASTNodeID.s_while;
                v.add((ctx.cond==null)?new XASTStmtNode():visitExpression(ctx.cond));
                v.add((ctx.body==null)?new XASTStmtNode():visitStatement(ctx.body));
                break;
            case "break":
                type = XASTNodeID.s_break;
                break;
            case "return":
                type = XASTNodeID.s_ret;
                v.add((ctx.ret==null)?new XASTStmtNode():visitExpression(ctx.ret));
                break;
            case "continue":
                type = XASTNodeID.s_cont;
                break;
            default:
                type = XASTNodeID.s_none;
                err.println("error:unknown keyword"+keyword);
        }
        return new XASTStmtNode(type,v);
    }

    @Override public XASTExprNode visitExpression(MxParser.ExpressionContext ctx) {
        if (ctx==null) return null;
        if (ctx.primary() != null){
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
                return new XASTExprNode(type,_v);
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
                return new XASTExprNode(type,v);
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
        return new XASTExprNode(type,
                ctx.expression().stream().map(this::visitExpression).collect(Collectors.toCollection(Vector::new)));
    }

    @Override public XASTVarDeclNode visitVarDeclStat(MxParser.VarDeclStatContext ctx) {
        if (ctx==null) return null;
        return visitVarDecl(ctx.varDecl());
    }

    @Override public XASTExprNode visitExprList(MxParser.ExprListContext ctx) {
        if (ctx==null) return null;
        return new XASTExprNode(XASTNodeID.e_list,
                ctx.expression().stream().map(this::visitExpression).collect(Collectors.toCollection(Vector::new)));
    }

    @Override public XASTPrimNode visitPrimary(MxParser.PrimaryContext ctx) {
        if (ctx.isthis != null) return new XASTPrimNode(XASTNodeID.p_this, 0, null);
        if (ctx.expression() != null) {
            Vector<XASTExprNode> _v = new Vector<>();
            _v.add(visitExpression(ctx.expression()));
            return new XASTPrimNode(XASTNodeID.p_expr, _v);
        }
        if (ctx.Identifier() != null) return new XASTPrimNode(XASTNodeID.p_id, 0, ctx.Identifier().getSymbol().getText());
        if (ctx.literal() != null) return visitLiteral(ctx.literal());
        err.println("error:prim:unknown primary type");
        return null;
    }

    @Override public XASTBaseNode visitCreator(MxParser.CreatorContext ctx) {
//        if (ctx.classInit()!=null){
//            return new XASTCreatorNode(visitType(ctx.type()),visitClassInit(ctx.classInit()));
//        }
        return new XASTCreatorNode(visitType(ctx.type()),(XASTExprNode)visitChildren(ctx));
    }

    @Override public XASTBaseNode visitArrayInit(MxParser.ArrayInitContext ctx) {
        return new XASTExprNode(XASTNodeID.e_list,
                ctx.expression().stream().map(this::visitExpression).collect(Collectors.toCollection(Vector::new)));
    }

    @Override public XASTExprNode visitClassInit(MxParser.ClassInitContext ctx) {
        return visitExprList(ctx.exprList());
    }

    @Override public XASTVarDeclNode visitVarDecl(MxParser.VarDeclContext ctx) {
        if (ctx==null) return null;
        String varName = ctx.Identifier().getSymbol().getText();
        out.println("varDecl: "+varName);
        return new XASTVarDeclNode(varName, visitType(ctx.type()), visitExpression(ctx.expression()));
    }

    @Override public XASTTypeNode visitType(MxParser.TypeContext ctx) {
        if (ctx==null) return new XASTTypeNode(XASTNodeID.t_void,0,null);
        XASTNodeID type = null;
        String className = null;
        if (ctx.classType()!=null) {
            out.println("class type");
            type = XASTNodeID.t_class;
            className = ctx.classType().Identifier(0).getSymbol().getText();
        } else if (ctx.primType()!=null) {
            out.println("prim type");
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
                    err.println("error:unknown primitive type"+ctx.primType().getText());
            }
            className = null;

        }
        return new XASTTypeNode(type,(ctx.LB()==null?0:ctx.LB().size()),className);
    }

    @Override public XASTBaseNode visitClassType(MxParser.ClassTypeContext ctx) { return visitChildren(ctx); }

    @Override public XASTBaseNode visitPrimType(MxParser.PrimTypeContext ctx) { return visitChildren(ctx); }

    @Override public XASTPrimNode visitLiteral(MxParser.LiteralContext ctx) {
        if (ctx.boolLiteral() != null) return visitBoolLiteral(ctx.boolLiteral());
        if (ctx.strLiteral() != null) return visitStrLiteral(ctx.strLiteral());
        if (ctx.intLiteral() != null) return visitIntLiteral(ctx.intLiteral());
        if (ctx.nullLiteral() != null) return visitNullLiteral(ctx.nullLiteral());
        return null;
    }

    @Override public XASTPrimNode visitBoolLiteral(MxParser.BoolLiteralContext ctx) {
        String t = ctx.getText();
        out.println("boolLiteral:"+t);
        return new XASTPrimNode(XASTNodeID.p_lit_bool, (t.equals("false") ? 0 : (t.equals("true") ?1:-1)), null);
    }

    @Override public XASTPrimNode visitIntLiteral(MxParser.IntLiteralContext ctx) {
        out.println("intLiteral:"+Integer.parseInt(ctx.getText()));
        return new XASTPrimNode(XASTNodeID.p_lit_int, Integer.parseInt(ctx.getText()), null);
    }

    @Override public XASTPrimNode visitStrLiteral(MxParser.StrLiteralContext ctx) {
        out.println("strLiteral:"+ctx.getText());
        return new XASTPrimNode(XASTNodeID.p_lit_str, 0, ctx.getText());
    }

    @Override public XASTPrimNode visitNullLiteral(MxParser.NullLiteralContext ctx) {
        return new XASTPrimNode(XASTNodeID.p_lit_null,0,null);
    }

}
