package org.yuan.project.log;

import org.junit.Test;

public class LoggerTest {

	@Test
	public void test() {
		Logger root = Logger.getRootLogger();
		Layout layout = new SimpleLayout();
		Appender appender = new ConsoleAppender();
		appender.setLayout(layout);
		root.addAppender(appender);
		
		Appender appender2 = new ConsoleAppender();
		appender2.setLayout(layout);
		root.addAppender(appender2);
		
		root.log(Level.OFF,"This is a test.");
	}
	
	@Test
	public void testLog() {
		Logger log = Logger.getLogger("test");
		Layout layout = new SimpleLayout();
		Appender appender = new ConsoleAppender();
		appender.setLayout(layout);
		log.addAppender(appender);
		log.setLevel(Level.INFO);
		
		log.log(Level.TRACE, "This is a trace message.");
		log.log(Level.INFO, "This ia a info message.");
	}
	
}
