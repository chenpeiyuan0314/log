package org.yuan.project.log.pattern;

import org.yuan.project.log.spi.LoggingEvent;

public class RelativeTimePatternConverter extends PatternConverter {
	
	public RelativeTimePatternConverter() {}

	@Override
	public void format(LoggingEvent event, StringBuffer sbuf) {
		sbuf.append(event.getTimeStamp() - LoggingEvent.getStartTime());
	}
	
	public static PatternConverter getInstance(String[] option) {
		return INSTANCE;
	}

	private static final PatternConverter INSTANCE = new RelativeTimePatternConverter();
}
