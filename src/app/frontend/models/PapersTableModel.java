package app.frontend.models;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import app.backend.entities.Paper;

public class PapersTableModel extends AbstractTableModel {
	private ArrayList<Paper> papers = new ArrayList<>();

	public static final int ACTIONS_BUTTON_COLUMN_INDEX = 3;

	public void setPaperList(ArrayList<Paper> papers) {
		this.papers.clear();
		this.papers.addAll(papers);
		fireTableDataChanged();
	}

	public void setPaper(Paper paper) {
		this.papers.add(paper);
		fireTableDataChanged();
	}

	public int getRowCount() {
		return papers.size();
	}

	public int getColumnCount() {
		return 4;
	}

	public Paper getPaperAt(int row) {
		return papers.get(row);
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		Paper paper = this.papers.get(rowIndex);
		switch (columnIndex) {
			case 0:
				return paper.getTitle();
			case 1:
				return paper.getAuthors();
			case 2: 
				return paper.getCategory();
			default:
				return null;
		}
	}

	public String getColumnName(int columnIndex) {
		switch (columnIndex) {
			case 0:
				return "Título";
			case 1:
				return "Autor(es)";
			case 2:
				return "Categoria";
			case 3:
				return "Ações";
			default:
				return null;
		}
	}

	public Class<?> getColumnClass(int columnIndex) {
		switch (columnIndex) {
			case 0:
				return String.class;
			case 1:
				return String.class;
			case 2:
				return String.class;
			case 3:
				return Object.class;
			default:
				return null;
		}
	}

	public boolean isCellEditable(int rowIndex, int columnIndex) {
		if (columnIndex == ACTIONS_BUTTON_COLUMN_INDEX)
			return true;
		else
			return false;
	}

	public void updateTable() {
		fireTableDataChanged();
	}
}
