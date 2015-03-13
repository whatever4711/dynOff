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
        
        final TestMessage response = getImage(msg, "141.13.92.2", 4711);
        System.out.println("Returning " + response.getContent());

        this.getSender().tell(response, getSelf());
        
    }

    protected TestMessage getImage(TestMessage msg, String host, int port) {
        Socket socket = null;
        TestMessage rcvd = null;
        try  {
            socket = new Socket(InetAddress.getByName(host), port);
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(msg);
            oos.flush();

            ObjectInput ois = new ObjectInputStream(socket.getInputStream());
            Object in = ois.readObject();
            if(in instanceof TestMessage) {
                 rcvd = (TestMessage) in;
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if(socket!=null) try {
                socket.close();
            } catch (final IOException e) {
                e.printStackTrace();
            }
            return rcvd;
        }

    }
}
