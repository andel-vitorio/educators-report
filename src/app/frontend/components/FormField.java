package app.frontend.components;

import javax.swing.*;
import javax.swing.text.JTextComponent;

import res.fonts.FontsManager;
import res.fonts.FontsManager.FontType;
import utils.ComponentDecorator;

import java.awt.*;

public class FormField extends JPanel {
    private JLabel formLabel;
    private JComponent formInput; // Use JComponent como tipo base
    private boolean multiline = false;
    int width, height;

    public FormField(String label, int width) {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(width, 60));
        setMaximumSize(getPreferredSize());
        setSize(getPreferredSize());
        setOpaque(false);

        this.width = width;

        this.formLabel = new JLabel(label);
        this.formLabel.setFont(FontsManager.getFont(FontType.REGULAR, 12));
        this.formLabel.setPreferredSize(new Dimension(width, 24));
        this.formLabel.setSize(this.formLabel.getPreferredSize());
        ComponentDecorator.addPaddingBottom(formLabel, 8);

        initializeInputComponent();

        add(this.formLabel, BorderLayout.PAGE_START);
        add(this.formInput, BorderLayout.CENTER);
    }

    private void initializeInputComponent() {
        if (multiline) {
            RoundedTextArea textArea = new RoundedTextArea();
            textArea.setWrapStyleWord(true);
            textArea.setLineWrap(true);
						textArea.setFont(FontsManager.getFont(FontType.MEDIUM, 12));
            ComponentDecorator.addPadding(textArea, 12);
            this.formInput = textArea;
        } else {
            RoundedTextField textField = new RoundedTextField();
            textField.setFont(FontsManager.getFont(FontType.MEDIUM, 12));
            ComponentDecorator.addPaddingHorizontal(textField, 12);
            this.formInput = textField;
        }
    }

    public void isMultiline(boolean multiline) {
        this.multiline = multiline;
        remove(formInput);
        initializeInputComponent();
        add(this.formInput, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    public void setEditable(boolean isEditable) {
        if (formInput instanceof JTextComponent) {
            ((JTextComponent) formInput).setEditable(isEditable);
        }
    }

    public void setWidthLabel(int width) {
        this.formLabel.setPreferredSize(new Dimension(width, 0));
    }

    public void setHeight(int height) {
        setPreferredSize(new Dimension(this.width, height));
        setMaximumSize(getPreferredSize());
        setSize(getPreferredSize());
    }

    public String getText() {
        if (formInput instanceof JTextComponent) {
            return ((JTextComponent) formInput).getText();
        }
        return "";
    }

    public void setText(String text) {
        if (formInput instanceof JTextComponent) {
            ((JTextComponent) formInput).setText(text);
        }
    }

    public void setText(int text) {
        setText(String.valueOf(text));
    }
}
