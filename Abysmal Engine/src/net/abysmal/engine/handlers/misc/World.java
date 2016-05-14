package net.abysmal.engine.handlers.misc;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.File;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import net.abysmal.engine.entities.Entity;
import net.abysmal.engine.entities.Player;
import net.abysmal.engine.maths.Dimension;
import net.abysmal.engine.maths.Vector;

public class World {

	public Dimension size;
	public ArrayList<Entity> populace = new ArrayList<Entity>();
	public Player p;
	public Tile[][] tiles;
	File map;
	File entityMap;
	public int tileSize;

	public World(File map, int tileSize) {
		this.map = map;
		this.tileSize = tileSize;
		try {
			BufferedImage bgi = ImageIO.read(map);
			BufferedImage bg = new BufferedImage(bgi.getWidth(), bgi.getHeight(), BufferedImage.TYPE_INT_ARGB);
			size = new Dimension(bgi.getWidth(), bgi.getHeight());
			tiles = new Tile[3][size.getArea()];
			bg.setData(bgi.getRaster());
			populateMap(((DataBufferInt) bg.getRaster().getDataBuffer()).getData());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public World(File tMap, File eMap) {
		map = tMap;
		entityMap = eMap;

		try {
			BufferedImage tileMapI = ImageIO.read(tMap);
			BufferedImage tileMap = new BufferedImage(tileMapI.getWidth(), tileMapI.getHeight(), BufferedImage.TYPE_INT_ARGB);
			tileMap.setData(tileMapI.getRaster());

			BufferedImage entityMapI = ImageIO.read(tMap);
			BufferedImage entityMap = new BufferedImage(entityMapI.getWidth(), entityMapI.getHeight(), BufferedImage.TYPE_INT_ARGB);
			entityMap.setData(entityMapI.getRaster());

			size = new Dimension(tileMapI.getWidth(), tileMapI.getHeight());
			tiles = new Tile[3][size.getArea()];
			populateMap(((DataBufferInt) tileMap.getRaster().getDataBuffer()).getData(), ((DataBufferInt) entityMap.getRaster().getDataBuffer()).getData());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addEntity(Entity e) {
		populace.add(e);
	}

	public void populateMap(int[] pixels) {
		for (int i = 0; i < this.size.getArea(); i++) {
			tiles[0][i] = Tile.getTile((pixels[i] >> 16) & 0xFF, 0);
			tiles[1][i] = Tile.getTile((pixels[i] >> 8) & 0xFF, 1);
			tiles[2][i] = Tile.getTile(pixels[i] & 0xFF, 2);
			if (((pixels[i] >>> 24) & 0xFF) != 255) {
				if(((pixels[i] >>> 24) & 0xFF) != 1)
					populace.add(new Entity(Entity.getEntity(((pixels[i] >>> 24) & 0xFF)), new Vector((i % size.getDimension()[0]) * tileSize, (int) (i / size.getDimension()[0]) * tileSize)));
				else
					p = new Player(Entity.getEntity(((pixels[i] >>> 24) & 0xFF)), new Vector((i % size.getDimension()[0]) * tileSize, (int) (i / size.getDimension()[0]) * tileSize));
			}
		}
	}

	public void populateMap(int[] pixelsT, int[] pixelsE) {
		for (int i = 0; i < this.size.getArea(); i++) {
			int tileSet = (pixelsT[i] >>> 24) & 0xFF;
			tiles[0][i] = Tile.getTile(((pixelsT[i] >> 16) & 0xFF) + (tileSet * 0x100) & 0xFFFF, 0);
			tiles[1][i] = Tile.getTile(((pixelsT[i] >> 8) & 0xFF) + (tileSet * 0x100) & 0xFFFF, 1);
			tiles[2][i] = Tile.getTile((pixelsT[i] & 0xFF) + (tileSet * 0x100) & 0xFFFF, 2);
			populace.add(new Entity(Entity.getEntity(pixelsE[i]), new Vector((i % size.getDimension()[0]) * tileSize, (int) (i / size.getDimension()[0]) * tileSize)));
		}
	}
}