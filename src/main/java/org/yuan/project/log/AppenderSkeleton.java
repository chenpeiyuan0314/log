package org.yuan.project.log;

import org.yuan.project.log.spi.LoggingEvent;
import org.yuan.project.log.spi.OptionHandler;

public abstract class AppenderSkeleton implements Appender, OptionHandler{
	
	public abstract void append(LoggingEvent event);

	@Override
	public void doAppend(LoggingEvent event) {
		if(!isAsSevereAsThreshold(event.getLevel())) {
			return;
		}
		
		append(event);
	}

	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public Layout getLayout() {
		return layout;
	}
	
	@Override
	public void setLayout(Layout layout) {
		this.layout = layout;
	}
	
	public Level getThreshold() {
		return level;
	}

	public void setThreshold(Level level) {
		this.level = level;
	}
	
	public boolean isAsSevereAsThreshold(Level level) {
		return (this.level == null) || (level.isGreaterOrEqual(this.level));
	}

	//-----------------------------------------------------------------
	//
	//-----------------------------------------------------------------
	private String name;
	private Level level;
	protected Layout layout;
}
