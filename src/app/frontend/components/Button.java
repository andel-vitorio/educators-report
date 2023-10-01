package app.frontend.components;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.border.AbstractBorder;

import res.fonts.FontsManager;
import res.fonts.FontsManager.FontType;
import res.values.ColorsManager;
import res.values.DimensManager;
import utils.ComponentDecorator;

public class Button extends JButton {

	public static class ButtonInfo {
		private String label;
		private ImageIcon icon;
		ActionListener actionListener;

		public ButtonInfo() {}

		public ButtonInfo(String label, ImageIcon icon) {
			this.label = label;
			this.icon = icon;
		}

		public ButtonInfo(String label, ImageIcon icon, ActionListener action) {
			this(label, icon);
			this.actionListener = action;
		}

		public void setIcon(ImageIcon icon) {
			this.icon = icon;
		}

		public void setLabel(String label) {
			this.label = label;
		}

		public void setActionListener(ActionListener actionListener) {
			this.actionListener = actionListener;
		}

		public ImageIcon getIcon() {
			return icon;
		}

		public String getLabel() {
			return label;
		}

		public ActionListener getActionListener() {
			return actionListener;
		}
	}

	 // Classe personalizada para criar uma borda com cantos arredondados
    private static class RoundedBorder extends AbstractBorder {
        private int radius;

        public RoundedBorder(int radius) {
            this.radius = radius;
        }

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            Graphics2D g2d = (Graphics2D) g.create();

            // Desenhe a borda com cantos arredondados
            g2d.setColor(c.getForeground());
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            int d = radius * 2;
            g2d.drawRoundRect(x, y, width - 1, height - 1, d, d);

            g2d.dispose();
        }

        @Override
        public Insets getBorderInsets(Component c) {
            int borderWidth = radius;
            return new Insets(borderWidth, borderWidth, borderWidth, borderWidth);
        }
    }

	public enum ButtonType {
		BASIC,
		FILLED
	}

	public Button(ButtonType type, String label) {
		super(label);

		switch(type) {
			case BASIC: {
				setBorderPainted(false);
				setFocusPainted(false);
				setOpaque(false);
				setContentAreaFilled(false);
				setFont(FontsManager.getFont(FontType.MEDIUM, DimensManager.getButtonFontsizeSmall()));
				break;
			}

			case FILLED: {
				setBorderPainted(true);
				setFocusPainted(false);
				setOpaque(false);
				setBackground(ColorsManager.getButtonBackground());
				setForeground(ColorsManager.getTextColorLight());
				setFont(FontsManager.getFont(FontType.MEDIUM, DimensManager.getButtonFontsizeSmall()));
				ComponentDecorator.addPadding(this, 16);
				break;
			}

			default:
				break; 
		}
	}

	public Button(ButtonType type, String label, Color backgroundColor) {
		this(type, label);
		setBackground(backgroundColor);
	}

	public void setIcon(ImageIcon icon) {
		super.setIcon(icon);
		setIconTextGap(DimensManager.getIconTextGap());
	}

	public void addRoundedBorder(int radius) {
		setBorder(new RoundedBorder(radius));
	}

}