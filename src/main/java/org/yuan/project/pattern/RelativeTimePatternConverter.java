package org.yuan.project.pattern;

import org.yuan.project.log.spi.LoggingEvent;

public class RelativeTimePatternConverter extends PatternConverter {
	
	public RelativeTimePatternConverter(String[] options) {
		super(options);
	}

	@Override
	public void format(LoggingEvent event, StringBuffer sbuf) {
		sbuf.append(event.getTimeStamp() - LoggingEvent.getStartTime());
	}

}
