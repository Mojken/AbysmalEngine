package net.abysmal.engine.networking;

public interface InterpretCommunication {
	
	public abstract int getCode(String command);
	public abstract void execute(int code);
	
}