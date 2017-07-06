package de.uniba.wiai.ktr.mg.dynoff.akkaenvironment.actors;

import akka.actor.UntypedAbstractActor;


public class TestActor extends UntypedAbstractActor {

	@Override
	public void onReceive(Object arg0) throws Exception {
		TestMessage tmp = (TestMessage) arg0;
		this.getSender().tell(new TestMessage(tmp.getContent()), getSelf());
	}

}
