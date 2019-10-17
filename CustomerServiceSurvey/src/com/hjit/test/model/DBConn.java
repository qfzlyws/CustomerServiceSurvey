package com.hjit.test.model;

import java.sql.Connection;
import java.sql.DriverManager;

public final class DBConn {
	private static String driverName = "oracle.jdbc.driver.OracleDriver";
	private static String dbURL = "jdbc:oracle:thin:@172.24.5.111:1521:hjcrdb";
	private static String userName = "crwms";
	private static String userPwd = "testcrwms";
	private static Connection conn = null;
	
	private DBConn()
	{
		System.err.println("DBConn object created");
	}
	
	public static Connection getConnection()
	{
		if (conn == null) {
			try {
				Class.forName(driverName);
				conn = DriverManager.getConnection(dbURL, userName, userPwd);
				conn.setAutoCommit(false);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return conn;
	}
}
