package org.yuan.project.pattern;

import org.yuan.project.log.spi.LoggingEvent;

public class ThreadPatternConverter extends PatternConverter {

	public ThreadPatternConverter(String[] options) {
		super(options);
	}

	@Override
	public void format(LoggingEvent event, StringBuffer sbuf) {
		sbuf.append(event.getThreadName());
	}

}
