package org.yuan.project.log;

import org.junit.Assert;
import org.junit.Test;

public class LevelTest {
	
	@Test
	public void testEquals() {
		Level[] levels = {Level.OFF, Level.FATAL, Level.ERROR, Level.WARN, Level.INFO, Level.DEBUG, Level.TRACE, Level.ALL};
		
		for(int i=0; i<levels.length; i++) {
			Level level1 = levels[i];
			for(int j=0; j<levels.length; j++) {
				Level level2 = levels[j];
				if(i == j) {
					Assert.assertTrue(level1.equals(level2));
				} else {
					Assert.assertFalse(level1.equals(level2));
				}
			}
		}
	}
	
	@Test
	public void testIsGreaterOrEqual() {
		Level[] levels = {Level.OFF, Level.FATAL, Level.ERROR, Level.WARN, Level.INFO, Level.DEBUG, Level.TRACE, Level.ALL};
		
		for(int i=0; i<levels.length; i++) {
			Level level1 = levels[i];
			for(int j=0; j<levels.length; j++) {
				Level level2 = levels[j];
				if(i <= j) {
					Assert.assertTrue(level1.isGreaterOrEqual(level2));
				} else {
					Assert.assertFalse(level1.isGreaterOrEqual(level2));
				}
			}
		}
	}
	
	@Test
	public void testToInt() {
		Level[] levels = {Level.OFF, Level.FATAL, Level.ERROR, Level.WARN, Level.INFO, Level.DEBUG, Level.TRACE, Level.ALL};
		int[] values = {Level.OFF_INT, Level.FATAL_INT, Level.ERROR_INT, Level.WARN_INT, Level.INFO_INT, Level.DEBUG_INT, Level.TRACE_INT, Level.ALL_INT};
	
		for(int i=0; i<levels.length && i<values.length; i++) {
			Assert.assertEquals(values[i], levels[i].toInt());
		}
	}
	
	@Test
	public void testToString() {
		Level[] levels = {Level.OFF, Level.FATAL, Level.ERROR, Level.WARN, Level.INFO, Level.DEBUG, Level.TRACE, Level.ALL};
		String[] titles = {"OFF", "FATAL", "ERROR", "WARN", "INFO", "DEBUG", "TRACE", "ALL"};
	
		for(int i=0; i<levels.length && i<titles.length; i++) {
			Assert.assertEquals(titles[i], levels[i].toString());
		}
	}
	
	@Test
	public void testToLevel() {
		Level[] levels = {Level.OFF, Level.FATAL, Level.ERROR, Level.WARN, Level.INFO, Level.DEBUG, Level.TRACE, Level.ALL};
		
		int[] values = {Level.OFF_INT, Level.FATAL_INT, Level.ERROR_INT, Level.WARN_INT, Level.INFO_INT, Level.DEBUG_INT, Level.TRACE_INT, Level.ALL_INT};
		for(int i=0; i<values.length && i<levels.length; i++) {
			Level level = Level.toLevel(values[i], null);
			Assert.assertEquals(levels[i], level);
		}
		
		String[] titles = {"OFF", "FATAL", "ERROR", "WARN", "INFO", "DEBUG", "TRACE", "ALL"};
		for(int i=0; i<titles.length && i<levels.length; i++) {
			Level level = Level.toLevel(titles[i], null);
			Assert.assertEquals(levels[i], level);
		}
		
		Assert.assertNull(Level.toLevel(0, null));
		Assert.assertNull(Level.toLevel("none", null));
	}
}
