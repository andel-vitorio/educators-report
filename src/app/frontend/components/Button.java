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

	/**
     * Classe interna que armazena informações associadas a um botão, como rótulo, ícone e ouvinte de ação.
     */
	public static class ButtonInfo {
		private String label;
		private ImageIcon icon;
		ActionListener actionListener;

		/**
         * Construtor vazio da classe ButtonInfo.
         */
		public ButtonInfo() {}

		/**
         * Construtor que aceita um rótulo e um ícone.
         *
         * @param label O rótulo do botão.
         * @param icon  O ícone do botão.
         */
		public ButtonInfo(String label, ImageIcon icon) {
			this.label = label;
			this.icon = icon;
		}

		/**
         * Construtor que aceita um rótulo, um ícone e um ouvinte de ação.
         *
         * @param label         O rótulo do botão.
         * @param icon          O ícone do botão.
         * @param actionListener O ouvinte de ação do botão.
         */
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

	 /**
     * Classe interna que cria uma borda com cantos arredondados para o botão.
     */
    private static class RoundedBorder extends AbstractBorder {
        private int radius;

				/**
         * Construtor que aceita o raio dos cantos arredondados.
         *
         * @param radius O raio dos cantos arredondados.
         */
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


    /**
     * Enumeração que define o tipo de botão, como "BASIC" ou "FILLED".
     */
	public enum ButtonType {
		BASIC,
		FILLED
	}


    /**
     * Enumeração que define o tipo de botão, como "BASIC" ou "FILLED".
     */
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
				setBackground(ColorsManager.getButtonBackgroundPrimary());
				setForeground(ColorsManager.getTextColorLight());
				setFont(FontsManager.getFont(FontType.MEDIUM, DimensManager.getButtonFontsizeSmall()));
				ComponentDecorator.addPadding(this, 16);
				break;
			}

			default:
				break; 
		}
	}

	/**
     * Construtor para criar um botão com um tipo específico, rótulo e cor de fundo.
     *
     * @param type           O tipo de botão (BASIC ou FILLED).
     * @param label          O rótulo do botão.
     * @param backgroundColor A cor de fundo do botão.
     */
	public Button(ButtonType type, String label, Color backgroundColor) {
		this(type, label);
		setBackground(backgroundColor);
	}

	/**
     * Define um ícone para o botão.
     *
     * @param icon O ícone a ser definido para o botão.
     */
	public void setIcon(ImageIcon icon) {
		super.setIcon(icon);
		setIconTextGap(DimensManager.getIconTextGap());
	}

	/**
     * Adiciona uma borda arredondada ao botão.
     *
     * @param radius O raio dos cantos arredondados da borda.
     */
	public void addRoundedBorder(int radius) {
		setBorder(new RoundedBorder(radius));
	}

}