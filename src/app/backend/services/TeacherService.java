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
   * Construtor da classe TeacherService. Inicializa a conexão com o banco de
   * dados e cria a tabela se não existir.
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
              "('Mary Brown', '1980-04-25', '987654', 'mary.brown@example.com', '555-123-4567', 'English', 15), " +
              "('Michael Johnson', '1992-07-12', '345678', 'michael.johnson@example.com', '777-888-9999', 'History', 7), "
              +
              "('Emily Davis', '1995-03-18', '567890', 'emily.davis@example.com', '333-222-1111', 'Chemistry', 6), " +
              "('David Wilson', '1987-10-05', '234567', 'david.wilson@example.com', '222-333-4444', 'Music', 12), " +
              "('Sarah Lee', '1983-09-08', '678901', 'sarah.lee@example.com', '777-123-4567', 'Physical Education', 9), "
              +
              "('Matthew Martinez', '1998-06-15', '345678', 'matthew.martinez@example.com', '555-666-7777', 'Art', 5), "
              +
              "('Olivia Smith', '1982-11-22', '789012', 'olivia.smith@example.com', '444-555-6666', 'Geography', 11), "
              +
              "('William Anderson', '1990-12-30', '123456', 'william.anderson@example.com', '333-444-5555', 'Biology', 8), "
              +
              "('Sophia Wilson', '1986-08-04', '456789', 'sophia.wilson@example.com', '777-987-6543', 'Computer Science', 10), "
              +
              "('James Taylor', '1991-01-01', '123456', 'james.taylor@example.com', '111-222-3333', 'Physical Education', 7), "
              +
              "('Ava Jackson', '1984-03-08', '234567', 'ava.jackson@example.com', '555-444-3333', 'Math', 9), " +
              "('Daniel Harris', '1993-05-20', '567890', 'daniel.harris@example.com', '999-888-7777', 'Physics', 6), " +
              "('Oliver Moore', '1989-02-14', '345678', 'oliver.moore@example.com', '777-555-6666', 'Music', 11), " +
              "('Emma Davis', '1981-07-30', '789012', 'emma.davis@example.com', '111-555-6666', 'English', 12), " +
              "('Benjamin Lewis', '1996-09-10', '123456', 'benjamin.lewis@example.com', '555-777-8888', 'History', 5), "
              +
              "('Mia Miller', '1987-11-15', '234567', 'mia.miller@example.com', '222-555-6666', 'Chemistry', 8), " +
              "('Elijah Clark', '1997-04-05', '678901', 'elijah.clark@example.com', '999-555-6666', 'Art', 7), " +
              "('Lily White', '1994-06-28', '123456', 'lily.white@example.com', '111-333-4444', 'Geography', 6), " +
              "('Henry Scott', '1983-12-12', '456789', 'henry.scott@example.com', '555-666-7777', 'Biology', 9), " +
              "('Chloe Hall', '1998-01-25', '987654', 'chloe.hall@example.com', '222-444-5555', 'Computer Science', 4)");

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
   * Método para atualizar as informações de um professor no banco de dados com
   * base no ID.
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
