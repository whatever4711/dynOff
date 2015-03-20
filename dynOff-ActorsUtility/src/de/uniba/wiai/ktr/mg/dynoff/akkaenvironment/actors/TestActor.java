package de.uniba.wiai.ktr.mg.dynoff.akkaenvironment.actors;

import akka.actor.UntypedActor;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class TestActor extends UntypedActor {

	@Override
	public void onReceive(Object arg0) throws Exception {
		TestMessage msg = (TestMessage) arg0;
		System.out.println("Received " + msg.getContent());

		final TestMessage response = getImage(msg);
		System.out.println("Returning " + response.getContent());

		this.getSender().tell(response, getSelf());

	}

	protected TestMessage getImage(TestMessage msg) {
		// Socket socket = null;
		TestMessage rcvd = null;
		Socket socket = null;
		try {
			socket = new Socket(InetAddress.getByName(msg.getHost()),
					msg.getPort());
			ObjectOutputStream oos = new ObjectOutputStream(
					socket.getOutputStream());
			oos.writeObject(msg);
			oos.flush();

			ObjectInput ois = new ObjectInputStream(socket.getInputStream());
			Object in = ois.readObject();
			if (in instanceof TestMessage) {
				rcvd = (TestMessage) in;
			}

		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				if (socket != null)
					socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return rcvd;

	}
}
