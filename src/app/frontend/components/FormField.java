package app.frontend.components;

import javax.swing.*;

import res.fonts.FontsManager;
import res.fonts.FontsManager.FontType;
import utils.ComponentDecorator;

import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class FormField extends JPanel {
    private JLabel formLabel;
    private RoundedTextField formInput;

    public FormField(String label, int width) {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(width, 60));
        setMaximumSize(getPreferredSize());
        setSize(getPreferredSize());
        setOpaque(false);

        this.formLabel = new JLabel(label);
        this.formLabel.setFont(FontsManager.getFont(FontType.REGULAR, 12));
        this.formLabel.setPreferredSize(new Dimension(width, 24));
        this.formLabel.setSize(this.formLabel.getPreferredSize());
				ComponentDecorator.addPaddingBottom(formLabel, 8);

        this.formInput = new RoundedTextField();
				this.formInput.setFont(FontsManager.getFont(FontType.MEDIUM, 12));
				ComponentDecorator.addPaddingHorizontal(formInput, 12);

        add(this.formLabel, BorderLayout.PAGE_START);
        add(this.formInput, BorderLayout.CENTER);
    }

		public void setEditable(boolean isEditable) {
			formInput.setEditable(isEditable);
		}

    public void setWidthLabel(int width) {
        this.formLabel.setPreferredSize(new Dimension(width, 0));
    }

    public String getText() {
        return this.formInput.getText();
    }

    public void setText(String text) {
        this.formInput.setText(text);
    }

    public void setText(int text) {
        this.formInput.setText(String.valueOf(text));
    }
}
