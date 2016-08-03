package org.yuan.project.log;

import org.yuan.project.log.spi.LoggingEvent;

public abstract class Layout {
	public static final String LINE_SEP = System.getProperty("line.separator");
	
	public abstract String format(LoggingEvent event);
}
