package org.yuan.project.log;

import org.junit.Before;
import org.junit.Test;
import org.yuan.project.log.spi.LoggingEvent;

public class ConsoleAppenderTest {
	private LoggingEvent event;
	private ConsoleAppender appender;
	
	@Before
	public void before() {
		event = new LoggingEvent(Level.INFO, "This is a info message.");
		appender = new ConsoleAppender(new SimpleLayout());
	}
	

	@Test
	public void testErr() {
		appender.setTarget(ConsoleAppender.SYSTEM_ERR);
		appender.activateOptions();
		
		appender.doAppend(event);
	}
	
	@Test
	public void testOUt() {
		appender.setTarget(ConsoleAppender.SYSTEM_OUT);
		appender.activateOptions();
		
		appender.doAppend(event);
	}
}
