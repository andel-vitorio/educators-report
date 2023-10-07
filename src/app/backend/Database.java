package app.backend;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * A classe Database fornece uma conexão com o banco de dados MySQL e métodos
 * para conectar e desconectar do banco de dados.
 */
public class Database {
    private static String url = "jdbc:mysql://localhost:3306/EducatorsReportDatabase";
    private static String user;  // Nome de usuário do banco de dados
    private static String pass;  // Senha do banco de dados
    protected static Connection connection = null;

    /**
     * Construtor da classe Database.
     * 
     * @param user Nome de usuário do banco de dados.
     * @param pass Senha do banco de dados.
     */
    public Database(String user, String pass) {
        Database.user = user;
        Database.pass = pass;
        if (connection == null) {
            connect();
        }
    }

    /**
     * Estabelece uma conexão com o banco de dados.
     */
    private static void connect() {
        try {
            // Use uma URL de conexão que cria o banco de dados se ele não existir
            String urlWithCreateDB = url + "?createDatabaseIfNotExist=true";
            connection = DriverManager.getConnection(urlWithCreateDB, user, pass);
        } catch (SQLException e) {
            throw new RuntimeException("Falha ao conectar ao banco de dados.");
        }
    }

    /**
     * Fecha a conexão com o banco de dados, se estiver aberta.
     */
    public static void disconnect() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Falha ao desconectar do banco de dados.");
        }
    }

    /**
     * Obtém a senha do banco de dados.
     * 
     * @return A senha do banco de dados.
     */
    public static String getPass() {
        return pass;
    }

    /**
     * Obtém o nome de usuário do banco de dados.
     * 
     * @return O nome de usuário do banco de dados.
     */
    public static String getUser() {
        return user;
    }
}
