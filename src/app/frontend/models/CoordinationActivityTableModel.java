package app.frontend.models;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import app.backend.entities.CoordinationActivity;

public class CoordinationActivityTableModel extends AbstractTableModel {
	private ArrayList<CoordinationActivity> activities = new ArrayList<>();

	public static final int ACTIONS_BUTTON_COLUMN_INDEX = 4;

	public void setCoordinationActivityList(ArrayList<CoordinationActivity> activities) {
		this.activities.clear();
		this.activities.addAll(activities);
		fireTableDataChanged();
	}

	public void setCoordinationActivity(CoordinationActivity activity) {
		this.activities.add(activity);
		fireTableDataChanged();
	}

	public int getRowCount() {
		return activities.size();
	}

	public int getColumnCount() {
		return 5;
	}

	public CoordinationActivity getCoordinationActivityAt(int row) {
		return activities.get(row);
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		CoordinationActivity activity = this.activities.get(rowIndex);
		switch (columnIndex) {
			case 0:
				return activity.getActivityTitle();
			case 1:
				return activity.getNameOfPersonResponsible();
			case 2: {
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy");
				String formattedStartDate = activity.getStartDate().format(formatter);
				String formattedEndDate = activity.getEndDate().format(formatter);
				return formattedStartDate + " até " + formattedEndDate;
			}
			case 3:
				return activity.getStatus();
			default:
				return null;
		}
	}

	public String getColumnName(int columnIndex) {
		switch (columnIndex) {
			case 0:
				return "Título";
			case 1:
				return "Responsável";
			case 2:
				return "Período";
			case 3:
				return "Status";
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
