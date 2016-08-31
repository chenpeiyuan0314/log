package org.yuan.project.log.pattern;

import org.yuan.project.log.spi.LoggingEvent;

public class ThreadPatternConverter extends PatternConverter {

	private ThreadPatternConverter() {}

	@Override
	public void format(LoggingEvent event, StringBuffer sbuf) {
		sbuf.append(event.getThreadName());
	}
	
	public static PatternConverter getInstance(String[] options) {
		return INSTANCE;
	}

	private static final PatternConverter INSTANCE = new ThreadPatternConverter();
}
