package org.yuan.project.log;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.yuan.project.log.pattern.DatePatternConverterTest;

@RunWith(Suite.class)
@SuiteClasses({
	LevelTest.class,
	SimpleLayoutTest.class,
	LogLogTest.class,
	HierarchyTest.class,
	ConsoleAppenderTest.class,
	WriterAppenderTest.class,
	LoggerTest.class,
	EnhancedPatternLayoutTest.class,
	LoggerTest.class,
	MDCTest.class,
	NDCTest.class,
	
	DatePatternConverterTest.class
})
public class TotalTest {}
