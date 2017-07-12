package de.uniba.wiai.ktr.mg.dynoff.akkaenvironment;

import java.util.concurrent.ConcurrentHashMap;

import de.uniba.wiai.ktr.mg.dynoff.akkaenvironment.wrapper.ActorRefTimeWrapper;
import de.uniba.wiai.ktr.mg.dynoff.akkaenvironment.wrapper.JobTimeWrapper;

/**
 * Initialisierungsnachricht fuer die Datenuebergabe an AsyncMailboxActor
 */
public class AsyncMailboxActorIniMsg {

	private ConcurrentHashMap<String, ActorRefTimeWrapper> actorRefTable;
	private ConcurrentHashMap<String, JobTimeWrapper> jobsTable;
	private long storageTime;

	public AsyncMailboxActorIniMsg(
			ConcurrentHashMap<String, JobTimeWrapper> jobstable,
			ConcurrentHashMap<String, ActorRefTimeWrapper> actorRefTable,
			long storagetime) {
		this.jobsTable = jobstable;
		this.storageTime = storagetime;
		this.actorRefTable = actorRefTable;
	}

	public String toString() {
		return "Init";
	}

	public ConcurrentHashMap<String, JobTimeWrapper> getJobstable() {
		return jobsTable;
	}

	public ConcurrentHashMap<String, ActorRefTimeWrapper> getActorRefTable() {
		return actorRefTable;
	}

	public long getStorageTime() {
		return storageTime;
	}
}
