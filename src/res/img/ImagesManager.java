package res.img;

import javax.swing.ImageIcon;

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

	public static ImageIcon getAddIcon() {
		return ADD_ICON;
	}

	public static ImageIcon getInfoIcon() {
		return INFO_ICON;
	}

	public static ImageIcon getEditIcon() {
		return EDIT_ICON;
	}

	public static ImageIcon getDeleteIcon() {
		return DELETE_ICON;
	}
	
}
