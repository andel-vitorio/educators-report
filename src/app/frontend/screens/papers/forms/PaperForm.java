package app.frontend.screens.papers.forms;

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
import app.backend.entities.Paper;
import app.frontend.models.PapersTableModel;

public class PaperForm extends JFrame {

	private Container container;
	private boolean isEditable = true;

	private FormField titleFormField;
	private FormField authorsFormField;
	private DatePicker publicationDatePicker;
	private FormField keywordsFormField;
	private FormField descriptionFormField;
	private FormField categoryFormField;
	private FormField urlFormField;

	public enum PaperActionType {
		EDIT_PAPER, ADD_PAPER, INFO_PAPER
	}

	public PaperForm(PapersTableModel paperActionType, PaperActionType actionType) {
		super("");

		String title;

		if (actionType == PaperActionType.EDIT_PAPER)
			title = "Editar Informações";
		else if (actionType == PaperActionType.ADD_PAPER)
			title = "Cadastrar Artigo";
		else {
			title = "Dados do Artigo";
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

	public PaperForm(PapersTableModel papersTableModel, PaperActionType actionType, Paper paper) {
		this(papersTableModel, actionType);
		titleFormField.setText(paper.getTitle());
		authorsFormField.setText(paper.getAuthors());
		publicationDatePicker.setDate(paper.getPublicationDate());
		keywordsFormField.setText(paper.getKeywords());
		descriptionFormField.setText(paper.getDescription());
		categoryFormField.setText(paper.getCategory());
		urlFormField.setText(paper.getUrl());
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
		titleFormField = new FormField("Título do Artigo", 0);
		titleFormField.setEditable(isEditable);
		formContainer.add(titleFormField, constraints);

		constraints.insets = new Insets(0, 0, 16, 0);
		constraints.gridy = y++;
		constraints.gridx = 0;
		constraints.gridwidth = 2;
		constraints.fill = GridBagConstraints.BOTH;
		authorsFormField = new FormField("Autor(es)", 504);
		authorsFormField.setHeight(100);
		authorsFormField.setEditable(isEditable);
		authorsFormField.isMultiline(true);
		formContainer.add(authorsFormField, constraints);

		constraints.gridwidth = 1;
		constraints.weightx = 0.5;
		constraints.gridy = y;
		constraints.insets = new Insets(0, 0, 16, 12);
		publicationDatePicker = new DatePicker("Data de Publicação", 0);
		publicationDatePicker.setEditable(isEditable);
		formContainer.add(publicationDatePicker, constraints);

		constraints.insets = new Insets(0, 12, 16, 0);
		constraints.gridwidth = 1;
		constraints.gridx = 1;
		constraints.gridy = y++;
		keywordsFormField = new FormField("Palavras-chaves", 0);
		keywordsFormField.setEditable(isEditable);
		formContainer.add(keywordsFormField, constraints);
		
		constraints.weightx = 0;
		constraints.gridwidth = 2;
		constraints.gridx = 0;
		constraints.gridy = y++;
		constraints.insets = new Insets(0, 0, 16, 0);

		formContainer.add(getSectionLabel("Conteúdo do Artigo"), constraints);

		constraints.insets = new Insets(0, 0, 16, 0);
		constraints.gridy = y++;
		constraints.gridx = 0;
		constraints.gridwidth = 2;
		constraints.fill = GridBagConstraints.BOTH;
		descriptionFormField = new FormField("Resumo/Descrição", 504);
		descriptionFormField.setHeight(120);
		descriptionFormField.setEditable(isEditable);
		descriptionFormField.isMultiline(true);
		formContainer.add(descriptionFormField, constraints);

		constraints.gridwidth = 1;
		constraints.weightx = 0.5;
		constraints.gridy = y;
		constraints.insets = new Insets(0, 0, 16, 12);
		categoryFormField = new FormField("Categoria/Tópico", 0);
		categoryFormField.setEditable(isEditable);
		formContainer.add(categoryFormField, constraints);

		constraints.insets = new Insets(0, 12, 16, 0);
		constraints.gridwidth = 1;
		constraints.gridx = 1;
		constraints.gridy = y++;
		urlFormField = new FormField("URL/Link", 0);
		urlFormField.setEditable(isEditable);
		formContainer.add(urlFormField, constraints);

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
			String title = titleFormField.getText();
			String authors = authorsFormField.getText();
			LocalDate publicationDate = publicationDatePicker.getDate();
			String keywords = keywordsFormField.getText();
			String description = descriptionFormField.getText();
			String category = categoryFormField.getText();
			String url = urlFormField.getText();

			Paper paper = new Paper();
			paper.setTitle(title);
			paper.setAuthors(authors);
			paper.setPublicationDate(publicationDate);
			paper.setKeywords(keywords);
			paper.setDescription(description);
			paper.setCategory(category);
			paper.setUrl(url);

			System.out.println(paper);
		}

		dispose();
	}
}
