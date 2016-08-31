package org.yuan.project.log.pattern;

import java.util.Map.Entry;

import org.yuan.project.log.spi.LoggingEvent;

public class PropertiesPatternConverter extends PatternConverter {
	
	public PropertiesPatternConverter(String[] option) {
		if(option != null && option.length > 0) {
			this.option = option[0];
		}
	}

	@Override
	public void format(LoggingEvent event, StringBuffer sbuf) {
		if(option == null) {
			sbuf.append("{");
			for(Entry<String,Object> entry : event.getProperties().entrySet()) {
				sbuf.append("{").append(entry.getKey()).append(",").append(entry.getValue()).append("}");
			}
			sbuf.append("}");
			return;
		}
		
		sbuf.append(event.getMDC(option));
	}
	
	public static PatternConverter getInstance(String[] option) {
		return new PropertiesPatternConverter(option);
	}

	private String option;
}
