package com.briup.myjdbc;
import com.briup.bean.Student;
public class ObjectTest {
	public static void main(String[] args) {
		Student stu = new Student(1,"alan","f",23,"guangdong");
		System.out.println(stu.hashCode());
		System.out.println(stu.toString());
		
		changeStu(stu);
		System.out.println(stu.toString());
		
		
		changeStu2(stu);
		System.out.println(stu.toString());
	}
	public static void changeStu(Student stu){
		stu = new Student(2,"bob","f",20,"guangdong");
		System.out.println(stu.hashCode());
	}
	public static void changeStu2(Student stu){
		stu.setName("jade");
		System.out.println(stu.hashCode());
	}
}
