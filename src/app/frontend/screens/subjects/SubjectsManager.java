package app.frontend.screens.subjects;

import javax.swing.JPanel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;

import java.util.ArrayList;

import app.backend.entities.Subjects;
import app.backend.entities.Teacher;
import app.frontend.components.ActionsButtons;
import app.frontend.components.Table;
import app.frontend.components.TopBar;
import app.frontend.components.Button.ButtonInfo;
import app.frontend.components.Table.CellEditor;
import app.frontend.components.Table.CellRenderer;
import app.frontend.models.SubjectTableModel;
import app.frontend.models.TeacherTableModel;
import app.frontend.screens.teachers.forms.TeacherForm;
import app.frontend.screens.teachers.forms.TeacherForm.ActionType;
import res.img.ImagesManager;
import res.values.ColorsManager;
import utils.ComponentDecorator;
import java.time.*;

public class SubjectsManager extends JPanel {

	private static final String TITLE_WINDOW = "Gerenciamento de Disciplinas";

	private TopBar topBar;
	private Table table;
	private SubjectTableModel subjectTableModel;
	private int lastSelectedRow = 0;

	public SubjectsManager() {
		this.setLayout(new BorderLayout(0, 0));

		topBar = new TopBar(TITLE_WINDOW);
		topBar.setBackground(ColorsManager.getOnBackgroundColor());
		ComponentDecorator.addBorderBottom(topBar, 1);

		topBar.setActionButton("Cadastrar", ImagesManager.getAddIcon(), new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
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

					// Criar um retângulo com cantos arredondados
					RoundRectangle2D roundedRectangle = new RoundRectangle2D.Float(0, 0, width - 1, height - 1, 24, 24);

					g2d.setColor(ColorsManager.getOnBackgroundColor());
					g2d.fill(roundedRectangle);

					// Desenhar a borda do painel
					g2d.setColor(ColorsManager.getOnBackgroundColor()); // Cor da borda personalizável
					g2d.draw(roundedRectangle);
			}
		};

		subjectTableModel = new SubjectTableModel();
		table = new Table(subjectTableModel);

		ArrayList<ButtonInfo> buttonInfos =  new ArrayList<>();
		buttonInfos.add(new ButtonInfo("", ImagesManager.getInfoIcon(), e -> { showSubjectInfo(); }));
		buttonInfos.add(new ButtonInfo("", ImagesManager.getEditIcon(), e -> { editSubject(); }));
		buttonInfos.add(new ButtonInfo("", ImagesManager.getDeleteIcon(), e -> { deleteSubject(); }));

		ActionsButtons actionsButtons = new ActionsButtons(buttonInfos);
		CellEditor actionsButtonCellEditor = new CellEditor(actionsButtons);
		CellRenderer actionsButtonCellRenderer = new CellRenderer(actionsButtons);

		table.setCustomColumn(SubjectTableModel.ACTIONS_BUTTON_COLUMN_INDEX, actionsButtonCellEditor, actionsButtonCellRenderer);
		table.setColumnWidth(SubjectTableModel.ACTIONS_BUTTON_COLUMN_INDEX, 120);

		tableContainer.setLayout(new BorderLayout());
		tableContainer.add(table, BorderLayout.CENTER);
		
		contentContainer.add(tableContainer, BorderLayout.CENTER);
		tableContainer.setBorder(null);

		ComponentDecorator.addPadding(table, 0);
		ComponentDecorator.addPadding(tableContainer, 12);
		ComponentDecorator.addPadding(contentContainer, 24);
		
		this.add(contentContainer);

		Subjects subject = new Subjects();

		subject.setCode("MATH101");
		subject.setName("Matemática Básica");
		subject.setDescription("Um curso introdutório de matemática.");
		subject.setStartTime(LocalTime.of(9, 0)); // Horário de início às 9:00
		subject.setEndTime(LocalTime.of(11, 0));  // Horário de término às 11:00
		subject.setClassroom("Sala 101");
		subject.setTeacherName("Professor Smith");
		subject.setRequirements("Nenhum pré-requisito necessário");
		subject.setCourseLoad(60);  // 60 horas de carga horária
		subject.setCredits(3);     // 3 créditos
		subject.setNumberOfVacancies(30);  // 30 vagas disponíveis

		subjectTableModel.setSubjects(subject);
		subjectTableModel.updateTable();
	}

	private void editSubject() {
		int selectedRow = table.getComponent().getSelectedRow();

		if ( selectedRow == -1 ) selectedRow = lastSelectedRow;
		else lastSelectedRow = selectedRow; 
		
		Subjects subject = subjectTableModel.getSubjectsAt(selectedRow);

	}

	private void showSubjectInfo() {
		int selectedRow = table.getComponent().getSelectedRow();

		if ( selectedRow == -1 ) selectedRow = lastSelectedRow;
		else lastSelectedRow = selectedRow; 

		Subjects subject = subjectTableModel.getSubjectsAt(selectedRow);
	}

	private void deleteSubject() {
		System.out.println("Delete Subject");
	}
}