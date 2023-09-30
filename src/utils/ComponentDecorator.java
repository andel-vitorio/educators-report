package utils;

import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JComponent;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;

import res.values.ColorsManager;

public class ComponentDecorator {
	
	public static void addBorderRight(JComponent component, int widht) {
		MatteBorder border = BorderFactory.createMatteBorder(0, 0, 0, widht, ColorsManager.getBorderColor());
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
