package org.yuan.project.log.pattern;

import org.yuan.project.log.spi.LoggingEvent;

public class NDCPatternConverter extends PatternConverter {
	
	public NDCPatternConverter() {}

	@Override
	public void format(LoggingEvent event, StringBuffer sbuf) {
		sbuf.append(event.getNDC());
	}
	
	public static PatternConverter getInstance(String[] option) {
		return INSTANCE;
	}

	private static final PatternConverter INSTANCE = new NDCPatternConverter();
}
