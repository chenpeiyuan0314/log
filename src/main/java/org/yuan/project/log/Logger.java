package org.yuan.project.log;

import java.util.ArrayList;
import java.util.List;

import org.yuan.project.log.spi.LoggingEvent;

public class Logger {
	
	/**
	 * 
	 * @return
	 */
	public static Logger getRootLogger() {
		return new Logger("root");
	}
	
	/**
	 * 
	 * @param name
	 * @return
	 */
	public static Logger getLogger(String name) {
		return new Logger(name);
	}

	Logger(String name) {
		this.name = name;
		appList = new ArrayList<Appender>();
		level = Level.DEBUG;
	}
	
	/**
	 * 
	 * @param message
	 */
	public void log(Level level, String message) {
		if(!level.isGreaterOrEqual(this.level)) {
			return;
		}
		
		LoggingEvent event = new LoggingEvent(level, message);
		for(Appender appender : appList) {
			appender.doAppend(event);
		}
	}
	
	/**
	 * 
	 * @param appender
	 */
	public void addAppender(Appender appender) {
		if(!appList.contains(appender)) {
			appList.add(appender);
		}
	}
	
	//-----------------------------------------------------------------
	//
	//-----------------------------------------------------------------
	public String getName() {
		return name;
	}
	
	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		this.level = level;
	}

	//-----------------------------------------------------------------
	// 
	//-----------------------------------------------------------------
	private String name;
	private List<Appender> appList;
	private Level level;
}
