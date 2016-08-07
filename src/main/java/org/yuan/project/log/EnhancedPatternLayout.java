package org.yuan.project.log;

import java.util.ArrayList;
import java.util.List;

import org.yuan.project.log.spi.LoggingEvent;
import org.yuan.project.pattern.FormattingInfo;
import org.yuan.project.pattern.PatternConverter;
import org.yuan.project.pattern.PatternParser;

public class EnhancedPatternLayout extends Layout {
	
	public EnhancedPatternLayout() {
		patternConverters = new ArrayList<PatternConverter>();
		formattingInfos = new ArrayList<FormattingInfo>();
	}

	public EnhancedPatternLayout(String pattern) {
		this.pattern = pattern;
	}

	@Override
	public String format(LoggingEvent event) {
		StringBuffer sb = new StringBuffer();
		PatternParser.parse(pattern, patternConverters, formattingInfos, PatternParser.getPatternLayoutRules());
		
		int len = Integer.min(patternConverters.size(), formattingInfos.size());
		int beg = 0;
		for(int i=0; i<len; i++) {
			patternConverters.get(i).format(event, sb);
			formattingInfos.get(i).format(beg, sb);
			beg = sb.length();
		}
		
		return sb.toString();
	}

	public String getConversionPattern() {
		return pattern;
	}

	public void setConversionPattern(String pattern) {
		this.pattern = pattern;
	}
	
	/*
	protected PatternParser createPatternParser(String pattern) {
		return new PatternParser(pattern);
	}
	*/
	
	//-----------------------------------------------------------------
	//
	//-----------------------------------------------------------------
	private String pattern;
	private List<PatternConverter> patternConverters;
	private List<FormattingInfo> formattingInfos;
}
