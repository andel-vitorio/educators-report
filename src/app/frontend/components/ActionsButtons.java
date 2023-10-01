package app.frontend.components;

import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JPanel;

import app.frontend.components.Button.ButtonInfo;
import app.frontend.components.Button.ButtonType;

public class ActionsButtons extends JPanel {

	public ActionsButtons(ArrayList<ButtonInfo> buttonsInfo) {
		setLayout(new GridLayout(1, buttonsInfo.size()));

		for (ButtonInfo buttonInfo : buttonsInfo) {
			Button button = new Button(ButtonType.BASIC, buttonInfo.getLabel());
			button.setIcon(buttonInfo.getIcon());
			button.addActionListener(buttonInfo.getActionListener());
			add(button);
		}
	}
}
