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
		return LogManager.getRootLogger();
	}
	
	/**
	 * 
	 * @param name
	 * @return
	 */
	public static Logger getLogger(String name) {
		return LogManager.getLogger(name);
	}

	protected Logger(String name) {
		this.name = name;
		appList = new ArrayList<Appender>();
	}
	
	/**
	 * 
	 * @param message
	 */
	public void log(Level level, String message) {
		if(!level.isGreaterOrEqual(getEffectiveLevel())) {
			return;
		}
		
		LoggingEvent event = new LoggingEvent(level, message);
		for(Logger log=this; log!=null; log=log.parent) {
			for(Appender appender : log.appList) {
				appender.doAppend(event);
			}
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
	
	public void removeAllAppenders() {
		appList.clear();
	}
	
	public Iterable<Appender> getAllAppenders() {
		return appList;
	}
	
	public Level getEffectiveLevel() {
		for(Logger log = this; log != null; log = log.parent) {
			if(log.level != null) {
				return log.level;
			}
		}
		return null;
	}
	
	public void closeNestedAppenders() {
		Iterable<Appender> list = getAllAppenders();
		for(Appender item : list) {
			item.close();
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

	public Logger getParent() {
		return parent;
	}

	//-----------------------------------------------------------------
	// 
	//-----------------------------------------------------------------
	private String name;
	private List<Appender> appList;
	protected Level level;
	protected Logger parent;
	//private LoggerRepository hierarchy;
}
