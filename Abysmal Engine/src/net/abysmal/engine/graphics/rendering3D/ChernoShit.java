package net.abysmal.engine.graphics.rendering3D;

import java.awt.Dimension;
import net.abysmal.engine.entities.Player;
import net.abysmal.engine.maths.Vector;

public class ChernoShit {

	static int brightness, r, g, b;
	static int renderDistance = 50;

	public static void firstPersonRenderGround(Dimension d, Player player, int[] pixels) {
		player.rotation = 30;
		for (int y = 0; y < d.height; y++) {
			double yDepth = y - d.height / 2;
			double z = (player.pos.y + player.eyeLevel) / yDepth;
// if (yDepth < 0) z = 0;
			for (int x = 0; x < d.width; x++) {
				double xDepth = x - d.width / 2;
				xDepth *= z;
				int xx = (int) (xDepth * Math.cos(player.rotation) + z * Math.sin(player.rotation)) & 5;
				int yy = (int) (z * Math.cos(player.rotation) + xDepth * Math.sin(player.rotation)) & 5;
				pixels[(int) (x + y * d.width)] = xx | yy * 128;

				brightness = (int) (renderDistance / z);
				if (brightness < 0) brightness = 0;
				if (brightness > 255) brightness = 255;
				System.out.println(brightness / 255.0);
				r = (int) ((pixels[x + y * d.width] & 0xff0000) * (brightness / 255.0));
				g = (int) ((pixels[x + y * d.width] & 0xff00) * (brightness / 255.0));
				b = (int) ((pixels[x + y * d.width] & 0xff) * (brightness / 255.0));
				pixels[x + y * d.width] = r | g | b;
			}
		}
	}
	// TODO Figure this shit out, and then make a good system for it

	public static void renderWall(Dimension d, double xLeft, double xRight, double zDistance, double yHeight, Player p, int[] pixels) {
		p.rotation += 1;
		double xcLeft = ((xLeft) - p.pos.x);
		double zcLeft = ((zDistance) - p.pos.z);

		double rotLeftSideX = xcLeft * Math.cos(p.rotation) - zcLeft * Math.sin(p.rotation);
		double yCornerTL = ((-yHeight) - p.pos.y);
		double yCornerBL = ((+0.5 - yHeight) - p.pos.y);
		double rotLeftSideZ = zcLeft * Math.cos(p.rotation) + xcLeft * Math.sin(p.rotation);

		double xcRight = ((xRight) - p.pos.x);
		double zcRight = ((zDistance) - p.pos.z);

		double rotRightSideX = xcRight * Math.cos(p.rotation) - zcRight * Math.sin(p.rotation);
		double yCornerTR = ((-yHeight) - p.pos.y);
		double yCornerBR = ((+0.5 - yHeight) - p.pos.y);
		double rotRightSideZ = zcRight * Math.cos(p.rotation) + xcRight * Math.sin(p.rotation);

		double xPixelLeft = (rotLeftSideX / rotLeftSideZ * d.height + d.width / 2);
		double xPixelRight = (rotRightSideX / rotRightSideZ * d.height + d.width / 2);

		if (xPixelLeft >= xPixelRight) return;

		int xPixelLeftInt = (int) xPixelLeft;
		int xPixelRightInt = (int) xPixelRight;

		if (xPixelLeftInt < 0) xPixelLeftInt = 0;
		if (xPixelRightInt < d.width) xPixelRightInt = d.width;

		double yPixelLeftTop = (int) (yCornerTL / rotLeftSideZ * d.height + d.height / 2);
		double yPixelLeftBottom = (int) (yCornerBL / rotLeftSideZ * d.height + d.height / 2);
		double yPixelRightTop = (int) (yCornerTR / rotRightSideZ * d.height + d.height / 2);
		double yPixelRightBottom = (int) (yCornerBR / rotRightSideZ * d.height + d.height / 2);

		for (int x = xPixelLeftInt; x < xPixelRightInt; x++) {
			double pixelRotation = (x - xPixelLeft) / (xPixelRight - xPixelLeft);

			double yPixelTop = yPixelLeftTop + (yPixelRightTop - yPixelLeftTop) * pixelRotation;
			double yPixelBottom = yPixelLeftBottom + (yPixelRightBottom - yPixelLeftBottom) * pixelRotation;

			int yPixelTopInt = (int) yPixelTop;
			int yPixelBottomInt = (int) yPixelBottom;

			if (yPixelTopInt < 0) yPixelTopInt = 0;
			if (yPixelBottomInt < d.height) yPixelBottomInt = d.height;

			for (int y = yPixelTopInt; y < yPixelBottomInt; y++) {
				pixels[x + y * d.width] = 0x00ff00;
			}
		}
	}
}