package app.frontend.screens.students;

import javax.swing.JPanel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;

import java.util.ArrayList;

import app.backend.entities.PosgraduateStudent;
import app.backend.entities.Student;
import app.backend.entities.Subjects;
import app.backend.entities.Teacher;
import app.backend.entities.UndergraduateStudent;
import app.frontend.components.ActionsButtons;
import app.frontend.components.Table;
import app.frontend.components.TopBar;
import app.frontend.components.Button.ButtonInfo;
import app.frontend.components.Table.CellEditor;
import app.frontend.components.Table.CellRenderer;
import app.frontend.models.StudentsTableModel;
import app.frontend.models.StudentsTableModel;
import app.frontend.models.TeacherTableModel;
import app.frontend.screens.subjects.forms.SubjectForm;
import app.frontend.screens.subjects.forms.SubjectForm.ActionType;
import res.img.ImagesManager;
import res.values.ColorsManager;
import utils.ComponentDecorator;
import java.time.*;

public class StudentsManager extends JPanel {

	private static final String TITLE_WINDOW = "Gerenciamento de Alunos Orientados";

	private TopBar topBar;
	private Table table;
	private StudentsTableModel studentsTableModel;
	private int lastSelectedRow = 0;

	public StudentsManager() {
		this.setLayout(new BorderLayout(0, 0));

		topBar = new TopBar(TITLE_WINDOW);
		topBar.setBackground(ColorsManager.getOnBackgroundColor());
		ComponentDecorator.addBorderBottom(topBar, 1);

		topBar.setActionButton("Cadastrar", ImagesManager.getAddIcon(), new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// add forms here
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

		// Criando um objeto UndergraduateStudent
		UndergraduateStudent undergraduateStudent = new UndergraduateStudent();

		// Definindo atributos específicos de UndergraduateStudent
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
		// add forms here
	}

	private void showSubjectInfo() {
		int selectedRow = table.getComponent().getSelectedRow();

		if (selectedRow == -1)
			selectedRow = lastSelectedRow;
		else
			lastSelectedRow = selectedRow;

		Student student = studentsTableModel.getStudentAt(selectedRow);
		// add forms here

	}

	private void deleteSubject() {
		System.out.println("Delete Student");
	}
}