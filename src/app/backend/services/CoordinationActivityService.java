package app.backend.services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import app.backend.Database;
import app.backend.entities.CoordinationActivity;
import java.time.LocalDate;

/**
 * Classe de serviço para gerenciar atividades de coordenação no banco de dados.
 */
public class CoordinationActivityService extends Database {

    /**
     * Construtor da classe CoordinationActivityService. Inicializa a conexão com o banco de dados e cria a tabela se não existir.
     */
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

    /**
     * Método para popular a tabela de atividades de coordenação com dados de exemplo.
     */
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

    /**
     * Método para obter todas as atividades de coordenação do banco de dados.
     *
     * @return Uma lista de atividades de coordenação.
     */
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

    /**
     * Método para obter atividades de coordenação por nome da pessoa responsável.
     *
     * @param personName O nome da pessoa responsável.
     * @return Uma lista de atividades de coordenação relacionadas à pessoa responsável.
     */
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

    /**
     * Método para adicionar uma nova atividade de coordenação ao banco de dados.
     *
     * @param activity A atividade de coordenação a ser adicionada.
     * @return true se a operação for bem-sucedida, false caso contrário.
     */
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

    /**
     * Método para excluir uma atividade de coordenação do banco de dados por ID.
     *
     * @param id O ID da atividade de coordenação a ser excluída.
     * @return true se a operação for bem-sucedida, false caso contrário.
     */
    public static boolean deleteCoordinationActivityById(int id) {
        try {
            Statement st = connection.createStatement();
            st.executeUpdate("DELETE FROM coordination_activities WHERE id=" + id);
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    /**
     * Método para atualizar uma atividade de coordenação no banco de dados por ID.
     *
     * @param id       O ID da atividade de coordenação a ser atualizada.
     * @param activity A nova atividade de coordenação.
     * @return true se a operação for bem-sucedida, false caso contrário.
     */
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
