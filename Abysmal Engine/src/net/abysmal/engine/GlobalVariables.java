package net.abysmal.engine;

public class GlobalVariables {
	
	private static int worldRotation;

	public static int getWorldRotation() {
		return worldRotation;
	}

	public static void setWorldRotation(int worldRotation) {
		GlobalVariables.worldRotation = worldRotation;
	}	
}