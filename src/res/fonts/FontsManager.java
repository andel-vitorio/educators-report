package res.fonts;

import java.awt.*;
import java.io.*;

public final class FontsManager {
    private static Font semiBold;
    private static Font regular;
    private static Font medium;
    private static boolean loadError;

    public enum FontType {
        SEMI_BOLD, REGULAR, MEDIUM
    }

    public static boolean load() {
        String path = FontsManager.class.getResource("/res/fonts").getFile();

        try {
            semiBold = createFont(path, FontType.SEMI_BOLD);
            regular = createFont(path, FontType.REGULAR);
            medium = createFont(path, FontType.MEDIUM);
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
            default:
                throw new IllegalArgumentException("Font type not supported");
        }

        try (InputStream fontStream = new FileInputStream(fontFile)) {
            return Font.createFont(Font.TRUETYPE_FONT, fontStream);
        }
    }

    public static boolean getLoadError() {
        return loadError;
    }

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
                break;
        }

        if (font != null) {
            return font.deriveFont(Font.PLAIN, size);
        } else {
            return null;
        }
    }
}
