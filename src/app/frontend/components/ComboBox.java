package app.frontend.components;

import javax.swing.*;
import javax.swing.plaf.basic.BasicComboBoxRenderer;
import javax.swing.plaf.basic.BasicComboBoxUI;

import res.fonts.FontsManager;
import res.fonts.FontsManager.FontType;
import res.values.ColorsManager;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ComboBox extends JComboBox<Object> {
	private Color backgroundColor = Color.WHITE;
	private Font font = new Font("Arial", Font.PLAIN, 12);

	public ComboBox(Object[] items) {
		super(items);
		setRenderer(new CenteredTextRenderer());

		setUI(new BasicComboBoxUI() {
			@Override
			protected JButton createArrowButton() {
				return new JButton() {
					@Override
					public int getWidth() {
						return 0;
					}
				};
			}
		});

		addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				repaint();
			}
		});
	}

	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g.create();
		g2d.setColor(getBackground());
		g2d.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 24, 24);

		super.paintComponent(g);

		g.setColor(backgroundColor);
		g.fillRect(0, 0, getWidth(), getHeight());

		setFont(FontsManager.getFont(FontType.MEDIUM, 12));

		Object selectedItem = getSelectedItem();
        if (selectedItem != null) {
            String selectedText = selectedItem.toString();
            FontMetrics fm = g.getFontMetrics();
            int textWidth = fm.stringWidth(selectedText);
            int textX = (getWidth() - textWidth) / 2;
            int textY = (getHeight() + fm.getAscent() - fm.getDescent()) / 2;
						g.setColor(getForeground());
            g.drawString(selectedText, textX, textY);
        }

		g2d.dispose();
	}

	@Override
	protected void paintBorder(Graphics g) {
		Graphics2D g2d = (Graphics2D) g.create();

		g2d.setColor(ColorsManager.getTextfieldBorderColor());
		g2d.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 24, 24);

		g2d.dispose();
	}

	private class CenteredTextRenderer extends BasicComboBoxRenderer {
		@Override
		public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
				boolean cellHasFocus) {
			super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

			if (value != null) {
				setHorizontalAlignment(SwingConstants.CENTER); // Define o alinhamento como CENTRO
			}

			return this;
		}
	}

	public void setBackgroundColor(Color color) {
		this.backgroundColor = color;
		repaint();
	}

	public Color getBackgroundColor() {
		return backgroundColor;
	}

	public void setFont(Font font) {
		this.font = font;
		repaint();
	}

	public Font getFont() {
		return font;
	}
}