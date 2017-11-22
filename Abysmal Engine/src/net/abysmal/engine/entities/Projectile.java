package net.abysmal.engine.entities;

import java.util.ArrayList;
import net.abysmal.engine.maths.Hitbox;
import net.abysmal.engine.maths.Vector;
import net.abysmal.engine.utils.HugeInteger;

public class Projectile<S> extends Entity {

	public int id;
	public float damage;
	public double angle;
	public int speed;
	public HugeInteger hugeDamage;
	public S source;
	
	public String path = "projectiles/";
	
	
	public ArrayList<Projectile<S>> projectileTypes = new ArrayList<>();

	public Projectile(Vector position, Projectile<S> projectileType, S source) {
		super(position, projectileType.mass, projectileType.hitbox, projectileType.textureStr);
		hitbox = new Hitbox(projectileType);
		this.speed = projectileType.speed;
		this.textureURL = projectileType.textureURL;
		hugeDamage = projectileType.hugeDamage;
	}
	
	public Projectile(int id, int speed, float mass, float damage, String texture, Hitbox hitbox){
		this.speed = speed;
		this.textureURL = ClassLoader.getSystemResource(path + texture + ".png");
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
		this.textureURL = ClassLoader.getSystemResource(path + texture + ".png");
		this.hitbox = hitbox;
	}
}