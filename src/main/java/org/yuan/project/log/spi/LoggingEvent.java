package org.yuan.project.log.spi;

import org.yuan.project.log.Level;

public class LoggingEvent {
	
	public LoggingEvent(Level leve, String message) {
		this.level = leve;
		this.message = message;
	}

	
	public String getMessage() {
		return message;
	}
	
	public Level getLevel() {
		return level;
	}

	//-----------------------------------------------------------------
	//
	//-----------------------------------------------------------------
	private String message;
	private Level level;
}
