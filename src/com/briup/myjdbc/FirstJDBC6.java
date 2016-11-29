package com.briup.myjdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.junit.Test;

/*
 * 跟着老师打，第二种链接方式
 */
public class FirstJDBC6 {
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String user = "alan";
	private String password = "root";
	
	@Test
	public void connectionTest() {
		Connection conn = null;
		try {
			Class.forName(driver);
			Properties prop = new Properties();
			prop.setProperty("user", user);
			prop.setProperty("password", password);
			conn = DriverManager.getConnection(url, prop);
			System.out.println(conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
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
