package org.yuan.project.log;

import org.junit.Assert;
import org.junit.Test;
import org.yuan.project.log.spi.LoggingEvent;

public class EnhancedPatternLayoutTest {

	@Test
	public void testFormat1() {
		EnhancedPatternLayout layout = new EnhancedPatternLayout();
		//layout.setConversionPattern("%p %l - %m%n");
		layout.setConversionPattern("%p %C.%M(%F:%L) - %m%n");
		
		String fmt = layout.format(new LoggingEvent(Logger.getRootLogger(), Level.DEBUG, "This is a test."));
		//System.out.println(fmt);
		Assert.assertEquals("DEBUG org.yuan.project.log.EnhancedPatternLayoutTest.testFormat1(EnhancedPatternLayoutTest.java:15) - This is a test." + Layout.LINE_SEP, fmt);
	}
	
	@Test
	public void testFormat2() {
		EnhancedPatternLayout layout = new EnhancedPatternLayout();
		layout.setConversionPattern("%6p - %.5m%n");
		
		String fmt = layout.format(new LoggingEvent(Logger.getRootLogger(), Level.DEBUG, "This is a test."));
		Assert.assertEquals(" DEBUG - test." + Layout.LINE_SEP, fmt);
	}
	
	@Test
	public void testFormat3() {
		EnhancedPatternLayout layout = new EnhancedPatternLayout();
		layout.setConversionPattern("%-6p - %.5m%n");
		
		String fmt = layout.format(new LoggingEvent(Logger.getRootLogger(), Level.DEBUG, "This is a test."));
		Assert.assertEquals("DEBUG  - test." + Layout.LINE_SEP, fmt);
	}
	
	@Test
	public void testFormat4() {
		EnhancedPatternLayout layout = new EnhancedPatternLayout();
		layout.setConversionPattern("%r %d %t - %m%n");
		
		String fmt = null;
		fmt = layout.format(new LoggingEvent(Logger.getRootLogger(), Level.DEBUG, "This is a test1."));
		System.out.print(fmt);
		
		fmt = layout.format(new LoggingEvent(Logger.getRootLogger(), Level.DEBUG, "This is a test2."));
		System.out.print(fmt);
		//Assert.assertEquals("15 main - This is a test." + Layout.LINE_SEP, fmt);
	}
	
}
