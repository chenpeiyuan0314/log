package org.yuan.project.log.pattern;

import org.yuan.project.log.spi.LoggingEvent;

public class SequenceNumberPatternConverter extends PatternConverter {
	
	public SequenceNumberPatternConverter() {}

	@Override
	public void format(LoggingEvent event, StringBuffer sbuf) {
		sbuf.append("0");
	}

	public static PatternConverter getInstance(String[] option) {
		return INSTANCE;
	}
	
	private static final PatternConverter INSTANCE = new SequenceNumberPatternConverter();
}
