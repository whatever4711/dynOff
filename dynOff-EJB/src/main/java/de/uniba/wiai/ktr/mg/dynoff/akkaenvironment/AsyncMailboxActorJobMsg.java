package de.uniba.wiai.ktr.mg.dynoff.akkaenvironment;

/**
 * Nachricht fuer die Weiterleitung von asynchronen Nachrichten fuer die
 * Verwaltung durch AsyncMailboxActor.
 */
public class AsyncMailboxActorJobMsg {

	private String actorId;
	private Object msg;

	public AsyncMailboxActorJobMsg(String actorId, Object msg) {
		super();
		this.actorId = actorId;
		this.msg = msg;
	}

	public String getActorId() {
		return actorId;
	}

	public Object getMsg() {
		return msg;
	}

	public void setActorId(String actorId) {
		this.actorId = actorId;
	}

	public void setMsg(Object msg) {
		this.msg = msg;
	}

	public String toString() {
		return "JobMsg";
	}
}
