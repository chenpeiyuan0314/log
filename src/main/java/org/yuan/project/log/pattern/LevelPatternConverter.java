package org.yuan.project.log.pattern;

import org.yuan.project.log.spi.LoggingEvent;

public class LevelPatternConverter extends PatternConverter {
	
	public LevelPatternConverter() {}

	@Override
	public void format(LoggingEvent event, StringBuffer sbuf) {
		sbuf.append(event.getLevel().toString());
	}

	public static PatternConverter getInstance(String[] option) {
		return INSTANCE;
	}
	
	private static final PatternConverter INSTANCE = new LevelPatternConverter();
}
