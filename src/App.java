
import javax.swing.*;

import app.frontend.auth.Login;
import app.frontend.components.*;
import app.frontend.screens.activities.CoordinationActivityManager;
import app.frontend.screens.papers.PapersManager;
import app.frontend.screens.students.StudentsManager;
import app.frontend.screens.subjects.SubjectsManager;
import app.frontend.screens.teachers.TeacherManager;
import app.frontend.screens.teachers.TeachersManager;

import java.io.IOException;
import java.awt.*;

import res.fonts.FontsManager;
import res.img.ImagesManager;
import res.values.*;

public class App extends JFrame {

	private static final String TEACHER_WINDOWS_ID = "teacher-windows";
	private static final String SUBJECT_WINDOWS_ID = "subject-windows";
	private static final String STUDENT_WINDOWS_ID = "student-windows";
	private static final String PAPER_WINDOWS_ID = "paper-windows";
	private static final String ACTIVITY_WINDOWS_ID = "activity-windows";

	public App() throws IOException {
		super("Educator's Report");
		this.init();
		this.addComponents();
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

		TeachersManager teachersManager = new TeachersManager();

		JPanel windows = new JPanel();
		windows.setLayout(new CardLayout());
		windows.add(teachersManager, TEACHER_WINDOWS_ID);
		windows.add(new SubjectsManager(), SUBJECT_WINDOWS_ID);
		windows.add(new StudentsManager(), STUDENT_WINDOWS_ID);
		windows.add(new PapersManager(), PAPER_WINDOWS_ID);
		windows.add(new CoordinationActivityManager(), ACTIVITY_WINDOWS_ID);
		windows.add(new TeacherManager(), TeachersManager.ACTIVITY_TEACHER_WINDOWS_ID);
		
		Navigation navigation = new Navigation(300, 300, null);
		navigation.setItem("Professores", ImagesManager.getTeacherIcon(), TEACHER_WINDOWS_ID);
		navigation.setItem("Disciplinas", ImagesManager.getClassIcon(), SUBJECT_WINDOWS_ID);
		navigation.setItem("Alunos", ImagesManager.getStudentIcon(), STUDENT_WINDOWS_ID);
		navigation.setItem("Artigos", ImagesManager.getPaperIcon(), PAPER_WINDOWS_ID);
		navigation.setItem("Atividades", ImagesManager.getActivityIcon(), ACTIVITY_WINDOWS_ID);

		navigation.getObservable().addObserver(action -> {
			CardLayout card = (CardLayout) windows.getLayout();
			card.show(windows, action);
		});

		teachersManager.getObservable().addObserver(action -> {
			CardLayout card = (CardLayout) windows.getLayout();
			card.show(windows, action);
		});
		
		SideBar sideBar = new SideBar(260, 720);
		sideBar.setBackgroundColor(ColorsManager.getOnBackgroundColor())
				.setHeader("Educator's Report", "Versão 1.0", ImagesManager.getLogo())
				.setNavigation(navigation);

		add(sideBar.getComponent(), BorderLayout.LINE_START);
		add(windows, BorderLayout.CENTER);
	}

	public static void main(String[] args) throws IOException {
		App app = new App();
		app.setVisible(true);
		new Login();
	}
}