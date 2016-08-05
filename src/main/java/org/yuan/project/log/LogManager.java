package org.yuan.project.log;

import org.yuan.project.log.spi.LoggerRepository;
import org.yuan.project.log.spi.RootLogger;

public class LogManager {
	
	public static Logger getLogger(String name) {
		return hierarchy.getLogger(name);
	}
	
	public static LoggerRepository getLoggerRepository() {
		return hierarchy;
	}
	
	public static Logger getRootLogger() {
		return hierarchy.getRootLogger();
	}
	
	public static void resetConfiguration() {
		hierarchy.resetConfiguration();
	}
	
	public static void shutdown() {
		hierarchy.shutdown();
	}

	public static Iterable<Logger> getCurrentLoggers() {
		return hierarchy.getCurrentLoggers();
	}
	
	//-----------------------------------------------------------------
	//
	//-----------------------------------------------------------------
	private static LoggerRepository hierarchy;
	static {
		Logger root = new RootLogger(Level.DEBUG);
		hierarchy = new Hierarchy(root);
	}
}
