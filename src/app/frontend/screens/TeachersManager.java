package app.frontend.screens;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import app.frontend.components.TopBar;
import res.img.ImagesManager;
import res.values.ColorsManager;

public class TeachersManager extends JPanel {

	private static final String TITLE_WINDOW = "Gerenciamento de Professores";

	private TopBar topBar;
	// private CostumerTableModel costumerTableModel;
	// private JTable table;

	public TeachersManager() {
		this.setLayout(new BorderLayout(0, 24));

		topBar = new TopBar(TITLE_WINDOW);
		topBar.setBackground(ColorsManager.getOnBackgroundColor());

		topBar.setActionButton("Cadastrar", ImagesManager.getAddIcon(), new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// new AddCostumerForm(costumerTableModel);
				System.out.println("Adionar professor");
			}
		});

		this.add(topBar, BorderLayout.PAGE_START);
		// createTable();
	}

	// private void createTable() {
	// JPanel tableContainer = new JPanel();
	// costumerTableModel = new CostumerTableModel();

	// table = new JTable();
	// table.setBackground(COLOR_ON_BACKGROUND);
	// table.setModel(costumerTableModel);

	// DefaultTableCellRenderer alignLeft = new DefaultTableCellRenderer();
	// alignLeft.setHorizontalAlignment(SwingConstants.LEFT);

	// JTableHeader tableHeader = table.getTableHeader();
	// tableHeader.setBackground(COLOR_ON_BACKGROUND);
	// tableHeader.setFont(FontManager.medium16());
	// tableHeader.setPreferredSize(new Dimension(0, 40));
	// tableHeader.setSize(tableHeader.getPreferredSize());
	// tableHeader.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0,
	// Color.gray));
	// ;

	// table.setFont(FontManager.medium12());

	// JScrollPane tableScroll = new JScrollPane(table);
	// tableScroll.getViewport().setBackground(COLOR_ON_BACKGROUND);

	// tableContainer.setLayout(new BorderLayout());
	// tableContainer.add(tableScroll, BorderLayout.CENTER);

	// this.add(tableContainer);
	// }
}
