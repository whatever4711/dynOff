package de.uniba.wiai.ktr.mg.dynoff.client;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import akka.actor.Props;
import de.uniba.wiai.ktr.mg.dynoff.akkaenvironment.actors.TestActor;
import de.uniba.wiai.ktr.mg.dynoff.akkaenvironment.actors.TestMessage;
import de.uniba.wiai.ktr.mg.dynoff.generated.JobMessage;
import de.uniba.wiai.ktr.mg.dynoff.generated.JobMessageAsync;
import de.uniba.wiai.ktr.mg.dynoff.generated.PropsPreAvailableMessage;
import de.uniba.wiai.ktr.mg.dynoff.generated.ServerFault_Exception;
import de.uniba.wiai.ktr.mg.dynoff.shared.SerializationHelper;

public class TestMain {

	private DynOffWebserviceStub stub;
	private String testmsg = "lkasödlfjpowierlksdjfülaskdjfpoiwerksdjfaölsdjfpoweiru";

	public static void main(String[] args) throws ClassNotFoundException,
			IOException, ServerFault_Exception {
				String host = "localhost";
				if (args.length==1)
					host = args[0];

		URL WSDLLocation = new URL("http://" + host +":8080/dynOff/DynOffWebservice?wsdl");

		TestMain test = new TestMain(WSDLLocation);

		test.finaltest();

	}

	public TestMain(){
		stub = new DynOffWebserviceStub();
	}

	public TestMain(URL wsdl){
		stub = new DynOffWebserviceStub(wsdl);
	}

	public void finaltest() throws IOException, ClassNotFoundException,
			ServerFault_Exception {
		String preavailable = null;
		String perprops = null;
		String jobid = null;

		testgetPreAvailableProps();

		preavailable = testgeneratePreAvailableActor();

		perprops = testgenerateActorFromProps();

		testsendMessage(preavailable);

		jobid = testdispatchAsyncJob(perprops);

		testgetAsyncJobResult(jobid);
		System.out.println();
		System.out.println("All Tests are finished.");

	}

	public String testsendMessage(String actorid) throws ServerFault_Exception,
			ClassNotFoundException, IOException {

		System.out.println();
		System.out.println("Test of Method: sendMessage");
		System.out.println("======================================");
		System.out.println("Sending String: " + testmsg + " to actor: "
				+ actorid);

		TestMessage msg = new TestMessage(testmsg);
		JobMessage jobmsg = new JobMessage();
		jobmsg.setActorid(actorid);
		jobmsg.setWaittime(10000);
		jobmsg.setMessage(SerializationHelper.serialize(msg));

		byte[] tmpresp = stub.sendMessage(jobmsg);

		TestMessage resp = (TestMessage) SerializationHelper
				.deserialize(tmpresp);
		String syncresult = resp.getContent();
		System.out.println("Received String: " + syncresult);

		return syncresult;

	}

	public void testgetPreAvailableProps() {
		System.out.println("Test of Method: getPreAvailableProps");
		System.out.println("====================================");
		System.out.println("The following actors are available:");
		List<PropsPreAvailableMessage> tmp = stub.getPreAvailableProps();
		for (PropsPreAvailableMessage msg : tmp) {
			System.out.println(msg.getActorname());
		}

	}

	public String testgeneratePreAvailableActor() throws ServerFault_Exception {
		System.out.println();
		System.out.println("Test of Method: generatePreAvailableActor");
		System.out.println("=========================================");
		List<PropsPreAvailableMessage> tmp = stub.getPreAvailableProps();
		List<String> actorids = new ArrayList<>();
		for (PropsPreAvailableMessage msg : tmp) {
			System.out.println(msg.getActorname());

			actorids.add(stub.generatePreAvailableActor(msg.getActorname()));

		}
		System.out.println("Preavailable actor creation successful: "
				+ actorids.get(0));
		return actorids.get(0);
	}

	public String testgenerateActorFromProps() throws IOException,
			ServerFault_Exception {
		System.out.println();
		System.out.println("Test of Method: generateActorFromProps");
		System.out.println("======================================");
		byte[] tmp = null;

		tmp = SerializationHelper.serialize(Props.create(TestActor.class));

		String actorid = "Actor generation from Props failed!";

		actorid = stub.generateActorFromProps(tmp);
		System.out.println("Actor creation successful: " + actorid);
		return actorid;

	}

	public String testdispatchAsyncJob(String actorid) throws IOException,
			ServerFault_Exception {
		System.out.println();
		System.out.println("Test of Method: dispatchAsyncJob");
		System.out.println("======================================");
		System.out.println("Sending String: " + testmsg + " to actor: "
				+ actorid);

		TestMessage msgtosend = new TestMessage(testmsg);
		byte[] content = SerializationHelper.serialize(msgtosend);

		JobMessageAsync asyncmsg = new JobMessageAsync();
		asyncmsg.setActorid(actorid);
		asyncmsg.setMessage(content);

		String jobid = stub.dispatchAsyncJob(asyncmsg);
		System.out.println("Received jobid: " + jobid);
		System.out.println();
		System.out.println("Thread sleeps for 3 seconds");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return jobid;
	}

	public String testgetAsyncJobResult(String jobId)
			throws ClassNotFoundException, IOException, ServerFault_Exception {
		System.out.println();
		System.out.println("Test of Method: getAsyncJobResult");
		System.out.println("======================================");
		System.out.println("Trying to get result from jobid: " + jobId);

		TestMessage resp = null;

		resp = (TestMessage) SerializationHelper.deserialize(stub
				.getAsyncJobresult(jobId));

		System.out.println("Received String: " + resp.getContent());

		return resp.getContent();
	}

}
