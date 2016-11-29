package com.briup.util2;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.junit.Test;

import com.briup.util.ConnectionFactory;

public class DbUtil {
	private static String driver;
	private static String url;
	private static String user;
	private static String password;
	static {
		Properties prop = new Properties();
		try {
			prop.load(ConnectionFactory.class.getResourceAsStream("connection.properties"));
			driver = prop.getProperty("driver");
			url = prop.getProperty("url");
			user = prop.getProperty("user");
			password = prop.getProperty("password");
			// 注册驱动
			Class.forName(driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static Connection getConnection() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}

	public void closeResource(ResultSet rs, Statement sta, Connection conn) {
		try {
			if (rs != null)
				rs.close();
			if(sta!=null)
				sta.close();
			if(conn!=null)
				conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void closeResource( Statement sta, Connection conn) {
		closeResource(null, sta,conn);
	}
	public void closeResource(ResultSet rs, PreparedStatement ps, Connection conn) {
		try {
			if (rs != null)
				rs.close();
			if(ps!=null)
				ps.close();
			if(conn!=null)
				conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void test(){
		Connection conn = getConnection();
		ResultSet rs = null;
		try {
			String sql = "select count(*) from s_emp";
			rs = conn.createStatement().executeQuery(sql);
			while(rs.next()){
				System.out.println(rs.getString(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
