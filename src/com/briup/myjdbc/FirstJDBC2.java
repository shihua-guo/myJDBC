package com.briup.myjdbc;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.briup.jdbc.Student;

public class FirstJDBC2 {
	public static void main(String[] args) {
		/*
		 * if(args.length < 3){ System.out.println(
		 * "Usage:java -djdbc.driver=<jdbc driver>" +FirstJDBC.class.getName()+
		 * "dburl user password"); System.exit(1); }
		 */
		Connection con = null;
		Statement stm = null;
		PreparedStatement prstm = null;
		ResultSet rs = null;
		List<Student> stuList = new ArrayList();
		try {
			// 1.register jdbc driver
			 Class.forName("oracle.jdbc.driver.OracleDriver");//????
			Driver driver = new oracle.jdbc.driver.OracleDriver();
			DriverManager.registerDriver(driver);
			// 2.create database connection through driver manager
			con = DriverManager.getConnection(
					"jdbc:oracle:thin:@172.16.3.122:1521:XE", "briup", "briup");
			stm = con.createStatement();

			prstm = con.prepareStatement("insert into t_student (" + "id,name,age) values(?,?,?)");
			// 3.prepare statement
			/*
			 * for(int i=1;i<=10;i++){ prstm.setInt(1, i); prstm.setString(2,
			 * "alan"+i); if(i==2){ prstm.setString(2, "张三"); } prstm.setInt(3,
			 * 20+i); prstm.execute(); }
			 */
			// 4.statement删除id=5的行
			stm = con.createStatement();
			// String sql = "delete from t_student " + "where id = 5";
			/*
			 * String sql = "select * from t_student "; stm.execute(sql);
			 * 
			 * rs = stm.executeQuery(sql);
			 */
			/*
			 * while(rs.next()){
			 * System.out.println("accoutno:"+rs.getString(1));
			 * System.out.println("name:"+rs.getString(2));
			 * System.out.println("balance:"+rs.getString(3)); }
			 */
			stuList = findAll(stm);
			for (Student stu : stuList) {
				System.out.println(stu.toString());
			}
//			stm.execute("delete from t_student");
			 saveAll(stuList, prstm);
		} 
		
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (prstm != null) {
					prstm.close();
				}
				if (stm != null) {
					stm.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static List<Student> findAll(Statement stm) {
		String sql = "select * from t_student ";
		List<Student> stuList = new ArrayList<Student>();
		try {
			stm.execute(sql);
			ResultSet rs = stm.executeQuery(sql);
			rs = stm.executeQuery(sql);
			while (rs.next()) {
				Student stu = new Student();
				stu.setId(Long.parseLong(rs.getString(1)));
				stu.setName(rs.getString(2));
				stu.setAge(Integer.parseInt(rs.getString(3)));
				stuList.add(stu);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return stuList;
	}

	public static void saveAll(List<Student> list, PreparedStatement prstm) {
		try {
			for (Student stu : list) {
				prstm.setLong(1, stu.getId() + 10);
				prstm.setString(2, stu.getName());
				prstm.setInt(3, stu.getAge());
				prstm.execute();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
