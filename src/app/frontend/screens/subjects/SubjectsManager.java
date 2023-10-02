package app.frontend.screens.subjects;

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
	private TeacherTableModel teacherTableModel;
	private int lastSelectedRow = 0;

	public SubjectsManager() {
		this.setLayout(new BorderLayout(0, 0));

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
	}
}