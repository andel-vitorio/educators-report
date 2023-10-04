package app.backend.entities;

import java.time.LocalTime;

/**
 * Classe que representa disciplinas.
 */
public class Subjects {
    private String code; // Código da disciplina.
    private String name; // Nome da disciplina.
    private String description; // Descrição da disciplina.
    private LocalTime startTime; // Hora de início da disciplina.
    private LocalTime endTime; // Hora de término da disciplina.
    private String classroom; // Sala de aula da disciplina.
    private String teacherName; // Nome do professor responsável pela disciplina.
    private String requirements; // Requisitos da disciplina.
    private int courseLoad; // Carga horária da disciplina.
    private int credits; // Créditos da disciplina.
    private int numberOfVacancies; // Número de vagas disponíveis para a disciplina.
    private int id; // Identificador único para a disciplina.

    /**
     * Define o identificador único para a disciplina.
     *
     * @param id O identificador único.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Obtém o identificador único para a disciplina.
     *
     * @return O identificador único.
     */
    public int getId() {
        return id;
    }

    /**
     * Define a sala de aula da disciplina.
     *
     * @param classroom A sala de aula da disciplina.
     */
    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }

    /**
     * Define o código da disciplina.
     *
     * @param code O código da disciplina.
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Define a carga horária da disciplina.
     *
     * @param courseLoad A carga horária da disciplina.
     */
    public void setCourseLoad(int courseLoad) {
        this.courseLoad = courseLoad;
    }

    /**
     * Define os créditos da disciplina.
     *
     * @param credits Os créditos da disciplina.
     */
    public void setCredits(int credits) {
        this.credits = credits;
    }

    /**
     * Define a descrição da disciplina.
     *
     * @param description A descrição da disciplina.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Define a hora de término da disciplina.
     *
     * @param endTime A hora de término da disciplina.
     */
    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    /**
     * Define o nome da disciplina.
     *
     * @param name O nome da disciplina.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Define o número de vagas disponíveis para a disciplina.
     *
     * @param numberOfVacancies O número de vagas disponíveis.
     */
    public void setNumberOfVacancies(int numberOfVacancies) {
        this.numberOfVacancies = numberOfVacancies;
    }

    /**
     * Define os requisitos da disciplina.
     *
     * @param requirements Os requisitos da disciplina.
     */
    public void setRequirements(String requirements) {
        this.requirements = requirements;
    }

    /**
     * Define a hora de início da disciplina.
     *
     * @param startTime A hora de início da disciplina.
     */
    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    /**
     * Define o nome do professor responsável pela disciplina.
     *
     * @param teacherName O nome do professor.
     */
    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    /**
     * Obtém a sala de aula da disciplina.
     *
     * @return A sala de aula da disciplina.
     */
    public String getClassroom() {
        return classroom;
    }

    /**
     * Obtém o código da disciplina.
     *
     * @return O código da disciplina.
     */
    public String getCode() {
        return code;
    }

    /**
     * Obtém a carga horária da disciplina.
     *
     * @return A carga horária da disciplina.
     */
    public int getCourseLoad() {
        return courseLoad;
    }

    /**
     * Obtém os créditos da disciplina.
     *
     * @return Os créditos da disciplina.
     */
    public int getCredits() {
        return credits;
    }

    /**
     * Obtém a descrição da disciplina.
     *
     * @return A descrição da disciplina.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Obtém a hora de término da disciplina.
     *
     * @return A hora de término da disciplina.
     */
    public LocalTime getEndTime() {
        return endTime;
    }

    /**
     * Obtém o nome da disciplina.
     *
     * @return O nome da disciplina.
     */
    public String getName() {
        return name;
    }

    /**
     * Obtém o número de vagas disponíveis para a disciplina.
     *
     * @return O número de vagas disponíveis.
     */
    public int getNumberOfVacancies() {
        return numberOfVacancies;
    }

    /**
     * Obtém os requisitos da disciplina.
     *
     * @return Os requisitos da disciplina.
     */
    public String getRequirements() {
        return requirements;
    }

    /**
     * Obtém a hora de início da disciplina.
     *
     * @return A hora de início da disciplina.
     */
    public LocalTime getStartTime() {
        return startTime;
    }

    /**
     * Obtém o nome do professor responsável pela disciplina.
     *
     * @return O nome do professor.
     */
    public String getTeacherName() {
        return teacherName;
    }

    /**
     * Retorna uma representação em string do objeto Subjects.
     *
     * @return Uma representação em string do objeto.
     */
    @Override
    public String toString() {
        return "Subjects{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", classroom='" + classroom + '\'' +
                ", teacherName='" + teacherName + '\'' +
                ", requirements='" + requirements + '\'' +
                ", courseLoad=" + courseLoad +
                ", credits=" + credits +
                ", numberOfVacancies=" + numberOfVacancies +
                '}';
    }
}
