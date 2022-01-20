package com.jx.utils;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConn {

	// some comment
	public static void main(String[] args) {

		String dbURL = "jdbc:sqlserver://IS197\\SQLEXPRESS;databaseName=testground";
		Properties properties = new Properties();
		properties.put("user", "test_user");
		properties.put("password", "Rochele123");
		Connection conn = null;

		try {

			conn = DriverManager.getConnection(dbURL, properties);
			if (conn != null) {
				DatabaseMetaData dm = (DatabaseMetaData) conn.getMetaData();
				System.out.println("Driver name: " + dm.getDriverName());
				System.out.println("Driver version: " + dm.getDriverVersion());
				System.out.println("Product name: " + dm.getDatabaseProductName());
				System.out.println("Product version: " + dm.getDatabaseProductVersion());
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
	}

}
