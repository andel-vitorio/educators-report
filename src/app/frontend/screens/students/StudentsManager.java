package app.frontend.screens.students;

import javax.swing.JPanel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;

import java.util.ArrayList;

import app.backend.entities.PosgraduateStudent;
import app.backend.entities.Student;
import app.backend.entities.UndergraduateStudent;
import app.backend.services.PosgraduateStudentService;
import app.backend.services.UndergraduateStudentService;
import app.frontend.components.ActionsButtons;
import app.frontend.components.Table;
import app.frontend.components.TopBar;
import app.frontend.components.Button.ButtonInfo;
import app.frontend.components.Table.CellEditor;
import app.frontend.components.Table.CellRenderer;
import app.frontend.models.StudentsTableModel;
import app.frontend.screens.students.forms.StudentForm;
import app.frontend.screens.students.forms.StudentForm.StudentActionType;
import res.img.ImagesManager;
import res.values.ColorsManager;
import utils.ComponentDecorator;

/**
 * Esta classe representa a tela de gerenciamento de alunos orientados.
 */
public class StudentsManager extends JPanel {

  private static final String TITLE_WINDOW = "Gerenciamento de Alunos Orientados";

  private TopBar topBar;
  private Table table;
  private StudentsTableModel studentsTableModel;
  private int lastSelectedRow = 0;

  /**
   * Cria uma nova instância de StudentsManager.
   */
  public StudentsManager() {
    this.setLayout(new BorderLayout(0, 0));

    topBar = new TopBar(TITLE_WINDOW);
    topBar.setBackground(ColorsManager.getOnBackgroundColor());
    ComponentDecorator.addBorderBottom(topBar, 1);

    topBar.setActionButton("Cadastrar Aluno", ImagesManager.getAddIcon(), new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        new StudentForm(studentsTableModel, StudentActionType.ADD_STUDENT);
      }
    });

    this.add(topBar, BorderLayout.PAGE_START);

    JPanel contentContainer = new JPanel(new BorderLayout());

    JPanel tableContainer = new JPanel() {
      @Override
      protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        int width = getWidth();
        int height = getHeight();

        RoundRectangle2D roundedRectangle = new RoundRectangle2D.Float(0, 0, width - 1, height - 1, 24, 24);

        g2d.setColor(ColorsManager.getOnBackgroundColor());
        g2d.fill(roundedRectangle);

        g2d.setColor(ColorsManager.getOnBackgroundColor());
        g2d.draw(roundedRectangle);
      }
    };

    studentsTableModel = new StudentsTableModel();
    table = new Table(studentsTableModel);

    ArrayList<ButtonInfo> buttonInfos = new ArrayList<>();
    buttonInfos.add(new ButtonInfo("", ImagesManager.getInfoIcon(), e -> {
      showSubjectInfo();
    }));
    buttonInfos.add(new ButtonInfo("", ImagesManager.getEditIcon(), e -> {
      editSubject();
    }));
    buttonInfos.add(new ButtonInfo("", ImagesManager.getDeleteIcon(), e -> {
      deleteSubject();
    }));

    ActionsButtons actionsButtons = new ActionsButtons(buttonInfos);
    CellEditor actionsButtonCellEditor = new CellEditor(actionsButtons);
    CellRenderer actionsButtonCellRenderer = new CellRenderer(actionsButtons);

    table.setCustomColumn(StudentsTableModel.ACTIONS_BUTTON_COLUMN_INDEX, actionsButtonCellEditor,
        actionsButtonCellRenderer);
    table.setColumnWidth(StudentsTableModel.ACTIONS_BUTTON_COLUMN_INDEX, 120);

    tableContainer.setLayout(new BorderLayout());
    tableContainer.add(table, BorderLayout.CENTER);

    contentContainer.add(tableContainer, BorderLayout.CENTER);
    tableContainer.setBorder(null);

    ComponentDecorator.addPadding(table, 0);
    ComponentDecorator.addPadding(tableContainer, 12);
    ComponentDecorator.addPadding(contentContainer, 24);

    this.add(contentContainer);

    ArrayList<UndergraduateStudent> undergraduateStudents = UndergraduateStudentService.getUndergraduateStudents();
    ArrayList<PosgraduateStudent> posgraduateStudents = PosgraduateStudentService.getPosgraduateStudents();
    ArrayList<Student> students = new ArrayList<>();

    if (undergraduateStudents != null) {
      students.addAll(undergraduateStudents);
    }

    if (posgraduateStudents != null) {
      students.addAll(posgraduateStudents);
    }

    studentsTableModel.setStudentList(students);
  }

  /**
   * Edita um aluno selecionado.
   */
  private void editSubject() {
    int selectedRow = table.getComponent().getSelectedRow();

    if (selectedRow == -1)
      selectedRow = lastSelectedRow;
    else
      lastSelectedRow = selectedRow;

    Student student = studentsTableModel.getStudentAt(selectedRow);
    new StudentForm(studentsTableModel, StudentActionType.EDIT_STUDENT, student);
  }

  /**
   * Exibe informações de um aluno selecionado.
   */
  private void showSubjectInfo() {
    int selectedRow = table.getComponent().getSelectedRow();

    if (selectedRow == -1)
      selectedRow = lastSelectedRow;
    else
      lastSelectedRow = selectedRow;

    Student student = studentsTableModel.getStudentAt(selectedRow);
    new StudentForm(studentsTableModel, StudentActionType.INFO_STUDENT, student);

  }

  /**
   * Exclui um aluno selecionado.
   */
  private void deleteSubject() {
    int selectedRow = table.getComponent().getSelectedRow();

    if (selectedRow == -1)
      selectedRow = lastSelectedRow;
    else
      lastSelectedRow = selectedRow;

    Student student = studentsTableModel.getStudentAt(selectedRow);

    if (student instanceof UndergraduateStudent)
      UndergraduateStudentService.deleteUndergraduateStudentById(student.getId());
    else if (student instanceof PosgraduateStudent)
      PosgraduateStudentService.deletePosgraduateStudentById(student.getId());

    ArrayList<UndergraduateStudent> undergraduateStudents = UndergraduateStudentService.getUndergraduateStudents();
    ArrayList<PosgraduateStudent> posgraduateStudents = PosgraduateStudentService.getPosgraduateStudents();
    ArrayList<Student> students = new ArrayList<>();

    if (undergraduateStudents != null) {
      students.addAll(undergraduateStudents);
    }

    if (posgraduateStudents != null) {
      students.addAll(posgraduateStudents);
    }

    studentsTableModel.setStudentList(students);
  }
}
