package org.yuan.project.log.pattern;

import org.yuan.project.log.spi.LoggingEvent;

public abstract class PatternConverter {
	
	public abstract void format(LoggingEvent event, StringBuffer sbuf);
	
	public void format(Object obj, StringBuffer sbuf) {
		if(obj instanceof LoggingEvent) {
			format((LoggingEvent)obj, sbuf);
		}
	}
}
