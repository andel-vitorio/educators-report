package app.frontend.components;

import java.awt.*;

import javax.swing.*;

import res.fonts.FontsManager;
import res.fonts.FontsManager.FontType;
import res.values.*;
import utils.ComponentDecorator;

/**
 * Uma barra lateral personalizável para aplicativos.
 */
public class SideBar {

    private JPanel component = new JPanel();
    private int widthOfSideBar;

    /**
     * Cria uma barra lateral personalizável com a largura e altura especificadas.
     *
     * @param width  A largura da barra lateral.
     * @param height A altura da barra lateral.
     */
    public SideBar(int width, int height) {
        this.widthOfSideBar = width;

        component.setSize(width, height);
        component.setPreferredSize(new Dimension(width, height));
        component.setLayout(new BoxLayout(component, BoxLayout.PAGE_AXIS));

        ComponentDecorator.addBorderRight(component, 1);
    }

    /**
     * Define a cor de fundo da barra lateral.
     *
     * @param color A cor de fundo desejada.
     * @return Esta instância de `SideBar`.
     */
    public SideBar setBackgroundColor(Color color) {
        component.setBackground(color);
        return this;
    }

    /**
     * Define o cabeçalho da barra lateral com um título, um subtítulo e um ícone.
     *
     * @param title    O título do cabeçalho.
     * @param subtitle O subtítulo do cabeçalho.
     * @param icon     O ícone a ser exibido no cabeçalho.
     * @return Esta instância de `SideBar`.
     */
    public SideBar setHeader(String title, String subtitle, ImageIcon icon) {
        JPanel container = new JPanel();
        JPanel header = new JPanel();
        JLabel titleLabel = new JLabel(title);
        JLabel subtitleLabel = new JLabel(subtitle);
        JLabel iconLabel = new JLabel(icon);

        if (!FontsManager.getLoadError()) {
            titleLabel.setFont(FontsManager.getFont(FontType.SEMI_BOLD, DimensManager.getTitleSize()));
            subtitleLabel.setFont(FontsManager.getFont(FontType.MEDIUM, DimensManager.getSubtitleSize()));
        }

        container.setLayout(new FlowLayout(FlowLayout.LEFT, 24, 24));
        container.setOpaque(false);
        container.setPreferredSize(new Dimension(widthOfSideBar, 64));
        container.setMaximumSize(container.getPreferredSize());

        header.setLayout(new BoxLayout(header, BoxLayout.PAGE_AXIS));
        header.setOpaque(false);
        header.add(titleLabel);
        header.add(subtitleLabel);

        container.add(iconLabel);
        container.add(header);
        component.add(container, BorderLayout.PAGE_START);

        return this;
    }

    /**
     * Define a navegação na barra lateral.
     *
     * @param navigation O objeto `Navigation` que representa a navegação na barra lateral.
     * @return Esta instância de `SideBar`.
     */
    public SideBar setNavigation(Navigation navigation) {
        component.add(navigation);
        return this;
    }

    /**
     * Obtém o componente da barra lateral.
     *
     * @return O componente `JPanel` da barra lateral.
     */
    public JPanel getComponent() {
        return component;
    }
}
