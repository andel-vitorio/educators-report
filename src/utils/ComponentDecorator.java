package utils;

import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JComponent;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import res.values.ColorsManager;

public class ComponentDecorator {
	
	public static void addBorderRight(JComponent component, int widht) {
		MatteBorder border = BorderFactory.createMatteBorder(0, 0, 0, widht, ColorsManager.getBorderColor());
		component.setBorder(border);
	}

	public static void addBorder(JComponent component, int top, int right, int bottom, int left) {
		MatteBorder border = BorderFactory.createMatteBorder(top, left, bottom, right, ColorsManager.getBorderColor());
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
	
	public static void addBorder(JComponent component, int widht) {
		Border border = BorderFactory.createLineBorder(ColorsManager.getBorderColor(), widht);
		component.setBorder(border);
	}

	public static void addGap(JComponent component, int gap) {
		component.add(Box.createRigidArea(new Dimension(0, gap)));
	}
}
