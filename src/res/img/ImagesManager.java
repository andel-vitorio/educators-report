package res.img;

import javax.swing.ImageIcon;

public class ImagesManager {

	public static final String LOGO_PATH = "/logo.png";

	public static ImageIcon LOGO;

	public static void load() {
		String path = ImagesManager.class.getResource("/res/img").getFile();
		LOGO = new ImageIcon(path + LOGO_PATH);
	}

	public static ImageIcon getLogo() {
		return LOGO;
	}

	ImagesManager() {}
}
