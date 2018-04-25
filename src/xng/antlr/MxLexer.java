// Generated from /root/GitRepo/XNG/grammar/Mx.g4 by ANTLR 4.7
package xng.antlr;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.ATN;
import org.antlr.v4.runtime.atn.ATNDeserializer;
import org.antlr.v4.runtime.atn.LexerATNSimulator;
import org.antlr.v4.runtime.atn.PredictionContextCache;
import org.antlr.v4.runtime.dfa.DFA;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class MxLexer extends Lexer {
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
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
		"T__9", "T__10", "T__11", "T__12", "T__13", "T__14", "T__15", "T__16", 
		"T__17", "T__18", "T__19", "T__20", "T__21", "T__22", "T__23", "T__24", 
		"T__25", "T__26", "T__27", "T__28", "T__29", "T__30", "T__31", "T__32", 
		"T__33", "T__34", "T__35", "T__36", "T__37", "T__38", "T__39", "NullLiteral", 
		"BoolLiteral", "IntLiteral", "StrLiteral", "DecimalLiteral", "Identifier", 
		"EscSeq", "LB", "RB", "LP", "RP", "LBB", "RBB", "COMMENT", "NEWLINE", 
		"WS"
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


	public MxLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Mx.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\29\u015b\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64\t"+
		"\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\3\2\3\2\3\2\3\2\3\2\3\2"+
		"\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3"+
		"\7\3\7\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\13"+
		"\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\r\3"+
                "\r\3\16\3\16\3\16\3\17\3\17\3\17\3\20\3\20\3\20\3\20\3\21\3\21\3\22\3" +
                "\22\3\23\3\23\3\24\3\24\3\25\3\25\3\26\3\26\3\27\3\27\3\30\3\30\3\30\3" +
		"\31\3\31\3\31\3\32\3\32\3\32\3\33\3\33\3\33\3\34\3\34\3\35\3\35\3\36\3"+
		"\36\3\36\3\37\3\37\3\37\3 \3 \3!\3!\3\"\3\"\3#\3#\3#\3$\3$\3$\3%\3%\3"+
		"&\3&\3&\3&\3&\3\'\3\'\3\'\3\'\3\'\3(\3(\3(\3(\3)\3)\3)\3)\3)\3)\3)\3*"+
		"\3*\3*\3*\3*\3+\3+\3+\3+\3+\3+\3+\3+\3+\5+\u010c\n+\3,\3,\3-\3-\3-\6-"+
		"\u0113\n-\r-\16-\u0114\5-\u0117\n-\3-\3-\3.\3.\3.\7.\u011e\n.\f.\16.\u0121"+
		"\13.\5.\u0123\n.\3/\3/\7/\u0127\n/\f/\16/\u012a\13/\3\60\3\60\3\60\3\61"+
		"\3\61\3\62\3\62\3\63\3\63\3\64\3\64\3\65\3\65\3\66\3\66\3\67\3\67\3\67"+
		"\3\67\7\67\u013f\n\67\f\67\16\67\u0142\13\67\3\67\5\67\u0145\n\67\3\67"+
		"\3\67\3\67\3\67\38\58\u014c\n8\38\68\u014f\n8\r8\168\u0150\38\38\39\6"+
		"9\u0156\n9\r9\169\u0157\39\39\3\u0140\2:\3\3\5\4\7\5\t\6\13\7\r\b\17\t"+
		"\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27"+
		"-\30/\31\61\32\63\33\65\34\67\359\36;\37= ?!A\"C#E$G%I&K\'M(O)Q*S+U,W"+
		"-Y.[/]\60_\2a\61c\62e\63g\64i\65k\66m\67o8q9\3\2\t\4\2$$^^\3\2\63;\3\2"+
		"\62;\4\2C\\c|\6\2\62;C\\aac|\5\2$$^^pp\4\2\13\f\"\"\2\u0165\2\3\3\2\2"+
		"\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3"+
		"\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2"+
		"\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2"+
		"\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2"+
		"\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3"+
		"\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\2I\3\2\2"+
		"\2\2K\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2\2Q\3\2\2\2\2S\3\2\2\2\2U\3\2\2\2\2"+
		"W\3\2\2\2\2Y\3\2\2\2\2[\3\2\2\2\2]\3\2\2\2\2a\3\2\2\2\2c\3\2\2\2\2e\3"+
		"\2\2\2\2g\3\2\2\2\2i\3\2\2\2\2k\3\2\2\2\2m\3\2\2\2\2o\3\2\2\2\2q\3\2\2"+
		"\2\3s\3\2\2\2\5y\3\2\2\2\7~\3\2\2\2\t\u0080\3\2\2\2\13\u0083\3\2\2\2\r"+
		"\u0088\3\2\2\2\17\u008c\3\2\2\2\21\u008e\3\2\2\2\23\u0094\3\2\2\2\25\u009b"+
		"\3\2\2\2\27\u00a4\3\2\2\2\31\u00aa\3\2\2\2\33\u00ac\3\2\2\2\35\u00af\3"+
                "\2\2\2\37\u00b2\3\2\2\2!\u00b6\3\2\2\2#\u00b8\3\2\2\2%\u00ba\3\2\2\2\'" +
                "\u00bc\3\2\2\2)\u00be\3\2\2\2+\u00c0\3\2\2\2-\u00c2\3\2\2\2/\u00c4\3\2" +
		"\2\2\61\u00c7\3\2\2\2\63\u00ca\3\2\2\2\65\u00cd\3\2\2\2\67\u00d0\3\2\2"+
		"\29\u00d2\3\2\2\2;\u00d4\3\2\2\2=\u00d7\3\2\2\2?\u00da\3\2\2\2A\u00dc"+
		"\3\2\2\2C\u00de\3\2\2\2E\u00e0\3\2\2\2G\u00e3\3\2\2\2I\u00e6\3\2\2\2K"+
		"\u00e8\3\2\2\2M\u00ed\3\2\2\2O\u00f2\3\2\2\2Q\u00f6\3\2\2\2S\u00fd\3\2"+
		"\2\2U\u010b\3\2\2\2W\u010d\3\2\2\2Y\u010f\3\2\2\2[\u0122\3\2\2\2]\u0124"+
		"\3\2\2\2_\u012b\3\2\2\2a\u012e\3\2\2\2c\u0130\3\2\2\2e\u0132\3\2\2\2g"+
		"\u0134\3\2\2\2i\u0136\3\2\2\2k\u0138\3\2\2\2m\u013a\3\2\2\2o\u014e\3\2"+
		"\2\2q\u0155\3\2\2\2st\7e\2\2tu\7n\2\2uv\7c\2\2vw\7u\2\2wx\7u\2\2x\4\3"+
		"\2\2\2yz\7x\2\2z{\7q\2\2{|\7k\2\2|}\7f\2\2}\6\3\2\2\2~\177\7.\2\2\177"+
		"\b\3\2\2\2\u0080\u0081\7k\2\2\u0081\u0082\7h\2\2\u0082\n\3\2\2\2\u0083"+
		"\u0084\7g\2\2\u0084\u0085\7n\2\2\u0085\u0086\7u\2\2\u0086\u0087\7g\2\2"+
		"\u0087\f\3\2\2\2\u0088\u0089\7h\2\2\u0089\u008a\7q\2\2\u008a\u008b\7t"+
		"\2\2\u008b\16\3\2\2\2\u008c\u008d\7=\2\2\u008d\20\3\2\2\2\u008e\u008f"+
		"\7y\2\2\u008f\u0090\7j\2\2\u0090\u0091\7k\2\2\u0091\u0092\7n\2\2\u0092"+
		"\u0093\7g\2\2\u0093\22\3\2\2\2\u0094\u0095\7t\2\2\u0095\u0096\7g\2\2\u0096"+
		"\u0097\7v\2\2\u0097\u0098\7w\2\2\u0098\u0099\7t\2\2\u0099\u009a\7p\2\2"+
		"\u009a\24\3\2\2\2\u009b\u009c\7e\2\2\u009c\u009d\7q\2\2\u009d\u009e\7"+
		"p\2\2\u009e\u009f\7v\2\2\u009f\u00a0\7k\2\2\u00a0\u00a1\7p\2\2\u00a1\u00a2"+
		"\7w\2\2\u00a2\u00a3\7g\2\2\u00a3\26\3\2\2\2\u00a4\u00a5\7d\2\2\u00a5\u00a6"+
		"\7t\2\2\u00a6\u00a7\7g\2\2\u00a7\u00a8\7c\2\2\u00a8\u00a9\7m\2\2\u00a9"+
		"\30\3\2\2\2\u00aa\u00ab\7\60\2\2\u00ab\32\3\2\2\2\u00ac\u00ad\7-\2\2\u00ad"+
		"\u00ae\7-\2\2\u00ae\34\3\2\2\2\u00af\u00b0\7/\2\2\u00b0\u00b1\7/\2\2\u00b1"+
                "\36\3\2\2\2\u00b2\u00b3\7p\2\2\u00b3\u00b4\7g\2\2\u00b4\u00b5\7y\2\2\u00b5" +
                " \3\2\2\2\u00b6\u00b7\7\u0080\2\2\u00b7\"\3\2\2\2\u00b8\u00b9\7#\2\2\u00b9" +
                "$\3\2\2\2\u00ba\u00bb\7-\2\2\u00bb&\3\2\2\2\u00bc\u00bd\7/\2\2\u00bd(" +
                "\3\2\2\2\u00be\u00bf\7,\2\2\u00bf*\3\2\2\2\u00c0\u00c1\7\61\2\2\u00c1" +
                ",\3\2\2\2\u00c2\u00c3\7\'\2\2\u00c3.\3\2\2\2\u00c4\u00c5\7>\2\2\u00c5" +
                "\u00c6\7>\2\2\u00c6\60\3\2\2\2\u00c7\u00c8\7@\2\2\u00c8\u00c9\7@\2\2\u00c9" +
                "\62\3\2\2\2\u00ca\u00cb\7>\2\2\u00cb\u00cc\7?\2\2\u00cc\64\3\2\2\2\u00cd" +
                "\u00ce\7@\2\2\u00ce\u00cf\7?\2\2\u00cf\66\3\2\2\2\u00d0\u00d1\7>\2\2\u00d1" +
                "8\3\2\2\2\u00d2\u00d3\7@\2\2\u00d3:\3\2\2\2\u00d4\u00d5\7?\2\2\u00d5\u00d6" +
                "\7?\2\2\u00d6<\3\2\2\2\u00d7\u00d8\7#\2\2\u00d8\u00d9\7?\2\2\u00d9>\3" +
                "\2\2\2\u00da\u00db\7(\2\2\u00db@\3\2\2\2\u00dc\u00dd\7`\2\2\u00ddB\3\2" +
                "\2\2\u00de\u00df\7~\2\2\u00dfD\3\2\2\2\u00e0\u00e1\7(\2\2\u00e1\u00e2" +
                "\7(\2\2\u00e2F\3\2\2\2\u00e3\u00e4\7~\2\2\u00e4\u00e5\7~\2\2\u00e5H\3" +
                "\2\2\2\u00e6\u00e7\7?\2\2\u00e7J\3\2\2\2\u00e8\u00e9\7v\2\2\u00e9\u00ea" +
                "\7j\2\2\u00ea\u00eb\7k\2\2\u00eb\u00ec\7u\2\2\u00ecL\3\2\2\2\u00ed\u00ee" +
                "\7d\2\2\u00ee\u00ef\7q\2\2\u00ef\u00f0\7q\2\2\u00f0\u00f1\7n\2\2\u00f1" +
                "N\3\2\2\2\u00f2\u00f3\7k\2\2\u00f3\u00f4\7p\2\2\u00f4\u00f5\7v\2\2\u00f5" +
                "P\3\2\2\2\u00f6\u00f7\7u\2\2\u00f7\u00f8\7v\2\2\u00f8\u00f9\7t\2\2\u00f9" +
                "\u00fa\7k\2\2\u00fa\u00fb\7p\2\2\u00fb\u00fc\7i\2\2\u00fcR\3\2\2\2\u00fd" +
                "\u00fe\7p\2\2\u00fe\u00ff\7w\2\2\u00ff\u0100\7n\2\2\u0100\u0101\7n\2\2" +
                "\u0101T\3\2\2\2\u0102\u0103\7v\2\2\u0103\u0104\7t\2\2\u0104\u0105\7w\2" +
                "\2\u0105\u010c\7g\2\2\u0106\u0107\7h\2\2\u0107\u0108\7c\2\2\u0108\u0109" +
                "\7n\2\2\u0109\u010a\7u\2\2\u010a\u010c\7g\2\2\u010b\u0102\3\2\2\2\u010b" +
                "\u0106\3\2\2\2\u010cV\3\2\2\2\u010d\u010e\5[.\2\u010eX\3\2\2\2\u010f\u0116" +
                "\7$\2\2\u0110\u0113\5_\60\2\u0111\u0113\n\2\2\2\u0112\u0110\3\2\2\2\u0112" +
                "\u0111\3\2\2\2\u0113\u0114\3\2\2\2\u0114\u0112\3\2\2\2\u0114\u0115\3\2" +
                "\2\2\u0115\u0117\3\2\2\2\u0116\u0112\3\2\2\2\u0116\u0117\3\2\2\2\u0117" +
                "\u0118\3\2\2\2\u0118\u0119\7$\2\2\u0119Z\3\2\2\2\u011a\u0123\7\62\2\2" +
                "\u011b\u011f\t\3\2\2\u011c\u011e\t\4\2\2\u011d\u011c\3\2\2\2\u011e\u0121" +
                "\3\2\2\2\u011f\u011d\3\2\2\2\u011f\u0120\3\2\2\2\u0120\u0123\3\2\2\2\u0121" +
                "\u011f\3\2\2\2\u0122\u011a\3\2\2\2\u0122\u011b\3\2\2\2\u0123\\\3\2\2\2" +
                "\u0124\u0128\t\5\2\2\u0125\u0127\t\6\2\2\u0126\u0125\3\2\2\2\u0127\u012a" +
                "\3\2\2\2\u0128\u0126\3\2\2\2\u0128\u0129\3\2\2\2\u0129^\3\2\2\2\u012a" +
                "\u0128\3\2\2\2\u012b\u012c\7^\2\2\u012c\u012d\t\7\2\2\u012d`\3\2\2\2\u012e" +
                "\u012f\7]\2\2\u012fb\3\2\2\2\u0130\u0131\7_\2\2\u0131d\3\2\2\2\u0132\u0133" +
                "\7*\2\2\u0133f\3\2\2\2\u0134\u0135\7+\2\2\u0135h\3\2\2\2\u0136\u0137\7" +
                "}\2\2\u0137j\3\2\2\2\u0138\u0139\7\177\2\2\u0139l\3\2\2\2\u013a\u013b" +
                "\7\61\2\2\u013b\u013c\7\61\2\2\u013c\u0140\3\2\2\2\u013d\u013f\13\2\2" +
                "\2\u013e\u013d\3\2\2\2\u013f\u0142\3\2\2\2\u0140\u0141\3\2\2\2\u0140\u013e" +
                "\3\2\2\2\u0141\u0144\3\2\2\2\u0142\u0140\3\2\2\2\u0143\u0145\7\17\2\2" +
                "\u0144\u0143\3\2\2\2\u0144\u0145\3\2\2\2\u0145\u0146\3\2\2\2\u0146\u0147" +
                "\7\f\2\2\u0147\u0148\3\2\2\2\u0148\u0149\b\67\2\2\u0149n\3\2\2\2\u014a" +
                "\u014c\7\17\2\2\u014b\u014a\3\2\2\2\u014b\u014c\3\2\2\2\u014c\u014d\3" +
                "\2\2\2\u014d\u014f\7\f\2\2\u014e\u014b\3\2\2\2\u014f\u0150\3\2\2\2\u0150" +
                "\u014e\3\2\2\2\u0150\u0151\3\2\2\2\u0151\u0152\3\2\2\2\u0152\u0153\b8" +
                "\2\2\u0153p\3\2\2\2\u0154\u0156\t\b\2\2\u0155\u0154\3\2\2\2\u0156\u0157" +
                "\3\2\2\2\u0157\u0155\3\2\2\2\u0157\u0158\3\2\2\2\u0158\u0159\3\2\2\2\u0159" +
                "\u015a\b9\3\2\u015ar\3\2\2\2\17\2\u010b\u0112\u0114\u0116\u011f\u0122" +
                "\u0128\u0140\u0144\u014b\u0150\u0157\4\b\2\2\2\3\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}