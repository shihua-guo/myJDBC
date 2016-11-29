package com.briup.jdbc;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JTest2 {
	public static void main(String[] args) {
		Connection conn = null;
		Statement sta = null;
		ResultSet rs = null;
		Driver driver = new oracle.jdbc.driver.OracleDriver();
		try {
			Properties pro = new Properties();
			pro.setProperty("user", "alan");
			pro.setProperty("password", "root");
			DriverManager.registerDriver(driver);
			conn = driver.connect(
					"jdbc:oracle:thin:@localhost:1521:xe", pro);
			sta = conn.createStatement();
			String  sql = "select * from teacher";
			rs = sta.executeQuery(sql);
			while(rs.next()){
				System.out.println(rs.getString(1));
				System.out.println(rs.getString(2));
				System.out.println(rs.getString(3));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			try {
				if(rs!=null){
					rs.close();
				}
				if(sta!=null){
					sta.close();
				}
				if(conn!=null){
					conn.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
