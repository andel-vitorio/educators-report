package app.backend.entities;

import java.time.LocalDate;

/**
 * Classe que representa uma atividade de coordenação.
 */
public class CoordinationActivity {
  private String activityTitle; // Título da atividade.
  private String nameOfPersonResponsible; // Nome da pessoa responsável pela atividade.
  private LocalDate startDate; // Data de início da atividade.
  private LocalDate endDate; // Data de término da atividade.
  private String priority; // Prioridade da atividade.
  private String status; // Status da atividade.
  private String description; // Descrição da atividade.
  private int id; // Identificador único para a atividade.

  /**
   * Define o identificador único para a atividade.
   *
   * @param id O identificador único.
   */
  public void setId(int id) {
    this.id = id;
  }

  /**
   * Obtém o identificador único para a atividade.
   *
   * @return O identificador único.
   */
  public int getId() {
    return id;
  }

  /**
   * Define o título da atividade.
   *
   * @param activityTitle O título da atividade.
   */
  public void setActivityTitle(String activityTitle) {
    this.activityTitle = activityTitle;
  }

  /**
   * Define a descrição da atividade.
   *
   * @param description Uma descrição da atividade.
   */
  public void setDescription(String description) {
    this.description = description;
  }

  /**
   * Define a data de término da atividade.
   *
   * @param endDate A data de término da atividade.
   */
  public void setEndDate(LocalDate endDate) {
    this.endDate = endDate;
  }

  /**
   * Define o nome da pessoa responsável pela atividade.
   *
   * @param nameOfPersonResponsible O nome da pessoa responsável.
   */
  public void setNameOfPersonResponsible(String nameOfPersonResponsible) {
    this.nameOfPersonResponsible = nameOfPersonResponsible;
  }

  /**
   * Define a prioridade da atividade.
   *
   * @param priority A prioridade da atividade.
   */
  public void setPriority(String priority) {
    this.priority = priority;
  }

  /**
   * Define a data de início da atividade.
   *
   * @param startDate A data de início da atividade.
   */
  public void setStartDate(LocalDate startDate) {
    this.startDate = startDate;
  }

  /**
   * Define o status da atividade.
   *
   * @param status O status da atividade.
   */
  public void setStatus(String status) {
    this.status = status;
  }

  /**
   * Obtém o título da atividade.
   *
   * @return O título da atividade.
   */
  public String getActivityTitle() {
    return activityTitle;
  }

  /**
   * Obtém a descrição da atividade.
   *
   * @return Uma descrição da atividade.
   */
  public String getDescription() {
    return description;
  }

  /**
   * Obtém a data de término da atividade.
   *
   * @return A data de término da atividade.
   */
  public LocalDate getEndDate() {
    return endDate;
  }

  /**
   * Obtém o nome da pessoa responsável pela atividade.
   *
   * @return O nome da pessoa responsável.
   */
  public String getNameOfPersonResponsible() {
    return nameOfPersonResponsible;
  }

  /**
   * Obtém a prioridade da atividade.
   *
   * @return A prioridade da atividade.
   */
  public String getPriority() {
    return priority;
  }

  /**
   * Obtém a data de início da atividade.
   *
   * @return A data de início da atividade.
   */
  public LocalDate getStartDate() {
    return startDate;
  }

  /**
   * Obtém o status da atividade.
   *
   * @return O status da atividade.
   */
  public String getStatus() {
    return status;
  }

  /**
   * Retorna uma representação em string do objeto CoordinationActivity.
   *
   * @return Uma representação em string do objeto.
   */
  @Override
  public String toString() {
    return "CoordinationActivity{" +
        "activityTitle='" + activityTitle + '\'' +
        ", nameOfPersonResponsible='" + nameOfPersonResponsible + '\'' +
        ", startDate=" + startDate +
        ", endDate=" + endDate +
        ", priority='" + priority + '\'' +
        ", status='" + status + '\'' +
        ", description='" + description + '\'' +
        '}';
  }
}
