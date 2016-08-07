package org.yuan.project.pattern;

import org.yuan.project.log.spi.LoggingEvent;

public class MessagePatternConverter extends PatternConverter {
	
	public MessagePatternConverter() {}

	@Override
	public void format(LoggingEvent event, StringBuffer sbuf) {
		sbuf.append(event.getMessage());
	}
	
}
