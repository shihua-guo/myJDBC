package com.briup.myjdbc;

import com.briup.jdbc.Student;
import com.briup.jdbc.Teacher;

public class EqualTest {
	public static void main(String[] args) {
		Student stu = new Student(1,"",23);
//		Object stu1 = new Student(1,"",23);
		Object stu1 = new Student(1,"",23);
		Object stu2 = stu;
		Teacher t1=new Teacher();
		System.out.println(stu);
		System.out.println(stu2);
		System.out.println(stu==stu2);
	}
}
