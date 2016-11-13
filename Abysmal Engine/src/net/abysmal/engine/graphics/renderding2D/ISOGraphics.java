package net.abysmal.engine.graphics.renderding2D;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import javax.imageio.ImageIO;
import net.abysmal.engine.graphics.Graphics;
import net.abysmal.engine.handlers.misc.Tile;
import net.abysmal.engine.maths.Dimension;
import net.abysmal.engine.maths.Vector;

public class ISOGraphics {

	public ArrayList<Vector> generateGrid(int tileSize, Dimension size) {
		boolean[] grid = new boolean[size.getArea()];
		ArrayList<Vector> isoGrid = new ArrayList<Vector>();

		for (int b = 0; b < grid.length; b++) {
			grid[b] = false;
		}

		for (int y = 0; y < size.getHeight(); y++) {
			for (int x = 0; x < size.getWidth(); x++) {
				if (x % tileSize == 0 && y % tileSize == 0) {
					grid[x + y * size.getWidth()] = true;
				}
			}
		}

		for (int b = 0; b < grid.length; b++) {
			if (grid[b]) {
				isoGrid.add(twoDToIso(new Vector(b % size.getWidth(), (int) b / size.getWidth())));
			}
		}

		return isoGrid;
	}

	public Dimension mapSize, worldSize;
	int[] pixels;
	ArrayList<BufferedImage> tileImagesBack = new ArrayList<BufferedImage>();
	ArrayList<BufferedImage> tileImagesReal = new ArrayList<BufferedImage>();
	ArrayList<BufferedImage> tileImagesFore = new ArrayList<BufferedImage>();

	public void updateMap(URL map) {
		try {
			BufferedImage bgi;
			bgi = ImageIO.read(map);
			BufferedImage bg = new BufferedImage(bgi.getWidth(), bgi.getHeight(), BufferedImage.TYPE_INT_RGB);
			bg.setData(bgi.getRaster());
			pixels = ((DataBufferInt) bg.getRaster().getDataBuffer()).getData();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void createWorld(URL map, int tileSize, ArrayList<Tile> groundTiles, ArrayList<Tile> realTiles) {
		try {
			BufferedImage bgi;
			bgi = ImageIO.read(map);
			BufferedImage bg = new BufferedImage(bgi.getWidth(), bgi.getHeight(), BufferedImage.TYPE_INT_RGB);
			bg.setData(bgi.getRaster());
			pixels = ((DataBufferInt) bg.getRaster().getDataBuffer()).getData();
			worldSize = new Dimension(bgi.getWidth() * tileSize, bgi.getHeight() * tileSize);
			mapSize = new Dimension(bgi.getWidth(), bgi.getHeight());

			Iterator<Tile> b = groundTiles.iterator(), r = realTiles.iterator();

			for (int layer = 0; layer < 3; layer++) {
				switch (layer) {
					case 0:
						while (b.hasNext())
							tileImagesBack.add(ImageIO.read(b.next().getTextureURL()));
					break;
					case 1:
						while (r.hasNext())
							tileImagesReal.add(ImageIO.read(r.next().getTextureURL()));
					break;
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void drawWorld(int tileSize, Vector offset, Graphics g) {
		for (int layer = 0; layer < 3; layer++) {
			for (int i = 0; i < pixels.length; i++) {
				switch (layer) {
					case 0:
						drawISO(tileImagesBack.get(pixels[i] & 0xFF), new Vector(i % mapSize.getWidth(), (int) i / mapSize.getWidth(), 0), offset, tileSize, g);
					break;
					case 1:
						drawISO(tileImagesReal.get((pixels[i] >> 8) & 0xFF), new Vector(i % mapSize.getWidth(), (int) i / mapSize.getWidth(), 1), offset, tileSize, g);
					break;
				}
			}
		}
	}

	public void drawISO(BufferedImage img, Vector pos, Vector offset, int tileSize, Graphics g) {
		g.drawImage(img, twoDToIso((pos.add(offset).multiply(tileSize)).add(-tileSize * (pos.z + offset.z)).add(new Vector(0, -img.getHeight()))));
	}

	public Vector getTileCoordinates(Vector p, int tileHeight) {
		return (new Vector((int) (p.x / tileHeight), (int) (p.y / tileHeight)));
	}

	public Vector IsoTo2D(Vector p) {
		return (new Vector((2 * p.y + p.x) / 2, (2 * p.y - p.x) / 2));
	}

	public Vector twoDToIso(Vector p) {
		return (new Vector(p.x - p.y, (p.x + p.y) / 2));
	}
}