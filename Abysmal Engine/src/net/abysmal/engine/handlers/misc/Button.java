package net.abysmal.engine.handlers.misc;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import net.abysmal.engine.graphics.Graphics;
import net.abysmal.engine.graphics.Window;
import net.abysmal.engine.graphics.geometry.Square;
import net.abysmal.engine.maths.Vector;

public class Button implements MouseListener {

	public Square bounds;
	public static Map<Integer, Map<Integer, Button>> buttons = new HashMap<Integer, Map<Integer, Button>>();
	public String label;
	public URL imagePath;
	public int id, screen;
	public int fill = 0;
	public boolean pressed = false;

	public static void registerButtons(int screen, JFrame f) {
		for (Map.Entry<Integer, Map<Integer, Button>> e0 : buttons.entrySet()) {
			if (e0.getKey() != screen) {
				for (Map.Entry<Integer, Button> e1 : e0.getValue().entrySet()) {
					f.removeMouseListener(e1.getValue());
				}
			} else {
				for (Map.Entry<Integer, Button> e1 : e0.getValue().entrySet()) {
					f.addMouseListener(e1.getValue());
				}
			}
		}
	}

	public Button(Square bounds, String label, URL imagePath, int screen, int id) {
		this.bounds = bounds;
		this.label = label;
		this.imagePath = imagePath;
		this.id = id;
		this.screen = screen;
		if (!buttons.containsKey(screen)) {
			buttons.put(screen, new HashMap<Integer, Button>());
		}
		if (buttons.get(screen).containsKey(id)) {
			System.err.println("Button with id " + id + " already exists on screen " + screen);
			System.exit(1);
		}
		buttons.get(screen).put(id, this);
	}
	public Button(Square bounds, String label, int pressedColour, int screen, int id) {
		this(bounds, label, null, screen, id);
		fill = pressedColour;
	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (bounds.isWithin(new Vector(e.getX()-Window.insets.left, e.getY()-Window.insets.top))) {
			pressed = true;
			update(true);
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (bounds.isWithin(new Vector(e.getX()-Window.insets.left, e.getY()-Window.insets.top))) {
			pressed = false;
			update(false);
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	public void update(boolean pressed) {
		System.out.println("Button with ID " + id + " on screen " + screen + " doesn't have a proper update handler!");
	}

	public void draw(Graphics g) {
		if (imagePath == null) {
			g.drawRect(bounds.a, bounds.b);
			if(pressed){
				Color c = g.getColour();
				g.setColour(new Color(fill, true));
				g.fillRect(bounds.a.add(1), bounds.b);
				g.setColour(c);
			}
			return;
		}

		try {
			g.drawImage(ImageIO.read(imagePath), bounds.a, bounds.b);
		} catch (IOException e) {
			g.drawRect(bounds.a, bounds.b);
			System.err.println("Unable to load image for button '" + label + "'");
		}
	}
}