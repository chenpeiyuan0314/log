package org.yuan.project.log;

import java.util.HashMap;
import java.util.Map;

public class MDC {

	public static Map<String,Object> getContext() {
		return LTM.get();
	}
	
	public static Object get(String key) {
		Map<String,Object> m = LTM.get();
		if(m == null) {
			return null;
		}
		return m.get(key);
	}
	
	public static void clear() {
		Map<String,Object> m = LTM.get();
		if(m == null) {
			return;
		}
		m.clear();
		LTM.set(null);
	}
	
	public static void put(String key, Object val) {
		Map<String,Object> m = LTM.get();
		if(m == null) {
			m = new HashMap<String,Object>();
			LTM.set(m);
		}
		m.put(key, val);
	}
	
	public static void remove(String key) {
		Map<String,Object> m = LTM.get();
		if(m == null) {
			return;
		}
		m.remove(key);
	}
	
	//------------------------------------------------------------------
	//
	//------------------------------------------------------------------
	private static final ThreadLocal<Map<String,Object>> LTM = 
		new ThreadLocal<Map<String,Object>>();
}
