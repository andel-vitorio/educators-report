package app.frontend.screens.subjects.forms;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.geom.RoundRectangle2D;
import java.time.LocalTime;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import app.frontend.components.Button;
import app.frontend.components.Button.ButtonType;
import app.frontend.components.FormField;
import res.fonts.FontsManager;
import res.fonts.FontsManager.FontType;
import res.values.ColorsManager;
import res.values.DimensManager;
import utils.ComponentDecorator;

import app.backend.entities.Subjects;
import app.backend.services.SubjectsService;
import app.frontend.components.TimePicker;
import app.frontend.models.SubjectTableModel;

public class SubjectForm extends JFrame {

	private Container container;
	private boolean isEditable = true;

	private FormField codeFormField;
	private FormField nameFormField;
	private FormField descriptionFormField;
	private TimePicker startTimePicker;
	private TimePicker endTimePicker;
	private FormField classroomFormField;
	private FormField teacherNameFormField;
	private FormField requirementsFormField;
	private FormField courseLoadFormField;
	private FormField creditsFormField;
	private FormField numberOfVacanciesFormField;

	public enum SubjectActionType {
		EDIT_SUBJECT, ADD_SUBJECT, INFO_SUBJECT
	}

	SubjectTableModel subjectTableModel;
	SubjectActionType actionType;
	Subjects subject;

	public SubjectForm(SubjectTableModel subjectTableModel, SubjectActionType actionType) {
		super("");

		String title;

		this.subjectTableModel = subjectTableModel;
		this.actionType = actionType;

		if (actionType == SubjectActionType.EDIT_SUBJECT)
			title = "Editar Informações";
		else if (actionType == SubjectActionType.ADD_SUBJECT)
			title = "Cadastrar Disciplina";
		else {
			title = "Dados da Disciplina";
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

	public SubjectForm(SubjectTableModel subjectTableModel, SubjectActionType actionType, Subjects subject) {
		this(subjectTableModel, actionType);
		codeFormField.setText(subject.getCode());
		nameFormField.setText(subject.getName());
		descriptionFormField.setText(subject.getDescription());
		startTimePicker.setTime(subject.getStartTime());
		endTimePicker.setTime(subject.getEndTime());
		teacherNameFormField.setText(subject.getTeacherName());
		requirementsFormField.setText(subject.getRequirements());
		courseLoadFormField.setText(subject.getCourseLoad());
		creditsFormField.setText(subject.getCredits());
		numberOfVacanciesFormField.setText(subject.getNumberOfVacancies());
		classroomFormField.setText(subject.getClassroom());
		this.subject = subject;
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
		constraints.gridwidth = 3;
		constraints.gridx = 0;
		constraints.gridy = y++;

		formContainer.add(getSectionLabel("Dados da Disciplina"), constraints);

		constraints.gridwidth = 1;
		constraints.weightx = 0.5;
		constraints.gridy = y;
		constraints.insets = new Insets(0, 0, 16, 12);
		codeFormField = new FormField("Código da Disciplina", 0);
		codeFormField.setEditable(isEditable);
		formContainer.add(codeFormField, constraints);

		constraints.insets = new Insets(0, 12, 16, 0);
		constraints.gridwidth = 2;
		constraints.gridx = 1;
		constraints.gridy = y++;
		nameFormField = new FormField("Nome da disciplina", 0);
		nameFormField.setEditable(isEditable);
		formContainer.add(nameFormField, constraints);

		constraints.insets = new Insets(0, 0, 16, 0);
		constraints.gridy = y++;
		constraints.gridx = 0;
		constraints.gridwidth = 3;
		constraints.fill = GridBagConstraints.BOTH;
		descriptionFormField = new FormField("Descrição", 504);
		descriptionFormField.setHeight(100);
		descriptionFormField.setEditable(isEditable);
		descriptionFormField.isMultiline(true);
		formContainer.add(descriptionFormField, constraints);

		constraints.insets = new Insets(0, 0, 0, 0);
		constraints.gridwidth = 3;
		constraints.gridheight = 1;
		constraints.gridx = 0;
		constraints.gridy = y++;

		formContainer.add(getSectionLabel("Horário e Local"), constraints);

		constraints.gridwidth = 1;
		constraints.weightx = 0.5;
		constraints.gridy = y;
		constraints.insets = new Insets(0, 0, 16, 12);
		startTimePicker = new TimePicker("Início", 0);
		startTimePicker.setEditable(isEditable);
		formContainer.add(startTimePicker, constraints);

		constraints.gridwidth = 1;
		constraints.weightx = 0.5;
		constraints.gridx = 1;
		constraints.gridy = y;
		constraints.insets = new Insets(0, 0, 16, 12);
		endTimePicker = new TimePicker("Fim", 0);
		endTimePicker.setEditable(isEditable);
		formContainer.add(endTimePicker, constraints);

		constraints.insets = new Insets(0, 12, 16, 0);
		constraints.gridwidth = 1;
		constraints.gridx = 2;
		constraints.gridy = y++;
		classroomFormField = new FormField("Sala", 0);
		classroomFormField.setEditable(isEditable);
		formContainer.add(classroomFormField, constraints);

		constraints.insets = new Insets(0, 0, 0, 0);
		constraints.gridwidth = 3;
		constraints.gridheight = 1;
		constraints.gridx = 0;
		constraints.gridy = y++;

		formContainer.add(getSectionLabel("Informações do Professor"), constraints);

		constraints.insets = new Insets(0, 0, 16, 0);
		constraints.gridy = y++;
		constraints.gridx = 0;
		constraints.gridwidth = 3;

		teacherNameFormField = new FormField("Nome do Professor", 504);
		teacherNameFormField.setEditable(isEditable);
		formContainer.add(teacherNameFormField, constraints);

		constraints.insets = new Insets(0, 0, 0, 0);
		constraints.gridwidth = 3;
		constraints.gridheight = 1;
		constraints.gridx = 0;
		constraints.gridy = y++;

		formContainer.add(getSectionLabel("Outras Informações"), constraints);

		constraints.insets = new Insets(0, 0, 16, 0);
		constraints.gridy = y++;
		constraints.gridx = 0;
		constraints.gridwidth = 3;

		requirementsFormField = new FormField("Pré-requisitos", 504);
		requirementsFormField.setEditable(isEditable);
		formContainer.add(requirementsFormField, constraints);

		constraints.gridwidth = 1;
		constraints.weightx = 0.5;
		constraints.gridy = y;
		constraints.insets = new Insets(0, 0, 16, 12);
		courseLoadFormField = new FormField("Carga Horária", 0);
		courseLoadFormField.setEditable(isEditable);
		formContainer.add(courseLoadFormField, constraints);

		constraints.insets = new Insets(0, 12, 16, 0);
		constraints.gridwidth = 1;
		constraints.gridx = 1;
		constraints.gridy = y;
		creditsFormField = new FormField("Créditos", 0);
		creditsFormField.setEditable(isEditable);
		formContainer.add(creditsFormField, constraints);

		constraints.insets = new Insets(0, 12, 16, 0);
		constraints.gridwidth = 1;
		constraints.gridx = 2;
		constraints.gridy = y++;
		numberOfVacanciesFormField = new FormField("Número de Vagas", 0);
		numberOfVacanciesFormField.setEditable(isEditable);
		formContainer.add(numberOfVacanciesFormField, constraints);

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

			if ( actionType == SubjectActionType.ADD_SUBJECT )
				subject = new Subjects();

			String code = codeFormField.getText();
			String name = nameFormField.getText();
			String description = descriptionFormField.getText();
			String teacherName = teacherNameFormField.getText();
			String requirements = requirementsFormField.getText();
			String classroom = classroomFormField.getText();

			LocalTime startTime = startTimePicker.getTime();
			LocalTime endTime = endTimePicker.getTime();

			int courseLoad = Integer.parseInt(courseLoadFormField.getText());
			int credits = Integer.parseInt(creditsFormField.getText());
			int numberOfVacancies = Integer.parseInt(numberOfVacanciesFormField.getText());

			subject.setCode(code);
			subject.setName(name);
			subject.setDescription(description);
			subject.setStartTime(startTime);
			subject.setEndTime(endTime);
			subject.setTeacherName(teacherName);
			subject.setRequirements(requirements);
			subject.setCourseLoad(courseLoad);
			subject.setCredits(credits);
			subject.setNumberOfVacancies(numberOfVacancies);
			subject.setClassroom(classroom);

			if ( actionType == SubjectActionType.ADD_SUBJECT ) SubjectsService.postSubject(subject);
			else if ( actionType == SubjectActionType.EDIT_SUBJECT ) SubjectsService.updateSubjectById(subject.getId(), subject);

			subjectTableModel.setSubjectsList(SubjectsService.getSubjects());
		}

		dispose();
	}
}
