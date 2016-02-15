package net.abysmal.engine.graphics;

import java.awt.Dimension;
import javax.swing.JFrame;
import net.abysmal.engine.handlers.HID.Keyboard;
import net.abysmal.engine.handlers.HID.Mouse;
import net.abysmal.engine.main.FundamentalGameSpecifics;

public class Window {

	JFrame frame;
	boolean running = false;
	
	public Mouse mouseListener;
	public Keyboard keyboardListener;
	
	public JFrame createWindow(String title, Dimension size, Panel p) {
		frame = new JFrame(title);
		frame.setSize(size);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setContentPane(p);
		frame.setVisible(true);
		
		mouseListener = new Mouse();
		keyboardListener = new Keyboard();
		
		frame.addMouseListener(mouseListener);
		frame.addKeyListener(keyboardListener);
		
		running = true;
		render();
		update();
		return frame;
	}

	private void render(){
		new Thread(new Runnable(){
			public void run(){
				while (running){
					frame.repaint();
					try {
						Thread.sleep(160);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}
	
	private void update(){
		new Thread(new Runnable(){
			public void run(){
				while (running){

					try {
						Thread.sleep(1000 / FundamentalGameSpecifics.TPS);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}
	
}
