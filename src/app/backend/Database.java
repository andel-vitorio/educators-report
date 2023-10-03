package app.backend;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
	private static String url = "jdbc:mysql://localhost:3306/EducatorsReportDatabase";
	private static String user;
	private static String pass;
	protected static Connection connection = null;

	public Database(String user, String pass) {
		Database.user = user;
		Database.pass = pass;
		if (connection == null) {
			connect();
		}
	}

	private static void connect() {
		try {
			connection = DriverManager.getConnection(url, user, pass);
		} catch (SQLException e) {
			if (e.getSQLState().equals("08001")) {
				createDatabase();
				connect();
			} else {
				e.printStackTrace();
				throw new RuntimeException("Failed to connect to the database.");
			}
		}
	}

	private static void createDatabase() {
		try {
			Connection rootConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306", user, pass);
			Statement stmt = rootConnection.createStatement();
			stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS SnackBarDatabase");
			stmt.close();
			rootConnection.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Failed to create the database.");
		}
	}

	public static void disconnect() {
		try {
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Failed to disconnect from the database.");
		}
	}
}
