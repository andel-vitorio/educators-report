package app.frontend.components;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.time.LocalTime;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import res.fonts.FontsManager;
import res.fonts.FontsManager.FontType;
import utils.ComponentDecorator;

/**
 * Um componente para selecionar um horário.
 */
public class TimePicker extends JPanel {

    private JLabel formLabel;

    private ComboBox hoursComboBox;
    Integer[] hours = new Integer[25];

    private ComboBox minutesComboBox;
    Integer[] minutes = new Integer[61];

    /**
     * Cria um novo TimePicker com o rótulo especificado e largura.
     *
     * @param label  O rótulo para o componente.
     * @param width  A largura do componente.
     */
    public TimePicker(String label, int width) {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(width, 60));
        setMaximumSize(getPreferredSize());
        setSize(getPreferredSize());
        setOpaque(false);

        for (Integer i = 0; i < 24; i++)
            hours[i + 1] = i;

        for (Integer i = 0; i < 60; i++)
            minutes[i + 1] = i;

        hours[0] = minutes[0] = null;

        formLabel = new JLabel(label);
        formLabel.setFont(FontsManager.getFont(FontType.REGULAR, 12));
        formLabel.setPreferredSize(new Dimension(width, 24));
        formLabel.setSize(formLabel.getPreferredSize());
        ComponentDecorator.addPaddingBottom(formLabel, 8);

        JPanel container = new JPanel(new GridBagLayout());
        container.setOpaque(false);

        int x = 0;

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.BOTH;
        constraints.anchor = GridBagConstraints.CENTER; // Centraliza verticalmente
        constraints.weightx = 1.0;
        constraints.weighty = 1.0;

        constraints.gridx = x++;
        constraints.insets = new Insets(0, 0, 0, 8);

        hoursComboBox = new ComboBox(hours);
        hoursComboBox.setFont(FontsManager.getFont(FontType.MEDIUM, 12));
        container.add(hoursComboBox, constraints);

        constraints.gridx = x++;
        constraints.weightx = 1;
        constraints.insets = new Insets(0, 0, 0, 8);

        JLabel dayLabel = new JLabel(":");
        dayLabel.setFont(FontsManager.getFont(FontType.REGULAR, 10));
        dayLabel.setPreferredSize(new Dimension(24, 24));
        dayLabel.setSize(dayLabel.getPreferredSize());
        dayLabel.setVerticalAlignment(SwingConstants.CENTER);
        dayLabel.setHorizontalAlignment(SwingConstants.CENTER);
        dayLabel.setBackground(Color.BLACK);
        container.add(dayLabel, constraints);

        constraints.gridx = x++;
        constraints.weightx = 1.0;
        constraints.insets = new Insets(0, 0, 0, 8);

        minutesComboBox = new ComboBox(minutes);
        minutesComboBox.setFont(FontsManager.getFont(FontType.MEDIUM, 12));
        container.add(minutesComboBox, constraints);

        GridBagConstraints fillerConstraints = new GridBagConstraints();
        fillerConstraints.fill = GridBagConstraints.BOTH;
        fillerConstraints.weightx = 1.0;
        fillerConstraints.gridheight = 1;
        fillerConstraints.gridx = x++;
        fillerConstraints.gridy = 0;

        JPanel filler = new JPanel();
        filler.setOpaque(false);

        container.add(filler, fillerConstraints);

        add(formLabel, BorderLayout.PAGE_START);
        add(container, BorderLayout.CENTER);
    }

    /**
     * Obtém o horário selecionado.
     *
     * @return O horário selecionado como um objeto LocalTime.
     */
    public LocalTime getTime() {
        int hour = (int) hoursComboBox.getSelectedItem();
        int minute = (int) minutesComboBox.getSelectedItem();
        return LocalTime.of(hour, minute);
    }

    /**
     * Define o horário selecionado.
     *
     * @param time O horário a ser definido.
     */
    public void setTime(LocalTime time) {
        hoursComboBox.setSelectedItem(time.getHour());
        minutesComboBox.setSelectedItem(time.getMinute());
    }

    /**
     * Define se o componente é editável ou não.
     *
     * @param isEditable true se o componente for editável, false caso contrário.
     */
    public void setEditable(boolean isEditable) {
        hoursComboBox.setEnabled(isEditable);
        minutesComboBox.setEnabled(isEditable);
    }
}

