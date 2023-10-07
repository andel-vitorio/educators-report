
import javax.swing.*;

import app.backend.services.CoordinationActivityService;
import app.backend.services.PaperService;
import app.backend.services.PosgraduateStudentService;
import app.backend.services.SubjectsService;
import app.backend.services.TeacherService;
import app.backend.services.UndergraduateStudentService;
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

/**
 * Classe principal da aplicação Educator's Report.
 */
public class App extends JFrame {

  // IDs das janelas utilizados para o gerenciamento de componentes
  private static final String TEACHER_WINDOWS_ID = "teacher-windows";
  private static final String SUBJECT_WINDOWS_ID = "subject-windows";
  private static final String STUDENT_WINDOWS_ID = "student-windows";
  private static final String PAPER_WINDOWS_ID = "paper-windows";
  private static final String ACTIVITY_WINDOWS_ID = "activity-windows";

  /**
   * Construtor da classe App.
   *
   * @throws IOException Se ocorrer um erro ao carregar recursos.
   */
  public App() throws IOException {
    super("Educator's Report");

    // Carregar recursos como imagens e fontes
    ImagesManager.load();
    FontsManager.load();

    // Inicializar a tela de login
    Login login = new Login();

    // Adicionar um observador para tratar a confirmação de login
    login.getObservable().addObserver(action -> {
      if (action.equals("confirmed-login")) {
        this.init();
        try {
          addComponents();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    });
  }

  /**
   * Inicializa a configuração da janela principal.
   */
  private void init() {
    this.setLayout(new BorderLayout());
    this.setMinimumSize(new Dimension(1280, 720));
    this.setPreferredSize(this.getMinimumSize());
    this.setSize(this.getPreferredSize());
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setBackground(ColorsManager.getBackgroundColor());
    this.setLocationRelativeTo(null);

    // Inicializar os serviços de backend
    new TeacherService();
    new CoordinationActivityService();
    new PaperService();
    new PosgraduateStudentService();
    new SubjectsService();
    new UndergraduateStudentService();
  }

  /**
   * Adiciona componentes à janela principal.
   *
   * @throws IOException Se ocorrer um erro ao adicionar componentes.
   */
  private void addComponents() throws IOException {
    // Inicializar gerenciadores de tela
    TeachersManager teachersManager = new TeachersManager();
    TeacherManager teacherManager = new TeacherManager();

    // Criar um painel para alternar entre as diferentes telas
    JPanel windows = new JPanel();
    windows.setLayout(new CardLayout());
    windows.add(teachersManager, TEACHER_WINDOWS_ID);
    windows.add(new SubjectsManager(), SUBJECT_WINDOWS_ID);
    windows.add(new StudentsManager(), STUDENT_WINDOWS_ID);
    windows.add(new PapersManager(), PAPER_WINDOWS_ID);
    windows.add(new CoordinationActivityManager(), ACTIVITY_WINDOWS_ID);
    windows.add(teacherManager, TeachersManager.ACTIVITY_TEACHER_WINDOWS_ID);

    // Criar um painel de navegação lateral
    Navigation navigation = new Navigation(300, 300, null);
    navigation.setItem("Professores", ImagesManager.getTeacherIcon(), TEACHER_WINDOWS_ID);
    navigation.setItem("Disciplinas", ImagesManager.getClassIcon(), SUBJECT_WINDOWS_ID);
    navigation.setItem("Alunos", ImagesManager.getStudentIcon(), STUDENT_WINDOWS_ID);
    navigation.setItem("Artigos", ImagesManager.getPaperIcon(), PAPER_WINDOWS_ID);
    navigation.setItem("Atividades", ImagesManager.getActivityIcon(), ACTIVITY_WINDOWS_ID);

    // Adicionar um observador para alternar entre as telas com base na navegação
    navigation.getObservable().addObserver(action -> {
      CardLayout card = (CardLayout) windows.getLayout();
      card.show(windows, action);
    });

    // Adicionar um observador para mostrar as disciplinas de um professor
    // selecionado
    teachersManager.getObservable().addObserver(action -> {
      CardLayout card = (CardLayout) windows.getLayout();
      teacherManager.setTeacher(teachersManager.getSelectedTeacher());
      teacherManager.showSubjectsTable();
      card.show(windows, action);
    });

    // Criar e configurar a barra lateral
    SideBar sideBar = new SideBar(260, 720);
    sideBar.setBackgroundColor(ColorsManager.getOnBackgroundColor())
        .setHeader("RIT Systems", "Versão 1.0", ImagesManager.getLogo())
        .setNavigation(navigation);

    // Adicionar componentes à janela principal
    add(sideBar.getComponent(), BorderLayout.LINE_START);
    add(windows, BorderLayout.CENTER); 
  }

  public static void changeComponentColors(Container container, Color foregroundColor, Color backgroundColor) {
    for (Component component : container.getComponents()) {
      if (component instanceof Container) {
        // If the component is a container (e.g., JPanel), recursively change colors of
        // its components.
        changeComponentColors((Container) component, foregroundColor, backgroundColor);
      } else {
        // Change the colors of non-container components (e.g., JLabel, JButton).
        component.setForeground(foregroundColor);
        component.setBackground(backgroundColor);
      }
    }
  }

  /**
   * Método principal que inicia a aplicação.
   *
   * @param args Argumentos da linha de comando (não utilizados).
   * @throws IOException Se ocorrer um erro durante a execução.
   */
  public static void main(String[] args) throws IOException {
    App app = new App();
    app.setVisible(true);
  }

}
