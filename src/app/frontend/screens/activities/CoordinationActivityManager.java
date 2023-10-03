package app.frontend.screens.activities;

import javax.swing.JPanel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;

import java.util.ArrayList;

import app.backend.entities.CoordinationActivity;
import app.backend.services.CoordinationActivityService;
import app.frontend.components.ActionsButtons;
import app.frontend.components.Table;
import app.frontend.components.TopBar;
import app.frontend.components.Button.ButtonInfo;
import app.frontend.components.Table.CellEditor;
import app.frontend.components.Table.CellRenderer;
import app.frontend.models.CoordinationActivityTableModel;
import app.frontend.screens.activities.form.CoordinationActivityForm;
import app.frontend.screens.activities.form.CoordinationActivityForm.CoordinationActivityActionType;
import res.img.ImagesManager;
import res.values.ColorsManager;
import utils.ComponentDecorator;
import java.time.*;

public class CoordinationActivityManager extends JPanel {

	private static final String TITLE_WINDOW = "Gerenciamento de Atividades da Coordenação";

	private TopBar topBar;
	private Table table;
	private CoordinationActivityTableModel coordinationActivityTableModel;
	private int lastSelectedRow = 0;

	public CoordinationActivityManager() {
		this.setLayout(new BorderLayout(0, 0));

		topBar = new TopBar(TITLE_WINDOW);
		topBar.setBackground(ColorsManager.getOnBackgroundColor());
		ComponentDecorator.addBorderBottom(topBar, 1);

		topBar.setActionButton("Cadastrar", ImagesManager.getAddIcon(), new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new CoordinationActivityForm(coordinationActivityTableModel,
						CoordinationActivityActionType.ADD_ACTIVITY);
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
		tableContainer.add(table, BorderLayout.CENTER);

		contentContainer.add(tableContainer, BorderLayout.CENTER);
		tableContainer.setBorder(null);

		ComponentDecorator.addPadding(table, 0);
		ComponentDecorator.addPadding(tableContainer, 12);
		ComponentDecorator.addPadding(contentContainer, 24);

		this.add(contentContainer);

		ArrayList<CoordinationActivity> coordinationActivities = CoordinationActivityService.getCoordinationActivities();

		if ( coordinationActivities != null )
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
		int selectedRow = table.getComponent().getSelectedRow();

		if (selectedRow == -1)
			selectedRow = lastSelectedRow;
		else
			lastSelectedRow = selectedRow;

		CoordinationActivity activity = coordinationActivityTableModel.getCoordinationActivityAt(selectedRow);

		CoordinationActivityService.deleteCoordinationActivityById(activity.getId());
		coordinationActivityTableModel.setCoordinationActivityList(CoordinationActivityService.getCoordinationActivities());
	}
}