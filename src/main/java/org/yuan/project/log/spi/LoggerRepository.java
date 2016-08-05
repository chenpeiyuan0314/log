package org.yuan.project.log.spi;

import org.yuan.project.log.Logger;

public interface LoggerRepository {

	Logger getRootLogger();
	
	Logger getLogger(String name);
	
	void resetConfiguration();
	
	void shutdown();
	
	Iterable<Logger> getCurrentLoggers();
	
}
