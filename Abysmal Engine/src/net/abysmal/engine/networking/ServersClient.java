package net.abysmal.engine.networking;

import java.net.InetAddress;

public class ServersClient {

	public InetAddress	address;
	public short		ID;
	public int			port;
	public int			attempt	= 0;

	public ServersClient(short ID, InetAddress address, int port) {
		this.ID = ID;
		this.address = address;
		this.port = port;
	}

	public short getID() {
		return ID;
	}
}