package net.abysmal.engine.graphics;

import java.awt.Dimension;
import javax.swing.JFrame;

public class Window {

	public JFrame createWindow(String title, Dimension size) {
		JFrame frame = new JFrame(title);
		frame.setSize(size);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setContentPane(new Panel());
		frame.setVisible(true);
		return frame;
	}

}
