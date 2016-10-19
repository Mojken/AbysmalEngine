package net.abysmal.engine.graphics.geometry;

import net.abysmal.engine.maths.Dimension;
import net.abysmal.engine.maths.Vector;

public class Grid {

	public Square[] grid;
	public Dimension tileSize, gridSize;

	public Grid(Dimension tileSize, Dimension gridSize) {
		grid = new Square[gridSize.getArea()];
		this.gridSize = gridSize;
		this.tileSize = tileSize;
		for (int i = 0; i < grid.length; i++) {
			grid[i] = new Square(getGridCoordinate(i), getGridCoordinate(i).add(tileSize.toVector()));
		}
	}

	public Vector getGridCoordinate(int gridIndex) {
		if (gridIndex >= 0) {
			Vector pos = Vector.ZERO();
			pos.x = gridIndex % gridSize.getWidth();
			pos.y = (int) (gridIndex / gridSize.getWidth());
			return pos.multiply(tileSize.toVector());
		} else {
			
			Vector pos = Vector.ZERO();
			pos.x = gridSize.getWidth() + (gridIndex % gridSize.getWidth());
			pos.y = (int) (gridIndex / gridSize.getWidth());
			return pos.multiply(tileSize.toVector());
		}
	}

	public Vector getGridCoordinate(Vector pos) {
		return getGridCoordinate(getGridIndex(pos));
	}

	public int getGridIndex(Vector pos) {
		int xPos = (int) (pos.x / tileSize.getWidth());
		int yPos = (int) ((pos.y / tileSize.getHeight())) * gridSize.getWidth();
		return xPos + yPos;
	}

	public int vectorToIndex(Vector pos) {
		return (int) (pos.x + (pos.y * gridSize.getWidth()));
	}

	public Vector getSize() {
		return gridSize.toVector().multiply(tileSize.toVector());
	}
}