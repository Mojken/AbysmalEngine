package net.abysmal.engine.handlers.misc;

import java.net.URL;
import java.util.ArrayList;

public class Tile {

	int ID;
	public boolean solid = true;
	URL texture;
	String path = "tiles/";
	String[] traits;
	public static ArrayList<Tile> tilesForeground = new ArrayList<Tile>();
	public static ArrayList<Tile> tilesReal = new ArrayList<Tile>();
	public static ArrayList<Tile> tilesBackground = new ArrayList<Tile>();

	public Tile(int id, String name, String[] traits) {
		this.traits = traits;
		ID = id;
		texture = ClassLoader.getSystemResource(path + name + ".png");
		if (traits != null) {
			for (String s:traits) {
				if (s.equals("nonSolid")) {
					solid = false;
					break;
				}
			}
		}
	}

	public Tile(Tile t) {
		texture = t.texture;
		traits = t.traits;
		solid = t.solid;
	}

	public URL getTextureURL() {
		return texture;
	}

	public String[] getTraits() {
		return traits;
	}

	public int getID() {
		return ID;
	}

	public static Tile getTile(int index, int layer) {
		// TODO Error handling
		return new Tile(getArrayList(layer).get(index));
	}

	public static ArrayList<Tile> getArrayList(int layer) {
		switch (layer % 3) {
			case 2:
				return tilesBackground;
			case 1:
				return tilesReal;
			case 0:
				return tilesForeground;
		}
		return null;

	}

}