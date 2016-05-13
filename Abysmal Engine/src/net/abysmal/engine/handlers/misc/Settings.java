package net.abysmal.engine.handlers.misc;

public class Settings {

	static int button_movement_up = 87; 		// W
	static int button_movement_left = 65;		// A
	static int button_movement_down = 83;		// S
	static int button_movement_right = 68;		// D
	static int button_movement_upLeft;
	static int button_movement_upRight;
	static int button_interact = 69;			// E
	static int button_crouch = 17;				// ctrl
	static int button_run = 16;					// shift
	static int button_throw = 81;				// Q
	static int button_inventory = 73;			// I
	static int button_jump = 32;				// space
	static int button_reload = 82;				// R
	static int button_confirm = 13;				// enter
	static int button_cancel = 27;				// escape
// static int button_
	
	public static void setDvorak() {
		button_movement_up = 44; 		// ,
		button_movement_left = 65;		// a
		button_movement_down = 79;		// o
		button_movement_right = 69;		// e
		button_interact = 190;			// .
		button_throw = 186;				// ;
		button_inventory = 67;			// c
		button_reload = 80;				// p
	}
	
	static int[] buttons_numerals = { 48, 49, 50, 51, 52, 53, 54, 55, 56, 57 };

	/** Abysmal method to get the currently chosen movement buttons
	 * 
	 * @return int array of the nine movement buttons selected, up, left, down, right, up-left, up-right, jump, run, crouch */
	public static int[] getMovementKeys() {
		int[] movementKeys = { button_movement_up, button_movement_left, button_movement_down, button_movement_right, button_movement_upLeft, button_movement_upRight, button_jump, button_run, button_crouch };
		return movementKeys;
	}

	public static void setButton_movement_up(int button_movement_up) {
		Settings.button_movement_up = button_movement_up;
	}

	public static void setButton_movement_left(int button_movement_left) {
		Settings.button_movement_left = button_movement_left;
	}

	public static void setButton_movement_down(int button_movement_down) {
		Settings.button_movement_down = button_movement_down;
	}

	public static void setButton_movement_right(int button_movement_right) {
		Settings.button_movement_right = button_movement_right;
	}

	public static void setButton_movement_up_left(int button_movement_upLeft) {
		Settings.button_movement_upLeft = button_movement_upLeft;
	}

	public static void setButton_movement_up_right(int button_movement_upRight) {
		Settings.button_movement_upRight = button_movement_upRight;
	}

	public static int getButton_interact() {
		return button_interact;
	}

	public static void setButton_interact(int button_interact) {
		Settings.button_interact = button_interact;
	}

	public static void setButton_crouch(int button_crouch) {
		Settings.button_crouch = button_crouch;
	}

	public static void setButton_run(int button_run) {
		Settings.button_run = button_run;
	}

	public static int getButton_throw() {
		return button_throw;
	}

	public static void setButton_throw(int button_throw) {
		Settings.button_throw = button_throw;
	}

	public static int getButton_inventory() {
		return button_inventory;
	}

	public static void setButton_inventory(int button_inventory) {
		Settings.button_inventory = button_inventory;
	}

	public static void setButton_jump(int button_jump) {
		Settings.button_jump = button_jump;
	}

	public static int getButton_reload() {
		return button_reload;
	}

	public static void setButton_reload(int button_reload) {
		Settings.button_reload = button_reload;
	}

	public static int getButton_confirm() {
		return button_confirm;
	}

	public static void setButton_confirm(int button_confirm) {
		Settings.button_confirm = button_confirm;
	}

	public static int getButton_cancel() {
		return button_cancel;
	}

	public static void setButton_cancel(int button_cancel) {
		Settings.button_cancel = button_cancel;
	}

	public static int[] getButtons_numerals() {
		return buttons_numerals;
	}

	public static void setButtons_numerals(int[] buttons_numerals) {
		Settings.buttons_numerals = buttons_numerals;
	}
}