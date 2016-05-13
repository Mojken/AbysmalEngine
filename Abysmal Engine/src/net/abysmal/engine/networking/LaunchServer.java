package net.abysmal.engine.networking;

public class LaunchServer {

	private int port;
	private Server server;

	public static boolean isNumeric(String str) {
		return str.matches("-?\\d+(\\.\\d+)?");
	}

	public LaunchServer(int port) {
		this.port = port;
		server = new Server(port);
	}

	public static void main(String[] args) {
		int port;
		if (args.length != 1 || !(isNumeric(args[0]))) {
			System.out.println("Usage: Java -jar CCServer.jar <port>");
			return;
		}
		port = Integer.parseInt(args[0]);
		new LaunchServer(port);
	}
}