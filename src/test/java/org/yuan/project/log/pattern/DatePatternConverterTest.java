package org.yuan.project.log.pattern;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;
import org.yuan.project.log.EnhancedPatternLayout;
import org.yuan.project.log.Level;
import org.yuan.project.log.Logger;
import org.yuan.project.log.WriterAppender;
import org.yuan.project.log.spi.LoggingEvent;
import org.yuan.project.test.kit.MockOutputStream;


public class DatePatternConverterTest {

	@Test
	public void test() {
		Logger logger = Logger.getRootLogger();
		MockOutputStream mos = new MockOutputStream();
		WriterAppender appender = new WriterAppender(new EnhancedPatternLayout("%d{yyyy-MM-dd}"), mos);
		
		Date date = new Date();
		appender.doAppend(new LoggingEvent(logger, Level.ALL, "This is a test", date.getTime()));
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Assert.assertEquals(sdf.format(date), mos.toString());
	}
	
}
