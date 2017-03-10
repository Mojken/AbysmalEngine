package net.abysmal.engine.handlers.misc;

import net.abysmal.engine.graphics.Graphics;

public interface Tick {
	
	public abstract void update();
	public abstract void render(Graphics g);

}