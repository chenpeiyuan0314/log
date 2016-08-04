package org.yuan.project.log;

import org.junit.Assert;
import org.junit.Test;

public class LoggerTest {

	@Test
	public void test() {
		Logger root = Logger.getRootLogger();
		Layout layout = new SimpleLayout();
		Appender appender = new ConsoleAppender(layout);
		root.addAppender(appender);
		
		Appender appender2 = new ConsoleAppender(layout);
		root.addAppender(appender2);
		
		root.log(Level.OFF,"This is a test.");
	}
	
	@Test
	public void testLog() {
		Logger log = Logger.getLogger("test");
		Layout layout = new SimpleLayout();
		Appender appender = new ConsoleAppender(layout);
		log.addAppender(appender);
		log.setLevel(Level.INFO);
		
		log.log(Level.TRACE, "This is a trace message.");
		log.log(Level.INFO, "This ia a info message.");
	}
	
	@Test
	public void testLog2() {
		Layout layout = new SimpleLayout();
		Appender appender = new ConsoleAppender(layout);
		Logger root = Logger.getRootLogger();
		root.addAppender(appender);
		Logger a = Logger.getLogger("a");
		a.addAppender(appender);
		
		a.log(Level.INFO, "This is a test for testLog2.");
	}
	
	@Test
	public void testGetEffectiveLevel() {
		Level level = Level.TRACE;
		Logger root = Logger.getRootLogger();
		root.setLevel(level);
		
		Logger abc = Logger.getLogger("a.b.c");
		Assert.assertNull(abc.getLevel());
		Assert.assertEquals(level, abc.getEffectiveLevel());
		
		level = Level.DEBUG;
		Logger a = Logger.getLogger("a");
		a.setLevel(level);
		Assert.assertEquals(level, abc.getEffectiveLevel());
		
		level = Level.ERROR;
		Logger ab = Logger.getLogger("a.b");
		ab.setLevel(level);
		Assert.assertEquals(level, abc.getEffectiveLevel());
	}
	
}
