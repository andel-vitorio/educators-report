package app.backend.services;

import app.backend.Database;
import app.backend.entities.UndergraduateStudent;

import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * Classe de serviço para gerenciar estudantes de graduação no banco de dados.
 */
public class UndergraduateStudentService extends Database {

    /**
     * Construtor da classe UndergraduateStudentService. Inicializa a conexão com o banco de dados e cria a tabela se não existir.
     */
    public UndergraduateStudentService() {
        super(Database.getPass(), Database.getUser());

        try {
            Statement st = connection.createStatement();
            ResultSet tableExists = st.executeQuery("SHOW TABLES LIKE 'undergraduate_students'");

            if (!tableExists.next()) {
                st.executeUpdate(
                        "CREATE TABLE undergraduate_students (" +
                                "id INT AUTO_INCREMENT PRIMARY KEY," +
                                "name VARCHAR(255)," +
                                "dateOfEntry DATE," +
                                "registration VARCHAR(255)," +
                                "email VARCHAR(255)," +
                                "phoneNumber VARCHAR(255)," +
                                "nameOfMentee VARCHAR(255)," +
                                "status VARCHAR(255)," +
                                "projectName VARCHAR(255)," +
                                "typeOfOrientation VARCHAR(255)" +
                                ")");

                populateUndergraduateStudentTable();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Método para popular a tabela de estudantes de graduação com dados de exemplo.
     */
    public static void populateUndergraduateStudentTable() {
        try {
            Statement st = connection.createStatement();

            st.executeUpdate(
                    "INSERT INTO undergraduate_students (name, dateOfEntry, registration, email, phoneNumber, nameOfMentee, status, projectName, typeOfOrientation) "
                            +
                            "VALUES " +
                            "('John Doe', '1990-05-15', '123456', 'john.doe@example.com', '123-456-7890', 'Mentee 1', 'Active', 'Project 1', 'Orientation 1'), "
                            +
                            "('Alice Smith', '1985-02-20', '789012', 'alice.smith@example.com', '987-654-3210', 'Mentee 2', 'Inactive', 'Project 2', 'Orientation 2'), "
                            +
                            "('Bob Johnson', '1988-09-10', '456789', 'bob.johnson@example.com', '555-555-5555', 'Mentee 3', 'Active', 'Project 3', 'Orientation 3')");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Método para obter todos os estudantes de graduação do banco de dados.
     *
     * @return Uma lista de estudantes de graduação.
     */
    public static ArrayList<UndergraduateStudent> getUndergraduateStudents() {
        ArrayList<UndergraduateStudent> undergraduateStudents = new ArrayList<>();

        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM undergraduate_students");

            while (rs.next()) {
                UndergraduateStudent student = new UndergraduateStudent();
                student.setName(rs.getString("name"));
                student.setDateOfEntry(rs.getDate("dateOfEntry").toLocalDate());
                student.setRegistration(rs.getString("registration"));
                student.setEmail(rs.getString("email"));
                student.setPhoneNumber(rs.getString("phoneNumber"));
                student.setNameOfMentee(rs.getString("nameOfMentee"));
                student.setStatus(rs.getString("status"));
                student.setProjectName(rs.getString("projectName"));
                student.setTypeOfOrientation(rs.getString("typeOfOrientation"));
                student.setId(rs.getInt("id"));
                undergraduateStudents.add(student);
            }

            return undergraduateStudents;
        } catch (SQLException e) {
            System.out.println("Erro ao obter informações sobre os estudantes de graduação");
            return null;
        }
    }

    /**
     * Método para obter estudantes de graduação com base no nome do mentor.
     *
     * @param nameOfMentee O nome do mentor a ser usado como critério de pesquisa.
     * @return Uma lista de estudantes de graduação que correspondem ao nome do mentor.
     */
    public static ArrayList<UndergraduateStudent> getUndergraduateStudentsByNameOfMentee(String nameOfMentee) {
        ArrayList<UndergraduateStudent> undergraduateStudents = new ArrayList<>();

        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM undergraduate_students WHERE nameOfMentee = '" + nameOfMentee + "'");

            while (rs.next()) {
                UndergraduateStudent student = new UndergraduateStudent();
                student.setName(rs.getString("name"));
                student.setDateOfEntry(rs.getDate("dateOfEntry").toLocalDate());
                student.setRegistration(rs.getString("registration"));
                student.setEmail(rs.getString("email"));
                student.setPhoneNumber(rs.getString("phoneNumber"));
                student.setNameOfMentee(rs.getString("nameOfMentee"));
                student.setStatus(rs.getString("status"));
                student.setProjectName(rs.getString("projectName"));
                student.setTypeOfOrientation(rs.getString("typeOfOrientation"));
                student.setId(rs.getInt("id"));
                undergraduateStudents.add(student);
            }

            return undergraduateStudents;
        } catch (SQLException e) {
            System.out.println("Erro ao obter informações sobre os estudantes de graduação por nome de mentor");
            return null;
        }
    }

    /**
     * Método para adicionar um novo estudante de graduação ao banco de dados.
     *
     * @param student O estudante de graduação a ser adicionado.
     * @return true se a operação for bem-sucedida, false caso contrário.
     */
    public static boolean postUndergraduateStudent(UndergraduateStudent student) {
        try {
            Statement st = connection.createStatement();
            st.executeUpdate(
                    "INSERT INTO undergraduate_students (name, dateOfEntry, registration, email, phoneNumber, nameOfMentee, status, projectName, typeOfOrientation) VALUES ('"
                            + student.getName() + "', '"
                            + student.getDateOfEntry() + "', '"
                            + student.getRegistration() + "', '"
                            + student.getEmail() + "', '"
                            + student.getPhoneNumber() + "', '"
                            + student.getNameOfMentee() + "', '"
                            + student.getStatus() + "', '"
                            + student.getProjectName() + "', '"
                            + student.getTypeOfOrientation()
                            + "')");

            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    /**
     * Método para excluir um estudante de graduação com base no ID.
     *
     * @param id O ID do estudante de graduação a ser excluído.
     * @return true se a operação for bem-sucedida, false caso contrário.
     */
    public static boolean deleteUndergraduateStudentById(int id) {
        try {
            Statement st = connection.createStatement();
            st.executeUpdate("DELETE FROM undergraduate_students WHERE id=" + id);
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    /**
     * Método para atualizar as informações de um estudante de graduação no banco de dados com base no ID.
     *
     * @param id      O ID do estudante de graduação a ser atualizado.
     * @param student O objeto UndergraduateStudent com as novas informações.
     * @return true se a operação for bem-sucedida, false caso contrário.
     */
    public static boolean updateUndergraduateStudentById(int id, UndergraduateStudent student) {
        try {
            Statement st = connection.createStatement();
            st.executeUpdate(
                    "UPDATE undergraduate_students SET"
                            + " name='" + student.getName() + "', "
                            + " dateOfEntry='" + student.getDateOfEntry() + "', "
                            + " registration='" + student.getRegistration() + "', "
                            + " email='" + student.getEmail() + "', "
                            + " phoneNumber='" + student.getPhoneNumber() + "', "
                            + " nameOfMentee='" + student.getNameOfMentee() + "', "
                            + " status='" + student.getStatus() + "', "
                            + " projectName='" + student.getProjectName() + "', "
                            + " typeOfOrientation='" + student.getTypeOfOrientation() + "'"
                            + " WHERE id=" + id);
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
}
