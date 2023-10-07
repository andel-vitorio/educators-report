package res.fonts;

import java.awt.*;
import java.io.*;

/**
 * Classe responsável pelo gerenciamento de fontes utilizadas na aplicação.
 */
public final class FontsManager {
    private static Font bold;
    private static Font semiBold;
    private static Font regular;
    private static Font medium;
    private static boolean loadError;

    /**
     * Enumeração que representa os tipos de fontes disponíveis.
     */
    public enum FontType {
        SEMI_BOLD, REGULAR, MEDIUM, BOLD
    }

    /**
     * Carrega as fontes a partir dos arquivos de fonte especificados.
     *
     * @return true se as fontes forem carregadas com sucesso, false caso contrário.
     */
    public static boolean load() {
        String path = FontsManager.class.getResource("/res/fonts").getFile();

        try {
            semiBold = createFont(path, FontType.SEMI_BOLD);
            regular = createFont(path, FontType.REGULAR);
            medium = createFont(path, FontType.MEDIUM);
            bold = createFont(path, FontType.BOLD);
            loadError = false;
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
            loadError = true;
        }

        return loadError;
    }

    private static Font createFont(String path, FontType fontType) throws IOException, FontFormatException {
        File fontFile;
        switch (fontType) {
            case SEMI_BOLD:
                fontFile = new File(path + "/Poppins-SemiBold.ttf");
                break;
            case REGULAR:
                fontFile = new File(path + "/Poppins-Regular.ttf");
                break;
            case MEDIUM:
                fontFile = new File(path + "/Poppins-Medium.ttf");
                break;
            case BOLD:
                fontFile = new File(path + "/Poppins-Bold.ttf");
                break;
            default:
                throw new IllegalArgumentException("Tipo de fonte não suportado");
        }

        try (InputStream fontStream = new FileInputStream(fontFile)) {
            return Font.createFont(Font.TRUETYPE_FONT, fontStream);
        }
    }

    /**
     * Obtém o estado de erro de carregamento das fontes.
     *
     * @return true se houver um erro no carregamento das fontes, false caso contrário.
     */
    public static boolean getLoadError() {
        return loadError;
    }

    /**
     * Obtém uma instância de fonte com o tipo e tamanho especificados.
     *
     * @param fontType O tipo de fonte desejado.
     * @param size     O tamanho da fonte.
     * @return Uma instância de Font com o tipo e tamanho especificados, ou null se a fonte não estiver disponível.
     */
    public static Font getFont(FontType fontType, int size) {
        Font font = null;
        switch (fontType) {
            case SEMI_BOLD:
                font = semiBold;
                break;
            case REGULAR:
                font = regular;
                break;
            case MEDIUM:
                font = medium;
            case BOLD:
                font = bold;
                break;
        }

        if (font != null) {
            return font.deriveFont(Font.PLAIN, size);
        } else {
            return null;
        }
    }
}
