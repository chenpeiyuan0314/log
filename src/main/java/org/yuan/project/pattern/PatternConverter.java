package org.yuan.project.pattern;

import org.yuan.project.log.spi.LoggingEvent;

public abstract class PatternConverter {
	
	public abstract void format(LoggingEvent event, StringBuffer sbuf);
	
}
