package app.frontend.components;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import res.values.ColorsManager;

import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class RoundedTextArea extends JTextArea {
	private int borderRadius = 24;

	public RoundedTextArea() {
		super();
		setOpaque(false);
		setBorder(new EmptyBorder(10, 10, 10, 10));
		setLineWrap(true);
		setWrapStyleWord(true);
	}

	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g.create();

		int width = getWidth();
		int height = getHeight();

		RoundRectangle2D roundedRectangle = new RoundRectangle2D.Float(0, 0, width - 1, height - 1, borderRadius,
				borderRadius);

		g2d.setColor(getBackground());
		g2d.fill(roundedRectangle);

		g2d.setColor(ColorsManager.getTextfieldBorderColor());
		g2d.draw(roundedRectangle);

		g2d.dispose();

		super.paintComponent(g);
	}
}
