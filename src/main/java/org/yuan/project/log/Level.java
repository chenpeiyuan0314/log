package org.yuan.project.log;

public class Level {

	public static final int OFF_INT   = 80000;
	public static final int FATAL_INT = 70000;
	public static final int ERROR_INT = 60000;
	public static final int WARN_INT  = 50000;
	public static final int INFO_INT  = 40000;
	public static final int DEBUG_INT = 30000;
	public static final int TRACE_INT = 20000;
	public static final int ALL_INT   = 10000;
	
	public static final Level OFF   = new Level("OFF", OFF_INT);
	public static final Level FATAL = new Level("FATAL", FATAL_INT);
	public static final Level ERROR = new Level("ERROR", ERROR_INT);
	public static final Level WARN  = new Level("WARN", WARN_INT);
	public static final Level INFO  = new Level("INFO", INFO_INT);
	public static final Level DEBUG = new Level("DEBUG", DEBUG_INT);
	public static final Level TRACE = new Level("TRACE", TRACE_INT);
	public static final Level ALL   = new Level("ALL", ALL_INT);
	
	public static Level toLevel(String title) {
		return toLevel(title, Level.DEBUG);
	}
	
	public static Level toLevel(String title, Level level) {
		if("OFF".equalsIgnoreCase(title)) {
			return OFF;
		}
		if("FATAL".equalsIgnoreCase(title)) {
			return FATAL;
		}
		if("ERROR".equalsIgnoreCase(title)) {
			return ERROR;
		}
		if("WARN".equalsIgnoreCase(title)) {
			return WARN;
		}
		if("INFO".equalsIgnoreCase(title)) {
			return INFO;
		}
		if("DEBUG".equalsIgnoreCase(title)) {
			return DEBUG;
		}
		if("TRACE".equalsIgnoreCase(title)) {
			return TRACE;
		}
		if("ALL".equalsIgnoreCase(title)) {
			return ALL;
		}
		return level;
		
	}
	
	public static Level toLevel(int value) {
		return toLevel(value, Level.DEBUG);
	}
	
	public static Level toLevel(int value, Level level) {
		switch(value) {
		case OFF_INT:
			return OFF;
		case FATAL_INT:
			return FATAL;
		case ERROR_INT:
			return ERROR;
		case WARN_INT:
			return WARN;
		case INFO_INT:
			return INFO;
		case DEBUG_INT:
			return DEBUG;
		case TRACE_INT:
			return TRACE;
		case ALL_INT:
			return ALL;
		}
		return level;
	}
	
	private Level(String title, int value) {
		this.title = title;
		this.value = value;
	}
	
	public int toInt() {
		return value;
	}
	
	public String toString() {
		return title;
	}
	
	public boolean isGreaterOrEqual(Level level) {
		return this.value >= level.value;
	}
	
	public boolean equals(Object o) {
		if(o instanceof Level) {
			Level level = (Level)o;
			if(this.value == level.value) {
				return true;
			}
		}
		return false;
	}
	
	//-----------------------------------------------------------------
	//
	//-----------------------------------------------------------------
	private String title;
	private int value;
}
