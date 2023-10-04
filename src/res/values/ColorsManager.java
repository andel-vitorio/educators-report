package res.values;

import java.awt.Color;

public class ColorsManager {
		
	private static final Color BACKGROUND_COLOR = new Color(0xF4, 0xF4, 0xF4);
	private static final Color ON_BACKGROUND_COLOR = new Color(0xFF, 0xFF, 0xFF);
	private static final Color BORDER_COLOR = new Color(0x00, 0x00, 0x00, 0x1F);
	private static final Color TEXTFIELD_BORDER_COLOR = new Color(0x00, 0x00, 0x00, 0x3E);
	private static final Color TRANSPARENT = new Color(0x00, 0x00, 0x00, 0x00);
	private static final Color TEXT_COLOR_DARK = new Color(0, 0, 0, 0xAC);
	private static final Color TEXT_COLOR_LIGHT = new Color(0xFF, 0xFF, 0xFF);
	private static final Color BUTTON_BACKGROUND_PRIMARY = new Color(0x4A, 0X62, 0x68);
	private static final Color BUTTON_BACKGROUND_SECONDARY = new Color(0x00, 0x00, 0x00,0x0D);

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

	public static Color getTextColorDark() {
		return TEXT_COLOR_DARK;
	}

	public static Color getButtonBackgroundPrimary() {
		return BUTTON_BACKGROUND_PRIMARY;
	}

	public static Color getTextColorLight() {
		return TEXT_COLOR_LIGHT;
	}

	public static Color getTextfieldBorderColor() {
		return TEXTFIELD_BORDER_COLOR;
	}

	public static Color getButtonBackgroundSecondary() {
		return BUTTON_BACKGROUND_SECONDARY;
	}
}
