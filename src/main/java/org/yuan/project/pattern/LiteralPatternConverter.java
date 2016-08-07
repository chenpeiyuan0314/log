package org.yuan.project.pattern;

import org.yuan.project.log.spi.LoggingEvent;

public class LiteralPatternConverter extends PatternConverter {
	
	public LiteralPatternConverter(String literal) {
		this.literal = literal;
	}

	@Override
	public void format(LoggingEvent event, StringBuffer sbuf) {
		sbuf.append(literal);
	}

	private String literal;
}
