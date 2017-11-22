package net.abysmal.engine.graphics.renderding2D;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class TileSheet {

	static ArrayList<TileSheet> sheets;
	public BufferedImage[] tiles;
	BufferedImage sheet;
	int tileSize, IDa, IDb;

	public TileSheet(BufferedImage sheet, int tileSize, int IDa, int IDb) {
		if (null == sheets) sheets = new ArrayList<TileSheet>();
		sheets.add(this);

		this.sheet = sheet;
		this.tileSize = tileSize;
		tiles = new BufferedImage[18];

		int i = 0;
		for (int y = 0; y < sheet.getHeight() / tileSize; y++) {
			for (int x = 0; x < sheet.getWidth() / tileSize; x++) {
				tiles[i] = sheet.getSubimage(x, y, x * tileSize, y * tileSize);
				i++;
			}
		}
	}

	public static TileSheet getTileSheet(int IDa, int IDb) {
		for (TileSheet sheet:sheets) {
			if (sheet.IDa == IDa && sheet.IDb == IDb) { return sheet; }
		}
		return null;
	}
}