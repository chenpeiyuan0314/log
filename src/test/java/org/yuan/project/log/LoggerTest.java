package org.yuan.project.log;

import org.junit.Test;

public class LoggerTest {

	@Test
	public void test() {
		Logger log = Logger.getLogger("sole");
		
		log.log("This is a test.");
	}
}
