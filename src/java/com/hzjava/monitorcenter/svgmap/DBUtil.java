package com.hzjava.monitorcenter.svgmap;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
	
	private static final String driverClass = "com.mysql.jdbc.Driver";
	private static final String username = "root";
	private static final String password = "mysql";
	private static final String url = "jdbc:mysql://localhost:3306/test";
	private static SessionFactory sessionFactory = null;
//	static{
//		try {
//			Class.forName(driverClass);
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		}
//	}
	
	/**
	 * hibernate static
	 */
	static{
		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");
		sessionFactory = cfg.buildSessionFactory();
	}
	public static Connection createConnection(){
		try {
			Connection conn = DriverManager.getConnection(url, username, password);
			conn.setAutoCommit(false);
			return conn;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static Session createSession(){
		return sessionFactory.openSession();
	}
	
	public static void main(String[] args) throws SQLException {
		Session session = createSession();
		System.out.println(session);
		session.close();
	}
	
	

}
