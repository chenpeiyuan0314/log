package org.yuan.project.log;

import org.junit.Assert;
import org.junit.Test;
import org.yuan.project.log.spi.LoggingEvent;

public class LoggingEventTest {

	@Test
	public void testGet() {
		LoggingEvent event = new LoggingEvent(Logger.getLogger("a.b.c"), Level.INFO, "This is a test.");
		
		Assert.assertEquals("a.b.c", event.getLoggerName());
		Assert.assertEquals("INFO", event.getLevel().toString());
		Assert.assertEquals("org.yuan.project.log.spi.LoggingEvent.testGet(LoggingEventTest.java:11)", event.getLocationInfo().getFullInfo());
	}
	
}
