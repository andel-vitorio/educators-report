package app.frontend.models;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import app.backend.entities.Teacher;

/**
 * Um modelo de tabela personalizado para exibir informações sobre professores.
 */
public class TeacherTableModel extends AbstractTableModel {

    /** Lista de professores a serem exibidos na tabela. */
    private ArrayList<Teacher> teachers = new ArrayList<>();

    /** Índice da coluna que conterá botões de ação (por exemplo, botões "Editar" ou "Excluir"). */
    public static final int ACTIONS_BUTTON_COLUMN_INDEX = 2;

    /**
     * Define a lista de professores a serem exibidos na tabela.
     *
     * @param teachers A lista de professores.
     */
    public void setTeachersList(ArrayList<Teacher> teachers) {
        this.teachers.clear();
        this.teachers.addAll(teachers);
        fireTableDataChanged(); // Notifica a tabela que os dados mudaram
    }

    /**
     * Adiciona um professor à tabela.
     *
     * @param teacher O professor a ser adicionado.
     */
    public void setTeachers(Teacher teacher) {
        this.teachers.add(teacher);
        fireTableDataChanged(); // Notifica a tabela que os dados mudaram
    }

    /**
     * Obtém o número de linhas na tabela (ou seja, o número de professores).
     *
     * @return O número de linhas.
     */
    public int getRowCount() {
        return teachers.size();
    }

    /**
     * Obtém o número de colunas na tabela.
     *
     * @return O número de colunas.
     */
    public int getColumnCount() {
        return 3;
    }

    /**
     * Obtém um professor em uma determinada linha.
     *
     * @param row O índice da linha.
     * @return O professor na linha especificada.
     */
    public Teacher getTeachersAt(int row) {
        return teachers.get(row);
    }

    /**
     * Obtém o valor a ser exibido em uma célula específica da tabela.
     *
     * @param rowIndex    O índice da linha.
     * @param columnIndex O índice da coluna.
     * @return O valor a ser exibido na célula especificada.
     */
    public Object getValueAt(int rowIndex, int columnIndex) {
        Teacher teacher = this.teachers.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return teacher.getIndentificatorNumber();
            case 1:
                return teacher.getName();
            default:
                return null;
        }
    }

    /**
     * Obtém o nome da coluna com base no índice da coluna.
     *
     * @param columnIndex O índice da coluna.
     * @return O nome da coluna.
     */
    public String getColumnName(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return "Nº de Cadastro";
            case 1:
                return "Professor";
            case 2:
                return "Ações";
            default:
                return null;
        }
    }

    /**
     * Obtém a classe das células em uma determinada coluna.
     *
     * @param columnIndex O índice da coluna.
     * @return A classe das células na coluna especificada.
     */
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return Integer.class;
            case 1:
                return String.class;
            case 2:
                return Object.class;
            default:
                return null;
        }
    }

    /**
     * Verifica se uma célula específica é editável (por exemplo, coluna de botões de ação).
     *
     * @param rowIndex    O índice da linha.
     * @param columnIndex O índice da coluna.
     * @return true se a célula é editável, caso contrário, false.
     */
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        if (columnIndex == ACTIONS_BUTTON_COLUMN_INDEX)
            return true;
        else
            return false;
    }

    /**
     * Atualiza a tabela para refletir quaisquer alterações nos dados.
     */
    public void updateTable() {
        fireTableDataChanged();
    }
}
