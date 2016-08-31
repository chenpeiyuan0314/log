package org.yuan.project.log.pattern;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.yuan.project.log.spi.LoggingEvent;

public class DatePatternConverter extends PatternConverter {
	
	public DatePatternConverter(String[] options) {
		if(options.length > 0) {
			sdf = new SimpleDateFormat(options[0]);
			return;
		}
		sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
	}

	@Override
	public void format(LoggingEvent event, StringBuffer sbuf) {
		sbuf.append(sdf.format(new Date(event.getTimeStamp())));
	}

	public static PatternConverter getInstance(String[] option) {
		return new DatePatternConverter(option);
	}
	
	private SimpleDateFormat sdf;
}
