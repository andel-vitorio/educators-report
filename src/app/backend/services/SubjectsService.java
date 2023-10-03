package app.backend.services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import app.backend.Database;
import app.backend.entities.*;

public class SubjectsService extends Database {

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

	public static ArrayList<Subjects> getSubjectsByTeacher(Teacher teacher) {
		ArrayList<Subjects> subjects = new ArrayList<>();

		try {
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM subjects WHERE teacherName=" + teacher.getName());

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
							+ ")");

			return true;
		} catch (SQLException e) {
			return false;
		}
	}

	public static boolean deleteSubjectById(int id) {
		try {
			Statement st = connection.createStatement();
			st.executeUpdate("DELETE FROM subjects WHERE id=" + id);
			return true;
		} catch (SQLException e) {
			return false;
		}
	}

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
