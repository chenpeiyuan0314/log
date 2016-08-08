package org.yuan.project.pattern;

import org.yuan.project.log.spi.LoggingEvent;

public class FileLocationPatternConverter extends PatternConverter {

	@Override
	public void format(LoggingEvent event, StringBuffer sbuf) {
		sbuf.append(event.getLocationInfo().getFileName());
	}

}
