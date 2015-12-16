package net.abysmal.engine.entities;

import net.abysmal.engine.maths.Hitbox;
import net.abysmal.engine.maths.Vector3;

public class Projectile extends Entity {

	int damage, travelSpeed, statusDuration;
	Vector3 pos;
	Hitbox hitbox;

	public Projectile(Projectile projectile) {
		hitbox = new Hitbox(projectile);
	}
}
