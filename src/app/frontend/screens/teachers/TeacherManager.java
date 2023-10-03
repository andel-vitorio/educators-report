package app.frontend.screens.teachers;

import javax.swing.JPanel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;

import java.util.ArrayList;

import app.backend.entities.CoordinationActivity;
import app.backend.entities.Paper;
import app.backend.entities.PosgraduateStudent;
import app.backend.entities.Student;
import app.backend.entities.Teacher;
import app.backend.entities.UndergraduateStudent;
import app.frontend.components.ActionsButtons;
import app.frontend.components.ComboBox;
import app.frontend.components.Table;
import app.frontend.components.TopBar;
import app.frontend.components.Button.ButtonInfo;
import app.frontend.components.Table.CellEditor;
import app.frontend.components.Table.CellRenderer;
import app.frontend.models.CoordinationActivityTableModel;
import app.frontend.models.PapersTableModel;
import app.frontend.models.StudentsTableModel;
import app.frontend.models.SubjectTableModel;
import app.frontend.models.TeacherTableModel;
import app.frontend.screens.activities.form.CoordinationActivityForm;
import app.frontend.screens.activities.form.CoordinationActivityForm.CoordinationActivityActionType;
import app.frontend.screens.papers.forms.PaperForm;
import app.frontend.screens.papers.forms.PaperForm.PaperActionType;
import app.frontend.screens.students.forms.StudentForm;
import app.frontend.screens.students.forms.StudentForm.StudentActionType;
import app.frontend.screens.subjects.forms.SubjectForm;
import app.frontend.screens.teachers.forms.TeacherForm;
import app.frontend.screens.teachers.forms.TeacherForm.ActionType;
import app.frontend.screens.subjects.forms.SubjectForm.SubjectActionType;
import res.img.ImagesManager;
import res.values.ColorsManager;
import utils.ComponentDecorator;

import java.time.*;

public class TeacherManager extends JPanel {

	private static final String TITLE_WINDOW = "Gerenciamento das Atividades";

	private TopBar topBar;
	private Table table;
	private TeacherTableModel teacherTableModel;
	private SubjectTableModel subjectTableModel;
	private StudentsTableModel studentsTableModel;
	private PapersTableModel papersTableModel;
	private CoordinationActivityTableModel coordinationActivityTableModel;
	private int lastSelectedRow = 0;
	private ComboBox comboBox;
	private JPanel contentContainer;

	public TeacherManager() {
		this.setLayout(new BorderLayout(0, 0));

		topBar = new TopBar(TITLE_WINDOW);
		topBar.setBackground(ColorsManager.getOnBackgroundColor());
		ComponentDecorator.addBorderBottom(topBar, 1);

		topBar.setActionButton("Cadastrar", ImagesManager.getAddIcon(), new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedOption = comboBox.getSelectedIndex();

				switch (selectedOption) {
					case 0: {
						new SubjectForm(subjectTableModel, SubjectActionType.ADD_SUBJECT);
						break;
					}

					case 1: {
						new StudentForm(studentsTableModel, StudentActionType.ADD_STUDENT);
						break;
					}

					case 2: {
						new PaperForm(papersTableModel, PaperActionType.ADD_PAPER);
						break;
					}

					case 3: {
						new CoordinationActivityForm(coordinationActivityTableModel,
								CoordinationActivityActionType.ADD_ACTIVITY);
						break;
					}

					default:
						break;
				}
			}
		});

		this.add(topBar, BorderLayout.PAGE_START);

		contentContainer = new JPanel(new BorderLayout());

		comboBox = new ComboBox(
				new String[] { "Disciplinas", "Alunos Orientados", "Artigos", "Atividades de Coordenação" });
		comboBox.setPreferredSize(new Dimension(100, 36));

		comboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedOption = comboBox.getSelectedIndex();

				switch (selectedOption) {
					case 0: {
						contentContainer.removeAll();
						showSubjectsTable();
						break;
					}

					case 1: {
						contentContainer.removeAll();
						showStudents();
						break;
					}

					case 2: {
						contentContainer.removeAll();
						showPapers();
						break;
					}

					case 3: {
						contentContainer.removeAll();
						showActivity();
						break;
					}

					default:
						break;
				}

			}
		});

		showSubjectsTable();
		this.add(contentContainer);
	}

	private void showSubjectsTable() {

		JPanel tableContainer = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				Graphics2D g2d = (Graphics2D) g;

				int width = getWidth();
				int height = getHeight();

				// Criar um retângulo com cantos arredondados
				RoundRectangle2D roundedRectangle = new RoundRectangle2D.Float(0, 0, width - 1, height - 1, 24, 24);

				g2d.setColor(ColorsManager.getOnBackgroundColor());
				g2d.fill(roundedRectangle);

				// Desenhar a borda do painel
				g2d.setColor(ColorsManager.getOnBackgroundColor()); // Cor da borda personalizável
				g2d.draw(roundedRectangle);
			}
		};

		ArrayList<ButtonInfo> buttonInfos = new ArrayList<>();
		buttonInfos.add(new ButtonInfo("", ImagesManager.getInfoIcon(), e -> {
			showTeacherInfo();
		}));
		buttonInfos.add(new ButtonInfo("", ImagesManager.getEditIcon(), e -> {
			editTeacher();
		}));
		buttonInfos.add(new ButtonInfo("", ImagesManager.getDeleteIcon(), e -> {
			deleteTeacher();
		}));

		teacherTableModel = new TeacherTableModel();
		table = new Table(teacherTableModel);

		ActionsButtons actionsButtons = new ActionsButtons(buttonInfos);
		CellEditor actionsButtonCellEditor = new CellEditor(actionsButtons);
		CellRenderer actionsButtonCellRenderer = new CellRenderer(actionsButtons);

		table.setCustomColumn(TeacherTableModel.ACTIONS_BUTTON_COLUMN_INDEX, actionsButtonCellEditor,
				actionsButtonCellRenderer);
		table.setColumnWidth(0, 200);
		table.setColumnWidth(2, 200);
		table.setBackground(ColorsManager.getOnBackgroundColor());

		tableContainer.setLayout(new BorderLayout());
		tableContainer.add(comboBox, BorderLayout.PAGE_START);
		tableContainer.add(table, BorderLayout.CENTER);

		tableContainer.setBorder(null);
		contentContainer.add(tableContainer, BorderLayout.CENTER);

		ComponentDecorator.addPadding(table, 16, 0, 0, 0);
		ComponentDecorator.addPadding(tableContainer, 12);
		ComponentDecorator.addPadding(contentContainer, 24);

		Teacher teacher = new Teacher();

		teacher.setBirthDay(LocalDate.of(2023, 12, 31));
		teacher.setEmail("email@asdas");
		teacher.setIndenticatorNumber("2123");
		teacher.setName("Andel");
		teacher.setPhone("12312313");
		teacher.setTrainingArea("TEste");
		teacher.setYearsOfExperience(123);

		ArrayList<Teacher> teachers = new ArrayList<>();

		teachers.add(teacher);

		teacherTableModel.setTeachersList(teachers);
		teacherTableModel.updateTable();
	}

	private void editTeacher() {
		int selectedRow = table.getComponent().getSelectedRow();

		if (selectedRow == -1)
			selectedRow = lastSelectedRow;
		else
			lastSelectedRow = selectedRow;

		Teacher teacher = teacherTableModel.getTeachersAt(selectedRow);

		new TeacherForm(teacherTableModel, ActionType.EDIT_TEACHER, teacher);
	}

	private void showTeacherInfo() {
		int selectedRow = table.getComponent().getSelectedRow();

		if (selectedRow == -1)
			selectedRow = lastSelectedRow;
		else
			lastSelectedRow = selectedRow;

		Teacher teacher = teacherTableModel.getTeachersAt(selectedRow);

		new TeacherForm(teacherTableModel, ActionType.INFO_TEACHER, teacher);
	}

	private void deleteTeacher() {
		System.out.println("Delete Teacher");
	}

	private void showStudents() {

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
		tableContainer.add(comboBox, BorderLayout.PAGE_START);
		tableContainer.add(table, BorderLayout.CENTER);

		contentContainer.add(tableContainer, BorderLayout.CENTER);
		tableContainer.setBorder(null);

		ComponentDecorator.addPadding(table, 0);
		ComponentDecorator.addPadding(tableContainer, 12);
		ComponentDecorator.addPadding(contentContainer, 24);

		UndergraduateStudent undergraduateStudent = new UndergraduateStudent();

		undergraduateStudent.setName("João");
		undergraduateStudent.setDateOfEntry(LocalDate.of(2022, 9, 1));
		undergraduateStudent.setRegistration("123456");
		undergraduateStudent.setEmail("joao@example.com");
		undergraduateStudent.setPhoneNumber("+55 123 456 789");
		undergraduateStudent.setNameOfMentee("Maria");
		undergraduateStudent.setStatus("Ativo");
		undergraduateStudent.setProjectName("Projeto de Pesquisa XYZ");
		undergraduateStudent.setTypeOfOrientation("Projeto Final de Curso");

		PosgraduateStudent posgraduateStudent = new PosgraduateStudent();

		posgraduateStudent.setName("Fernanda");
		posgraduateStudent.setDateOfEntry(LocalDate.of(2020, 2, 15));
		posgraduateStudent.setRegistration("789012");
		posgraduateStudent.setEmail("fernanda@example.com");
		posgraduateStudent.setPhoneNumber("+55 987 654 321");
		posgraduateStudent.setStatus("Ativo");
		posgraduateStudent.setNameOfMentee("Maria");
		posgraduateStudent.setPosgraduateProgram("Mestrado em Ciência da Computação");
		posgraduateStudent.setResearchTitle("Algoritmos Quânticos Avançados");
		posgraduateStudent.setDefenseDate(LocalDate.of(2022, 11, 30));

		studentsTableModel.setStudent(undergraduateStudent);
		studentsTableModel.setStudent(posgraduateStudent);
		studentsTableModel.updateTable();
	}

	private void editSubject() {
		int selectedRow = table.getComponent().getSelectedRow();

		if (selectedRow == -1)
			selectedRow = lastSelectedRow;
		else
			lastSelectedRow = selectedRow;

		Student student = studentsTableModel.getStudentAt(selectedRow);
		new StudentForm(studentsTableModel, StudentActionType.EDIT_STUDENT, student);
	}

	private void showSubjectInfo() {
		int selectedRow = table.getComponent().getSelectedRow();

		if (selectedRow == -1)
			selectedRow = lastSelectedRow;
		else
			lastSelectedRow = selectedRow;

		Student student = studentsTableModel.getStudentAt(selectedRow);
		new StudentForm(studentsTableModel, StudentActionType.INFO_STUDENT, student);

	}

	private void deleteSubject() {
		System.out.println("Delete Student");
	}

	public void showPapers() {
		JPanel tableContainer = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				Graphics2D g2d = (Graphics2D) g;

				int width = getWidth();
				int height = getHeight();

				// Criar um retângulo com cantos arredondados
				RoundRectangle2D roundedRectangle = new RoundRectangle2D.Float(0, 0, width - 1, height - 1, 24, 24);

				g2d.setColor(ColorsManager.getOnBackgroundColor());
				g2d.fill(roundedRectangle);

				// Desenhar a borda do painel
				g2d.setColor(ColorsManager.getOnBackgroundColor()); // Cor da borda personalizável
				g2d.draw(roundedRectangle);
			}
		};

		papersTableModel = new PapersTableModel();
		table = new Table(papersTableModel);

		ArrayList<ButtonInfo> buttonInfos = new ArrayList<>();
		buttonInfos.add(new ButtonInfo("", ImagesManager.getInfoIcon(), e -> {
			showPaperInfo();
		}));
		buttonInfos.add(new ButtonInfo("", ImagesManager.getEditIcon(), e -> {
			editPaper();
		}));
		buttonInfos.add(new ButtonInfo("", ImagesManager.getDeleteIcon(), e -> {
			deletePaper();
		}));

		ActionsButtons actionsButtons = new ActionsButtons(buttonInfos);
		CellEditor actionsButtonCellEditor = new CellEditor(actionsButtons);
		CellRenderer actionsButtonCellRenderer = new CellRenderer(actionsButtons);

		table.setCustomColumn(PapersTableModel.ACTIONS_BUTTON_COLUMN_INDEX, actionsButtonCellEditor,
				actionsButtonCellRenderer);
		table.setColumnWidth(PapersTableModel.ACTIONS_BUTTON_COLUMN_INDEX, 120);

		tableContainer.setLayout(new BorderLayout());
		tableContainer.add(comboBox, BorderLayout.PAGE_START);
		tableContainer.add(table, BorderLayout.CENTER);

		contentContainer.add(tableContainer, BorderLayout.CENTER);
		tableContainer.setBorder(null);

		ComponentDecorator.addPadding(table, 0);
		ComponentDecorator.addPadding(tableContainer, 12);
		ComponentDecorator.addPadding(contentContainer, 24);

		Paper paper = new Paper();
		paper.setTitle("Título do Artigo");
		paper.setAuthors("Autor 1, Autor 2");
		paper.setPublicationDate(LocalDate.of(2023, 9, 30));
		paper.setKeywords("palavra-chave1, palavra-chave2");
		paper.setDescription("Descrição do artigo.");
		paper.setCategory("Categoria do artigo");
		paper.setUrl("https://exemplo.com/artigo");

		papersTableModel.setPaper(paper);
		papersTableModel.updateTable();
	}

	private void editPaper() {
		int selectedRow = table.getComponent().getSelectedRow();

		if (selectedRow == -1)
			selectedRow = lastSelectedRow;
		else
			lastSelectedRow = selectedRow;

		Paper paper = papersTableModel.getPaperAt(selectedRow);
		new PaperForm(papersTableModel, PaperActionType.EDIT_PAPER, paper);
	}

	private void showPaperInfo() {
		int selectedRow = table.getComponent().getSelectedRow();

		if (selectedRow == -1)
			selectedRow = lastSelectedRow;
		else
			lastSelectedRow = selectedRow;

		Paper paper = papersTableModel.getPaperAt(selectedRow);
		new PaperForm(papersTableModel, PaperActionType.INFO_PAPER, paper);
	}

	private void deletePaper() {
		System.out.println("Delete Subject");
	}

	private void showActivity() {
		JPanel tableContainer = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				Graphics2D g2d = (Graphics2D) g;

				int width = getWidth();
				int height = getHeight();

				// Criar um retângulo com cantos arredondados
				RoundRectangle2D roundedRectangle = new RoundRectangle2D.Float(0, 0, width - 1, height - 1, 24, 24);

				g2d.setColor(ColorsManager.getOnBackgroundColor());
				g2d.fill(roundedRectangle);

				// Desenhar a borda do painel
				g2d.setColor(ColorsManager.getOnBackgroundColor()); // Cor da borda personalizável
				g2d.draw(roundedRectangle);
			}
		};

		coordinationActivityTableModel = new CoordinationActivityTableModel();
		table = new Table(coordinationActivityTableModel);

		ArrayList<ButtonInfo> buttonInfos = new ArrayList<>();
		buttonInfos.add(new ButtonInfo("", ImagesManager.getInfoIcon(), e -> {
			showCoordinationActivityInfo();
		}));
		buttonInfos.add(new ButtonInfo("", ImagesManager.getEditIcon(), e -> {
			editCoordinationActivity();
		}));
		buttonInfos.add(new ButtonInfo("", ImagesManager.getDeleteIcon(), e -> {
			deleteCoordinationActivity();
		}));

		ActionsButtons actionsButtons = new ActionsButtons(buttonInfos);
		CellEditor actionsButtonCellEditor = new CellEditor(actionsButtons);
		CellRenderer actionsButtonCellRenderer = new CellRenderer(actionsButtons);

		table.setCustomColumn(CoordinationActivityTableModel.ACTIONS_BUTTON_COLUMN_INDEX, actionsButtonCellEditor,
				actionsButtonCellRenderer);
		table.setColumnWidth(CoordinationActivityTableModel.ACTIONS_BUTTON_COLUMN_INDEX, 120);

		tableContainer.setLayout(new BorderLayout());
		tableContainer.add(comboBox, BorderLayout.PAGE_START);
		tableContainer.add(table, BorderLayout.CENTER);

		contentContainer.add(tableContainer, BorderLayout.CENTER);
		tableContainer.setBorder(null);

		ComponentDecorator.addPadding(table, 0);
		ComponentDecorator.addPadding(tableContainer, 12);
		ComponentDecorator.addPadding(contentContainer, 24);

		this.add(contentContainer);

		CoordinationActivity activity = new CoordinationActivity();

		activity.setActivityTitle("Reunião de Planejamento");
		activity.setNameOfPersonResponsible("João Silva");
		activity.setStartDate(LocalDate.of(2023, 10, 1));
		activity.setEndDate(LocalDate.of(2023, 10, 5));
		activity.setPriiority("Alta");
		activity.setStatus("Em Andamento");
		activity.setDescription("Reunião para planejar as atividades do próximo trimestre.");

		coordinationActivityTableModel.setCoordinationActivity(activity);
		coordinationActivityTableModel.updateTable();
	}

	private void editCoordinationActivity() {
		int selectedRow = table.getComponent().getSelectedRow();

		if (selectedRow == -1)
			selectedRow = lastSelectedRow;
		else
			lastSelectedRow = selectedRow;

		CoordinationActivity activity = coordinationActivityTableModel.getCoordinationActivityAt(selectedRow);
		new CoordinationActivityForm(coordinationActivityTableModel,
				CoordinationActivityActionType.EDIT_ACTIVITY, activity);
	}

	private void showCoordinationActivityInfo() {
		int selectedRow = table.getComponent().getSelectedRow();

		if (selectedRow == -1)
			selectedRow = lastSelectedRow;
		else
			lastSelectedRow = selectedRow;

		CoordinationActivity activity = coordinationActivityTableModel.getCoordinationActivityAt(selectedRow);
		new CoordinationActivityForm(coordinationActivityTableModel,
				CoordinationActivityActionType.EDIT_ACTIVITY, activity);
	}

	private void deleteCoordinationActivity() {
		System.out.println("Delete Activity");
	}
}