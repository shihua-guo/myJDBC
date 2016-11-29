package com.briup.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.briup.util.ResultHandle;

/**
 * 处理结果集：打印到控制台
 * @author alan
 * @date Sep 27, 2016 4:54:15 PM
 */
public class ResultHandleImpl implements ResultHandle {

	@Override
	public void handle(ResultSet rs) {
		try {
			while(rs.next()){
				System.out.println(
						rs.getInt(1)+","+rs.getString(2)+","
						+rs.getString(3)+","+rs.getInt(4)+","
						+rs.getDate(5));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
