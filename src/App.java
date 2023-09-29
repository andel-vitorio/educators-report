

import javax.swing.*;
import java.io.IOException;
import java.awt.*;

public class App extends JFrame {

	public App() throws IOException {
		super("Educator's Report");
		this.init();
		this.addComponents();
		// this.setExtendedState(MAXIMIZED_BOTH);
	}

    void init() {
        this.setLayout(new BorderLayout());
        this.setMinimumSize(new Dimension(1280, 720));
        this.setPreferredSize(this.getMinimumSize());
        this.setSize(this.getPreferredSize());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);   // Centrealiza a janela no centro da tela
    }

    void addComponents() throws IOException {
    }

    public static void main(String[] args) throws IOException {
        App snackBar = new App();
        snackBar.setVisible(true);
    }
}