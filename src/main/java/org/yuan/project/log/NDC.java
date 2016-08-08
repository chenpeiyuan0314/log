package org.yuan.project.log;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

@SuppressWarnings({"rawtypes", "unchecked"})
public class NDC {
	
	public static void clear() {
		Stack stack = getCurrentStack();
		if(stack != null) {
			stack.setSize(0);
		}
	}
	
	public static String get() {
		Stack stack = getCurrentStack();
		if(stack != null && !stack.isEmpty()) {
			Node node = (Node)stack.peek();
			return node.fullMessage;
		}
		return null;
	}
	
	public static String peek() {
		Stack stack = getCurrentStack();
		if(stack != null && !stack.isEmpty()) {
			Node node = (Node)stack.peek();
			return node.message;
		}
		return null;
	}
	
	public static void push(String msg) {
		Stack stack = getCurrentStack();
		if(stack == null) {
			stack = new Stack();
			Node node = new Node(msg, null);
			stack.push(node);
			HT.put(Thread.currentThread(), stack);
			return;
		}
		if(stack.isEmpty()) {
			Node node = new Node(msg, null);
			stack.push(node);
			return;
		}
		Node node = new Node(msg, (Node)stack.peek());
		stack.push(node);
		return;
	}
	
	public static String pop() {
		Stack stack = getCurrentStack();
		if(stack != null && !stack.isEmpty()) {
			Node node = (Node)stack.pop();
			return node.message;
		}
		return null;
	}
	
	public static int getDepth() {
		Stack stack = getCurrentStack();
		if(stack == null) {
			return 0;
		}
		return stack.size();
	}
	
	public static void setMaxDepth(int max) {
		Stack stack = getCurrentStack();
		if(stack != null && max < stack.size()) {
			stack.setSize(max);
		}
	}
	
	public static Stack cloneStack() {
		Stack stack = getCurrentStack();
		if(stack == null) {
			return null;
		}
		return (Stack)stack.clone();
	}
	
	public static void remove() {
		HT.remove(Thread.currentThread());
	}
	
	public static void inherit(Stack stack) {
		if(stack != null) {
			HT.put(Thread.currentThread(), stack);
		}
	}
	
	private static Stack getCurrentStack() {
		return HT.get(Thread.currentThread());
	}

	//-------------------------------------------------------------
	//
	//-------------------------------------------------------------
	private static final Map<Thread,Stack> HT = new HashMap<Thread,Stack>();
	
	private static class Node {
		public Node(String message, Node prev) {
			this.message = message;
			if(prev == null) {
				this.fullMessage = message;
				return;
			}
			this.fullMessage = prev.fullMessage + " " + message;
		}
		
		private String message;
		private String fullMessage;
	}
}
