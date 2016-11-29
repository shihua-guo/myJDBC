package com.briup.util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {
	private static String driver;
	private static String url;
	private static String user;
	private static String password;
	private static Properties prop;
	static {
		prop = new Properties();
		try {
			//读取文件，获取对应的参数
			prop.load(ConnectionFactory.class.getResourceAsStream("connection.properties"));
			driver = prop.getProperty("driver");
			url = prop.getProperty("url");
			user = prop.getProperty("user");
			password = prop.getProperty("password");
			//注册驱动
			Class.forName(driver);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 从unpool中获取一个conn，每次都是新的
	 * @return conn 
	 */
	@SuppressWarnings("finally")
	public static Connection getConnection() {
		Connection conn=null;
		try {
			conn = DriverManager.getConnection(url, user, password);
			return conn;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			if(conn != null){
				return conn;
			}
			else {
				System.out.println("获取数据库连接失败！");
				return null;
			}
		}
	}

}
