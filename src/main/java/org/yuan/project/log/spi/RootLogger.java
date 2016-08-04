package org.yuan.project.log.spi;

import org.yuan.project.log.Level;
import org.yuan.project.log.Logger;
import org.yuan.project.log.kit.LogLog;

public class RootLogger extends Logger {
	
	public RootLogger(Level level) {
		super("root");
		this.level = level;
	}

	@Override
	public void setLevel(Level level) {
		if(level == null) {
			LogLog.error("You have tried to set a null level to root.", new Throwable());
			return;
		}
		
		this.level = level;
	}
	
}
