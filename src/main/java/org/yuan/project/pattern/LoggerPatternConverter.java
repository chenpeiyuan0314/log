package org.yuan.project.pattern;

import org.yuan.project.log.spi.LoggingEvent;

public class LoggerPatternConverter extends PatternConverter {
	
	public LoggerPatternConverter(String[] options) {
		super(options);
	}

	@Override
	public void format(LoggingEvent event, StringBuffer sbuf) {
		sbuf.append(event.getLoggerName());
	}

}
