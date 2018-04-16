// Generated from /root/GitRepo/XNG/grammar/Mx.g4 by ANTLR 4.7
package xng.antlr;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link MxParser}.
 */
public interface MxListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link MxParser#compilationUnit}.
	 * @param ctx the parse tree
	 */
	void enterCompilationUnit(MxParser.CompilationUnitContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#compilationUnit}.
	 * @param ctx the parse tree
	 */
	void exitCompilationUnit(MxParser.CompilationUnitContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#classDecl}.
	 * @param ctx the parse tree
	 */
	void enterClassDecl(MxParser.ClassDeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#classDecl}.
	 * @param ctx the parse tree
	 */
	void exitClassDecl(MxParser.ClassDeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#classBodyDecl}.
	 * @param ctx the parse tree
	 */
	void enterClassBodyDecl(MxParser.ClassBodyDeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#classBodyDecl}.
	 * @param ctx the parse tree
	 */
	void exitClassBodyDecl(MxParser.ClassBodyDeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#constructorDecl}.
	 * @param ctx the parse tree
	 */
	void enterConstructorDecl(MxParser.ConstructorDeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#constructorDecl}.
	 * @param ctx the parse tree
	 */
	void exitConstructorDecl(MxParser.ConstructorDeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#funcDecl}.
	 * @param ctx the parse tree
	 */
	void enterFuncDecl(MxParser.FuncDeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#funcDecl}.
	 * @param ctx the parse tree
	 */
	void exitFuncDecl(MxParser.FuncDeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#paramDecl}.
	 * @param ctx the parse tree
	 */
	void enterParamDecl(MxParser.ParamDeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#paramDecl}.
	 * @param ctx the parse tree
	 */
	void exitParamDecl(MxParser.ParamDeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#block}.
	 * @param ctx the parse tree
	 */
	void enterBlock(MxParser.BlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#block}.
	 * @param ctx the parse tree
	 */
	void exitBlock(MxParser.BlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#blockStat}.
	 * @param ctx the parse tree
	 */
	void enterBlockStat(MxParser.BlockStatContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#blockStat}.
	 * @param ctx the parse tree
	 */
	void exitBlockStat(MxParser.BlockStatContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(MxParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(MxParser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#varDeclStat}.
	 * @param ctx the parse tree
	 */
	void enterVarDeclStat(MxParser.VarDeclStatContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#varDeclStat}.
	 * @param ctx the parse tree
	 */
	void exitVarDeclStat(MxParser.VarDeclStatContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#exprList}.
	 * @param ctx the parse tree
	 */
	void enterExprList(MxParser.ExprListContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#exprList}.
	 * @param ctx the parse tree
	 */
	void exitExprList(MxParser.ExprListContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(MxParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(MxParser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#primary}.
	 * @param ctx the parse tree
	 */
	void enterPrimary(MxParser.PrimaryContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#primary}.
	 * @param ctx the parse tree
	 */
	void exitPrimary(MxParser.PrimaryContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#creator}.
	 * @param ctx the parse tree
	 */
	void enterCreator(MxParser.CreatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#creator}.
	 * @param ctx the parse tree
	 */
	void exitCreator(MxParser.CreatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#arrayInit}.
	 * @param ctx the parse tree
	 */
	void enterArrayInit(MxParser.ArrayInitContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#arrayInit}.
	 * @param ctx the parse tree
	 */
	void exitArrayInit(MxParser.ArrayInitContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#classInit}.
	 * @param ctx the parse tree
	 */
	void enterClassInit(MxParser.ClassInitContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#classInit}.
	 * @param ctx the parse tree
	 */
	void exitClassInit(MxParser.ClassInitContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#varDecl}.
	 * @param ctx the parse tree
	 */
	void enterVarDecl(MxParser.VarDeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#varDecl}.
	 * @param ctx the parse tree
	 */
	void exitVarDecl(MxParser.VarDeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#type}.
	 * @param ctx the parse tree
	 */
	void enterType(MxParser.TypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#type}.
	 * @param ctx the parse tree
	 */
	void exitType(MxParser.TypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#classType}.
	 * @param ctx the parse tree
	 */
	void enterClassType(MxParser.ClassTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#classType}.
	 * @param ctx the parse tree
	 */
	void exitClassType(MxParser.ClassTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#primType}.
	 * @param ctx the parse tree
	 */
	void enterPrimType(MxParser.PrimTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#primType}.
	 * @param ctx the parse tree
	 */
	void exitPrimType(MxParser.PrimTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterLiteral(MxParser.LiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitLiteral(MxParser.LiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#nullLiteral}.
	 * @param ctx the parse tree
	 */
	void enterNullLiteral(MxParser.NullLiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#nullLiteral}.
	 * @param ctx the parse tree
	 */
	void exitNullLiteral(MxParser.NullLiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#boolLiteral}.
	 * @param ctx the parse tree
	 */
	void enterBoolLiteral(MxParser.BoolLiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#boolLiteral}.
	 * @param ctx the parse tree
	 */
	void exitBoolLiteral(MxParser.BoolLiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#intLiteral}.
	 * @param ctx the parse tree
	 */
	void enterIntLiteral(MxParser.IntLiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#intLiteral}.
	 * @param ctx the parse tree
	 */
	void exitIntLiteral(MxParser.IntLiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#strLiteral}.
	 * @param ctx the parse tree
	 */
	void enterStrLiteral(MxParser.StrLiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#strLiteral}.
	 * @param ctx the parse tree
	 */
	void exitStrLiteral(MxParser.StrLiteralContext ctx);
}