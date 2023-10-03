package app.backend.services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import app.backend.Database;
import app.backend.entities.Paper;
import java.time.LocalDate;

public class PaperService extends Database {

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

	public static void populatePaperTable() {
		try {
			Statement st = connection.createStatement();

			st.executeUpdate(
					"INSERT INTO papers (title, authors, publicationDate, keywords, description, category, url) "
							+
							"VALUES " +
							"('Paper 1', 'Author 1, Author 2', '2023-09-30', 'Keyword 1, Keyword 2', 'Description 1', 'Category 1', 'https://example.com/paper1'), "
							+
							"('Paper 2', 'Author 3, Author 4', '2023-09-30', 'Keyword 3, Keyword 4', 'Description 2', 'Category 2', 'https://example.com/paper2'), "
							+
							"('Paper 3', 'Author 5, Author 6', '2023-09-30', 'Keyword 5, Keyword 6', 'Description 3', 'Category 3', 'https://example.com/paper3')");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

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

	public static boolean deletePaperById(int id) {
		try {
			Statement st = connection.createStatement();
			st.executeUpdate("DELETE FROM papers WHERE id=" + id);
			return true;
		} catch (SQLException e) {
			return false;
		}
	}

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
