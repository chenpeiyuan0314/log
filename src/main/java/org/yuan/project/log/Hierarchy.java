package org.yuan.project.log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.yuan.project.log.spi.LoggerRepository;

public class Hierarchy implements LoggerRepository {

	public Hierarchy(Logger root) {
		this.root = root;
		table = new HashMap<String,Object>();
	}

	@Override
	public Logger getRootLogger() {
		return root;
	}

	@Override
	public Logger getLogger(String name) {
		Object obj = table.get(name);
		
		if(obj instanceof Logger) {
			return (Logger)obj;
		}
		if(obj instanceof List) {
			Logger logger = new Logger(name);
			List<?> list = (List<?>)obj;
			table.put(name, logger);
			updateChildren(list, logger);
			updateParent(logger);
			return logger;
		}
		if(obj == null) {
			Logger logger = new Logger(name);
			table.put(name, logger);
			updateParent(logger);
			return logger;
		}
		
		return null;
	}
	
	@Override
	public Iterable<Logger> getCurrentLoggers() {
		List<Logger> list = new ArrayList<Logger>();
		for(Object obj : table.values()) {
			if(obj instanceof Logger) {
				list.add((Logger)obj);
			}
		}
		return list;
	}

	@Override
	public void resetConfiguration() {
		root.setLevel(Level.DEBUG);
		
		synchronized(table) {
			shutdown();
			
			Iterable<Logger> iter = getCurrentLoggers();
			for(Logger log : iter) {
				log.setLevel(null);
			}
		}
	}

	@Override
	public void shutdown() {
		root.closeNestedAppenders();
		root.removeAllAppenders();
		
		synchronized(table) {
			Iterable<Logger> list = getCurrentLoggers();
			for(Logger item : list) {
				item.closeNestedAppenders();
				item.removeAllAppenders();
			}
		}
	}

	private void updateParent(Logger logger) {
		String name = logger.getName();
		
		int i = name.length();
		while((i = name.lastIndexOf(".", i - 1)) != -1) {
			String sub = name.substring(0, i);
			Object obj = table.get(sub);
			if(obj instanceof Logger) {
				Logger parent = (Logger)obj;
				logger.parent = parent;
				return;
			}
			if(obj instanceof List) {
				@SuppressWarnings("unchecked")
				List<Logger> list = (List<Logger>)obj;
				list.add(logger);
				continue;
			}
			if(obj == null) {
				List<Logger> list = new ArrayList<Logger>();
				list.add(logger);
				table.put(sub, list);
				continue;
			}
		}
		
		if(logger.parent == null) {
			logger.parent = root;
		}
	}
	
	private void updateChildren(List<?> list, Logger logger) {
		for(int i=0; i<list.size(); i++) {
			Logger child = (Logger)list.get(i);
			if(!child.parent.getName().startsWith(logger.getName())) {
				logger.parent = child.parent;
				child.parent = logger;
			}
		}
	}
	
	//--------------------------------------------------------
	//
	//--------------------------------------------------------
	private Logger root;
	private Map<String,Object> table;
}
