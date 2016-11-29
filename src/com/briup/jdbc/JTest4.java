package com.briup.jdbc;
import static com.briup.util.ConnectionFactory.getConnection;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.junit.Test;

import com.briup.util.ResultHandle;
import com.briup.util.SqlExcutor;
/**
 * 1.建表
 * 2:插入1000条记录

3:查询刚插入的记录并打印在控制台上

4:更新表中所有记录的salary,在以前基础上加200

5:查询刚更新的记录并打印在控制台上

6:删除表中所有记录,如果删除成功就打印输出“删除成功“，否则输出”删除不成功“

7:删除表对象
 * 封装conn和sql语句
 * @author alan
 * @date Sep 27, 2016 3:09:04 PM
 */

public class JTest4 {
	
	private Connection conn = getConnection();
	/**
	 * 测试封装的FC和Excutor
	 */
	@Test
	public void testPack(){
		SqlExcutor excutor = new SqlExcutor(conn);
		String sql = "insert into s_emp values(1,'alan','alan',1000,sysdate-99)";
		excutor.CommSql(sql);
	}
	
	/**
	 * 创建表
	 */
	@Test
	public void createTable(){
		SqlExcutor excutor = new SqlExcutor(conn);
		String sql = "create table s_emp("
				+ "id number(5) primary key,"
				+ "name varchar(10) not null,"
				+ "passwd varchar(10),"
				+ "salary number(5),"
				+ "start_date date)";
		excutor.CommSql(sql);
		try {
			conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 插入数据1000行
	 */
	@Test
	public void insert(){
		String sql = "insert into s_emp values(?,?,?,?,?)";
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);
			for(int i=1;i<=1000;i++){
				ps.setInt(1, i);
				ps.setString(2,"alan"+i);
				ps.setString(3,"p"+i);
				ps.setLong(4, (long) ((Math.random()*2000)+i*(Math.random()*100)));
				ps.setDate(5, new Date(i*3000000) );
				ps.addBatch();
			}
			int[] count = ps.executeBatch();
			conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 测试随机数
	 */
	@Test
	public void randTest(){
		/*for(int i=0;i<100;i++){
			System.out.println(
					(long) ((Math.random()*2000)+i*(Math.random()*100)));
		}*/
		for(int i=0;i<100;i++){
			System.out.println(
					new Date(i*3000000));
		}
	}
	
	/**
	 * 查询刚插入的记录并打印在控制台上
	 */
	@Test
	public void query1(){
		String sql = "select * from s_emp where id<10";
		SqlExcutor excutor = new SqlExcutor(conn);
		ResultHandle rh = new ResultHandleImpl();
		excutor.QuerySql(sql, rh);
	
	}
	
	/**
	 * 更新表中所有记录的salary,在以前基础上加200
	 */
	@Test
	public void update1(){
		String sql = "update s_emp set salary=salary+200 where id<10";
		SqlExcutor excutor = new SqlExcutor(conn);
		excutor.CommSql(sql);
		try {
			conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			try {
				conn.commit();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/*
	 * 删除表中所有记录,
	 * 如果删除成功就打印输出“删除成功“，否则输出”删除不成功“
	 */
	@Test
	public void deleteAll() throws Exception{
		String sql = "delete from s_emp";
		SqlExcutor excutor = new SqlExcutor(conn);
		try {
			excutor.CommSql(sql);
			conn.commit();
			System.out.println("删除成功！");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new Exception("删除表失败！");
		}
	}
	
	/*
	 * 删除表对象
	 */
	@Test
	public void dropTable(){
		String sql = "drop table s_emp";
		SqlExcutor excutor = new SqlExcutor(conn);
		excutor.CommSql(sql);
	}
}
