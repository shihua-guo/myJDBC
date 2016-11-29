package com.briup.jdbc;

import java.lang.reflect.Field;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.briup.bean.Student;

/**
 * 构建一个学生类，包含id,名字，性别，年龄，地址 由对象创建s_stu表
 * 作业1
 * @author alan
 * @date Sep 26, 2016 2:54:06 PM
 */
public class JTest3 {
	public static void main(String[] args) {
		Connection conn = null;
		PreparedStatement ps = null;
		Statement sta = null;
		ResultSet rs = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "alan", "root");
			sta = conn.createStatement();
			// 创建表
//			 createTable(sta);
			createTableR(conn,Student.class);
			// insert 通过statement
//			Student stu = new Student(2, "jade", "f", 23, "dongguan");
//			 insert(sta, stu);
			
			// insert 通过pre statement
//			insertPre(conn, stu);
			
//			delete by id
//			delete(sta,1);
//			conn.commit();
			
//			Student stu = new Student(1, "bob", "m", 23, "liuzhou");
//			updateAll(sta,1,stu);
			
			//查询
//			select(sta);
			
//			conn.commit();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (sta != null) {
					sta.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	/*
	 * 创建表
	 */
	public static void createTable(Statement sta) {
		String sql = "create table student(" + "id number(10) not null," + "name varchar2(20) not null,"+"gender char(1) not null," + "age number(5),"
				+ "address varchar2(20) )";
		try {
			sta.execute(sql);
			// 如果有结果就返回true如果没有rs就是返回false
			// System.out.println(sta.execute(sql));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*
	 * 创建表：通过反射获取属性名称
	 */
	public static void createTableR(Connection conn, Class<?> c) throws SecurityException, ClassNotFoundException{
//		Field[] fileds = stu.getClass().getFields();
		Field[] fileds = c.getDeclaredFields();
		for(Field f:fileds){
			System.out.println(f.getName());
		}
		PreparedStatement ps = null;
		
		String sql = "create table student2("
				+ fileds[0].getName()+" number(10) not null,"
				+ fileds[1].getName()+" varchar2(10) not null,"
				+ fileds[2].getName()+" number(10) ,"
				+ fileds[3].getName()+" char(1),"
				+ fileds[4].getName()+" varchar2(20) )";
		try {
			ps = conn.prepareStatement(sql);
			ps.execute(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			try {
				if(ps!=null)
					ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	/*
	 * 插入数据：通过statement
	 */
	public static void insert(Statement sta, Student stu) {
		String sql = "insert into student values(" 
				+ stu.getId() +","
				+ "'" + stu.getName() + "'" + ","
				+ "'" + stu.getGender() + "'" + ","
				+ stu.getAge() + "," 
				+ "'" + stu.getAddress() + "'" + ")";
		try {
			sta.execute(sql);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*
	 * 插入数据，通过prestatement
	 */
	public static void insertPre(Connection conn, Student stu) {
		String sql = "insert into student values(?,?,?,?,?)";
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, stu.getId());
			ps.setString(2, stu.getName());
			ps.setString(3, stu.getGender());
			ps.setInt(4, stu.getAge());
			ps.setString(5, stu.getAddress());
			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (ps != null)
					ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/*
	 * 删除数据
	 */
	public static void delete(Statement sta,int id) {
		String sql = "delete from student where id="+id;
		System.out.println("delete");
		try {
			sta.execute(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*
	 * 更新数据
	 */
	public static void updateAll(Statement sta,int id,Student stu) {
		String sql = "update student set "
				+ "id="+stu.getId()+","
				+ "name= '"+stu.getName()+"',"
				+ "gender='"+stu.getGender()+"',"
				+ "age="+stu.getAge()+","
				+ "address='"+stu.getAddress()+"' "
				+ "where id="+id;
		try {
			sta.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*
	 * 查询数据
	 */
	public static void select(Statement sta) {
		String sql = "select * from student";
		try {
			ResultSet rs = sta.executeQuery(sql);
			List<Student> stuList = new ArrayList<Student>();
			while(rs.next()){
				Student stu = new Student();
				stu.setId(rs.getInt(1));
				stu.setName(rs.getString(2));
				stu.setGender(rs.getString(3));
				stu.setAge(rs.getInt(4));
				stu.setAddress(rs.getString(5));
				stuList.add(stu);
			}
			for(Student stu:stuList){
				System.out.println(stu.toString());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
