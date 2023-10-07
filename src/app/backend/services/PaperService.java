package app.backend.services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import app.backend.Database;
import app.backend.entities.Paper;

/**
 * Classe de serviço para gerenciar trabalhos acadêmicos (papers) no banco de
 * dados.
 */
public class PaperService extends Database {

  /**
   * Construtor da classe PaperService. Inicializa a conexão com o banco de dados
   * e cria a tabela se não existir.
   */
  public PaperService() {
    super(Database.getPass(), Database.getUser());

    try {
      Statement st = connection.createStatement();
      ResultSet tableExists = st.executeQuery("SHOW TABLES LIKE 'papers'");

      if (!tableExists.next()) {
        st.executeUpdate(
            "CREATE TABLE papers (" +
                "id INT AUTO_INCREMENT PRIMARY KEY," +
                "title VARCHAR(255)," +
                "authors TEXT," +
                "publicationDate DATE," +
                "keywords TEXT," +
                "description TEXT," +
                "category VARCHAR(255)," +
                "url VARCHAR(255)" +
                ")");

        populatePaperTable();
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  /**
   * Método para popular a tabela de trabalhos acadêmicos com dados de exemplo.
   */
  public static void populatePaperTable() {
    try {
      Statement st = connection.createStatement();

      st.executeUpdate(
          "INSERT INTO papers (title, authors, publicationDate, keywords, description, category, url) "
              +
              "VALUES " +
              "('Paper 1', 'Ava Jackson, Author 2', '2023-09-30', 'Keyword 1, Keyword 2', 'Description 1', 'Category 1', 'https://example.com/paper1'), "
              +
              "('Paper 2', 'Ava Jackson, Author 4', '2023-09-30', 'Keyword 3, Keyword 4', 'Description 2', 'Category 2', 'https://example.com/paper2'), "
              +
              "('Paper 3', 'Mary Brown, Author 6', '2023-09-30', 'Keyword 5, Keyword 6', 'Description 3', 'Category 3', 'https://example.com/paper3'),"
              +
              "('Paper 4', 'Mary Brown, Author 8', '2023-10-10', 'Keyword 7, Keyword 8', 'Description 4', 'Category 4', 'https://example.com/paper4'), "
              +
              "('Paper 5', 'Mary Brown, Author 10', '2023-10-12', 'Keyword 9, Keyword 10', 'Description 5', 'Category 5', 'https://example.com/paper5'), "
              +
              "('Paper 6', 'Emma Davis, Author 12', '2023-10-15', 'Keyword 11, Keyword 12', 'Description 6', 'Category 6', 'https://example.com/paper6'), "
              +
              "('Paper 7', 'Emma Davis, Author 14', '2023-10-20', 'Keyword 13, Keyword 14', 'Description 7', 'Category 7', 'https://example.com/paper7'), "
              +
              "('Paper 8', 'Emma Davis, Author 16', '2023-10-25', 'Keyword 15, Keyword 16', 'Description 8', 'Category 8', 'https://example.com/paper8'), "
              +
              "('Paper 9', 'William Anderson, Author 18', '2023-10-30', 'Keyword 17, Keyword 18', 'Description 9', 'Category 9', 'https://example.com/paper9'), "
              +
              "('Paper 10', 'William Anderson, Author 20', '2023-11-01', 'Keyword 19, Keyword 20', 'Description 10', 'Category 10', 'https://example.com/paper10'), "
              +
              "('Paper 11', 'Mia Miller, Author 22', '2023-11-05', 'Keyword 21, Keyword 22', 'Description 11', 'Category 11', 'https://example.com/paper11'), "
              +
              "('Paper 12', 'Mia Miller, Author 24', '2023-11-10', 'Keyword 23, Keyword 24', 'Description 12', 'Category 12', 'https://example.com/paper12'), "
              +
              "('Paper 13', 'Mia Miller, Author 26', '2023-11-15', 'Keyword 25, Keyword 26', 'Description 13', 'Category 13', 'https://example.com/paper13'), "
              +
              "('Paper 14', 'Emma Davis, Author 28', '2023-11-20', 'Keyword 27, Keyword 28', 'Description 14', 'Category 14', 'https://example.com/paper14'), "
              +
              "('Paper 15', 'Emma Davis, Author 30', '2023-11-25', 'Keyword 29, Keyword 30', 'Description 15', 'Category 15', 'https://example.com/paper15'), "
              +
              "('Paper 16', 'Emma Davis, Author 32', '2023-12-01', 'Keyword 31, Keyword 32', 'Description 16', 'Category 16', 'https://example.com/paper16'), "
              +
              "('Paper 17', 'Emma Davis, Author 34', '2023-12-05', 'Keyword 33, Keyword 34', 'Description 17', 'Category 17', 'https://example.com/paper17'), "
              +
              "('Paper 18', 'Henry Scott, Author 36', '2023-12-10', 'Keyword 35, Keyword 36', 'Description 18', 'Category 18', 'https://example.com/paper18'), "
              +
              "('Paper 19', 'Henry Scott, Author 38', '2023-12-15', 'Keyword 37, Keyword 38', 'Description 19', 'Category 19', 'https://example.com/paper19'), "
              +
              "('Paper 20', 'Henry Scott, Author 40', '2023-12-20', 'Keyword 39, Keyword 40', 'Description 20', 'Category 20', 'https://example.com/paper20')");

    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  /**
   * Método para obter todos os trabalhos acadêmicos do banco de dados.
   *
   * @return Uma lista de trabalhos acadêmicos.
   */
  public static ArrayList<Paper> getPapers() {
    ArrayList<Paper> papers = new ArrayList<>();

    try {
      Statement st = connection.createStatement();
      ResultSet rs = st.executeQuery("SELECT * FROM papers");

      while (rs.next()) {
        Paper paper = new Paper();
        paper.setTitle(rs.getString("title"));
        paper.setAuthors(rs.getString("authors"));
        paper.setPublicationDate(rs.getDate("publicationDate").toLocalDate());
        paper.setKeywords(rs.getString("keywords"));
        paper.setDescription(rs.getString("description"));
        paper.setCategory(rs.getString("category"));
        paper.setUrl(rs.getString("url"));
        paper.setId(rs.getInt("id"));
        papers.add(paper);
      }

      return papers;
    } catch (SQLException e) {
      System.out.println("Erro ao obter informações sobre os papers");
      return null;
    }
  }

  /**
   * Método para obter trabalhos acadêmicos por nome do autor.
   *
   * @param professorName O nome do autor.
   * @return Uma lista de trabalhos acadêmicos relacionados ao autor.
   */
  public static ArrayList<Paper> getPapersByAuthor(String professorName) {
    ArrayList<Paper> papers = new ArrayList<>();

    try {
      Statement st = connection.createStatement();
      ResultSet rs = st.executeQuery("SELECT * FROM papers WHERE authors LIKE '%" + professorName + "%'");

      while (rs.next()) {
        Paper paper = new Paper();
        paper.setTitle(rs.getString("title"));
        paper.setAuthors(rs.getString("authors"));
        paper.setPublicationDate(rs.getDate("publicationDate").toLocalDate());
        paper.setKeywords(rs.getString("keywords"));
        paper.setDescription(rs.getString("description"));
        paper.setCategory(rs.getString("category"));
        paper.setUrl(rs.getString("url"));
        paper.setId(rs.getInt("id"));
        papers.add(paper);
      }

      return papers;
    } catch (SQLException e) {
      System.out.println("Erro ao obter informações sobre os papers por autor");
      return null;
    }
  }

  /**
   * Método para adicionar um novo trabalho acadêmico ao banco de dados.
   *
   * @param paper O trabalho acadêmico a ser adicionado.
   * @return true se a operação for bem-sucedida, false caso contrário.
   */
  public static boolean postPaper(Paper paper) {
    try {
      Statement st = connection.createStatement();
      st.executeUpdate(
          "INSERT INTO papers (title, authors, publicationDate, keywords, description, category, url) VALUES ('"
              + paper.getTitle() + "', '"
              + paper.getAuthors() + "', '"
              + paper.getPublicationDate() + "', '"
              + paper.getKeywords() + "', '"
              + paper.getDescription() + "', '"
              + paper.getCategory() + "', '"
              + paper.getUrl()
              + "')");

      return true;
    } catch (SQLException e) {
      return false;
    }
  }

  /**
   * Método para excluir um trabalho acadêmico do banco de dados por ID.
   *
   * @param id O ID do trabalho acadêmico a ser excluído.
   * @return true se a operação for bem-sucedida, false caso contrário.
   */
  public static boolean deletePaperById(int id) {
    try {
      Statement st = connection.createStatement();
      st.executeUpdate("DELETE FROM papers WHERE id=" + id);
      return true;
    } catch (SQLException e) {
      return false;
    }
  }

  /**
   * Método para atualizar um trabalho acadêmico no banco de dados por ID.
   *
   * @param id    O ID do trabalho acadêmico a ser atualizado.
   * @param paper O novo trabalho acadêmico.
   * @return true se a operação for bem-sucedida, false caso contrário.
   */
  public static boolean updatePaperById(int id, Paper paper) {
    try {
      Statement st = connection.createStatement();
      st.executeUpdate(
          "UPDATE papers SET"
              + " title='" + paper.getTitle() + "', "
              + " authors='" + paper.getAuthors() + "', "
              + " publicationDate='" + paper.getPublicationDate() + "', "
              + " keywords='" + paper.getKeywords() + "', "
              + " description='" + paper.getDescription() + "', "
              + " category='" + paper.getCategory() + "', "
              + " url='" + paper.getUrl() + "'"
              + " WHERE id=" + id);
      return true;
    } catch (SQLException e) {
      return false;
    }
  }
}
