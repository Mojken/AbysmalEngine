package net.abysmal.engine.graphics.rendering3D;

import net.abysmal.engine.entities.Player;
import net.abysmal.engine.maths.Vector;

public class ChernoShit {

	int brightness, r, g, b;
	int renderDistance = 5000;

	public int[] firstPersonRenderGround(Vector screenSize, Player player) {
		int[] pixels = new int[(int) (screenSize.x * screenSize.y)];
		for (int y = 0; y <= screenSize.y; y++) {
			double yDepth = y - screenSize.y / 2;
			double z = (player.pos.y + player.eyeLevel) / yDepth;
			if (yDepth < 0) z = 0;

			for (int x = 0; x < screenSize.x; x++) {
				double xDepth = x - screenSize.x / 2;
				xDepth *= z;
				int xx = (int) (xDepth * Math.cos(player.rotation) + z * Math.sin(player.rotation)) & 5;
				int yy = (int) (z * Math.cos(player.rotation) + xDepth * Math.sin(player.rotation)) & 5;
				pixels[(int) (x + y * screenSize.y)] = xx | yy * 128;
				
				brightness = (int) (renderDistance / z);
				if (brightness < 0) brightness = 0;
				if (brightness > 255) brightness = 255;
				r = (pixels[(int) (x + y * screenSize.y)] & 0xff0000) * brightness / 255;
				g = (pixels[(int) (x + y * screenSize.y)] & 0xff00) * brightness / 255;
				b = (pixels[(int) (x + y * screenSize.y)] & 0xff) * brightness / 255;
				pixels[(int) (x + y * screenSize.y)] = r << 16 | g << 8 | b;
			}
		}
		return pixels;
	}
}