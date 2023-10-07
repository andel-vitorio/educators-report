package app.frontend.models;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import app.backend.entities.PosgraduateStudent;
import app.backend.entities.Student;
import app.backend.entities.UndergraduateStudent;

/**
 * Um modelo de tabela personalizado para exibir informações sobre estudantes.
 */
public class StudentsTableModel extends AbstractTableModel {

    /** Lista de estudantes a serem exibidos na tabela. */
    private ArrayList<Student> students = new ArrayList<>();

    /** Índice da coluna que conterá botões de ação (por exemplo, botões "Editar" ou "Excluir"). */
    public static final int ACTIONS_BUTTON_COLUMN_INDEX = 4;

    /**
     * Define a lista de estudantes a serem exibidos na tabela.
     *
     * @param students A lista de estudantes.
     */
    public void setStudentList(ArrayList<Student> students) {
        this.students.clear();
        this.students.addAll(students);
        fireTableDataChanged(); // Notifica a tabela que os dados mudaram
    }

    /**
     * Adiciona um estudante à tabela.
     *
     * @param student O estudante a ser adicionado.
     */
    public void setStudent(Student student) {
        this.students.add(student);
        fireTableDataChanged(); // Notifica a tabela que os dados mudaram
    }

    /**
     * Obtém o número de linhas na tabela (ou seja, o número de estudantes).
     *
     * @return O número de linhas.
     */
    public int getRowCount() {
        return students.size();
    }

    /**
     * Obtém o número de colunas na tabela.
     *
     * @return O número de colunas.
     */
    public int getColumnCount() {
        return 5;
    }

    /**
     * Obtém um estudante em uma determinada linha.
     *
     * @param row O índice da linha.
     * @return O estudante na linha especificada.
     */
    public Student getStudentAt(int row) {
        return students.get(row);
    }

    /**
     * Obtém o valor a ser exibido em uma célula específica da tabela.
     *
     * @param rowIndex    O índice da linha.
     * @param columnIndex O índice da coluna.
     * @return O valor a ser exibido na célula especificada.
     */
    public Object getValueAt(int rowIndex, int columnIndex) {
        Student student = this.students.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return student.getName();
            case 1:
                if (student instanceof PosgraduateStudent)
                    return ((PosgraduateStudent) student).getPosgraduateProgram();
                else
                    return ((UndergraduateStudent) student).getTypeOfOrientation();
            case 2: {
                if (student instanceof PosgraduateStudent)
                    return ((PosgraduateStudent) student).getResearchTitle();
                else
                    return ((UndergraduateStudent) student).getProjectName();
            }
            case 3:
                return student.getStatus();
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

    /**
     * Obtém a classe das células em uma determinada coluna.
     *
     * @param columnIndex O índice da coluna.
     * @return A classe das células na coluna especificada.
     */
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
