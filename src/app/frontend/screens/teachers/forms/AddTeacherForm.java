package app.frontend.screens.teachers.forms;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.geom.RoundRectangle2D;
import java.text.Normalizer.Form;
import java.time.LocalDate;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JViewport;

import app.backend.entities.Teacher;
import app.frontend.components.Button;
import app.frontend.components.Button.ButtonType;
import app.frontend.components.DatePicker;
import app.frontend.components.FormField;
import app.frontend.models.TeacherTableModel;
import res.fonts.FontsManager;
import res.fonts.FontsManager.FontType;
import res.values.ColorsManager;
import res.values.DimensManager;
import utils.ComponentDecorator;

public class AddTeacherForm extends JFrame {

	private Container container;

	private FormField nameFormField;
	private DatePicker birthDayDatePicker;
	private FormField indetificationFormField;
	private FormField yearsOfExperienceFormField;
	private FormField emailFormField;
	private FormField phoneNumberFormField;
	private FormField trainingAreaFormField;

	public AddTeacherForm(TeacherTableModel teacherTableModel) {
		super("Formulário de Cadastro de Professor");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setPreferredSize(new Dimension(600, 650));
		setSize(getPreferredSize());
		setResizable(false);
		setLocationRelativeTo(null);

		container = getContentPane();
		container.setBackground(ColorsManager.getOnBackgroundColor());
		container.setLayout(new BorderLayout());

		JLabel titleLabel = new JLabel("Cadastro de Professores", JLabel.LEFT);
		titleLabel.setFont(FontsManager.getFont(FontType.BOLD, DimensManager.getTitleFormsFontsize()));
		titleLabel.setBackground(ColorsManager.getBackgroundColor());
		ComponentDecorator.addPadding(titleLabel, 24);

		container.add(titleLabel, BorderLayout.PAGE_START);
		container.add(getFormContainer(), BorderLayout.CENTER);
		container.add(getButtonsContainer(), BorderLayout.PAGE_END);

		setVisible(true);
	}

	private JScrollPane getFormContainer() {
		JPanel formContainer = new JPanel(new GridBagLayout());
		ComponentDecorator.addPadding(formContainer, 0, 24);
		formContainer.setOpaque(false);
		int y = 0;

		GridBagConstraints constraints = new GridBagConstraints();
		
		// Seção: Informações Pessoais
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.anchor = GridBagConstraints.PAGE_START;
		constraints.gridwidth = 2;
		constraints.gridx = 0;
		constraints.gridy = y++;

		formContainer.add(getSectionLabel("Informações Pessoais"), constraints);

		constraints.insets = new Insets(0, 0, 16, 0);
		constraints.gridy = y++;
		nameFormField = new FormField("Nome Completo", 504);
		formContainer.add(nameFormField, constraints);

		constraints.gridwidth = 1;
		constraints.weightx = 0.5;
		constraints.gridy = y;
		constraints.insets = new Insets(0, 0, 16, 12);
		birthDayDatePicker = new DatePicker("Data de Nascimento", 0);
		formContainer.add(birthDayDatePicker, constraints);

		constraints.insets = new Insets(0, 12, 16, 0);
		constraints.gridwidth = 1;
		constraints.gridx = 1;
		constraints.gridy = y++;
		indetificationFormField = new FormField("Identificação", 0);
		formContainer.add(indetificationFormField, constraints);

		// Seção: Informações de Contato
		constraints.gridwidth = 2;
		constraints.gridx = 0;
		constraints.gridy = y++;
		constraints.insets = new Insets(0, 0, 0, 0);

		formContainer.add(getSectionLabel("Informações de Contato"), constraints);

		constraints.insets = new Insets(0, 0, 16, 0);
		constraints.gridy = y++;
		emailFormField = new FormField("E-mail", 504);
		formContainer.add(emailFormField, constraints);

		constraints.insets = new Insets(0, 0, 16, 0);
		constraints.gridy = y++;
		phoneNumberFormField = new FormField("Número de telefone", 504);
		formContainer.add(phoneNumberFormField, constraints);
		
		// Seção Informações Profissionais
		constraints.insets = new Insets(0, 0, 0, 0);
		constraints.gridy = y++;
		formContainer.add(getSectionLabel("Informações Profissional"), constraints);

		constraints.gridwidth = 1;
		constraints.weightx = 0.5;
		constraints.gridy = y;
		constraints.insets = new Insets(0, 0, 0, 12);
		trainingAreaFormField = new FormField("Área de Formação", 0);
		formContainer.add(trainingAreaFormField, constraints);

		constraints.insets = new Insets(0, 12, 0, 0);
		constraints.gridwidth = 1;
		constraints.gridx = 1;
		constraints.gridy = y++;
		yearsOfExperienceFormField = new FormField("Anos de Experiência", 0);
		formContainer.add(yearsOfExperienceFormField, constraints);

		GridBagConstraints fillerConstraints = new GridBagConstraints();
		fillerConstraints.weighty = 1.0;
		fillerConstraints.gridx = 0;
		fillerConstraints.gridy = y++;
		fillerConstraints.gridwidth = 2;

		JPanel filler = new JPanel();
		filler.setOpaque(false);

		formContainer.add(filler, fillerConstraints);

		JScrollPane scrollPane = new JScrollPane(formContainer);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.getViewport().setBackground(ColorsManager.getOnBackgroundColor());
		scrollPane.setBorder(null);
		
		return scrollPane;
	}

	private JLabel getSectionLabel(String text) {
		JLabel label = new JLabel(text, JLabel.LEFT);
		label.setFont(FontsManager.getFont(FontType.SEMI_BOLD, DimensManager.getSectionLabelFontsize()));
		label.setOpaque(false);
		ComponentDecorator.addPaddingBottom(label, 12);
		return label;
	}

	private JPanel getButtonsContainer() {
		JPanel buttonsContainer = new JPanel();
		buttonsContainer.setLayout(new FlowLayout(FlowLayout.RIGHT, 24, 0));
		buttonsContainer.setOpaque(false);
		// buttonsContainer.setBorder(new EmptyBorder(24, 0, 24, 0));

		JPanel confirmButtonContainer = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
					super.paintComponent(g);
					Graphics2D g2d = (Graphics2D) g;

					int width = getWidth();
					int height = getHeight();

					setBackground(ColorsManager.getOnBackgroundColor());

					RoundRectangle2D roundedRectangle = new RoundRectangle2D.Float(0, 0, width, height, 24, 24);

					g2d.setColor(ColorsManager.getButtonBackgroundPrimary());
					g2d.fill(roundedRectangle);
			}
		};

		Button confirmButton = new Button(ButtonType.BASIC, "CONFIRMAR");
		confirmButton.setForeground(ColorsManager.getTextColorLight());
		confirmButton.setBorder(null);
		ComponentDecorator.addPadding(confirmButton, 12, 16);

		confirmButtonContainer.add(confirmButton);
		confirmButtonContainer.setBorder(null);

		JPanel cancelButtonContainer = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
					super.paintComponent(g);
					Graphics2D g2d = (Graphics2D) g;

					int width = getWidth();
					int height = getHeight();

					setBackground(ColorsManager.getOnBackgroundColor());

					RoundRectangle2D roundedRectangle = new RoundRectangle2D.Float(0, 0, width, height, 24, 24);

					g2d.setColor(ColorsManager.getButtonBackgroundSecondary());
					g2d.fill(roundedRectangle);
			}
		};

		Button cancelButton = new Button(ButtonType.BASIC, "CANCELAR");
		cancelButton.setForeground(ColorsManager.getTextColorDark());
		cancelButton.setBorder(null);
		ComponentDecorator.addPadding(cancelButton, 12, 16);

		cancelButtonContainer.add(cancelButton);
		cancelButtonContainer.setBorder(null);

		cancelButton.addActionListener(event -> dispose());
		confirmButton.addActionListener(event -> confirm());

		buttonsContainer.add(cancelButtonContainer);
		buttonsContainer.add(confirmButtonContainer);

		ComponentDecorator.addPadding(buttonsContainer, 24,0);

		return buttonsContainer;
	}

	private void confirm() {
		Teacher teacher = new Teacher();
		teacher.setBirthDay(birthDayDatePicker.getDate());
		teacher.setEmail(emailFormField.getText());
		teacher.setIndenticatorNumber(indetificationFormField.getText());
		teacher.setName(nameFormField.getText());
		teacher.setPhone(phoneNumberFormField.getText());
		teacher.setTrainingArea(trainingAreaFormField.getText());
		teacher.setYearsOfExperience(Integer.parseInt(yearsOfExperienceFormField.getText()));
		
		System.out.println("Confirm Button Pressed");
		System.out.println(teacher);

		dispose();
	}
}
