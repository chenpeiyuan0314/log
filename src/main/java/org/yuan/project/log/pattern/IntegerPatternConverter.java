package org.yuan.project.log.pattern;

import java.util.Date;

import org.yuan.project.log.spi.LoggingEvent;

public class IntegerPatternConverter extends PatternConverter {
	
	public IntegerPatternConverter() {}

	@Override
	public void format(LoggingEvent event, StringBuffer sbuf) {}

	public void format(Object obj, StringBuffer sbuf) {
		if(obj instanceof Integer) {
			sbuf.append(obj.toString());
		}
		if(obj instanceof Date) {
			sbuf.append(((Date)obj).getTime());
		}
	}
	
	public static PatternConverter getInstance(String[] option) {
		return INSTANCE;
	}
	
	private static final PatternConverter INSTANCE = new IntegerPatternConverter();
}
