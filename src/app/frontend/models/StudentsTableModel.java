package app.frontend.models;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import app.backend.entities.PosgraduateStudent;
import app.backend.entities.Student;
import app.backend.entities.UndergraduateStudent;

public class StudentsTableModel extends AbstractTableModel {
	private ArrayList<Student> students = new ArrayList<>();

	public static final int ACTIONS_BUTTON_COLUMN_INDEX = 4;

	public void setStudentList(ArrayList<Student> students) {
		this.students.clear();
		this.students.addAll(students);
		fireTableDataChanged();
	}

	public void setStudent(Student student) {
		this.students.add(student);
		fireTableDataChanged();
	}

	public int getRowCount() {
		return students.size();
	}

	public int getColumnCount() {
		return 5;
	}

	public Student getStudentAt(int row) {
		return students.get(row);
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		Student student = this.students.get(rowIndex);
		switch (columnIndex) {
			case 0:
				return student.getName();
			case 1:
				if ( student instanceof PosgraduateStudent ) return ((PosgraduateStudent)student).getPosgraduateProgram();
				else return ((UndergraduateStudent)student).getTypeOfOrientation();
			case 2: {
				if ( student instanceof PosgraduateStudent ) return ((PosgraduateStudent)student).getResearchTitle();
				else return ((UndergraduateStudent)student).getProjectName();
			}
			case 3:
				return student.getStatus();
			default:
				return null;
		}
	}

	public String getColumnName(int columnIndex) {
		switch (columnIndex) {
			case 0:
				return "Nome";
			case 1:
				return "Tipo de Orientação";
			case 2:
				return "Título da Pesquisa/Projeto";
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
