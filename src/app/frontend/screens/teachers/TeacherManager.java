package app.frontend.screens.teachers;

import javax.swing.JPanel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;

import java.util.ArrayList;

import app.backend.entities.*;
import app.backend.services.*;
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
import app.frontend.screens.report.ReportIndividual;

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
	private Teacher teacher;

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public TeacherManager() {
		this.setLayout(new BorderLayout(0, 0));

		topBar = new TopBar(TITLE_WINDOW);
		topBar.setBackground(ColorsManager.getOnBackgroundColor());
		ComponentDecorator.addBorderBottom(topBar, 1);

		ArrayList<ButtonInfo> buttonInfos = new ArrayList<>();

		buttonInfos.add(new ButtonInfo("Gerar Relatório", ImagesManager.getAddIcon(), new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new ReportIndividual(teacher);
			}
		}));

		buttonInfos.add(new ButtonInfo("Cadastrar", ImagesManager.getAddIcon(), new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedOption = comboBox.getSelectedIndex();

				switch (selectedOption) {
					case 0: {
						new SubjectForm(subjectTableModel, SubjectActionType.ADD_SUBJECT, teacher);
						break;
					}

					case 1: {
						new StudentForm(studentsTableModel, StudentActionType.ADD_STUDENT, teacher);
						break;
					}

					case 2: {
						new PaperForm(papersTableModel, PaperActionType.ADD_PAPER, teacher);
						break;
					}

					case 3: {
						new CoordinationActivityForm(coordinationActivityTableModel,
								CoordinationActivityActionType.ADD_ACTIVITY, teacher);
						break;
					}

					default:
						break;
				}
			}
		}));

		topBar.setActionButtons(buttonInfos);

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
						showSubjectsTable();
						break;
					}

					case 1: {
						contentContainer.removeAll();
						showStudentsTable();
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

	public void showSubjectsTable() {

		contentContainer.removeAll();

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
			showSubjectInfo();
		}));
		buttonInfos.add(new ButtonInfo("", ImagesManager.getEditIcon(), e -> {
			editSubject();
		}));
		buttonInfos.add(new ButtonInfo("", ImagesManager.getDeleteIcon(), e -> {
			deleteSubject();
		}));

		subjectTableModel = new SubjectTableModel();
		table = new Table(subjectTableModel);

		ActionsButtons actionsButtons = new ActionsButtons(buttonInfos);
		CellEditor actionsButtonCellEditor = new CellEditor(actionsButtons);
		CellRenderer actionsButtonCellRenderer = new CellRenderer(actionsButtons);

		table.setCustomColumn(SubjectTableModel.ACTIONS_BUTTON_COLUMN_INDEX, actionsButtonCellEditor,
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

		if (teacher == null)
			return;
		ArrayList<Subjects> subjects = SubjectsService.getSubjectsByTeacherName(teacher.getName());
		if (subjects != null)
			subjectTableModel.setSubjectsList(subjects);
	}

	private void editSubject() {
		int selectedRow = table.getComponent().getSelectedRow();

		if (selectedRow == -1)
			selectedRow = lastSelectedRow;
		else
			lastSelectedRow = selectedRow;

		Subjects subject = subjectTableModel.getSubjectsAt(selectedRow);
		new SubjectForm(subjectTableModel, SubjectActionType.EDIT_SUBJECT, subject, teacher);
	}

	private void showSubjectInfo() {
		int selectedRow = table.getComponent().getSelectedRow();

		if (selectedRow == -1)
			selectedRow = lastSelectedRow;
		else
			lastSelectedRow = selectedRow;

		Subjects subject = subjectTableModel.getSubjectsAt(selectedRow);
		new SubjectForm(subjectTableModel, SubjectActionType.INFO_SUBJECT, subject);
	}

	private void deleteSubject() {
		int selectedRow = table.getComponent().getSelectedRow();

		if (selectedRow == -1)
			selectedRow = lastSelectedRow;
		else
			lastSelectedRow = selectedRow;

		Subjects subject = subjectTableModel.getSubjectsAt(selectedRow);
		SubjectsService.deleteSubjectById(subject.getId());
		subjectTableModel.setSubjectsList(SubjectsService.getSubjects());

		if (teacher == null)
			return;
		ArrayList<Subjects> subjects = SubjectsService.getSubjectsByTeacherName(teacher.getName());
		if (subjects != null)
			subjectTableModel.setSubjectsList(subjects);
	}

	private void showStudentsTable() {

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
			showStudentInfo();
		}));
		buttonInfos.add(new ButtonInfo("", ImagesManager.getEditIcon(), e -> {
			editStudent();
		}));
		buttonInfos.add(new ButtonInfo("", ImagesManager.getDeleteIcon(), e -> {
			deleteStudent();
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

		if (teacher == null)
			return;

		ArrayList<UndergraduateStudent> undergraduateStudents = UndergraduateStudentService
				.getUndergraduateStudentsByNameOfMentee(teacher.getName());
		ArrayList<PosgraduateStudent> posgraduateStudents = PosgraduateStudentService
				.getPosgraduateStudentsByNameOfMentee(teacher.getName());
		ArrayList<Student> students = new ArrayList<>();

		if (undergraduateStudents != null) {
			students.addAll(undergraduateStudents);
		}

		if (posgraduateStudents != null) {
			students.addAll(posgraduateStudents);
		}

		studentsTableModel.setStudentList(students);
	}

	private void editStudent() {
		int selectedRow = table.getComponent().getSelectedRow();

		if (selectedRow == -1)
			selectedRow = lastSelectedRow;
		else
			lastSelectedRow = selectedRow;

		Student student = studentsTableModel.getStudentAt(selectedRow);
		new StudentForm(studentsTableModel, StudentActionType.EDIT_STUDENT, student, teacher);
	}

	private void showStudentInfo() {
		int selectedRow = table.getComponent().getSelectedRow();

		if (selectedRow == -1)
			selectedRow = lastSelectedRow;
		else
			lastSelectedRow = selectedRow;

		Student student = studentsTableModel.getStudentAt(selectedRow);
		new StudentForm(studentsTableModel, StudentActionType.INFO_STUDENT, student);

		if (teacher == null)
			return;

		ArrayList<UndergraduateStudent> undergraduateStudents = UndergraduateStudentService
				.getUndergraduateStudentsByNameOfMentee(teacher.getName());
		ArrayList<PosgraduateStudent> posgraduateStudents = PosgraduateStudentService
				.getPosgraduateStudentsByNameOfMentee(teacher.getName());
		ArrayList<Student> students = new ArrayList<>();

		if (undergraduateStudents != null) {
			students.addAll(undergraduateStudents);
		}

		if (posgraduateStudents != null) {
			students.addAll(posgraduateStudents);
		}

		studentsTableModel.setStudentList(students);
	}

	private void deleteStudent() {
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

		if (teacher == null)
			return;

		ArrayList<UndergraduateStudent> undergraduateStudents = UndergraduateStudentService
				.getUndergraduateStudentsByNameOfMentee(teacher.getName());
		ArrayList<PosgraduateStudent> posgraduateStudents = PosgraduateStudentService
				.getPosgraduateStudentsByNameOfMentee(teacher.getName());
		ArrayList<Student> students = new ArrayList<>();

		if (undergraduateStudents != null) {
			students.addAll(undergraduateStudents);
		}

		if (posgraduateStudents != null) {
			students.addAll(posgraduateStudents);
		}

		studentsTableModel.setStudentList(students);
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

		if (teacher == null)
			return;
		ArrayList<Paper> papers = PaperService.getPapersByAuthor(teacher.getName());
		if (papers != null)
			papersTableModel.setPaperList(papers);
	}

	private void editPaper() {
		int selectedRow = table.getComponent().getSelectedRow();

		if (selectedRow == -1)
			selectedRow = lastSelectedRow;
		else
			lastSelectedRow = selectedRow;

		Paper paper = papersTableModel.getPaperAt(selectedRow);
		new PaperForm(papersTableModel, PaperActionType.EDIT_PAPER, paper, teacher);

		if (teacher == null)
			return;
		ArrayList<Paper> papers = PaperService.getPapersByAuthor(teacher.getName());
		if (papers != null)
			papersTableModel.setPaperList(papers);
	}

	private void showPaperInfo() {
		int selectedRow = table.getComponent().getSelectedRow();

		if (selectedRow == -1)
			selectedRow = lastSelectedRow;
		else
			lastSelectedRow = selectedRow;

		Paper paper = papersTableModel.getPaperAt(selectedRow);
		new PaperForm(papersTableModel, PaperActionType.INFO_PAPER, paper);

		if (teacher == null)
			return;
		ArrayList<Paper> papers = PaperService.getPapersByAuthor(teacher.getName());
		if (papers != null)
			papersTableModel.setPaperList(papers);
	}

	private void deletePaper() {
		int selectedRow = table.getComponent().getSelectedRow();

		if (selectedRow == -1)
			selectedRow = lastSelectedRow;
		else
			lastSelectedRow = selectedRow;

		Paper paper = papersTableModel.getPaperAt(selectedRow);
		PaperService.deletePaperById(paper.getId());

		if (teacher == null)
			return;
		ArrayList<Paper> papers = PaperService.getPapersByAuthor(teacher.getName());
		if (papers != null)
			papersTableModel.setPaperList(papers);
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

		if (teacher == null)
			return;
		ArrayList<CoordinationActivity> coordinationActivities = CoordinationActivityService
				.getActivitiesByNameOfPersonResponsible(teacher.getName());
		if (coordinationActivities != null)
			coordinationActivityTableModel.setCoordinationActivityList(coordinationActivities);
	}

	private void editCoordinationActivity() {
		int selectedRow = table.getComponent().getSelectedRow();

		if (selectedRow == -1)
			selectedRow = lastSelectedRow;
		else
			lastSelectedRow = selectedRow;

		CoordinationActivity activity = coordinationActivityTableModel.getCoordinationActivityAt(selectedRow);
		new CoordinationActivityForm(coordinationActivityTableModel,
				CoordinationActivityActionType.EDIT_ACTIVITY, activity, teacher);
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

		if (teacher == null)
			return;
		ArrayList<CoordinationActivity> coordinationActivities = CoordinationActivityService
				.getActivitiesByNameOfPersonResponsible(teacher.getName());
		if (coordinationActivities != null)
			coordinationActivityTableModel.setCoordinationActivityList(coordinationActivities);
	}

	private void deleteCoordinationActivity() {

		int selectedRow = table.getComponent().getSelectedRow();

		if (selectedRow == -1)
			selectedRow = lastSelectedRow;
		else
			lastSelectedRow = selectedRow;

		CoordinationActivity activity = coordinationActivityTableModel.getCoordinationActivityAt(selectedRow);

		CoordinationActivityService.deleteCoordinationActivityById(activity.getId());

		if (teacher == null)
			return;
		ArrayList<CoordinationActivity> coordinationActivities = CoordinationActivityService
				.getActivitiesByNameOfPersonResponsible(teacher.getName());
		if (coordinationActivities != null)
			coordinationActivityTableModel.setCoordinationActivityList(coordinationActivities);
	}
}