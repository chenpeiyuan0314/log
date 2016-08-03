package org.yuan.project.log;

import org.junit.Test;

public class LoggerTest {

	@Test
	public void test() {
		Logger root = Logger.getRootLogger();
		Layout layout = new Layout();
		Appender appender = new Appender();
		appender.setLayout(layout);
		root.addAppender(appender);
		
		Appender appender2 = new Appender();
		appender2.setLayout(layout);
		root.addAppender(appender2);
		
		root.log("This is a test.");
	}
}
