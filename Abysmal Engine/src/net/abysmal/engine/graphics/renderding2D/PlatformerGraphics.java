package net.abysmal.engine.graphics.renderding2D;

import net.abysmal.engine.entities.Player;
import net.abysmal.engine.graphics.Window;
import net.abysmal.engine.handlers.misc.World;
import net.abysmal.engine.maths.Vector;

public class PlatformerGraphics {

	public static Vector[] positionWithinBounds(World wo, Player p, Window w) {
		int x = (int) ((w.getSize().getWidth() / 2) - p.getX());
		int y = (int) ((w.getSize().getHeight() / 2) - p.getY());
		int px = (int) (p.hitboxPoints[0].getX() + w.getSize().getWidth() / 2);
		int py = (int) (p.hitboxPoints[0].getY() + w.getSize().getHeight() / 2);
		if (x > 0) {
			x = 0;
			px = (int) (p.hitboxPoints[0].getX() + p.getX());
		}

		if (y > 0) {
			y = 0;
			py = (int) (p.hitboxPoints[0].getY() + p.getY());
		}

		if (x < (-wo.worldSize.getWidth() + w.getSize().getWidth())) {
			x = (-wo.worldSize.getWidth() + w.getSize().getWidth());
			px = (int) ((w.getSize().getWidth() / 2) + p.getX() - (wo.worldSize.getWidth() - w.getSize().getWidth() / 2) + p.hitboxPoints[0].getX());
		}

		if (y < (-wo.worldSize.getHeight() + w.getSize().getHeight())) {
			y = (-wo.worldSize.getHeight() + w.getSize().getHeight());
			py = (int) ((w.getSize().getHeight() / 2) + p.getY() - (wo.worldSize.getHeight() - w.getSize().getHeight() / 2) + p.hitboxPoints[0].getY());
		}

		return new Vector[] { new Vector(x, y), new Vector(px, py) };
	}
}