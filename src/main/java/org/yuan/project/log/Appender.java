package org.yuan.project.log;

import org.yuan.project.log.spi.LoggingEvent;

public abstract class Appender {
	
	public abstract void doAppend(LoggingEvent event);

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
	protected Layout layout;
}
