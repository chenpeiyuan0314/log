package org.yuan.project.log.pattern;

import org.yuan.project.log.spi.LoggingEvent;

public class MethodLocationPatternConverter extends PatternConverter {

	public MethodLocationPatternConverter() {}

	@Override
	public void format(LoggingEvent event, StringBuffer sbuf) {
		sbuf.append(event.getLocationInfo().getMethodName());
	}
	
	public static PatternConverter getInstance(String[] option) {
		return INSTANCE;
	}

	private static final PatternConverter INSTANCE = new MethodLocationPatternConverter();
}
