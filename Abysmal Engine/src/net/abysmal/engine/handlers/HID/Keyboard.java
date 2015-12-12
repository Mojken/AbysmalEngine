package net.abysmal.engine.handlers.HID;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import net.abysmal.engine.handlers.misc.Settings;
import net.abysmal.engine.handlers.misc.Time;

public class Keyboard implements KeyListener {

	int[][] currentlyPressedKeys = new int[1024][2];

	public Keyboard() {}

	@Override
	public void keyPressed(KeyEvent e) {
		currentlyPressedKeys[e.getKeyCode()][0] = 1;
		currentlyPressedKeys[e.getKeyCode()][1] = (int) (Time.getTime(Time.MILLIS) - e.getWhen());
	}

	@Override
	public void keyReleased(KeyEvent e) {
		currentlyPressedKeys[e.getKeyCode()][0] = 0;
		currentlyPressedKeys[e.getKeyCode()][1] = 0;
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	/** Abysmal method for receiving keyboard input.
	 * 
	 * @return int[1024][2] of [keycode] and [pressed, time held down]. Pressed is either 0 or 1, time held down
	 * is in milliseconds. */

	public int[][] getCurrentlyPressedKeys() {
		return currentlyPressedKeys;
	}
	
	public int[] getPressedMovementButtons() {
		int[] currentMovementButtons = Settings.getMovementKeys();
		int[] pressedMovementButtons = {currentlyPressedKeys[currentMovementButtons[0]][0], currentlyPressedKeys[currentMovementButtons[1]][0], currentlyPressedKeys[currentMovementButtons[2]][0], currentlyPressedKeys[currentMovementButtons[3]][0]};
		
		return pressedMovementButtons;
	}
}