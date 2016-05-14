package net.abysmal.engine.handlers.misc;

public class Tile {

	String texture;
	String[] traits;
	
	public String getTexture() {
		return texture;
	}
	
	public String[] getTraits() {
		return traits;
	}

	public static Tile getTile(int ID) {
		//TODO Device a means to get a tile type from it's ID.
		return new Tile();
	}
	
}