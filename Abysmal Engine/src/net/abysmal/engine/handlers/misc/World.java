package net.abysmal.engine.handlers.misc;

import java.io.File;
import java.util.ArrayList;
import net.abysmal.engine.entities.Entity;
import net.abysmal.engine.maths.Dimension;

public class World {
	
	Dimension size;
	ArrayList<Entity> populace = new ArrayList<Entity>();
	File map;
	
	public World(File map, Dimension size){
		this.map = map;
		this.size = size;
	}
	
	public World(File map){
		this(map, new Dimension(-1, -1));
	}
	
	public void addEntity(Entity e){
		populace.add(e);
	}
	
	public void populateMap() {
		//TODO: populate map with all entities, I.E spawn them.
	}
}