package org.yuan.project.pattern;

public class FormattingInfo {
	
	public FormattingInfo() {
		minLength = 0;
		maxLength = Integer.MAX_VALUE;
		leftAlign = false;
	}
	
	public void format(int beg, StringBuffer buf) {
		int rawLength = buf.length() - beg;
		
		if(rawLength > maxLength) {
			buf.delete(beg, buf.length() - maxLength);
		} else if(rawLength < minLength) {
			int len = minLength - rawLength;
			
			if(leftAlign) {
				for(int i=0; i<len; i++) {
					buf.append(" ");
				}
			} else {
				for(int i=0; i<len; i++) {
					buf.insert(beg, " ");
				}
			}
		}
	}
	
	public int getMinLength() {
		return minLength;
	}
	
	public void setMinLength(int minLength) {
		this.minLength = minLength;
	}
	
	public int getMaxLength() {
		return maxLength;
	}
	
	public void setMaxLength(int maxLength) {
		this.maxLength = maxLength;
	}
	
	public boolean isLeftAlign() {
		return leftAlign;
	}
	
	public void setLeftAlign(boolean leftAlign) {
		this.leftAlign = leftAlign;
	}

	//-----------------------------------------------------------
	//
	//-----------------------------------------------------------
	private int minLength;
	private int maxLength;
	private boolean leftAlign;
}
