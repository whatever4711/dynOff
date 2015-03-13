package de.uniba.wiai.ktr.mg.dynoff.webservice.messages;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Nachrichtenklasse f�r die �bertragung Eintr�gen aus actorPreTable in
 * Actorenvironment per JAX-WS
 */
@XmlType
public class PropsPreAvailableMessage {

	private String actorName;
	private String description;

	public PropsPreAvailableMessage(String actorName, String description) {
		super();
		this.actorName = actorName;
		this.description = description;
	}

	@XmlElement(name = "actorname", required = true)
	public String getActorName() {
		return actorName;
	}

	public void setActorName(String actorName) {
		this.actorName = actorName;
	}

	@XmlElement(name = "description", required = true)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
