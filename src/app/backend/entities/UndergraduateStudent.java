package app.backend.entities;

/**
 * Classe que representa um estudante de graduação, que é uma extensão da classe
 * Student.
 */
public class UndergraduateStudent extends Student {
  private String projectName; // Nome do projeto do estudante de graduação.
  private String typeOfOrientation; // Tipo de orientação do estudante de graduação.

  /**
   * Define o nome do projeto do estudante de graduação.
   *
   * @param projectName O nome do projeto.
   */
  public void setProjectName(String projectName) {
    this.projectName = projectName;
  }

  /**
   * Define o tipo de orientação do estudante de graduação.
   *
   * @param typeOfOrientation O tipo de orientação.
   */
  public void setTypeOfOrientation(String typeOfOrientation) {
    this.typeOfOrientation = typeOfOrientation;
  }

  /**
   * Obtém o nome do projeto do estudante de graduação.
   *
   * @return O nome do projeto.
   */
  public String getProjectName() {
    return projectName;
  }

  /**
   * Obtém o tipo de orientação do estudante de graduação.
   *
   * @return O tipo de orientação.
   */
  public String getTypeOfOrientation() {
    return typeOfOrientation;
  }

  /**
   * Retorna uma representação em string do objeto UndergraduateStudent.
   *
   * @return Uma representação em string do objeto.
   */
  @Override
  public String toString() {
    return "UndergraduateStudent{" +
        "projectName='" + projectName + '\'' +
        ", typeOfOrientation='" + typeOfOrientation + '\'' +
        "} " + super.toString();
  }
}
