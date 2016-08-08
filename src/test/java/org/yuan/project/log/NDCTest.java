package org.yuan.project.log;

import java.util.Stack;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class NDCTest {
	
	@Before
	public void before() {
		NDC.clear();
	}

	@Test
	public void testPush() {
		Assert.assertEquals(0, NDC.getDepth());
		NDC.push("one");
		Assert.assertEquals(1, NDC.getDepth());
	}
	
	@Test
	public void testGet() {
		NDC.push("one");
		NDC.push("two");
		
		Assert.assertEquals("one two", NDC.get());
	}
	
	@Test
	public void testPeek() {
		NDC.push("one");
		NDC.push("two");
		
		Assert.assertEquals("two", NDC.peek());
		Assert.assertEquals("two", NDC.peek());
	}
	
	@Test
	public void testPop() {
		NDC.push("one");
		NDC.push("two");
		
		Assert.assertEquals("two", NDC.pop());
		Assert.assertEquals("one", NDC.pop());
	}
	
	@Test
	public void testInherit() {
		NDC.push("one");
		NDC.push("two");
		
		Stack stack = NDC.cloneStack();
		stack.pop();
		
		NDC.inherit(stack);
		Assert.assertEquals("one", NDC.pop());
	}
	
	@Test
	public void testClear() {
		NDC.push("one");
		NDC.push("two");
		
		Assert.assertEquals(2, NDC.getDepth());
		NDC.clear();
		Assert.assertEquals(0, NDC.getDepth());
	}
	
	@Test
	public void testSetMaxDepth() {
		NDC.push("one");
		NDC.push("two");
		NDC.push("three");
		
		Assert.assertEquals(3, NDC.getDepth());
		NDC.setMaxDepth(1);
		Assert.assertEquals(1, NDC.getDepth());
	}
}
