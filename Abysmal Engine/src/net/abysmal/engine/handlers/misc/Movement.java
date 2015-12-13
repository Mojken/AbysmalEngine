package net.abysmal.engine.handlers.misc;

import net.abysmal.engine.handlers.HID.Keyboard;
import net.abysmal.engine.main.FundamentalGameSpecifics;

public class Movement {

	int movementSpeed = 2;
	int runSpeed = 5;
	int crouchSpeed = 1;
	double angle;
	int acceleration;
	int momentum;
	int[] xyPressedKeys;
	boolean[] movementKeys = Keyboard.getPressedMovementButtons();

	public Movement() {
		if (FundamentalGameSpecifics.dimentionMode == FundamentalGameSpecifics.MODE_2D_TOP) {
			topMovement();
		} else if (FundamentalGameSpecifics.dimentionMode == FundamentalGameSpecifics.MODE_2D_SIDE) {
			sideMovement();
		}
	}

	void sideMovement() {}

	void topMovement() {
		if (movementKeys[0]) {
			xyPressedKeys[0] += 1;
		} else if (!movementKeys[0]) {
			xyPressedKeys[0] -= 1;
		}

		if (movementKeys[1]) {
			xyPressedKeys[1] -= 1;
		} else if (!movementKeys[1]) {
			xyPressedKeys[1] += 1;
		}

		if (movementKeys[2]) {
			xyPressedKeys[0] -= 1;
		} else if (!movementKeys[2]) {
			xyPressedKeys[0] += 1;
		}

		if (movementKeys[3]) {
			xyPressedKeys[1] += 1;
		} else if (!movementKeys[3]) {
			xyPressedKeys[1] -= 1;
		}

		if (movementKeys[0] && movementKeys[1] || !movementKeys[0] && !movementKeys[1]) {
			xyPressedKeys[0] = 0;
		}

		if (movementKeys[2] && movementKeys[3] || !movementKeys[2] && !movementKeys[3]) {
			xyPressedKeys[1] = 0;
		}

		if (Math.abs(xyPressedKeys[0]) == 2) {
			xyPressedKeys[0] = 0;
		}

		if (Math.abs(xyPressedKeys[1]) == 2) {
			xyPressedKeys[1] = 0;
		}

// if (xyPressedKeys[0] == 1 && xyPressedKeys[1] == 1) {
// angle = Constants.UP_LEFT;
// }
//
// if (xyPressedKeys[0] == 1 && xyPressedKeys[1] == 0) {
// angle = Constants.UP;
// }
//
// if (xyPressedKeys[0] == 1 && xyPressedKeys[1] == -1) {
// angle = Constants.UP_RIGHT;
// }
//
// if (xyPressedKeys[0] == 0 && xyPressedKeys[1] == 1) {
// angle = Constants.LEFT;
// }
//
// if (xyPressedKeys[0] == 0 && xyPressedKeys[1] == 0) {
// angle = -1;
// }
//
// if (xyPressedKeys[0] == 0 && xyPressedKeys[1] == -1) {
// angle = Constants.RIGHT;
// }
//
// if (xyPressedKeys[0] == -1 && xyPressedKeys[1] == 1) {
// angle = Constants.DOWN_LEFT;
// }
//
// if (xyPressedKeys[0] == -1 && xyPressedKeys[1] == 0) {
// angle = Constants.DOWN;
// }
//
// if (xyPressedKeys[0] == -1 && xyPressedKeys[1] == -1) {
// angle = Constants.DOWN_RIGHT;
// }

	}

	void FPMovement() {

	}

	void TPMovement() {

	}
}