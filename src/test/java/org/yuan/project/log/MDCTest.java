package org.yuan.project.log;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class MDCTest {
	
	@Before
	public void before() {
		MDC.clear();
	}
	
	@Test
	public void testPutAndGet() {
		MDC.put("key", "val");
		Assert.assertEquals("val", MDC.get("key"));
	}

	@Test
	public void testGetContext() {
		MDC.put("key", "val");
		Assert.assertEquals("val", MDC.getContext().get("key"));
	}
	
	@Test
	public void testRemove() {
		MDC.put("key", "val");
		
		MDC.remove("key");
		Assert.assertNull(MDC.get("key"));
	}
	
	@Test
	public void testMultiThread() {
		Thread ta = new Thread(new Task("A"));
		Thread tb = new Thread(new Task("B"));
		
		ta.start();
		tb.start();
	}
	
	private static class Task implements Runnable {
		private final String id;
		
		public Task(String id) {
			this.id = id;
		}

		@Override
		public void run() {
			try {
				for(int i=0; i<10; i++) {
					MDC.put(id + i, i);
					Thread.yield();
				}
				System.out.println(MDC.getContext());
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
}
