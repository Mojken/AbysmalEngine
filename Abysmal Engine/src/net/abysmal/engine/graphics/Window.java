package net.abysmal.engine.graphics;

import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;
import net.abysmal.engine.handlers.HID.Keyboard;
import net.abysmal.engine.handlers.HID.Mouse;
import net.abysmal.engine.handlers.misc.Tick;
import net.abysmal.engine.main.FundamentalGameSpecifics;
import net.abysmal.engine.maths.Dimension;

public class Window {

	public static JFrame frame;
	boolean running = false;

	public Mouse mouseListener;
	public Keyboard keyboardListener;

	public static int width;
	public static int height;
	public static Dimension dimension;
	public static String title;
	public static Window w;
	Panel panel;

	public JFrame createWindow(String title, Dimension size, Tick t) {
		frame = new JFrame(title);
		panel = new Panel(t);
		frame.setSize(size.getWidth(), size.getHeight());
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setContentPane(panel);
		frame.setVisible(true);

		mouseListener = new Mouse(frame);
		keyboardListener = new Keyboard();

		frame.addMouseListener(mouseListener);
		frame.addKeyListener(keyboardListener);

		running = true;
		update(t);
		render(t);
		return frame;
	}

	public void createWindow(String title, int width, Tick t) {
		Window.width = width;
		Window.title = title;
		height = width / 16 * 9;
		dimension = new Dimension(width, height);
		createWindow(title, dimension, t);
	}

	@SuppressWarnings("serial")
	class Panel extends JPanel {

		Tick t;

		Panel(Tick t) {
			this.t = t;
		}
		
		@Override
		public void paint(Graphics g) {
			t.render(g.create());
		}
	}

	private void render(Tick t) {
		new Thread(new Runnable() {

			public void run() {
				while (running) {
					panel.repaint();
					try {
						Thread.sleep(1000 / FundamentalGameSpecifics.TPS);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}

	private void update(Tick t) {
		new Thread(new Runnable() {

			public void run() {
				while (running) {
					t.update();
					try {
						Thread.sleep(1000 / FundamentalGameSpecifics.TPS);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}
	
	public Dimension getSize() {
		return new Dimension(width-(frame.getInsets().left + frame.getInsets().right), height-(frame.getInsets().top + frame.getInsets().bottom));
	}

}
