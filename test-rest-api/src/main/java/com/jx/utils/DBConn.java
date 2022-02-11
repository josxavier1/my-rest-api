package com.jx.utils;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class DBConn {

	private static void testDB(String dbURL, String user, String password, String query) {

		Properties properties = new Properties();
		properties.put("user", user);
		properties.put("password", password);
		Connection conn = null;

		try {

			conn = DriverManager.getConnection(dbURL, properties);
			if (conn != null) {
				DatabaseMetaData dm = (DatabaseMetaData) conn.getMetaData();
				System.out.println("Driver name: " + dm.getDriverName());
				System.out.println("Driver version: " + dm.getDriverVersion());
				System.out.println("Product name: " + dm.getDatabaseProductName());
				System.out.println("Product version: " + dm.getDatabaseProductVersion());
				System.out.println("************************************************");

				testQuery(query, conn);
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

	private static void testQuery(String query, Connection conn) {

		try {

			PreparedStatement ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				System.out.println("data....");			}
		} catch (SQLException e) {
			// handle the exception
		}
	}

	// some comment
	public static void main(String[] args) {

		// MS SQL
		String msdbURL = "jdbc:sqlserver://IS197\\SQLEXPRESS;databaseName=testground";
		String msUser = "test_user";
		String msPassword = "Rochele123";
		String msQuery = "SELECT TOP (5) id, customer_name, phone FROM [testground].[dbo].[sales_customer]";
		testDB(msdbURL, msUser, msPassword, msQuery);

		// MS SQL
		String mySQLdbURL = "jdbc:mysql://localhost:3306/world?serverTimezone=UTC";
		String mySQLUser = "world_user";
		String mySQLPassword = "Rochele123";
		String mySqlQuery = "SELECT * FROM world.city limit 5";
		testDB(mySQLdbURL, mySQLUser, mySQLPassword, mySqlQuery);
		
	}
}
