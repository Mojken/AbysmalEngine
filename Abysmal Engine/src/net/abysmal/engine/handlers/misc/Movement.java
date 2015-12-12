package net.abysmal.engine.handlers.misc;

import net.abysmal.engine.handlers.HID.Keyboard;
import net.abysmal.engine.main.FundamentalGameSpecifics;

public class Movement {
	
	int movementSpeed = 2;
	int runSpeed = 5;
	int crouchSpeed = 1;
	int angle;
	int acceleration;
	int momentum;
	boolean[] movementKeys = Keyboard.getPressedMovementButtons();
	
	
	public Movement() {
		if (FundamentalGameSpecifics.dimentionMode == FundamentalGameSpecifics.MODE_2D_TOP) {
			topMovement();
		} else if (FundamentalGameSpecifics.dimentionMode == FundamentalGameSpecifics.MODE_2D_SIDE) {
			sideMovement();
		}
	}
	
	void sideMovement() {
		
	}
	
	void topMovement() {
		
	}
	
	void FPMovement() {
		
	}
	
	void TPMovement() {
		
	}
}