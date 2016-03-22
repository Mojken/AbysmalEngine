package net.abysmal.engine.handlers.misc;

import java.awt.Graphics;

public interface Tick {
	
	public abstract void update();
	public abstract void render(Graphics g);

}