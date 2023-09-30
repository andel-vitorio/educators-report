package app.frontend.models;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import app.backend.entities.Teacher;

public class TeacherTableModel extends AbstractTableModel {
    private ArrayList<Teacher> teachers = new ArrayList<>();
    
    public void setTeachersList(ArrayList<Teacher> costumer) {
        this.teachers.clear();
        this.teachers.addAll(costumer);
        fireTableDataChanged();
    }

    public void setTeachers(Teacher costumer) {
        this.teachers.add(costumer);
        fireTableDataChanged();
    }

    public int getRowCount() {
        return teachers.size();
    }
    
    public int getColumnCount() {
        return 3;
    }

    public Teacher getTeachersAt(int row) {
        return teachers.get(row);
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        Teacher teacher = this.teachers.get(rowIndex);
        switch (columnIndex) {
            case 0: return teacher.getIndenticatorNumber();
            case 1: return teacher.getName();
            case 2: return "Editar";
            default: return null;
        }
    }

    public String getColumnName(int columnIndex) {
        switch (columnIndex) {
            case 0: return "Nº de Cadastro";
            case 1: return "Professor";
            case 2: return "Ações";
            default: return null;
        }
    }

    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0: return Integer.class;
            case 1: return String.class;
            case 2: return Object.class;
            default: return null;
        }
    }

		public boolean isCellEditable(int rowIndex, int columnIndex) {
			if (columnIndex == 2) return true;
			else return false;
		}

		public void updateTable() {
			fireTableDataChanged();
		}
}
