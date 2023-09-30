
import javax.swing.*;

import app.frontend.components.*;
import app.frontend.components.Button.ButtonInfo;
import app.frontend.screens.TeachersManager;

import java.io.IOException;
import java.util.ArrayList;
import java.awt.*;

import res.fonts.FontsManager;
import res.img.ImagesManager;
import res.values.*;

public class App extends JFrame {

	private static final String TEACHER_WINDOWS_ID = "teacher-windows";

	public App() throws IOException {
		super("Educator's Report");
		this.init();
		this.addComponents();
		// this.setExtendedState(MAXIMIZED_BOTH);
	}

	void init() {

		ImagesManager.load();
		FontsManager.load();

		this.setLayout(new BorderLayout());
		this.setMinimumSize(new Dimension(1280, 720));
		this.setPreferredSize(this.getMinimumSize());
		this.setSize(this.getPreferredSize());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBackground(ColorsManager.getBackgroundColor());
		this.setLocationRelativeTo(null);
	}

	void addComponents() throws IOException {

		Navigation navigation = new Navigation(300, 300, null);
		navigation.setItem("Professores", ImagesManager.getTeacherIcon(), "teacher");
		navigation.setItem("Disciplinas", ImagesManager.getClassIcon(), "class");
		navigation.setItem("Alunos", ImagesManager.getStudentIcon(), "student");
		navigation.setItem("Artigos", ImagesManager.getPaperIcon(), "paper");
		navigation.setItem("Atividades", ImagesManager.getActivityIcon(), "activity");

		JPanel windows = new JPanel();
		windows.setLayout(new CardLayout());
		windows.add(new TeachersManager(), TEACHER_WINDOWS_ID);

		SideBar sideBar = new SideBar(260, 720);
		sideBar.setBackgroundColor(ColorsManager.getOnBackgroundColor())
				.setHeader("Educator's Report", "Versão 1.0", ImagesManager.getLogo())
				.setNavigation(navigation);

		add(sideBar.getComponent(), BorderLayout.LINE_START);
		add(windows, BorderLayout.CENTER);
	}

	public static void main(String[] args) throws IOException {
		App snackBar = new App();
		snackBar.setVisible(true);
	}
}