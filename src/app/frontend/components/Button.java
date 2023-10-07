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

/**
 * Um botão personalizado que pode ser configurado com diferentes estilos.
 */
public class Button extends JButton {

    /**
     * Classe interna que armazena informações associadas a um botão, como rótulo, ícone e ouvinte de ação.
     */
    public static class ButtonInfo {
        private String label;
        private ImageIcon icon;
        ActionListener actionListener;
        Color backgroundColor;

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

        /**
         * Construtor que aceita um rótulo, uma cor de fundo, um ícone e um ouvinte de ação.
         *
         * @param label          O rótulo do botão.
         * @param background     A cor de fundo do botão.
         * @param icon           O ícone do botão.
         * @param actionListener O ouvinte de ação do botão.
         */
        public ButtonInfo(String label, Color background, ImageIcon icon, ActionListener action) {
            this(label, icon);
            this.backgroundColor = background;
            this.actionListener = action;
        }

        /**
         * Define o ícone do botão.
         *
         * @param icon O ícone a ser definido para o botão.
         */
        public void setIcon(ImageIcon icon) {
            this.icon = icon;
        }

        /**
         * Define o rótulo do botão.
         *
         * @param label O rótulo a ser definido para o botão.
         */
        public void setLabel(String label) {
            this.label = label;
        }

        /**
         * Define o ouvinte de ação do botão.
         *
         * @param actionListener O ouvinte de ação a ser definido para o botão.
         */
        public void setActionListener(ActionListener actionListener) {
            this.actionListener = actionListener;
        }

        /**
         * Define a cor de fundo do botão.
         *
         * @param backgroundColor A cor de fundo a ser definida para o botão.
         */
        public void setBackgroundColor(Color backgroundColor) {
            this.backgroundColor = backgroundColor;
        }

        /**
         * Obtém o ícone do botão.
         *
         * @return ImageIcon - o ícone do botão.
         */
        public ImageIcon getIcon() {
            return icon;
        }

        /**
         * Obtém o rótulo do botão.
         *
         * @return String - o rótulo do botão.
         */
        public String getLabel() {
            return label;
        }

        /**
         * Obtém o ouvinte de ação do botão.
         *
         * @return ActionListener - o ouvinte de ação do botão.
         */
        public ActionListener getActionListener() {
            return actionListener;
        }

        /**
         * Obtém a cor de fundo do botão.
         *
         * @return Color - a cor de fundo do botão.
         */
        public Color getBackgroundColor() {
            return backgroundColor;
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
     * Cria um novo botão com o tipo, rótulo e estilo especificados.
     *
     * @param type  O tipo de botão (BASIC ou FILLED).
     * @param label O rótulo do botão.
     */
    public Button(ButtonType type, String label) {
        super(label);

        switch (type) {
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
     * Cria um novo botão com o tipo, rótulo, cor de fundo e estilo especificados.
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
