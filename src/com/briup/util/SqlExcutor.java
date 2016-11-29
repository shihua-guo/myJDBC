package com.briup.util;

import static com.briup.util.ConnectionFactory.getConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SqlExcutor {
	private Connection conn;

	public SqlExcutor() {
	}

	public SqlExcutor(Connection conn) {
		if (conn == null) {
			conn = getConnection();
		}
		this.conn = conn;
		try {
			// 为什么要设置为false
			this.conn.setAutoCommit(false);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 用于执行select语句的方法
	 * 
	 * @param sql：传入的sql语句
	 * @param rh：处理结果的
	 */
	public void QuerySql(String sql, ResultHandle rh) {
		Statement sta = null;
		ResultSet rs = null;
		try {
			sta = conn.createStatement();
			rs = sta.executeQuery(sql);
			if (rh != null) {
				rh.handle(rs);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (sta != null)
					sta.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public int CommSql(String sql) {
		Statement sta = null;
		int count=0;
		try {
			sta = conn.createStatement();
			count = sta.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
				try {
					if (sta != null)
						sta.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		return count;
	}
}
