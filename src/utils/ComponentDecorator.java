package utils;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JComponent;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import res.values.ColorsManager;

public class ComponentDecorator {

	public static class RoundedBorder implements Border {

		private int radius;


		public RoundedBorder(int radius) {
				this.radius = radius;
		}


		@Override
		public Insets getBorderInsets(Component c) {
				return new Insets(this.radius+1, this.radius+1, this.radius+2, this.radius);
		}


		@Override
		public boolean isBorderOpaque() {
				return true;
		}


		@Override
		public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
				g.drawRoundRect(x, y, width-1, height-1, radius, radius);
				c.repaint();
		}

}

	public static void addBorderRight(JComponent component, int widht) {
		MatteBorder border = BorderFactory.createMatteBorder(0, 0, 0, widht, ColorsManager.getBorderColor());
		component.setBorder(border);
	}

	public static void addBorder(JComponent component, int top, int right, int bottom, int left) {
		MatteBorder border = BorderFactory.createMatteBorder(top, left, bottom, right, ColorsManager.getBorderColor());
		component.setBorder(border);
	}

	public static void addBorderBottom(JComponent component, int width) {
		MatteBorder border = BorderFactory.createMatteBorder(0,0,width,0, ColorsManager.getBorderColor());
		component.setBorder(BorderFactory.createCompoundBorder(border, component.getBorder()));
	}

	public static void addBorderBottom(JComponent component, int width, Color color) {
		MatteBorder border = BorderFactory.createMatteBorder(0,0,width,0, color);
		component.setBorder(border);
	}

	public static void addPadding(JComponent component, int top, int right, int bottom, int left) {
		EmptyBorder border = new EmptyBorder(top, left, bottom, right);
		component.setBorder(border);
	}

	public static void addPaddingHorizontal(JComponent component, int widht) {
		EmptyBorder border = new EmptyBorder(0, widht, 0, widht);
		component.setBorder(border);
	}

	public static void addPaddingVertical(JComponent component, int widht) {
		EmptyBorder border = new EmptyBorder(widht, 0, widht, 0);
		component.setBorder(border);
	}

	public static void addPadding(JComponent component, int vertical, int horizontal) {
		EmptyBorder border = new EmptyBorder(vertical, horizontal, vertical, horizontal);
		component.setBorder(border);
	}

	public static void addPadding(JComponent component, int width) {
		EmptyBorder border = new EmptyBorder(width, width, width, width);
		component.setBorder(border);
	}

	public static void addPaddingBottom(JComponent component, int width) {
		EmptyBorder border = new EmptyBorder(0, 0, width, 0);
		component.setBorder(border);
	}

	public static void addBorder(JComponent component, int widht) {
		Border border = BorderFactory.createLineBorder(ColorsManager.getBorderColor(), widht);
		component.setBorder(border);
	}

	public static void addGap(JComponent component, int gap) {
		component.add(Box.createRigidArea(new Dimension(0, gap)));
	}
}
