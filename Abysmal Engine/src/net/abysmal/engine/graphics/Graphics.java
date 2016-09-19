package net.abysmal.engine.graphics;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.image.ImageObserver;
import java.text.AttributedCharacterIterator;
import net.abysmal.engine.maths.Vector;

public class Graphics {

	java.awt.Graphics g;

	public Graphics(java.awt.Graphics g) {
		this.g = g;
	}

	public static byte[] splitColours(int colour) {
		return new byte[] { (byte) ((colour >> 16) & 0xFF), (byte) ((colour >> 8) & 0xFF), (byte) (colour & 0xFF) };
	}

	public static int mergeColours(byte[] colours) {
		if (colours.length == 4) return (colours[0] << 24) + (colours[1] << 16) + (colours[2] << 8) + colours[3];
		return 0xff000000 + (colours[0] << 16) + (colours[1] << 8) + colours[2];
		
	}

	public void clearRect(Vector a, Vector b) {
		g.clearRect((int) a.x, (int) a.y, (int) b.x, (int) b.y);
	}

	public void clipRect(Vector a, Vector b) {
		g.clipRect((int) a.x, (int) a.y, (int) b.x, (int) b.y);
	}

	public void copyArea(Vector a, Vector b, Vector d) {
		g.copyArea((int) a.x, (int) a.y, (int) b.x, (int) b.y, (int) d.x, (int) d.y);
	}

	public void drawArc(Vector a, Vector b, int startAngle, int arcAngle) {
		g.drawArc((int) a.x, (int) a.y, (int) b.x, (int) b.y, startAngle, arcAngle);
	}

	public boolean drawImage(Image img, Vector a) {
		return g.drawImage(img, (int) a.x, (int) a.y, null);
	}

	public boolean drawImage(Image img, Vector a, Color bgcolor) {
		return g.drawImage(img, (int) a.x, (int) a.y, bgcolor, null);
	}

	public boolean drawImage(Image img, Vector a, Vector b) {
		return g.drawImage(img, (int) a.x, (int) a.y, (int) b.x, (int) b.y, null);
	}

	public boolean drawImage(Image img, Vector a, Vector b, Color bgcolor) {
		return g.drawImage(img, (int) a.x, (int) a.y, (int) b.x, (int) b.y, bgcolor, null);
	}

	public boolean drawImage(Image img, Vector da, Vector db, Vector sa, Vector sb) {
		return g.drawImage(img, (int) da.x, (int) da.y, (int) db.x, (int) db.y, (int) da.x, (int) da.y, (int) db.x, (int) db.y, (ImageObserver) null);
	}

	public boolean drawImage(Image img, Vector da, Vector db, Vector sa, Vector sb, Color bgcolor) {
		return g.drawImage(img, (int) da.x, (int) da.y, (int) db.x, (int) db.y, (int) da.x, (int) da.y, (int) db.x, (int) db.y, bgcolor, null);
	}

	public void drawLine(Vector a, Vector b) {
		g.drawLine((int) a.x, (int) a.y, (int) b.x, (int) b.y);
	}

	public void drawOval(Vector a, Vector b) {
		g.drawOval((int) a.x, (int) a.y, (int) b.x, (int) b.y);
	}

	public void drawRoundRect(Vector a, Vector b, Vector arc) {
		g.drawRoundRect((int) a.x, (int) a.y, (int) (b.x - a.x), (int) (b.y - a.y), (int) arc.x, (int) arc.y);
	}

	public void drawString(String str, Vector v) {
		g.drawString(str, (int) v.x, (int) v.y);
	}

	public void drawString(AttributedCharacterIterator iterator, Vector v) {
		g.drawString(iterator, (int) v.x, (int) v.y);
	}

	public void fillArc(Vector a, Vector b, int startAngle, int arcAngle) {
		g.fillArc((int) a.x, (int) a.y, (int) b.x, (int) b.y, startAngle, arcAngle);
	}

	public void fillOval(Vector a, Vector b) {
		g.fillOval((int) a.x, (int) a.y, (int) b.x, (int) b.y);
	}

	public void fillRect(Vector a, Vector b) {
		g.fillRect((int) a.x, (int) a.y, (int) (b.x - a.x), (int) (b.y - a.y));
	}

	public void fillRoundRect(Vector a, Vector b, Vector arc) {
		g.fillRoundRect((int) a.x, (int) a.y, (int) b.x, (int) b.y, (int) arc.x, (int) arc.y);
	}

	public void setClip(Vector a, Vector b) {
		g.setClip((int) a.x, (int) a.y, (int) b.x, (int) b.y);
	}

	public void translate(Vector v) {
		g.translate((int) v.x, (int) v.y);
	}

	public java.awt.Graphics create() {
		return g.create();
	}

	public void dispose() {
		g.dispose();
	}

	public void drawPolygon(int[] xPoints, int[] yPoints, int nPoints) {
		g.drawPolygon(xPoints, yPoints, nPoints);
	}

	public void drawPolyline(int[] xPoints, int[] yPoints, int nPoints) {
		g.drawPolyline(xPoints, yPoints, nPoints);
	}

	public void fillPolygon(int[] xPoints, int[] yPoints, int nPoints) {
		g.fillPolygon(xPoints, yPoints, nPoints);
	}

	public Shape getClip() {
		return g.getClip();
	}

	public Rectangle getClipBounds() {
		return g.getClipBounds();
	}

	public Color getColour() {
		return g.getColor();
	}

	public Font getFont() {
		return g.getFont();
	}

	public FontMetrics getFontMetrics(Font f) {
		return g.getFontMetrics(f);
	}

	public void setClip(Shape clip) {
		g.setClip(clip);
	}

	public void setColor(Color c) {
		g.setColor(c);
	}

	public void setFont(Font font) {
		g.setFont(font);
	}

	public void setPaintMode() {
		g.setPaintMode();
	}

	public void setXORMode(Color c1) {
		g.setXORMode(c1);
	}

	public void drawRect(Vector a, Vector b) {
		g.drawRect((int) a.x, (int) a.y, (int) (b.x - a.x), (int) (b.y - a.y));
	}
}