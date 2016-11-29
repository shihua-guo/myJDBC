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

public class FirstJDBC5 {
	public static void main(String[] args) {
		Connection con = null;
		Statement stm = null;
		PreparedStatement prstm = null;
		ResultSet rs = null;
		List<Student> stuList = new ArrayList();
		try {
			// 1.register jdbc driver
			// Class.forName("oracle.jdbc.driver.OracleDriver");//????
			Driver driver = new oracle.jdbc.driver.OracleDriver();
			DriverManager.registerDriver(driver);
			String user = "";
			// 2.create database connection through driver manager
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "alan", "root");
		} 	/*
			 * catch (ClassNotFoundException e) { // TODO Auto-generated catch
			 * block e.printStackTrace(); }
			 */
			catch (SQLException e) {
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

}
