package net.abysmal.engine.maths;

public class HexCoord {

	public int x, y;
	
	public final static HexCoord ZERO = new HexCoord(10, 10);
	
	public HexCoord(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
