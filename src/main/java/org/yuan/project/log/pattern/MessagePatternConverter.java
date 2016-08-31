package org.yuan.project.log.pattern;

import org.yuan.project.log.spi.LoggingEvent;

public class MessagePatternConverter extends PatternConverter {
	
	public MessagePatternConverter() {}

	@Override
	public void format(LoggingEvent event, StringBuffer sbuf) {
		sbuf.append(event.getMessage());
	}
	
	public static PatternConverter getInstance(String[] option) {
		return INSTANCE;
	}
	
	private static final PatternConverter INSTANCE = new MessagePatternConverter();
}
