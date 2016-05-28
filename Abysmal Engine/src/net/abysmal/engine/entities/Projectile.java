package net.abysmal.engine.entities;

import java.util.ArrayList;
import net.abysmal.engine.maths.Hitbox;
import net.abysmal.engine.maths.Vector;

public class Projectile extends Entity {

	float damage, speed;
	Entity source;
	
	public ArrayList<Projectile> projectileTypes = new ArrayList<Projectile>();

	public Projectile(Vector position, Projectile projectileType, Entity source, float angle) {
		super(position, projectileType.mass, projectileType.hitbox, projectileType.textureStr);
		hitbox = new Hitbox(projectileType);
		this.textureStr = projectileType.textureStr;
	}
	
	public Projectile(int id, float speed, float mass, float damage, String texture, Hitbox hitbox){
		this.speed = speed;
		this.textureStr = texturePath + texture + ".png";
		this.mass = mass;
		this.hitbox = hitbox;
		projectileTypes.add(id, this);
	}
}