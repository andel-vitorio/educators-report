package app.frontend.components;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import app.frontend.components.Button.ButtonType;
import res.fonts.FontsManager;
import res.fonts.FontsManager.FontType;
import res.values.ColorsManager;
import res.values.DimensManager;
import utils.ComponentDecorator;
import utils.Observable;

/**
 * Um painel de navegação personalizado que exibe botões de navegação.
 */
public class Navigation extends JPanel implements ActionListener {
    private JButton activedButton;
    private int qButton;
    int width;

    private Observable observable;

    /**
     * Cria um painel de navegação com a largura e altura especificadas.
     *
     * @param width          A largura do painel de navegação.
     * @param height         A altura do painel de navegação.
     * @param actionListener Um ouvinte de ação para os botões de navegação.
     */
    public Navigation(int width, int height, ActionListener actionListener) {
        JLabel titleLabel = new JLabel("MENU");

        setLayout(new GridBagLayout());
        setPreferredSize(new Dimension(width, height));
        setMaximumSize(getPreferredSize());
        setOpaque(false);

        int fillHorizontal = GridBagConstraints.HORIZONTAL;
        this.width = width;

        GridBagConstraints gbcTitleLabel = getButtonConstraints(fillHorizontal, 1.0, 1, 0, 0);

        ComponentDecorator.addPadding(titleLabel, 48, 24, 0, 24);

        if (!FontsManager.getLoadError()) {
            titleLabel.setFont(FontsManager.getFont(FontType.MEDIUM, DimensManager.getTitleSize()));
        }

        add(titleLabel, gbcTitleLabel);

        observable = new Observable();
    }

    /**
     * Define um item de navegação com um rótulo, um ícone e uma tag associada.
     *
     * @param label O rótulo do item de navegação.
     * @param icon  O ícone do item de navegação.
     * @param tag   A tag associada ao item de navegação.
     */
    public void setItem(String label, ImageIcon icon, String tag) {
        Button button = new Button(ButtonType.BASIC, label, ColorsManager.getOnBackgroundColor());
        button.setHorizontalAlignment(SwingConstants.LEFT);
        button.setIcon(icon);
        button.setMinimumSize(new Dimension(width, 48));

        if (!FontsManager.getLoadError()) button.setFont(FontsManager.getFont(FontType.REGULAR, DimensManager.getButtonFontsizeMedium()));

        if (qButton == 0) {
            if (!FontsManager.getLoadError()) button.setFont(FontsManager.getFont(FontType.SEMI_BOLD, (int) (1.1 * DimensManager.getButtonFontsizeMedium())));
            activedButton = button;
        }

        int fillHorizontal = GridBagConstraints.HORIZONTAL;

        GridBagConstraints gbcButton1 = getButtonConstraints(fillHorizontal, 1.0, 2, 0, qButton + 1);
        gbcButton1.insets = new Insets(16, 0, 0, 0);
        gbcButton1.weighty = 1.5;

        add(button, gbcButton1);
        qButton++;

        button.setActionCommand(tag);
        button.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!FontsManager.getLoadError()) activedButton.setFont(FontsManager.getFont(FontType.REGULAR, DimensManager.getButtonFontsizeMedium()));
        activedButton = (JButton) e.getSource();
        observable.notifyObservers(e.getActionCommand());
        if (!FontsManager.getLoadError()) activedButton.setFont(FontsManager.getFont(FontType.SEMI_BOLD, (int) (1.1 * DimensManager.getButtonFontsizeMedium())));
    }

    private GridBagConstraints getButtonConstraints(int fill, double weightx, int gridwidth, int gridx, int gridy) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = fill;
        gbc.weightx = weightx;
        gbc.gridwidth = gridwidth;
        gbc.gridx = gridx;
        gbc.gridy = gridy;
        return gbc;
    }

    /**
     * Obtém o objeto observável associado ao painel de navegação.
     *
     * @return O objeto observável.
     */
    public Observable getObservable() {
        return observable;
    }
}
