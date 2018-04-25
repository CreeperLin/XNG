// Generated from /root/GitRepo/XNG/grammar/Mx.g4 by ANTLR 4.7
package xng.antlr;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.ATN;
import org.antlr.v4.runtime.atn.ATNDeserializer;
import org.antlr.v4.runtime.atn.ParserATNSimulator;
import org.antlr.v4.runtime.atn.PredictionContextCache;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.List;

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
		RULE_compilationUnit = 0, RULE_progSection = 1, RULE_classDecl = 2, RULE_classBodyDecl = 3, 
		RULE_constructorDecl = 4, RULE_funcDecl = 5, RULE_paramDecl = 6, RULE_block = 7, 
		RULE_blockStat = 8, RULE_statement = 9, RULE_varDeclStat = 10, RULE_exprList = 11, 
		RULE_expression = 12, RULE_primary = 13, RULE_creator = 14, RULE_arrayInit = 15, 
		RULE_classInit = 16, RULE_varDecl = 17, RULE_type = 18, RULE_nonArrayType = 19, 
		RULE_classType = 20, RULE_primType = 21, RULE_literal = 22;
	public static final String[] ruleNames = {
		"compilationUnit", "progSection", "classDecl", "classBodyDecl", "constructorDecl", 
		"funcDecl", "paramDecl", "block", "blockStat", "statement", "varDeclStat", 
		"exprList", "expression", "primary", "creator", "arrayInit", "classInit", 
		"varDecl", "type", "nonArrayType", "classType", "primType", "literal"
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
			setState(49);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__1) | (1L << T__37) | (1L << T__38) | (1L << T__39) | (1L << Identifier))) != 0)) {
				{
				{
				setState(46);
				progSection();
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
//		catch (RecognitionException re) {
//			_localctx.exception = re;
//			_errHandler.reportError(this, re);
//			_errHandler.recover(this, re);
//		}
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
			setState(57);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(54);
				classDecl();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(55);
				funcDecl();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(56);
				varDeclStat();
				}
				break;
			}
		}
//		catch (RecognitionException re) {
//			_localctx.exception = re;
//			_errHandler.reportError(this, re);
//			_errHandler.recover(this, re);
//		}
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
			setState(59);
			match(T__0);
			setState(60);
			match(Identifier);
			setState(61);
			match(LBB);
			setState(65);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__1) | (1L << T__37) | (1L << T__38) | (1L << T__39) | (1L << Identifier))) != 0)) {
				{
				{
				setState(62);
				classBodyDecl();
				}
				}
				setState(67);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(68);
			match(RBB);
			}
		}
//		catch (RecognitionException re) {
//			_localctx.exception = re;
//			_errHandler.reportError(this, re);
//			_errHandler.recover(this, re);
//		}
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
			setState(73);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(70);
				varDeclStat();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(71);
				funcDecl();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(72);
				constructorDecl();
				}
				break;
			}
		}
//		catch (RecognitionException re) {
//			_localctx.exception = re;
//			_errHandler.reportError(this, re);
//			_errHandler.recover(this, re);
//		}
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
			setState(75);
			match(Identifier);
			setState(76);
			match(LP);
			setState(78);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__37) | (1L << T__38) | (1L << T__39) | (1L << Identifier))) != 0)) {
				{
				setState(77);
				paramDecl();
				}
			}

			setState(80);
			match(RP);
			setState(81);
			block();
			}
		}
//		catch (RecognitionException re) {
//			_localctx.exception = re;
//			_errHandler.reportError(this, re);
//			_errHandler.recover(this, re);
//		}
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
			setState(85);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__37:
			case T__38:
			case T__39:
			case Identifier:
				{
				setState(83);
				type();
				}
				break;
			case T__1:
				{
				setState(84);
				match(T__1);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(87);
			match(Identifier);
			setState(88);
			match(LP);
			setState(90);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__37) | (1L << T__38) | (1L << T__39) | (1L << Identifier))) != 0)) {
				{
				setState(89);
				paramDecl();
				}
			}

			setState(92);
			match(RP);
			setState(93);
			block();
			}
		}
//		catch (RecognitionException re) {
//			_localctx.exception = re;
//			_errHandler.reportError(this, re);
//			_errHandler.recover(this, re);
//		}
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
			setState(95);
			varDecl();
			setState(100);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__2) {
				{
				{
				setState(96);
				match(T__2);
				setState(97);
				varDecl();
				}
				}
				setState(102);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
//		catch (RecognitionException re) {
//			_localctx.exception = re;
//			_errHandler.reportError(this, re);
//			_errHandler.recover(this, re);
//		}
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
			setState(103);
			match(LBB);
			setState(107);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__3) | (1L << T__5) | (1L << T__6) | (1L << T__7) | (1L << T__8) | (1L << T__9) | (1L << T__10) | (1L << T__12) | (1L << T__13) | (1L << T__14) | (1L << T__15) | (1L << T__16) | (1L << T__17) | (1L << T__18) | (1L << T__36) | (1L << T__37) | (1L << T__38) | (1L << T__39) | (1L << NullLiteral) | (1L << BoolLiteral) | (1L << IntLiteral) | (1L << StrLiteral) | (1L << Identifier) | (1L << LP) | (1L << LBB))) != 0)) {
				{
				{
				setState(104);
				blockStat();
				}
				}
				setState(109);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(110);
			match(RBB);
			}
		}
//		catch (RecognitionException re) {
//			_localctx.exception = re;
//			_errHandler.reportError(this, re);
//			_errHandler.recover(this, re);
//		}
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
			setState(115);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(112);
				block();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(113);
				statement();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(114);
				varDeclStat();
				}
				break;
			}
		}
//		catch (RecognitionException re) {
//			_localctx.exception = re;
//			_errHandler.reportError(this, re);
//			_errHandler.recover(this, re);
//		}
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
			setState(161);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LBB:
				enterOuterAlt(_localctx, 1);
				{
				setState(117);
				block();
				}
				break;
			case T__3:
				enterOuterAlt(_localctx, 2);
				{
				setState(118);
				((StatementContext)_localctx).key = match(T__3);
				setState(119);
				match(LP);
				setState(120);
				((StatementContext)_localctx).cond = expression(0);
				setState(121);
				match(RP);
				setState(122);
				((StatementContext)_localctx).body = statement();
				setState(125);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
				case 1:
					{
					setState(123);
					((StatementContext)_localctx).iselse = match(T__4);
					setState(124);
					((StatementContext)_localctx).elsebody = statement();
					}
					break;
				}
				}
				break;
			case T__5:
				enterOuterAlt(_localctx, 3);
				{
				setState(127);
				((StatementContext)_localctx).key = match(T__5);
				setState(128);
				match(LP);
				setState(130);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__12) | (1L << T__13) | (1L << T__14) | (1L << T__15) | (1L << T__16) | (1L << T__17) | (1L << T__18) | (1L << T__36) | (1L << NullLiteral) | (1L << BoolLiteral) | (1L << IntLiteral) | (1L << StrLiteral) | (1L << Identifier) | (1L << LP))) != 0)) {
					{
					setState(129);
					((StatementContext)_localctx).init = expression(0);
					}
				}

				setState(132);
				match(T__6);
				setState(134);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__12) | (1L << T__13) | (1L << T__14) | (1L << T__15) | (1L << T__16) | (1L << T__17) | (1L << T__18) | (1L << T__36) | (1L << NullLiteral) | (1L << BoolLiteral) | (1L << IntLiteral) | (1L << StrLiteral) | (1L << Identifier) | (1L << LP))) != 0)) {
					{
					setState(133);
					((StatementContext)_localctx).cond = expression(0);
					}
				}

				setState(136);
				match(T__6);
				setState(138);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__12) | (1L << T__13) | (1L << T__14) | (1L << T__15) | (1L << T__16) | (1L << T__17) | (1L << T__18) | (1L << T__36) | (1L << NullLiteral) | (1L << BoolLiteral) | (1L << IntLiteral) | (1L << StrLiteral) | (1L << Identifier) | (1L << LP))) != 0)) {
					{
					setState(137);
					((StatementContext)_localctx).step = exprList();
					}
				}

				setState(140);
				match(RP);
				setState(141);
				((StatementContext)_localctx).body = statement();
				}
				break;
			case T__7:
				enterOuterAlt(_localctx, 4);
				{
				setState(142);
				((StatementContext)_localctx).key = match(T__7);
				setState(143);
				match(LP);
				setState(144);
				((StatementContext)_localctx).cond = expression(0);
				setState(145);
				match(RP);
				setState(146);
				((StatementContext)_localctx).body = statement();
				}
				break;
			case T__8:
				enterOuterAlt(_localctx, 5);
				{
				setState(148);
				((StatementContext)_localctx).key = match(T__8);
				setState(150);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__12) | (1L << T__13) | (1L << T__14) | (1L << T__15) | (1L << T__16) | (1L << T__17) | (1L << T__18) | (1L << T__36) | (1L << NullLiteral) | (1L << BoolLiteral) | (1L << IntLiteral) | (1L << StrLiteral) | (1L << Identifier) | (1L << LP))) != 0)) {
					{
					setState(149);
					((StatementContext)_localctx).ret = expression(0);
					}
				}

				setState(152);
				match(T__6);
				}
				break;
			case T__9:
				enterOuterAlt(_localctx, 6);
				{
				setState(153);
				((StatementContext)_localctx).key = match(T__9);
				setState(154);
				match(T__6);
				}
				break;
			case T__10:
				enterOuterAlt(_localctx, 7);
				{
				setState(155);
				((StatementContext)_localctx).key = match(T__10);
				setState(156);
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
				setState(157);
				((StatementContext)_localctx).expr = expression(0);
				setState(158);
				match(T__6);
				}
				break;
			case T__6:
				enterOuterAlt(_localctx, 9);
				{
				setState(160);
				((StatementContext)_localctx).colon = match(T__6);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
//		catch (RecognitionException re) {
//			_localctx.exception = re;
//			_errHandler.reportError(this, re);
//			_errHandler.recover(this, re);
//		}
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
			setState(163);
			varDecl();
			setState(164);
			match(T__6);
			}
		}
//		catch (RecognitionException re) {
//			_localctx.exception = re;
//			_errHandler.reportError(this, re);
//			_errHandler.recover(this, re);
//		}
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
			setState(166);
			expression(0);
			setState(171);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__2) {
				{
				{
				setState(167);
				match(T__2);
				setState(168);
				expression(0);
				}
				}
				setState(173);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
//		catch (RecognitionException re) {
//			_localctx.exception = re;
//			_errHandler.reportError(this, re);
//			_errHandler.recover(this, re);
//		}
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
		int _startState = 24;
		enterRecursionRule(_localctx, 24, RULE_expression, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(184);
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
				setState(175);
				primary();
				}
				break;
			case T__14:
			case T__15:
				{
				setState(176);
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
				setState(177);
				expression(15);
				}
				break;
			case T__12:
			case T__13:
				{
				setState(178);
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
				setState(179);
				expression(14);
				}
				break;
			case T__16:
			case T__17:
				{
				setState(180);
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
				setState(181);
				expression(13);
				}
				break;
			case T__18:
				{
				setState(182);
				((ExpressionContext)_localctx).op = match(T__18);
				setState(183);
				creator();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(237);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,20,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(235);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,19,_ctx) ) {
					case 1:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(186);
						if (!(precpred(_ctx, 19))) throw new FailedPredicateException(this, "precpred(_ctx, 19)");
						setState(187);
						((ExpressionContext)_localctx).op = match(T__11);
						setState(188);
						expression(20);
						}
						break;
					case 2:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(189);
						if (!(precpred(_ctx, 11))) throw new FailedPredicateException(this, "precpred(_ctx, 11)");
						setState(190);
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
						setState(191);
						expression(12);
						}
						break;
					case 3:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(192);
						if (!(precpred(_ctx, 10))) throw new FailedPredicateException(this, "precpred(_ctx, 10)");
						setState(193);
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
						setState(194);
						expression(11);
						}
						break;
					case 4:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(195);
						if (!(precpred(_ctx, 9))) throw new FailedPredicateException(this, "precpred(_ctx, 9)");
						setState(196);
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
						setState(197);
						expression(10);
						}
						break;
					case 5:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(198);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						setState(199);
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
						setState(200);
						expression(9);
						}
						break;
					case 6:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(201);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(202);
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
						setState(203);
						expression(8);
						}
						break;
					case 7:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(204);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(205);
						((ExpressionContext)_localctx).op = match(T__30);
						setState(206);
						expression(7);
						}
						break;
					case 8:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(207);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(208);
						((ExpressionContext)_localctx).op = match(T__31);
						setState(209);
						expression(6);
						}
						break;
					case 9:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(210);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(211);
						((ExpressionContext)_localctx).op = match(T__32);
						setState(212);
						expression(5);
						}
						break;
					case 10:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(213);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(214);
						((ExpressionContext)_localctx).op = match(T__33);
						setState(215);
						expression(4);
						}
						break;
					case 11:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(216);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(217);
						((ExpressionContext)_localctx).op = match(T__34);
						setState(218);
						expression(3);
						}
						break;
					case 12:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(219);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(220);
						((ExpressionContext)_localctx).op = match(T__35);
						setState(221);
						expression(1);
						}
						break;
					case 13:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(222);
						if (!(precpred(_ctx, 18))) throw new FailedPredicateException(this, "precpred(_ctx, 18)");
						setState(223);
						((ExpressionContext)_localctx).op = match(LB);
						setState(224);
						expression(0);
						setState(225);
						match(RB);
						}
						break;
					case 14:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(227);
						if (!(precpred(_ctx, 17))) throw new FailedPredicateException(this, "precpred(_ctx, 17)");
						setState(228);
						((ExpressionContext)_localctx).op = match(LP);
						setState(230);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__12) | (1L << T__13) | (1L << T__14) | (1L << T__15) | (1L << T__16) | (1L << T__17) | (1L << T__18) | (1L << T__36) | (1L << NullLiteral) | (1L << BoolLiteral) | (1L << IntLiteral) | (1L << StrLiteral) | (1L << Identifier) | (1L << LP))) != 0)) {
							{
							setState(229);
							exprList();
							}
						}

						setState(232);
						match(RP);
						}
						break;
					case 15:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(233);
						if (!(precpred(_ctx, 16))) throw new FailedPredicateException(this, "precpred(_ctx, 16)");
						setState(234);
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
				setState(239);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,20,_ctx);
			}
			}
		}
//		catch (RecognitionException re) {
//			_localctx.exception = re;
//			_errHandler.reportError(this, re);
//			_errHandler.recover(this, re);
//		}
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
			setState(247);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Identifier:
				enterOuterAlt(_localctx, 1);
				{
				setState(240);
				match(Identifier);
				}
				break;
			case NullLiteral:
			case BoolLiteral:
			case IntLiteral:
			case StrLiteral:
				enterOuterAlt(_localctx, 2);
				{
				setState(241);
				literal();
				}
				break;
			case T__36:
				enterOuterAlt(_localctx, 3);
				{
				setState(242);
				((PrimaryContext)_localctx).isthis = match(T__36);
				}
				break;
			case LP:
				enterOuterAlt(_localctx, 4);
				{
				setState(243);
				match(LP);
				setState(244);
				expression(0);
				setState(245);
				match(RP);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
//		catch (RecognitionException re) {
//			_localctx.exception = re;
//			_errHandler.reportError(this, re);
//			_errHandler.recover(this, re);
//		}
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
		enterRule(_localctx, 28, RULE_creator);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(249);
			nonArrayType();
			setState(254);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,23,_ctx) ) {
			case 1:
				{
				setState(250);
				arrayInit();
				}
				break;
			case 2:
				{
				setState(252);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,22,_ctx) ) {
				case 1:
					{
					setState(251);
					classInit();
					}
					break;
				}
				}
				break;
			}
			}
		}
//		catch (RecognitionException re) {
//			_localctx.exception = re;
//			_errHandler.reportError(this, re);
//			_errHandler.recover(this, re);
//		}
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
		enterRule(_localctx, 30, RULE_arrayInit);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(256);
			match(LB);
			setState(257);
			expression(0);
			setState(258);
			match(RB);
			setState(265);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,24,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(259);
					match(LB);
					setState(260);
					expression(0);
					setState(261);
					match(RB);
					}
					} 
				}
				setState(267);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,24,_ctx);
			}
			setState(272);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,25,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(268);
					match(LB);
					setState(269);
					match(RB);
					}
					} 
				}
				setState(274);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,25,_ctx);
			}
			}
		}
//		catch (RecognitionException re) {
//			_localctx.exception = re;
//			_errHandler.reportError(this, re);
//			_errHandler.recover(this, re);
//		}
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
		enterRule(_localctx, 32, RULE_classInit);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(275);
			match(LP);
			setState(277);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__12) | (1L << T__13) | (1L << T__14) | (1L << T__15) | (1L << T__16) | (1L << T__17) | (1L << T__18) | (1L << T__36) | (1L << NullLiteral) | (1L << BoolLiteral) | (1L << IntLiteral) | (1L << StrLiteral) | (1L << Identifier) | (1L << LP))) != 0)) {
				{
				setState(276);
				exprList();
				}
			}

			setState(279);
			match(RP);
			}
		}
//		catch (RecognitionException re) {
//			_localctx.exception = re;
//			_errHandler.reportError(this, re);
//			_errHandler.recover(this, re);
//		}
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
		enterRule(_localctx, 34, RULE_varDecl);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(281);
			type();
			setState(282);
			match(Identifier);
			setState(285);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__35) {
				{
				setState(283);
				match(T__35);
				setState(284);
				expression(0);
				}
			}

			}
		}
//		catch (RecognitionException re) {
//			_localctx.exception = re;
//			_errHandler.reportError(this, re);
//			_errHandler.recover(this, re);
//		}
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
		enterRule(_localctx, 36, RULE_type);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(287);
			nonArrayType();
			setState(292);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==LB) {
				{
				{
				setState(288);
				match(LB);
				setState(289);
				match(RB);
				}
				}
				setState(294);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
//		catch (RecognitionException re) {
//			_localctx.exception = re;
//			_errHandler.reportError(this, re);
//			_errHandler.recover(this, re);
//		}
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
		enterRule(_localctx, 38, RULE_nonArrayType);
		try {
			setState(297);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__37:
			case T__38:
			case T__39:
				enterOuterAlt(_localctx, 1);
				{
				setState(295);
				primType();
				}
				break;
			case Identifier:
				enterOuterAlt(_localctx, 2);
				{
				setState(296);
				classType();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
//		catch (RecognitionException re) {
//			_localctx.exception = re;
//			_errHandler.reportError(this, re);
//			_errHandler.recover(this, re);
//		}
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
		enterRule(_localctx, 40, RULE_classType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(299);
			match(Identifier);
			}
		}
//		catch (RecognitionException re) {
//			_localctx.exception = re;
//			_errHandler.reportError(this, re);
//			_errHandler.recover(this, re);
//		}
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
		enterRule(_localctx, 42, RULE_primType);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(301);
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
//		catch (RecognitionException re) {
//			_localctx.exception = re;
//			_errHandler.reportError(this, re);
//			_errHandler.recover(this, re);
//		}
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
		enterRule(_localctx, 44, RULE_literal);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(303);
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
//		catch (RecognitionException re) {
//			_localctx.exception = re;
//			_errHandler.reportError(this, re);
//			_errHandler.recover(this, re);
//		}
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\39\u0134\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\3\2\7\2\62"+
		"\n\2\f\2\16\2\65\13\2\3\2\3\2\3\3\3\3\3\3\5\3<\n\3\3\4\3\4\3\4\3\4\7\4"+
		"B\n\4\f\4\16\4E\13\4\3\4\3\4\3\5\3\5\3\5\5\5L\n\5\3\6\3\6\3\6\5\6Q\n\6"+
		"\3\6\3\6\3\6\3\7\3\7\5\7X\n\7\3\7\3\7\3\7\5\7]\n\7\3\7\3\7\3\7\3\b\3\b"+
		"\3\b\7\be\n\b\f\b\16\bh\13\b\3\t\3\t\7\tl\n\t\f\t\16\to\13\t\3\t\3\t\3"+
		"\n\3\n\3\n\5\nv\n\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\5\13\u0080"+
		"\n\13\3\13\3\13\3\13\5\13\u0085\n\13\3\13\3\13\5\13\u0089\n\13\3\13\3"+
		"\13\5\13\u008d\n\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13"+
		"\5\13\u0099\n\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\5\13\u00a4"+
		"\n\13\3\f\3\f\3\f\3\r\3\r\3\r\7\r\u00ac\n\r\f\r\16\r\u00af\13\r\3\16\3"+
		"\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\5\16\u00bb\n\16\3\16\3\16"+
		"\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16"+
		"\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16"+
		"\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16"+
		"\5\16\u00e9\n\16\3\16\3\16\3\16\7\16\u00ee\n\16\f\16\16\16\u00f1\13\16"+
		"\3\17\3\17\3\17\3\17\3\17\3\17\3\17\5\17\u00fa\n\17\3\20\3\20\3\20\5\20"+
		"\u00ff\n\20\5\20\u0101\n\20\3\21\3\21\3\21\3\21\3\21\3\21\3\21\7\21\u010a"+
		"\n\21\f\21\16\21\u010d\13\21\3\21\3\21\7\21\u0111\n\21\f\21\16\21\u0114"+
		"\13\21\3\22\3\22\5\22\u0118\n\22\3\22\3\22\3\23\3\23\3\23\3\23\5\23\u0120"+
		"\n\23\3\24\3\24\3\24\7\24\u0125\n\24\f\24\16\24\u0128\13\24\3\25\3\25"+
		"\5\25\u012c\n\25\3\26\3\26\3\27\3\27\3\30\3\30\3\30\2\3\32\31\2\4\6\b"+
		"\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\2\13\3\2\21\22\3\2\17\20\3\2"+
		"\23\24\3\2\26\30\3\2\31\32\3\2\33\36\3\2\37 \3\2(*\3\2+.\2\u0156\2\63"+
		"\3\2\2\2\4;\3\2\2\2\6=\3\2\2\2\bK\3\2\2\2\nM\3\2\2\2\fW\3\2\2\2\16a\3"+
		"\2\2\2\20i\3\2\2\2\22u\3\2\2\2\24\u00a3\3\2\2\2\26\u00a5\3\2\2\2\30\u00a8"+
		"\3\2\2\2\32\u00ba\3\2\2\2\34\u00f9\3\2\2\2\36\u00fb\3\2\2\2 \u0102\3\2"+
		"\2\2\"\u0115\3\2\2\2$\u011b\3\2\2\2&\u0121\3\2\2\2(\u012b\3\2\2\2*\u012d"+
		"\3\2\2\2,\u012f\3\2\2\2.\u0131\3\2\2\2\60\62\5\4\3\2\61\60\3\2\2\2\62"+
		"\65\3\2\2\2\63\61\3\2\2\2\63\64\3\2\2\2\64\66\3\2\2\2\65\63\3\2\2\2\66"+
		"\67\7\2\2\3\67\3\3\2\2\28<\5\6\4\29<\5\f\7\2:<\5\26\f\2;8\3\2\2\2;9\3"+
		"\2\2\2;:\3\2\2\2<\5\3\2\2\2=>\7\3\2\2>?\7\60\2\2?C\7\65\2\2@B\5\b\5\2"+
		"A@\3\2\2\2BE\3\2\2\2CA\3\2\2\2CD\3\2\2\2DF\3\2\2\2EC\3\2\2\2FG\7\66\2"+
		"\2G\7\3\2\2\2HL\5\26\f\2IL\5\f\7\2JL\5\n\6\2KH\3\2\2\2KI\3\2\2\2KJ\3\2"+
		"\2\2L\t\3\2\2\2MN\7\60\2\2NP\7\63\2\2OQ\5\16\b\2PO\3\2\2\2PQ\3\2\2\2Q"+
		"R\3\2\2\2RS\7\64\2\2ST\5\20\t\2T\13\3\2\2\2UX\5&\24\2VX\7\4\2\2WU\3\2"+
		"\2\2WV\3\2\2\2XY\3\2\2\2YZ\7\60\2\2Z\\\7\63\2\2[]\5\16\b\2\\[\3\2\2\2"+
		"\\]\3\2\2\2]^\3\2\2\2^_\7\64\2\2_`\5\20\t\2`\r\3\2\2\2af\5$\23\2bc\7\5"+
		"\2\2ce\5$\23\2db\3\2\2\2eh\3\2\2\2fd\3\2\2\2fg\3\2\2\2g\17\3\2\2\2hf\3"+
		"\2\2\2im\7\65\2\2jl\5\22\n\2kj\3\2\2\2lo\3\2\2\2mk\3\2\2\2mn\3\2\2\2n"+
		"p\3\2\2\2om\3\2\2\2pq\7\66\2\2q\21\3\2\2\2rv\5\20\t\2sv\5\24\13\2tv\5"+
		"\26\f\2ur\3\2\2\2us\3\2\2\2ut\3\2\2\2v\23\3\2\2\2w\u00a4\5\20\t\2xy\7"+
		"\6\2\2yz\7\63\2\2z{\5\32\16\2{|\7\64\2\2|\177\5\24\13\2}~\7\7\2\2~\u0080"+
		"\5\24\13\2\177}\3\2\2\2\177\u0080\3\2\2\2\u0080\u00a4\3\2\2\2\u0081\u0082"+
		"\7\b\2\2\u0082\u0084\7\63\2\2\u0083\u0085\5\32\16\2\u0084\u0083\3\2\2"+
		"\2\u0084\u0085\3\2\2\2\u0085\u0086\3\2\2\2\u0086\u0088\7\t\2\2\u0087\u0089"+
		"\5\32\16\2\u0088\u0087\3\2\2\2\u0088\u0089\3\2\2\2\u0089\u008a\3\2\2\2"+
		"\u008a\u008c\7\t\2\2\u008b\u008d\5\30\r\2\u008c\u008b\3\2\2\2\u008c\u008d"+
		"\3\2\2\2\u008d\u008e\3\2\2\2\u008e\u008f\7\64\2\2\u008f\u00a4\5\24\13"+
		"\2\u0090\u0091\7\n\2\2\u0091\u0092\7\63\2\2\u0092\u0093\5\32\16\2\u0093"+
		"\u0094\7\64\2\2\u0094\u0095\5\24\13\2\u0095\u00a4\3\2\2\2\u0096\u0098"+
		"\7\13\2\2\u0097\u0099\5\32\16\2\u0098\u0097\3\2\2\2\u0098\u0099\3\2\2"+
		"\2\u0099\u009a\3\2\2\2\u009a\u00a4\7\t\2\2\u009b\u009c\7\f\2\2\u009c\u00a4"+
		"\7\t\2\2\u009d\u009e\7\r\2\2\u009e\u00a4\7\t\2\2\u009f\u00a0\5\32\16\2"+
		"\u00a0\u00a1\7\t\2\2\u00a1\u00a4\3\2\2\2\u00a2\u00a4\7\t\2\2\u00a3w\3"+
		"\2\2\2\u00a3x\3\2\2\2\u00a3\u0081\3\2\2\2\u00a3\u0090\3\2\2\2\u00a3\u0096"+
		"\3\2\2\2\u00a3\u009b\3\2\2\2\u00a3\u009d\3\2\2\2\u00a3\u009f\3\2\2\2\u00a3"+
		"\u00a2\3\2\2\2\u00a4\25\3\2\2\2\u00a5\u00a6\5$\23\2\u00a6\u00a7\7\t\2"+
		"\2\u00a7\27\3\2\2\2\u00a8\u00ad\5\32\16\2\u00a9\u00aa\7\5\2\2\u00aa\u00ac"+
		"\5\32\16\2\u00ab\u00a9\3\2\2\2\u00ac\u00af\3\2\2\2\u00ad\u00ab\3\2\2\2"+
		"\u00ad\u00ae\3\2\2\2\u00ae\31\3\2\2\2\u00af\u00ad\3\2\2\2\u00b0\u00b1"+
		"\b\16\1\2\u00b1\u00bb\5\34\17\2\u00b2\u00b3\t\2\2\2\u00b3\u00bb\5\32\16"+
		"\21\u00b4\u00b5\t\3\2\2\u00b5\u00bb\5\32\16\20\u00b6\u00b7\t\4\2\2\u00b7"+
		"\u00bb\5\32\16\17\u00b8\u00b9\7\25\2\2\u00b9\u00bb\5\36\20\2\u00ba\u00b0"+
		"\3\2\2\2\u00ba\u00b2\3\2\2\2\u00ba\u00b4\3\2\2\2\u00ba\u00b6\3\2\2\2\u00ba"+
		"\u00b8\3\2\2\2\u00bb\u00ef\3\2\2\2\u00bc\u00bd\f\25\2\2\u00bd\u00be\7"+
		"\16\2\2\u00be\u00ee\5\32\16\26\u00bf\u00c0\f\r\2\2\u00c0\u00c1\t\5\2\2"+
		"\u00c1\u00ee\5\32\16\16\u00c2\u00c3\f\f\2\2\u00c3\u00c4\t\4\2\2\u00c4"+
		"\u00ee\5\32\16\r\u00c5\u00c6\f\13\2\2\u00c6\u00c7\t\6\2\2\u00c7\u00ee"+
		"\5\32\16\f\u00c8\u00c9\f\n\2\2\u00c9\u00ca\t\7\2\2\u00ca\u00ee\5\32\16"+
		"\13\u00cb\u00cc\f\t\2\2\u00cc\u00cd\t\b\2\2\u00cd\u00ee\5\32\16\n\u00ce"+
		"\u00cf\f\b\2\2\u00cf\u00d0\7!\2\2\u00d0\u00ee\5\32\16\t\u00d1\u00d2\f"+
		"\7\2\2\u00d2\u00d3\7\"\2\2\u00d3\u00ee\5\32\16\b\u00d4\u00d5\f\6\2\2\u00d5"+
		"\u00d6\7#\2\2\u00d6\u00ee\5\32\16\7\u00d7\u00d8\f\5\2\2\u00d8\u00d9\7"+
		"$\2\2\u00d9\u00ee\5\32\16\6\u00da\u00db\f\4\2\2\u00db\u00dc\7%\2\2\u00dc"+
		"\u00ee\5\32\16\5\u00dd\u00de\f\3\2\2\u00de\u00df\7&\2\2\u00df\u00ee\5"+
		"\32\16\3\u00e0\u00e1\f\24\2\2\u00e1\u00e2\7\61\2\2\u00e2\u00e3\5\32\16"+
		"\2\u00e3\u00e4\7\62\2\2\u00e4\u00ee\3\2\2\2\u00e5\u00e6\f\23\2\2\u00e6"+
		"\u00e8\7\63\2\2\u00e7\u00e9\5\30\r\2\u00e8\u00e7\3\2\2\2\u00e8\u00e9\3"+
		"\2\2\2\u00e9\u00ea\3\2\2\2\u00ea\u00ee\7\64\2\2\u00eb\u00ec\f\22\2\2\u00ec"+
		"\u00ee\t\3\2\2\u00ed\u00bc\3\2\2\2\u00ed\u00bf\3\2\2\2\u00ed\u00c2\3\2"+
		"\2\2\u00ed\u00c5\3\2\2\2\u00ed\u00c8\3\2\2\2\u00ed\u00cb\3\2\2\2\u00ed"+
		"\u00ce\3\2\2\2\u00ed\u00d1\3\2\2\2\u00ed\u00d4\3\2\2\2\u00ed\u00d7\3\2"+
		"\2\2\u00ed\u00da\3\2\2\2\u00ed\u00dd\3\2\2\2\u00ed\u00e0\3\2\2\2\u00ed"+
		"\u00e5\3\2\2\2\u00ed\u00eb\3\2\2\2\u00ee\u00f1\3\2\2\2\u00ef\u00ed\3\2"+
		"\2\2\u00ef\u00f0\3\2\2\2\u00f0\33\3\2\2\2\u00f1\u00ef\3\2\2\2\u00f2\u00fa"+
		"\7\60\2\2\u00f3\u00fa\5.\30\2\u00f4\u00fa\7\'\2\2\u00f5\u00f6\7\63\2\2"+
		"\u00f6\u00f7\5\32\16\2\u00f7\u00f8\7\64\2\2\u00f8\u00fa\3\2\2\2\u00f9"+
		"\u00f2\3\2\2\2\u00f9\u00f3\3\2\2\2\u00f9\u00f4\3\2\2\2\u00f9\u00f5\3\2"+
		"\2\2\u00fa\35\3\2\2\2\u00fb\u0100\5(\25\2\u00fc\u0101\5 \21\2\u00fd\u00ff"+
		"\5\"\22\2\u00fe\u00fd\3\2\2\2\u00fe\u00ff\3\2\2\2\u00ff\u0101\3\2\2\2"+
		"\u0100\u00fc\3\2\2\2\u0100\u00fe\3\2\2\2\u0101\37\3\2\2\2\u0102\u0103"+
		"\7\61\2\2\u0103\u0104\5\32\16\2\u0104\u010b\7\62\2\2\u0105\u0106\7\61"+
		"\2\2\u0106\u0107\5\32\16\2\u0107\u0108\7\62\2\2\u0108\u010a\3\2\2\2\u0109"+
		"\u0105\3\2\2\2\u010a\u010d\3\2\2\2\u010b\u0109\3\2\2\2\u010b\u010c\3\2"+
		"\2\2\u010c\u0112\3\2\2\2\u010d\u010b\3\2\2\2\u010e\u010f\7\61\2\2\u010f"+
		"\u0111\7\62\2\2\u0110\u010e\3\2\2\2\u0111\u0114\3\2\2\2\u0112\u0110\3"+
		"\2\2\2\u0112\u0113\3\2\2\2\u0113!\3\2\2\2\u0114\u0112\3\2\2\2\u0115\u0117"+
		"\7\63\2\2\u0116\u0118\5\30\r\2\u0117\u0116\3\2\2\2\u0117\u0118\3\2\2\2"+
		"\u0118\u0119\3\2\2\2\u0119\u011a\7\64\2\2\u011a#\3\2\2\2\u011b\u011c\5"+
		"&\24\2\u011c\u011f\7\60\2\2\u011d\u011e\7&\2\2\u011e\u0120\5\32\16\2\u011f"+
		"\u011d\3\2\2\2\u011f\u0120\3\2\2\2\u0120%\3\2\2\2\u0121\u0126\5(\25\2"+
		"\u0122\u0123\7\61\2\2\u0123\u0125\7\62\2\2\u0124\u0122\3\2\2\2\u0125\u0128"+
		"\3\2\2\2\u0126\u0124\3\2\2\2\u0126\u0127\3\2\2\2\u0127\'\3\2\2\2\u0128"+
		"\u0126\3\2\2\2\u0129\u012c\5,\27\2\u012a\u012c\5*\26\2\u012b\u0129\3\2"+
		"\2\2\u012b\u012a\3\2\2\2\u012c)\3\2\2\2\u012d\u012e\7\60\2\2\u012e+\3"+
		"\2\2\2\u012f\u0130\t\t\2\2\u0130-\3\2\2\2\u0131\u0132\t\n\2\2\u0132/\3"+
		"\2\2\2 \63;CKPW\\fmu\177\u0084\u0088\u008c\u0098\u00a3\u00ad\u00ba\u00e8"+
		"\u00ed\u00ef\u00f9\u00fe\u0100\u010b\u0112\u0117\u011f\u0126\u012b";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}