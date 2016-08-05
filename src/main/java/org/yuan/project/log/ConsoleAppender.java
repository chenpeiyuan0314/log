package org.yuan.project.log;

import java.io.OutputStreamWriter;
import java.io.PrintStream;

import org.yuan.project.log.kit.QuietWriter;

public class ConsoleAppender extends WriterAppender {
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
	public void activateOptions() {
		if(target.equals(SYSTEM_OUT)) {
			qw = new QuietWriter(new OutputStreamWriter(System.out));
		} else {
			qw = new QuietWriter(new OutputStreamWriter(System.err));
		}
	}
	
	@Override
	protected void closeWriter() {}

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
}
