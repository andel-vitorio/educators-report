package app.backend.services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import app.backend.Database;
import app.backend.entities.CoordinationActivity;
import java.time.LocalDate;

public class CoordinationActivityService extends Database {

	public CoordinationActivityService() {
		super(Database.getPass(), Database.getUser());

		try {
			Statement st = connection.createStatement();
			ResultSet tableExists = st.executeQuery("SHOW TABLES LIKE 'coordination_activities'");

			if (!tableExists.next()) {
				st.executeUpdate(
						"CREATE TABLE coordination_activities (" +
								"id INT AUTO_INCREMENT PRIMARY KEY," +
								"activityTitle VARCHAR(255)," +
								"nameOfPersonResponsible VARCHAR(255)," +
								"startDate DATE," +
								"endDate DATE," +
								"priority VARCHAR(255)," +
								"status VARCHAR(255)," +
								"description TEXT" +
								")");

				populateCoordinationActivityTable();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void populateCoordinationActivityTable() {
		try {
			Statement st = connection.createStatement();

			st.executeUpdate(
					"INSERT INTO coordination_activities (activityTitle, nameOfPersonResponsible, startDate, endDate, priority, status, description) "
							+
							"VALUES " +
							"('Activity 1', 'Person 1', '2023-09-30', '2023-10-05', 'High', 'In Progress', 'Description 1'), " +
							"('Activity 2', 'Person 2', '2023-10-01', '2023-10-10', 'Medium', 'Completed', 'Description 2'), " +
							"('Activity 3', 'Person 3', '2023-10-05', '2023-10-15', 'Low', 'Not Started', 'Description 3')");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static ArrayList<CoordinationActivity> getCoordinationActivities() {
		ArrayList<CoordinationActivity> coordinationActivities = new ArrayList<>();

		try {
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM coordination_activities");

			while (rs.next()) {
				CoordinationActivity activity = new CoordinationActivity();
				activity.setActivityTitle(rs.getString("activityTitle"));
				activity.setNameOfPersonResponsible(rs.getString("nameOfPersonResponsible"));
				activity.setStartDate(rs.getDate("startDate").toLocalDate());
				activity.setEndDate(rs.getDate("endDate").toLocalDate());
				activity.setPriority(rs.getString("priority"));
				activity.setStatus(rs.getString("status"));
				activity.setDescription(rs.getString("description"));
				activity.setId(rs.getInt("id"));
				coordinationActivities.add(activity);
			}

			return coordinationActivities;
		} catch (SQLException e) {
			System.out.println("Erro ao obter informações sobre as atividades de coordenação");
			return null;
		}
	}

	public static ArrayList<CoordinationActivity> getActivitiesByNameOfPersonResponsible(String personName) {
    ArrayList<CoordinationActivity> coordinationActivities = new ArrayList<>();

    try {
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM coordination_activities WHERE nameOfPersonResponsible='" + personName + "'");

        while (rs.next()) {
            CoordinationActivity activity = new CoordinationActivity();
            activity.setActivityTitle(rs.getString("activityTitle"));
            activity.setNameOfPersonResponsible(rs.getString("nameOfPersonResponsible"));
            activity.setStartDate(rs.getDate("startDate").toLocalDate());
            activity.setEndDate(rs.getDate("endDate").toLocalDate());
            activity.setPriority(rs.getString("priority"));
            activity.setStatus(rs.getString("status"));
            activity.setDescription(rs.getString("description"));
            activity.setId(rs.getInt("id"));
            coordinationActivities.add(activity);
        }

        return coordinationActivities;
    } catch (SQLException e) {
        System.out.println("Erro ao obter informações sobre as atividades de coordenação");
        return null;
    }
}

	public static boolean postCoordinationActivity(CoordinationActivity activity) {
		try {
			Statement st = connection.createStatement();
			st.executeUpdate(
					"INSERT INTO coordination_activities (activityTitle, nameOfPersonResponsible, startDate, endDate, priority, status, description) VALUES ('"
							+ activity.getActivityTitle() + "', '"
							+ activity.getNameOfPersonResponsible() + "', '"
							+ activity.getStartDate() + "', '"
							+ activity.getEndDate() + "', '"
							+ activity.getPriority() + "', '"
							+ activity.getStatus() + "', '"
							+ activity.getDescription()
							+ "')");

			return true;
		} catch (SQLException e) {
			return false;
		}
	}

	public static boolean deleteCoordinationActivityById(int id) {
		try {
			Statement st = connection.createStatement();
			st.executeUpdate("DELETE FROM coordination_activities WHERE id=" + id);
			return true;
		} catch (SQLException e) {
			return false;
		}
	}

	public static boolean updateCoordinationActivityById(int id, CoordinationActivity activity) {
		try {
			Statement st = connection.createStatement();
			st.executeUpdate(
					"UPDATE coordination_activities SET"
							+ " activityTitle='" + activity.getActivityTitle() + "', "
							+ " nameOfPersonResponsible='" + activity.getNameOfPersonResponsible() + "', "
							+ " startDate='" + activity.getStartDate() + "', "
							+ " endDate='" + activity.getEndDate() + "', "
							+ " priority='" + activity.getPriority() + "', "
							+ " status='" + activity.getStatus() + "', "
							+ " description='" + activity.getDescription() + "'"
							+ " WHERE id=" + id);
			return true;
		} catch (SQLException e) {
			return false;
		}
	}
}
