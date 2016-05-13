package net.abysmal.engine.networking;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import net.abysmal.engine.graphics.Window;

public class Client {

	public boolean connected = false;
	private String name, address;
	private int port;
	private DatagramSocket socket;
	private InetAddress ip;
	private Thread send;
	private Thread listen;

	public Client(String name, String address) {
		this.name = name;
		if (address != null && address.split(":").length == 2) {
			this.address = address.split(":")[0];
			this.port = Integer.parseInt(address.split(":")[1]);
			boolean connected = openConnection(this.address);
			if (!connected) System.err.println("Unable to connect to " + this.address + this.port);
			if (connected) {
				//TODO Change to Launcher
				String message = "/c/" + convert(1337).substring(2);
				send(message.getBytes());
				if (recieve().startsWith("/s/c/")) {
					this.connected = true;
					listen();
				}
			}
		} else {
			System.err.println("Incorrect IP Format");
		}
	}

	public String getName() {
		return name;
	}

	public String getAddress() {
		return address;
	}

	public int getPort() {
		return port;
	}

	public String recieve() {
		byte[] data = new byte[1024];
		DatagramPacket packet = new DatagramPacket(data, data.length);

		try {
			socket.receive(packet);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return (new String(data));
	}

	public void listen() {
		listen = new Thread("Listen") {

			public void run() {
				while (connected) {
					String message = recieve();
					//TODO Change to Client
					Window.interpretCommunication.execute(Window.interpretCommunication.getCode(message));
				}
			}
		};
		listen.start();
	}

	public void send(final byte[] data) {
		send = new Thread("Send") {

			public void run() {
				DatagramPacket packet = new DatagramPacket(data, data.length, ip, port);
				try {
					socket.send(packet);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		};
		send.start();
	}

	public boolean openConnection(String address) {
		try {
			socket = new DatagramSocket();
			ip = InetAddress.getByName(address);
		} catch (UnknownHostException e) {
			e.printStackTrace();
			return false;
		} catch (SocketException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public static String convert(int number) {
		char[] result = new char[4];
		result[0] = (char) (number >>> 24);
		result[1] = (char) ((number >> 16) & 0xFF);
		result[2] = (char) ((number >> 8) & 0xFF);
		result[3] = (char) (number & 0xFF);
		return new String(result);
	}

	public static int convert(String message) {
		int result = 0;
		for (int i = 0; i < message.length(); i++) {
			char c = message.charAt(i);
			result = (result << 8) | (c & 0xFF);
		}
		return result;
	}

	public void closeConnection() {
		socket.close();
	}
}