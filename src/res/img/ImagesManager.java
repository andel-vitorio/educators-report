package res.img;

import javax.swing.ImageIcon;

public class ImagesManager {

	public static ImageIcon LOGO;	
	public static ImageIcon TEACHER_ICON;
	public static ImageIcon CLASS_ICON;
	public static ImageIcon STUDENTS_ICON;
	public static ImageIcon PAPERS_ICON;
	public static ImageIcon ACTIVITIES_ICON;
	


	public static void load() {
		String path = ImagesManager.class.getResource("/res/img").getFile();
		LOGO = new ImageIcon(path + "/logo.png");		
		TEACHER_ICON = new ImageIcon(path + "/teacher-icon.png");
		CLASS_ICON = new ImageIcon(path + "/class-icon.png");
		STUDENTS_ICON = new ImageIcon(path + "/student-icon.png");
		PAPERS_ICON = new ImageIcon(path + "/paper-icon.png");
		ACTIVITIES_ICON = new ImageIcon(path + "/book.png");
	}

	public static ImageIcon getLogo() {
		return LOGO;
	}

	public static ImageIcon getTeacherIcon() {
		return TEACHER_ICON;
	}

	public static ImageIcon getActivityIcon() {
		return ACTIVITIES_ICON;
	}
	public static ImageIcon getClassIcon() {
		return CLASS_ICON;
	}

	public static ImageIcon getPaperIcon() {
		return PAPERS_ICON;
	}

	public static ImageIcon getStudentIcon() {
		return STUDENTS_ICON;
	}
	
}
