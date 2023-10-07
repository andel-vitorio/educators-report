package app.frontend.models;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import app.backend.entities.Paper;

/**
 * Um modelo de tabela personalizado para exibir informações sobre papers (artigos científicos).
 */
public class PapersTableModel extends AbstractTableModel {

    /** Lista de papers a serem exibidos na tabela. */
    private ArrayList<Paper> papers = new ArrayList<>();

    /** Índice da coluna que conterá botões de ação (por exemplo, botões "Editar" ou "Excluir"). */
    public static final int ACTIONS_BUTTON_COLUMN_INDEX = 3;

    /**
     * Define a lista de papers a serem exibidos na tabela.
     *
     * @param papers A lista de papers.
     */
    public void setPaperList(ArrayList<Paper> papers) {
        this.papers.clear();
        this.papers.addAll(papers);
        fireTableDataChanged(); // Notifica a tabela que os dados mudaram
    }

    /**
     * Adiciona um paper à tabela.
     *
     * @param paper O paper a ser adicionado.
     */
    public void setPaper(Paper paper) {
        this.papers.add(paper);
        fireTableDataChanged(); // Notifica a tabela que os dados mudaram
    }

    /**
     * Obtém o número de linhas na tabela (ou seja, o número de papers).
     *
     * @return O número de linhas.
     */
    public int getRowCount() {
        return papers.size();
    }

    /**
     * Obtém o número de colunas na tabela.
     *
     * @return O número de colunas.
     */
    public int getColumnCount() {
        return 4;
    }

    /**
     * Obtém um paper em uma determinada linha.
     *
     * @param row O índice da linha.
     * @return O paper na linha especificada.
     */
    public Paper getPaperAt(int row) {
        return papers.get(row);
    }

    /**
     * Obtém o valor a ser exibido em uma célula específica da tabela.
     *
     * @param rowIndex    O índice da linha.
     * @param columnIndex O índice da coluna.
     * @return O valor a ser exibido na célula especificada.
     */
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
                return "Autor(es)";
            case 2:
                return "Categoria";
            case 3:
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
