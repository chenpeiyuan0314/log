package org.yuan.project.demo;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;

import org.junit.Test;

public class ClassLoaderDemo {

	public static void main(String[] args) throws Exception {
		ClassLoader loader = null;
		loader = new ClassLoaderA(null);
		Class aa = loader.loadClass(A.class.getName());
		aa.newInstance();
		A.word = "aa";
		System.out.println(aa);
		loader = new ClassLoaderA(null);
		Class ba = loader.loadClass(A.class.getName());
		ba.newInstance();
		System.out.println(ba);
		
		System.out.println(aa == ba);
		System.out.println(A.word);
	}
	
	public static class ClassLoaderA extends ClassLoader {
		
        public ClassLoaderA(ClassLoader parent) {
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
            	String path = System.getProperty("user.dir") + "\\target\\test-classes\\";
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
	
	public static class A {
		static {
			System.out.println("Load Class A");
		}
		
		public static String word = null;
	}
}
