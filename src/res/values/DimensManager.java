package res.values;

/**
 * Classe responsável por gerenciar as dimensões utilizadas na aplicação, como tamanhos de fontes, espaçamentos e alturas.
 */
public class DimensManager {
    
    private static final int TITLE_SIZE = 16;   
    private static final int SUBTITLE_SIZE = 12;
    private static final int ICON_TEXT_GAP = 8;
    
    private static final int BUTTON_FONTSIZE_MEDIUM = 16;
    private static final int BUTTON_FONTSIZE_SMALL = 12;
    private static final int ROW_HEIGHT = 40;

    private static final int TABLE_HEADER_TEXTSIZE = 12;
    private static final int TABLE_ITEM_TEXTSIZE = 12;
    
    private static final int TITLE_FORMS_FONTSIZE = 18;   
    private static final int SECTION_LABEL_FONTSIZE = 14;

    /**
     * Obtém o tamanho da fonte para títulos.
     *
     * @return O tamanho da fonte para títulos.
     */
    public static int getTitleSize() {
        return TITLE_SIZE;
    }

    /**
     * Obtém o tamanho da fonte para subtítulos.
     *
     * @return O tamanho da fonte para subtítulos.
     */
    public static int getSubtitleSize() {
        return SUBTITLE_SIZE;
    }
    
    /**
     * Obtém o tamanho da fonte média para botões e elementos.
     *
     * @return O tamanho da fonte média.
     */
    public static int getButtonFontsizeMedium() {
        return BUTTON_FONTSIZE_MEDIUM;
    }

    /**
     * Obtém o espaçamento entre ícones e texto.
     *
     * @return O espaçamento entre ícones e texto.
     */
    public static int getIconTextGap() {
        return ICON_TEXT_GAP;
    }

    /**
     * Obtém o tamanho da fonte pequena para botões e elementos.
     *
     * @return O tamanho da fonte pequena.
     */
    public static int getButtonFontsizeSmall() {
        return BUTTON_FONTSIZE_SMALL;
    }

    /**
     * Obtém o tamanho da fonte para cabeçalhos de tabelas.
     *
     * @return O tamanho da fonte para cabeçalhos de tabelas.
     */
    public static int getTableHeaderTextsize() {
        return TABLE_HEADER_TEXTSIZE;
    }

    /**
     * Obtém o tamanho da fonte para itens de tabela.
     *
     * @return O tamanho da fonte para itens de tabela.
     */
    public static int getTableItemTextsize() {
        return TABLE_ITEM_TEXTSIZE;
    }

    /**
     * Obtém a altura das linhas de tabelas.
     *
     * @return A altura das linhas de tabelas.
     */
    public static int getRowHeight() {
        return ROW_HEIGHT;
    }

    /**
     * Obtém o tamanho da fonte para títulos de formulários.
     *
     * @return O tamanho da fonte para títulos de formulários.
     */
    public static int getTitleFormsFontsize() {
        return TITLE_FORMS_FONTSIZE;
    }

    /**
     * Obtém o tamanho da fonte para rótulos de seção.
     *
     * @return O tamanho da fonte para rótulos de seção.
     */
    public static int getSectionLabelFontsize() {
        return SECTION_LABEL_FONTSIZE;
    }
}
