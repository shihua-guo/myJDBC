package com.briup.bean;

import java.util.Date;

public class Student {
	private int id;
	private String name;
	private int age;
	private String gender;
	private String address;
	public Student(){}
	public Student(int id, String name, String gender,int age, String address) {
		this.id = id;
		this.name = name;
		this.gender = gender;
		this.setAge(age);
		this.setAddress(address);
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", age=" + age + ", gender=" + gender + ", address=" + address
				+ "]";
	}
	
}
