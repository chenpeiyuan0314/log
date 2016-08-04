package org.yuan.project.test.kit;

import java.io.IOException;
import java.io.OutputStream;

public class MockOutputStream extends OutputStream {

	@Override
	public void write(int b) throws IOException {
		sb.append((char)b);
	}

	@Override
	public String toString() {
		return sb.toString();
	}
	
	private StringBuffer sb = new StringBuffer();
}
