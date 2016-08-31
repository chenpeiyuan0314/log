package org.yuan.project.log.pattern;

public class FileDatePatternConverter {
	
	private FileDatePatternConverter() {}

	public static PatternConverter getInstance(String[] option) {
		if(option == null || option.length == 0) {
			return DatePatternConverter.getInstance(new String[] {"yyyy-MM-dd"});
		}
		
		return DatePatternConverter.getInstance(option);
	}
}
