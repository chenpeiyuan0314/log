package org.yuan.project.log;

import org.junit.Assert;
import org.junit.Test;
import org.yuan.project.log.spi.LoggingEvent;

public class SimpleLayoutTest {

	@Test
	public void testFormat() {
		LoggingEvent event = new LoggingEvent(Logger.getRootLogger(), Level.INFO, "This is a info message.");
		Layout layout = new SimpleLayout();
		String msg = layout.format(event);
		
		StringBuffer sb = new StringBuffer();
		sb.append(Level.INFO.toString());
		sb.append(" - ");
		sb.append("This is a info message.");
		sb.append(Layout.LINE_SEP);
		
		Assert.assertEquals(sb.toString(), msg);
	}
	
}
