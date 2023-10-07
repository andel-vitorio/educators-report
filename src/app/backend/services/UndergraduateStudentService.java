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
   * Construtor da classe UndergraduateStudentService. Inicializa a conexão com o
   * banco de dados e cria a tabela se não existir.
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
              "('John Doe', '1990-05-15', '123456', 'john.doe@example.com', '123-456-7890', 'MenteeChloe Hall', 'Active', 'Project 1', 'Orientation 1'), "
              +
              "('Alice Smith', '1985-02-20', '789012', 'alice.smith@example.com', '987-654-3210', 'Chloe Hall', 'Inactive', 'Project 2', 'Orientation 2'), "
              +
              "('Bob Johnson', '1988-09-10', '456789', 'bob.johnson@example.com', '555-555-5555', 'Chloe Hall', 'Active', 'Project 3', 'Orientation 3'), "
              +
              "('Emily Wilson', '1992-03-25', '234567', 'emily.wilson@example.com', '444-555-6666', 'James Taylor', 'Active', 'Project 4', 'Orientation 4'), "
              +
              "('Michael Brown', '1991-11-12', '345678', 'michael.brown@example.com', '555-666-7777', 'James Taylor', 'Inactive', 'Project 5', 'Orientation 5'), "
              +
              "('Sarah Davis', '1993-07-07', '456789', 'sarah.davis@example.com', '777-888-9999', 'James Taylor', 'Active', 'Project 6', 'Orientation 6'), "
              +
              "('Christopher Garcia', '1994-09-30', '567890', 'christopher.garcia@example.com', '888-999-0000', 'James Taylor', 'Active', 'Project 7', 'Orientation 7'), "
              +
              "('Linda Johnson', '1995-01-15', '678901', 'linda.johnson@example.com', '999-000-1111', 'Oliver', 'Inactive', 'Project 8', 'Orientation 8'), "
              +
              "('Daniel Smith', '1996-06-20', '789012', 'daniel.smith@example.com', '111-222-3333', 'Oliver', 'Active', 'Project 9', 'Orientation 9'), "
              +
              "('Karen Wilson', '1997-12-01', '890123', 'karen.wilson@example.com', '222-333-4444', 'James Taylor', 'Active', 'Project 10', 'Orientation 10'), "
              +
              "('Robert Davis', '1998-04-05', '901234', 'robert.davis@example.com', '333-444-5555', 'James Taylor', 'Inactive', 'Project 11', 'Orientation 11'), "
              +
              "('Nancy Lee', '1999-08-10', '012345', 'nancy.lee@example.com', '444-555-6666', 'James Taylor', 'Active', 'Project 12', 'Orientation 12'), "
              +
              "('Patricia Johnson', '2000-02-15', '123456', 'patricia.johnson@example.com', '555-666-7777', 'Mary Brown', 'Active', 'Project 13', 'Orientation 13'), "
              +
              "('William Smith', '2001-10-20', '234567', 'william.smith@example.com', '666-777-8888', 'Mary Brown', 'Inactive', 'Project 14', 'Orientation 14'), "
              +
              "('Susan Brown', '2002-07-25', '345678', 'susan.brown@example.com', '777-888-9999', 'Mary Brown', 'Active', 'Project 15', 'Orientation 15'), "
              +
              "('David Wilson', '2003-11-30', '456789', 'david.wilson@example.com', '888-999-0000', 'Mary Brown', 'Active', 'Project 16', 'Orientation 16'), "
              +
              "('Emily Garcia', '2004-05-05', '567890', 'emily.garcia@example.com', '999-000-1111', 'Mary Brown', 'Inactive', 'Project 17', 'Orientation 17'), "
              +
              "('Michael Johnson', '2005-03-10', '678901', 'michael.johnson@example.com', '111-222-3333', 'Matthew Martinez', 'Active', 'Project 18', 'Orientation 18'), "
              +
              "('Linda Smith', '2006-06-15', '789012', 'linda.smith@example.com', '222-333-4444', 'Matthew Martinez', 'Active', 'Project 19', 'Orientation 19'), "
              +
              "('Daniel Davis', '2007-09-20', '890123', 'daniel.davis@example.com', '333-444-5555', 'Mentee 20', 'Inactive', 'Project 20', 'Orientation 20')");

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
   * @return Uma lista de estudantes de graduação que correspondem ao nome do
   *         mentor.
   */
  public static ArrayList<UndergraduateStudent> getUndergraduateStudentsByNameOfMentee(String nameOfMentee) {
    ArrayList<UndergraduateStudent> undergraduateStudents = new ArrayList<>();

    try {
      Statement st = connection.createStatement();
      ResultSet rs = st
          .executeQuery("SELECT * FROM undergraduate_students WHERE nameOfMentee = '" + nameOfMentee + "'");

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
   * Método para atualizar as informações de um estudante de graduação no banco de
   * dados com base no ID.
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
