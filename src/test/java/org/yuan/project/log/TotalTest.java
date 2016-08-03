package org.yuan.project.log;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
	LevelTest.class,
	SimpleLayoutTest.class
})
public class TotalTest {}
