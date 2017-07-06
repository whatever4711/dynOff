package de.uniba.wiai.ktr.mg.dynoff.akkaenvironment.actors;

import akka.actor.AbstractActor;


public class TestActor extends AbstractActor {

    @Override
    public Receive createReceive() {
        return receiveBuilder().match(TestMessage.class,
                s -> getSender().tell(new TestMessage(s.getContent()), getSelf())).build();
    }

}
