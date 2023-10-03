package app.frontend.screens.teachers;

import javax.swing.JPanel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;

import java.util.ArrayList;

import app.backend.entities.Teacher;
import app.backend.services.TeacherService;
import app.frontend.components.ActionsButtons;
import app.frontend.components.Table;
import app.frontend.components.TopBar;
import app.frontend.components.Button.ButtonInfo;
import app.frontend.components.Table.CellEditor;
import app.frontend.components.Table.CellRenderer;
import app.frontend.models.TeacherTableModel;
import app.frontend.screens.teachers.forms.TeacherForm;
import app.frontend.screens.teachers.forms.TeacherForm.ActionType;
import res.img.ImagesManager;
import res.values.ColorsManager;
import utils.ComponentDecorator;
import utils.Observable;

import java.time.*;

public class TeachersManager extends JPanel {

	private static final String TITLE_WINDOW = "Gerenciamento de Professores";
	public static final String ACTIVITY_TEACHER_WINDOWS_ID = "activity-teacher-windows";

	private TopBar topBar;
	private Table table;
	private TeacherTableModel teacherTableModel;
	private int lastSelectedRow = 0;

	private Teacher selectedTeacher;

	private Observable observable;

	public TeachersManager() {
		this.setLayout(new BorderLayout(0, 0));
		observable = new Observable();

		topBar = new TopBar(TITLE_WINDOW);
		topBar.setBackground(ColorsManager.getOnBackgroundColor());
		ComponentDecorator.addBorderBottom(topBar, 1);

		topBar.setActionButton("Cadastrar", ImagesManager.getAddIcon(), new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new TeacherForm(teacherTableModel, ActionType.ADD_TEACHER);
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
		buttonInfos.add(new ButtonInfo("", ImagesManager.getActivityIcon(), e -> { showActivity(); }));

		ActionsButtons actionsButtons = new ActionsButtons(buttonInfos);
		CellEditor actionsButtonCellEditor = new CellEditor(actionsButtons);
		CellRenderer actionsButtonCellRenderer = new CellRenderer(actionsButtons);

		table.setCustomColumn(TeacherTableModel.ACTIONS_BUTTON_COLUMN_INDEX, actionsButtonCellEditor, actionsButtonCellRenderer);
		table.setColumnWidth(0, 200);
		table.setColumnWidth(2, 160);

		tableContainer.setLayout(new BorderLayout());
		tableContainer.add(table, BorderLayout.CENTER);
		
		contentContainer.add(tableContainer, BorderLayout.CENTER);
		tableContainer.setBorder(null);

		ComponentDecorator.addPadding(table, 0);
		ComponentDecorator.addPadding(tableContainer, 12);
		ComponentDecorator.addPadding(contentContainer, 24);
		
		this.add(contentContainer);
		ArrayList<Teacher> teachers = TeacherService.getTeachers();

		if ( teachers != null ) teacherTableModel.setTeachersList(teachers);
	}

	private void editTeacher() {
		int selectedRow = table.getComponent().getSelectedRow();

		if ( selectedRow == -1 ) selectedRow = lastSelectedRow;
		else lastSelectedRow = selectedRow; 
		
		Teacher teacher = teacherTableModel.getTeachersAt(selectedRow);

		new TeacherForm(teacherTableModel, ActionType.EDIT_TEACHER, teacher);
	}

	private void showTeacherInfo() {
		int selectedRow = table.getComponent().getSelectedRow();

		if ( selectedRow == -1 ) selectedRow = lastSelectedRow;
		else lastSelectedRow = selectedRow; 

		Teacher teacher = teacherTableModel.getTeachersAt(selectedRow);
		
		new TeacherForm(teacherTableModel, ActionType.INFO_TEACHER, teacher);
	}

	private void deleteTeacher() {
		int selectedRow = table.getComponent().getSelectedRow();

		if ( selectedRow == -1 ) selectedRow = lastSelectedRow;
		else lastSelectedRow = selectedRow; 

		selectedTeacher = teacherTableModel.getTeachersAt(selectedRow);

		TeacherService.deleteTeacherById(selectedTeacher.getId());
		teacherTableModel.setTeachersList(TeacherService.getTeachers());
	}

	private void showActivity() {
		int selectedRow = table.getComponent().getSelectedRow();

		if ( selectedRow == -1 ) selectedRow = lastSelectedRow;
		else lastSelectedRow = selectedRow; 

		selectedTeacher = teacherTableModel.getTeachersAt(selectedRow);
		
		observable.notifyObservers(ACTIVITY_TEACHER_WINDOWS_ID);
	}

	public Teacher getSelectedTeacher() {
		return selectedTeacher;
	}

	public Observable getObservable() {
		return observable;
	}
}