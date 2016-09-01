package org.yuan.project.demo;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;

import org.junit.Test;

public class ReflectDemo {

	//@Test
	public void test() throws Exception {
		Class<?> clazz = Person.class;
		Method setter = clazz.getMethod("setName", String.class);
		Method getter = clazz.getMethod("getName");
		Constructor<?> ctr = clazz.getConstructor(String.class);
		
		Object p = ctr.newInstance("Chen");
		System.out.println(getter.invoke(p));
		setter.invoke(p, "Yuan");
		System.out.println(getter.invoke(p));
	}
	
	//@Test
	public void test2() throws Exception {
		Class<?> clazz = Person.class;
		Method setter = clazz.getMethod("setName", String.class);
		Method getter = clazz.getMethod("getName");
		Method newInstance = clazz.getMethod("newInstance", String.class);
		
		Object p = newInstance.invoke(null, "Chen");
		System.out.println(getter.invoke(p));
		setter.invoke(p, "Yuan");
		System.out.println(getter.invoke(p));
	}
	
	//@Test
	public void test3() throws Exception {
		BeanInfo bi = Introspector.getBeanInfo(Person.class);
		PropertyDescriptor[] props = bi.getPropertyDescriptors();
		PropertyDescriptor prop = null;
		for(PropertyDescriptor item : props) {
			System.out.println(item.getName() + "," + item.getDisplayName());
			if("name".equals(item.getName())) {
				prop = item;
			}
		}
		Object obj = Person.class.getConstructor(String.class).newInstance("Chen");
		
		Method getter = prop.getReadMethod();
		Method setter = prop.getWriteMethod();
		System.out.println(getter.invoke(obj));
		setter.invoke(obj, "Yuan");
		System.out.println(getter.invoke(obj));
	}
	
	//@Test
	public void test4() throws Exception {
		Class<?> clazz = Person.class;
		StringBuffer sb = new StringBuffer();
		
		sb.append(Modifier.toString(clazz.getModifiers()));
		sb.append(clazz.isInterface() ? "interface" : "class" + " ");
		sb.append(clazz.getSimpleName());
		sb.append(" {");
		sb.append(SEP);
		
		print(sb, clazz.getConstructors(), clazz.getSimpleName());
		sb.append(SEP);
		print(sb, clazz.getDeclaredMethods());
		sb.append(SEP);
		print(sb, clazz.getDeclaredFields());
		
		sb.append("}");
		clazz.getSimpleName();
		System.out.println(sb.toString());
	}
	
	@Test
	public void test5() throws Exception {
		Class<?> clazz = Class.forName("org.yuan.project.demo.Person");
		//Class<?> clazz = Person.class;
		System.out.println(clazz.getName());
		System.out.println(clazz.getPackage().getName());
		//System.out.println(clazz.getTypeName());
	}
	
	private void print(StringBuffer sb, Constructor[] constructors, String clazzName) {
		for(Constructor constructor : constructors) {
			sb.append(TAB);
			sb.append(Modifier.toString(constructor.getModifiers()) + " ");
			sb.append(clazzName);
			print(sb, constructor.getParameters());
			sb.append(SEP);
		}
	}
	
	private void print(StringBuffer sb, Field[] fields) {
		for(Field field : fields) {
			sb.append(TAB);
			sb.append(Modifier.toString(field.getModifiers()) + " ");
			sb.append(field.getType().getSimpleName() + " ");
			sb.append(field.getName() + ";");
			sb.append(SEP);
		}
	}
	
	private void print(StringBuffer sb, Method[] methods) {
		for(Method method : methods) {
			print(sb, method.getAnnotations());
			sb.append(TAB);
			sb.append(Modifier.toString(method.getModifiers()) + " ");
			sb.append(method.getReturnType().getSimpleName() + " ");
			sb.append(method.getName());
			print(sb, method.getParameters());
			sb.append(SEP);
		}
	}
	
	private void print(StringBuffer sb, Parameter[] parameters) {
		sb.append("(");
		
		for(int i=0; i<parameters.length; i++) {
			if(i > 0) {
				sb.append(", ");
			}
			Parameter parameter = parameters[i];
			sb.append(parameter.getType().getSimpleName());
		}
		
		sb.append(");");
	}
	
	private void print(StringBuffer sb, Annotation[] annotations) {
		for(Annotation annotation : annotations) {
			sb.append(TAB);
			sb.append("@");
			sb.append(annotation.annotationType().getSimpleName());
			sb.append(SEP);
		}
	}
	
	private static final String SEP = System.getProperty("line.separator");
	private static final String TAB = "  ";
}

class Person {
	public static Person newInstance(String name, String sex) {
		return new Person(name, sex);
	}
	
	public Person(String name, String sex) {
		this.name = name;
		this.sex  = sex;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public static Integer getId() {
		return id;
	}
	
	@Deprecated
	public String toString() {
		return "Person [sex=" + sex + ", name=" + name + "]";
	}

	private String sex;
	private String name;
	private static Integer ct = 1;
	private static final Integer id = ct++;
}

