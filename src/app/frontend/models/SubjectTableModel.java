package app.frontend.models;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import app.backend.entities.Subjects;

/**
 * Um modelo de tabela personalizado para exibir informações sobre disciplinas.
 */
public class SubjectTableModel extends AbstractTableModel {

    /** Lista de disciplinas a serem exibidas na tabela. */
    private ArrayList<Subjects> subjects = new ArrayList<>();

    /** Índice da coluna que conterá botões de ação (por exemplo, botões "Editar" ou "Excluir"). */
    public static final int ACTIONS_BUTTON_COLUMN_INDEX = 4;

    /**
     * Define a lista de disciplinas a serem exibidas na tabela.
     *
     * @param subjects A lista de disciplinas.
     */
    public void setSubjectsList(ArrayList<Subjects> subjects) {
        this.subjects.clear();
        this.subjects.addAll(subjects);
        fireTableDataChanged(); // Notifica a tabela que os dados mudaram
    }

    /**
     * Adiciona uma disciplina à tabela.
     *
     * @param subject A disciplina a ser adicionada.
     */
    public void setSubjects(Subjects subject) {
        this.subjects.add(subject);
        fireTableDataChanged(); // Notifica a tabela que os dados mudaram
    }

    /**
     * Obtém o número de linhas na tabela (ou seja, o número de disciplinas).
     *
     * @return O número de linhas.
     */
    public int getRowCount() {
        return subjects.size();
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
     * Obtém uma disciplina em uma determinada linha.
     *
     * @param row O índice da linha.
     * @return A disciplina na linha especificada.
     */
    public Subjects getSubjectsAt(int row) {
        return subjects.get(row);
    }

    /**
     * Obtém o valor a ser exibido em uma célula específica da tabela.
     *
     * @param rowIndex    O índice da linha.
     * @param columnIndex O índice da coluna.
     * @return O valor a ser exibido na célula especificada.
     */
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

    /**
     * Obtém o nome da coluna com base no índice da coluna.
     *
     * @param columnIndex O índice da coluna.
     * @return O nome da coluna.
     */
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
