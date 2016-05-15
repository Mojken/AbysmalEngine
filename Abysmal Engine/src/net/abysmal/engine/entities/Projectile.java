package net.abysmal.engine.entities;

import net.abysmal.engine.maths.Hitbox;
import net.abysmal.engine.maths.Vector;

public class Projectile extends Entity {

	int damage, travelSpeed, statusDuration, mass;
	Vector pos;
	Hitbox hitbox;

	public Projectile(Vector position, Projectile projectile, int mass) {
		super(position, mass, new Hitbox(projectile));
		hitbox = new Hitbox(projectile);
	}
}