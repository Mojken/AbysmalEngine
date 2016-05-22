package net.abysmal.engine.physics;

import net.abysmal.engine.entities.Entity;
import net.abysmal.engine.handlers.misc.World;
import net.abysmal.engine.maths.Vector;

public class Gravity {

	static float terminalVelocity = 300;
	public static double gravity = 9.8;

	static void fall(Entity e) {
		e.forces[1] = new Vector(0, (float) VectorPhysics.fma(e.mass, gravity));
		e.forces[2] = generateDrag(e);
		if (!e.onGround) {
			e.forces[3] = Vector.ZERO();
			e.forces[4] = Vector.ZERO();
		}
		else {
			e.forces[3] = new Vector(0f, (float) -(e.forces[1].y + VectorPhysics.fma(e.mass, e.getMomentum().getY()) * 60));
		}
	}
	
	public static Vector calculateFriction(Entity e) {
		float friction = (float) -VectorPhysics.fma(e.mass, e.getMomentum().getX());
		float maxFriction = (float) -(0.6 * e.forces[3].getY());
		return new Vector(friction > maxFriction ? maxFriction : friction, 0f);
	}
	
	public static Vector generateDrag(Entity e) {
		terminalVelocity = (float) java.lang.Math.sqrt((2 * e.mass * gravity) / (1.225 * ((e.width / 10) * (e.depth / 10)) * 0.82));
		Vector v = e.getMomentum();
		double percentage = java.lang.Math.pow(v.getY(), 2) / java.lang.Math.pow(terminalVelocity, 2);
		v = v.multiply(new Vector(0, (float) -percentage));
		return v;
	}

	public static void fall(Entity e, World w) {
		int tX, tY;
//		for (int i = 0; i < w.tiles[1].length; i++) {
//			if (!w.tiles[1][i].solid) continue;
//			tX = (i % w.mapSize.getWidth()) * w.tileSize;
//			tY = (i / w.mapSize.getWidth()) * w.tileSize;
//			e.onGround = false;
//			if (e.getX() + e.hitboxPoints[1].x > tX && e.getX() + e.hitboxPoints[0].x < tX + w.tileSize) {
//				if (e.getY() + e.hitboxPoints[1].y >= tY && e.getY() + e.hitboxPoints[0].y < tY + w.tileSize) {
//					e.onGround = true;
//					if (e.getY() + e.hitboxPoints[1].y > tY && e.getY() + e.hitboxPoints[0].y < tY + w.tileSize) e.teleport(new Vector(e.getX(), tY - e.hitboxPoints[1].y));
//					break;
//				}
//			}
//		}
		
		int[] indexes = {
				(int)(e.getX()/w.tileSize) + (w.mapSize.getWidth() * (int)(e.getY() / w.tileSize)),
				(int)(e.getX()/w.tileSize + (int)(e.getX()/w.tileSize) == w.mapSize.getWidth()-1 ? 0 : 1) + (w.mapSize.getWidth() * (int)(e.getY() / w.tileSize)),
				(int)(e.getX()/w.tileSize - (int)(e.getX()/w.tileSize) == 0 ? 0 : 1) + (w.mapSize.getWidth() * (int)(e.getY() / w.tileSize)),
				(int)(e.getX()/w.tileSize) + (w.mapSize.getWidth() * ((int)(e.getY() / w.tileSize) + ((int)(e.getY() / w.tileSize) == w.mapSize.getHeight()-1 ? 0 : 1))),
				(int)(e.getX()/w.tileSize + (int)(e.getX()/w.tileSize) == w.mapSize.getWidth()-1 ? 0 : 1) + (w.mapSize.getWidth() * ((int)(e.getY() / w.tileSize) + ((int)(e.getY() / w.tileSize) == w.mapSize.getHeight()-1 ? 0 : 1))),
				(int)(e.getX()/w.tileSize - (int)(e.getX()/w.tileSize) == 0 ? 0 : 1) + (w.mapSize.getWidth() * ((int)(e.getY() / w.tileSize) + ((int)(e.getY() / w.tileSize) == w.mapSize.getHeight()-1 ? 0 : 1))),
				(int)(e.getX()/w.tileSize) + (w.mapSize.getWidth() * ((int)(e.getY() / w.tileSize) - ((int)(e.getY() / w.tileSize) == 0 ? 0 : 1))),
				(int)(e.getX()/w.tileSize + (int)(e.getX()/w.tileSize) == w.mapSize.getWidth()-1 ? 0 : 1) + (w.mapSize.getWidth() * ((int)(e.getY() / w.tileSize) - ((int)(e.getY() / w.tileSize) == 0 ? 0 : 1))),
				(int)(e.getX()/w.tileSize - (int)(e.getX()/w.tileSize) == 0 ? 0 : 1) + (w.mapSize.getWidth() * ((int)(e.getY() / w.tileSize) - ((int)(e.getY() / w.tileSize) == 0 ? 0 : 1)))
		};
		
		for (int i : indexes) {
			if (!w.tiles[1][i].solid) continue;
			tX = (i % w.mapSize.getWidth()) * w.tileSize;
			tY = (i / w.mapSize.getWidth()) * w.tileSize;
			e.onGround = false;
			if (e.getX() + e.hitboxPoints[1].x > tX && e.getX() + e.hitboxPoints[0].x < tX + w.tileSize) {
				if (e.getY() + e.hitboxPoints[1].y >= tY && e.getY() + e.hitboxPoints[0].y < tY + w.tileSize) {
					e.onGround = true;
					if (e.getY() + e.hitboxPoints[1].y > tY && e.getY() + e.hitboxPoints[0].y < tY + w.tileSize) e.teleport(new Vector(e.getX(), tY - e.hitboxPoints[1].y));
					break;
				}
			}
		}
		fall(e);
	}
}