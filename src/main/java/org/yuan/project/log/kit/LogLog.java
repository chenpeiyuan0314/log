package org.yuan.project.log.kit;

import java.io.PrintStream;

public class LogLog {
	
	public static void debug(String msg) {
		debug(msg, null);
	}
	
	public static void debug(String msg, Throwable th) {
		if(!debugMode) {
			return;
		}
		
		print(PREFIX_DEBUG + msg, th, System.out);
	}
	
	public static void warn(String msg) {
		warn(msg, null);
	}
	
	public static void warn(String msg, Throwable th) {
		print(PREFIX_WARN + msg, th, System.err);
	}
	
	public static void error(String msg) {
		error(msg, null);
	}
	
	public static void error(String msg, Throwable th) {
		print(PREFIX_ERROR + msg, th, System.err);
	}
	
	private static void print(String msg, Throwable th, PrintStream ps) {
		if(quietMode) {
			return;
		}
		
		ps.println(msg);
		if(th != null) {
			th.printStackTrace(ps);
		}
	}
	
	public static void setDebugMode(boolean debugMode) {
		LogLog.debugMode = debugMode;
	}

	public static void setQuietMode(boolean quietMode) {
		LogLog.quietMode = quietMode;
	}

	//--------------------------------------------
	//
	//--------------------------------------------
	private static final String DEBUG_KEY = "log4j.debug";
	private static final String QUIET_KEY = "log4j.quiet";
	private static final String PREFIX_DEBUG = "log4j:debug ";
	private static final String PREFIX_ERROR = "log4j:error ";
	private static final String PREFIX_WARN  = "log4j:warn ";
	private static boolean debugMode = false;
	private static boolean quietMode = false;
	static {
		String key = null;
		
		// set debug mode
		key = System.getProperty(DEBUG_KEY);
		if(key != null) {
			if("TRUE".equalsIgnoreCase(key)) {
				debugMode = true;
			} else if("FALSE".equalsIgnoreCase(key)) {
				debugMode = false;
			}
		}
		
		// set quiet mode
		key = System.getProperty(QUIET_KEY);
		if(key != null) {
			if("TRUE".equalsIgnoreCase(key)) {
				quietMode = true;
			} else if("FALSE".equalsIgnoreCase(key)) {
				quietMode = false;
			}
		}
	}
}
