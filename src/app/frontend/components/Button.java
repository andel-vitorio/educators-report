package app.frontend.components;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import res.fonts.FontsManager;
import res.fonts.FontsManager.FontType;
import res.values.DimensManager;

public class Button extends JButton {

	public static class ButtonInfo {
		private String label;
		private ImageIcon icon;

		public ButtonInfo() {}

		public ButtonInfo(String label, ImageIcon icon) {
			this.label = label;
			this.icon = icon;
		}

		public void setIcon(ImageIcon icon) {
			this.icon = icon;
		}

		public void setLabel(String label) {
			this.label = label;
		}

		public ImageIcon getIcon() {
			return icon;
		}

		public String getLabel() {
			return label;
		}
	}

	public enum ButtonType {
		BASIC
	}

	public Button(ButtonType type, String label) {
		super(label);

		switch(type) {
			case BASIC: {
				setBorderPainted(false);
				setFocusPainted(false);
				setOpaque(true);
				// setBackground(Color.BLACK);
				// setForeground(Color.WHITE);
				setFont(FontsManager.getFont(FontType.MEDIUM, DimensManager.getButtonFontSize()));
				break;
			} 
		}
	}

	public Button(ButtonType type, String label, Color backgroundColor) {
		this(type, label);
		setBackground(backgroundColor);
	}


}