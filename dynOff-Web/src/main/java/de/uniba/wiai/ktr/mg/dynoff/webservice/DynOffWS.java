package de.uniba.wiai.ktr.mg.dynoff.webservice;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import de.uniba.wiai.ktr.mg.dynoff.webservice.messages.JobMessage;
import de.uniba.wiai.ktr.mg.dynoff.webservice.messages.JobMessageAsync;
import de.uniba.wiai.ktr.mg.dynoff.webservice.messages.PropsPreAvailableMessage;

@WebService(name = "DynOffWS", targetNamespace="http://dynoffws/")
public interface DynOffWS {

	/**
	 * Erzeugt eine Aktorinstanz aus einer serialisierten Props-Instanz.
	 * 
	 * @param props
	 *            Serialisierte Props-Instanz
	 * @return Aktoridentifikationsstring
	 * @throws ServerFault
	 *             Wird im Falle eines Fehlers geworfen.
	 */
	@WebMethod
	@WebResult(name = "actorid")
	public String generateActorFromProps(@WebParam(name = "props") byte[] props)
			throws ServerFault;

	/**
	 * Bearbeitet synchrone Nachrichten.
	 * 
	 * @param msg
	 *            In JobMessage gekapselte Nachricht
	 * @return Serialisiertes Antwortobjekt
	 * @throws ServerFault
	 *             Exception f�r die die Fehler�bertragung per JAX-WS
	 */
	@WebMethod
	@WebResult(name = "resultmessage")
	public byte[] sendMessage(@WebParam(name = "jobmessage") JobMessage msg) throws ServerFault;

	/**
	 * Bearbeitet asynchrone Nachrichten.
	 * 
	 * @param msg
	 *            In JobMessageAsync gekapselte Nachricht
	 * @return Postkastenreferenzstring
	 * @throws ServerFault
	 *             Exception f�r die die Fehler�bertragung per JAX-WS
	 */
	@WebMethod
	@WebResult(name = "jobid")
	public String dispatchAsyncJob(@WebParam(name = "jobmessageasync") JobMessageAsync msg)
			throws ServerFault;

	/**
	 * Dient dem Abruf von asynchronen Antworten
	 * 
	 * @param jobid
	 *            Postkastenreferenzstring
	 * @return Serialisiertes Antwortobjekt
	 * @throws ServerFault
	 *             Exception f�r die die Fehler�bertragung per JAX-WS
	 */
	@WebMethod
	@WebResult(name = "resultmessage")
	public byte[] getAsyncJobresult(@WebParam(name = "jobid") String jobid) throws ServerFault;

	/**
	 * �bertr�gt die Inhalte von actorPreTable aus Actorenvironment
	 * 
	 * @return actorPreTable aus Actorenvironment
	 */
	@WebMethod
	@WebResult(name = "preavailable")
	public List<PropsPreAvailableMessage> getPreAvailableProps();

	/**
	 * Erzeugt einen Aktor aus den in actorPreTable vorhaltenen Props-Instanzen
	 * 
	 * @param propsid
	 *            Vollqualifizierender Name der Aktorklasse, erh�ltlich �ber
	 *            getActorPreTable()
	 * @return Aktoridentifikationsstring
	 * @throws ServerFault
	 *             Exception f�r die die Fehler�bertragung per JAX-WS
	 */
	@WebMethod
	@WebResult(name = "actorid")
	public String generatePreAvailableActor(@WebParam(name = "propsid") String propsid)
			throws ServerFault;

}