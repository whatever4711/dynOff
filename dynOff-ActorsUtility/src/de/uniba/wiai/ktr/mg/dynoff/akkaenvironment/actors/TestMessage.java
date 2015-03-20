package de.uniba.wiai.ktr.mg.dynoff.akkaenvironment.actors;

import java.io.Serializable;

@SuppressWarnings("serial")
public class TestMessage implements Serializable {
	// private static final long serialVersionUID = -2800066513310728483L;

	private final String content, host;
	private final int port;
	private final byte[] image;

	public TestMessage(String content, String host, int port, byte[] image) {
		this.content = content;
		this.host = host;
		this.port = port;
		this.image = image;
	}

	public String getContent() {
		return content;
	}

	public String getHost() {
		return host;
	}

	public int getPort() {
		return port;
	}

	public byte[] getImage() {
		return image;
	}

}
