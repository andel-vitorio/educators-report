package app.frontend.components;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import res.values.ColorsManager;

import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class RoundedTextField extends JTextField {
    private int borderRadius = 24;

    public RoundedTextField() {
        super();
        setOpaque(false); // Tornar o fundo do campo de texto transparente
        setBorder(new EmptyBorder(0, 10, 0, 10)); // Adicionar preenchimento interno
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();

        int width = getWidth();
        int height = getHeight();

        // Criar um retângulo com cantos arredondados
        RoundRectangle2D roundedRectangle = new RoundRectangle2D.Float(0, 0, width - 1, height - 1, borderRadius, borderRadius);

        // Definir a cor do fundo do campo de texto
        g2d.setColor(getBackground());	
        g2d.fill(roundedRectangle);

        // Definir a cor da borda do campo de texto
        g2d.setColor(ColorsManager.getTextfieldBorderColor());
        g2d.draw(roundedRectangle);

        g2d.dispose();

        super.paintComponent(g);
    }
}
