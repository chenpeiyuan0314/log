package org.yuan.project.log.pattern;

import org.yuan.project.log.spi.LoggingEvent;

public class FileLocationPatternConverter extends PatternConverter {
	
	public FileLocationPatternConverter(String[] options) {
		super(options);
	}

	@Override
	public void format(LoggingEvent event, StringBuffer sbuf) {
		sbuf.append(event.getLocationInfo().getFileName());
	}

}
