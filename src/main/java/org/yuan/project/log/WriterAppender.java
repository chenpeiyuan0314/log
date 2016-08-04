package org.yuan.project.log;

import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

import org.yuan.project.log.kit.LogLog;
import org.yuan.project.log.kit.QuietWriter;
import org.yuan.project.log.spi.LoggingEvent;

public class WriterAppender extends AppenderSkeleton {

	public WriterAppender() {}
	
	public WriterAppender(Layout layout, OutputStream os) {
		this(layout, new OutputStreamWriter(os));
	}
	
	public WriterAppender(Layout layout, Writer writer) {
		this.layout = layout;
		setWriter(writer);
	}

	@Override
	public void activateOptions() {}

	@Override
	public void append(LoggingEvent event) {
		if(closed) {
			LogLog.warn("Not allowed to write to a closed appender.");
			return;
		}
		if(qw == null) {
			LogLog.error("No output stream or file set for the appender named [" + name + "].");
			return;
		}
		if(layout == null) {
			LogLog.error("No layout set for the appender named [" + name + "].");
			return;
		}
		
		qw.write(layout.format(event));
		
		if(getImmediateFlush()) {
			qw.flush();
		}
	}
	
	public void close() {
		if(closed) {
			return;
		}
		
		closed = true;
		reset();
	}
	
	protected void closeWriter() {
		if(qw == null) {
			return;
		}
		
		try {
			qw.close();
		} catch(Exception e) {
			LogLog.error("Could not close " + qw, e);
		}
	}
	
	protected void reset() {
		closeWriter();
		qw = null;
	}
	
	//--------------------------------------------------------
	//
	//--------------------------------------------------------
	public String getEncoding() {
		return encoding;
	}

	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

	public boolean getImmediateFlush() {
		return immediateFlush;
	}

	public void setImmediateFlush(boolean immediateFlush) {
		this.immediateFlush = immediateFlush;
	}

	public Writer getWriter() {
		return qw;
	}

	public void setWriter(Writer writer) {
		reset();
		this.qw = new QuietWriter(writer);
	}
	
	//--------------------------------------------------------
	//
	//--------------------------------------------------------
	private String encoding;
	private boolean immediateFlush = true;
	private QuietWriter qw;
	private boolean closed;
}
