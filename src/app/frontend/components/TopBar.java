package app.frontend.components;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import app.frontend.components.Button.ButtonType;
import res.fonts.FontsManager;
import res.fonts.FontsManager.FontType;
import res.img.ImagesManager;
import res.values.ColorsManager;
import utils.ComponentDecorator;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;

public class TopBar extends JPanel {

	private JLabel titleLabel;
	private Button button;

	public TopBar(String title) {
		setLayout(new BorderLayout());
		ComponentDecorator.addPadding(this, 16, 24);

		titleLabel = new JLabel(title);

		if ( !FontsManager.getLoadError() ) titleLabel.setFont(FontsManager.getFont(FontType.SEMI_BOLD, 16));
		// this.titleLabel.setFont(FontsManager.medium20());

		add(titleLabel, BorderLayout.LINE_START);
	}

	public void setActionButton(String label, ImageIcon icon, ActionListener listener) {

		JPanel container = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
					super.paintComponent(g);
					Graphics2D g2d = (Graphics2D) g;

					int width = getWidth();
					int height = getHeight();

					setBackground(ColorsManager.getOnBackgroundColor());

					RoundRectangle2D roundedRectangle = new RoundRectangle2D.Float(0, 0, width, height, 16, 16);

					g2d.setColor(ColorsManager.getButtonBackground());
					g2d.fill(roundedRectangle);
			}
		};

		button = new Button(ButtonType.BASIC, label);
		button.setIcon(icon);
		button.addActionListener(listener);
		button.setForeground(ColorsManager.getTextColorLight());
		ComponentDecorator.addPadding(button, 12);

		container.setLayout(new BorderLayout());
		container.add(button, BorderLayout.CENTER);

		add(container, BorderLayout.LINE_END);
	}
}
