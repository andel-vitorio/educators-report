package app.frontend.components;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import app.frontend.components.Button.ButtonType;
import app.frontend.components.Button.ButtonInfo;
import res.fonts.FontsManager;
import res.fonts.FontsManager.FontType;
import res.values.ColorsManager;
import utils.ComponentDecorator;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;
import java.util.ArrayList;

/**
 * Uma barra superior personalizada que pode exibir um título e botões de ação.
 */
public class TopBar extends JPanel {

    private JLabel titleLabel;
    private Button button;

    ArrayList<Button> actionsButtons;

    /**
     * Cria uma nova instância da classe TopBar com um título especificado.
     *
     * @param title O título a ser exibido na barra superior.
     */
    public TopBar(String title) {
        setLayout(new BorderLayout());
        ComponentDecorator.addPadding(this, 12, 24);

        titleLabel = new JLabel(title);

        if (!FontsManager.getLoadError())
            titleLabel.setFont(FontsManager.getFont(FontType.SEMI_BOLD, 16));

        add(titleLabel, BorderLayout.LINE_START);
    }

    /**
     * Define um botão de ação na barra superior com um rótulo, ícone e ouvinte de ação especificados.
     *
     * @param label    O rótulo do botão.
     * @param icon     O ícone do botão.
     * @param listener O ouvinte de ação para o botão.
     */
    public void setActionButton(String label, ImageIcon icon, ActionListener listener) {

        JPanel container = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;

                int width = getWidth();
                int height = getHeight();

                setBackground(ColorsManager.getOnBackgroundColor());

                RoundRectangle2D roundedRectangle = new RoundRectangle2D.Float(0, 0, width, height, 24, 24);

                g2d.setColor(ColorsManager.getButtonBackgroundPrimary());
                g2d.fill(roundedRectangle);
            }
        };

        button = new Button(ButtonType.BASIC, label);
        button.setIcon(icon);
        button.addActionListener(listener);
        button.setForeground(ColorsManager.getTextColorLight());
        button.setBorder(null);
        ComponentDecorator.addPadding(button, 12, 16);

        container.setLayout(new BorderLayout());
        container.add(button, BorderLayout.CENTER);

        add(container, BorderLayout.LINE_END);
    }

    /**
     * Define botões de ação na barra superior com base em uma lista de informações de botões.
     *
     * @param buttonInfoList A lista de informações de botões.
     */
    public void setActionButtons(ArrayList<ButtonInfo> buttonInfoList) {
        JPanel container = new JPanel(new FlowLayout(FlowLayout.RIGHT, 24, 0));
        container.setOpaque(false);
        actionsButtons = new ArrayList<>();

        for (ButtonInfo buttonInfo : buttonInfoList) {
            JPanel buttonContainer = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    Graphics2D g2d = (Graphics2D) g;

                    int width = getWidth();
                    int height = getHeight();

                    setBackground(ColorsManager.getOnBackgroundColor());

                    RoundRectangle2D roundedRectangle = new RoundRectangle2D.Float(0, 0, width, height, 24, 24);

                    if (buttonInfo.getBackgroundColor() != null)
                        g2d.setColor(buttonInfo.getBackgroundColor());
                    else
                        g2d.setColor(ColorsManager.getButtonBackgroundPrimary());

                    g2d.fill(roundedRectangle);
                }
            };

            Button button = new Button(ButtonType.BASIC, buttonInfo.getLabel());
            button.setIcon(buttonInfo.getIcon());
            button.addActionListener(buttonInfo.getActionListener());
            button.setForeground(ColorsManager.getTextColorLight());
            button.setBorder(null);
            ComponentDecorator.addPadding(button, 8, 16);

            buttonContainer.add(button);

            actionsButtons.add(button);

            container.add(buttonContainer);
        }

        add(container, BorderLayout.LINE_END);
    }

    /**
     * Obtém a lista de botões de ação na barra superior.
     *
     * @return A lista de botões de ação.
     */
    public ArrayList<Button> getActionsButtons() {
        return actionsButtons;
    }
}
