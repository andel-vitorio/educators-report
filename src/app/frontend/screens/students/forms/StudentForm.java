package app.frontend.screens.students.forms;

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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;
import java.text.Normalizer.Form;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JViewport;
import javax.swing.event.ChangeListener;

import app.backend.entities.Teacher;
import app.backend.entities.UndergraduateStudent;
import app.frontend.components.Button;
import app.frontend.components.ComboBox;
import app.frontend.components.Button.ButtonType;
import app.frontend.components.DatePicker;
import app.frontend.components.FormField;
import app.frontend.models.TeacherTableModel;
import res.fonts.FontsManager;
import res.fonts.FontsManager.FontType;
import res.values.ColorsManager;
import res.values.DimensManager;
import utils.ComponentDecorator;
import app.backend.entities.PosgraduateStudent;
import app.backend.entities.Student;
import app.backend.entities.Teacher;
import app.frontend.components.Button;
import app.frontend.components.Button.ButtonType;
import app.frontend.components.TimePicker;
import app.frontend.components.FormField;
import app.frontend.models.StudentsTableModel;
import app.frontend.models.StudentsTableModel;
import app.frontend.models.TeacherTableModel;
import res.fonts.FontsManager;
import res.fonts.FontsManager.FontType;
import res.values.ColorsManager;
import res.values.DimensManager;
import utils.ComponentDecorator;

public class StudentForm extends JFrame {

	private Container container;
	private boolean isEditable = true;

	private FormField nameFormField;
	private DatePicker dateOfEntryDatePicker;
	private FormField registrationFormField;
	private FormField emailFormField;
	private FormField phoneNumberFormField;
	private FormField nameOfMenteeFormField;
	private FormField statusFormField;
	private FormField projectNameFormField;
	private FormField typeOfOrientationFormField;
	private FormField posgraduateProgramFormField;
	private FormField researchTitleFormField;
	private DatePicker defenseDateDatePicker;

	private ComboBox typeStudent;

	public enum StudentActionType {
		EDIT_STUDENT, ADD_STUDENT, INFO_STUDENT
	}

	public StudentForm(StudentsTableModel studentsTableModel, StudentActionType actionType) {
		super("");

		String title;

		if (actionType == StudentActionType.EDIT_STUDENT)
			title = "Editar Informações";
		else if (actionType == StudentActionType.ADD_STUDENT)
			title = "Cadastrar Aluno";
		else {
			title = "Dados do Aluno";
			isEditable = false;
		}

		setTitle(title);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setPreferredSize(new Dimension(600, 650));
		setSize(getPreferredSize());
		setResizable(false);
		setLocationRelativeTo(null);

		container = getContentPane();
		container.setBackground(ColorsManager.getOnBackgroundColor());
		container.setLayout(new BorderLayout());

		JLabel titleLabel = new JLabel(title, JLabel.LEFT);
		titleLabel.setFont(FontsManager.getFont(FontType.BOLD, DimensManager.getTitleFormsFontsize()));
		titleLabel.setBackground(ColorsManager.getBackgroundColor());
		ComponentDecorator.addPadding(titleLabel, 24);

		container.add(titleLabel, BorderLayout.PAGE_START);
		container.add(getFormContainer(), BorderLayout.CENTER);
		container.add(getButtonsContainer(), BorderLayout.PAGE_END);

		setVisible(true);
	}

	public StudentForm(StudentsTableModel studentsTableModel, StudentActionType actionType,
			Student student) {
		this(studentsTableModel, actionType);

		if ( actionType == StudentActionType.EDIT_STUDENT )
				typeStudent.setEnabled(false);
		
		if (student instanceof UndergraduateStudent) {
			typeStudent.setSelectedIndex(1);
			ActionEvent event = new ActionEvent(typeStudent, ActionEvent.ACTION_PERFORMED, "Alunos de Graduação");
			typeStudent.getActionListeners()[0].actionPerformed(event);

			while ( projectNameFormField == null );

			UndergraduateStudent undergraduateStudent = (UndergraduateStudent) student;
			projectNameFormField.setText(undergraduateStudent.getProjectName());
			typeOfOrientationFormField.setText(undergraduateStudent.getTypeOfOrientation());
		} else if (student instanceof PosgraduateStudent) {
			typeStudent.setSelectedIndex(2);
			ActionEvent event = new ActionEvent(typeStudent, ActionEvent.ACTION_PERFORMED, "Alunos de Pós-graduação");
			typeStudent.getActionListeners()[0].actionPerformed(event);
			PosgraduateStudent posgraduateStudent = (PosgraduateStudent) student;
			posgraduateProgramFormField.setText(posgraduateStudent.getPosgraduateProgram());
			researchTitleFormField.setText(posgraduateStudent.getResearchTitle());
			defenseDateDatePicker.setDate(posgraduateStudent.getDefenseDate());
		}
		nameFormField.setText(student.getName());
		dateOfEntryDatePicker.setDate(student.getDateOfEntry());
		registrationFormField.setText(student.getRegistration());
		emailFormField.setText(student.getEmail());
		phoneNumberFormField.setText(student.getPhoneNumber());
		nameOfMenteeFormField.setText(student.getNameOfMentee());
		statusFormField.setText(student.getStatus());
	}

	private JScrollPane getFormContainer() {
		JPanel formContainer = new JPanel(new GridBagLayout());
		ComponentDecorator.addPadding(formContainer, 0, 24);
		formContainer.setOpaque(false);
		int y = 0;

		GridBagConstraints constraints = new GridBagConstraints();

		// Seção: Informações Pessoais
		constraints.fill = GridBagConstraints.BOTH;
		constraints.anchor = GridBagConstraints.PAGE_START;
		constraints.gridwidth = 2;
		constraints.gridx = 0;
		constraints.gridy = y++;
		constraints.insets = new Insets(0, 0, 16, 12);

		typeStudent = new ComboBox(
				new String[] { "Escolha o tipo de aluno", "Aluno de Graduação", "Aluno de Pós-graduação" });
		typeStudent.setFont(FontsManager.getFont(FontType.MEDIUM, 12));
		typeStudent.setEditable(isEditable);
		formContainer.add(typeStudent, constraints);

		formContainer.add(getSectionLabel("Informações Pessoais"), constraints);

		constraints.gridwidth = 2;
		constraints.gridy = y++;
		constraints.insets = new Insets(0, 0, 16, 12);
		nameFormField = new FormField("Nome completo do aluno", 0);
		nameFormField.setEditable(isEditable);
		formContainer.add(nameFormField, constraints);

		constraints.gridwidth = 1;
		constraints.gridy = y;
		constraints.weightx = 1.0;
		constraints.insets = new Insets(0, 0, 16, 12);
		dateOfEntryDatePicker = new DatePicker("Data de Ingresso", 0);
		dateOfEntryDatePicker.setEditable(isEditable);
		formContainer.add(dateOfEntryDatePicker, constraints);

		constraints.gridwidth = 1;
		constraints.gridx = 1;
		constraints.gridy = y++;
		constraints.weightx = 1.0;
		constraints.insets = new Insets(0, 0, 16, 12);
		registrationFormField = new FormField("Matrícula", 0);
		registrationFormField.setEditable(isEditable);
		formContainer.add(registrationFormField, constraints);

		constraints.gridwidth = 2;
		constraints.gridx = 0;
		constraints.gridy = y++;
		constraints.weightx = 1.0;

		formContainer.add(getSectionLabel("Informações de Contato"), constraints);

		constraints.gridwidth = 2;
		constraints.weightx = 1.0;
		constraints.gridy = y++;
		constraints.insets = new Insets(0, 0, 16, 12);
		emailFormField = new FormField("E-mail", 0);
		emailFormField.setEditable(isEditable);
		formContainer.add(emailFormField, constraints);

		constraints.gridwidth = 2;
		constraints.weightx = 1.0;
		constraints.gridy = y++;
		constraints.insets = new Insets(0, 0, 16, 12);
		phoneNumberFormField = new FormField("Número de Telefone", 0);
		phoneNumberFormField.setEditable(isEditable);
		formContainer.add(phoneNumberFormField, constraints);

		constraints.gridwidth = 2;
		constraints.gridx = 0;
		constraints.gridy = y++;

		formContainer.add(getSectionLabel("Informações sobre a Orientação"), constraints);

		typeStudent.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String selectedType = (String) typeStudent.getSelectedItem();
				formContainer.removeAll();

				if ("Aluno de Graduação".equals(selectedType)) {
					int y = 0;

					GridBagConstraints constraints = new GridBagConstraints();

					// Seção: Informações Pessoais
					constraints.fill = GridBagConstraints.BOTH;
					constraints.anchor = GridBagConstraints.PAGE_START;
					constraints.gridwidth = 2;
					constraints.gridx = 0;
					constraints.gridy = y++;
					constraints.insets = new Insets(0, 0, 16, 12);

					typeStudent.setFont(FontsManager.getFont(FontType.MEDIUM, 12));
					typeStudent.setEditable(isEditable);
					formContainer.add(typeStudent, constraints);

					formContainer.add(getSectionLabel("Informações Pessoais"), constraints);

					constraints.gridwidth = 2;
					constraints.gridy = y++;
					constraints.insets = new Insets(0, 0, 16, 12);
					nameFormField = new FormField("Nome completo do aluno", 0);
					nameFormField.setEditable(isEditable);
					formContainer.add(nameFormField, constraints);

					constraints.gridwidth = 1;
					constraints.gridy = y;
					constraints.weightx = 1.0;
					constraints.insets = new Insets(0, 0, 16, 12);
					dateOfEntryDatePicker = new DatePicker("Data de Ingresso", 0);
					dateOfEntryDatePicker.setEditable(isEditable);
					formContainer.add(dateOfEntryDatePicker, constraints);

					constraints.gridwidth = 1;
					constraints.gridx = 1;
					constraints.gridy = y++;
					constraints.weightx = 1.0;
					constraints.insets = new Insets(0, 0, 16, 12);
					registrationFormField = new FormField("Matrícula", 0);
					registrationFormField.setEditable(isEditable);
					formContainer.add(registrationFormField, constraints);

					constraints.gridwidth = 2;
					constraints.gridx = 0;
					constraints.gridy = y++;
					constraints.weightx = 1.0;

					formContainer.add(getSectionLabel("Informações de Contato"), constraints);

					constraints.gridwidth = 2;
					constraints.weightx = 1.0;
					constraints.gridy = y++;
					constraints.insets = new Insets(0, 0, 16, 12);
					emailFormField = new FormField("E-mail", 0);
					emailFormField.setEditable(isEditable);
					formContainer.add(emailFormField, constraints);

					constraints.gridwidth = 2;
					constraints.weightx = 1.0;
					constraints.gridy = y++;
					constraints.insets = new Insets(0, 0, 16, 12);
					phoneNumberFormField = new FormField("Número de Telefone", 0);
					phoneNumberFormField.setEditable(isEditable);
					formContainer.add(phoneNumberFormField, constraints);

					constraints.gridwidth = 2;
					constraints.gridx = 0;
					constraints.gridy = y++;

					formContainer.add(getSectionLabel("Informações sobre a Orientação"), constraints);

					constraints.gridwidth = 2;
					constraints.weightx = 1.0;
					constraints.gridy = y++;
					constraints.insets = new Insets(0, 0, 16, 12);
					nameOfMenteeFormField = new FormField("Nome do Orientador", 0);
					nameOfMenteeFormField.setEditable(isEditable);
					formContainer.add(nameOfMenteeFormField, constraints);

					constraints.gridwidth = 2;
					constraints.weightx = 1.0;
					constraints.gridy = y++;
					constraints.insets = new Insets(0, 0, 16, 12);
					projectNameFormField = new FormField("Nome do Projeto", 0);
					projectNameFormField.setEditable(isEditable);
					formContainer.add(projectNameFormField, constraints);

					constraints.gridwidth = 1;
					constraints.weightx = 1.0;
					constraints.gridy = y;
					constraints.insets = new Insets(0, 0, 16, 12);
					typeOfOrientationFormField = new FormField("Tipos de Orientação", 0);
					typeOfOrientationFormField.setEditable(isEditable);
					formContainer.add(typeOfOrientationFormField, constraints);

					constraints.gridwidth = 1;
					constraints.weightx = 1.0;
					constraints.gridy = y++;
					constraints.gridx = 1;
					constraints.insets = new Insets(0, 0, 16, 12);
					statusFormField = new FormField("Status", 0);
					statusFormField.setEditable(isEditable);
					formContainer.add(statusFormField, constraints);

					GridBagConstraints fillerConstraints = new GridBagConstraints();
					fillerConstraints.fill = GridBagConstraints.BOTH;
					fillerConstraints.weighty = 1.0;
					fillerConstraints.gridwidth = 3;
					fillerConstraints.gridx = 0;
					fillerConstraints.gridy = y++;

					JPanel filler = new JPanel();
					filler.setOpaque(false);

					formContainer.add(filler, fillerConstraints);
				} else if ("Aluno de Pós-graduação".equals(selectedType)) {
					int y = 0;

					GridBagConstraints constraints = new GridBagConstraints();

					// Seção: Informações Pessoais
					constraints.fill = GridBagConstraints.BOTH;
					constraints.anchor = GridBagConstraints.PAGE_START;
					constraints.gridwidth = 2;
					constraints.gridx = 0;
					constraints.gridy = y++;
					constraints.insets = new Insets(0, 0, 16, 12);

					typeStudent.setFont(FontsManager.getFont(FontType.MEDIUM, 12));
					typeStudent.setEditable(isEditable);
					formContainer.add(typeStudent, constraints);

					formContainer.add(getSectionLabel("Informações Pessoais"), constraints);

					constraints.gridwidth = 2;
					constraints.gridy = y++;
					constraints.insets = new Insets(0, 0, 16, 12);
					nameFormField = new FormField("Nome completo do aluno", 0);
					nameFormField.setEditable(isEditable);
					formContainer.add(nameFormField, constraints);

					constraints.gridwidth = 1;
					constraints.gridy = y;
					constraints.weightx = 1.0;
					constraints.insets = new Insets(0, 0, 16, 12);
					dateOfEntryDatePicker = new DatePicker("Data de Ingresso", 0);
					dateOfEntryDatePicker.setEditable(isEditable);
					formContainer.add(dateOfEntryDatePicker, constraints);

					constraints.gridwidth = 1;
					constraints.gridx = 1;
					constraints.gridy = y++;
					constraints.weightx = 1.0;
					constraints.insets = new Insets(0, 0, 16, 12);
					registrationFormField = new FormField("Matrícula", 0);
					registrationFormField.setEditable(isEditable);
					formContainer.add(registrationFormField, constraints);

					constraints.gridwidth = 2;
					constraints.gridx = 0;
					constraints.gridy = y++;
					constraints.weightx = 1.0;

					formContainer.add(getSectionLabel("Informações de Contato"), constraints);

					constraints.gridwidth = 2;
					constraints.weightx = 1.0;
					constraints.gridy = y++;
					constraints.insets = new Insets(0, 0, 16, 12);
					emailFormField = new FormField("E-mail", 0);
					emailFormField.setEditable(isEditable);
					formContainer.add(emailFormField, constraints);

					constraints.gridwidth = 2;
					constraints.weightx = 1.0;
					constraints.gridy = y++;
					constraints.insets = new Insets(0, 0, 16, 12);
					phoneNumberFormField = new FormField("Número de Telefone", 0);
					phoneNumberFormField.setEditable(isEditable);
					formContainer.add(phoneNumberFormField, constraints);

					constraints.gridwidth = 2;
					constraints.gridx = 0;
					constraints.gridy = y++;

					formContainer.add(getSectionLabel("Informações sobre a Orientação"), constraints);

					constraints.gridwidth = 2;
					constraints.weightx = 1.0;
					constraints.gridy = y++;
					constraints.insets = new Insets(0, 0, 16, 12);
					nameOfMenteeFormField = new FormField("Nome do Orientador", 0);
					nameOfMenteeFormField.setEditable(isEditable);
					formContainer.add(nameOfMenteeFormField, constraints);

					constraints.gridwidth = 2;
					constraints.weightx = 1.0;
					constraints.gridy = y++;
					constraints.insets = new Insets(0, 0, 16, 12);
					posgraduateProgramFormField = new FormField("Programa de Pós-graduação", 0);
					posgraduateProgramFormField.setEditable(isEditable);
					formContainer.add(posgraduateProgramFormField, constraints);

					constraints.gridwidth = 2;
					constraints.weightx = 1.0;
					constraints.gridy = y++;
					constraints.insets = new Insets(0, 0, 16, 12);
					researchTitleFormField = new FormField("Título da Pesquisa", 0);
					researchTitleFormField.setEditable(isEditable);
					formContainer.add(researchTitleFormField, constraints);

					constraints.gridwidth = 1;
					constraints.weightx = 1.0;
					constraints.gridy = y;
					constraints.insets = new Insets(0, 0, 16, 12);
					defenseDateDatePicker = new DatePicker("Data de Defesa", 0);
					defenseDateDatePicker.setEditable(isEditable);
					formContainer.add(defenseDateDatePicker, constraints);

					constraints.gridwidth = 1;
					constraints.weightx = 1.0;
					constraints.gridy = y++;
					constraints.gridx = 1;
					constraints.insets = new Insets(0, 0, 16, 12);
					statusFormField = new FormField("Status", 0);
					statusFormField.setEditable(isEditable);
					formContainer.add(statusFormField, constraints);

					GridBagConstraints fillerConstraints = new GridBagConstraints();
					fillerConstraints.fill = GridBagConstraints.BOTH;
					fillerConstraints.weighty = 1.0;
					fillerConstraints.gridwidth = 3;
					fillerConstraints.gridx = 0;
					fillerConstraints.gridy = y++;

					JPanel filler = new JPanel();
					filler.setOpaque(false);

					formContainer.add(filler, fillerConstraints);
				}

				formContainer.revalidate();
				formContainer.repaint();
			}
		});

		GridBagConstraints fillerConstraints = new GridBagConstraints();
		fillerConstraints.fill = GridBagConstraints.BOTH;
		fillerConstraints.weighty = 1.0;
		fillerConstraints.gridwidth = 3;
		fillerConstraints.gridx = 0;
		fillerConstraints.gridy = y++;

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

		if (isEditable) {
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
			ComponentDecorator.addPadding(cancelButton, 6, 16);

			cancelButtonContainer.add(cancelButton);
			cancelButtonContainer.setBorder(null);

			cancelButton.addActionListener(event -> dispose());
			buttonsContainer.add(cancelButtonContainer);
		}

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
		ComponentDecorator.addPadding(confirmButton, 6, 16);

		confirmButtonContainer.add(confirmButton);
		confirmButtonContainer.setBorder(null);

		confirmButton.addActionListener(event -> confirm());

		buttonsContainer.add(confirmButtonContainer);

		ComponentDecorator.addPadding(buttonsContainer, 24, 0);

		return buttonsContainer;
	}

	private void confirm() {

		if (isEditable) {
			String selectedType = typeStudent.getSelectedItem().toString();

			if ("Aluno de Graduação".equals(selectedType)) {
				UndergraduateStudent undergraduateStudent = new UndergraduateStudent();
				undergraduateStudent.setName(nameFormField.getText());
				undergraduateStudent.setDateOfEntry(dateOfEntryDatePicker.getDate());
				undergraduateStudent.setRegistration(registrationFormField.getText());
				undergraduateStudent.setEmail(emailFormField.getText());
				undergraduateStudent.setPhoneNumber(phoneNumberFormField.getText());
				undergraduateStudent.setNameOfMentee(nameOfMenteeFormField.getText());
				undergraduateStudent.setStatus(statusFormField.getText());
				undergraduateStudent.setProjectName(projectNameFormField.getText());
				undergraduateStudent.setTypeOfOrientation(typeOfOrientationFormField.getText());

				System.out.println(undergraduateStudent);

			} else if ("Aluno de Pós-graduação".equals(selectedType)) {
				PosgraduateStudent posgraduateStudent = new PosgraduateStudent();
				posgraduateStudent.setName(nameFormField.getText());
				posgraduateStudent.setDateOfEntry(dateOfEntryDatePicker.getDate());
				posgraduateStudent.setRegistration(registrationFormField.getText());
				posgraduateStudent.setEmail(emailFormField.getText());
				posgraduateStudent.setPhoneNumber(phoneNumberFormField.getText());
				posgraduateStudent.setNameOfMentee(nameOfMenteeFormField.getText());
				posgraduateStudent.setStatus(statusFormField.getText());
				posgraduateStudent.setPosgraduateProgram(posgraduateProgramFormField.getText());
				posgraduateStudent.setResearchTitle(researchTitleFormField.getText());
				posgraduateStudent.setDefenseDate(defenseDateDatePicker.getDate());
				System.out.println(posgraduateStudent);
			}

		}

		dispose();
	}
}
