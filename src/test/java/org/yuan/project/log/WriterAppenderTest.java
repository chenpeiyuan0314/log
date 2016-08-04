package org.yuan.project.log;

import org.junit.Assert;
import org.junit.Test;
import org.yuan.project.test.kit.MockOutputStream;

public class WriterAppenderTest {

	@Test
	public void testLog3() {
		MockOutputStream mos = new MockOutputStream();
		Layout layout = new SimpleLayout();
		Appender appender = new WriterAppender(layout, mos);
		Logger a = Logger.getLogger("a");
		a.addAppender(appender);
		
		a.log(Level.INFO, "This is a test for testLog3.");
		
		StringBuffer sb = new StringBuffer();
		sb.append(Level.INFO);
		sb.append(" - ");
		sb.append("This is a test for testLog3.");
		sb.append(Layout.LINE_SEP);
		Assert.assertEquals(sb.toString(), mos.toString());
	}
}
