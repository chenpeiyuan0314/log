package org.yuan.project.log;

import org.yuan.project.log.spi.LoggingEvent;

public class ConsoleAppender extends Appender {

	@Override
	public void doAppend(LoggingEvent event) {
		System.out.print(layout.format(event));
	}

	//-----------------------------------------------------------------
	//
	//-----------------------------------------------------------------
}
