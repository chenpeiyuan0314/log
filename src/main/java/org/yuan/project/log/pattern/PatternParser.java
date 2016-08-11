package org.yuan.project.log.pattern;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.yuan.project.log.Layout;
import org.yuan.project.log.kit.LogLog;

public final class PatternParser {

	private static final char ESCAPE_CHAR = '%';
	
	private static final int DOT_STATE = 0;
	private static final int MAX_STATE = 1;
	private static final int MIN_STATE = 2;
	private static final int LIT_STATE = 3;
	private static final int CON_STATE = 4;
	
	private static final Map<String,Object> PATTERN_LAYOUT_RULES;
	private static final Map<String,Object> FILENAME_PATTERN_RULES;
	
	static {
		PATTERN_LAYOUT_RULES = new HashMap<String,Object>();
		PATTERN_LAYOUT_RULES.put("c", LoggerPatternConverter.class);
		
		PATTERN_LAYOUT_RULES.put("C", ClassNamePatternConverter.class);
		
		PATTERN_LAYOUT_RULES.put("F", FileLocationPatternConverter.class);
		
		PATTERN_LAYOUT_RULES.put("L", LineLocationPatternConverter.class);
		
		PATTERN_LAYOUT_RULES.put("M", MethodLocationPatternConverter.class);
		
		PATTERN_LAYOUT_RULES.put("l", FullLocationPatternConverter.class);
		
		PATTERN_LAYOUT_RULES.put("m", MessagePatternConverter.class);
		PATTERN_LAYOUT_RULES.put("message", MessagePatternConverter.class);
		
		PATTERN_LAYOUT_RULES.put("p", LevelPatternConverter.class);
		PATTERN_LAYOUT_RULES.put("level", LevelPatternConverter.class);
		
		PATTERN_LAYOUT_RULES.put("r", RelativeTimePatternConverter.class);
		
		PATTERN_LAYOUT_RULES.put("t", ThreadPatternConverter.class);
		
		PATTERN_LAYOUT_RULES.put("d", DatePatternConverter.class);
		
		FILENAME_PATTERN_RULES = new HashMap<String,Object>();
	}
	
	private PatternParser() {}
	
	public static Map<String, Object> getPatternLayoutRules() {
		return PATTERN_LAYOUT_RULES;
	}

	public static Map<String, Object> getFilenamePatternRules() {
		return FILENAME_PATTERN_RULES;
	}

	public static void parse(String pattern, List<PatternConverter> patternConverters, 
		List<FormattingInfo> formattingInfos, Map<String,Object> rules) {
		FormattingInfo fmtInfo = new FormattingInfo();
		StringBuffer literal = new StringBuffer();
		
		int i=0;
		int state = LIT_STATE;
		while(i < pattern.length()) {
			char ch = pattern.charAt(i++);
			
			switch(state) {
			case LIT_STATE:
				if(i == pattern.length()) {
					literal.append(ch);
					continue;
				}
				
				if(ch == ESCAPE_CHAR) {
					switch(pattern.charAt(i)) {
					case ESCAPE_CHAR:
						literal.append(ESCAPE_CHAR);
						i++;
						break;
					case 'n':
						literal.append(Layout.LINE_SEP);
						i++;
						break;
					default:
						if(literal.length() > 0) {
							patternConverters.add(new LiteralPatternConverter(literal.toString()));
							formattingInfos.add(new FormattingInfo());
						}
						
						literal.setLength(0);
						literal.append(ESCAPE_CHAR);
						state = CON_STATE;
						fmtInfo = new FormattingInfo();
					}
				} else {
					literal.append(ch);
				}
				
				break;
			case CON_STATE:
				literal.append(ch);
				
				switch(ch) {
				case '-':
					fmtInfo.setLeftAlign(true);
					break;
				case '.':
					state = DOT_STATE;
					break;
				default:
					if(Character.isDigit(ch)) {
						fmtInfo.setMinLength(ch - '0');
						state = MIN_STATE;
					} else {
						i = finalizeConverter(ch, pattern, i, literal, fmtInfo, rules, patternConverters, formattingInfos);
						state = LIT_STATE;
						fmtInfo = new FormattingInfo();
					}
				}
				
				break;
			case DOT_STATE:
				literal.append(ch);
				
				if(Character.isDigit(ch)) {
					fmtInfo.setMaxLength(ch - '0');
					state = MIN_STATE;
				} else {
					LogLog.error("Error occured in position " + i + ".\n Was expecting digit, instead got char \"" + ch + "\".");
					state = LIT_STATE;
				}
				
				break;
			case MIN_STATE:
				literal.append(ch);
				
				if(Character.isDigit(ch)) {
					fmtInfo.setMinLength(fmtInfo.getMinLength() * 10 + (ch - '0'));
				} else if(ch == '.') {
					state = DOT_STATE;
				} else {
					i = finalizeConverter(ch, pattern, i, literal, fmtInfo, rules, patternConverters, formattingInfos);
					state = LIT_STATE;
					fmtInfo = new FormattingInfo();
				}
				
				break;
			case MAX_STATE:
				literal.append(ch);
				
				if(Character.isDigit(ch)) {
					fmtInfo.setMaxLength(fmtInfo.getMaxLength() * 10 + (ch - '0'));
				} else {
					i = finalizeConverter(ch, pattern, i, literal, fmtInfo, rules, patternConverters, formattingInfos);
					state = LIT_STATE;
					fmtInfo = new FormattingInfo();
				}
				
				break;
			}
		}
		
		if(literal.length() != 0) {
			patternConverters.add(new LiteralPatternConverter(literal.toString()));
			formattingInfos.add(new FormattingInfo());
		}
	}
	
	private static int finalizeConverter(char ch, String pattern, int i, StringBuffer buf, FormattingInfo fmtInfo, 
		Map<String,Object> rules, List<PatternConverter> patternConverters, List<FormattingInfo> formattingInfos) {
		Class<?> converterClass = (Class<?>)rules.get(String.valueOf(ch));
		
		if(converterClass == null) {
			return i;
		}
		
		try {
			StringBuffer sbuf = new StringBuffer();
			i = extractOptions(pattern, i, sbuf);
			Constructor<?> constructor = converterClass.getConstructor(String[].class);
			PatternConverter pc = (PatternConverter)constructor.newInstance((Object)new String[]{sbuf.toString()});
			//PatternConverter pc = (PatternConverter)converterClass.newInstance();
			patternConverters.add(pc);
			formattingInfos.add(fmtInfo);
		} catch(Exception e) {
			LogLog.error(e.getMessage());
		}
		
		buf.setLength(0);
		
		return i;
	}
	
	private static int extractOptions(String pattern, int i, StringBuffer sbuf) {
		int beg = pattern.indexOf("{", i);
		if(beg == -1) {
			return i;
		}
		int end = pattern.indexOf("}", beg + 1);
		if(end == -1) {
			return i;
		}
		sbuf.append(pattern.substring(beg + 1, end));
		return end + 1;
	}
}
