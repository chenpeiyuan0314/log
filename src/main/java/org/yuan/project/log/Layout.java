package org.yuan.project.log;

import org.yuan.project.log.spi.LoggingEvent;

public class Layout {
	public static final String LINE_SEP = System.getProperty("line.separator");
	
	public String format(LoggingEvent event) {
		StringBuffer sb = new StringBuffer();
		sb.append(event.getMessage());
		sb.append(LINE_SEP);
		return sb.toString();
	}
	
}
