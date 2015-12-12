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
		
		
		
	}

	void FPMovement() {

	}

	void TPMovement() {

	}
}