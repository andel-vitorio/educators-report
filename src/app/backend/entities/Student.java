package app.backend.entities;

import java.time.LocalDate;

/**
 * Classe que representa um estudante.
 */
public class Student {
  private String name; // Nome do estudante.
  private LocalDate dateOfEntry; // Data de entrada do estudante.
  private String registration; // Registro do estudante.
  private String email; // Email do estudante.
  private String phoneNumber; // Número de telefone do estudante.
  private String nameOfMentee; // Nome do mentor do estudante.
  private String status; // Status do estudante.
  private int id; // Identificador único para o estudante.

  /**
   * Define o identificador único para o estudante.
   *
   * @param id O identificador único.
   */
  public void setId(int id) {
    this.id = id;
  }

  /**
   * Obtém o identificador único para o estudante.
   *
   * @return O identificador único.
   */
  public int getId() {
    return id;
  }

  /**
   * Define a data de entrada do estudante.
   *
   * @param dateOfEntry A data de entrada do estudante.
   */
  public void setDateOfEntry(LocalDate dateOfEntry) {
    this.dateOfEntry = dateOfEntry;
  }

  /**
   * Define o email do estudante.
   *
   * @param email O email do estudante.
   */
  public void setEmail(String email) {
    this.email = email;
  }

  /**
   * Define o nome do estudante.
   *
   * @param name O nome do estudante.
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Define o nome do mentor do estudante.
   *
   * @param nameOfMentee O nome do mentor do estudante.
   */
  public void setNameOfMentee(String nameOfMentee) {
    this.nameOfMentee = nameOfMentee;
  }

  /**
   * Define o número de telefone do estudante.
   *
   * @param phoneNumber O número de telefone do estudante.
   */
  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  /**
   * Define o registro do estudante.
   *
   * @param registration O registro do estudante.
   */
  public void setRegistration(String registration) {
    this.registration = registration;
  }

  /**
   * Define o status do estudante.
   *
   * @param status O status do estudante.
   */
  public void setStatus(String status) {
    this.status = status;
  }

  /**
   * Obtém a data de entrada do estudante.
   *
   * @return A data de entrada do estudante.
   */
  public LocalDate getDateOfEntry() {
    return dateOfEntry;
  }

  /**
   * Obtém o email do estudante.
   *
   * @return O email do estudante.
   */
  public String getEmail() {
    return email;
  }

  /**
   * Obtém o nome do estudante.
   *
   * @return O nome do estudante.
   */
  public String getName() {
    return name;
  }

  /**
   * Obtém o nome do mentor do estudante.
   *
   * @return O nome do mentor do estudante.
   */
  public String getNameOfMentee() {
    return nameOfMentee;
  }

  /**
   * Obtém o número de telefone do estudante.
   *
   * @return O número de telefone do estudante.
   */
  public String getPhoneNumber() {
    return phoneNumber;
  }

  /**
   * Obtém o registro do estudante.
   *
   * @return O registro do estudante.
   */
  public String getRegistration() {
    return registration;
  }

  /**
   * Obtém o status do estudante.
   *
   * @return O status do estudante.
   */
  public String getStatus() {
    return status;
  }

  /**
   * Retorna uma representação em string do objeto Student.
   *
   * @return Uma representação em string do objeto.
   */
  @Override
  public String toString() {
    return "Student{" +
        "name='" + name + '\'' +
        ", dateOfEntry=" + dateOfEntry +
        ", registration='" + registration + '\'' +
        ", email='" + email + '\'' +
        ", phoneNumber='" + phoneNumber + '\'' +
        ", nameOfMentee='" + nameOfMentee + '\'' +
        ", status='" + status + '\'' +
        '}';
  }
}
