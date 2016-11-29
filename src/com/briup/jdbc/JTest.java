package com.briup.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JTest {
	public static void main(String[] args) {
		Connection conn = null;
		Statement sta = null;
		ResultSet rs = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn=DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:XE","alan","root");
			sta = conn.createStatement();
			String sql = "select * from teacher";
			rs = sta.executeQuery(sql);
			while(rs.next()){
				System.out.println(
						"id:"+rs.getInt(1)+" name:"+rs.getString(2)+" age:"+rs.getInt(3));
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
