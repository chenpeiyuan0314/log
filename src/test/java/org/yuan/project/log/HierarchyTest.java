package org.yuan.project.log;

import org.junit.Assert;
import org.junit.Test;
import org.yuan.project.log.spi.LoggerRepository;
import org.yuan.project.log.spi.RootLogger;

public class HierarchyTest {

	@Test
	public void testGetLogger() {
		Logger root = new RootLogger(Level.DEBUG);
		LoggerRepository hierarchy = new Hierarchy(root);
		
		Logger a = hierarchy.getLogger("a");
		Logger ab = hierarchy.getLogger("a.b");
		Logger abc = hierarchy.getLogger("a.b.c");
		
		Assert.assertEquals(root, a.getParent());
		Assert.assertEquals(a, ab.getParent());
		Assert.assertEquals(ab, abc.getParent());
	}
	
	@Test
	public void testGetRootLogger() {
		Logger root = new RootLogger(Level.DEBUG);
		LoggerRepository hierarchy = new Hierarchy(root);
		
		Assert.assertEquals(root, hierarchy.getRootLogger());
	}
	
	@Test
	public void testGetCurrentLoggers() {
		Logger root = new RootLogger(Level.DEBUG);
		LoggerRepository hierarchy = new Hierarchy(root);
		
		Logger a = hierarchy.getLogger("a");
		Logger ab = hierarchy.getLogger("a.b");
		Logger abc = hierarchy.getLogger("a.b.c");
		
		int count = count(hierarchy.getCurrentLoggers());
		Assert.assertEquals(3, count);
	}
	
	@Test
	public void testShutdown() {
		Logger root = new RootLogger(Level.DEBUG);
		LoggerRepository hierarchy = new Hierarchy(root);
		
		Logger test = hierarchy.getLogger("test");
		Appender appender = new ConsoleAppender();
		root.addAppender(appender);
		test.addAppender(appender);
		
		hierarchy.shutdown();
		
		Assert.assertEquals(0, count(root.getAllAppenders()));
		Assert.assertEquals(0, count(test.getAllAppenders()));
	}
	
	@Test
	public void testResetConfiguration() {
		Logger root = new RootLogger(Level.DEBUG);
		LoggerRepository hierarchy = new Hierarchy(root);
		
		Logger test = hierarchy.getLogger("test");
		Appender appender = new ConsoleAppender();
		root.addAppender(appender);
		root.setLevel(Level.INFO);
		test.addAppender(appender);
		test.setLevel(Level.INFO);
		
		hierarchy.resetConfiguration();
		
		Assert.assertEquals(Level.DEBUG, root.getLevel());
		Assert.assertNull(test.getLevel());
	}
	
	private int count(Iterable<?> iter) {
		int count = 0;
		for(Object item : iter) {
			count++;
		}
		return count;
	}
}
