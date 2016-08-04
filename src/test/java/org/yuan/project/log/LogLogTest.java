package org.yuan.project.log;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.yuan.project.log.kit.LogLog;

public class LogLogTest {
	private PrintStream out = System.out;
	private PrintStream err = System.err;
	private MockOutputStream mos = new MockOutputStream();
	
	@Before
	public void before() {
		System.setOut(new PrintStream(mos));
		System.setErr(new PrintStream(mos));
	}
	
	@After
	public void after() {
		System.setOut(out);
		System.setErr(err);
	}

	@Test
	public void testDebugMode() {
		LogLog.setDebugMode(true);
		LogLog.setQuietMode(false);
		
		LogLog.debug("This is a debug message.");
		Assert.assertTrue(check("This is a debug message."));
		LogLog.warn("This is a warn message.");
		Assert.assertTrue(check("This is a warn message."));
		LogLog.error("This is a error message.");
		Assert.assertTrue(check("This is a error message."));
	}
	
	@Test
	public void testQuietMode() {
		LogLog.setQuietMode(true);
		
		LogLog.debug("This is a debug message.");
		LogLog.warn("This is a warn message.");
		LogLog.error("This is a error message.");
		
		Assert.assertTrue(check(""));
	}
	
	@Test
	public void testConfigDebugMode() {
		System.setProperty("log4j.debug", "true");
		System.setProperty("log4j.quiet", "false");
		
		LogLog.debug("This is a debug message.");
		Assert.assertTrue(check("This is a debug message."));
		LogLog.warn("This is a warn message.");
		Assert.assertTrue(check("This is a warn message."));
		LogLog.error("This is a error message.");
		Assert.assertTrue(check("This is a error message."));
	}
	
	@Test
	public void testConfigQuietMode() {
		System.setProperty("log4j.quiet", "true");
		
		LogLog.debug("This is a debug message.");
		LogLog.warn("This is a warn message.");
		LogLog.error("This is a error message.");
		
		Assert.assertTrue(check(""));
	}
	
	private boolean check(String msg) {
		if(mos == null) {
			return true;
		}
		
		if("".equals(msg) && !"".equals(mos.sb.toString())) {
			return false;
		}
		
		int index = mos.sb.toString().indexOf(msg);
		mos.sb.setLength(0);
		return index != -1;
	}
	
	public static class MockOutputStream extends OutputStream {

		@Override
		public void write(int b) throws IOException {
			sb.append((char)b);
		}
		
		private StringBuffer sb = new StringBuffer();
	}
	
	public static class MockClassLoader extends ClassLoader {
		
        public MockClassLoader(ClassLoader parent) {
			super(parent);
		}

		@Override
        public Class<?> findClass(String name) {
            byte[] data = loadClassData(name);  
            return this.defineClass(name, data, 0, data.length);  
        }
        
        private byte[] loadClassData(String name) {
        	FileInputStream is = null;
        	ByteArrayOutputStream bs = null;
            try {
            	String path = System.getProperty("user.dir") + "\\target\\classes\\";
                name = name.replace(".", "//");  
                is = new FileInputStream(new File(path + name + ".class"));  
                bs = new ByteArrayOutputStream();  
                int b = 0;  
                while ((b = is.read()) != -1) {  
                    bs.write(b);  
                }  
                return bs.toByteArray();  
            } catch (Exception e) {  
                e.printStackTrace();  
            } finally {
            	if(is != null) {
            		try {
            			is.close();
            		} catch(Exception e) {
            			e.printStackTrace();
            		}
            	}
            	if(bs != null) {
            		try {
            			bs.close();
            		} catch(Exception e) {
            			e.printStackTrace();
            		}
            	}
            }
            return null;  
        }
	}
}
