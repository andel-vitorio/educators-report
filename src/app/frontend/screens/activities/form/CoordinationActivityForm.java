package app.frontend.screens.activities.form;

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
import java.time.LocalDate;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import app.frontend.components.Button;
import app.frontend.components.Button.ButtonType;
import app.frontend.components.DatePicker;
import app.frontend.components.FormField;
import res.fonts.FontsManager;
import res.fonts.FontsManager.FontType;
import res.values.ColorsManager;
import res.values.DimensManager;
import utils.ComponentDecorator;
import app.backend.entities.CoordinationActivity;
import app.backend.entities.Teacher;
import app.backend.services.CoordinationActivityService;
import app.backend.services.TeacherService;
import app.frontend.models.CoordinationActivityTableModel;
import app.frontend.screens.teachers.forms.TeacherForm.ActionType;

import java.util.*;

public class CoordinationActivityForm extends JFrame {

	private Container container;
	private boolean isEditable = true;

	private FormField activityTitleFormField;
	private FormField nameOfPersonResponsibleFormField;
	private DatePicker startDatePicker;
	private DatePicker endDatePicker;
	private FormField priorityFormField;
	private FormField statusFormField;
	private FormField descriptionFormField;

	Teacher teacher;

	public enum CoordinationActivityActionType {
		EDIT_ACTIVITY, ADD_ACTIVITY, INFO_ACTIVITY
	}
	
	CoordinationActivity coordinationActivity;
	CoordinationActivityActionType coordinationActivityActionType;
	CoordinationActivityTableModel coordinationActivityTableModel;

	public CoordinationActivityForm(CoordinationActivityTableModel coordinationActivityTableModel, CoordinationActivityActionType actionType, Teacher teacher) {
		this(coordinationActivityTableModel, actionType);
		this.teacher = teacher;
		nameOfPersonResponsibleFormField.setText(teacher.getName());
		nameOfPersonResponsibleFormField.setEditable(false);
	}

	public CoordinationActivityForm(CoordinationActivityTableModel coordinationActivityTableModel, CoordinationActivityActionType actionType) {
		super("");

		String title;

		this.coordinationActivityActionType = actionType;
		this.coordinationActivityTableModel = coordinationActivityTableModel;

		if (actionType == CoordinationActivityActionType.EDIT_ACTIVITY)
			title = "Editar Informações";
		else if (actionType == CoordinationActivityActionType.ADD_ACTIVITY)
			title = "Cadastrar Atividade da Coordenação";
		else {
			title = "Dados da Atividade da Coordenação";
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

	public CoordinationActivityForm(CoordinationActivityTableModel coordinationActivityTableModel, CoordinationActivityActionType actionType,
			CoordinationActivity activity, Teacher teacher) {
				this(coordinationActivityTableModel, actionType, activity);
				this.teacher = teacher;
				if ( teacher != null ) {
					nameOfPersonResponsibleFormField.setText(teacher.getName());
					nameOfPersonResponsibleFormField.setEditable(false);
				}
			}

	public CoordinationActivityForm(CoordinationActivityTableModel coordinationActivityTableModel, CoordinationActivityActionType actionType,
			CoordinationActivity activity) {
		this(coordinationActivityTableModel, actionType);
		
		this.coordinationActivity = activity;

		activityTitleFormField.setText(activity.getActivityTitle());
		nameOfPersonResponsibleFormField.setText(activity.getNameOfPersonResponsible());
		priorityFormField.setText(activity.getPriority());
		statusFormField.setText(activity.getStatus());
		descriptionFormField.setText(activity.getDescription());
		startDatePicker.setDate(activity.getStartDate());
		endDatePicker.setDate(activity.getEndDate());
	}

	private JScrollPane getFormContainer() {
		JPanel formContainer = new JPanel(new GridBagLayout());
		ComponentDecorator.addPadding(formContainer, 0, 24);
		formContainer.setOpaque(false);
		int y = 0;

		GridBagConstraints constraints = new GridBagConstraints();

		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.anchor = GridBagConstraints.PAGE_START;
		constraints.gridwidth = 2;
		constraints.gridx = 0;
		constraints.gridy = y++;
		constraints.weightx = 1.0;

		formContainer.add(getSectionLabel("Informações Gerais"), constraints);

		constraints.insets = new Insets(0, 0, 16, 0);
		constraints.gridwidth = 2;
		constraints.gridx = 0;
		constraints.gridy = y++;
		activityTitleFormField = new FormField("Título da Atividade", 0);
		activityTitleFormField.setEditable(isEditable);
		formContainer.add(activityTitleFormField, constraints);

		constraints.insets = new Insets(0, 0, 16, 0);
		constraints.gridwidth = 2;
		constraints.gridx = 0;
		constraints.gridy = y++;
		nameOfPersonResponsibleFormField = new FormField("Nome Completo do Rensponsável", 0);
		nameOfPersonResponsibleFormField.setEditable(isEditable);

		formContainer.add(nameOfPersonResponsibleFormField, constraints);

		constraints.gridwidth = 1;
		constraints.weightx = 0.5;
		constraints.gridy = y;
		constraints.insets = new Insets(0, 0, 16, 12);
		startDatePicker = new DatePicker("Data de Início", 0);
		startDatePicker.setEditable(isEditable);
		formContainer.add(startDatePicker, constraints);

		constraints.insets = new Insets(0, 12, 16, 0);
		constraints.gridwidth = 1;
		constraints.gridx = 1;
		constraints.gridy = y++;
		endDatePicker = new DatePicker("Data de Fim", 0);
		endDatePicker.setEditable(isEditable);
		formContainer.add(endDatePicker, constraints);

		constraints.gridwidth = 1;
		constraints.weightx = 0.5;
		constraints.gridy = y;
		constraints.gridx = 0;
		constraints.insets = new Insets(0, 0, 16, 12);
		priorityFormField = new FormField("Prioridade", 0);
		priorityFormField.setEditable(isEditable);
		formContainer.add(priorityFormField, constraints);

		constraints.insets = new Insets(0, 12, 16, 0);
		constraints.gridwidth = 1;
		constraints.gridx = 1;
		constraints.gridy = y++;
		statusFormField = new FormField("Status", 0);
		statusFormField.setEditable(isEditable);
		formContainer.add(statusFormField, constraints);

		constraints.insets = new Insets(0, 0, 16, 0);
		constraints.gridy = y++;
		constraints.gridx = 0;
		constraints.gridwidth = 2;
		constraints.fill = GridBagConstraints.BOTH;
		descriptionFormField = new FormField("Descrição", 504);
		descriptionFormField.setHeight(120);
		descriptionFormField.setEditable(isEditable);
		descriptionFormField.isMultiline(true);
		formContainer.add(descriptionFormField, constraints);

		GridBagConstraints fillerConstraints = new GridBagConstraints();
		fillerConstraints.fill = GridBagConstraints.BOTH;
		fillerConstraints.weighty = 1.0;
		fillerConstraints.gridwidth = 2;
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

			if ( coordinationActivityActionType == CoordinationActivityActionType.ADD_ACTIVITY )
				coordinationActivity = new CoordinationActivity();

			String activityTitle = activityTitleFormField.getText();
			String nameOfPersonResponsible = nameOfPersonResponsibleFormField.getText();
			String priority = priorityFormField.getText();
			String status = statusFormField.getText();
			String description = descriptionFormField.getText();

			LocalDate startDate = startDatePicker.getDate();
			LocalDate endDate = endDatePicker.getDate();

			coordinationActivity.setActivityTitle(activityTitle);
			coordinationActivity.setNameOfPersonResponsible(nameOfPersonResponsible);
			coordinationActivity.setPriority(priority);
			coordinationActivity.setStatus(status);
			coordinationActivity.setDescription(description);
			coordinationActivity.setStartDate(startDate);
			coordinationActivity.setEndDate(endDate);

			if ( coordinationActivityActionType == CoordinationActivityActionType.ADD_ACTIVITY ) CoordinationActivityService.postCoordinationActivity(coordinationActivity);
			else if ( coordinationActivityActionType == CoordinationActivityActionType.EDIT_ACTIVITY ) CoordinationActivityService.updateCoordinationActivityById(coordinationActivity.getId(), coordinationActivity);

			if (teacher == null) coordinationActivityTableModel.setCoordinationActivityList(CoordinationActivityService.getCoordinationActivities());
			else {
				ArrayList<CoordinationActivity> coordinationActivities = CoordinationActivityService
						.getActivitiesByNameOfPersonResponsible(teacher.getName());
				if (coordinationActivities != null)
					coordinationActivityTableModel.setCoordinationActivityList(coordinationActivities);
			}

			
		}

		dispose();
	}
}
