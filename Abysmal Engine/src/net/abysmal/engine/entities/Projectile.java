package net.abysmal.engine.entities;

import net.abysmal.engine.maths.Hitbox;
import net.abysmal.engine.maths.Vector;

public class Projectile extends Entity {

	int damage, travelSpeed, statusDuration;
	Vector pos;
	Hitbox hitbox;

	public Projectile(Vector position, Projectile projectile) {
		super(position);
		hitbox = new Hitbox(projectile);
	}
}