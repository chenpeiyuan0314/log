package org.yuan.project.log.pattern;

import org.yuan.project.log.spi.LoggingEvent;

public class LevelPatternConverter extends PatternConverter {
	
	public LevelPatternConverter(String[] options) {
		super(options);
	}

	@Override
	public void format(LoggingEvent event, StringBuffer sbuf) {
		sbuf.append(event.getLevel().toString());
	}

}
