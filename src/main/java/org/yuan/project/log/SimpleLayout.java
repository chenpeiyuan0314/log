package org.yuan.project.log;

import org.yuan.project.log.spi.LoggingEvent;

public class SimpleLayout extends Layout {

	public String format(LoggingEvent event) {
		StringBuffer sb = new StringBuffer();
		sb.append(event.getLevel().toString());
		sb.append(" - ");
		sb.append(event.getMessage());
		sb.append(LINE_SEP);
		return sb.toString();
	}
	
}
