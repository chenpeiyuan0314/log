package org.yuan.project.log;

import org.junit.Assert;
import org.junit.Test;
import org.yuan.project.test.kit.MockOutputStream;

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
	public void testLog3() {
		MockOutputStream mos = new MockOutputStream();
		Layout layout = new SimpleLayout();
		Appender appender = new WriterAppender(layout, mos);
		Logger a = Logger.getLogger("a");
		a.addAppender(appender);
		
		a.log(Level.INFO, "This is a test for testLog3.");
		Assert.assertEquals("INFO - This is a test for testLog3." + Layout.LINE_SEP, mos.toString());
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
	
	@Test
	public void testGetRootLogger() {
		Assert.assertEquals(Logger.getRootLogger(), Logger.getRootLogger());
	}
	
	@Test
	public void testGetLogger() {
		Logger none = Logger.getLogger("none");
		Assert.assertNotNull(none);
		Assert.assertNull(none.getLevel());
	}
	
	@Test
	public void testAddAppender() {
		LogManager.shutdown();
		
		Logger test = Logger.getLogger("test");
		Appender appender = new ConsoleAppender();
		test.addAppender(appender);
		
		Assert.assertEquals(1, count(test.getAllAppenders()));
	}
	
	@Test
	public void testAppender() {
		LogManager.shutdown();
		
		Logger test = Logger.getLogger("test");
		Appender appender = new ConsoleAppender();
		test.addAppender(appender);
		test.removeAllAppenders();
		
		Assert.assertEquals(0, count(test.getAllAppenders()));
	}
	
	private int count(Iterable<?> iter) {
		int sum = 0;
		for(Object obj : iter) {
			sum++;
		}
		return sum;
	}
}
