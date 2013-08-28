package com.zhaowei.redis.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DB {
	
	private static final DB db = new DB();
	
	private static String ORACLE_TEST_URL = "jdbc:oracle:thin:@10.2.134.12:1521:orcl";
	private static String ORACLE_TEST_USERNAME = "mapabc_opg";
	private static String ORACLE_TEST_PASSWORD = "mapabc_opg";

	
	private Connection conn = null;
	
	private Statement stat = null;
	
	private DB() {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			conn = DriverManager.getConnection(ORACLE_TEST_URL, ORACLE_TEST_USERNAME, ORACLE_TEST_PASSWORD);
			stat = conn.createStatement();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static DB getInstance() {
		return db;
	}
	
	public ResultSet querySql(String sql) throws SQLException {
		return stat.executeQuery(sql);
	}

}
