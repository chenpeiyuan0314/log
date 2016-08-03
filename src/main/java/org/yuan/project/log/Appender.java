package org.yuan.project.log;

import org.yuan.project.log.spi.LoggingEvent;

public class Appender {
	
	public void doAppend(LoggingEvent event) {
		String result = layout.format(event);
		System.out.print(result);
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Layout getLayout() {
		return layout;
	}
	
	public void setLayout(Layout layout) {
		this.layout = layout;
	}
	
	//-----------------------------------------------------------------
	//
	//-----------------------------------------------------------------
	private String name;
	private Layout layout;
}
