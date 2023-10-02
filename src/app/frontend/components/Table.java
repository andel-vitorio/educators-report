package app.frontend.components;

import javax.swing.*;

import java.awt.*;
import java.util.EventObject;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumn;

import res.fonts.FontsManager;
import res.fonts.FontsManager.FontType;
import res.values.ColorsManager;
import res.values.DimensManager;
import utils.ComponentDecorator;

public class Table extends JPanel {
	private JTable table;

	private static final Color COLOR_ON_BACKGROUND = Color.WHITE; // Defina sua cor aqui

	public enum CellContentAlignment {
		START, CENTER, END
	}

	public static class CellRenderer extends DefaultTableCellRenderer {
		private Component component;

		public CellRenderer(Component component) {
			this.component = component;
			setHorizontalAlignment(SwingConstants.LEFT);
		}

		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			if (isSelected)
				component.setBackground(table.getSelectionBackground());
			else
				component.setBackground(table.getBackground());
			return component;
		}

	}

	public static class CellEditor extends AbstractCellEditor implements TableCellEditor {

		private Component component;

		public CellEditor(Component component) {
			this.component = component;
		}

		@Override
		public Object getCellEditorValue() {
			return null;
		}

		@Override
		public boolean isCellEditable(EventObject e) {
			return true;
		}

		@Override
		public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
			if (isSelected)
				component.setBackground(table.getSelectionBackground());
			else
				component.setBackground(table.getBackground());
			return component;
		}
	}

	public Table(AbstractTableModel tableModel) {
		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
		renderer.setHorizontalAlignment(SwingConstants.LEFT);

		table = new JTable(tableModel);
		table.setBackground(ColorsManager.getOnBackgroundColor());
		table.setShowVerticalLines(false);
		table.setRowHeight(DimensManager.getRowHeight());

		for (int i = 0; i < table.getColumnCount(); i++)
			table.getColumnModel().getColumn(i).setCellRenderer(renderer);

		JTableHeader tableHeader = table.getTableHeader();
		tableHeader.setBackground(COLOR_ON_BACKGROUND);
		tableHeader.setPreferredSize(new Dimension(0, 40));
		tableHeader.setSize(tableHeader.getPreferredSize());
		tableHeader.setDefaultRenderer(renderer);
		ComponentDecorator.addBorderBottom(tableHeader, 1, Color.BLACK);

		if (!FontsManager.getLoadError()) {
			tableHeader.setFont(FontsManager.getFont(FontType.REGULAR, DimensManager.getTableHeaderTextsize()));
			table.setFont(FontsManager.getFont(FontType.REGULAR, DimensManager.getTableItemTextsize()));
		}

		ComponentDecorator.addPadding(table, 10);
		JScrollPane tableScroll = new JScrollPane(table);
		tableScroll.setBorder(BorderFactory.createEmptyBorder());
		tableScroll.getViewport().setBackground(ColorsManager.getOnBackgroundColor());

		setLayout(new BorderLayout());
		add(tableScroll, BorderLayout.CENTER);
	}

	public void setCustomColumn(int columnIndex, CellEditor cellEditor, CellRenderer columnRenderer) {
		table.getColumnModel().getColumn(columnIndex).setCellEditor(cellEditor);
		table.getColumnModel().getColumn(columnIndex).setCellRenderer(columnRenderer);;
	}

	public void setColumnWidth(int columnIndex, int widht) {
		TableColumn column = table.getColumnModel().getColumn(columnIndex);
		column.setPreferredWidth(widht);
		column.setMaxWidth(column.getPreferredWidth());
		column.setMaxWidth(column.getPreferredWidth());
	}

	public void setHorizontalAlignment(int columnIndex, CellContentAlignment a) {
		TableColumn column = table.getColumnModel().getColumn(columnIndex);
		DefaultTableCellRenderer align = new DefaultTableCellRenderer();

		switch (a) {
			case END: {
				align.setHorizontalAlignment(SwingConstants.RIGHT);
				break;
			}

			default: {
				align.setHorizontalAlignment(SwingConstants.LEFT);
				break;
			}
		}

		column.setCellRenderer(align);
	}

	public JTable getComponent() {
		return table;
	}
}
