package app.frontend.screens.teachers.forms;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import app.backend.entities.Teacher;
import app.backend.services.TeacherService;
import app.frontend.components.Button;
import app.frontend.components.Button.ButtonType;
import app.frontend.components.DatePicker;
import app.frontend.components.FormField;
import app.frontend.models.TeacherTableModel;
import res.fonts.FontsManager;
import res.fonts.FontsManager.FontType;
import res.values.ColorsManager;
import res.values.DimensManager;
import utils.ComponentDecorator;

/**
 * Classe que representa o formulário para adicionar, editar ou visualizar
 * informações de um professor.
 */
public class TeacherForm extends JFrame {

  private Container container;
  private FormField nameFormField;
  private DatePicker birthDayDatePicker;
  private FormField indetificationFormField;
  private FormField yearsOfExperienceFormField;
  private FormField emailFormField;
  private FormField phoneNumberFormField;
  private FormField trainingAreaFormField;
  private boolean isEditable = true;
  private TeacherTableModel teacherTableModel;

  Teacher teacher;

  /**
   * Enumeração que representa as ações possíveis no formulário do professor:
   * edição, adição ou visualização.
   */
  public enum ActionType {
    EDIT_TEACHER, ADD_TEACHER, INFO_TEACHER
  }

  ActionType actionType;

  /**
   * Construtor da classe TeacherForm.
   *
   * @param teacherTableModel O modelo de tabela de professores.
   * @param actionType        A ação a ser executada no formulário (editar,
   *                          adicionar ou visualizar).
   */

  public TeacherForm(TeacherTableModel teacherTableModel, ActionType actionType) {
    super("");

    String title;
    this.actionType = actionType;
    this.teacherTableModel = teacherTableModel;

    if (actionType == ActionType.EDIT_TEACHER)
      title = "Editar Informações";
    else if (actionType == ActionType.ADD_TEACHER)
      title = "Cadastrar Professor";
    else {
      title = "Dados do Professor";
      isEditable = false;
    }

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
    container.add(getFormContainer(), BorderLayout.CENTER);
    container.add(getButtonsContainer(), BorderLayout.PAGE_END);

    setVisible(true);
  }

  /**
   * Construtor da classe TeacherForm para edição ou visualização de um professor
   * existente.
   *
   * @param teacherTableModel O modelo de tabela de professores.
   * @param actionType        A ação a ser executada no formulário (editar ou
   *                          visualizar).
   * @param teacher           O professor a ser editado ou visualizado.
   */
  public TeacherForm(TeacherTableModel teacherTableModel, ActionType actionType, Teacher teacher) {
    this(teacherTableModel, actionType);
    this.teacher = teacher;
    birthDayDatePicker.setDate(teacher.getBirthDay());
    emailFormField.setText(teacher.getEmail());
    indetificationFormField.setText(teacher.getIndentificatorNumber());
    nameFormField.setText(teacher.getName());
    phoneNumberFormField.setText(teacher.getPhone());
    trainingAreaFormField.setText(teacher.getTrainingArea());
    yearsOfExperienceFormField.setText(teacher.getYearsOfExperience());
  }

  /**
   * Retorna um painel de rolagem contendo o formulário para adicionar, editar ou
   * visualizar informações de um professor.
   *
   * @return Um componente JScrollPane que contém o formulário.
   */
  private JScrollPane getFormContainer() {
    JPanel formContainer = new JPanel(new GridBagLayout());
    ComponentDecorator.addPadding(formContainer, 0, 24);
    formContainer.setOpaque(false);
    int y = 0;

    GridBagConstraints constraints = new GridBagConstraints();

    // Seção: Informações Pessoais
    constraints.fill = GridBagConstraints.HORIZONTAL;
    constraints.anchor = GridBagConstraints.PAGE_START;
    constraints.gridwidth = 2;
    constraints.gridx = 0;
    constraints.gridy = y++;

    formContainer.add(getSectionLabel("Informações Pessoais"), constraints);

    constraints.insets = new Insets(0, 0, 16, 0);
    constraints.gridy = y++;
    nameFormField = new FormField("Nome Completo", 504);
    nameFormField.setEditable(isEditable);
    formContainer.add(nameFormField, constraints);

    constraints.gridwidth = 1;
    constraints.weightx = 0.5;
    constraints.gridy = y;
    constraints.insets = new Insets(0, 0, 16, 12);
    birthDayDatePicker = new DatePicker("Data de Nascimento", 0);
    birthDayDatePicker.setEditable(isEditable);
    formContainer.add(birthDayDatePicker, constraints);

    constraints.insets = new Insets(0, 12, 16, 0);
    constraints.gridwidth = 1;
    constraints.gridx = 1;
    constraints.gridy = y++;
    indetificationFormField = new FormField("Identificação", 0);
    indetificationFormField.setEditable(isEditable);
    formContainer.add(indetificationFormField, constraints);

    // Seção: Informações de Contato
    constraints.gridwidth = 2;
    constraints.gridx = 0;
    constraints.gridy = y++;
    constraints.insets = new Insets(0, 0, 0, 0);

    formContainer.add(getSectionLabel("Informações de Contato"), constraints);

    constraints.insets = new Insets(0, 0, 16, 0);
    constraints.gridy = y++;
    emailFormField = new FormField("E-mail", 504);
    emailFormField.setEditable(isEditable);
    formContainer.add(emailFormField, constraints);

    constraints.insets = new Insets(0, 0, 16, 0);
    constraints.gridy = y++;
    phoneNumberFormField = new FormField("Número de telefone", 504);
    phoneNumberFormField.setEditable(isEditable);
    formContainer.add(phoneNumberFormField, constraints);

    // Seção Informações Profissionais
    constraints.insets = new Insets(0, 0, 0, 0);
    constraints.gridy = y++;
    formContainer.add(getSectionLabel("Informações Profissional"), constraints);

    constraints.gridwidth = 1;
    constraints.weightx = 0.5;
    constraints.gridy = y;
    constraints.insets = new Insets(0, 0, 0, 12);
    trainingAreaFormField = new FormField("Área de Formação", 0);
    trainingAreaFormField.setEditable(isEditable);
    formContainer.add(trainingAreaFormField, constraints);

    constraints.insets = new Insets(0, 12, 0, 0);
    constraints.gridwidth = 1;
    constraints.gridx = 1;
    constraints.gridy = y++;
    yearsOfExperienceFormField = new FormField("Anos de Experiência", 0);
    yearsOfExperienceFormField.setEditable(isEditable);
    formContainer.add(yearsOfExperienceFormField, constraints);

    GridBagConstraints fillerConstraints = new GridBagConstraints();
    fillerConstraints.weighty = 1.0;
    fillerConstraints.gridx = 0;
    fillerConstraints.gridy = y++;
    fillerConstraints.gridwidth = 2;

    JPanel filler = new JPanel();
    filler.setOpaque(false);

    formContainer.add(filler, fillerConstraints);

    JScrollPane scrollPane = new JScrollPane(formContainer);
    scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    scrollPane.getViewport().setBackground(ColorsManager.getOnBackgroundColor());
    scrollPane.setBorder(null);

    return scrollPane;
  }

  /**
   * Cria e retorna um rótulo de seção com o texto especificado.
   *
   * @param text O texto a ser exibido no rótulo de seção.
   * @return Um componente JLabel que representa um rótulo de seção formatado.
   */
  private JLabel getSectionLabel(String text) {
    JLabel label = new JLabel(text, JLabel.LEFT);
    label.setFont(FontsManager.getFont(FontType.SEMI_BOLD, DimensManager.getSectionLabelFontsize()));
    label.setOpaque(false);
    ComponentDecorator.addPaddingBottom(label, 12);
    return label;
  }

  /**
   * Cria e retorna um contêiner de botões que contém os botões "CANCELAR" e
   * "CONFIRMAR".
   *
   * @return Um contêiner JPanel que contém os botões de cancelar e confirmar.
   */
  private JPanel getButtonsContainer() {
    JPanel buttonsContainer = new JPanel();
    buttonsContainer.setLayout(new FlowLayout(FlowLayout.RIGHT, 24, 0));
    buttonsContainer.setOpaque(false);

    if (isEditable) {
      JPanel cancelButtonContainer = new JPanel() {
        @Override
        protected void paintComponent(Graphics g) {
          super.paintComponent(g);
          Graphics2D g2d = (Graphics2D) g;

          int width = getWidth();
          int height = getHeight();

          setBackground(ColorsManager.getOnBackgroundColor());

          RoundRectangle2D roundedRectangle = new RoundRectangle2D.Float(0, 0, width, height, 24, 24);

          g2d.setColor(ColorsManager.getButtonBackgroundSecondary());
          g2d.fill(roundedRectangle);
        }
      };

      Button cancelButton = new Button(ButtonType.BASIC, "CANCELAR");
      cancelButton.setForeground(ColorsManager.getTextColorDark());
      cancelButton.setBorder(null);
      ComponentDecorator.addPadding(cancelButton, 6, 16);

      cancelButtonContainer.add(cancelButton);
      cancelButtonContainer.setBorder(null);

      cancelButton.addActionListener(event -> dispose());
      buttonsContainer.add(cancelButtonContainer);
    }

    JPanel confirmButtonContainer = new JPanel() {
      @Override
      protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        int width = getWidth();
        int height = getHeight();

        setBackground(ColorsManager.getOnBackgroundColor());

        RoundRectangle2D roundedRectangle = new RoundRectangle2D.Float(0, 0, width, height, 24, 24);

        g2d.setColor(ColorsManager.getButtonBackgroundPrimary());
        g2d.fill(roundedRectangle);
      }
    };

    Button confirmButton = new Button(ButtonType.BASIC, "CONFIRMAR");
    confirmButton.setForeground(ColorsManager.getTextColorLight());
    confirmButton.setBorder(null);
    ComponentDecorator.addPadding(confirmButton, 6, 16);

    confirmButtonContainer.add(confirmButton);
    confirmButtonContainer.setBorder(null);

    confirmButton.addActionListener(event -> confirm());

    buttonsContainer.add(confirmButtonContainer);

    ComponentDecorator.addPadding(buttonsContainer, 24, 0);

    return buttonsContainer;
  }

  /**
   * Realiza a ação de confirmação, que pode ser a adição ou edição de um
   * professor.
   * 
   * Se o formulário for editável e a ação for "ADICIONAR PROFESSOR", cria um novo
   * objeto Teacher
   * com os dados fornecidos no formulário, chama o serviço para adicionar o
   * professor e atualiza a tabela
   * de professores.
   * 
   * Se o formulário for editável e a ação for "EDITAR PROFESSOR", atualiza os
   * dados do professor
   * existente com os dados fornecidos no formulário e chama o serviço para
   * atualizar o professor.
   * 
   * Fecha a janela de formulário após a conclusão da ação.
   */
  private void confirm() {

    if (isEditable) {

      if (actionType == ActionType.ADD_TEACHER)
        teacher = new Teacher();

      teacher.setBirthDay(birthDayDatePicker.getDate());
      teacher.setEmail(emailFormField.getText());
      teacher.setIndentificatorNumber(indetificationFormField.getText());
      teacher.setName(nameFormField.getText());
      teacher.setPhone(phoneNumberFormField.getText());
      teacher.setTrainingArea(trainingAreaFormField.getText());
      teacher.setYearsOfExperience(Integer.parseInt(yearsOfExperienceFormField.getText()));

      if (actionType == ActionType.ADD_TEACHER)
        TeacherService.postTeacher(teacher);
      else if (actionType == ActionType.EDIT_TEACHER)
        TeacherService.updateTeacherById(teacher.getId(), teacher);

      teacherTableModel.setTeachersList(TeacherService.getTeachers());
    }

    dispose();
  }
}
