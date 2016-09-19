package net.abysmal.engine.entities;

import java.util.ArrayList;
import net.abysmal.engine.maths.Hitbox;
import net.abysmal.engine.maths.Vector;
import net.abysmal.engine.utils.HugeInteger;

public class Projectile<S> extends Entity {

	protected int id;
	protected float damage;
	protected int speed;
	protected HugeInteger hugeDamage;
	protected S source;
	
	public ArrayList<Projectile<S>> projectileTypes = new ArrayList<Projectile<S>>();

	public Projectile(Vector position, Projectile<S> projectileType, S source) {
		super(position, projectileType.mass, projectileType.hitbox, projectileType.textureStr);
		hitbox = new Hitbox(projectileType);
		this.textureStr = projectileType.textureStr;
	}
	
	public Projectile(int id, int speed, float mass, float damage, String texture, Hitbox hitbox){
		this.speed = speed;
		this.textureURL = ClassLoader.getSystemResource(path + "projectiles/" + texture + ".png");
		this.mass = mass;
		this.damage = damage;
		this.hitbox = hitbox;
		projectileTypes.add(id, this);
	}
	
	public Projectile(int id, int speed, float mass, HugeInteger damage, String texture, Hitbox hitbox){
		this.id = id;
		this.speed = speed;
		this.mass = mass;
		hugeDamage = damage;
		this.textureURL = ClassLoader.getSystemResource(path + "projectiles/" + texture + ".png");
		this.hitbox = hitbox;
		projectileTypes.add(id, this);
	}
}