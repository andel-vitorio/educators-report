package app.backend.services;

import app.backend.Database;
import app.backend.entities.PosgraduateStudent;

import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe de serviço para gerenciar estudantes de pós-graduação no banco de dados.
 */
public class PosgraduateStudentService extends Database {

    /**
     * Construtor da classe PosgraduateStudentService. Inicializa a conexão com o banco de dados e cria a tabela se não existir.
     */
    public PosgraduateStudentService() {
        super(Database.getPass(), Database.getUser());

        try {
            Statement st = connection.createStatement();
            ResultSet tableExists = st.executeQuery("SHOW TABLES LIKE 'posgraduate_students'");

            if (!tableExists.next()) {
                st.executeUpdate(
                        "CREATE TABLE posgraduate_students (" +
                                "id INT AUTO_INCREMENT PRIMARY KEY," +
                                "name VARCHAR(255)," +
                                "dateOfEntry DATE," +
                                "registration VARCHAR(255)," +
                                "email VARCHAR(255)," +
                                "phoneNumber VARCHAR(255)," +
                                "nameOfMentee VARCHAR(255)," +
                                "status VARCHAR(255)," +
                                "posgraduateProgram VARCHAR(255)," +
                                "researchTitle VARCHAR(255)," +
                                "defenseDate DATE" +
                                ")");

                populatePosgraduateStudentTable();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Método para popular a tabela de estudantes de pós-graduação com dados de exemplo.
     */
    public static void populatePosgraduateStudentTable() {
        try {
            Statement st = connection.createStatement();

            st.executeUpdate(
                    "INSERT INTO posgraduate_students (name, dateOfEntry, registration, email, phoneNumber, nameOfMentee, status, posgraduateProgram, researchTitle, defenseDate) "
                            +
                            "VALUES " +
                            "('John Doe', '1990-05-15', '123456', 'john.doe@example.com', '123-456-7890', 'Mentee 1', 'Active', 'Program 1', 'Title 1', '2023-09-30'), "
                            +
                            "('Alice Smith', '1985-02-20', '789012', 'alice.smith@example.com', '987-654-3210', 'Mentee 2', 'Inactive', 'Program 2', 'Title 2', '2023-10-01'), "
                            +
                            "('Bob Johnson', '1988-09-10', '456789', 'bob.johnson@example.com', '555-555-5555', 'Mentee 3', 'Active', 'Program 3', 'Title 3', '2023-10-05')");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Método para obter todos os estudantes de pós-graduação do banco de dados.
     *
     * @return Uma lista de estudantes de pós-graduação.
     */
    public static ArrayList<PosgraduateStudent> getPosgraduateStudents() {
        ArrayList<PosgraduateStudent> posgraduateStudents = new ArrayList<>();

        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM posgraduate_students");

            while (rs.next()) {
                PosgraduateStudent student = new PosgraduateStudent();
                student.setName(rs.getString("name"));
                student.setDateOfEntry(rs.getDate("dateOfEntry").toLocalDate());
                student.setRegistration(rs.getString("registration"));
                student.setEmail(rs.getString("email"));
                student.setPhoneNumber(rs.getString("phoneNumber"));
                student.setNameOfMentee(rs.getString("nameOfMentee"));
                student.setStatus(rs.getString("status"));
                student.setPosgraduateProgram(rs.getString("posgraduateProgram"));
                student.setResearchTitle(rs.getString("researchTitle"));
                student.setDefenseDate(rs.getDate("defenseDate").toLocalDate());
                student.setId(rs.getInt("id"));
                posgraduateStudents.add(student);
            }

            return posgraduateStudents;
        } catch (SQLException e) {
            System.out.println("Erro ao obter informações sobre os estudantes de pós-graduação");
            return null;
        }
    }

    /**
     * Método para obter estudantes de pós-graduação por nome do orientador.
     *
     * @param nameOfMentee O nome do orientador.
     * @return Uma lista de estudantes de pós-graduação relacionados ao orientador.
     */
    public static ArrayList<PosgraduateStudent> getPosgraduateStudentsByNameOfMentee(String nameOfMentee) {
        ArrayList<PosgraduateStudent> posgraduateStudents = new ArrayList<>();

        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM posgraduate_students WHERE nameOfMentee = '" + nameOfMentee + "'");

            while (rs.next()) {
                PosgraduateStudent student = new PosgraduateStudent();
                student.setName(rs.getString("name"));
                student.setDateOfEntry(rs.getDate("dateOfEntry").toLocalDate());
                student.setRegistration(rs.getString("registration"));
                student.setEmail(rs.getString("email"));
                student.setPhoneNumber(rs.getString("phoneNumber"));
                student.setNameOfMentee(rs.getString("nameOfMentee"));
                student.setStatus(rs.getString("status"));
                student.setPosgraduateProgram(rs.getString("posgraduateProgram"));
                student.setResearchTitle(rs.getString("researchTitle"));
                student.setDefenseDate(rs.getDate("defenseDate").toLocalDate());
                student.setId(rs.getInt("id"));
                posgraduateStudents.add(student);
            }

            return posgraduateStudents;
        } catch (SQLException e) {
            System.out.println("Erro ao obter informações sobre os estudantes de pós-graduação por nome de mentor");
            return null;
        }
    }

    /**
     * Método para adicionar um novo estudante de pós-graduação ao banco de dados.
     *
     * @param student O estudante de pós-graduação a ser adicionado.
     * @return true se a operação for bem-sucedida, false caso contrário.
     */
    public static boolean postPosgraduateStudent(PosgraduateStudent student) {
        try {
            Statement st = connection.createStatement();
            st.executeUpdate(
                    "INSERT INTO posgraduate_students (name, dateOfEntry, registration, email, phoneNumber, nameOfMentee, status, posgraduateProgram, researchTitle, defenseDate) VALUES ('"
                            + student.getName() + "', '"
                            + student.getDateOfEntry() + "', '"
                            + student.getRegistration() + "', '"
                            + student.getEmail() + "', '"
                            + student.getPhoneNumber() + "', '"
                            + student.getNameOfMentee() + "', '"
                            + student.getStatus() + "', '"
                            + student.getPosgraduateProgram() + "', '"
                            + student.getResearchTitle() + "', '"
                            + student.getDefenseDate()
                            + "')");

            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    /**
     * Método para excluir um estudante de pós-graduação do banco de dados com base no ID.
     *
     * @param id O ID do estudante de pós-graduação a ser excluído.
     * @return true se a operação for bem-sucedida, false caso contrário.
     */
    public static boolean deletePosgraduateStudentById(int id) {
        try {
            Statement st = connection.createStatement();
            st.executeUpdate("DELETE FROM posgraduate_students WHERE id=" + id);
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    /**
     * Método para atualizar as informações de um estudante de pós-graduação no banco de dados com base no ID.
     *
     * @param id      O ID do estudante de pós-graduação a ser atualizado.
     * @param student O objeto de estudante de pós-graduação com as informações atualizadas.
     * @return true se a operação for bem-sucedida, false caso contrário.
     */
    public static boolean updatePosgraduateStudentById(int id, PosgraduateStudent student) {
        try {
            Statement st = connection.createStatement();
            st.executeUpdate(
                    "UPDATE posgraduate_students SET"
                            + " name='" + student.getName() + "', "
                            + " dateOfEntry='" + student.getDateOfEntry() + "', "
                            + " registration='" + student.getRegistration() + "', "
                            + " email='" + student.getEmail() + "', "
                            + " phoneNumber='" + student.getPhoneNumber() + "', "
                            + " nameOfMentee='" + student.getNameOfMentee() + "', "
                            + " status='" + student.getStatus() + "', "
                            + " posgraduateProgram='" + student.getPosgraduateProgram() + "', "
                            + " researchTitle='" + student.getResearchTitle() + "', "
                            + " defenseDate='" + student.getDefenseDate() + "'"
                            + " WHERE id=" + id);
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
}
