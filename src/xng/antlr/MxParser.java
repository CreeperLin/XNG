// Generated from /root/GitRepo/XNG/grammar/Mx.g4 by ANTLR 4.7
package xng.antlr;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class MxParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, T__23=24, 
		T__24=25, T__25=26, T__26=27, T__27=28, T__28=29, T__29=30, T__30=31, 
		T__31=32, T__32=33, T__33=34, T__34=35, T__35=36, T__36=37, T__37=38, 
		T__38=39, T__39=40, NullLiteral=41, BoolLiteral=42, IntLiteral=43, StrLiteral=44, 
		DecimalLiteral=45, Identifier=46, LB=47, RB=48, LP=49, RP=50, LBB=51, 
		RBB=52, COMMENT=53, NEWLINE=54, WS=55;
	public static final int
		RULE_compilationUnit = 0, RULE_classDecl = 1, RULE_classBodyDecl = 2, 
		RULE_constructorDecl = 3, RULE_funcDecl = 4, RULE_paramDecl = 5, RULE_block = 6, 
		RULE_blockStat = 7, RULE_statement = 8, RULE_varDeclStat = 9, RULE_exprList = 10, 
		RULE_expression = 11, RULE_primary = 12, RULE_creator = 13, RULE_arrayInit = 14, 
		RULE_classInit = 15, RULE_varDecl = 16, RULE_type = 17, RULE_nonArrayType = 18, 
		RULE_classType = 19, RULE_primType = 20, RULE_literal = 21;
	public static final String[] ruleNames = {
		"compilationUnit", "classDecl", "classBodyDecl", "constructorDecl", "funcDecl", 
		"paramDecl", "block", "blockStat", "statement", "varDeclStat", "exprList", 
		"expression", "primary", "creator", "arrayInit", "classInit", "varDecl", 
		"type", "nonArrayType", "classType", "primType", "literal"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'class'", "'void'", "','", "'if'", "'else'", "'for'", "';'", "'while'", 
		"'return'", "'continue'", "'break'", "'.'", "'++'", "'--'", "'~'", "'!'", 
		"'+'", "'-'", "'new'", "'*'", "'/'", "'%'", "'<<'", "'>>'", "'<='", "'>='", 
		"'<'", "'>'", "'=='", "'!='", "'&'", "'^'", "'|'", "'&&'", "'||'", "'='", 
		"'this'", "'bool'", "'int'", "'string'", "'null'", null, null, null, null, 
		null, "'['", "']'", "'('", "')'", "'{'", "'}'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, "NullLiteral", "BoolLiteral", "IntLiteral", 
		"StrLiteral", "DecimalLiteral", "Identifier", "LB", "RB", "LP", "RP", 
		"LBB", "RBB", "COMMENT", "NEWLINE", "WS"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "Mx.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public MxParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class CompilationUnitContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(MxParser.EOF, 0); }
		public List<ClassDeclContext> classDecl() {
			return getRuleContexts(ClassDeclContext.class);
		}
		public ClassDeclContext classDecl(int i) {
			return getRuleContext(ClassDeclContext.class,i);
		}
		public List<FuncDeclContext> funcDecl() {
			return getRuleContexts(FuncDeclContext.class);
		}
		public FuncDeclContext funcDecl(int i) {
			return getRuleContext(FuncDeclContext.class,i);
		}
		public List<VarDeclStatContext> varDeclStat() {
			return getRuleContexts(VarDeclStatContext.class);
		}
		public VarDeclStatContext varDeclStat(int i) {
			return getRuleContext(VarDeclStatContext.class,i);
		}
		public CompilationUnitContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_compilationUnit; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitCompilationUnit(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CompilationUnitContext compilationUnit() throws RecognitionException {
		CompilationUnitContext _localctx = new CompilationUnitContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_compilationUnit);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(49);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__1) | (1L << T__37) | (1L << T__38) | (1L << T__39) | (1L << Identifier))) != 0)) {
				{
				setState(47);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
				case 1:
					{
					setState(44);
					classDecl();
					}
					break;
				case 2:
					{
					setState(45);
					funcDecl();
					}
					break;
				case 3:
					{
					setState(46);
					varDeclStat();
					}
					break;
				}
				}
				setState(51);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(52);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ClassDeclContext extends ParserRuleContext {
		public TerminalNode Identifier() { return getToken(MxParser.Identifier, 0); }
		public List<ClassBodyDeclContext> classBodyDecl() {
			return getRuleContexts(ClassBodyDeclContext.class);
		}
		public ClassBodyDeclContext classBodyDecl(int i) {
			return getRuleContext(ClassBodyDeclContext.class,i);
		}
		public ClassDeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classDecl; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitClassDecl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ClassDeclContext classDecl() throws RecognitionException {
		ClassDeclContext _localctx = new ClassDeclContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_classDecl);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(54);
			match(T__0);
			setState(55);
			match(Identifier);
			setState(56);
			match(LBB);
			setState(60);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__1) | (1L << T__37) | (1L << T__38) | (1L << T__39) | (1L << Identifier))) != 0)) {
				{
				{
				setState(57);
				classBodyDecl();
				}
				}
				setState(62);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(63);
			match(RBB);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ClassBodyDeclContext extends ParserRuleContext {
		public VarDeclStatContext varDeclStat() {
			return getRuleContext(VarDeclStatContext.class,0);
		}
		public FuncDeclContext funcDecl() {
			return getRuleContext(FuncDeclContext.class,0);
		}
		public ConstructorDeclContext constructorDecl() {
			return getRuleContext(ConstructorDeclContext.class,0);
		}
		public ClassBodyDeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classBodyDecl; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitClassBodyDecl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ClassBodyDeclContext classBodyDecl() throws RecognitionException {
		ClassBodyDeclContext _localctx = new ClassBodyDeclContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_classBodyDecl);
		try {
			setState(68);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(65);
				varDeclStat();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(66);
				funcDecl();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(67);
				constructorDecl();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ConstructorDeclContext extends ParserRuleContext {
		public TerminalNode Identifier() { return getToken(MxParser.Identifier, 0); }
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public ParamDeclContext paramDecl() {
			return getRuleContext(ParamDeclContext.class,0);
		}
		public ConstructorDeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constructorDecl; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitConstructorDecl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConstructorDeclContext constructorDecl() throws RecognitionException {
		ConstructorDeclContext _localctx = new ConstructorDeclContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_constructorDecl);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(70);
			match(Identifier);
			setState(71);
			match(LP);
			setState(73);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__37) | (1L << T__38) | (1L << T__39) | (1L << Identifier))) != 0)) {
				{
				setState(72);
				paramDecl();
				}
			}

			setState(75);
			match(RP);
			setState(76);
			block();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FuncDeclContext extends ParserRuleContext {
		public TerminalNode Identifier() { return getToken(MxParser.Identifier, 0); }
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public ParamDeclContext paramDecl() {
			return getRuleContext(ParamDeclContext.class,0);
		}
		public FuncDeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_funcDecl; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitFuncDecl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FuncDeclContext funcDecl() throws RecognitionException {
		FuncDeclContext _localctx = new FuncDeclContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_funcDecl);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(80);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__37:
			case T__38:
			case T__39:
			case Identifier:
				{
				setState(78);
				type();
				}
				break;
			case T__1:
				{
				setState(79);
				match(T__1);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(82);
			match(Identifier);
			setState(83);
			match(LP);
			setState(85);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__37) | (1L << T__38) | (1L << T__39) | (1L << Identifier))) != 0)) {
				{
				setState(84);
				paramDecl();
				}
			}

			setState(87);
			match(RP);
			setState(88);
			block();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ParamDeclContext extends ParserRuleContext {
		public List<VarDeclContext> varDecl() {
			return getRuleContexts(VarDeclContext.class);
		}
		public VarDeclContext varDecl(int i) {
			return getRuleContext(VarDeclContext.class,i);
		}
		public ParamDeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_paramDecl; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitParamDecl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParamDeclContext paramDecl() throws RecognitionException {
		ParamDeclContext _localctx = new ParamDeclContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_paramDecl);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(90);
			varDecl();
			setState(95);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__2) {
				{
				{
				setState(91);
				match(T__2);
				setState(92);
				varDecl();
				}
				}
				setState(97);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BlockContext extends ParserRuleContext {
		public List<BlockStatContext> blockStat() {
			return getRuleContexts(BlockStatContext.class);
		}
		public BlockStatContext blockStat(int i) {
			return getRuleContext(BlockStatContext.class,i);
		}
		public BlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_block; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitBlock(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BlockContext block() throws RecognitionException {
		BlockContext _localctx = new BlockContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_block);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(98);
			match(LBB);
			setState(102);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__3) | (1L << T__5) | (1L << T__6) | (1L << T__7) | (1L << T__8) | (1L << T__9) | (1L << T__10) | (1L << T__12) | (1L << T__13) | (1L << T__14) | (1L << T__15) | (1L << T__16) | (1L << T__17) | (1L << T__18) | (1L << T__36) | (1L << T__37) | (1L << T__38) | (1L << T__39) | (1L << NullLiteral) | (1L << BoolLiteral) | (1L << IntLiteral) | (1L << StrLiteral) | (1L << Identifier) | (1L << LP) | (1L << LBB))) != 0)) {
				{
				{
				setState(99);
				blockStat();
				}
				}
				setState(104);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(105);
			match(RBB);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BlockStatContext extends ParserRuleContext {
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public VarDeclStatContext varDeclStat() {
			return getRuleContext(VarDeclStatContext.class,0);
		}
		public BlockStatContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_blockStat; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitBlockStat(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BlockStatContext blockStat() throws RecognitionException {
		BlockStatContext _localctx = new BlockStatContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_blockStat);
		try {
			setState(110);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(107);
				block();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(108);
				statement();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(109);
				varDeclStat();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StatementContext extends ParserRuleContext {
		public Token key;
		public ExpressionContext cond;
		public StatementContext body;
		public Token iselse;
		public StatementContext elsebody;
		public ExpressionContext init;
		public ExprListContext step;
		public ExpressionContext ret;
		public ExpressionContext expr;
		public Token colon;
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public ExprListContext exprList() {
			return getRuleContext(ExprListContext.class,0);
		}
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_statement);
		int _la;
		try {
			setState(156);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LBB:
				enterOuterAlt(_localctx, 1);
				{
				setState(112);
				block();
				}
				break;
			case T__3:
				enterOuterAlt(_localctx, 2);
				{
				setState(113);
				((StatementContext)_localctx).key = match(T__3);
				setState(114);
				match(LP);
				setState(115);
				((StatementContext)_localctx).cond = expression(0);
				setState(116);
				match(RP);
				setState(117);
				((StatementContext)_localctx).body = statement();
				setState(120);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
				case 1:
					{
					setState(118);
					((StatementContext)_localctx).iselse = match(T__4);
					setState(119);
					((StatementContext)_localctx).elsebody = statement();
					}
					break;
				}
				}
				break;
			case T__5:
				enterOuterAlt(_localctx, 3);
				{
				setState(122);
				((StatementContext)_localctx).key = match(T__5);
				setState(123);
				match(LP);
				setState(125);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__12) | (1L << T__13) | (1L << T__14) | (1L << T__15) | (1L << T__16) | (1L << T__17) | (1L << T__18) | (1L << T__36) | (1L << NullLiteral) | (1L << BoolLiteral) | (1L << IntLiteral) | (1L << StrLiteral) | (1L << Identifier) | (1L << LP))) != 0)) {
					{
					setState(124);
					((StatementContext)_localctx).init = expression(0);
					}
				}

				setState(127);
				match(T__6);
				setState(129);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__12) | (1L << T__13) | (1L << T__14) | (1L << T__15) | (1L << T__16) | (1L << T__17) | (1L << T__18) | (1L << T__36) | (1L << NullLiteral) | (1L << BoolLiteral) | (1L << IntLiteral) | (1L << StrLiteral) | (1L << Identifier) | (1L << LP))) != 0)) {
					{
					setState(128);
					((StatementContext)_localctx).cond = expression(0);
					}
				}

				setState(131);
				match(T__6);
				setState(133);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__12) | (1L << T__13) | (1L << T__14) | (1L << T__15) | (1L << T__16) | (1L << T__17) | (1L << T__18) | (1L << T__36) | (1L << NullLiteral) | (1L << BoolLiteral) | (1L << IntLiteral) | (1L << StrLiteral) | (1L << Identifier) | (1L << LP))) != 0)) {
					{
					setState(132);
					((StatementContext)_localctx).step = exprList();
					}
				}

				setState(135);
				match(RP);
				setState(136);
				((StatementContext)_localctx).body = statement();
				}
				break;
			case T__7:
				enterOuterAlt(_localctx, 4);
				{
				setState(137);
				((StatementContext)_localctx).key = match(T__7);
				setState(138);
				match(LP);
				setState(139);
				((StatementContext)_localctx).cond = expression(0);
				setState(140);
				match(RP);
				setState(141);
				((StatementContext)_localctx).body = statement();
				}
				break;
			case T__8:
				enterOuterAlt(_localctx, 5);
				{
				setState(143);
				((StatementContext)_localctx).key = match(T__8);
				setState(145);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__12) | (1L << T__13) | (1L << T__14) | (1L << T__15) | (1L << T__16) | (1L << T__17) | (1L << T__18) | (1L << T__36) | (1L << NullLiteral) | (1L << BoolLiteral) | (1L << IntLiteral) | (1L << StrLiteral) | (1L << Identifier) | (1L << LP))) != 0)) {
					{
					setState(144);
					((StatementContext)_localctx).ret = expression(0);
					}
				}

				setState(147);
				match(T__6);
				}
				break;
			case T__9:
				enterOuterAlt(_localctx, 6);
				{
				setState(148);
				((StatementContext)_localctx).key = match(T__9);
				setState(149);
				match(T__6);
				}
				break;
			case T__10:
				enterOuterAlt(_localctx, 7);
				{
				setState(150);
				((StatementContext)_localctx).key = match(T__10);
				setState(151);
				match(T__6);
				}
				break;
			case T__12:
			case T__13:
			case T__14:
			case T__15:
			case T__16:
			case T__17:
			case T__18:
			case T__36:
			case NullLiteral:
			case BoolLiteral:
			case IntLiteral:
			case StrLiteral:
			case Identifier:
			case LP:
				enterOuterAlt(_localctx, 8);
				{
				setState(152);
				((StatementContext)_localctx).expr = expression(0);
				setState(153);
				match(T__6);
				}
				break;
			case T__6:
				enterOuterAlt(_localctx, 9);
				{
				setState(155);
				((StatementContext)_localctx).colon = match(T__6);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VarDeclStatContext extends ParserRuleContext {
		public VarDeclContext varDecl() {
			return getRuleContext(VarDeclContext.class,0);
		}
		public VarDeclStatContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_varDeclStat; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitVarDeclStat(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VarDeclStatContext varDeclStat() throws RecognitionException {
		VarDeclStatContext _localctx = new VarDeclStatContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_varDeclStat);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(158);
			varDecl();
			setState(159);
			match(T__6);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExprListContext extends ParserRuleContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public ExprListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exprList; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitExprList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExprListContext exprList() throws RecognitionException {
		ExprListContext _localctx = new ExprListContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_exprList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(161);
			expression(0);
			setState(166);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__2) {
				{
				{
				setState(162);
				match(T__2);
				setState(163);
				expression(0);
				}
				}
				setState(168);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressionContext extends ParserRuleContext {
		public Token op;
		public PrimaryContext primary() {
			return getRuleContext(PrimaryContext.class,0);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public CreatorContext creator() {
			return getRuleContext(CreatorContext.class,0);
		}
		public ExprListContext exprList() {
			return getRuleContext(ExprListContext.class,0);
		}
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		return expression(0);
	}

	private ExpressionContext expression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExpressionContext _localctx = new ExpressionContext(_ctx, _parentState);
		ExpressionContext _prevctx = _localctx;
		int _startState = 22;
		enterRecursionRule(_localctx, 22, RULE_expression, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(179);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__36:
			case NullLiteral:
			case BoolLiteral:
			case IntLiteral:
			case StrLiteral:
			case Identifier:
			case LP:
				{
				setState(170);
				primary();
				}
				break;
			case T__14:
			case T__15:
				{
				setState(171);
				((ExpressionContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==T__14 || _la==T__15) ) {
					((ExpressionContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(172);
				expression(15);
				}
				break;
			case T__12:
			case T__13:
				{
				setState(173);
				((ExpressionContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==T__12 || _la==T__13) ) {
					((ExpressionContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(174);
				expression(14);
				}
				break;
			case T__16:
			case T__17:
				{
				setState(175);
				((ExpressionContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==T__16 || _la==T__17) ) {
					((ExpressionContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(176);
				expression(13);
				}
				break;
			case T__18:
				{
				setState(177);
				((ExpressionContext)_localctx).op = match(T__18);
				setState(178);
				creator();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(232);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,20,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(230);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,19,_ctx) ) {
					case 1:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(181);
						if (!(precpred(_ctx, 19))) throw new FailedPredicateException(this, "precpred(_ctx, 19)");
						setState(182);
						((ExpressionContext)_localctx).op = match(T__11);
						setState(183);
						expression(20);
						}
						break;
					case 2:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(184);
						if (!(precpred(_ctx, 11))) throw new FailedPredicateException(this, "precpred(_ctx, 11)");
						setState(185);
						((ExpressionContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__19) | (1L << T__20) | (1L << T__21))) != 0)) ) {
							((ExpressionContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(186);
						expression(12);
						}
						break;
					case 3:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(187);
						if (!(precpred(_ctx, 10))) throw new FailedPredicateException(this, "precpred(_ctx, 10)");
						setState(188);
						((ExpressionContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==T__16 || _la==T__17) ) {
							((ExpressionContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(189);
						expression(11);
						}
						break;
					case 4:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(190);
						if (!(precpred(_ctx, 9))) throw new FailedPredicateException(this, "precpred(_ctx, 9)");
						setState(191);
						((ExpressionContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==T__22 || _la==T__23) ) {
							((ExpressionContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(192);
						expression(10);
						}
						break;
					case 5:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(193);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						setState(194);
						((ExpressionContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__24) | (1L << T__25) | (1L << T__26) | (1L << T__27))) != 0)) ) {
							((ExpressionContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(195);
						expression(9);
						}
						break;
					case 6:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(196);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(197);
						((ExpressionContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==T__28 || _la==T__29) ) {
							((ExpressionContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(198);
						expression(8);
						}
						break;
					case 7:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(199);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(200);
						((ExpressionContext)_localctx).op = match(T__30);
						setState(201);
						expression(7);
						}
						break;
					case 8:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(202);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(203);
						((ExpressionContext)_localctx).op = match(T__31);
						setState(204);
						expression(6);
						}
						break;
					case 9:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(205);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(206);
						((ExpressionContext)_localctx).op = match(T__32);
						setState(207);
						expression(5);
						}
						break;
					case 10:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(208);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(209);
						((ExpressionContext)_localctx).op = match(T__33);
						setState(210);
						expression(4);
						}
						break;
					case 11:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(211);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(212);
						((ExpressionContext)_localctx).op = match(T__34);
						setState(213);
						expression(3);
						}
						break;
					case 12:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(214);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(215);
						((ExpressionContext)_localctx).op = match(T__35);
						setState(216);
						expression(1);
						}
						break;
					case 13:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(217);
						if (!(precpred(_ctx, 18))) throw new FailedPredicateException(this, "precpred(_ctx, 18)");
						setState(218);
						((ExpressionContext)_localctx).op = match(LB);
						setState(219);
						expression(0);
						setState(220);
						match(RB);
						}
						break;
					case 14:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(222);
						if (!(precpred(_ctx, 17))) throw new FailedPredicateException(this, "precpred(_ctx, 17)");
						setState(223);
						((ExpressionContext)_localctx).op = match(LP);
						setState(225);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__12) | (1L << T__13) | (1L << T__14) | (1L << T__15) | (1L << T__16) | (1L << T__17) | (1L << T__18) | (1L << T__36) | (1L << NullLiteral) | (1L << BoolLiteral) | (1L << IntLiteral) | (1L << StrLiteral) | (1L << Identifier) | (1L << LP))) != 0)) {
							{
							setState(224);
							exprList();
							}
						}

						setState(227);
						match(RP);
						}
						break;
					case 15:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(228);
						if (!(precpred(_ctx, 16))) throw new FailedPredicateException(this, "precpred(_ctx, 16)");
						setState(229);
						((ExpressionContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==T__12 || _la==T__13) ) {
							((ExpressionContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						}
						break;
					}
					} 
				}
				setState(234);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,20,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class PrimaryContext extends ParserRuleContext {
		public Token isthis;
		public TerminalNode Identifier() { return getToken(MxParser.Identifier, 0); }
		public LiteralContext literal() {
			return getRuleContext(LiteralContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public PrimaryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_primary; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitPrimary(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PrimaryContext primary() throws RecognitionException {
		PrimaryContext _localctx = new PrimaryContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_primary);
		try {
			setState(242);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Identifier:
				enterOuterAlt(_localctx, 1);
				{
				setState(235);
				match(Identifier);
				}
				break;
			case NullLiteral:
			case BoolLiteral:
			case IntLiteral:
			case StrLiteral:
				enterOuterAlt(_localctx, 2);
				{
				setState(236);
				literal();
				}
				break;
			case T__36:
				enterOuterAlt(_localctx, 3);
				{
				setState(237);
				((PrimaryContext)_localctx).isthis = match(T__36);
				}
				break;
			case LP:
				enterOuterAlt(_localctx, 4);
				{
				setState(238);
				match(LP);
				setState(239);
				expression(0);
				setState(240);
				match(RP);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CreatorContext extends ParserRuleContext {
		public NonArrayTypeContext nonArrayType() {
			return getRuleContext(NonArrayTypeContext.class,0);
		}
		public ArrayInitContext arrayInit() {
			return getRuleContext(ArrayInitContext.class,0);
		}
		public ClassInitContext classInit() {
			return getRuleContext(ClassInitContext.class,0);
		}
		public CreatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_creator; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitCreator(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CreatorContext creator() throws RecognitionException {
		CreatorContext _localctx = new CreatorContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_creator);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(244);
			nonArrayType();
			setState(249);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,23,_ctx) ) {
			case 1:
				{
				setState(245);
				arrayInit();
				}
				break;
			case 2:
				{
				setState(247);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,22,_ctx) ) {
				case 1:
					{
					setState(246);
					classInit();
					}
					break;
				}
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ArrayInitContext extends ParserRuleContext {
		public List<TerminalNode> LB() { return getTokens(MxParser.LB); }
		public TerminalNode LB(int i) {
			return getToken(MxParser.LB, i);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<TerminalNode> RB() { return getTokens(MxParser.RB); }
		public TerminalNode RB(int i) {
			return getToken(MxParser.RB, i);
		}
		public ArrayInitContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arrayInit; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitArrayInit(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArrayInitContext arrayInit() throws RecognitionException {
		ArrayInitContext _localctx = new ArrayInitContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_arrayInit);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(251);
			match(LB);
			setState(252);
			expression(0);
			setState(253);
			match(RB);
			setState(260);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,24,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(254);
					match(LB);
					setState(255);
					expression(0);
					setState(256);
					match(RB);
					}
					} 
				}
				setState(262);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,24,_ctx);
			}
			setState(267);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,25,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(263);
					match(LB);
					setState(264);
					match(RB);
					}
					} 
				}
				setState(269);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,25,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ClassInitContext extends ParserRuleContext {
		public ExprListContext exprList() {
			return getRuleContext(ExprListContext.class,0);
		}
		public ClassInitContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classInit; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitClassInit(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ClassInitContext classInit() throws RecognitionException {
		ClassInitContext _localctx = new ClassInitContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_classInit);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(270);
			match(LP);
			setState(272);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__12) | (1L << T__13) | (1L << T__14) | (1L << T__15) | (1L << T__16) | (1L << T__17) | (1L << T__18) | (1L << T__36) | (1L << NullLiteral) | (1L << BoolLiteral) | (1L << IntLiteral) | (1L << StrLiteral) | (1L << Identifier) | (1L << LP))) != 0)) {
				{
				setState(271);
				exprList();
				}
			}

			setState(274);
			match(RP);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VarDeclContext extends ParserRuleContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode Identifier() { return getToken(MxParser.Identifier, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public VarDeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_varDecl; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitVarDecl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VarDeclContext varDecl() throws RecognitionException {
		VarDeclContext _localctx = new VarDeclContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_varDecl);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(276);
			type();
			setState(277);
			match(Identifier);
			setState(280);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__35) {
				{
				setState(278);
				match(T__35);
				setState(279);
				expression(0);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypeContext extends ParserRuleContext {
		public NonArrayTypeContext nonArrayType() {
			return getRuleContext(NonArrayTypeContext.class,0);
		}
		public List<TerminalNode> LB() { return getTokens(MxParser.LB); }
		public TerminalNode LB(int i) {
			return getToken(MxParser.LB, i);
		}
		public List<TerminalNode> RB() { return getTokens(MxParser.RB); }
		public TerminalNode RB(int i) {
			return getToken(MxParser.RB, i);
		}
		public TypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeContext type() throws RecognitionException {
		TypeContext _localctx = new TypeContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_type);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(282);
			nonArrayType();
			setState(287);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==LB) {
				{
				{
				setState(283);
				match(LB);
				setState(284);
				match(RB);
				}
				}
				setState(289);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NonArrayTypeContext extends ParserRuleContext {
		public PrimTypeContext primType() {
			return getRuleContext(PrimTypeContext.class,0);
		}
		public ClassTypeContext classType() {
			return getRuleContext(ClassTypeContext.class,0);
		}
		public NonArrayTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_nonArrayType; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitNonArrayType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NonArrayTypeContext nonArrayType() throws RecognitionException {
		NonArrayTypeContext _localctx = new NonArrayTypeContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_nonArrayType);
		try {
			setState(292);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__37:
			case T__38:
			case T__39:
				enterOuterAlt(_localctx, 1);
				{
				setState(290);
				primType();
				}
				break;
			case Identifier:
				enterOuterAlt(_localctx, 2);
				{
				setState(291);
				classType();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ClassTypeContext extends ParserRuleContext {
		public TerminalNode Identifier() { return getToken(MxParser.Identifier, 0); }
		public ClassTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classType; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitClassType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ClassTypeContext classType() throws RecognitionException {
		ClassTypeContext _localctx = new ClassTypeContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_classType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(294);
			match(Identifier);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PrimTypeContext extends ParserRuleContext {
		public PrimTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_primType; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitPrimType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PrimTypeContext primType() throws RecognitionException {
		PrimTypeContext _localctx = new PrimTypeContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_primType);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(296);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__37) | (1L << T__38) | (1L << T__39))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LiteralContext extends ParserRuleContext {
		public TerminalNode BoolLiteral() { return getToken(MxParser.BoolLiteral, 0); }
		public TerminalNode IntLiteral() { return getToken(MxParser.IntLiteral, 0); }
		public TerminalNode StrLiteral() { return getToken(MxParser.StrLiteral, 0); }
		public TerminalNode NullLiteral() { return getToken(MxParser.NullLiteral, 0); }
		public LiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_literal; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitLiteral(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LiteralContext literal() throws RecognitionException {
		LiteralContext _localctx = new LiteralContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_literal);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(298);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << NullLiteral) | (1L << BoolLiteral) | (1L << IntLiteral) | (1L << StrLiteral))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 11:
			return expression_sempred((ExpressionContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expression_sempred(ExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 19);
		case 1:
			return precpred(_ctx, 11);
		case 2:
			return precpred(_ctx, 10);
		case 3:
			return precpred(_ctx, 9);
		case 4:
			return precpred(_ctx, 8);
		case 5:
			return precpred(_ctx, 7);
		case 6:
			return precpred(_ctx, 6);
		case 7:
			return precpred(_ctx, 5);
		case 8:
			return precpred(_ctx, 4);
		case 9:
			return precpred(_ctx, 3);
		case 10:
			return precpred(_ctx, 2);
		case 11:
			return precpred(_ctx, 1);
		case 12:
			return precpred(_ctx, 18);
		case 13:
			return precpred(_ctx, 17);
		case 14:
			return precpred(_ctx, 16);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\39\u012f\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\3\2\3\2\3\2\7\2\62"+
		"\n\2\f\2\16\2\65\13\2\3\2\3\2\3\3\3\3\3\3\3\3\7\3=\n\3\f\3\16\3@\13\3"+
		"\3\3\3\3\3\4\3\4\3\4\5\4G\n\4\3\5\3\5\3\5\5\5L\n\5\3\5\3\5\3\5\3\6\3\6"+
		"\5\6S\n\6\3\6\3\6\3\6\5\6X\n\6\3\6\3\6\3\6\3\7\3\7\3\7\7\7`\n\7\f\7\16"+
		"\7c\13\7\3\b\3\b\7\bg\n\b\f\b\16\bj\13\b\3\b\3\b\3\t\3\t\3\t\5\tq\n\t"+
		"\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\5\n{\n\n\3\n\3\n\3\n\5\n\u0080\n\n\3"+
		"\n\3\n\5\n\u0084\n\n\3\n\3\n\5\n\u0088\n\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n"+
		"\3\n\3\n\3\n\5\n\u0094\n\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\5\n\u009f"+
		"\n\n\3\13\3\13\3\13\3\f\3\f\3\f\7\f\u00a7\n\f\f\f\16\f\u00aa\13\f\3\r"+
		"\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\5\r\u00b6\n\r\3\r\3\r\3\r\3\r\3\r"+
		"\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3"+
		"\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r"+
		"\3\r\3\r\3\r\3\r\5\r\u00e4\n\r\3\r\3\r\3\r\7\r\u00e9\n\r\f\r\16\r\u00ec"+
		"\13\r\3\16\3\16\3\16\3\16\3\16\3\16\3\16\5\16\u00f5\n\16\3\17\3\17\3\17"+
		"\5\17\u00fa\n\17\5\17\u00fc\n\17\3\20\3\20\3\20\3\20\3\20\3\20\3\20\7"+
		"\20\u0105\n\20\f\20\16\20\u0108\13\20\3\20\3\20\7\20\u010c\n\20\f\20\16"+
		"\20\u010f\13\20\3\21\3\21\5\21\u0113\n\21\3\21\3\21\3\22\3\22\3\22\3\22"+
		"\5\22\u011b\n\22\3\23\3\23\3\23\7\23\u0120\n\23\f\23\16\23\u0123\13\23"+
		"\3\24\3\24\5\24\u0127\n\24\3\25\3\25\3\26\3\26\3\27\3\27\3\27\2\3\30\30"+
		"\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,\2\13\3\2\21\22\3\2\17"+
		"\20\3\2\23\24\3\2\26\30\3\2\31\32\3\2\33\36\3\2\37 \3\2(*\3\2+.\2\u0152"+
		"\2\63\3\2\2\2\48\3\2\2\2\6F\3\2\2\2\bH\3\2\2\2\nR\3\2\2\2\f\\\3\2\2\2"+
		"\16d\3\2\2\2\20p\3\2\2\2\22\u009e\3\2\2\2\24\u00a0\3\2\2\2\26\u00a3\3"+
		"\2\2\2\30\u00b5\3\2\2\2\32\u00f4\3\2\2\2\34\u00f6\3\2\2\2\36\u00fd\3\2"+
		"\2\2 \u0110\3\2\2\2\"\u0116\3\2\2\2$\u011c\3\2\2\2&\u0126\3\2\2\2(\u0128"+
		"\3\2\2\2*\u012a\3\2\2\2,\u012c\3\2\2\2.\62\5\4\3\2/\62\5\n\6\2\60\62\5"+
		"\24\13\2\61.\3\2\2\2\61/\3\2\2\2\61\60\3\2\2\2\62\65\3\2\2\2\63\61\3\2"+
		"\2\2\63\64\3\2\2\2\64\66\3\2\2\2\65\63\3\2\2\2\66\67\7\2\2\3\67\3\3\2"+
		"\2\289\7\3\2\29:\7\60\2\2:>\7\65\2\2;=\5\6\4\2<;\3\2\2\2=@\3\2\2\2><\3"+
		"\2\2\2>?\3\2\2\2?A\3\2\2\2@>\3\2\2\2AB\7\66\2\2B\5\3\2\2\2CG\5\24\13\2"+
		"DG\5\n\6\2EG\5\b\5\2FC\3\2\2\2FD\3\2\2\2FE\3\2\2\2G\7\3\2\2\2HI\7\60\2"+
		"\2IK\7\63\2\2JL\5\f\7\2KJ\3\2\2\2KL\3\2\2\2LM\3\2\2\2MN\7\64\2\2NO\5\16"+
		"\b\2O\t\3\2\2\2PS\5$\23\2QS\7\4\2\2RP\3\2\2\2RQ\3\2\2\2ST\3\2\2\2TU\7"+
		"\60\2\2UW\7\63\2\2VX\5\f\7\2WV\3\2\2\2WX\3\2\2\2XY\3\2\2\2YZ\7\64\2\2"+
		"Z[\5\16\b\2[\13\3\2\2\2\\a\5\"\22\2]^\7\5\2\2^`\5\"\22\2_]\3\2\2\2`c\3"+
		"\2\2\2a_\3\2\2\2ab\3\2\2\2b\r\3\2\2\2ca\3\2\2\2dh\7\65\2\2eg\5\20\t\2"+
		"fe\3\2\2\2gj\3\2\2\2hf\3\2\2\2hi\3\2\2\2ik\3\2\2\2jh\3\2\2\2kl\7\66\2"+
		"\2l\17\3\2\2\2mq\5\16\b\2nq\5\22\n\2oq\5\24\13\2pm\3\2\2\2pn\3\2\2\2p"+
		"o\3\2\2\2q\21\3\2\2\2r\u009f\5\16\b\2st\7\6\2\2tu\7\63\2\2uv\5\30\r\2"+
		"vw\7\64\2\2wz\5\22\n\2xy\7\7\2\2y{\5\22\n\2zx\3\2\2\2z{\3\2\2\2{\u009f"+
		"\3\2\2\2|}\7\b\2\2}\177\7\63\2\2~\u0080\5\30\r\2\177~\3\2\2\2\177\u0080"+
		"\3\2\2\2\u0080\u0081\3\2\2\2\u0081\u0083\7\t\2\2\u0082\u0084\5\30\r\2"+
		"\u0083\u0082\3\2\2\2\u0083\u0084\3\2\2\2\u0084\u0085\3\2\2\2\u0085\u0087"+
		"\7\t\2\2\u0086\u0088\5\26\f\2\u0087\u0086\3\2\2\2\u0087\u0088\3\2\2\2"+
		"\u0088\u0089\3\2\2\2\u0089\u008a\7\64\2\2\u008a\u009f\5\22\n\2\u008b\u008c"+
		"\7\n\2\2\u008c\u008d\7\63\2\2\u008d\u008e\5\30\r\2\u008e\u008f\7\64\2"+
		"\2\u008f\u0090\5\22\n\2\u0090\u009f\3\2\2\2\u0091\u0093\7\13\2\2\u0092"+
		"\u0094\5\30\r\2\u0093\u0092\3\2\2\2\u0093\u0094\3\2\2\2\u0094\u0095\3"+
		"\2\2\2\u0095\u009f\7\t\2\2\u0096\u0097\7\f\2\2\u0097\u009f\7\t\2\2\u0098"+
		"\u0099\7\r\2\2\u0099\u009f\7\t\2\2\u009a\u009b\5\30\r\2\u009b\u009c\7"+
		"\t\2\2\u009c\u009f\3\2\2\2\u009d\u009f\7\t\2\2\u009er\3\2\2\2\u009es\3"+
		"\2\2\2\u009e|\3\2\2\2\u009e\u008b\3\2\2\2\u009e\u0091\3\2\2\2\u009e\u0096"+
		"\3\2\2\2\u009e\u0098\3\2\2\2\u009e\u009a\3\2\2\2\u009e\u009d\3\2\2\2\u009f"+
		"\23\3\2\2\2\u00a0\u00a1\5\"\22\2\u00a1\u00a2\7\t\2\2\u00a2\25\3\2\2\2"+
		"\u00a3\u00a8\5\30\r\2\u00a4\u00a5\7\5\2\2\u00a5\u00a7\5\30\r\2\u00a6\u00a4"+
		"\3\2\2\2\u00a7\u00aa\3\2\2\2\u00a8\u00a6\3\2\2\2\u00a8\u00a9\3\2\2\2\u00a9"+
		"\27\3\2\2\2\u00aa\u00a8\3\2\2\2\u00ab\u00ac\b\r\1\2\u00ac\u00b6\5\32\16"+
		"\2\u00ad\u00ae\t\2\2\2\u00ae\u00b6\5\30\r\21\u00af\u00b0\t\3\2\2\u00b0"+
		"\u00b6\5\30\r\20\u00b1\u00b2\t\4\2\2\u00b2\u00b6\5\30\r\17\u00b3\u00b4"+
		"\7\25\2\2\u00b4\u00b6\5\34\17\2\u00b5\u00ab\3\2\2\2\u00b5\u00ad\3\2\2"+
		"\2\u00b5\u00af\3\2\2\2\u00b5\u00b1\3\2\2\2\u00b5\u00b3\3\2\2\2\u00b6\u00ea"+
		"\3\2\2\2\u00b7\u00b8\f\25\2\2\u00b8\u00b9\7\16\2\2\u00b9\u00e9\5\30\r"+
		"\26\u00ba\u00bb\f\r\2\2\u00bb\u00bc\t\5\2\2\u00bc\u00e9\5\30\r\16\u00bd"+
		"\u00be\f\f\2\2\u00be\u00bf\t\4\2\2\u00bf\u00e9\5\30\r\r\u00c0\u00c1\f"+
		"\13\2\2\u00c1\u00c2\t\6\2\2\u00c2\u00e9\5\30\r\f\u00c3\u00c4\f\n\2\2\u00c4"+
		"\u00c5\t\7\2\2\u00c5\u00e9\5\30\r\13\u00c6\u00c7\f\t\2\2\u00c7\u00c8\t"+
		"\b\2\2\u00c8\u00e9\5\30\r\n\u00c9\u00ca\f\b\2\2\u00ca\u00cb\7!\2\2\u00cb"+
		"\u00e9\5\30\r\t\u00cc\u00cd\f\7\2\2\u00cd\u00ce\7\"\2\2\u00ce\u00e9\5"+
		"\30\r\b\u00cf\u00d0\f\6\2\2\u00d0\u00d1\7#\2\2\u00d1\u00e9\5\30\r\7\u00d2"+
		"\u00d3\f\5\2\2\u00d3\u00d4\7$\2\2\u00d4\u00e9\5\30\r\6\u00d5\u00d6\f\4"+
		"\2\2\u00d6\u00d7\7%\2\2\u00d7\u00e9\5\30\r\5\u00d8\u00d9\f\3\2\2\u00d9"+
		"\u00da\7&\2\2\u00da\u00e9\5\30\r\3\u00db\u00dc\f\24\2\2\u00dc\u00dd\7"+
		"\61\2\2\u00dd\u00de\5\30\r\2\u00de\u00df\7\62\2\2\u00df\u00e9\3\2\2\2"+
		"\u00e0\u00e1\f\23\2\2\u00e1\u00e3\7\63\2\2\u00e2\u00e4\5\26\f\2\u00e3"+
		"\u00e2\3\2\2\2\u00e3\u00e4\3\2\2\2\u00e4\u00e5\3\2\2\2\u00e5\u00e9\7\64"+
		"\2\2\u00e6\u00e7\f\22\2\2\u00e7\u00e9\t\3\2\2\u00e8\u00b7\3\2\2\2\u00e8"+
		"\u00ba\3\2\2\2\u00e8\u00bd\3\2\2\2\u00e8\u00c0\3\2\2\2\u00e8\u00c3\3\2"+
		"\2\2\u00e8\u00c6\3\2\2\2\u00e8\u00c9\3\2\2\2\u00e8\u00cc\3\2\2\2\u00e8"+
		"\u00cf\3\2\2\2\u00e8\u00d2\3\2\2\2\u00e8\u00d5\3\2\2\2\u00e8\u00d8\3\2"+
		"\2\2\u00e8\u00db\3\2\2\2\u00e8\u00e0\3\2\2\2\u00e8\u00e6\3\2\2\2\u00e9"+
		"\u00ec\3\2\2\2\u00ea\u00e8\3\2\2\2\u00ea\u00eb\3\2\2\2\u00eb\31\3\2\2"+
		"\2\u00ec\u00ea\3\2\2\2\u00ed\u00f5\7\60\2\2\u00ee\u00f5\5,\27\2\u00ef"+
		"\u00f5\7\'\2\2\u00f0\u00f1\7\63\2\2\u00f1\u00f2\5\30\r\2\u00f2\u00f3\7"+
		"\64\2\2\u00f3\u00f5\3\2\2\2\u00f4\u00ed\3\2\2\2\u00f4\u00ee\3\2\2\2\u00f4"+
		"\u00ef\3\2\2\2\u00f4\u00f0\3\2\2\2\u00f5\33\3\2\2\2\u00f6\u00fb\5&\24"+
		"\2\u00f7\u00fc\5\36\20\2\u00f8\u00fa\5 \21\2\u00f9\u00f8\3\2\2\2\u00f9"+
		"\u00fa\3\2\2\2\u00fa\u00fc\3\2\2\2\u00fb\u00f7\3\2\2\2\u00fb\u00f9\3\2"+
		"\2\2\u00fc\35\3\2\2\2\u00fd\u00fe\7\61\2\2\u00fe\u00ff\5\30\r\2\u00ff"+
		"\u0106\7\62\2\2\u0100\u0101\7\61\2\2\u0101\u0102\5\30\r\2\u0102\u0103"+
		"\7\62\2\2\u0103\u0105\3\2\2\2\u0104\u0100\3\2\2\2\u0105\u0108\3\2\2\2"+
		"\u0106\u0104\3\2\2\2\u0106\u0107\3\2\2\2\u0107\u010d\3\2\2\2\u0108\u0106"+
		"\3\2\2\2\u0109\u010a\7\61\2\2\u010a\u010c\7\62\2\2\u010b\u0109\3\2\2\2"+
		"\u010c\u010f\3\2\2\2\u010d\u010b\3\2\2\2\u010d\u010e\3\2\2\2\u010e\37"+
		"\3\2\2\2\u010f\u010d\3\2\2\2\u0110\u0112\7\63\2\2\u0111\u0113\5\26\f\2"+
		"\u0112\u0111\3\2\2\2\u0112\u0113\3\2\2\2\u0113\u0114\3\2\2\2\u0114\u0115"+
		"\7\64\2\2\u0115!\3\2\2\2\u0116\u0117\5$\23\2\u0117\u011a\7\60\2\2\u0118"+
		"\u0119\7&\2\2\u0119\u011b\5\30\r\2\u011a\u0118\3\2\2\2\u011a\u011b\3\2"+
		"\2\2\u011b#\3\2\2\2\u011c\u0121\5&\24\2\u011d\u011e\7\61\2\2\u011e\u0120"+
		"\7\62\2\2\u011f\u011d\3\2\2\2\u0120\u0123\3\2\2\2\u0121\u011f\3\2\2\2"+
		"\u0121\u0122\3\2\2\2\u0122%\3\2\2\2\u0123\u0121\3\2\2\2\u0124\u0127\5"+
		"*\26\2\u0125\u0127\5(\25\2\u0126\u0124\3\2\2\2\u0126\u0125\3\2\2\2\u0127"+
		"\'\3\2\2\2\u0128\u0129\7\60\2\2\u0129)\3\2\2\2\u012a\u012b\t\t\2\2\u012b"+
		"+\3\2\2\2\u012c\u012d\t\n\2\2\u012d-\3\2\2\2 \61\63>FKRWahpz\177\u0083"+
		"\u0087\u0093\u009e\u00a8\u00b5\u00e3\u00e8\u00ea\u00f4\u00f9\u00fb\u0106"+
		"\u010d\u0112\u011a\u0121\u0126";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}