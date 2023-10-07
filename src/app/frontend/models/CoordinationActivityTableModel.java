package app.frontend.models;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import app.backend.entities.CoordinationActivity;

/**
 * Um modelo de tabela personalizado para exibir informações sobre atividades de coordenação.
 */
public class CoordinationActivityTableModel extends AbstractTableModel {

    /** Lista de atividades de coordenação a serem exibidas na tabela. */
    private ArrayList<CoordinationActivity> activities = new ArrayList<>();

    /** Índice da coluna que conterá botões de ação (por exemplo, botões "Editar" ou "Excluir"). */
    public static final int ACTIONS_BUTTON_COLUMN_INDEX = 4;

    /**
     * Define a lista de atividades de coordenação a serem exibidas na tabela.
     *
     * @param activities A lista de atividades de coordenação.
     */
    public void setCoordinationActivityList(ArrayList<CoordinationActivity> activities) {
        this.activities.clear();
        this.activities.addAll(activities);
        fireTableDataChanged(); // Notifica a tabela que os dados mudaram
    }

    /**
     * Adiciona uma atividade de coordenação à tabela.
     *
     * @param activity A atividade de coordenação a ser adicionada.
     */
    public void setCoordinationActivity(CoordinationActivity activity) {
        this.activities.add(activity);
        fireTableDataChanged(); // Notifica a tabela que os dados mudaram
    }

    /**
     * Obtém o número de linhas na tabela (ou seja, o número de atividades).
     *
     * @return O número de linhas.
     */
    public int getRowCount() {
        return activities.size();
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
     * Obtém uma atividade de coordenação em uma determinada linha.
     *
     * @param row O índice da linha.
     * @return A atividade de coordenação na linha especificada.
     */
    public CoordinationActivity getCoordinationActivityAt(int row) {
        return activities.get(row);
    }

    /**
     * Obtém o valor a ser exibido em uma célula específica da tabela.
     *
     * @param rowIndex    O índice da linha.
     * @param columnIndex O índice da coluna.
     * @return O valor a ser exibido na célula especificada.
     */
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

    /**
     * Obtém o nome da coluna com base no índice da coluna.
     *
     * @param columnIndex O índice da coluna.
     * @return O nome da coluna.
     */
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
