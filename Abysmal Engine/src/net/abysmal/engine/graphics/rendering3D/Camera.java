package net.abysmal.engine.graphics.rendering3D;

import net.abysmal.engine.entities.Entity;
import net.abysmal.engine.maths.Vector;

public class Camera {

	Entity host;

	public Camera(Entity e) {
		host = e;
	}
	
	public Vector transform(Vector p) {
		
		Vector c = new Vector((float) Math.cos(host.facing.x % 2 - 1), (float) Math.cos(host.facing.y % 2 - 1), (float) Math.cos(host.facing.z % 2 - 1));
		Vector s = new Vector((float) Math.sin(host.facing.x % 2 - 1), (float) Math.sin(host.facing.y % 2 - 1), (float) Math.sin(host.facing.z % 2 - 1));
		p = p.sub(host.pos);

		double dx = c.y + (s.z * p.y + c.z * p.x) - s.y * p.z;
		double dy = s.x * (c.y * p.z + s.y * (s.y * p.y + c.z * p.x)) + c.x * (c.x * p.y - s.z * p.x);
		double dz = c.x * (c.y * p.z + s.y * (s.y * p.y + c.z * p.x)) + s.x * (c.x * p.y - s.z * p.x);

		return new Vector((int) ((300 / dz) * dx + 500), (int) ((300 / dz) * dy + 500));
	}
}