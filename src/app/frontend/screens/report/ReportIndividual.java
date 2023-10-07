package app.frontend.screens.report;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import app.frontend.components.FormField;
import res.fonts.FontsManager;
import res.fonts.FontsManager.FontType;
import res.values.ColorsManager;
import res.values.DimensManager;
import utils.ComponentDecorator;

import app.backend.entities.*;
import app.backend.services.*;
import app.frontend.components.TimePicker;

import javax.swing.BoxLayout;
import app.frontend.components.DatePicker;

/**
 * A classe ReportIndividual representa uma janela de relatório individual para
 * um professor.
 */
public class ReportIndividual extends JFrame {

  private Container container;
  Teacher teacher;

  /**
   * Construtor da classe ReportIndividual.
   * 
   * @param teacher O professor para o qual o relatório individual está sendo
   *                gerado.
   */
  public ReportIndividual(Teacher teacher) {
    super("");

    this.teacher = teacher;

    String title = "Relatório";

    setTitle(title);
    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    setPreferredSize(new Dimension(600, 650));
    setSize(getPreferredSize());
    setResizable(false);
    setLocationRelativeTo(null);

    container = getContentPane();
    container.setBackground(ColorsManager.getOnBackgroundColor());
    container.setLayout(new BorderLayout());

    JLabel titleLabel = new JLabel(title, JLabel.LEFT);
    titleLabel.setFont(FontsManager.getFont(FontType.BOLD, DimensManager.getTitleFormsFontsize()));
    titleLabel.setBackground(ColorsManager.getBackgroundColor());
    ComponentDecorator.addPadding(titleLabel, 24);

    container.add(titleLabel, BorderLayout.PAGE_START);
    container.add(getContentContainer(), BorderLayout.CENTER);

    setVisible(true);
  }

  /**
   * Cria e retorna o contêiner de conteúdo com todos os relatórios.
   * 
   * @return Um JScrollPane contendo todos os relatórios.
   */
  private JScrollPane getContentContainer() {
    JPanel contentContainer = new JPanel();
    contentContainer.setLayout(new BoxLayout(contentContainer, BoxLayout.PAGE_AXIS));
    contentContainer.setOpaque(false);

    ArrayList<Subjects> subjects = SubjectsService.getSubjectsByTeacherName(teacher.getName());

    for (Subjects subject : subjects) {
      contentContainer.add(getSubjectReport(subject));
    }

    ArrayList<UndergraduateStudent> undergraduateStudents = UndergraduateStudentService
        .getUndergraduateStudentsByNameOfMentee(teacher.getName());
    ArrayList<PosgraduateStudent> posgraduateStudents = PosgraduateStudentService
        .getPosgraduateStudentsByNameOfMentee(teacher.getName());

    for (UndergraduateStudent undergraduateStudent : undergraduateStudents)
      contentContainer.add(getStudentReport(undergraduateStudent, "Aluno de Graduação"));

    for (PosgraduateStudent posgraduateStudent : posgraduateStudents)
      contentContainer.add(getStudentReport(posgraduateStudent, "Aluno de Pós-graduação"));

    ArrayList<Paper> papers = PaperService.getPapersByAuthor(teacher.getName());

    for (Paper paper : papers) {
      contentContainer.add(getPaperReport(paper));
    }

    ArrayList<CoordinationActivity> coordinationActivities = CoordinationActivityService
        .getActivitiesByNameOfPersonResponsible(teacher.getName());

    for (CoordinationActivity activity : coordinationActivities) {
      contentContainer.add(getActivityReport(activity));
    }

    JScrollPane scrollPane = new JScrollPane(contentContainer);
    scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    scrollPane.getViewport().setBackground(ColorsManager.getOnBackgroundColor());
    scrollPane.setBorder(null);

    return scrollPane;
  }

  /**
   * Cria e configura um JLabel para ser usado como rótulo de seção em uma
   * interface gráfica.
   *
   * @param text O texto a ser exibido no rótulo.
   * @return Um JLabel configurado como rótulo de seção.
   */
  private JLabel getSectionLabel(String text) {
    JLabel label = new JLabel(text, JLabel.LEFT);
    label.setFont(FontsManager.getFont(FontType.SEMI_BOLD, DimensManager.getSectionLabelFontsize()));
    label.setOpaque(false);
    ComponentDecorator.addPaddingBottom(label, 12);
    return label;
  }

  /**
   * Cria e retorna um JPanel que exibe um relatório das informações de uma
   * disciplina.
   *
   * @param subject A disciplina da qual exibir as informações.
   * @return Um JPanel configurado para exibir as informações da disciplina.
   */
  private JPanel getSubjectReport(Subjects subject) {

    FormField codeFormField;
    FormField nameFormField;
    FormField descriptionFormField;
    TimePicker startTimePicker;
    TimePicker endTimePicker;
    FormField classroomFormField;
    FormField teacherNameFormField;
    FormField requirementsFormField;
    FormField courseLoadFormField;
    FormField creditsFormField;
    FormField numberOfVacanciesFormField;

    JPanel formContainer = new JPanel(new GridBagLayout());
    ComponentDecorator.addPadding(formContainer, 0, 24);
    formContainer.setOpaque(false);
    int y = 0;

    GridBagConstraints constraints = new GridBagConstraints();

    // Seção: Informações Pessoais
    constraints.fill = GridBagConstraints.HORIZONTAL;
    constraints.anchor = GridBagConstraints.PAGE_START;
    constraints.gridwidth = 3;
    constraints.gridx = 0;
    constraints.gridy = y++;

    formContainer.add(getSectionLabel("Dados da Disciplina"), constraints);

    constraints.gridwidth = 1;
    constraints.weightx = 0.5;
    constraints.gridy = y;
    constraints.insets = new Insets(0, 0, 16, 12);
    codeFormField = new FormField("Código da Disciplina", 0);
    codeFormField.setEditable(false);
    formContainer.add(codeFormField, constraints);

    constraints.insets = new Insets(0, 12, 16, 0);
    constraints.gridwidth = 2;
    constraints.gridx = 1;
    constraints.gridy = y++;
    nameFormField = new FormField("Nome da disciplina", 0);
    nameFormField.setEditable(false);
    formContainer.add(nameFormField, constraints);

    constraints.insets = new Insets(0, 0, 16, 0);
    constraints.gridy = y++;
    constraints.gridx = 0;
    constraints.gridwidth = 3;
    constraints.fill = GridBagConstraints.BOTH;
    descriptionFormField = new FormField("Descrição", 504);
    descriptionFormField.setHeight(100);
    descriptionFormField.setEditable(false);
    descriptionFormField.isMultiline(true);
    formContainer.add(descriptionFormField, constraints);

    constraints.insets = new Insets(0, 0, 0, 0);
    constraints.gridwidth = 3;
    constraints.gridheight = 1;
    constraints.gridx = 0;
    constraints.gridy = y++;

    formContainer.add(getSectionLabel("Horário e Local"), constraints);

    constraints.gridwidth = 1;
    constraints.weightx = 0.5;
    constraints.gridy = y;
    constraints.insets = new Insets(0, 0, 16, 12);
    startTimePicker = new TimePicker("Início", 0);
    startTimePicker.setEditable(false);
    formContainer.add(startTimePicker, constraints);

    constraints.gridwidth = 1;
    constraints.weightx = 0.5;
    constraints.gridx = 1;
    constraints.gridy = y;
    constraints.insets = new Insets(0, 0, 16, 12);
    endTimePicker = new TimePicker("Fim", 0);
    endTimePicker.setEditable(false);
    formContainer.add(endTimePicker, constraints);

    constraints.insets = new Insets(0, 12, 16, 0);
    constraints.gridwidth = 1;
    constraints.gridx = 2;
    constraints.gridy = y++;
    classroomFormField = new FormField("Sala", 0);
    classroomFormField.setEditable(false);
    formContainer.add(classroomFormField, constraints);

    constraints.insets = new Insets(0, 0, 0, 0);
    constraints.gridwidth = 3;
    constraints.gridheight = 1;
    constraints.gridx = 0;
    constraints.gridy = y++;

    formContainer.add(getSectionLabel("Informações do Professor"), constraints);

    constraints.insets = new Insets(0, 0, 16, 0);
    constraints.gridy = y++;
    constraints.gridx = 0;
    constraints.gridwidth = 3;

    teacherNameFormField = new FormField("Nome do Professor", 504);
    teacherNameFormField.setEditable(false);
    formContainer.add(teacherNameFormField, constraints);

    constraints.insets = new Insets(0, 0, 0, 0);
    constraints.gridwidth = 3;
    constraints.gridheight = 1;
    constraints.gridx = 0;
    constraints.gridy = y++;

    formContainer.add(getSectionLabel("Outras Informações"), constraints);

    constraints.insets = new Insets(0, 0, 16, 0);
    constraints.gridy = y++;
    constraints.gridx = 0;
    constraints.gridwidth = 3;

    requirementsFormField = new FormField("Pré-requisitos", 504);
    requirementsFormField.setEditable(false);
    formContainer.add(requirementsFormField, constraints);

    constraints.gridwidth = 1;
    constraints.weightx = 0.5;
    constraints.gridy = y;
    constraints.insets = new Insets(0, 0, 16, 12);
    courseLoadFormField = new FormField("Carga Horária", 0);
    courseLoadFormField.setEditable(false);
    formContainer.add(courseLoadFormField, constraints);

    constraints.insets = new Insets(0, 12, 16, 0);
    constraints.gridwidth = 1;
    constraints.gridx = 1;
    constraints.gridy = y;
    creditsFormField = new FormField("Créditos", 0);
    creditsFormField.setEditable(false);
    formContainer.add(creditsFormField, constraints);

    constraints.insets = new Insets(0, 12, 16, 0);
    constraints.gridwidth = 1;
    constraints.gridx = 2;
    constraints.gridy = y++;
    numberOfVacanciesFormField = new FormField("Número de Vagas", 0);
    numberOfVacanciesFormField.setEditable(false);
    formContainer.add(numberOfVacanciesFormField, constraints);

    codeFormField.setText(subject.getCode());
    nameFormField.setText(subject.getName());
    descriptionFormField.setText(subject.getDescription());
    startTimePicker.setTime(subject.getStartTime());
    endTimePicker.setTime(subject.getEndTime());
    teacherNameFormField.setText(subject.getTeacherName());
    requirementsFormField.setText(subject.getRequirements());
    courseLoadFormField.setText(subject.getCourseLoad());
    creditsFormField.setText(subject.getCredits());
    numberOfVacanciesFormField.setText(subject.getNumberOfVacancies());
    classroomFormField.setText(subject.getClassroom());

    return formContainer;
  }

  /**
   * Cria e retorna um JPanel que exibe um relatório das informações de um aluno,
   * seja de graduação ou pós-graduação.
   *
   * @param student     O objeto que representa o aluno cujas informações serão
   *                    exibidas.
   * @param typeStudent O tipo de aluno, que pode ser "Aluno de Graduação" ou
   *                    "Aluno de Pós-graduação".
   * @return Um JPanel configurado para exibir as informações do aluno.
   */
  private JPanel getStudentReport(Student student, String typeStudent) {
    JPanel formContainer = new JPanel(new GridBagLayout());
    ComponentDecorator.addPadding(formContainer, 0, 24);
    formContainer.setOpaque(false);

    FormField nameFormField;
    DatePicker dateOfEntryDatePicker;
    FormField registrationFormField;
    FormField emailFormField;
    FormField phoneNumberFormField;
    FormField nameOfMenteeFormField;
    FormField statusFormField;
    FormField projectNameFormField;
    FormField typeOfOrientationFormField;
    FormField posgraduateProgramFormField;
    FormField researchTitleFormField;
    DatePicker defenseDateDatePicker;

    if ("Aluno de Graduação".equals(typeStudent)) {
      int y = 0;

      GridBagConstraints constraints = new GridBagConstraints();

      // Seção: Informações Pessoais
      constraints.fill = GridBagConstraints.BOTH;
      constraints.anchor = GridBagConstraints.PAGE_START;
      constraints.gridwidth = 2;
      constraints.gridx = 0;
      constraints.gridy = y++;
      constraints.insets = new Insets(0, 0, 16, 12);

      formContainer.add(getSectionLabel("Informações Pessoais"), constraints);

      constraints.gridwidth = 2;
      constraints.gridy = y++;
      constraints.insets = new Insets(0, 0, 16, 12);
      nameFormField = new FormField("Nome completo do aluno", 0);
      nameFormField.setEditable(false);
      formContainer.add(nameFormField, constraints);

      constraints.gridwidth = 1;
      constraints.gridy = y;
      constraints.weightx = 1.0;
      constraints.insets = new Insets(0, 0, 16, 12);
      dateOfEntryDatePicker = new DatePicker("Data de Ingresso", 0);
      dateOfEntryDatePicker.setEditable(false);
      formContainer.add(dateOfEntryDatePicker, constraints);

      constraints.gridwidth = 1;
      constraints.gridx = 1;
      constraints.gridy = y++;
      constraints.weightx = 1.0;
      constraints.insets = new Insets(0, 0, 16, 12);
      registrationFormField = new FormField("Matrícula", 0);
      registrationFormField.setEditable(false);
      formContainer.add(registrationFormField, constraints);

      constraints.gridwidth = 2;
      constraints.gridx = 0;
      constraints.gridy = y++;
      constraints.weightx = 1.0;

      formContainer.add(getSectionLabel("Informações de Contato"), constraints);

      constraints.gridwidth = 2;
      constraints.weightx = 1.0;
      constraints.gridy = y++;
      constraints.insets = new Insets(0, 0, 16, 12);
      emailFormField = new FormField("E-mail", 0);
      emailFormField.setEditable(false);
      formContainer.add(emailFormField, constraints);

      constraints.gridwidth = 2;
      constraints.weightx = 1.0;
      constraints.gridy = y++;
      constraints.insets = new Insets(0, 0, 16, 12);
      phoneNumberFormField = new FormField("Número de Telefone", 0);
      phoneNumberFormField.setEditable(false);
      formContainer.add(phoneNumberFormField, constraints);

      constraints.gridwidth = 2;
      constraints.gridx = 0;
      constraints.gridy = y++;

      formContainer.add(getSectionLabel("Informações sobre a Orientação"), constraints);

      constraints.gridwidth = 2;
      constraints.weightx = 1.0;
      constraints.gridy = y++;
      constraints.insets = new Insets(0, 0, 16, 12);
      nameOfMenteeFormField = new FormField("Nome do Orientador", 0);
      nameOfMenteeFormField.setEditable(false);

      if (teacher != null) {
        nameOfMenteeFormField.setText(teacher.getName());
        nameOfMenteeFormField.setEditable(false);
      }

      formContainer.add(nameOfMenteeFormField, constraints);

      constraints.gridwidth = 2;
      constraints.weightx = 1.0;
      constraints.gridy = y++;
      constraints.insets = new Insets(0, 0, 16, 12);
      projectNameFormField = new FormField("Nome do Projeto", 0);
      projectNameFormField.setEditable(false);
      formContainer.add(projectNameFormField, constraints);

      constraints.gridwidth = 1;
      constraints.weightx = 1.0;
      constraints.gridy = y;
      constraints.insets = new Insets(0, 0, 16, 12);
      typeOfOrientationFormField = new FormField("Tipos de Orientação", 0);
      typeOfOrientationFormField.setEditable(false);
      formContainer.add(typeOfOrientationFormField, constraints);

      constraints.gridwidth = 1;
      constraints.weightx = 1.0;
      constraints.gridy = y++;
      constraints.gridx = 1;
      constraints.insets = new Insets(0, 0, 16, 12);
      statusFormField = new FormField("Status", 0);
      statusFormField.setEditable(false);
      formContainer.add(statusFormField, constraints);

      UndergraduateStudent undergraduateStudent = (UndergraduateStudent) student;
      projectNameFormField.setText(undergraduateStudent.getProjectName());
      typeOfOrientationFormField.setText(undergraduateStudent.getTypeOfOrientation());
      nameFormField.setText(student.getName());
      dateOfEntryDatePicker.setDate(student.getDateOfEntry());
      registrationFormField.setText(student.getRegistration());
      emailFormField.setText(student.getEmail());
      phoneNumberFormField.setText(student.getPhoneNumber());
      nameOfMenteeFormField.setText(student.getNameOfMentee());
      statusFormField.setText(student.getStatus());
    } else if ("Aluno de Pós-graduação".equals(typeStudent)) {
      int y = 0;

      GridBagConstraints constraints = new GridBagConstraints();

      // Seção: Informações Pessoais
      constraints.fill = GridBagConstraints.BOTH;
      constraints.anchor = GridBagConstraints.PAGE_START;
      constraints.gridwidth = 2;
      constraints.gridx = 0;
      constraints.gridy = y++;
      constraints.insets = new Insets(0, 0, 16, 12);

      formContainer.add(getSectionLabel("Informações Pessoais"), constraints);

      constraints.gridwidth = 2;
      constraints.gridy = y++;
      constraints.insets = new Insets(0, 0, 16, 12);
      nameFormField = new FormField("Nome completo do aluno", 0);
      nameFormField.setEditable(false);
      formContainer.add(nameFormField, constraints);

      constraints.gridwidth = 1;
      constraints.gridy = y;
      constraints.weightx = 1.0;
      constraints.insets = new Insets(0, 0, 16, 12);
      dateOfEntryDatePicker = new DatePicker("Data de Ingresso", 0);
      dateOfEntryDatePicker.setEditable(false);
      formContainer.add(dateOfEntryDatePicker, constraints);

      constraints.gridwidth = 1;
      constraints.gridx = 1;
      constraints.gridy = y++;
      constraints.weightx = 1.0;
      constraints.insets = new Insets(0, 0, 16, 12);
      registrationFormField = new FormField("Matrícula", 0);
      registrationFormField.setEditable(false);
      formContainer.add(registrationFormField, constraints);

      constraints.gridwidth = 2;
      constraints.gridx = 0;
      constraints.gridy = y++;
      constraints.weightx = 1.0;

      formContainer.add(getSectionLabel("Informações de Contato"), constraints);

      constraints.gridwidth = 2;
      constraints.weightx = 1.0;
      constraints.gridy = y++;
      constraints.insets = new Insets(0, 0, 16, 12);
      emailFormField = new FormField("E-mail", 0);
      emailFormField.setEditable(false);
      formContainer.add(emailFormField, constraints);

      constraints.gridwidth = 2;
      constraints.weightx = 1.0;
      constraints.gridy = y++;
      constraints.insets = new Insets(0, 0, 16, 12);
      phoneNumberFormField = new FormField("Número de Telefone", 0);
      phoneNumberFormField.setEditable(false);
      formContainer.add(phoneNumberFormField, constraints);

      constraints.gridwidth = 2;
      constraints.gridx = 0;
      constraints.gridy = y++;

      formContainer.add(getSectionLabel("Informações sobre a Orientação"), constraints);

      constraints.gridwidth = 2;
      constraints.weightx = 1.0;
      constraints.gridy = y++;
      constraints.insets = new Insets(0, 0, 16, 12);
      nameOfMenteeFormField = new FormField("Nome do Orientador", 0);
      nameOfMenteeFormField.setEditable(false);

      formContainer.add(nameOfMenteeFormField, constraints);

      constraints.gridwidth = 2;
      constraints.weightx = 1.0;
      constraints.gridy = y++;
      constraints.insets = new Insets(0, 0, 16, 12);
      posgraduateProgramFormField = new FormField("Programa de Pós-graduação", 0);
      posgraduateProgramFormField.setEditable(false);
      formContainer.add(posgraduateProgramFormField, constraints);

      constraints.gridwidth = 2;
      constraints.weightx = 1.0;
      constraints.gridy = y++;
      constraints.insets = new Insets(0, 0, 16, 12);
      researchTitleFormField = new FormField("Título da Pesquisa", 0);
      researchTitleFormField.setEditable(false);
      formContainer.add(researchTitleFormField, constraints);

      constraints.gridwidth = 1;
      constraints.weightx = 1.0;
      constraints.gridy = y;
      constraints.insets = new Insets(0, 0, 16, 12);
      defenseDateDatePicker = new DatePicker("Data de Defesa", 0);
      defenseDateDatePicker.setEditable(false);
      formContainer.add(defenseDateDatePicker, constraints);

      constraints.gridwidth = 1;
      constraints.weightx = 1.0;
      constraints.gridy = y++;
      constraints.gridx = 1;
      constraints.insets = new Insets(0, 0, 16, 12);
      statusFormField = new FormField("Status", 0);
      statusFormField.setEditable(false);
      formContainer.add(statusFormField, constraints);

      PosgraduateStudent posgraduateStudent = (PosgraduateStudent) student;
      posgraduateProgramFormField.setText(posgraduateStudent.getPosgraduateProgram());
      researchTitleFormField.setText(posgraduateStudent.getResearchTitle());
      defenseDateDatePicker.setDate(posgraduateStudent.getDefenseDate());
      nameFormField.setText(student.getName());
      dateOfEntryDatePicker.setDate(student.getDateOfEntry());
      registrationFormField.setText(student.getRegistration());
      emailFormField.setText(student.getEmail());
      phoneNumberFormField.setText(student.getPhoneNumber());
      nameOfMenteeFormField.setText(student.getNameOfMentee());
      statusFormField.setText(student.getStatus());
    }

    return formContainer;
  }

  /**
   * Cria e retorna um JPanel que exibe um relatório das informações de um artigo.
   *
   * @param paper O objeto que representa o artigo cujas informações serão
   *              exibidas.
   * @return Um JPanel configurado para exibir as informações do artigo.
   */
  private JPanel getPaperReport(Paper paper) {
    FormField titleFormField;
    FormField authorsFormField;
    DatePicker publicationDatePicker;
    FormField keywordsFormField;
    FormField descriptionFormField;
    FormField categoryFormField;
    FormField urlFormField;

    JPanel formContainer = new JPanel(new GridBagLayout());
    ComponentDecorator.addPadding(formContainer, 0, 24);
    formContainer.setOpaque(false);
    int y = 0;

    GridBagConstraints constraints = new GridBagConstraints();

    constraints.fill = GridBagConstraints.HORIZONTAL;
    constraints.anchor = GridBagConstraints.PAGE_START;
    constraints.gridwidth = 2;
    constraints.gridx = 0;
    constraints.gridy = y++;
    constraints.weightx = 1.0;

    formContainer.add(getSectionLabel("Informações Gerais"), constraints);

    constraints.insets = new Insets(0, 0, 16, 0);
    constraints.gridwidth = 2;
    constraints.gridx = 0;
    constraints.gridy = y++;
    titleFormField = new FormField("Título do Artigo", 0);
    titleFormField.setEditable(false);
    formContainer.add(titleFormField, constraints);

    constraints.insets = new Insets(0, 0, 16, 0);
    constraints.gridy = y++;
    constraints.gridx = 0;
    constraints.gridwidth = 2;
    constraints.fill = GridBagConstraints.BOTH;
    authorsFormField = new FormField("Autor(es)", 504);
    authorsFormField.setHeight(100);
    authorsFormField.setEditable(false);
    authorsFormField.isMultiline(true);
    formContainer.add(authorsFormField, constraints);

    constraints.gridwidth = 1;
    constraints.weightx = 0.5;
    constraints.gridy = y;
    constraints.insets = new Insets(0, 0, 16, 12);
    publicationDatePicker = new DatePicker("Data de Publicação", 0);
    publicationDatePicker.setEditable(false);
    formContainer.add(publicationDatePicker, constraints);

    constraints.insets = new Insets(0, 12, 16, 0);
    constraints.gridwidth = 1;
    constraints.gridx = 1;
    constraints.gridy = y++;
    keywordsFormField = new FormField("Palavras-chaves", 0);
    keywordsFormField.setEditable(false);
    formContainer.add(keywordsFormField, constraints);

    constraints.weightx = 0;
    constraints.gridwidth = 2;
    constraints.gridx = 0;
    constraints.gridy = y++;
    constraints.insets = new Insets(0, 0, 16, 0);

    formContainer.add(getSectionLabel("Conteúdo do Artigo"), constraints);

    constraints.insets = new Insets(0, 0, 16, 0);
    constraints.gridy = y++;
    constraints.gridx = 0;
    constraints.gridwidth = 2;
    constraints.fill = GridBagConstraints.BOTH;
    descriptionFormField = new FormField("Resumo/Descrição", 504);
    descriptionFormField.setHeight(120);
    descriptionFormField.setEditable(false);
    descriptionFormField.isMultiline(true);
    formContainer.add(descriptionFormField, constraints);

    constraints.gridwidth = 1;
    constraints.weightx = 0.5;
    constraints.gridy = y;
    constraints.insets = new Insets(0, 0, 16, 12);
    categoryFormField = new FormField("Categoria/Tópico", 0);
    categoryFormField.setEditable(false);
    formContainer.add(categoryFormField, constraints);

    constraints.insets = new Insets(0, 12, 16, 0);
    constraints.gridwidth = 1;
    constraints.gridx = 1;
    constraints.gridy = y++;
    urlFormField = new FormField("URL/Link", 0);
    urlFormField.setEditable(false);
    formContainer.add(urlFormField, constraints);

    titleFormField.setText(paper.getTitle());
    authorsFormField.setText(paper.getAuthors());
    publicationDatePicker.setDate(paper.getPublicationDate());
    keywordsFormField.setText(paper.getKeywords());
    descriptionFormField.setText(paper.getDescription());
    categoryFormField.setText(paper.getCategory());
    urlFormField.setText(paper.getUrl());

    return formContainer;
  }

  /**
   * Cria e retorna um JPanel que exibe um relatório das informações de uma
   * atividade de coordenação.
   *
   * @param activity O objeto que representa a atividade cujas informações serão
   *                 exibidas.
   * @return Um JPanel configurado para exibir as informações da atividade de
   *         coordenação.
   */
  private JPanel getActivityReport(CoordinationActivity activity) {

    FormField activityTitleFormField;
    FormField nameOfPersonResponsibleFormField;
    DatePicker startDatePicker;
    DatePicker endDatePicker;
    FormField priorityFormField;
    FormField statusFormField;
    FormField descriptionFormField;

    JPanel formContainer = new JPanel(new GridBagLayout());
    ComponentDecorator.addPadding(formContainer, 0, 24);
    formContainer.setOpaque(false);
    int y = 0;

    GridBagConstraints constraints = new GridBagConstraints();

    constraints.fill = GridBagConstraints.HORIZONTAL;
    constraints.anchor = GridBagConstraints.PAGE_START;
    constraints.gridwidth = 2;
    constraints.gridx = 0;
    constraints.gridy = y++;
    constraints.weightx = 1.0;

    formContainer.add(getSectionLabel("Informações Gerais"), constraints);

    constraints.insets = new Insets(0, 0, 16, 0);
    constraints.gridwidth = 2;
    constraints.gridx = 0;
    constraints.gridy = y++;
    activityTitleFormField = new FormField("Título da Atividade", 0);
    activityTitleFormField.setEditable(false);
    formContainer.add(activityTitleFormField, constraints);

    constraints.insets = new Insets(0, 0, 16, 0);
    constraints.gridwidth = 2;
    constraints.gridx = 0;
    constraints.gridy = y++;
    nameOfPersonResponsibleFormField = new FormField("Nome Completo do Rensponsável", 0);
    nameOfPersonResponsibleFormField.setEditable(false);

    formContainer.add(nameOfPersonResponsibleFormField, constraints);

    constraints.gridwidth = 1;
    constraints.weightx = 0.5;
    constraints.gridy = y;
    constraints.insets = new Insets(0, 0, 16, 12);
    startDatePicker = new DatePicker("Data de Início", 0);
    startDatePicker.setEditable(false);
    formContainer.add(startDatePicker, constraints);

    constraints.insets = new Insets(0, 12, 16, 0);
    constraints.gridwidth = 1;
    constraints.gridx = 1;
    constraints.gridy = y++;
    endDatePicker = new DatePicker("Data de Fim", 0);
    endDatePicker.setEditable(false);
    formContainer.add(endDatePicker, constraints);

    constraints.gridwidth = 1;
    constraints.weightx = 0.5;
    constraints.gridy = y;
    constraints.gridx = 0;
    constraints.insets = new Insets(0, 0, 16, 12);
    priorityFormField = new FormField("Prioridade", 0);
    priorityFormField.setEditable(false);
    formContainer.add(priorityFormField, constraints);

    constraints.insets = new Insets(0, 12, 16, 0);
    constraints.gridwidth = 1;
    constraints.gridx = 1;
    constraints.gridy = y++;
    statusFormField = new FormField("Status", 0);
    statusFormField.setEditable(false);
    formContainer.add(statusFormField, constraints);

    constraints.insets = new Insets(0, 0, 16, 0);
    constraints.gridy = y++;
    constraints.gridx = 0;
    constraints.gridwidth = 2;
    constraints.fill = GridBagConstraints.BOTH;
    descriptionFormField = new FormField("Descrição", 504);
    descriptionFormField.setHeight(120);
    descriptionFormField.setEditable(false);
    descriptionFormField.isMultiline(true);
    formContainer.add(descriptionFormField, constraints);

    activityTitleFormField.setText(activity.getActivityTitle());
    nameOfPersonResponsibleFormField.setText(activity.getNameOfPersonResponsible());
    priorityFormField.setText(activity.getPriority());
    statusFormField.setText(activity.getStatus());
    descriptionFormField.setText(activity.getDescription());
    startDatePicker.setDate(activity.getStartDate());
    endDatePicker.setDate(activity.getEndDate());

    return formContainer;
  }
}
