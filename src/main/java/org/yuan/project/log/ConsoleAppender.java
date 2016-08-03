package org.yuan.project.log;

import java.io.PrintStream;

import org.yuan.project.log.spi.LoggingEvent;

public class ConsoleAppender extends AppenderSkeleton {
	public static final String SYSTEM_OUT = "System.out";
	public static final String SYSTEM_ERR = "System.err";
	
	public ConsoleAppender() {}
	
	public ConsoleAppender(Layout layout) {
		this(layout, SYSTEM_OUT);
	}

	public ConsoleAppender(Layout layout, String target) {
		this.target = target;
		this.layout = layout;
		activateOptions();
	}

	@Override
	public void append(LoggingEvent event) {
		ps.print(layout.format(event));
	}
	
	@Override
	public void activateOptions() {
		if(target.equals(SYSTEM_OUT)) {
			ps = System.out;
		} else {
			ps = System.err;
		}
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	//-----------------------------------------------------------------
	//
	//-----------------------------------------------------------------
	private String target;
	private PrintStream ps;
}
