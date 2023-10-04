package app.backend.services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import app.backend.Database;
import app.backend.entities.*;

/**
 * Classe de serviço para gerenciar disciplinas no banco de dados.
 */
public class SubjectsService extends Database {

    /**
     * Construtor da classe SubjectsService. Inicializa a conexão com o banco de dados e cria a tabela se não existir.
     */
    public SubjectsService() {
        super(Database.getPass(), Database.getUser());

        try {
            Statement st = connection.createStatement();
            ResultSet tableExists = st.executeQuery("SHOW TABLES LIKE 'subjects'");

            if (!tableExists.next()) {
                st.executeUpdate(
                        "CREATE TABLE subjects (" +
                                "id INT AUTO_INCREMENT PRIMARY KEY," +
                                "code VARCHAR(255)," +
                                "name VARCHAR(255)," +
                                "description TEXT," +
                                "startTime TIME," +
                                "endTime TIME," +
                                "classroom VARCHAR(255)," +
                                "teacherName VARCHAR(255)," +
                                "requirements TEXT," +
                                "courseLoad INT," +
                                "credits INT," +
                                "numberOfVacancies INT" +
                                ")");

                populateSubjectsTable();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Método para popular a tabela de disciplinas com dados de exemplo.
     */
    public static void populateSubjectsTable() {
        try {
            Statement st = connection.createStatement();

            st.executeUpdate(
                    "INSERT INTO subjects (code, name, description, startTime, endTime, classroom, teacherName, requirements, courseLoad, credits, numberOfVacancies) "
                            +
                            "VALUES " +
                            "('CS101', 'Computer Science 101', 'Introduction to Computer Science', '08:00:00', '10:00:00', 'Room 101', 'John Smith', 'None', 3, 3, 30), "
                            +
                            "('MATH202', 'Advanced Mathematics', 'Advanced Math Course', '09:30:00', '11:30:00', 'Room 202', 'Alice Johnson', 'Prerequisite: MATH101', 4, 4, 25), "
                            +
                            "('PHYS301', 'Physics 301', 'Advanced Physics Course', '14:00:00', '16:00:00', 'Room 301', 'Bob Davis', 'Prerequisite: PHYS201', 4, 4, 20)");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Método para obter todas as disciplinas do banco de dados.
     *
     * @return Uma lista de disciplinas.
     */
    public static ArrayList<Subjects> getSubjects() {
        ArrayList<Subjects> subjects = new ArrayList<>();

        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM subjects");

            while (rs.next()) {
                Subjects subject = new Subjects();
                subject.setCode(rs.getString("code"));
                subject.setName(rs.getString("name"));
                subject.setDescription(rs.getString("description"));
                subject.setStartTime(rs.getTime("startTime").toLocalTime());
                subject.setEndTime(rs.getTime("endTime").toLocalTime());
                subject.setClassroom(rs.getString("classroom"));
                subject.setTeacherName(rs.getString("teacherName"));
                subject.setRequirements(rs.getString("requirements"));
                subject.setCourseLoad(rs.getInt("courseLoad"));
                subject.setCredits(rs.getInt("credits"));
                subject.setNumberOfVacancies(rs.getInt("numberOfVacancies"));
                subject.setId(rs.getInt("id"));
                subjects.add(subject);
            }

            return subjects;
        } catch (SQLException e) {
            System.out.println("Erro ao obter informações sobre as disciplinas");
            return null;
        }
    }

    /**
     * Método para obter disciplinas pelo nome do professor.
     *
     * @param teacherName O nome do professor.
     * @return Uma lista de disciplinas ministradas pelo professor.
     */
    public static ArrayList<Subjects> getSubjectsByTeacherName(String teacherName) {
        ArrayList<Subjects> subjects = new ArrayList<>();

        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM subjects WHERE teacherName = ?");
            ps.setString(1, teacherName);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Subjects subject = new Subjects();
                subject.setCode(rs.getString("code"));
                subject.setName(rs.getString("name"));
                subject.setDescription(rs.getString("description"));
                subject.setStartTime(rs.getTime("startTime").toLocalTime());
                subject.setEndTime(rs.getTime("endTime").toLocalTime());
                subject.setClassroom(rs.getString("classroom"));
                subject.setTeacherName(rs.getString("teacherName"));
                subject.setRequirements(rs.getString("requirements"));
                subject.setCourseLoad(rs.getInt("courseLoad"));
                subject.setCredits(rs.getInt("credits"));
                subject.setNumberOfVacancies(rs.getInt("numberOfVacancies"));
                subject.setId(rs.getInt("id"));
                subjects.add(subject);
            }

            return subjects;
        } catch (SQLException e) {
            System.out.println("Erro ao obter informações sobre as disciplinas do professor " + teacherName);
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Método para adicionar uma nova disciplina ao banco de dados.
     *
     * @param subject A disciplina a ser adicionada.
     * @return true se a operação for bem-sucedida, false caso contrário.
     */
    public static boolean postSubject(Subjects subject) {
        try {
            Statement st = connection.createStatement();
            st.executeUpdate(
                    "INSERT INTO subjects (code, name, description, startTime, endTime, classroom, teacherName, requirements, courseLoad, credits, numberOfVacancies) VALUES ('"
                            + subject.getCode() + "', '"
                            + subject.getName() + "', '"
                            + subject.getDescription() + "', '"
                            + subject.getStartTime() + "', '"
                            + subject.getEndTime() + "', '"
                            + subject.getClassroom() + "', '"
                            + subject.getTeacherName() + "', '"
                            + subject.getRequirements() + "', "
                            + subject.getCourseLoad() + ", "
                            + subject.getCredits() + ", "
                            + subject.getNumberOfVacancies()
                            + "')");

            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    /**
     * Método para excluir uma disciplina do banco de dados com base no ID.
     *
     * @param id O ID da disciplina a ser excluída.
     * @return true se a operação for bem-sucedida, false caso contrário.
     */
    public static boolean deleteSubjectById(int id) {
        try {
            Statement st = connection.createStatement();
            st.executeUpdate("DELETE FROM subjects WHERE id=" + id);
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    /**
     * Método para atualizar as informações de uma disciplina no banco de dados com base no ID.
     *
     * @param id      O ID da disciplina a ser atualizada.
     * @param subject O objeto de disciplina com as informações atualizadas.
     * @return true se a operação for bem-sucedida, false caso contrário.
     */
    public static boolean updateSubjectById(int id, Subjects subject) {
        try {
            Statement st = connection.createStatement();
            st.executeUpdate(
                    "UPDATE subjects SET"
                            + " code='" + subject.getCode() + "', "
                            + " name='" + subject.getName() + "', "
                            + " description='" + subject.getDescription() + "', "
                            + " startTime='" + subject.getStartTime() + "', "
                            + " endTime='" + subject.getEndTime() + "', "
                            + " classroom='" + subject.getClassroom() + "', "
                            + " teacherName='" + subject.getTeacherName() + "', "
                            + " requirements='" + subject.getRequirements() + "', "
                            + " courseLoad=" + subject.getCourseLoad() + ", "
                            + " credits=" + subject.getCredits() + ", "
                            + " numberOfVacancies=" + subject.getNumberOfVacancies()
                            + " WHERE id=" + id);
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
}
