package net.abysmal.engine.handlers.misc;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.File;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import net.abysmal.engine.entities.Entity;
import net.abysmal.engine.maths.Dimension;
import net.abysmal.engine.maths.Vector;

public class World {

	Dimension size;
	ArrayList<Entity> populace = new ArrayList<Entity>();
	Tile[][] tiles;
	File map;
	int[] pixels;

	public World(File map) {
		this.map = map;
		try {
			BufferedImage bgi = ImageIO.read(map);
			BufferedImage bg = new BufferedImage(bgi.getWidth(), bgi.getHeight(), BufferedImage.TYPE_INT_ARGB);
			size = new Dimension(bgi.getWidth(), bgi.getHeight());
			bg.setData(bgi.getRaster());
			pixels = ((DataBufferInt) bg.getRaster().getDataBuffer()).getData();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addEntity(Entity e) {
		populace.add(e);
	}

	public void populateMap() {
		for (int i = 0; i < this.size.getArea(); i++) {
			tiles[0][i] = Tile.getTile((pixels[i] >> 16) & 0xFF);
			tiles[1][i] = Tile.getTile((pixels[i] >> 8) & 0xFF);
			tiles[2][i] = Tile.getTile(pixels[i] & 0xFF);				
			populace.add(new Entity(new Vector(i%size.getDimension()[0], (int) Math.floor(i/size.getDimension()[0])), ((pixels[i] >>> 24) & 0xFF)));
		}
	}
}