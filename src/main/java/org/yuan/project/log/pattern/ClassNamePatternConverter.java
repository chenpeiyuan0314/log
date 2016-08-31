package org.yuan.project.log.pattern;

import org.yuan.project.log.spi.LoggingEvent;

public class ClassNamePatternConverter extends PatternConverter {

	public ClassNamePatternConverter() {}

	@Override
	public void format(LoggingEvent event, StringBuffer sbuf) {
		sbuf.append(event.getLocationInfo().getClassName());
	}

	public static PatternConverter getInstance(String[] option) {
		return INSTANCE;
	}
	
	private static final PatternConverter INSTANCE = new ClassNamePatternConverter();
}
