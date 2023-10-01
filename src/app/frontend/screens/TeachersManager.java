package app.frontend.screens;

import javax.swing.JPanel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;

import java.util.ArrayList;

import app.backend.entities.Teacher;
import app.frontend.components.ActionsButtons;
import app.frontend.components.Table;
import app.frontend.components.TopBar;
import app.frontend.components.Button.ButtonInfo;
import app.frontend.components.Table.CellEditor;
import app.frontend.components.Table.CellRenderer;
import app.frontend.models.TeacherTableModel;
import res.img.ImagesManager;
import res.values.ColorsManager;
import utils.ComponentDecorator;
import java.time.*;

public class TeachersManager extends JPanel {

	private static final String TITLE_WINDOW = "Gerenciamento de Professores";

	private TopBar topBar;
	private Table table;
	private TeacherTableModel teacherTableModel;

	public TeachersManager() {
		this.setLayout(new BorderLayout(0, 0));

		topBar = new TopBar(TITLE_WINDOW);
		topBar.setBackground(ColorsManager.getOnBackgroundColor());
		ComponentDecorator.addBorderBottom(topBar, 1);

		topBar.setActionButton("Cadastrar", ImagesManager.getAddIcon(), new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Adionar professor");
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

		teacherTableModel = new TeacherTableModel();
		table = new Table(teacherTableModel);

		ArrayList<ButtonInfo> buttonInfos =  new ArrayList<>();
		buttonInfos.add(new ButtonInfo("", ImagesManager.getInfoIcon(), e -> { showTeacherInfo(); }));
		buttonInfos.add(new ButtonInfo("", ImagesManager.getEditIcon(), e -> { editTeacher(); }));
		buttonInfos.add(new ButtonInfo("", ImagesManager.getDeleteIcon(), e -> { deleteTeacher(); }));

		ActionsButtons actionsButtons = new ActionsButtons(buttonInfos);
		CellEditor actionsButtonCellEditor = new CellEditor(actionsButtons);
		CellRenderer actionsButtonCellRenderer = new CellRenderer(actionsButtons);

		table.setCustomColumn(TeacherTableModel.ACTIONS_BUTTON_COLUMN_INDEX, actionsButtonCellEditor, actionsButtonCellRenderer);
		table.setColumnWidth(0, 200);
		table.setColumnWidth(2, 120);

		tableContainer.setLayout(new BorderLayout());
		tableContainer.add(table, BorderLayout.CENTER);
		
		contentContainer.add(tableContainer, BorderLayout.CENTER);
		tableContainer.setBorder(null);

		ComponentDecorator.addPadding(table, 0);
		ComponentDecorator.addPadding(tableContainer, 12);
		ComponentDecorator.addPadding(contentContainer, 24);
		
		this.add(contentContainer);

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
		System.out.println("Edit Teacher");
	}

	private void showTeacherInfo() {
		System.out.println("Info Teacher");
	}

	private void deleteTeacher() {
		System.out.println("Delete Teacher");
	}
}