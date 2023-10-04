package app.backend.entities;

import java.time.LocalDate;

/**
 * Classe que representa um professor.
 */
public class Teacher {
    private String name; // Nome do professor.
    private LocalDate birthDay; // Data de nascimento do professor.
    private String indentificatorNumber; // Número de identificação do professor.
    private String email; // Email do professor.
    private String phone; // Número de telefone do professor.
    private String trainingArea; // Área de formação do professor.
    private int yearsOfExperience; // Anos de experiência do professor.
    private int id; // Identificador único para o professor.

    /**
     * Define a data de nascimento do professor.
     *
     * @param birthDay A data de nascimento do professor.
     */
    public void setBirthDay(LocalDate birthDay) {
        this.birthDay = birthDay;
    }

    /**
     * Define o email do professor.
     *
     * @param email O email do professor.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Define o número de identificação do professor.
     *
     * @param indenticatorNumber O número de identificação do professor.
     */
    public void setIndentificatorNumber(String indenticatorNumber) {
        this.indentificatorNumber = indenticatorNumber;
    }

    /**
     * Define o nome do professor.
     *
     * @param name O nome do professor.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Define o número de telefone do professor.
     *
     * @param phone O número de telefone do professor.
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Define a área de formação do professor.
     *
     * @param trainingArea A área de formação do professor.
     */
    public void setTrainingArea(String trainingArea) {
        this.trainingArea = trainingArea;
    }

    /**
     * Define os anos de experiência do professor.
     *
     * @param yearsOfExperience Os anos de experiência do professor.
     */
    public void setYearsOfExperience(int yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }

    /**
     * Define o identificador único para o professor.
     *
     * @param id O identificador único.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Obtém a data de nascimento do professor.
     *
     * @return A data de nascimento do professor.
     */
    public LocalDate getBirthDay() {
        return birthDay;
    }

    /**
     * Obtém o email do professor.
     *
     * @return O email do professor.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Obtém o número de identificação do professor.
     *
     * @return O número de identificação do professor.
     */
    public String getIndentificatorNumber() {
        return indentificatorNumber;
    }

    /**
     * Obtém o nome do professor.
     *
     * @return O nome do professor.
     */
    public String getName() {
        return name;
    }

    /**
     * Obtém o número de telefone do professor.
     *
     * @return O número de telefone do professor.
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Obtém a área de formação do professor.
     *
     * @return A área de formação do professor.
     */
    public String getTrainingArea() {
        return trainingArea;
    }

    /**
     * Obtém os anos de experiência do professor.
     *
     * @return Os anos de experiência do professor.
     */
    public int getYearsOfExperience() {
        return yearsOfExperience;
    }

    /**
     * Obtém o identificador único do professor.
     *
     * @return O identificador único.
     */
    public int getId() {
        return id;
    }

    /**
     * Retorna uma representação em string do objeto Teacher.
     *
     * @return Uma representação em string do objeto.
     */
    @Override
    public String toString() {
        return "Teacher{" +
                "name='" + name + '\'' +
                ", birthDay=" + birthDay +
                ", indenticatorNumber='" + indentificatorNumber + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", trainingArea='" + trainingArea + '\'' +
                ", yearsOfExperience=" + yearsOfExperience +
                '}';
    }
}
