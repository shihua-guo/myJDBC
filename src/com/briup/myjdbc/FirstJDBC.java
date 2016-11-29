package com.briup.myjdbc;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class FirstJDBC {
	public static void main(String[] args) {
		/*if(args.length < 3){
			System.out.println("Usage:java -djdbc.driver=<jdbc driver>"
					+FirstJDBC.class.getName()+"dburl user password");
			System.exit(1);
		}*/
		Connection con = null;
		Statement stm = null;
		PreparedStatement prstm = null;
		ResultSet rs = null;
		try {
			//1.register jdbc driver
			Class.forName("oracle.jdbc.driver.OracleDriver");//????zhuce qudong 
			Driver driver = new oracle.jdbc.driver.OracleDriver();
			DriverManager.registerDriver(driver);
			//2.create database connection through driver manager
			con = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:XE","alan","root");
			stm = con.createStatement();
			
			//3.prepare statement
			prstm = con.prepareStatement("insert into account ("
					+ "accountno,name,balance) values(?,?,?)");
			for(int i=1;i<=10;i++){
				prstm.setInt(1, i);
				prstm.setString(2, "alan"+i);
				prstm.setDouble(3, 3000.0d+10*i);
				prstm.execute();
			}
			//4.statement
			stm = con.createStatement();
			String sql = "select * from account";
			rs = stm.executeQuery(sql);
			while(rs.next()){
				System.out.println("accoutno:"+rs.getString(1));
				System.out.println("name:"+rs.getString(2));
				System.out.println("balance:"+rs.getString(3));
			}
		} /*catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} */catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
				try {
					if(rs!=null){
						rs.close();
					}
					if(prstm != null){
						prstm.close();
					}
					if(stm != null){
						stm.close();
					}
					if(con!=null){
						con.close();
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}
}
