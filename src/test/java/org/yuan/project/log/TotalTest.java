package org.yuan.project.log;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
	LevelTest.class,
	SimpleLayoutTest.class,
	LogLogTest.class,
	HierarchyTest.class,
	ConsoleAppenderTest.class,
	WriterAppenderTest.class,
	LoggerTest.class
})
public class TotalTest {}
