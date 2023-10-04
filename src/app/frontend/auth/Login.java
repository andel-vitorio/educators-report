package app.frontend.auth;

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
import utils.Observable;
import app.backend.Database;

/**
 * Janela de autenticação para acesso ao sistema.
 * Esta classe representa a tela de login onde o usuário insere suas credenciais.
 */
public class Login extends JFrame {

	private Container container;
	private boolean isEditable = true;

	private FormField userFormField;
	private FormField passwordFormField;

	private Observable observable = new Observable();

	/**
     * Construtor padrão para a tela de login.
     * Inicializa a tela de autenticação com os componentes necessários.
     */
	public Login() {
		super("Autenticação");

		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setPreferredSize(new Dimension(400, 400));
		setSize(getPreferredSize());
		setResizable(false);
		setLocationRelativeTo(null);

		container = getContentPane();
		container.setBackground(ColorsManager.getOnBackgroundColor());
		container.setLayout(new BorderLayout());

		JLabel titleLabel = new JLabel("Autenticação", JLabel.LEFT);
		titleLabel.setFont(FontsManager.getFont(FontType.BOLD, DimensManager.getTitleFormsFontsize()));
		titleLabel.setBackground(ColorsManager.getBackgroundColor());
		ComponentDecorator.addPadding(titleLabel, 24);

		container.add(titleLabel, BorderLayout.PAGE_START);
		container.add(getFormContainer(), BorderLayout.CENTER);
		container.add(getButtonsContainer(), BorderLayout.PAGE_END);

		setVisible(true);
	}
	/**
     * Retorna um JScrollPane contendo o formulário de autenticação.
     * 
     * @return JScrollPane - painel de rolagem contendo os campos do formulário.
     */
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

		formContainer.add(getSectionLabel("Entrar"), constraints);

		constraints.insets = new Insets(0, 0, 16, 0);
		constraints.gridwidth = 2;
		constraints.gridx = 0;
		constraints.gridy = y++;
		userFormField = new FormField("Usuário", 0);
		userFormField.setEditable(isEditable);
		formContainer.add(userFormField, constraints);

		constraints.insets = new Insets(0, 0, 16, 0);
		constraints.gridwidth = 2;
		constraints.gridx = 0;
		constraints.gridy = y++;
		passwordFormField = new FormField("Senha", 0);
		passwordFormField.setEditable(isEditable);
		formContainer.add(passwordFormField, constraints);


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

	/**
     * Cria um JLabel estilizado para exibir uma seção do formulário.
     * 
     * @param text O texto da seção.
     * @return JLabel - label estilizado para a seção.
     */
	private JLabel getSectionLabel(String text) {
		JLabel label = new JLabel(text, JLabel.LEFT);
		label.setFont(FontsManager.getFont(FontType.SEMI_BOLD, DimensManager.getSectionLabelFontsize()));
		label.setOpaque(false);
		ComponentDecorator.addPaddingBottom(label, 12);
		return label;
	}

	/**
     * Retorna um JPanel contendo os botões da tela de login.
     * 
     * @return JPanel - painel contendo os botões.
     */
	private JPanel getButtonsContainer() {
		JPanel buttonsContainer = new JPanel();
		buttonsContainer.setLayout(new FlowLayout(FlowLayout.RIGHT, 24, 0));
		buttonsContainer.setOpaque(false);

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

	/**
     * Método para confirmar a autenticação.
     * Inicia uma nova conexão com o banco de dados usando as credenciais fornecidas.
     * Notifica os observadores e fecha a janela de login após a confirmação.
     */
	private void confirm() {
		new Database(userFormField.getText(), passwordFormField.getText());
		observable.notifyObservers("confirmed-login");
		dispose();
	}

	/**
     * Obtém o observável associado à tela de login.
     * 
     * @return Observable - observável que notifica mudanças.
     */
	public Observable getObservable() {
		return observable;
	}
}
