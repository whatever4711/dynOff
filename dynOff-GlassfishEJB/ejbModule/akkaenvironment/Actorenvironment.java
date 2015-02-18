package akkaenvironment;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;

import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;

import scala.concurrent.Await;
import scala.concurrent.Future;
import scala.concurrent.duration.Duration;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.pattern.Patterns;
import akka.util.Timeout;
import akkaenvironment.actors.TestActor;
import akkaenvironment.wrapper.ActorRefTimeWrapper;
import akkaenvironment.wrapper.JobTimeWrapper;
import akkaenvironment.wrapper.PropsPreAvailableWrapper;

/**
 * Singleton Session Bean Implementierung
 */
@ConcurrencyManagement(ConcurrencyManagementType.BEAN)
@Singleton(name = "ejb/Actorenvironment")
@LocalBean
public class Actorenvironment {

	/**
	 * Ausf�hrungssystem der Aktoren
	 */
	private ActorSystem actorsys;

	/**
	 * Enth�lt alle aktiven Aktorreferenzen, Shared Memory mit
	 * AsyncMailboxActor.
	 */
	private ConcurrentHashMap<String, ActorRefTimeWrapper> actorRefTable;

	/**
	 * Enth�lt alle fertigen, asynchronen Antworten, Shared Memory mit
	 * AsyncMailboxActor.
	 */
	private ConcurrentHashMap<String, JobTimeWrapper> jobsTable;

	/**
	 * Enth�lt alle explizit vorgehaltenen Props-Instanzen f�r die
	 * Aktorerzeugung
	 */
	private ConcurrentHashMap<String, PropsPreAvailableWrapper> actorPreTable;

	/**
	 * Referenz auf den Aktor f�r die Verwaltung von asynchronen Nachrichten
	 */
	private ActorRef asyncActor;

	/**
	 * Vorhaltezeit in Millisekunden f�r Aktorreferenzen und asynchrone
	 * Nachrichten. Wird an asyncActor beim initialen Programmstart mit
	 * AsyncMailboxActorIniMsg �bergeben und in ihm nochmals gehalten.
	 */
	private long storageTime = 600000;

	/**
	 * Constructor f�r die Instanziierung durch die Java EE-Laufzeitumgebung.
	 * Legt alle Tabellen an und initialisiert den AsyncMailboxActor.
	 */
	public Actorenvironment() {

		actorsys = ActorSystem.create();
		actorRefTable = new ConcurrentHashMap<>();
		jobsTable = new ConcurrentHashMap<>();
		Props async = Props.create(AsyncMailboxActor.class);
		asyncActor = actorsys.actorOf(async);
		asyncActor.tell(new AsyncMailboxActorIniMsg(jobsTable, actorRefTable,
				storageTime), null);
		actorPreTable = new ConcurrentHashMap<>();
		generateActorPreTable();
	}

	/**
	 * Erstellt die Inhalte von actorPreTable. Muss aktuell noch h�ndisch
	 * angepasst werden, sollten weitere Aktoren hinzugef�gt werden m�ssen.
	 */
	private void generateActorPreTable() {
		actorPreTable.put(
				TestActor.class.getName(),
				new PropsPreAvailableWrapper(TestActor.class.getName(),
						"Testactor fuer Echotest mit String", Props
								.create(TestActor.class)));
	}

	/**
	 * Erzeugt aus der �bergebenen Props-Instanz einen Aktor und gibt dessen
	 * Identifikationsstring zur�ck.
	 * 
	 * @param props
	 *            Props-Instanz aus der Class-Instanz des Aktors
	 * @return Aktoridentifikationsstring
	 */
	public String generateActorFromProps(Props props) {
		cleanup();
		ActorRef actor = actorsys.actorOf(props);
		ActorRefTimeWrapper tmp = new ActorRefTimeWrapper(actor,
				(System.currentTimeMillis() + storageTime));
		actorRefTable.put(actor.toString(), tmp);
		return actor.toString();
	}

	/**
	 * Sendet eine Nachricht mit synchroner Antwort an einen Aktor. L�uft die
	 * Wartezeit ab, wird eine Exception geworfen.
	 * 
	 * @param actorid
	 *            Identifikationsstring des Zielaktors.
	 * @param msg
	 *            Instanz der zu versenden Nachricht, muss im Aktor gecastet
	 *            werden.
	 * @param waittime
	 *            Maximale Wartezeit auf die Antwort.
	 * @return Antwortobjekt
	 * @throws Exception
	 *             Wird geworfen bei Ablauf der Wartezeit oder wenn der
	 *             Zielaktor nicht existiert.
	 * 
	 * 
	 */
	public Object sendMessage(String actorid, Object msg, int waittime)
			throws Exception {
		ActorRefTimeWrapper tmp = actorRefTable.get(actorid);
		if (tmp == null) {
			throw new Exception("Actor not available.");
		} else {
			tmp.setTimeout(System.currentTimeMillis() + storageTime);
			ActorRef actor = tmp.getActorref();
			Timeout timeout = new Timeout(Duration.create(waittime, "seconds"));
			Future<Object> future = Patterns.ask(actor, msg, timeout);
			Object result;
			result = Await.result(future, timeout.duration());
			return result;
		}
	}

	/**
	 * Leitet einen asynchronen Auftrag weiter und gibt einen
	 * Jobidentifikationsstring zur�ck, f�r den Abruf des Ergebnisses �ber
	 * getAsyncJobResult(String).
	 * 
	 * @param actorId
	 *            Identifikationsstring des Zielaktors.
	 * @param msg
	 *            Instanz der zu versenden Nachricht, muss im Aktor gecastet
	 *            werden.
	 * @return Jobidentifikationsstring
	 * @throws Exception
	 *             Wird geworfen wenn der Zielaktor nicht existiert.
	 */
	public String dispatchAsyncJob(String actorId, Object msg) throws Exception {
		AsyncMailboxActorJobMsg jobMsg = new AsyncMailboxActorJobMsg(actorId,
				msg);
		Timeout timeout = new Timeout(Duration.create(3, "seconds"));
		Future<Object> future = Patterns.ask(asyncActor, jobMsg, timeout);
		jobMsg = (AsyncMailboxActorJobMsg) Await.result(future,
				timeout.duration());
		if (jobMsg.getActorId() == null) {
			throw new Exception("Actor not available.");
		}
		return jobMsg.getActorId();
	}

	/**
	 * Dient dem asynchronen Ergebnisabruf mit einem Jobidentifikationsstring.
	 * 
	 * @param jobId
	 *            Jobidentifikationsstring
	 * @return Antwortobjekt
	 * @throws Exception
	 *             Wird geworfen wenn keine Antwort f�r den
	 *             Jobidentifikationsstring vorhanden ist.
	 */
	public Object getAsyncJobResult(String jobId) {
		JobTimeWrapper tmp = jobsTable.remove(jobId);
		if (tmp != null) {
			return tmp.getResultMsg();
		} else {
			return null;
		}
	}

	/**
	 * L�scht alle Aktorreferenzen und Ergebnisnachrichten, deren Vorhaltezeit
	 * abgelaufen ist.
	 */
	public void cleanup() {
		long currentTime = System.currentTimeMillis();
		Collection<ActorRefTimeWrapper> actorcol = actorRefTable.values();
		Collection<JobTimeWrapper> jobcol = jobsTable.values();
		for (ActorRefTimeWrapper wrap : actorcol) {
			if (wrap.getTimeout() < currentTime) {
				wrap.getActorref().tell(akka.actor.PoisonPill.getInstance(),
						null);
				actorcol.remove(wrap);
			}
		}
		for (JobTimeWrapper wrap : jobcol) {
			if (wrap.getTimeout() < currentTime) {
				jobcol.remove(wrap);
			}
		}
	}

	/**
	 * Gibt die actorPreTable zur�ck.
	 * 
	 * @return actorPreTable
	 */
	public ConcurrentHashMap<String, PropsPreAvailableWrapper> getActorPreTable() {
		return actorPreTable;
	}

	/**
	 * Erzeugt einen Aktor aus den in actorPreTable vorhaltenen Props-Instanzen
	 * 
	 * @param propsid
	 *            Vollqualifizierender Name der Aktorklasse, erh�ltlich �ber
	 *            getActorPreTable()
	 * @return Aktoridentifikationsstring
	 */
	public String generateActorFromPreProps(String propsid) {
		cleanup();
		ActorRef actor = actorsys
				.actorOf(actorPreTable.get(propsid).getProps());
		actorRefTable.put(actor.toString(), new ActorRefTimeWrapper(actor,
				System.currentTimeMillis() + storageTime));
		return actor.toString();
	}

}
