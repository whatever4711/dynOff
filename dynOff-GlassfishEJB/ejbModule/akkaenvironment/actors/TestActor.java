package akkaenvironment.actors;

import akka.actor.UntypedActor;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;


public class TestActor extends UntypedActor {
    private TestMessage msg;


    @Override
    public void onReceive(Object arg0) throws Exception {
        msg = (TestMessage) arg0;

        this.getSender().tell(getImage("localhost", 4711), getSelf());
    }

    protected TestMessage getImage(String host, int port) {
        Socket socket = null;
        TestMessage rcvd = null;
        try  {
            socket = new Socket(InetAddress.getByName(host), port);
            ObjectOutputStream oout = new ObjectOutputStream(socket.getOutputStream());
            oout.writeObject(msg);
            oout.flush();

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
