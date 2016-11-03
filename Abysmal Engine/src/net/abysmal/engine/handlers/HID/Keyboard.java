package net.abysmal.engine.handlers.HID;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import net.abysmal.engine.handlers.misc.Settings;
import net.abysmal.engine.handlers.misc.Time;

public class Keyboard implements KeyListener {

	static int[][] currentlyPressedKeys = new int[1024][2];
	public ArrayList<IKeyboard> listeners;

	public Keyboard() {
		listeners = new ArrayList<IKeyboard>();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (currentlyPressedKeys[e.getKeyCode()][0] == 0)
			listeners.forEach((v) -> v.keyPressed(e));
		currentlyPressedKeys[e.getKeyCode()][0] = 1;
		currentlyPressedKeys[e.getKeyCode()][1] = (int) (Time.getTime(Time.MILLIS) - e.getWhen());
	}

	@Override
	public void keyReleased(KeyEvent e) {
		currentlyPressedKeys[e.getKeyCode()][0] = 0;
		currentlyPressedKeys[e.getKeyCode()][1] = 0;
		listeners.forEach((v) -> v.keyReleased(e));
	}

	@Override
	public void keyTyped(KeyEvent e) {
		if (currentlyPressedKeys[e.getKeyCode()][0] == 0)
			listeners.forEach((v) -> v.keyTyped(e));
	}

	/**
	 * Abysmal method for receiving keyboard input.
	 * 
	 * @return int[1024][2] of [keycode] and [pressed, time held down]. Pressed
	 *         is either 0 or 1, time held down is in milliseconds.
	 */

	public int[][] getCurrentlyPressedKeys() {
		return currentlyPressedKeys;
	}

	@Deprecated
	public static boolean[] getPressedMovementButtons() {
		int[] currentMovementButtons = Settings.getMovementKeys();
		boolean[] pressedMovementButtons = { currentlyPressedKeys[currentMovementButtons[0]][0] == 1 ? true : false, currentlyPressedKeys[currentMovementButtons[1]][0] == 1 ? true : false, currentlyPressedKeys[currentMovementButtons[2]][0] == 1 ? true : false, currentlyPressedKeys[currentMovementButtons[3]][0] == 1 ? true : false, currentlyPressedKeys[currentMovementButtons[4]][0] == 1 ? true : false, currentlyPressedKeys[currentMovementButtons[5]][0] == 1 ? true : false, currentlyPressedKeys[currentMovementButtons[6]][0] == 1 ? true : false, currentlyPressedKeys[currentMovementButtons[7]][0] == 1 ? true : false, currentlyPressedKeys[currentMovementButtons[8]][0] == 1 ? true : false };
		return pressedMovementButtons;
	}
}