package net.abysmal.engine.physics;

import net.abysmal.engine.entities.Entity;

public class Gravity {
	
	double gravity = 9.8;
	int terminalVelocity = 300;
	double velocity = 0;
	
	public void fall(Entity entity) {
		terminalVelocity = (int) java.lang.Math.sqrt((2 * entity.mass * gravity) / 1.225 * entity.width * entity.depth  * 0.82);
		
		if (entity.pos.y + 1 == 4) {
			if (velocity> terminalVelocity) velocity += gravity;
			entity.pos.y += (velocity/10);
		} else {
			velocity = 0;
		}
	}
}
