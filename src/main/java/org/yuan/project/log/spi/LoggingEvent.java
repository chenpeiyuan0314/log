package org.yuan.project.log.spi;

public class LoggingEvent {
	
	public LoggingEvent(String message) {
		this.message = message;
	}

	
	public String getMessage() {
		return message;
	}

	//-----------------------------------------------------------------
	//
	//-----------------------------------------------------------------
	private String message;
}
