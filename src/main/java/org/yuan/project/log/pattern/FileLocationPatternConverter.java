package org.yuan.project.log.pattern;

import org.yuan.project.log.spi.LoggingEvent;

public class FileLocationPatternConverter extends PatternConverter {
	
	public FileLocationPatternConverter() {}

	@Override
	public void format(LoggingEvent event, StringBuffer sbuf) {
		sbuf.append(event.getLocationInfo().getFileName());
	}

	public static PatternConverter getInstance(String[] option) {
		return INSTANCE;
	}
	
	private static final PatternConverter INSTANCE = new FileLocationPatternConverter();
}
