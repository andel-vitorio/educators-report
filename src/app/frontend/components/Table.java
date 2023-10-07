package app.frontend.components;

import javax.swing.*;
import java.awt.*;
import java.util.EventObject;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumn;

import res.fonts.FontsManager;
import res.fonts.FontsManager.FontType;
import res.values.ColorsManager;
import res.values.DimensManager;
import utils.ComponentDecorator;

/**
 * Uma tabela personalizável para exibir dados tabulares.
 */
public class Table extends JPanel {
  private JTable table;

  private static final Color COLOR_ON_BACKGROUND = Color.WHITE;

  /**
   * Define o alinhamento horizontal das células da tabela.
   */
  public enum CellContentAlignment {
    START, CENTER, END
  }

  /**
   * Um renderizador de célula personalizado para a tabela.
   */
  public static class CellRenderer extends DefaultTableCellRenderer {
    private Component component;

    /**
     * Cria um novo renderizador de célula personalizado.
     *
     * @param component O componente a ser renderizado.
     */
    public CellRenderer(Component component) {
      this.component = component;
      setHorizontalAlignment(SwingConstants.LEFT);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
        int row, int column) {
      if (isSelected) {
        component.setBackground(table.getSelectionBackground());
      } else {
        component.setBackground(table.getBackground());
      }

      // Verifica se esta é a linha selecionada e define a visibilidade
      boolean isRowSelected = table.getSelectionModel().isSelectedIndex(row);
      component.setVisible(isRowSelected);

      return component;
    }
  }

 /**
 * Um editor de célula personalizado para a tabela.
 */
public static class CellEditor extends AbstractCellEditor implements TableCellEditor {

    private Component component;

    /**
     * Cria um novo editor de célula personalizado.
     *
     * @param component O componente a ser editado.
     */
    public CellEditor(Component component) {
        this.component = component;
    }

    @Override
    public Object getCellEditorValue() {
        return null;
    }

    @Override
    public boolean isCellEditable(EventObject e) {
        return true;
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        component.setBackground(table.getSelectionBackground());

        // Certifique-se de que o componente seja visível durante a edição
        component.setVisible(true);

        return component;
    }
}


  /**
   * Cria uma nova tabela personalizada com o modelo de tabela especificado.
   *
   * @param tableModel O modelo de tabela a ser usado na tabela.
   */
  public Table(AbstractTableModel tableModel) {
    DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
    renderer.setHorizontalAlignment(SwingConstants.LEFT);

    table = new JTable(tableModel);
    table.setBackground(ColorsManager.getOnBackgroundColor());
    table.setShowVerticalLines(false);
    table.setRowHeight(DimensManager.getRowHeight());

    for (int i = 0; i < table.getColumnCount(); i++)
      table.getColumnModel().getColumn(i).setCellRenderer(renderer);

    JTableHeader tableHeader = table.getTableHeader();
    tableHeader.setBackground(COLOR_ON_BACKGROUND);
    tableHeader.setPreferredSize(new Dimension(0, 40));
    tableHeader.setSize(tableHeader.getPreferredSize());
    tableHeader.setDefaultRenderer(renderer);
    ComponentDecorator.addBorderBottom(tableHeader, 1, Color.BLACK);

    if (!FontsManager.getLoadError()) {
      tableHeader.setFont(FontsManager.getFont(FontType.REGULAR, DimensManager.getTableHeaderTextsize()));
      table.setFont(FontsManager.getFont(FontType.REGULAR, DimensManager.getTableItemTextsize()));
    }

    ComponentDecorator.addPadding(table, 10);
    JScrollPane tableScroll = new JScrollPane(table);
    tableScroll.setBorder(BorderFactory.createEmptyBorder());
    tableScroll.getViewport().setBackground(ColorsManager.getOnBackgroundColor());

    setLayout(new BorderLayout());
    add(tableScroll, BorderLayout.CENTER);
  }

  /**
   * Define um editor de célula personalizado e um renderizador de célula
   * personalizado para uma coluna específica.
   *
   * @param columnIndex    O índice da coluna a ser personalizada.
   * @param cellEditor     O editor de célula personalizado.
   * @param columnRenderer O renderizador de célula personalizado.
   */
  public void setCustomColumn(int columnIndex, CellEditor cellEditor, CellRenderer columnRenderer) {
    table.getColumnModel().getColumn(columnIndex).setCellEditor(cellEditor);
    table.getColumnModel().getColumn(columnIndex).setCellRenderer(columnRenderer);
  }

  /**
   * Define a largura de uma coluna específica.
   *
   * @param columnIndex O índice da coluna cuja largura deve ser definida.
   * @param width       A largura da coluna.
   */
  public void setColumnWidth(int columnIndex, int width) {
    TableColumn column = table.getColumnModel().getColumn(columnIndex);
    column.setPreferredWidth(width);
    column.setMaxWidth(column.getPreferredWidth());
    column.setMaxWidth(column.getPreferredWidth());
  }

  /**
   * Define o alinhamento horizontal das células de uma coluna específica.
   *
   * @param columnIndex O índice da coluna cujas células devem ter o alinhamento
   *                    horizontal definido.
   * @param alignment   O alinhamento horizontal desejado.
   */
  public void setHorizontalAlignment(int columnIndex, CellContentAlignment alignment) {
    TableColumn column = table.getColumnModel().getColumn(columnIndex);
    DefaultTableCellRenderer align = new DefaultTableCellRenderer();

    switch (alignment) {
      case END: {
        align.setHorizontalAlignment(SwingConstants.RIGHT);
        break;
      }

      default: {
        align.setHorizontalAlignment(SwingConstants.LEFT);
        break;
      }
    }

    column.setCellRenderer(align);
  }

  /**
   * Obtém o componente da tabela.
   *
   * @return O componente `JTable` da tabela personalizada.
   */
  public JTable getComponent() {
    return table;
  }
}
