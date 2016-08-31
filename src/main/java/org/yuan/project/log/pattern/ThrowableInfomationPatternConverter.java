package org.yuan.project.log.pattern;

import org.yuan.project.log.Layout;
import org.yuan.project.log.spi.LoggingEvent;

public class ThrowableInfomationPatternConverter extends PatternConverter {
	
	public ThrowableInfomationPatternConverter(String[] option) {
		if(option != null && option.length > 0) {
			if("none".equalsIgnoreCase(option[0])) {
				size = 0;
				return;
			}
			if("short".equalsIgnoreCase(option[0])) {
				size = 1;
				return;
			}
			try {
				size = Integer.parseInt(option[0]);
			} catch(Exception e) {}
		}
	}

	@Override
	public void format(LoggingEvent event, StringBuffer sbuf) {
		if(size != 0) {
			String[] rep = event.getThrowableInfo().getThrowableStrRep();
			
			int length = rep.length;
			if(size < 0) {
				length += size;
			} else if(length > size) {
				length = size;
			}
			
			for(int i=0; i<length; i++) {
				sbuf.append(rep[i]).append(Layout.LINE_SEP);
			}
		}
	}
	
	public static PatternConverter getInstance(String[] option) {
		return new ThrowableInfomationPatternConverter(option);
	}

	private int size;
}
