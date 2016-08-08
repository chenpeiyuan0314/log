package org.yuan.project.pattern;

import org.yuan.project.log.spi.LoggingEvent;

public class RelativeTimePatternConverter extends PatternConverter {

	@Override
	public void format(LoggingEvent event, StringBuffer sbuf) {
		sbuf.append(event.getTimeStamp() - LoggingEvent.getStartTime());
	}

}
