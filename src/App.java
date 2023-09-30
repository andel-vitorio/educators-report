
import javax.swing.*;

import app.frontend.components.SideBar;

import java.io.IOException;
import java.awt.*;

import res.fonts.FontsManager;
import res.img.ImagesManager;
import res.values.*;

public class App extends JFrame {

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
		SideBar sideBar = new SideBar(300, 720);
		sideBar.setBackgroundColor(ColorsManager.getOnBackgroundColor())
				.setHeader("Educator's Report", "Versão 1.0", ImagesManager.getLogo());

		this.add(sideBar.getComponent(), BorderLayout.LINE_START);
	}

	public static void main(String[] args) throws IOException {
		App snackBar = new App();
		snackBar.setVisible(true);
	}
}