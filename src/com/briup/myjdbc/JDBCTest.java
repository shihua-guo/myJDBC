package com.briup.myjdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCTest {	
	public static void main(String[] args) {
		Connection con = null;
		Statement sta = null;
		
		try {
			String driver = "oracle.jdbc.driver.OracleDriver";
			String user = "alan";
			String pawd = "root";
			String url = "jdbc:oracle:thin:@localhost:1521:XE";
			//注册驱动
			Class.forName(driver);
			//获取链接
			con = DriverManager.getConnection(url, user, pawd);
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			if(con!=null){
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}
}
