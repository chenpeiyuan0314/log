package org.yuan.project.log.pattern;

import org.yuan.project.log.spi.LoggingEvent;

public class FullLocationPatternConverter extends PatternConverter {
	
	public FullLocationPatternConverter() {}

	@Override
	public void format(LoggingEvent event, StringBuffer sbuf) {
		sbuf.append(event.getLocationInfo().getFullInfo());
	}
	
	public static PatternConverter getInstance(String[] option) {
		return INSTANCE;
	}

	private static final PatternConverter INSTANCE = new FullLocationPatternConverter();
}
