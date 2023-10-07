package app.frontend.components;

import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JPanel;

import app.frontend.components.Button.ButtonInfo;
import app.frontend.components.Button.ButtonType;

/**
 * Um painel que contém botões de ação.
 * Esta classe cria um painel com botões com base nas informações fornecidas.
 */
public class ActionsButtons extends JPanel {

    /**
     * Cria um novo painel de botões de ação com base nas informações fornecidas.
     *
     * @param buttonsInfo Uma lista de informações sobre os botões a serem criados.
     */
    public ActionsButtons(ArrayList<ButtonInfo> buttonsInfo) {
        setLayout(new GridLayout(1, buttonsInfo.size()));

        // Itera sobre as informações dos botões e cria cada botão.
        for (ButtonInfo buttonInfo : buttonsInfo) {
            Button button = new Button(ButtonType.BASIC, buttonInfo.getLabel());
            button.setIcon(buttonInfo.getIcon());
            button.addActionListener(buttonInfo.getActionListener());
            add(button);
        }
    }
}
