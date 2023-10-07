package app.frontend.components;

import javax.swing.*;
import javax.swing.text.JTextComponent;

import res.fonts.FontsManager;
import res.fonts.FontsManager.FontType;
import utils.ComponentDecorator;

import java.awt.*;

/**
 * Um componente de campo de formulário personalizado.
 */
public class FormField extends JPanel {
    private JLabel formLabel;
    private JComponent formInput; // Use JComponent como tipo base
    int width, height;

    /**
     * Enumeração que define os tipos de campos de formulário disponíveis.
     */
    public enum FieldType {
        NORMAL,
        PASSWORD,
        MULTILINE
    }

    private FieldType fieldType = FieldType.NORMAL;

    /**
     * Cria um campo de formulário com um rótulo e largura especificados.
     *
     * @param label  O rótulo do campo de formulário.
     * @param width  A largura desejada do campo de formulário.
     */
    public FormField(String label, int width) {
        this(label, FieldType.NORMAL, width);
    }

    /**
     * Cria um campo de formulário com um rótulo, tipo de campo e largura especificados.
     *
     * @param label     O rótulo do campo de formulário.
     * @param fieldType O tipo de campo de formulário (NORMAL, PASSWORD ou MULTILINE).
     * @param width     A largura desejada do campo de formulário.
     */
    public FormField(String label, FieldType fieldType, int width) {
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

        this.fieldType = fieldType;
        initializeInputComponent();

        add(this.formLabel, BorderLayout.PAGE_START);
        add(this.formInput, BorderLayout.CENTER);
    }

    private void initializeInputComponent() {
        if (fieldType == FieldType.MULTILINE) {
            RoundedTextArea textArea = new RoundedTextArea();
            textArea.setWrapStyleWord(true);
            textArea.setLineWrap(true);
            textArea.setFont(FontsManager.getFont(FontType.MEDIUM, 12));
            ComponentDecorator.addPadding(textArea, 12);
            this.formInput = textArea;
        } else if (fieldType == FieldType.PASSWORD) {
            RoundedPasswordField passwordField = new RoundedPasswordField();
            passwordField.setFont(FontsManager.getFont(FontType.MEDIUM, 12));
            ComponentDecorator.addPaddingHorizontal(passwordField, 12);
            this.formInput = passwordField;
        } else {
            RoundedTextField textField = new RoundedTextField();
            textField.setFont(FontsManager.getFont(FontType.MEDIUM, 12));
            ComponentDecorator.addPaddingHorizontal(textField, 12);
            this.formInput = textField;
        }
    }

    /**
     * Define se o campo de formulário é multilinha ou não.
     *
     * @param multiline true se o campo de formulário deve ser multilinha, caso contrário, false.
     */
    public void isMultiline(boolean multiline) {
        remove(formInput);
        initializeInputComponent();
        add(this.formInput, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    /**
     * Define se o campo de formulário é editável ou não.
     *
     * @param isEditable true se o campo de formulário deve ser editável, caso contrário, false.
     */
    public void setEditable(boolean isEditable) {
        if (formInput instanceof JTextComponent) {
            ((JTextComponent) formInput).setEditable(isEditable);
        }
    }

    /**
     * Define a largura do rótulo do campo de formulário.
     *
     * @param width A largura desejada do rótulo.
     */
    public void setWidthLabel(int width) {
        this.formLabel.setPreferredSize(new Dimension(width, 0));
    }

    /**
     * Define a altura do campo de formulário.
     *
     * @param height A altura desejada do campo de formulário.
     */
    public void setHeight(int height) {
        setPreferredSize(new Dimension(this.width, height));
        setMaximumSize(getPreferredSize());
        setSize(getPreferredSize());
    }

    /**
     * Obtém o texto inserido no campo de formulário.
     *
     * @return O texto inserido no campo de formulário.
     */
    public String getText() {
        if (formInput instanceof JTextComponent) {
            return ((JTextComponent) formInput).getText();
        }
        return "";
    }

    /**
     * Define o texto a ser exibido no campo de formulário.
     *
     * @param text O texto a ser definido no campo de formulário.
     */
    public void setText(String text) {
        if (formInput instanceof JTextComponent) {
            ((JTextComponent) formInput).setText(text);
        }
    }

    /**
     * Define o texto a ser exibido no campo de formulário como um valor numérico.
     *
     * @param text O valor numérico a ser definido no campo de formulário como texto.
     */
    public void setText(int text) {
        setText(String.valueOf(text));
    }
}
