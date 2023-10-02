package app.frontend.models;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import app.backend.entities.Subjects;

public class SubjectTableModel extends AbstractTableModel {
	private ArrayList<Subjects> subjects = new ArrayList<>();

	public static final int ACTIONS_BUTTON_COLUMN_INDEX = 4;

	public void setSubjectsList(ArrayList<Subjects> costumer) {
		this.subjects.clear();
		this.subjects.addAll(costumer);
		fireTableDataChanged();
	}

	public void setSubjects(Subjects costumer) {
		this.subjects.add(costumer);
		fireTableDataChanged();
	}

	public int getRowCount() {
		return subjects.size();
	}

	public int getColumnCount() {
		return 5;
	}

	public Subjects getSubjectsAt(int row) {
		return subjects.get(row);
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		Subjects subject = this.subjects.get(rowIndex);
		switch (columnIndex) {
			case 0:
				return subject.getCode();
			case 1:
				return subject.getName();
			case 2: {
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
				String startTimeFormatted = subject.getStartTime().format(formatter);
				String endTimeFormatted = subject.getEndTime().format(formatter);
				return startTimeFormatted + " até " + endTimeFormatted;
			}
			case 3:
				return subject.getTeacherName();
			default:
				return null;
		}
	}

	public String getColumnName(int columnIndex) {
		switch (columnIndex) {
			case 0:
				return "Código";
			case 1:
				return "Nome";
			case 2:
				return "Horário";
			case 3:
				return "Professor";
			case 4:
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
				return String.class;
			case 4:
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
