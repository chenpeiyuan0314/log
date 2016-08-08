package org.yuan.project.log.spi;

public class LocationInfo {
	
	public LocationInfo(Throwable th, String className) {
		if(th == null || className == null) {
			return;
		}
		
		StackTraceElement[] elements = th.getStackTrace();
		for(int i=0; i<elements.length; i++) {
			StackTraceElement element = elements[i];
			if(className.equals(element.getClassName())) {
				int index = i + 1;
				if(index < elements.length) {
					element = elements[index];
					this.className = element.getClassName();
					this.methodName = element.getMethodName();
					this.fileName = element.getFileName();
					this.lineNumber = String.valueOf(element.getLineNumber());
					
					StringBuffer sb = new StringBuffer();
					sb.append(className);
					sb.append(".");
					sb.append(methodName);
					sb.append("(");
					sb.append(fileName);
					sb.append(":");
					sb.append(lineNumber);
					sb.append(")");
					fullInfo = sb.toString();
				}
				return;
			}
		}
		
	}
	
	public LocationInfo(String fileName, String className, String methodName, String lineNumber) {
		this.fileName = fileName;
		this.className = className;
		this.methodName = methodName;
		this.lineNumber = lineNumber;
		
		StringBuffer sb = new StringBuffer();
		sb.append(className);
		sb.append(".");
		sb.append(methodName);
		sb.append("(");
		sb.append(fileName);
		sb.append(":");
		sb.append(lineNumber);
		sb.append(")");
		fullInfo = sb.toString();
	}

	public String getFullInfo() {
		return fullInfo;
	}

	public String getFileName() {
		return fileName;
	}

	public String getClassName() {
		return className;
	}

	public String getMethodName() {
		return methodName;
	}

	public String getLineNumber() {
		return lineNumber;
	}

	//-------------------------------------------------------
	// 
	//-------------------------------------------------------
	private String fullInfo;
	private String fileName;
	private String className;
	private String methodName;
	private String lineNumber;
}
