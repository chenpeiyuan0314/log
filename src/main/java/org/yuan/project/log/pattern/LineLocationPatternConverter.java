package org.yuan.project.log.pattern;

import org.yuan.project.log.spi.LoggingEvent;

public class LineLocationPatternConverter extends PatternConverter {
	
	public LineLocationPatternConverter() {}

	@Override
	public void format(LoggingEvent event, StringBuffer sbuf) {
		sbuf.append(event.getLocationInfo().getLineNumber());
	}
	
	public static PatternConverter getInstance(String[] option) {
		return INSTANCE;
	}

	private static final PatternConverter INSTANCE = new LineLocationPatternConverter();
}
