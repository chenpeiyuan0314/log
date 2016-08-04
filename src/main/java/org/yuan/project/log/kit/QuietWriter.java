package org.yuan.project.log.kit;

import java.io.FilterWriter;
import java.io.Writer;

public class QuietWriter extends FilterWriter {

	public QuietWriter(Writer out) {
		super(out);
	}

	@Override
	public void flush(){
		try {
			out.flush();
		} catch(Exception e) {
			LogLog.error("Failed to flush " + this , e);
		}
	}

	@Override
	public void write(String str){
		try {
			out.write(str);
		} catch(Exception e) {
			LogLog.error("Failed to write " + str, e);
		}
	}

}
