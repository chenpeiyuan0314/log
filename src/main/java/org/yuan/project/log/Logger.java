package org.yuan.project.log;

public class Logger {
	
	public static Logger getLogger(String name) {
		return new Logger(name);
	}

	Logger(String name) {
		this.name = name;
	}
	
	public void log(String message) {
		System.out.println(message);
	}
	
	//-----------------------------------------------------------------
	//
	//-----------------------------------------------------------------
	public String getName() {
		return name;
	}
	
	//-----------------------------------------------------------------
	// 
	//-----------------------------------------------------------------
	private String name;
	
}
