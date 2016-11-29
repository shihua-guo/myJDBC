package com.briup.pool;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.management.RuntimeErrorException;

public class MyPool {
	private int init_num=5;
	private int max_num = 50;
	private int current_num = 0;
	private LinkedList<Connection> pool = 
			new LinkedList<Connection>();
	static{
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public MyPool(){
		for(int i=0;i<init_num;i++){
			Connection conn = createConnection();
			current_num++;
			pool.add(conn);
		}
	}
	public Connection createConnection(){
		try {
			Connection conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:xe","alan","root");
			Connection proxy = (Connection) Proxy.newProxyInstance(conn.getClass().getClassLoader(), 
					new Class[] {Connection.class} , new InvocationHandler() {
						
						@Override
						public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
							//方法执行返回的结果
							Object result = null;
							//方法的名字
							String methodName = method.getName();
							if("close".equals(methodName)){
								System.out.println("close is invoked");
								pool.addLast(conn);
							}
							else{
								result = method.invoke(conn, args);
							}
							return result;
						}
					});
			return proxy;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}
	
	//获取链接
	public Connection getConnection(){
		if(pool.size()>0){
			return pool.removeFirst();
		}
		if(current_num<max_num){
			current_num++;
			return createConnection();
		}
		throw new RuntimeException("达到最大链接数");
	}
	
	//释放链接
	public void releaseConnection(Connection conn){
		if(pool.size()<init_num){
			pool.addLast(conn);
		}
		else{
			try {
				conn.close();
				current_num--;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		MyPool p = new MyPool();
		System.out.println("当前连接数："+p.current_num);
		System.out.println("链接池："+p.pool.size());
		
		Connection connection1 = p.getConnection();
		Connection connection2 = p.getConnection();
		Connection connection3 = p.getConnection();
		Connection connection4 = p.getConnection();
		Connection connection5 = p.getConnection();
		
		System.out.println("当前连接数："+p.current_num);
		System.out.println("链接池："+p.pool.size());
		try {
			connection1.close();
			System.out.println("当前连接数："+p.current_num);
			System.out.println("链接池："+p.pool.size());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		p.releaseConnection(connection1);
		System.out.println("当前连接数："+p.current_num);
		System.out.println("链接池："+p.pool.size());
	}
}
