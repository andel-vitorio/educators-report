package app.backend.services;

import app.backend.Database;
import app.backend.entities.PosgraduateStudent;

import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * Classe de serviço para gerenciar estudantes de pós-graduação no banco de
 * dados.
 */
public class PosgraduateStudentService extends Database {

  /**
   * Construtor da classe PosgraduateStudentService. Inicializa a conexão com o
   * banco de dados e cria a tabela se não existir.
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
   * Método para popular a tabela de estudantes de pós-graduação com dados de
   * exemplo.
   */
  public static void populatePosgraduateStudentTable() {
    try {
      Statement st = connection.createStatement();

      st.executeUpdate(
          "INSERT INTO posgraduate_students (name, dateOfEntry, registration, email, phoneNumber, nameOfMentee, status, posgraduateProgram, researchTitle, defenseDate) "
              +
              "VALUES " +
              "('John Doe', '1990-05-15', '123456', 'john.doe@example.com', '123-456-7890', 'Mary Brown', 'Active', 'Program 1', 'Title 1', '2023-09-30'), "
              +
              "('Alice Smith', '1985-02-20', '789012', 'alice.smith@example.com', '987-654-3210', 'Mary Brown', 'Inactive', 'Program 2', 'Title 2', '2023-10-01'), "
              +
              "('Bob Johnson', '1988-09-10', '456789', 'bob.johnson@example.com', '555-555-5555', 'Mary Brown', 'Active', 'Program 3', 'Title 3', '2023-10-05'), "
              +
              "('Jane Smith', '1992-11-25', '987654', 'jane.smith@example.com', '333-333-3333', 'Mary Brown', 'Active', 'Program 4', 'Title 4', '2023-10-10'), "
              +
              "('Michael Johnson', '1991-07-18', '654321', 'michael.johnson@example.com', '222-222-2222', 'Mary Brown', 'Inactive', 'Program 5', 'Title 5', '2023-10-15'), "
              +
              "('Emily Davis', '1989-04-30', '555555', 'emily.davis@example.com', '777-777-7777', 'Mary Brown', 'Active', 'Program 6', 'Title 6', '2023-10-20'), "
              +
              "('David Wilson', '1990-08-12', '777777', 'david.wilson@example.com', '999-999-9999', 'Emily Davis', 'Inactive', 'Program 7', 'Title 7', '2023-10-25'), "
              +
              "('Maria Garcia', '1993-01-05', '888888', 'maria.garcia@example.com', '111-111-1111', 'Emily Davis', 'Active', 'Program 8', 'Title 8', '2023-10-30'), "
              +
              "('Kevin Brown', '1992-03-28', '999999', 'kevin.brown@example.com', '444-444-4444', 'Emily Davis', 'Inactive', 'Program 9', 'Title 9', '2023-11-01'), "
              +
              "('Sarah Lee', '1987-06-17', '101010', 'sarah.lee@example.com', '666-666-6666', 'Matthew Martinez', 'Active', 'Program 10', 'Title 10', '2023-11-05'), "
              +
              "('Robert Smith', '1994-09-02', '121212', 'robert.smith@example.com', '333-333-3333', 'Matthew Martinez', 'Inactive', 'Program 11', 'Title 11', '2023-11-10'), "
              +
              "('Linda Johnson', '1993-02-22', '131313', 'linda.johnson@example.com', '222-222-2222', 'Matthew Martinez', 'Active', 'Program 12', 'Title 12', '2023-11-15'), "
              +
              "('William Davis', '1995-03-11', '141414', 'william.davis@example.com', '555-555-5555', 'Matthew Martinez', 'Inactive', 'Program 13', 'Title 13', '2023-11-20'), "
              +
              "('Susan Wilson', '1994-11-29', '151515', 'susan.wilson@example.com', '777-777-7777', 'Ava Jackson', 'Active', 'Program 14', 'Title 14', '2023-11-25'), "
              +
              "('Karen Garcia', '1996-07-03', '161616', 'karen.garcia@example.com', '999-999-9999', 'Ava Jackson', 'Inactive', 'Program 15', 'Title 15', '2023-12-01'), "
              +
              "('Thomas Brown', '1997-04-19', '171717', 'thomas.brown@example.com', '111-111-1111', 'Ava Jackson', 'Active', 'Program 16', 'Title 16', '2023-12-05'), "
              +
              "('Nancy Lee', '1995-12-10', '181818', 'nancy.lee@example.com', '666-666-6666', 'Ava Jackson', 'Inactive', 'Program 17', 'Title 17', '2023-12-10'), "
              +
              "('Daniel Smith', '1997-08-07', '191919', 'daniel.smith@example.com', '333-333-3333', 'Lily White', 'Active', 'Program 18', 'Title 18', '2023-12-15'), "
              +
              "('Patricia Johnson', '1996-06-14', '202020', 'patricia.johnson@example.com', '222-222-2222', 'Lily White', 'Inactive', 'Program 19', 'Title 19', '2023-12-20'), "
              +
              "('Christopher Davis', '1998-01-25', '212121', 'christopher.davis@example.com', '555-555-5555', 'Lily White', 'Active', 'Program 20', 'Title 20', '2023-12-25')");

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
   * Método para excluir um estudante de pós-graduação do banco de dados com base
   * no ID.
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
   * Método para atualizar as informações de um estudante de pós-graduação no
   * banco de dados com base no ID.
   *
   * @param id      O ID do estudante de pós-graduação a ser atualizado.
   * @param student O objeto de estudante de pós-graduação com as informações
   *                atualizadas.
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
