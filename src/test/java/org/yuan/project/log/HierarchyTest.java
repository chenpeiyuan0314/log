package org.yuan.project.log;

import org.junit.Assert;
import org.junit.Test;
import org.yuan.project.log.spi.LoggerRepository;

public class HierarchyTest {

	@Test
	public void testGetLogger() {
		LoggerRepository hierarchy = new Hierarchy();
		Logger root = hierarchy.getRootLogger();
		Logger a = hierarchy.getLogger("a");
		Logger ab = hierarchy.getLogger("a.b");
		Logger abc = hierarchy.getLogger("a.b.c");
		
		Assert.assertEquals(root, a.getParent());
		Assert.assertEquals(a, ab.getParent());
		Assert.assertEquals(ab, abc.getParent());
	}
}
