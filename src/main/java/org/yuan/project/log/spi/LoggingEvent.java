package org.yuan.project.log.spi;

import java.util.Collections;
import java.util.Map;

import org.yuan.project.log.Level;
import org.yuan.project.log.Logger;
import org.yuan.project.log.MDC;
import org.yuan.project.log.NDC;

public class LoggingEvent {
	
	public LoggingEvent(Logger logger, Level level, String message) {
		this.logger = logger;
		this.level = level;
		this.message = message;
		this.timeStamp = System.currentTimeMillis();
		this.locationInfo = new LocationInfo(new Throwable(), className);
	}

	public LoggingEvent(Logger logger, Level level, String message, long timeStamp) {
		this.logger = logger;
		this.level = level;
		this.message = message;
		this.timeStamp = timeStamp;
		this.locationInfo = new LocationInfo(new Throwable(), className);
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
	
	public String getNDC() {
		return NDC.get();
	}
	
	public Object getMDC(String key) {
		return MDC.get(key);
	}
	
	public Map<String,Object> getProperties() {
		return Collections.unmodifiableMap(MDC.getContext());
	}
	
	public ThrowableInfo getThrowableInfo() {
		return throwableInfo;
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
	private ThrowableInfo throwableInfo;
	
	private static long startTime = System.currentTimeMillis();
	private static String className = LoggingEvent.class.getName();
}
