package org.yuan.project.log.spi;

import org.yuan.project.log.Level;
import org.yuan.project.log.Logger;

public class LoggingEvent {
	
	public LoggingEvent(Logger logger, Level level, String message) {
		this.logger = logger;
		this.level = level;
		this.message = message;
		this.timeStamp = System.currentTimeMillis();
		this.locationInfo = new LocationInfo(new Throwable(), LoggingEvent.class.getName());
	}

	
	public String getMessage() {
		return message;
	}
	
	public Level getLevel() {
		return level;
	}

	public String getThreadName() {
		if(threadName == null) {
			threadName = Thread.currentThread().getName();
		}
		return threadName;
	}
	
	public String getLoggerName() {
		return logger.getName();
	}
	
	public long getTimeStamp() {
		return timeStamp;
	}
	
	public static long getStartTime() {
		return startTime;
	}

	public LocationInfo getLocationInfo() {
		return locationInfo;
	}

	//-----------------------------------------------------------------
	//
	//-----------------------------------------------------------------
	private String message;
	private Level level;
	private Logger logger;
	private String threadName;
	private long timeStamp;
	private LocationInfo locationInfo;
	
	private static long startTime = System.currentTimeMillis();
}
