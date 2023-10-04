package app.backend.services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import app.backend.Database;
import app.backend.entities.Teacher;

/**
 * Classe de serviço para gerenciar professores no banco de dados.
 */
public class TeacherService extends Database {

    /**
     * Construtor da classe TeacherService. Inicializa a conexão com o banco de dados e cria a tabela se não existir.
     */
    public TeacherService() {
        super(Database.getPass(), Database.getUser());

        try {
            Statement st = connection.createStatement();
            ResultSet tableExists = st.executeQuery("SHOW TABLES LIKE 'teachers'");

            if (!tableExists.next()) {
                st.executeUpdate(
                        "CREATE TABLE teachers (" +
                                "id INT AUTO_INCREMENT PRIMARY KEY," +
                                "name VARCHAR(255)," +
                                "birthDay DATE," +
                                "indentificatorNumber VARCHAR(255)," +
                                "email VARCHAR(255)," +
                                "phone VARCHAR(255)," +
                                "trainingArea VARCHAR(255)," +
                                "yearsOfExperience INT" +
                                ")");

                populateTeacherTable();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Método para popular a tabela de professores com dados de exemplo.
     */
    public static void populateTeacherTable() {
        try {
            Statement st = connection.createStatement();

            st.executeUpdate(
                    "INSERT INTO teachers (name, birthDay, indentificatorNumber, email, phone, trainingArea, yearsOfExperience) "
                            +
                            "VALUES " +
                            "('John Doe', '1990-05-15', '123456', 'john.doe@example.com', '123-456-7890', 'Math', 5), " +
                            "('Alice Smith', '1985-02-20', '789012', 'alice.smith@example.com', '987-654-3210', 'Science', 8), " +
                            "('Bob Johnson', '1988-09-10', '456789', 'bob.johnson@example.com', '555-555-5555', 'Physics', 10)");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Método para obter todos os professores do banco de dados.
     *
     * @return Uma lista de professores.
     */
    public static ArrayList<Teacher> getTeachers() {
        ArrayList<Teacher> teachers = new ArrayList<>();

        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM teachers");

            while (rs.next()) {
                Teacher teacher = new Teacher();
                teacher.setBirthDay(rs.getDate("birthDay").toLocalDate());
                teacher.setEmail(rs.getString("email"));
                teacher.setIndentificatorNumber(rs.getString("indentificatorNumber"));
                teacher.setName(rs.getString("name"));
                teacher.setPhone(rs.getString("phone"));
                teacher.setTrainingArea(rs.getString("trainingArea"));
                teacher.setYearsOfExperience(rs.getInt("yearsOfExperience"));
                teacher.setId(rs.getInt("id"));
                teachers.add(teacher);
            }

            return teachers;
        } catch (SQLException e) {
            System.out.println("Erro ao obter informações sobre os professores");
            return null;
        }
    }

    /**
     * Método para adicionar um novo professor ao banco de dados.
     *
     * @param teacher O professor a ser adicionado.
     * @return true se a operação for bem-sucedida, false caso contrário.
     */
    public static boolean postTeacher(Teacher teacher) {
        try {
            Statement st = connection.createStatement();
            st.executeUpdate(
                    "INSERT INTO teachers (name, birthDay, indentificatorNumber, email, phone, trainingArea, yearsOfExperience) VALUES ('"
                            + teacher.getName() + "', '"
                            + teacher.getBirthDay() + "', '"
                            + teacher.getIndentificatorNumber() + "', '"
                            + teacher.getEmail() + "', '"
                            + teacher.getPhone() + "', '"
                            + teacher.getTrainingArea() + "', "
                            + teacher.getYearsOfExperience()
                            + ")");

            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    /**
     * Método para excluir um professor do banco de dados com base no ID.
     *
     * @param id O ID do professor a ser excluído.
     * @return true se a operação for bem-sucedida, false caso contrário.
     */
    public static boolean deleteTeacherById(int id) {
        try {
            Statement st = connection.createStatement();
            st.executeUpdate("DELETE FROM teachers WHERE id=" + id);
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    /**
     * Método para atualizar as informações de um professor no banco de dados com base no ID.
     *
     * @param id      O ID do professor a ser atualizado.
     * @param teacher O objeto Teacher com as novas informações.
     * @return true se a operação for bem-sucedida, false caso contrário.
     */
    public static boolean updateTeacherById(int id, Teacher teacher) {
        try {
            Statement st = connection.createStatement();
            st.executeUpdate(
                    "UPDATE teachers SET"
                            + " name='" + teacher.getName() + "', "
                            + " birthDay='" + teacher.getBirthDay() + "', "
                            + " indentificatorNumber='" + teacher.getIndentificatorNumber() + "', "
                            + " email='" + teacher.getEmail() + "', "
                            + " phone='" + teacher.getPhone() + "', "
                            + " trainingArea='" + teacher.getTrainingArea() + "', "
                            + " yearsOfExperience=" + teacher.getYearsOfExperience()
                            + " WHERE id=" + id);
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
}
