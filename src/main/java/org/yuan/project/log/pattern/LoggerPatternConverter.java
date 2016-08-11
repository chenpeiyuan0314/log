package org.yuan.project.log.pattern;

import org.yuan.project.log.spi.LoggingEvent;

public class LoggerPatternConverter extends PatternConverter {

	@Override
	public void format(LoggingEvent event, StringBuffer sbuf) {
		sbuf.append(event.getLoggerName());
	}

}
