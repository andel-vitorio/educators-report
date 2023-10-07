package res.values;

import java.awt.Color;

/**
 * Classe responsável por gerenciar as cores utilizadas na aplicação.
 */
public class ColorsManager {

    private static final Color BACKGROUND_COLOR = new Color(0xF4, 0xF4, 0xF4);
    private static final Color ON_BACKGROUND_COLOR = new Color(0xFF, 0xFF, 0xFF);
    private static final Color BORDER_COLOR = new Color(0x00, 0x00, 0x00, 0x1F);
    private static final Color TEXTFIELD_BORDER_COLOR = new Color(0x00, 0x00, 0x00, 0x3E);
    private static final Color TRANSPARENT = new Color(0x00, 0x00, 0x00, 0x00);
    private static final Color TEXT_COLOR_DARK = new Color(0, 0, 0, 0xAC);
    private static final Color TEXT_COLOR_LIGHT = new Color(0xFF, 0xFF, 0xFF);
    private static final Color BUTTON_BACKGROUND_PRIMARY = new Color(0x4A, 0X62, 0x68);
    private static final Color BUTTON_BACKGROUND_SECONDARY = new Color(0x00, 0x00, 0x00, 0x0D);

    /**
     * Obtém a cor de fundo padrão da aplicação.
     *
     * @return A cor de fundo.
     */
    public static Color getBackgroundColor() {
        return BACKGROUND_COLOR;
    }

    /**
     * Obtém a cor principal para elementos na tela.
     *
     * @return A cor principal.
     */
    public static Color getOnBackgroundColor() {
        return ON_BACKGROUND_COLOR;
    }

    /**
     * Obtém a cor da borda utilizada em elementos.
     *
     * @return A cor da borda.
     */
    public static Color getBorderColor() {
        return BORDER_COLOR;
    }

    /**
     * Obtém uma cor transparente.
     *
     * @return A cor transparente.
     */
    public static Color getTransparent() {
        return TRANSPARENT;
    }

    /**
     * Obtém a cor do texto em modo escuro.
     *
     * @return A cor do texto em modo escuro.
     */
    public static Color getTextColorDark() {
        return TEXT_COLOR_DARK;
    }

    /**
     * Obtém a cor de fundo primária para botões.
     *
     * @return A cor de fundo primária.
     */
    public static Color getButtonBackgroundPrimary() {
        return BUTTON_BACKGROUND_PRIMARY;
    }

    /**
     * Obtém a cor do texto em modo claro.
     *
     * @return A cor do texto em modo claro.
     */
    public static Color getTextColorLight() {
        return TEXT_COLOR_LIGHT;
    }

    /**
     * Obtém a cor da borda de campos de texto.
     *
     * @return A cor da borda de campos de texto.
     */
    public static Color getTextfieldBorderColor() {
        return TEXTFIELD_BORDER_COLOR;
    }

    /**
     * Obtém a cor de fundo secundária para botões.
     *
     * @return A cor de fundo secundária.
     */
    public static Color getButtonBackgroundSecondary() {
        return BUTTON_BACKGROUND_SECONDARY;
    }
}
