package org.yuan.project.log;

import org.yuan.project.log.spi.LoggingEvent;

public interface Appender {
	
	void doAppend(LoggingEvent event);
	
	String getName();
	
	void setName(String name);
	
	Layout getLayout();
	
	void setLayout(Layout layout);

	void close();
	
}
