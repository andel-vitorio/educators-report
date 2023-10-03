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

public class Navigation extends JPanel implements ActionListener {
	private JButton activedButton;
	private int qButton;
	int widht;

	private Observable observable;

	public Navigation(int widht, int height, ActionListener actionListener) {
		JLabel titleLabel = new JLabel("MENU");

		setLayout(new GridBagLayout());
		setPreferredSize(new Dimension(widht, height));
		setMaximumSize((getPreferredSize()));
		setOpaque(false);

		int fillHorizontal = GridBagConstraints.HORIZONTAL;
		this.widht = widht;

		GridBagConstraints gbcTitleLabel = getButtonContrainst(fillHorizontal, 1.0, 1, 0, 0);

		ComponentDecorator.addPadding(titleLabel, 48, 24, 0, 24);

		if (!FontsManager.getLoadError()) {
			titleLabel.setFont(FontsManager.getFont(FontType.MEDIUM, DimensManager.getTitleSize()));
		}

		add(titleLabel, gbcTitleLabel);

		observable = new Observable();
	}

	public void setItem(String label, ImageIcon icon, String tag) {

		Button button = new Button(ButtonType.BASIC, label, ColorsManager.getOnBackgroundColor());
		button.setHorizontalAlignment(SwingConstants.LEFT);
		button.setIcon(icon);
		button.setMinimumSize(new Dimension(widht, 48));

		if ( !FontsManager.getLoadError() )	button.setFont(FontsManager.getFont(FontType.REGULAR, DimensManager.getButtonFontsizeMedium()));

		if (qButton == 0) {
			// button.setBackground(Color.BLUE);
			if ( !FontsManager.getLoadError() )	button.setFont(FontsManager.getFont(FontType.SEMI_BOLD, (int) (1.1 * DimensManager.getButtonFontsizeMedium())));
			activedButton = button;
		}

		int fillHorizontal = GridBagConstraints.HORIZONTAL;

		GridBagConstraints gbcButton1 = getButtonContrainst(fillHorizontal, 1.0, 2, 0, qButton + 1);
		gbcButton1.insets = new Insets(16, 0, 0, 0);
		gbcButton1.weighty = 1.5;

		add(button, gbcButton1);
		qButton++;

		button.setActionCommand(tag);
		button.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if ( !FontsManager.getLoadError() )	activedButton.setFont(FontsManager.getFont(FontType.REGULAR, DimensManager.getButtonFontsizeMedium()));
		activedButton = (JButton) e.getSource();
		observable.notifyObservers(e.getActionCommand());
		if ( !FontsManager.getLoadError() )	activedButton.setFont(FontsManager.getFont(FontType.SEMI_BOLD, (int) (1.1 * DimensManager.getButtonFontsizeMedium())));
	}

	private GridBagConstraints getButtonContrainst(int fill, double weightx, int gridwidth, int gridx, int gridy) {
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = fill;
		gbc.weightx = weightx;
		gbc.gridwidth = gridwidth;
		gbc.gridx = gridx;
		gbc.gridy = gridy;
		return gbc;
	}

	public Observable getObservable() {
		return observable;
	}
}
