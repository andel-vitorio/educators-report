package res.values;

import java.awt.Color;

public class ColorsManager {
		
	private static final Color BACKGROUND_COLOR = new Color(0xF4, 0xF4, 0xF4);
	private static final Color ON_BACKGROUND_COLOR = new Color(0xFF, 0xFF, 0xFF);
	private static final Color BORDER_COLOR = new Color(0x00, 0x00, 0x00, 0x1F);

	public static Color getBackgroundColor() {
		return BACKGROUND_COLOR;
	}

	public static Color getOnBackgroundColor() {
		return ON_BACKGROUND_COLOR;
	}

	public static Color getBorderColor() {
		return BORDER_COLOR;
	}
}
