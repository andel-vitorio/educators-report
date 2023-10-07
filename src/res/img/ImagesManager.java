package res.img;

import javax.swing.ImageIcon;

/**
 * Classe responsável pelo gerenciamento de imagens e ícones na aplicação.
 */
public class ImagesManager {

    public static ImageIcon LOGO;
    public static ImageIcon TEACHER_ICON;
    public static ImageIcon CLASS_ICON;
    public static ImageIcon STUDENTS_ICON;
    public static ImageIcon PAPERS_ICON;
    public static ImageIcon ACTIVITIES_ICON;

    public static ImageIcon ADD_ICON;
    public static ImageIcon INFO_ICON;
    public static ImageIcon EDIT_ICON;
    public static ImageIcon DELETE_ICON;
    public static ImageIcon REPORT_ICON;

    /**
     * Carrega as imagens e ícones a partir dos arquivos especificados.
     */
    public static void load() {
        String path = ImagesManager.class.getResource("/res/img").getFile();
        LOGO = new ImageIcon(path + "/logo.png");
        TEACHER_ICON = new ImageIcon(path + "/teacher-icon.png");
        CLASS_ICON = new ImageIcon(path + "/class-icon.png");
        STUDENTS_ICON = new ImageIcon(path + "/student-icon.png");
        PAPERS_ICON = new ImageIcon(path + "/paper-icon.png");
        ACTIVITIES_ICON = new ImageIcon(path + "/book.png");
        ADD_ICON = new ImageIcon(path + "/add-icon-light.png");
        INFO_ICON = new ImageIcon(path + "/info.png");
        EDIT_ICON = new ImageIcon(path + "/edit.png");
        DELETE_ICON = new ImageIcon(path + "/delete.png");
        REPORT_ICON = new ImageIcon(path + "/report.png");
    }

    /**
     * Obtém o ícone do logotipo da aplicação.
     *
     * @return O ícone do logotipo.
     */
    public static ImageIcon getLogo() {
        return LOGO;
    }

    /**
     * Obtém o ícone do professor.
     *
     * @return O ícone do professor.
     */
    public static ImageIcon getTeacherIcon() {
        return TEACHER_ICON;
    }

    /**
     * Obtém o ícone de atividades.
     *
     * @return O ícone de atividades.
     */
    public static ImageIcon getActivityIcon() {
        return ACTIVITIES_ICON;
    }

    /**
     * Obtém o ícone da classe.
     *
     * @return O ícone da classe.
     */
    public static ImageIcon getClassIcon() {
        return CLASS_ICON;
    }

    /**
     * Obtém o ícone do papel.
     *
     * @return O ícone do papel.
     */
    public static ImageIcon getPaperIcon() {
        return PAPERS_ICON;
    }

    /**
     * Obtém o ícone do estudante.
     *
     * @return O ícone do estudante.
     */
    public static ImageIcon getStudentIcon() {
        return STUDENTS_ICON;
    }

    /**
     * Obtém o ícone de adição.
     *
     * @return O ícone de adição.
     */
    public static ImageIcon getAddIcon() {
        return ADD_ICON;
    }

    /**
     * Obtém o ícone de informação.
     *
     * @return O ícone de informação.
     */
    public static ImageIcon getInfoIcon() {
        return INFO_ICON;
    }

    /**
     * Obtém o ícone de edição.
     *
     * @return O ícone de edição.
     */
    public static ImageIcon getEditIcon() {
        return EDIT_ICON;
    }

    /**
     * Obtém o ícone de exclusão.
     *
     * @return O ícone de exclusão.
     */
    public static ImageIcon getDeleteIcon() {
        return DELETE_ICON;
    }

    /**
     * Obtém o ícone de relatório.
     *
     * @return O ícone de relatório.
     */
    public static ImageIcon getReportIcon() {
        return REPORT_ICON;
    }
}
