package com.briup.myjdbc;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.junit.Test;

/*
 * 跟着老师打，第二种链接方式,使用Properties文件
 */
public class FirstJDBC7 {
	private String driver;     
	private String url;        
	private String user;       
	private String password;   
	private Properties prop = new Properties();
	
	@Test
	public void connectionTest() {
		Connection conn = null;
		try {
			prop.load(FirstJDBC7.class.getResourceAsStream("pro.properties"));
			driver = prop.getProperty("driver");
			url = prop.getProperty("url");
			user = prop.getProperty("user");
			password = prop.getProperty("password");
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user,password);
			System.out.println(conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
