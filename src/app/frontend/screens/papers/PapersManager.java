package app.frontend.screens.papers;

import javax.swing.JPanel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;

import java.util.ArrayList;

import app.backend.entities.Paper;
import app.backend.entities.Subjects;
import app.backend.entities.Teacher;
import app.frontend.components.ActionsButtons;
import app.frontend.components.Table;
import app.frontend.components.TopBar;
import app.frontend.components.Button.ButtonInfo;
import app.frontend.components.Table.CellEditor;
import app.frontend.components.Table.CellRenderer;
import app.frontend.models.PapersTableModel;
import app.frontend.models.TeacherTableModel;
import app.frontend.screens.papers.forms.PaperForm;
import app.frontend.screens.papers.forms.PaperForm.PaperActionType;
import app.frontend.screens.subjects.forms.SubjectForm;
import app.frontend.screens.subjects.forms.SubjectForm.ActionType;
import res.img.ImagesManager;
import res.values.ColorsManager;
import utils.ComponentDecorator;
import java.time.*;

public class PapersManager extends JPanel {

	private static final String TITLE_WINDOW = "Gerenciamento de Artigos";

	private TopBar topBar;
	private Table table;
	private PapersTableModel papersTableModel;
	private int lastSelectedRow = 0;

	public PapersManager() {
		this.setLayout(new BorderLayout(0, 0));

		topBar = new TopBar(TITLE_WINDOW);
		topBar.setBackground(ColorsManager.getOnBackgroundColor());
		ComponentDecorator.addBorderBottom(topBar, 1);

		topBar.setActionButton("Cadastrar", ImagesManager.getAddIcon(), new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new PaperForm(papersTableModel, PaperActionType.ADD_PAPER);
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

		papersTableModel = new PapersTableModel();
		table = new Table(papersTableModel);

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

		table.setCustomColumn(PapersTableModel.ACTIONS_BUTTON_COLUMN_INDEX, actionsButtonCellEditor,
				actionsButtonCellRenderer);
		table.setColumnWidth(PapersTableModel.ACTIONS_BUTTON_COLUMN_INDEX, 120);

		tableContainer.setLayout(new BorderLayout());
		tableContainer.add(table, BorderLayout.CENTER);

		contentContainer.add(tableContainer, BorderLayout.CENTER);
		tableContainer.setBorder(null);

		ComponentDecorator.addPadding(table, 0);
		ComponentDecorator.addPadding(tableContainer, 12);
		ComponentDecorator.addPadding(contentContainer, 24);

		this.add(contentContainer);

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

	private void editSubject() {
		int selectedRow = table.getComponent().getSelectedRow();

		if (selectedRow == -1)
			selectedRow = lastSelectedRow;
		else
			lastSelectedRow = selectedRow;

		Paper paper = papersTableModel.getPaperAt(selectedRow);
		new PaperForm(papersTableModel, PaperActionType.EDIT_PAPER, paper);
	}

	private void showSubjectInfo() {
		int selectedRow = table.getComponent().getSelectedRow();

		if (selectedRow == -1)
			selectedRow = lastSelectedRow;
		else
			lastSelectedRow = selectedRow;

		Paper paper = papersTableModel.getPaperAt(selectedRow);
		new PaperForm(papersTableModel, PaperActionType.INFO_PAPER, paper);
	}

	private void deleteSubject() {
		System.out.println("Delete Subject");
	}
}