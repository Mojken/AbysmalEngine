package net.abysmal.engine.handlers.HID;

import java.awt.event.KeyEvent;

public interface IKeyboard {

	public void keyPressed(KeyEvent e);
	public void keyReleased(KeyEvent e);
	public void keyTyped(KeyEvent e);
	
}
