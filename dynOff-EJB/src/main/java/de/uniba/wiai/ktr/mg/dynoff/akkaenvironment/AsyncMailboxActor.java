package de.uniba.wiai.ktr.mg.dynoff.akkaenvironment;

import java.util.Collection;
import java.util.Hashtable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.logging.Logger;

import de.uniba.wiai.ktr.mg.dynoff.akkaenvironment.wrapper.ActorRefTimeWrapper;
import de.uniba.wiai.ktr.mg.dynoff.akkaenvironment.wrapper.JobTimeWrapper;
import akka.actor.AbstractActor;

/**
 * Aktor fuer die Verwaltung von asynchronen Nachrichten.
 */
public class AsyncMailboxActor extends AbstractActor {

	/**
	 * Logger
	 */
	Logger logger = Logger.getLogger(AsyncMailboxActor.class.getName());

	/**
	 * Enthaelt alle aktiven Aktorreferenzen, Shared Memory mit Actorenvironment.
	 */
	private ConcurrentHashMap<String, ActorRefTimeWrapper> actorRefTable;

	/**
	 * Enthaelt alle fertigen, asynchronen Antworten, Shared Memory mit
	 * Actorenvironment.
	 */
	private ConcurrentHashMap<String, JobTimeWrapper> jobsTable;

	/**
	 * Hilfstabelle fuer die Verwaltung von asynchronen Auftraegen, bis zum
	 * Eintreffen deren Antwort.
	 */
	private Hashtable<String, ConcurrentLinkedQueue<JobTimeWrapper>> openJobs = new Hashtable<>();

	/**
	 * Vorhaltezeit in Millisekunden fuer Aktorreferenzen und asynchrone
	 * Nachrichten. Wird primaer in Actorenvironment gehalten.
	 */
	private long storageTime;

	@Override
	public Receive createReceive() {
		return receiveBuilder().matchUnchecked(AsyncMailboxActorIniMsg.class, this::initMsgReceived).
				matchUnchecked(AsyncMailboxActorJobMsg.class, this::jobMsgReceived).
				matchAny(this::resultMsgReceived).build();
	}

	/**
	 * 
	 * 
	 * @param msg
	 *            Nachrichtenobjekt
	 */
	private void initMsgReceived(Object msg) {
		logger.info("Init Message received: " + msg.getClass().toString());
		AsyncMailboxActorIniMsg arg = (AsyncMailboxActorIniMsg) msg;
		jobsTable = arg.getJobstable();
		storageTime = arg.getStorageTime();
		actorRefTable = arg.getActorRefTable();
	}

	/**
	 * Dient der Verarbeitung von neuen, asynchronen Auftraegen. Legt eine
	 * Postkastenqueue in openJobs an und schickt den Jobidentifikationsstring
	 * zurueck.
	 * 
	 * @param msg
	 *            Nachrichtenobjekt
	 */
	private void jobMsgReceived(Object msg) {
		logger.info("Job Message received: " + msg.getClass().toString());
		cleanUp();
		AsyncMailboxActorJobMsg jobMsg = (AsyncMailboxActorJobMsg) msg;

		ActorRefTimeWrapper wrap = actorRefTable.get(jobMsg.getActorId());

		String jobId = jobMsg.getActorId() + System.currentTimeMillis();

		if (wrap != null) {

			wrap.setTimeout(System.currentTimeMillis() + storageTime);
			JobTimeWrapper openJobResult = new JobTimeWrapper(jobId,
					jobMsg.getMsg(), (System.currentTimeMillis() + storageTime));

			ConcurrentLinkedQueue<JobTimeWrapper> tmpqueue = openJobs
					.get(jobMsg.getActorId());

			if (tmpqueue != null) {

				tmpqueue.add(openJobResult);

			} else {

				tmpqueue = new ConcurrentLinkedQueue<JobTimeWrapper>();
				tmpqueue.add(openJobResult);
				openJobs.put(jobMsg.getActorId(), tmpqueue);
			}

			jobMsg.setActorId(jobId);
			getSender().tell(jobMsg, getSelf());
		} else {
			openJobs.remove(jobMsg.getActorId());
			jobMsg.setMsg(null);
			jobMsg.setActorId(null);
			getSender().tell(jobMsg, getSelf());
		}
		wrap.getActorref().tell(jobMsg.getMsg(), getSelf());
	}

	/**
	 * Verlegt die erhaltene asynchrone Aktorantwort von openJobs nach
	 * jobsTable.
	 * 
	 * @param msg
	 *            Nachrichtenobjekt
	 */
	private void resultMsgReceived(Object msg) {
		logger.info("Result Message received: " + msg.getClass().toString());
		logger.info("OpenJobs Size: " + openJobs.size());
		if(openJobs.size() > 0) {
			JobTimeWrapper result = openJobs.get(getSender().toString()).poll();
			logger.info("Result Wrapper: " + result.getResultMsg().toString());
			result.setTimeout(System.currentTimeMillis() + storageTime);
			result.setResultMsg(msg);
			jobsTable.put(result.getJobId(), result);
		}
	}

	/**
	 * Entfernt abgelaufene Auftraege aus openJobs
	 */
	private void cleanUp() {
		Collection<ConcurrentLinkedQueue<JobTimeWrapper>> col = openJobs
				.values();
		for (ConcurrentLinkedQueue<JobTimeWrapper> q : col) {
			if (q.isEmpty()) {
				col.remove(q);
			} else {
				for (JobTimeWrapper w : q) {
					if (w.getTimeout() < System.currentTimeMillis()) {
						q.remove(w);
					}
				}
			}
		}
	}

}
