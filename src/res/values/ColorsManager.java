package res.values;

import java.awt.Color;

public class ColorsManager {
		
	private static final Color BACKGROUND_COLOR = new Color(0xF4, 0xF4, 0xF4);
	private static final Color ON_BACKGROUND_COLOR = new Color(0xFF, 0xFF, 0xFF);
	private static final Color BORDER_COLOR = new Color(0x00, 0x00, 0x00, 0x1F);
	private static final Color TRANSPARENT = new Color(0x00, 0x00, 0x00, 0x00);
	private static final Color TEXT_COLOR_DARK_2 = new Color(0, 0, 0, 0xAC);

	public static Color getBackgroundColor() {
		return BACKGROUND_COLOR;
	}

	public static Color getOnBackgroundColor() {
		return ON_BACKGROUND_COLOR;
	}

	public static Color getBorderColor() {
		return BORDER_COLOR;
	}

	public static Color getTransparent() {
		return TRANSPARENT;
	}

	public static Color getTextColorDark2() {
		return TEXT_COLOR_DARK_2;
	}
}
