package org.yuan.project.pattern;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.yuan.project.log.spi.LoggingEvent;

public class DatePatternConverter extends PatternConverter {

	@Override
	public void format(LoggingEvent event, StringBuffer sbuf) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		sbuf.append(sdf.format(new Date(event.getTimeStamp())));
	}

}
