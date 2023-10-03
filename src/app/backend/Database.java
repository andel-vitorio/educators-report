package app.backend;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

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
            // Use a URL de conexão que cria o banco de dados se ele não existir
            String urlWithCreateDB = url + "?createDatabaseIfNotExist=true";
            connection = DriverManager.getConnection(urlWithCreateDB, user, pass);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to connect to the database.");
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

		public static String getPass() {
			return pass;
		}

		public static String getUser() {
			return user;
		}
}
