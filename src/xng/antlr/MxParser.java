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
		RBB=52, SL_COMMENT=53, COMMENT=54, NEWLINE=55, WS=56;
	public static final int
		RULE_compilationUnit = 0, RULE_progSection = 1, RULE_classDecl = 2, RULE_classBodyDecl = 3, 
		RULE_constructorDecl = 4, RULE_funcDecl = 5, RULE_paramDecl = 6, RULE_block = 7, 
		RULE_blockStat = 8, RULE_statement = 9, RULE_varDeclStat = 10, RULE_exprList = 11, 
		RULE_expression = 12, RULE_primary = 13, RULE_creator = 14, RULE_arrayInit = 15, 
		RULE_arrayDimInit = 16, RULE_classInit = 17, RULE_varDecl = 18, RULE_type = 19, 
		RULE_nonArrayType = 20, RULE_classType = 21, RULE_primType = 22, RULE_literal = 23;
	public static final String[] ruleNames = {
		"compilationUnit", "progSection", "classDecl", "classBodyDecl", "constructorDecl", 
		"funcDecl", "paramDecl", "block", "blockStat", "statement", "varDeclStat", 
		"exprList", "expression", "primary", "creator", "arrayInit", "arrayDimInit", 
		"classInit", "varDecl", "type", "nonArrayType", "classType", "primType", 
		"literal"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'class'", "'void'", "','", "'if'", "'else'", "'for'", "';'", "'while'", 
		"'return'", "'continue'", "'break'", "'.'", "'++'", "'--'", "'new'", "'~'", 
		"'!'", "'+'", "'-'", "'*'", "'/'", "'%'", "'<<'", "'>>'", "'<='", "'>='", 
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
		"LBB", "RBB", "SL_COMMENT", "COMMENT", "NEWLINE", "WS"
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
		public List<ProgSectionContext> progSection() {
			return getRuleContexts(ProgSectionContext.class);
		}
		public ProgSectionContext progSection(int i) {
			return getRuleContext(ProgSectionContext.class,i);
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
			setState(51);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__1) | (1L << T__37) | (1L << T__38) | (1L << T__39) | (1L << Identifier))) != 0)) {
				{
				{
				setState(48);
				progSection();
				}
				}
				setState(53);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(54);
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

	public static class ProgSectionContext extends ParserRuleContext {
		public ClassDeclContext classDecl() {
			return getRuleContext(ClassDeclContext.class,0);
		}
		public FuncDeclContext funcDecl() {
			return getRuleContext(FuncDeclContext.class,0);
		}
		public VarDeclStatContext varDeclStat() {
			return getRuleContext(VarDeclStatContext.class,0);
		}
		public ProgSectionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_progSection; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitProgSection(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProgSectionContext progSection() throws RecognitionException {
		ProgSectionContext _localctx = new ProgSectionContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_progSection);
		try {
			setState(59);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(56);
				classDecl();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(57);
				funcDecl();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(58);
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
		enterRule(_localctx, 4, RULE_classDecl);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(61);
			match(T__0);
			setState(62);
			match(Identifier);
			setState(63);
			match(LBB);
			setState(67);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__1) | (1L << T__37) | (1L << T__38) | (1L << T__39) | (1L << Identifier))) != 0)) {
				{
				{
				setState(64);
				classBodyDecl();
				}
				}
				setState(69);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(70);
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
		enterRule(_localctx, 6, RULE_classBodyDecl);
		try {
			setState(75);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(72);
				varDeclStat();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(73);
				funcDecl();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(74);
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
		enterRule(_localctx, 8, RULE_constructorDecl);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(77);
			match(Identifier);
			setState(78);
			match(LP);
			setState(80);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__37) | (1L << T__38) | (1L << T__39) | (1L << Identifier))) != 0)) {
				{
				setState(79);
				paramDecl();
				}
			}

			setState(82);
			match(RP);
			setState(83);
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
		enterRule(_localctx, 10, RULE_funcDecl);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(87);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__37:
			case T__38:
			case T__39:
			case Identifier:
				{
				setState(85);
				type();
				}
				break;
			case T__1:
				{
				setState(86);
				match(T__1);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(89);
			match(Identifier);
			setState(90);
			match(LP);
			setState(92);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__37) | (1L << T__38) | (1L << T__39) | (1L << Identifier))) != 0)) {
				{
				setState(91);
				paramDecl();
				}
			}

			setState(94);
			match(RP);
			setState(95);
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
		enterRule(_localctx, 12, RULE_paramDecl);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(97);
			varDecl();
			setState(102);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__2) {
				{
				{
				setState(98);
				match(T__2);
				setState(99);
				varDecl();
				}
				}
				setState(104);
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
		enterRule(_localctx, 14, RULE_block);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(105);
			match(LBB);
			setState(109);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__3) | (1L << T__5) | (1L << T__6) | (1L << T__7) | (1L << T__8) | (1L << T__9) | (1L << T__10) | (1L << T__12) | (1L << T__13) | (1L << T__14) | (1L << T__15) | (1L << T__16) | (1L << T__17) | (1L << T__18) | (1L << T__36) | (1L << T__37) | (1L << T__38) | (1L << T__39) | (1L << NullLiteral) | (1L << BoolLiteral) | (1L << IntLiteral) | (1L << StrLiteral) | (1L << Identifier) | (1L << LP) | (1L << LBB))) != 0)) {
				{
				{
				setState(106);
				blockStat();
				}
				}
				setState(111);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(112);
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
		enterRule(_localctx, 16, RULE_blockStat);
		try {
			setState(117);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(114);
				block();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(115);
				statement();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(116);
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
		enterRule(_localctx, 18, RULE_statement);
		int _la;
		try {
			setState(163);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LBB:
				enterOuterAlt(_localctx, 1);
				{
				setState(119);
				block();
				}
				break;
			case T__3:
				enterOuterAlt(_localctx, 2);
				{
				setState(120);
				((StatementContext)_localctx).key = match(T__3);
				setState(121);
				match(LP);
				setState(122);
				((StatementContext)_localctx).cond = expression(0);
				setState(123);
				match(RP);
				setState(124);
				((StatementContext)_localctx).body = statement();
				setState(127);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
				case 1:
					{
					setState(125);
					((StatementContext)_localctx).iselse = match(T__4);
					setState(126);
					((StatementContext)_localctx).elsebody = statement();
					}
					break;
				}
				}
				break;
			case T__5:
				enterOuterAlt(_localctx, 3);
				{
				setState(129);
				((StatementContext)_localctx).key = match(T__5);
				setState(130);
				match(LP);
				setState(132);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__12) | (1L << T__13) | (1L << T__14) | (1L << T__15) | (1L << T__16) | (1L << T__17) | (1L << T__18) | (1L << T__36) | (1L << NullLiteral) | (1L << BoolLiteral) | (1L << IntLiteral) | (1L << StrLiteral) | (1L << Identifier) | (1L << LP))) != 0)) {
					{
					setState(131);
					((StatementContext)_localctx).init = expression(0);
					}
				}

				setState(134);
				match(T__6);
				setState(136);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__12) | (1L << T__13) | (1L << T__14) | (1L << T__15) | (1L << T__16) | (1L << T__17) | (1L << T__18) | (1L << T__36) | (1L << NullLiteral) | (1L << BoolLiteral) | (1L << IntLiteral) | (1L << StrLiteral) | (1L << Identifier) | (1L << LP))) != 0)) {
					{
					setState(135);
					((StatementContext)_localctx).cond = expression(0);
					}
				}

				setState(138);
				match(T__6);
				setState(140);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__12) | (1L << T__13) | (1L << T__14) | (1L << T__15) | (1L << T__16) | (1L << T__17) | (1L << T__18) | (1L << T__36) | (1L << NullLiteral) | (1L << BoolLiteral) | (1L << IntLiteral) | (1L << StrLiteral) | (1L << Identifier) | (1L << LP))) != 0)) {
					{
					setState(139);
					((StatementContext)_localctx).step = exprList();
					}
				}

				setState(142);
				match(RP);
				setState(143);
				((StatementContext)_localctx).body = statement();
				}
				break;
			case T__7:
				enterOuterAlt(_localctx, 4);
				{
				setState(144);
				((StatementContext)_localctx).key = match(T__7);
				setState(145);
				match(LP);
				setState(146);
				((StatementContext)_localctx).cond = expression(0);
				setState(147);
				match(RP);
				setState(148);
				((StatementContext)_localctx).body = statement();
				}
				break;
			case T__8:
				enterOuterAlt(_localctx, 5);
				{
				setState(150);
				((StatementContext)_localctx).key = match(T__8);
				setState(152);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__12) | (1L << T__13) | (1L << T__14) | (1L << T__15) | (1L << T__16) | (1L << T__17) | (1L << T__18) | (1L << T__36) | (1L << NullLiteral) | (1L << BoolLiteral) | (1L << IntLiteral) | (1L << StrLiteral) | (1L << Identifier) | (1L << LP))) != 0)) {
					{
					setState(151);
					((StatementContext)_localctx).ret = expression(0);
					}
				}

				setState(154);
				match(T__6);
				}
				break;
			case T__9:
				enterOuterAlt(_localctx, 6);
				{
				setState(155);
				((StatementContext)_localctx).key = match(T__9);
				setState(156);
				match(T__6);
				}
				break;
			case T__10:
				enterOuterAlt(_localctx, 7);
				{
				setState(157);
				((StatementContext)_localctx).key = match(T__10);
				setState(158);
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
				setState(159);
				((StatementContext)_localctx).expr = expression(0);
				setState(160);
				match(T__6);
				}
				break;
			case T__6:
				enterOuterAlt(_localctx, 9);
				{
				setState(162);
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
		enterRule(_localctx, 20, RULE_varDeclStat);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(165);
			varDecl();
			setState(166);
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
		enterRule(_localctx, 22, RULE_exprList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(168);
			expression(0);
			setState(173);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__2) {
				{
				{
				setState(169);
				match(T__2);
				setState(170);
				expression(0);
				}
				}
				setState(175);
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
		public CreatorContext creator() {
			return getRuleContext(CreatorContext.class,0);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
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
		int _startState = 24;
		enterRecursionRule(_localctx, 24, RULE_expression, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(186);
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
				setState(177);
				primary();
				}
				break;
			case T__14:
				{
				setState(178);
				((ExpressionContext)_localctx).op = match(T__14);
				setState(179);
				creator();
				}
				break;
			case T__15:
			case T__16:
				{
				setState(180);
				((ExpressionContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==T__15 || _la==T__16) ) {
					((ExpressionContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(181);
				expression(14);
				}
				break;
			case T__12:
			case T__13:
				{
				setState(182);
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
				setState(183);
				expression(13);
				}
				break;
			case T__17:
			case T__18:
				{
				setState(184);
				((ExpressionContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==T__17 || _la==T__18) ) {
					((ExpressionContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(185);
				expression(12);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(239);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,20,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(237);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,19,_ctx) ) {
					case 1:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(188);
						if (!(precpred(_ctx, 19))) throw new FailedPredicateException(this, "precpred(_ctx, 19)");
						setState(189);
						((ExpressionContext)_localctx).op = match(T__11);
						setState(190);
						expression(20);
						}
						break;
					case 2:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(191);
						if (!(precpred(_ctx, 11))) throw new FailedPredicateException(this, "precpred(_ctx, 11)");
						setState(192);
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
						setState(193);
						expression(12);
						}
						break;
					case 3:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(194);
						if (!(precpred(_ctx, 10))) throw new FailedPredicateException(this, "precpred(_ctx, 10)");
						setState(195);
						((ExpressionContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==T__17 || _la==T__18) ) {
							((ExpressionContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(196);
						expression(11);
						}
						break;
					case 4:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(197);
						if (!(precpred(_ctx, 9))) throw new FailedPredicateException(this, "precpred(_ctx, 9)");
						setState(198);
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
						setState(199);
						expression(10);
						}
						break;
					case 5:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(200);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						setState(201);
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
						setState(202);
						expression(9);
						}
						break;
					case 6:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(203);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(204);
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
						setState(205);
						expression(8);
						}
						break;
					case 7:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(206);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(207);
						((ExpressionContext)_localctx).op = match(T__30);
						setState(208);
						expression(7);
						}
						break;
					case 8:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(209);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(210);
						((ExpressionContext)_localctx).op = match(T__31);
						setState(211);
						expression(6);
						}
						break;
					case 9:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(212);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(213);
						((ExpressionContext)_localctx).op = match(T__32);
						setState(214);
						expression(5);
						}
						break;
					case 10:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(215);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(216);
						((ExpressionContext)_localctx).op = match(T__33);
						setState(217);
						expression(4);
						}
						break;
					case 11:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(218);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(219);
						((ExpressionContext)_localctx).op = match(T__34);
						setState(220);
						expression(3);
						}
						break;
					case 12:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(221);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(222);
						((ExpressionContext)_localctx).op = match(T__35);
						setState(223);
						expression(1);
						}
						break;
					case 13:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(224);
						if (!(precpred(_ctx, 18))) throw new FailedPredicateException(this, "precpred(_ctx, 18)");
						setState(225);
						((ExpressionContext)_localctx).op = match(LB);
						setState(226);
						expression(0);
						setState(227);
						match(RB);
						}
						break;
					case 14:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(229);
						if (!(precpred(_ctx, 17))) throw new FailedPredicateException(this, "precpred(_ctx, 17)");
						setState(230);
						((ExpressionContext)_localctx).op = match(LP);
						setState(232);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__12) | (1L << T__13) | (1L << T__14) | (1L << T__15) | (1L << T__16) | (1L << T__17) | (1L << T__18) | (1L << T__36) | (1L << NullLiteral) | (1L << BoolLiteral) | (1L << IntLiteral) | (1L << StrLiteral) | (1L << Identifier) | (1L << LP))) != 0)) {
							{
							setState(231);
							exprList();
							}
						}

						setState(234);
						match(RP);
						}
						break;
					case 15:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(235);
						if (!(precpred(_ctx, 16))) throw new FailedPredicateException(this, "precpred(_ctx, 16)");
						setState(236);
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
				setState(241);
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
		enterRule(_localctx, 26, RULE_primary);
		try {
			setState(249);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Identifier:
				enterOuterAlt(_localctx, 1);
				{
				setState(242);
				match(Identifier);
				}
				break;
			case NullLiteral:
			case BoolLiteral:
			case IntLiteral:
			case StrLiteral:
				enterOuterAlt(_localctx, 2);
				{
				setState(243);
				literal();
				}
				break;
			case T__36:
				enterOuterAlt(_localctx, 3);
				{
				setState(244);
				((PrimaryContext)_localctx).isthis = match(T__36);
				}
				break;
			case LP:
				enterOuterAlt(_localctx, 4);
				{
				setState(245);
				match(LP);
				setState(246);
				expression(0);
				setState(247);
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
		public ClassInitContext classInit() {
			return getRuleContext(ClassInitContext.class,0);
		}
		public ArrayInitContext arrayInit() {
			return getRuleContext(ArrayInitContext.class,0);
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
		enterRule(_localctx, 28, RULE_creator);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(251);
			nonArrayType();
			setState(254);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,22,_ctx) ) {
			case 1:
				{
				setState(252);
				classInit();
				}
				break;
			case 2:
				{
				setState(253);
				arrayInit();
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
		public List<ArrayDimInitContext> arrayDimInit() {
			return getRuleContexts(ArrayDimInitContext.class);
		}
		public ArrayDimInitContext arrayDimInit(int i) {
			return getRuleContext(ArrayDimInitContext.class,i);
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
		enterRule(_localctx, 30, RULE_arrayInit);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(259);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,23,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(256);
					arrayDimInit();
					}
					} 
				}
				setState(261);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,23,_ctx);
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

	public static class ArrayDimInitContext extends ParserRuleContext {
		public TerminalNode LB() { return getToken(MxParser.LB, 0); }
		public TerminalNode RB() { return getToken(MxParser.RB, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ArrayDimInitContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arrayDimInit; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitArrayDimInit(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArrayDimInitContext arrayDimInit() throws RecognitionException {
		ArrayDimInitContext _localctx = new ArrayDimInitContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_arrayDimInit);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(262);
			match(LB);
			setState(264);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__12) | (1L << T__13) | (1L << T__14) | (1L << T__15) | (1L << T__16) | (1L << T__17) | (1L << T__18) | (1L << T__36) | (1L << NullLiteral) | (1L << BoolLiteral) | (1L << IntLiteral) | (1L << StrLiteral) | (1L << Identifier) | (1L << LP))) != 0)) {
				{
				setState(263);
				expression(0);
				}
			}

			setState(266);
			match(RB);
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
		enterRule(_localctx, 34, RULE_classInit);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(268);
			match(LP);
			setState(270);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__12) | (1L << T__13) | (1L << T__14) | (1L << T__15) | (1L << T__16) | (1L << T__17) | (1L << T__18) | (1L << T__36) | (1L << NullLiteral) | (1L << BoolLiteral) | (1L << IntLiteral) | (1L << StrLiteral) | (1L << Identifier) | (1L << LP))) != 0)) {
				{
				setState(269);
				exprList();
				}
			}

			setState(272);
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
		enterRule(_localctx, 36, RULE_varDecl);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(274);
			type();
			setState(275);
			match(Identifier);
			setState(278);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__35) {
				{
				setState(276);
				match(T__35);
				setState(277);
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
		enterRule(_localctx, 38, RULE_type);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(280);
			nonArrayType();
			setState(285);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==LB) {
				{
				{
				setState(281);
				match(LB);
				setState(282);
				match(RB);
				}
				}
				setState(287);
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
		enterRule(_localctx, 40, RULE_nonArrayType);
		try {
			setState(290);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__37:
			case T__38:
			case T__39:
				enterOuterAlt(_localctx, 1);
				{
				setState(288);
				primType();
				}
				break;
			case Identifier:
				enterOuterAlt(_localctx, 2);
				{
				setState(289);
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
		enterRule(_localctx, 42, RULE_classType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(292);
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
		enterRule(_localctx, 44, RULE_primType);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(294);
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
		enterRule(_localctx, 46, RULE_literal);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(296);
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
		case 12:
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3:\u012d\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\3\2\7\2\64\n\2\f\2\16\2\67\13\2\3\2\3\2\3\3\3\3\3\3\5\3>\n\3\3\4\3\4"+
		"\3\4\3\4\7\4D\n\4\f\4\16\4G\13\4\3\4\3\4\3\5\3\5\3\5\5\5N\n\5\3\6\3\6"+
		"\3\6\5\6S\n\6\3\6\3\6\3\6\3\7\3\7\5\7Z\n\7\3\7\3\7\3\7\5\7_\n\7\3\7\3"+
		"\7\3\7\3\b\3\b\3\b\7\bg\n\b\f\b\16\bj\13\b\3\t\3\t\7\tn\n\t\f\t\16\tq"+
		"\13\t\3\t\3\t\3\n\3\n\3\n\5\nx\n\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13"+
		"\3\13\5\13\u0082\n\13\3\13\3\13\3\13\5\13\u0087\n\13\3\13\3\13\5\13\u008b"+
		"\n\13\3\13\3\13\5\13\u008f\n\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13"+
		"\3\13\3\13\5\13\u009b\n\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13"+
		"\5\13\u00a6\n\13\3\f\3\f\3\f\3\r\3\r\3\r\7\r\u00ae\n\r\f\r\16\r\u00b1"+
		"\13\r\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\5\16\u00bd\n\16"+
		"\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16"+
		"\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16"+
		"\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16"+
		"\3\16\3\16\5\16\u00eb\n\16\3\16\3\16\3\16\7\16\u00f0\n\16\f\16\16\16\u00f3"+
		"\13\16\3\17\3\17\3\17\3\17\3\17\3\17\3\17\5\17\u00fc\n\17\3\20\3\20\3"+
		"\20\5\20\u0101\n\20\3\21\7\21\u0104\n\21\f\21\16\21\u0107\13\21\3\22\3"+
		"\22\5\22\u010b\n\22\3\22\3\22\3\23\3\23\5\23\u0111\n\23\3\23\3\23\3\24"+
		"\3\24\3\24\3\24\5\24\u0119\n\24\3\25\3\25\3\25\7\25\u011e\n\25\f\25\16"+
		"\25\u0121\13\25\3\26\3\26\5\26\u0125\n\26\3\27\3\27\3\30\3\30\3\31\3\31"+
		"\3\31\2\3\32\32\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\60\2\13"+
		"\3\2\22\23\3\2\17\20\3\2\24\25\3\2\26\30\3\2\31\32\3\2\33\36\3\2\37 \3"+
		"\2(*\3\2+.\2\u014d\2\65\3\2\2\2\4=\3\2\2\2\6?\3\2\2\2\bM\3\2\2\2\nO\3"+
		"\2\2\2\fY\3\2\2\2\16c\3\2\2\2\20k\3\2\2\2\22w\3\2\2\2\24\u00a5\3\2\2\2"+
		"\26\u00a7\3\2\2\2\30\u00aa\3\2\2\2\32\u00bc\3\2\2\2\34\u00fb\3\2\2\2\36"+
		"\u00fd\3\2\2\2 \u0105\3\2\2\2\"\u0108\3\2\2\2$\u010e\3\2\2\2&\u0114\3"+
		"\2\2\2(\u011a\3\2\2\2*\u0124\3\2\2\2,\u0126\3\2\2\2.\u0128\3\2\2\2\60"+
		"\u012a\3\2\2\2\62\64\5\4\3\2\63\62\3\2\2\2\64\67\3\2\2\2\65\63\3\2\2\2"+
		"\65\66\3\2\2\2\668\3\2\2\2\67\65\3\2\2\289\7\2\2\39\3\3\2\2\2:>\5\6\4"+
		"\2;>\5\f\7\2<>\5\26\f\2=:\3\2\2\2=;\3\2\2\2=<\3\2\2\2>\5\3\2\2\2?@\7\3"+
		"\2\2@A\7\60\2\2AE\7\65\2\2BD\5\b\5\2CB\3\2\2\2DG\3\2\2\2EC\3\2\2\2EF\3"+
		"\2\2\2FH\3\2\2\2GE\3\2\2\2HI\7\66\2\2I\7\3\2\2\2JN\5\26\f\2KN\5\f\7\2"+
		"LN\5\n\6\2MJ\3\2\2\2MK\3\2\2\2ML\3\2\2\2N\t\3\2\2\2OP\7\60\2\2PR\7\63"+
		"\2\2QS\5\16\b\2RQ\3\2\2\2RS\3\2\2\2ST\3\2\2\2TU\7\64\2\2UV\5\20\t\2V\13"+
		"\3\2\2\2WZ\5(\25\2XZ\7\4\2\2YW\3\2\2\2YX\3\2\2\2Z[\3\2\2\2[\\\7\60\2\2"+
		"\\^\7\63\2\2]_\5\16\b\2^]\3\2\2\2^_\3\2\2\2_`\3\2\2\2`a\7\64\2\2ab\5\20"+
		"\t\2b\r\3\2\2\2ch\5&\24\2de\7\5\2\2eg\5&\24\2fd\3\2\2\2gj\3\2\2\2hf\3"+
		"\2\2\2hi\3\2\2\2i\17\3\2\2\2jh\3\2\2\2ko\7\65\2\2ln\5\22\n\2ml\3\2\2\2"+
		"nq\3\2\2\2om\3\2\2\2op\3\2\2\2pr\3\2\2\2qo\3\2\2\2rs\7\66\2\2s\21\3\2"+
		"\2\2tx\5\20\t\2ux\5\24\13\2vx\5\26\f\2wt\3\2\2\2wu\3\2\2\2wv\3\2\2\2x"+
		"\23\3\2\2\2y\u00a6\5\20\t\2z{\7\6\2\2{|\7\63\2\2|}\5\32\16\2}~\7\64\2"+
		"\2~\u0081\5\24\13\2\177\u0080\7\7\2\2\u0080\u0082\5\24\13\2\u0081\177"+
		"\3\2\2\2\u0081\u0082\3\2\2\2\u0082\u00a6\3\2\2\2\u0083\u0084\7\b\2\2\u0084"+
		"\u0086\7\63\2\2\u0085\u0087\5\32\16\2\u0086\u0085\3\2\2\2\u0086\u0087"+
		"\3\2\2\2\u0087\u0088\3\2\2\2\u0088\u008a\7\t\2\2\u0089\u008b\5\32\16\2"+
		"\u008a\u0089\3\2\2\2\u008a\u008b\3\2\2\2\u008b\u008c\3\2\2\2\u008c\u008e"+
		"\7\t\2\2\u008d\u008f\5\30\r\2\u008e\u008d\3\2\2\2\u008e\u008f\3\2\2\2"+
		"\u008f\u0090\3\2\2\2\u0090\u0091\7\64\2\2\u0091\u00a6\5\24\13\2\u0092"+
		"\u0093\7\n\2\2\u0093\u0094\7\63\2\2\u0094\u0095\5\32\16\2\u0095\u0096"+
		"\7\64\2\2\u0096\u0097\5\24\13\2\u0097\u00a6\3\2\2\2\u0098\u009a\7\13\2"+
		"\2\u0099\u009b\5\32\16\2\u009a\u0099\3\2\2\2\u009a\u009b\3\2\2\2\u009b"+
		"\u009c\3\2\2\2\u009c\u00a6\7\t\2\2\u009d\u009e\7\f\2\2\u009e\u00a6\7\t"+
		"\2\2\u009f\u00a0\7\r\2\2\u00a0\u00a6\7\t\2\2\u00a1\u00a2\5\32\16\2\u00a2"+
		"\u00a3\7\t\2\2\u00a3\u00a6\3\2\2\2\u00a4\u00a6\7\t\2\2\u00a5y\3\2\2\2"+
		"\u00a5z\3\2\2\2\u00a5\u0083\3\2\2\2\u00a5\u0092\3\2\2\2\u00a5\u0098\3"+
		"\2\2\2\u00a5\u009d\3\2\2\2\u00a5\u009f\3\2\2\2\u00a5\u00a1\3\2\2\2\u00a5"+
		"\u00a4\3\2\2\2\u00a6\25\3\2\2\2\u00a7\u00a8\5&\24\2\u00a8\u00a9\7\t\2"+
		"\2\u00a9\27\3\2\2\2\u00aa\u00af\5\32\16\2\u00ab\u00ac\7\5\2\2\u00ac\u00ae"+
		"\5\32\16\2\u00ad\u00ab\3\2\2\2\u00ae\u00b1\3\2\2\2\u00af\u00ad\3\2\2\2"+
		"\u00af\u00b0\3\2\2\2\u00b0\31\3\2\2\2\u00b1\u00af\3\2\2\2\u00b2\u00b3"+
		"\b\16\1\2\u00b3\u00bd\5\34\17\2\u00b4\u00b5\7\21\2\2\u00b5\u00bd\5\36"+
		"\20\2\u00b6\u00b7\t\2\2\2\u00b7\u00bd\5\32\16\20\u00b8\u00b9\t\3\2\2\u00b9"+
		"\u00bd\5\32\16\17\u00ba\u00bb\t\4\2\2\u00bb\u00bd\5\32\16\16\u00bc\u00b2"+
		"\3\2\2\2\u00bc\u00b4\3\2\2\2\u00bc\u00b6\3\2\2\2\u00bc\u00b8\3\2\2\2\u00bc"+
		"\u00ba\3\2\2\2\u00bd\u00f1\3\2\2\2\u00be\u00bf\f\25\2\2\u00bf\u00c0\7"+
		"\16\2\2\u00c0\u00f0\5\32\16\26\u00c1\u00c2\f\r\2\2\u00c2\u00c3\t\5\2\2"+
		"\u00c3\u00f0\5\32\16\16\u00c4\u00c5\f\f\2\2\u00c5\u00c6\t\4\2\2\u00c6"+
		"\u00f0\5\32\16\r\u00c7\u00c8\f\13\2\2\u00c8\u00c9\t\6\2\2\u00c9\u00f0"+
		"\5\32\16\f\u00ca\u00cb\f\n\2\2\u00cb\u00cc\t\7\2\2\u00cc\u00f0\5\32\16"+
		"\13\u00cd\u00ce\f\t\2\2\u00ce\u00cf\t\b\2\2\u00cf\u00f0\5\32\16\n\u00d0"+
		"\u00d1\f\b\2\2\u00d1\u00d2\7!\2\2\u00d2\u00f0\5\32\16\t\u00d3\u00d4\f"+
		"\7\2\2\u00d4\u00d5\7\"\2\2\u00d5\u00f0\5\32\16\b\u00d6\u00d7\f\6\2\2\u00d7"+
		"\u00d8\7#\2\2\u00d8\u00f0\5\32\16\7\u00d9\u00da\f\5\2\2\u00da\u00db\7"+
		"$\2\2\u00db\u00f0\5\32\16\6\u00dc\u00dd\f\4\2\2\u00dd\u00de\7%\2\2\u00de"+
		"\u00f0\5\32\16\5\u00df\u00e0\f\3\2\2\u00e0\u00e1\7&\2\2\u00e1\u00f0\5"+
		"\32\16\3\u00e2\u00e3\f\24\2\2\u00e3\u00e4\7\61\2\2\u00e4\u00e5\5\32\16"+
		"\2\u00e5\u00e6\7\62\2\2\u00e6\u00f0\3\2\2\2\u00e7\u00e8\f\23\2\2\u00e8"+
		"\u00ea\7\63\2\2\u00e9\u00eb\5\30\r\2\u00ea\u00e9\3\2\2\2\u00ea\u00eb\3"+
		"\2\2\2\u00eb\u00ec\3\2\2\2\u00ec\u00f0\7\64\2\2\u00ed\u00ee\f\22\2\2\u00ee"+
		"\u00f0\t\3\2\2\u00ef\u00be\3\2\2\2\u00ef\u00c1\3\2\2\2\u00ef\u00c4\3\2"+
		"\2\2\u00ef\u00c7\3\2\2\2\u00ef\u00ca\3\2\2\2\u00ef\u00cd\3\2\2\2\u00ef"+
		"\u00d0\3\2\2\2\u00ef\u00d3\3\2\2\2\u00ef\u00d6\3\2\2\2\u00ef\u00d9\3\2"+
		"\2\2\u00ef\u00dc\3\2\2\2\u00ef\u00df\3\2\2\2\u00ef\u00e2\3\2\2\2\u00ef"+
		"\u00e7\3\2\2\2\u00ef\u00ed\3\2\2\2\u00f0\u00f3\3\2\2\2\u00f1\u00ef\3\2"+
		"\2\2\u00f1\u00f2\3\2\2\2\u00f2\33\3\2\2\2\u00f3\u00f1\3\2\2\2\u00f4\u00fc"+
		"\7\60\2\2\u00f5\u00fc\5\60\31\2\u00f6\u00fc\7\'\2\2\u00f7\u00f8\7\63\2"+
		"\2\u00f8\u00f9\5\32\16\2\u00f9\u00fa\7\64\2\2\u00fa\u00fc\3\2\2\2\u00fb"+
		"\u00f4\3\2\2\2\u00fb\u00f5\3\2\2\2\u00fb\u00f6\3\2\2\2\u00fb\u00f7\3\2"+
		"\2\2\u00fc\35\3\2\2\2\u00fd\u0100\5*\26\2\u00fe\u0101\5$\23\2\u00ff\u0101"+
		"\5 \21\2\u0100\u00fe\3\2\2\2\u0100\u00ff\3\2\2\2\u0101\37\3\2\2\2\u0102"+
		"\u0104\5\"\22\2\u0103\u0102\3\2\2\2\u0104\u0107\3\2\2\2\u0105\u0103\3"+
		"\2\2\2\u0105\u0106\3\2\2\2\u0106!\3\2\2\2\u0107\u0105\3\2\2\2\u0108\u010a"+
		"\7\61\2\2\u0109\u010b\5\32\16\2\u010a\u0109\3\2\2\2\u010a\u010b\3\2\2"+
		"\2\u010b\u010c\3\2\2\2\u010c\u010d\7\62\2\2\u010d#\3\2\2\2\u010e\u0110"+
		"\7\63\2\2\u010f\u0111\5\30\r\2\u0110\u010f\3\2\2\2\u0110\u0111\3\2\2\2"+
		"\u0111\u0112\3\2\2\2\u0112\u0113\7\64\2\2\u0113%\3\2\2\2\u0114\u0115\5"+
		"(\25\2\u0115\u0118\7\60\2\2\u0116\u0117\7&\2\2\u0117\u0119\5\32\16\2\u0118"+
		"\u0116\3\2\2\2\u0118\u0119\3\2\2\2\u0119\'\3\2\2\2\u011a\u011f\5*\26\2"+
		"\u011b\u011c\7\61\2\2\u011c\u011e\7\62\2\2\u011d\u011b\3\2\2\2\u011e\u0121"+
		"\3\2\2\2\u011f\u011d\3\2\2\2\u011f\u0120\3\2\2\2\u0120)\3\2\2\2\u0121"+
		"\u011f\3\2\2\2\u0122\u0125\5.\30\2\u0123\u0125\5,\27\2\u0124\u0122\3\2"+
		"\2\2\u0124\u0123\3\2\2\2\u0125+\3\2\2\2\u0126\u0127\7\60\2\2\u0127-\3"+
		"\2\2\2\u0128\u0129\t\t\2\2\u0129/\3\2\2\2\u012a\u012b\t\n\2\2\u012b\61"+
		"\3\2\2\2\37\65=EMRY^how\u0081\u0086\u008a\u008e\u009a\u00a5\u00af\u00bc"+
		"\u00ea\u00ef\u00f1\u00fb\u0100\u0105\u010a\u0110\u0118\u011f\u0124";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}