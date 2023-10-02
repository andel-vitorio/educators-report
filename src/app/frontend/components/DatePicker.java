package app.frontend.components;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Rectangle;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.plaf.basic.BasicComboBoxRenderer;
import javax.swing.plaf.basic.BasicComboBoxUI;

import app.frontend.components.Button.ButtonType;
import res.fonts.FontsManager;
import res.fonts.FontsManager.FontType;
import res.values.ColorsManager;
import utils.ComponentDecorator;

public class DatePicker extends JPanel {

	private JLabel formLabel;

	private ComboBox daysComboBox;
	Integer[] days = new Integer[31];

	private ComboBox monthComboBox;
	Integer[] months = new Integer[12];

	private ComboBox yearComboBox;
	ArrayList<Integer> years = new ArrayList<>();

	public DatePicker(String label, int width) {
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(width, 60));
		setMaximumSize(getPreferredSize());
		setSize(getPreferredSize());
		setOpaque(false);

		for (Integer i = 0; i < 31; i++)
			days[i] = i + 1;

		for (Integer i = 0; i < 12; i++)
			months[i] = i + 1;

		for (Integer i = 2023; i > 1989; i--)
			years.add(i + 1);

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
		constraints.gridy = 0;
		constraints.insets = new Insets(0, 0, 0, 0);

		JLabel dayLabel = new JLabel("Dia: ");
		dayLabel.setFont(FontsManager.getFont(FontType.REGULAR, 10));
		dayLabel.setPreferredSize(new Dimension(24, 24));
		dayLabel.setSize(dayLabel.getPreferredSize());
		dayLabel.setVerticalAlignment(SwingConstants.CENTER);
		dayLabel.setBackground(Color.BLACK);
		container.add(dayLabel, constraints);

		constraints.gridx = x++;
		constraints.weightx = 0.5;
		constraints.insets = new Insets(0, 0, 0, 8);

		daysComboBox = new ComboBox(days);
		daysComboBox.setFont(FontsManager.getFont(FontType.MEDIUM, 12));
		// ComponentDecorator.addPadding(daysComboBox, 0, 4);
		container.add(daysComboBox, constraints);

		constraints.gridx = x++;
		constraints.weightx = 1.0;
		constraints.insets = new Insets(0, 0, 0, 0);

		JLabel monthLabel = new JLabel("Mês: ");
		monthLabel.setFont(FontsManager.getFont(FontType.REGULAR, 10));
		monthLabel.setPreferredSize(new Dimension(24, 24));
		monthLabel.setSize(monthLabel.getPreferredSize());
		monthLabel.setVerticalAlignment(SwingConstants.CENTER);
		container.add(monthLabel, constraints);

		constraints.gridx = x++;
		constraints.weightx = 0.5;
		constraints.insets = new Insets(0, 0, 0, 8);

		monthComboBox = new ComboBox(months);
		monthComboBox.setFont(FontsManager.getFont(FontType.MEDIUM, 12));
		container.add(monthComboBox, constraints);

		constraints.gridx = x++;
		constraints.weightx = 1.0;

		JLabel yearLabel = new JLabel("Ano: ");
		yearLabel.setFont(FontsManager.getFont(FontType.REGULAR, 10));
		yearLabel.setPreferredSize(new Dimension(24, 24));
		yearLabel.setSize(yearLabel.getPreferredSize());
		yearLabel.setVerticalAlignment(SwingConstants.CENTER);
		container.add(yearLabel, constraints);

		constraints.gridx = x++;
		constraints.weightx = 0.5;

		yearComboBox = new ComboBox(years.toArray());
		yearComboBox.setFont(FontsManager.getFont(FontType.MEDIUM, 12));
		container.add(yearComboBox, constraints);

		add(formLabel, BorderLayout.PAGE_START);
		add(container, BorderLayout.CENTER);
	}

	public LocalDate getDate() {
		int day = (int) daysComboBox.getSelectedItem();
		int month = (int) monthComboBox.getSelectedItem();
		int year = (int) yearComboBox.getSelectedItem();
		return LocalDate.of(year, month, day);
	}

	public void setDate(LocalDate localDate) {
		daysComboBox.setSelectedItem(localDate.getDayOfMonth());		
		monthComboBox.setSelectedItem(localDate.getMonthValue());
		yearComboBox.setSelectedItem(localDate.getYear());

	}

	public void setEditable(boolean isEditable) {
		daysComboBox.setEnabled(isEditable);
		monthComboBox.setEnabled(isEditable);
		yearComboBox.setEnabled(isEditable);
	}
}
