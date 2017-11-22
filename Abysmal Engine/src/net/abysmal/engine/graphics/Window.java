package net.abysmal.engine.graphics;

import java.awt.Graphics2D;
import java.awt.Insets;
import javax.swing.JFrame;
import javax.swing.JPanel;
import net.abysmal.engine.handlers.HID.IKeyboard;
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

	public static Insets insets;
	public static int width;
	public static int height;
	public static Dimension dimension;
	public static String title;
	public static Window w;
	Panel panel;
	Tick t;

	public JFrame createWindow(String title, Dimension size) {
		width = size.getWidth();
		height = size.getHeight();
		dimension = new Dimension(width, height);
		Window.title = title;
		frame = new JFrame(title);
		panel = new Panel();
		frame.setSize(size.getWidth(), size.getHeight());
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		insets = new Insets(26, 3, 0, 0);

		// TODO
		frame.setSize(size.getWidth() + insets.left + insets.right, size.getHeight() + insets.top + insets.bottom);

		frame.setContentPane(panel);

		mouseListener = new Mouse(frame);
		keyboardListener = new Keyboard();

		frame.addMouseListener(mouseListener);
		frame.addKeyListener(keyboardListener);

		return frame;
	}

	public void start(Tick t) {
		frame.setVisible(true);
		running = true;
		this.t = t;
		update(t);
		render();
	}

	public JFrame createWindow(String title, int width) {
		height = width / 16 * 9;
		return createWindow(title, new Dimension(width, height));
	}

	@SuppressWarnings("serial")
	class Panel extends JPanel {

		@Override
		public void paint(java.awt.Graphics g2) {
			Graphics g = new Graphics((Graphics2D)g2.create());
			g.clearRect();
			t.render(g);
		}
	}

	private void render() {
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
		return new Dimension(width, height);
	}

	public void addKeyListener(IKeyboard k) {
		keyboardListener.listeners.add(k);
	}

	public void removeKeyListener(IKeyboard k) {
		keyboardListener.listeners.remove(k);
	}

}
