package net.abysmal.engine;

public class GlobalVariables {
	
	private static int worldRotation;
	private static int dimensions;
	public static String textureRoot;
	
	public static void setTextureRoot(String path){
		textureRoot = path.substring(6);
	}

	public static int getWorldRotation() {
		return worldRotation;
	}
	
	public static int getDimensions() {
		return dimensions;
	}

	public static void setWorldRotation(int worldRotation) {
		GlobalVariables.worldRotation = worldRotation;
	}	
}