package org.yuan.project.pattern;

import org.yuan.project.log.spi.LoggingEvent;

public class LevelPatternConverter extends PatternConverter {
	
	public LevelPatternConverter() {}

	@Override
	public void format(LoggingEvent event, StringBuffer sbuf) {
		sbuf.append(event.getLevel().toString());
	}

}
