// Generated from C:\SelfProgram\ANTLRWORK\WS\Gesture.g4 by ANTLR 4.1
package gesture.parser;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class GestureParser extends Parser {
	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		UP=1, RIGHT_UP=2, RIGHT=3, RIGHT_DOWN=4, DOWN=5, LEFT_DOWN=6, LEFT=7, 
		LEFT_UP=8, WS=9;
	public static final String[] tokenNames = {
		"<INVALID>", "'1'", "'2'", "'3'", "'4'", "'5'", "'6'", "'7'", "'8'", "WS"
	};
	public static final int
		RULE_gesture = 0, RULE_peak = 1, RULE_valley = 2, RULE_dru_semicircle = 3, 
		RULE_ur_qudrant = 4, RULE_dr_qudrant = 5, RULE_lu_qudrant = 6, RULE_ru_qudrant = 7, 
		RULE_rul_semicircle = 8, RULE_up = 9, RULE_down = 10, RULE_left = 11, 
		RULE_right = 12, RULE_left_up = 13, RULE_right_up = 14, RULE_left_down = 15, 
		RULE_right_down = 16, RULE_right_up_arc = 17, RULE_up_right_arc = 18, 
		RULE_down_right_arc = 19, RULE_right_down_arc = 20, RULE_down_left_arc = 21, 
		RULE_left_down_arc = 22, RULE_left_up_arc = 23, RULE_up_left_arc = 24;
	public static final String[] ruleNames = {
		"gesture", "peak", "valley", "dru_semicircle", "ur_qudrant", "dr_qudrant", 
		"lu_qudrant", "ru_qudrant", "rul_semicircle", "up", "down", "left", "right", 
		"left_up", "right_up", "left_down", "right_down", "right_up_arc", "up_right_arc", 
		"down_right_arc", "right_down_arc", "down_left_arc", "left_down_arc", 
		"left_up_arc", "up_left_arc"
	};

	@Override
	public String getGrammarFileName() { return "Gesture.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public GestureParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class GestureContext extends ParserRuleContext {
		public RightContext right() {
			return getRuleContext(RightContext.class,0);
		}
		public Ur_qudrantContext ur_qudrant() {
			return getRuleContext(Ur_qudrantContext.class,0);
		}
		public Dr_qudrantContext dr_qudrant() {
			return getRuleContext(Dr_qudrantContext.class,0);
		}
		public Rul_semicircleContext rul_semicircle() {
			return getRuleContext(Rul_semicircleContext.class,0);
		}
		public Dru_semicircleContext dru_semicircle() {
			return getRuleContext(Dru_semicircleContext.class,0);
		}
		public Ru_qudrantContext ru_qudrant() {
			return getRuleContext(Ru_qudrantContext.class,0);
		}
		public Right_upContext right_up() {
			return getRuleContext(Right_upContext.class,0);
		}
		public ValleyContext valley() {
			return getRuleContext(ValleyContext.class,0);
		}
		public Lu_qudrantContext lu_qudrant() {
			return getRuleContext(Lu_qudrantContext.class,0);
		}
		public LeftContext left() {
			return getRuleContext(LeftContext.class,0);
		}
		public PeakContext peak() {
			return getRuleContext(PeakContext.class,0);
		}
		public GestureContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_gesture; }
	}

	public final GestureContext gesture() throws RecognitionException {
		GestureContext _localctx = new GestureContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_gesture);
		try {
			setState(61);
			switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(50); left();
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(51); right();
				}
				break;

			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(52); right_up();
				}
				break;

			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(53); peak();
				}
				break;

			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(54); valley();
				}
				break;

			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(55); dru_semicircle();
				}
				break;

			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(56); ur_qudrant();
				}
				break;

			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(57); dr_qudrant();
				}
				break;

			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(58); lu_qudrant();
				}
				break;

			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(59); ru_qudrant();
				}
				break;

			case 11:
				enterOuterAlt(_localctx, 11);
				{
				setState(60); rul_semicircle();
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

	public static class PeakContext extends ParserRuleContext {
		public Right_downContext right_down() {
			return getRuleContext(Right_downContext.class,0);
		}
		public Right_upContext right_up() {
			return getRuleContext(Right_upContext.class,0);
		}
		public PeakContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_peak; }
	}

	public final PeakContext peak() throws RecognitionException {
		PeakContext _localctx = new PeakContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_peak);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(63); right_up();
			setState(64); right_down();
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

	public static class ValleyContext extends ParserRuleContext {
		public Right_downContext right_down() {
			return getRuleContext(Right_downContext.class,0);
		}
		public Right_upContext right_up() {
			return getRuleContext(Right_upContext.class,0);
		}
		public ValleyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_valley; }
	}

	public final ValleyContext valley() throws RecognitionException {
		ValleyContext _localctx = new ValleyContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_valley);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(66); right_down();
			setState(67); right_up();
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

	public static class Dru_semicircleContext extends ParserRuleContext {
		public List<RightContext> right() {
			return getRuleContexts(RightContext.class);
		}
		public Right_up_arcContext right_up_arc() {
			return getRuleContext(Right_up_arcContext.class,0);
		}
		public Down_right_arcContext down_right_arc() {
			return getRuleContext(Down_right_arcContext.class,0);
		}
		public RightContext right(int i) {
			return getRuleContext(RightContext.class,i);
		}
		public LeftContext left() {
			return getRuleContext(LeftContext.class,0);
		}
		public Dru_semicircleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_dru_semicircle; }
	}

	public final Dru_semicircleContext dru_semicircle() throws RecognitionException {
		Dru_semicircleContext _localctx = new Dru_semicircleContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_dru_semicircle);
		int _la;
		try {
			setState(79);
			switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				{
				setState(69); down_right_arc();
				setState(70); right_up_arc();
				}
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				{
				setState(75);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==RIGHT) {
					{
					{
					setState(72); right();
					}
					}
					setState(77);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(78); left();
				}
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

	public static class Ur_qudrantContext extends ParserRuleContext {
		public Up_right_arcContext up_right_arc() {
			return getRuleContext(Up_right_arcContext.class,0);
		}
		public Ur_qudrantContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ur_qudrant; }
	}

	public final Ur_qudrantContext ur_qudrant() throws RecognitionException {
		Ur_qudrantContext _localctx = new Ur_qudrantContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_ur_qudrant);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(81); up_right_arc();
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

	public static class Dr_qudrantContext extends ParserRuleContext {
		public Down_right_arcContext down_right_arc() {
			return getRuleContext(Down_right_arcContext.class,0);
		}
		public Dr_qudrantContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_dr_qudrant; }
	}

	public final Dr_qudrantContext dr_qudrant() throws RecognitionException {
		Dr_qudrantContext _localctx = new Dr_qudrantContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_dr_qudrant);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(83); down_right_arc();
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

	public static class Lu_qudrantContext extends ParserRuleContext {
		public Left_up_arcContext left_up_arc() {
			return getRuleContext(Left_up_arcContext.class,0);
		}
		public Lu_qudrantContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_lu_qudrant; }
	}

	public final Lu_qudrantContext lu_qudrant() throws RecognitionException {
		Lu_qudrantContext _localctx = new Lu_qudrantContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_lu_qudrant);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(85); left_up_arc();
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

	public static class Ru_qudrantContext extends ParserRuleContext {
		public Right_up_arcContext right_up_arc() {
			return getRuleContext(Right_up_arcContext.class,0);
		}
		public Ru_qudrantContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ru_qudrant; }
	}

	public final Ru_qudrantContext ru_qudrant() throws RecognitionException {
		Ru_qudrantContext _localctx = new Ru_qudrantContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_ru_qudrant);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(87); right_up_arc();
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

	public static class Rul_semicircleContext extends ParserRuleContext {
		public List<UpContext> up() {
			return getRuleContexts(UpContext.class);
		}
		public DownContext down() {
			return getRuleContext(DownContext.class,0);
		}
		public UpContext up(int i) {
			return getRuleContext(UpContext.class,i);
		}
		public Right_up_arcContext right_up_arc() {
			return getRuleContext(Right_up_arcContext.class,0);
		}
		public Up_left_arcContext up_left_arc() {
			return getRuleContext(Up_left_arcContext.class,0);
		}
		public Rul_semicircleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_rul_semicircle; }
	}

	public final Rul_semicircleContext rul_semicircle() throws RecognitionException {
		Rul_semicircleContext _localctx = new Rul_semicircleContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_rul_semicircle);
		int _la;
		try {
			setState(99);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				{
				setState(89); right_up_arc();
				setState(90); up_left_arc();
				}
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				{
				setState(95);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==UP) {
					{
					{
					setState(92); up();
					}
					}
					setState(97);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(98); down();
				}
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

	public static class UpContext extends ParserRuleContext {
		public TerminalNode UP(int i) {
			return getToken(GestureParser.UP, i);
		}
		public List<TerminalNode> UP() { return getTokens(GestureParser.UP); }
		public UpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_up; }
	}

	public final UpContext up() throws RecognitionException {
		UpContext _localctx = new UpContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_up);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(102); 
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,5,_ctx);
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(101); match(UP);
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(104); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,5,_ctx);
			} while ( _alt!=2 && _alt!=-1 );
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

	public static class DownContext extends ParserRuleContext {
		public TerminalNode DOWN(int i) {
			return getToken(GestureParser.DOWN, i);
		}
		public List<TerminalNode> DOWN() { return getTokens(GestureParser.DOWN); }
		public DownContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_down; }
	}

	public final DownContext down() throws RecognitionException {
		DownContext _localctx = new DownContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_down);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(107); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(106); match(DOWN);
				}
				}
				setState(109); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==DOWN );
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

	public static class LeftContext extends ParserRuleContext {
		public List<TerminalNode> LEFT() { return getTokens(GestureParser.LEFT); }
		public TerminalNode LEFT(int i) {
			return getToken(GestureParser.LEFT, i);
		}
		public LeftContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_left; }
	}

	public final LeftContext left() throws RecognitionException {
		LeftContext _localctx = new LeftContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_left);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(112); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(111); match(LEFT);
				}
				}
				setState(114); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==LEFT );
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

	public static class RightContext extends ParserRuleContext {
		public List<TerminalNode> RIGHT() { return getTokens(GestureParser.RIGHT); }
		public TerminalNode RIGHT(int i) {
			return getToken(GestureParser.RIGHT, i);
		}
		public RightContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_right; }
	}

	public final RightContext right() throws RecognitionException {
		RightContext _localctx = new RightContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_right);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(117); 
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(116); match(RIGHT);
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(119); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
			} while ( _alt!=2 && _alt!=-1 );
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

	public static class Left_upContext extends ParserRuleContext {
		public List<TerminalNode> LEFT_UP() { return getTokens(GestureParser.LEFT_UP); }
		public TerminalNode LEFT_UP(int i) {
			return getToken(GestureParser.LEFT_UP, i);
		}
		public Left_upContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_left_up; }
	}

	public final Left_upContext left_up() throws RecognitionException {
		Left_upContext _localctx = new Left_upContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_left_up);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(122); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(121); match(LEFT_UP);
				}
				}
				setState(124); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==LEFT_UP );
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

	public static class Right_upContext extends ParserRuleContext {
		public List<TerminalNode> RIGHT_UP() { return getTokens(GestureParser.RIGHT_UP); }
		public TerminalNode RIGHT_UP(int i) {
			return getToken(GestureParser.RIGHT_UP, i);
		}
		public Right_upContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_right_up; }
	}

	public final Right_upContext right_up() throws RecognitionException {
		Right_upContext _localctx = new Right_upContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_right_up);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(127); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(126); match(RIGHT_UP);
				}
				}
				setState(129); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==RIGHT_UP );
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

	public static class Left_downContext extends ParserRuleContext {
		public TerminalNode LEFT_DOWN(int i) {
			return getToken(GestureParser.LEFT_DOWN, i);
		}
		public List<TerminalNode> LEFT_DOWN() { return getTokens(GestureParser.LEFT_DOWN); }
		public Left_downContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_left_down; }
	}

	public final Left_downContext left_down() throws RecognitionException {
		Left_downContext _localctx = new Left_downContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_left_down);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(132); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(131); match(LEFT_DOWN);
				}
				}
				setState(134); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==LEFT_DOWN );
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

	public static class Right_downContext extends ParserRuleContext {
		public List<TerminalNode> RIGHT_DOWN() { return getTokens(GestureParser.RIGHT_DOWN); }
		public TerminalNode RIGHT_DOWN(int i) {
			return getToken(GestureParser.RIGHT_DOWN, i);
		}
		public Right_downContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_right_down; }
	}

	public final Right_downContext right_down() throws RecognitionException {
		Right_downContext _localctx = new Right_downContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_right_down);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(137); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(136); match(RIGHT_DOWN);
				}
				}
				setState(139); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==RIGHT_DOWN );
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

	public static class Right_up_arcContext extends ParserRuleContext {
		public List<TerminalNode> LEFT() { return getTokens(GestureParser.LEFT); }
		public TerminalNode UP(int i) {
			return getToken(GestureParser.UP, i);
		}
		public List<TerminalNode> UP() { return getTokens(GestureParser.UP); }
		public TerminalNode LEFT(int i) {
			return getToken(GestureParser.LEFT, i);
		}
		public List<TerminalNode> LEFT_UP() { return getTokens(GestureParser.LEFT_UP); }
		public TerminalNode LEFT_UP(int i) {
			return getToken(GestureParser.LEFT_UP, i);
		}
		public Right_up_arcContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_right_up_arc; }
	}

	public final Right_up_arcContext right_up_arc() throws RecognitionException {
		Right_up_arcContext _localctx = new Right_up_arcContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_right_up_arc);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(142); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(141); match(UP);
				}
				}
				setState(144); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==UP );
			setState(147); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(146); match(LEFT_UP);
				}
				}
				setState(149); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==LEFT_UP );
			setState(152); 
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,15,_ctx);
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(151); match(LEFT);
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(154); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,15,_ctx);
			} while ( _alt!=2 && _alt!=-1 );
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

	public static class Up_right_arcContext extends ParserRuleContext {
		public List<TerminalNode> RIGHT_DOWN() { return getTokens(GestureParser.RIGHT_DOWN); }
		public List<TerminalNode> RIGHT() { return getTokens(GestureParser.RIGHT); }
		public TerminalNode RIGHT(int i) {
			return getToken(GestureParser.RIGHT, i);
		}
		public TerminalNode RIGHT_DOWN(int i) {
			return getToken(GestureParser.RIGHT_DOWN, i);
		}
		public TerminalNode DOWN(int i) {
			return getToken(GestureParser.DOWN, i);
		}
		public List<TerminalNode> DOWN() { return getTokens(GestureParser.DOWN); }
		public Up_right_arcContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_up_right_arc; }
	}

	public final Up_right_arcContext up_right_arc() throws RecognitionException {
		Up_right_arcContext _localctx = new Up_right_arcContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_up_right_arc);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(157); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(156); match(RIGHT);
				}
				}
				setState(159); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==RIGHT );
			setState(162); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(161); match(RIGHT_DOWN);
				}
				}
				setState(164); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==RIGHT_DOWN );
			setState(167); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(166); match(DOWN);
				}
				}
				setState(169); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==DOWN );
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

	public static class Down_right_arcContext extends ParserRuleContext {
		public TerminalNode UP(int i) {
			return getToken(GestureParser.UP, i);
		}
		public List<TerminalNode> RIGHT_UP() { return getTokens(GestureParser.RIGHT_UP); }
		public TerminalNode RIGHT_UP(int i) {
			return getToken(GestureParser.RIGHT_UP, i);
		}
		public List<TerminalNode> UP() { return getTokens(GestureParser.UP); }
		public List<TerminalNode> RIGHT() { return getTokens(GestureParser.RIGHT); }
		public TerminalNode RIGHT(int i) {
			return getToken(GestureParser.RIGHT, i);
		}
		public Down_right_arcContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_down_right_arc; }
	}

	public final Down_right_arcContext down_right_arc() throws RecognitionException {
		Down_right_arcContext _localctx = new Down_right_arcContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_down_right_arc);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(172); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(171); match(RIGHT);
				}
				}
				setState(174); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==RIGHT );
			setState(177); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(176); match(RIGHT_UP);
				}
				}
				setState(179); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==RIGHT_UP );
			setState(182); 
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,21,_ctx);
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(181); match(UP);
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(184); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,21,_ctx);
			} while ( _alt!=2 && _alt!=-1 );
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

	public static class Right_down_arcContext extends ParserRuleContext {
		public TerminalNode LEFT_DOWN(int i) {
			return getToken(GestureParser.LEFT_DOWN, i);
		}
		public List<TerminalNode> LEFT() { return getTokens(GestureParser.LEFT); }
		public List<TerminalNode> LEFT_DOWN() { return getTokens(GestureParser.LEFT_DOWN); }
		public TerminalNode LEFT(int i) {
			return getToken(GestureParser.LEFT, i);
		}
		public TerminalNode DOWN(int i) {
			return getToken(GestureParser.DOWN, i);
		}
		public List<TerminalNode> DOWN() { return getTokens(GestureParser.DOWN); }
		public Right_down_arcContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_right_down_arc; }
	}

	public final Right_down_arcContext right_down_arc() throws RecognitionException {
		Right_down_arcContext _localctx = new Right_down_arcContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_right_down_arc);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(187); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(186); match(DOWN);
				}
				}
				setState(189); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==DOWN );
			setState(192); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(191); match(LEFT_DOWN);
				}
				}
				setState(194); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==LEFT_DOWN );
			setState(197); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(196); match(LEFT);
				}
				}
				setState(199); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==LEFT );
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

	public static class Down_left_arcContext extends ParserRuleContext {
		public List<TerminalNode> LEFT() { return getTokens(GestureParser.LEFT); }
		public TerminalNode UP(int i) {
			return getToken(GestureParser.UP, i);
		}
		public List<TerminalNode> UP() { return getTokens(GestureParser.UP); }
		public TerminalNode LEFT(int i) {
			return getToken(GestureParser.LEFT, i);
		}
		public List<TerminalNode> LEFT_UP() { return getTokens(GestureParser.LEFT_UP); }
		public TerminalNode LEFT_UP(int i) {
			return getToken(GestureParser.LEFT_UP, i);
		}
		public Down_left_arcContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_down_left_arc; }
	}

	public final Down_left_arcContext down_left_arc() throws RecognitionException {
		Down_left_arcContext _localctx = new Down_left_arcContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_down_left_arc);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(202); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(201); match(LEFT);
				}
				}
				setState(204); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==LEFT );
			setState(207); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(206); match(LEFT_UP);
				}
				}
				setState(209); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==LEFT_UP );
			setState(212); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(211); match(UP);
				}
				}
				setState(214); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==UP );
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

	public static class Left_down_arcContext extends ParserRuleContext {
		public List<TerminalNode> RIGHT_DOWN() { return getTokens(GestureParser.RIGHT_DOWN); }
		public TerminalNode DOWN(int i) {
			return getToken(GestureParser.DOWN, i);
		}
		public TerminalNode RIGHT_DOWN(int i) {
			return getToken(GestureParser.RIGHT_DOWN, i);
		}
		public List<TerminalNode> RIGHT() { return getTokens(GestureParser.RIGHT); }
		public TerminalNode RIGHT(int i) {
			return getToken(GestureParser.RIGHT, i);
		}
		public List<TerminalNode> DOWN() { return getTokens(GestureParser.DOWN); }
		public Left_down_arcContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_left_down_arc; }
	}

	public final Left_down_arcContext left_down_arc() throws RecognitionException {
		Left_down_arcContext _localctx = new Left_down_arcContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_left_down_arc);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(217); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(216); match(DOWN);
				}
				}
				setState(219); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==DOWN );
			setState(222); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(221); match(RIGHT_DOWN);
				}
				}
				setState(224); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==RIGHT_DOWN );
			setState(227); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(226); match(RIGHT);
				}
				}
				setState(229); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==RIGHT );
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

	public static class Left_up_arcContext extends ParserRuleContext {
		public TerminalNode UP(int i) {
			return getToken(GestureParser.UP, i);
		}
		public List<TerminalNode> RIGHT_UP() { return getTokens(GestureParser.RIGHT_UP); }
		public TerminalNode RIGHT_UP(int i) {
			return getToken(GestureParser.RIGHT_UP, i);
		}
		public List<TerminalNode> UP() { return getTokens(GestureParser.UP); }
		public List<TerminalNode> RIGHT() { return getTokens(GestureParser.RIGHT); }
		public TerminalNode RIGHT(int i) {
			return getToken(GestureParser.RIGHT, i);
		}
		public Left_up_arcContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_left_up_arc; }
	}

	public final Left_up_arcContext left_up_arc() throws RecognitionException {
		Left_up_arcContext _localctx = new Left_up_arcContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_left_up_arc);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(232); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(231); match(UP);
				}
				}
				setState(234); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==UP );
			setState(237); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(236); match(RIGHT_UP);
				}
				}
				setState(239); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==RIGHT_UP );
			setState(242); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(241); match(RIGHT);
				}
				}
				setState(244); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==RIGHT );
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

	public static class Up_left_arcContext extends ParserRuleContext {
		public List<TerminalNode> LEFT() { return getTokens(GestureParser.LEFT); }
		public TerminalNode LEFT_DOWN(int i) {
			return getToken(GestureParser.LEFT_DOWN, i);
		}
		public List<TerminalNode> LEFT_DOWN() { return getTokens(GestureParser.LEFT_DOWN); }
		public TerminalNode LEFT(int i) {
			return getToken(GestureParser.LEFT, i);
		}
		public TerminalNode DOWN(int i) {
			return getToken(GestureParser.DOWN, i);
		}
		public List<TerminalNode> DOWN() { return getTokens(GestureParser.DOWN); }
		public Up_left_arcContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_up_left_arc; }
	}

	public final Up_left_arcContext up_left_arc() throws RecognitionException {
		Up_left_arcContext _localctx = new Up_left_arcContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_up_left_arc);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(247); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(246); match(LEFT);
				}
				}
				setState(249); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==LEFT );
			setState(252); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(251); match(LEFT_DOWN);
				}
				}
				setState(254); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==LEFT_DOWN );
			setState(257); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(256); match(DOWN);
				}
				}
				setState(259); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==DOWN );
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

	public static final String _serializedATN =
		"\3\uacf5\uee8c\u4f5d\u8b0d\u4a45\u78bd\u1b2f\u3378\3\13\u0108\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\5\2@\n\2\3\3\3"+
		"\3\3\3\3\4\3\4\3\4\3\5\3\5\3\5\3\5\7\5L\n\5\f\5\16\5O\13\5\3\5\5\5R\n"+
		"\5\3\6\3\6\3\7\3\7\3\b\3\b\3\t\3\t\3\n\3\n\3\n\3\n\7\n`\n\n\f\n\16\nc"+
		"\13\n\3\n\5\nf\n\n\3\13\6\13i\n\13\r\13\16\13j\3\f\6\fn\n\f\r\f\16\fo"+
		"\3\r\6\rs\n\r\r\r\16\rt\3\16\6\16x\n\16\r\16\16\16y\3\17\6\17}\n\17\r"+
		"\17\16\17~\3\20\6\20\u0082\n\20\r\20\16\20\u0083\3\21\6\21\u0087\n\21"+
		"\r\21\16\21\u0088\3\22\6\22\u008c\n\22\r\22\16\22\u008d\3\23\6\23\u0091"+
		"\n\23\r\23\16\23\u0092\3\23\6\23\u0096\n\23\r\23\16\23\u0097\3\23\6\23"+
		"\u009b\n\23\r\23\16\23\u009c\3\24\6\24\u00a0\n\24\r\24\16\24\u00a1\3\24"+
		"\6\24\u00a5\n\24\r\24\16\24\u00a6\3\24\6\24\u00aa\n\24\r\24\16\24\u00ab"+
		"\3\25\6\25\u00af\n\25\r\25\16\25\u00b0\3\25\6\25\u00b4\n\25\r\25\16\25"+
		"\u00b5\3\25\6\25\u00b9\n\25\r\25\16\25\u00ba\3\26\6\26\u00be\n\26\r\26"+
		"\16\26\u00bf\3\26\6\26\u00c3\n\26\r\26\16\26\u00c4\3\26\6\26\u00c8\n\26"+
		"\r\26\16\26\u00c9\3\27\6\27\u00cd\n\27\r\27\16\27\u00ce\3\27\6\27\u00d2"+
		"\n\27\r\27\16\27\u00d3\3\27\6\27\u00d7\n\27\r\27\16\27\u00d8\3\30\6\30"+
		"\u00dc\n\30\r\30\16\30\u00dd\3\30\6\30\u00e1\n\30\r\30\16\30\u00e2\3\30"+
		"\6\30\u00e6\n\30\r\30\16\30\u00e7\3\31\6\31\u00eb\n\31\r\31\16\31\u00ec"+
		"\3\31\6\31\u00f0\n\31\r\31\16\31\u00f1\3\31\6\31\u00f5\n\31\r\31\16\31"+
		"\u00f6\3\32\6\32\u00fa\n\32\r\32\16\32\u00fb\3\32\6\32\u00ff\n\32\r\32"+
		"\16\32\u0100\3\32\6\32\u0104\n\32\r\32\16\32\u0105\3\32\2\33\2\4\6\b\n"+
		"\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\60\62\2\2\u011c\2?\3\2\2\2\4A\3"+
		"\2\2\2\6D\3\2\2\2\bQ\3\2\2\2\nS\3\2\2\2\fU\3\2\2\2\16W\3\2\2\2\20Y\3\2"+
		"\2\2\22e\3\2\2\2\24h\3\2\2\2\26m\3\2\2\2\30r\3\2\2\2\32w\3\2\2\2\34|\3"+
		"\2\2\2\36\u0081\3\2\2\2 \u0086\3\2\2\2\"\u008b\3\2\2\2$\u0090\3\2\2\2"+
		"&\u009f\3\2\2\2(\u00ae\3\2\2\2*\u00bd\3\2\2\2,\u00cc\3\2\2\2.\u00db\3"+
		"\2\2\2\60\u00ea\3\2\2\2\62\u00f9\3\2\2\2\64@\5\30\r\2\65@\5\32\16\2\66"+
		"@\5\36\20\2\67@\5\4\3\28@\5\6\4\29@\5\b\5\2:@\5\n\6\2;@\5\f\7\2<@\5\16"+
		"\b\2=@\5\20\t\2>@\5\22\n\2?\64\3\2\2\2?\65\3\2\2\2?\66\3\2\2\2?\67\3\2"+
		"\2\2?8\3\2\2\2?9\3\2\2\2?:\3\2\2\2?;\3\2\2\2?<\3\2\2\2?=\3\2\2\2?>\3\2"+
		"\2\2@\3\3\2\2\2AB\5\36\20\2BC\5\"\22\2C\5\3\2\2\2DE\5\"\22\2EF\5\36\20"+
		"\2F\7\3\2\2\2GH\5(\25\2HI\5$\23\2IR\3\2\2\2JL\5\32\16\2KJ\3\2\2\2LO\3"+
		"\2\2\2MK\3\2\2\2MN\3\2\2\2NP\3\2\2\2OM\3\2\2\2PR\5\30\r\2QG\3\2\2\2QM"+
		"\3\2\2\2R\t\3\2\2\2ST\5&\24\2T\13\3\2\2\2UV\5(\25\2V\r\3\2\2\2WX\5\60"+
		"\31\2X\17\3\2\2\2YZ\5$\23\2Z\21\3\2\2\2[\\\5$\23\2\\]\5\62\32\2]f\3\2"+
		"\2\2^`\5\24\13\2_^\3\2\2\2`c\3\2\2\2a_\3\2\2\2ab\3\2\2\2bd\3\2\2\2ca\3"+
		"\2\2\2df\5\26\f\2e[\3\2\2\2ea\3\2\2\2f\23\3\2\2\2gi\7\3\2\2hg\3\2\2\2"+
		"ij\3\2\2\2jh\3\2\2\2jk\3\2\2\2k\25\3\2\2\2ln\7\7\2\2ml\3\2\2\2no\3\2\2"+
		"\2om\3\2\2\2op\3\2\2\2p\27\3\2\2\2qs\7\t\2\2rq\3\2\2\2st\3\2\2\2tr\3\2"+
		"\2\2tu\3\2\2\2u\31\3\2\2\2vx\7\5\2\2wv\3\2\2\2xy\3\2\2\2yw\3\2\2\2yz\3"+
		"\2\2\2z\33\3\2\2\2{}\7\n\2\2|{\3\2\2\2}~\3\2\2\2~|\3\2\2\2~\177\3\2\2"+
		"\2\177\35\3\2\2\2\u0080\u0082\7\4\2\2\u0081\u0080\3\2\2\2\u0082\u0083"+
		"\3\2\2\2\u0083\u0081\3\2\2\2\u0083\u0084\3\2\2\2\u0084\37\3\2\2\2\u0085"+
		"\u0087\7\b\2\2\u0086\u0085\3\2\2\2\u0087\u0088\3\2\2\2\u0088\u0086\3\2"+
		"\2\2\u0088\u0089\3\2\2\2\u0089!\3\2\2\2\u008a\u008c\7\6\2\2\u008b\u008a"+
		"\3\2\2\2\u008c\u008d\3\2\2\2\u008d\u008b\3\2\2\2\u008d\u008e\3\2\2\2\u008e"+
		"#\3\2\2\2\u008f\u0091\7\3\2\2\u0090\u008f\3\2\2\2\u0091\u0092\3\2\2\2"+
		"\u0092\u0090\3\2\2\2\u0092\u0093\3\2\2\2\u0093\u0095\3\2\2\2\u0094\u0096"+
		"\7\n\2\2\u0095\u0094\3\2\2\2\u0096\u0097\3\2\2\2\u0097\u0095\3\2\2\2\u0097"+
		"\u0098\3\2\2\2\u0098\u009a\3\2\2\2\u0099\u009b\7\t\2\2\u009a\u0099\3\2"+
		"\2\2\u009b\u009c\3\2\2\2\u009c\u009a\3\2\2\2\u009c\u009d\3\2\2\2\u009d"+
		"%\3\2\2\2\u009e\u00a0\7\5\2\2\u009f\u009e\3\2\2\2\u00a0\u00a1\3\2\2\2"+
		"\u00a1\u009f\3\2\2\2\u00a1\u00a2\3\2\2\2\u00a2\u00a4\3\2\2\2\u00a3\u00a5"+
		"\7\6\2\2\u00a4\u00a3\3\2\2\2\u00a5\u00a6\3\2\2\2\u00a6\u00a4\3\2\2\2\u00a6"+
		"\u00a7\3\2\2\2\u00a7\u00a9\3\2\2\2\u00a8\u00aa\7\7\2\2\u00a9\u00a8\3\2"+
		"\2\2\u00aa\u00ab\3\2\2\2\u00ab\u00a9\3\2\2\2\u00ab\u00ac\3\2\2\2\u00ac"+
		"\'\3\2\2\2\u00ad\u00af\7\5\2\2\u00ae\u00ad\3\2\2\2\u00af\u00b0\3\2\2\2"+
		"\u00b0\u00ae\3\2\2\2\u00b0\u00b1\3\2\2\2\u00b1\u00b3\3\2\2\2\u00b2\u00b4"+
		"\7\4\2\2\u00b3\u00b2\3\2\2\2\u00b4\u00b5\3\2\2\2\u00b5\u00b3\3\2\2\2\u00b5"+
		"\u00b6\3\2\2\2\u00b6\u00b8\3\2\2\2\u00b7\u00b9\7\3\2\2\u00b8\u00b7\3\2"+
		"\2\2\u00b9\u00ba\3\2\2\2\u00ba\u00b8\3\2\2\2\u00ba\u00bb\3\2\2\2\u00bb"+
		")\3\2\2\2\u00bc\u00be\7\7\2\2\u00bd\u00bc\3\2\2\2\u00be\u00bf\3\2\2\2"+
		"\u00bf\u00bd\3\2\2\2\u00bf\u00c0\3\2\2\2\u00c0\u00c2\3\2\2\2\u00c1\u00c3"+
		"\7\b\2\2\u00c2\u00c1\3\2\2\2\u00c3\u00c4\3\2\2\2\u00c4\u00c2\3\2\2\2\u00c4"+
		"\u00c5\3\2\2\2\u00c5\u00c7\3\2\2\2\u00c6\u00c8\7\t\2\2\u00c7\u00c6\3\2"+
		"\2\2\u00c8\u00c9\3\2\2\2\u00c9\u00c7\3\2\2\2\u00c9\u00ca\3\2\2\2\u00ca"+
		"+\3\2\2\2\u00cb\u00cd\7\t\2\2\u00cc\u00cb\3\2\2\2\u00cd\u00ce\3\2\2\2"+
		"\u00ce\u00cc\3\2\2\2\u00ce\u00cf\3\2\2\2\u00cf\u00d1\3\2\2\2\u00d0\u00d2"+
		"\7\n\2\2\u00d1\u00d0\3\2\2\2\u00d2\u00d3\3\2\2\2\u00d3\u00d1\3\2\2\2\u00d3"+
		"\u00d4\3\2\2\2\u00d4\u00d6\3\2\2\2\u00d5\u00d7\7\3\2\2\u00d6\u00d5\3\2"+
		"\2\2\u00d7\u00d8\3\2\2\2\u00d8\u00d6\3\2\2\2\u00d8\u00d9\3\2\2\2\u00d9"+
		"-\3\2\2\2\u00da\u00dc\7\7\2\2\u00db\u00da\3\2\2\2\u00dc\u00dd\3\2\2\2"+
		"\u00dd\u00db\3\2\2\2\u00dd\u00de\3\2\2\2\u00de\u00e0\3\2\2\2\u00df\u00e1"+
		"\7\6\2\2\u00e0\u00df\3\2\2\2\u00e1\u00e2\3\2\2\2\u00e2\u00e0\3\2\2\2\u00e2"+
		"\u00e3\3\2\2\2\u00e3\u00e5\3\2\2\2\u00e4\u00e6\7\5\2\2\u00e5\u00e4\3\2"+
		"\2\2\u00e6\u00e7\3\2\2\2\u00e7\u00e5\3\2\2\2\u00e7\u00e8\3\2\2\2\u00e8"+
		"/\3\2\2\2\u00e9\u00eb\7\3\2\2\u00ea\u00e9\3\2\2\2\u00eb\u00ec\3\2\2\2"+
		"\u00ec\u00ea\3\2\2\2\u00ec\u00ed\3\2\2\2\u00ed\u00ef\3\2\2\2\u00ee\u00f0"+
		"\7\4\2\2\u00ef\u00ee\3\2\2\2\u00f0\u00f1\3\2\2\2\u00f1\u00ef\3\2\2\2\u00f1"+
		"\u00f2\3\2\2\2\u00f2\u00f4\3\2\2\2\u00f3\u00f5\7\5\2\2\u00f4\u00f3\3\2"+
		"\2\2\u00f5\u00f6\3\2\2\2\u00f6\u00f4\3\2\2\2\u00f6\u00f7\3\2\2\2\u00f7"+
		"\61\3\2\2\2\u00f8\u00fa\7\t\2\2\u00f9\u00f8\3\2\2\2\u00fa\u00fb\3\2\2"+
		"\2\u00fb\u00f9\3\2\2\2\u00fb\u00fc\3\2\2\2\u00fc\u00fe\3\2\2\2\u00fd\u00ff"+
		"\7\b\2\2\u00fe\u00fd\3\2\2\2\u00ff\u0100\3\2\2\2\u0100\u00fe\3\2\2\2\u0100"+
		"\u0101\3\2\2\2\u0101\u0103\3\2\2\2\u0102\u0104\7\7\2\2\u0103\u0102\3\2"+
		"\2\2\u0104\u0105\3\2\2\2\u0105\u0103\3\2\2\2\u0105\u0106\3\2\2\2\u0106"+
		"\63\3\2\2\2\'?MQaejoty~\u0083\u0088\u008d\u0092\u0097\u009c\u00a1\u00a6"+
		"\u00ab\u00b0\u00b5\u00ba\u00bf\u00c4\u00c9\u00ce\u00d3\u00d8\u00dd\u00e2"+
		"\u00e7\u00ec\u00f1\u00f6\u00fb\u0100\u0105";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}