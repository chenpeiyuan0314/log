package org.yuan.project.log.spi;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

public class ThrowableInfo {

	public ThrowableInfo(Throwable throwable) {
		this.throwable = throwable;
	}
	
	public String[] getThrowableStrRep() {
		List<String> lines = new ArrayList<String>();
		
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		throwable.printStackTrace(pw);
		
		StringReader sr = new StringReader(sw.toString());
		BufferedReader br = new BufferedReader(sr);
		try {
			String line = null;
			while((line = br.readLine()) != null) {
				lines.add(line);
			}
		} catch(Exception e) {}
		
		return lines.toArray(new String[0]);
	}
	
	private Throwable throwable;
}
