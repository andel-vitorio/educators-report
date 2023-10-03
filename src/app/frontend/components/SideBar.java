package app.frontend.components;

import java.awt.*;

import javax.swing.*;

import res.fonts.FontsManager;
import res.fonts.FontsManager.FontType;
import res.values.*;
import utils.ComponentDecorator;

public class SideBar {

	private JPanel component = new JPanel();
	private int widthOfSideBar;

	public SideBar(int width, int height) {
		this.widthOfSideBar = width;

		component.setSize(width, height);
		component.setPreferredSize(new Dimension(width, height));
		component.setLayout(new BoxLayout(component, BoxLayout.PAGE_AXIS));

		ComponentDecorator.addBorderRight(component, 1);
	}

	public SideBar setBackgroundColor(Color color) {
		component.setBackground(color);
		return this;
	}

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

	public SideBar setNavigation(Navigation navigation) {
		component.add(navigation);
		return this;
	}

	public JPanel getComponent() {
		return component;
	}
}
