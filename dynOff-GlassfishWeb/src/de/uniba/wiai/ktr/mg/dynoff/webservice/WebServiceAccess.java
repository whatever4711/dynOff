package de.uniba.wiai.ktr.mg.dynoff.webservice;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.jws.WebService;

import de.uniba.wiai.ktr.mg.dynoff.akkaenvironment.Actorenvironment;
import de.uniba.wiai.ktr.mg.dynoff.akkaenvironment.wrapper.PropsPreAvailableWrapper;
import de.uniba.wiai.ktr.mg.dynoff.webservice.helper.SerializationHelper;
import de.uniba.wiai.ktr.mg.dynoff.webservice.messages.JobMessage;
import de.uniba.wiai.ktr.mg.dynoff.webservice.messages.JobMessageAsync;
import de.uniba.wiai.ktr.mg.dynoff.webservice.messages.PropsPreAvailableMessage;
import akka.actor.Props;

/**
 * Nach JAX-WS annotierte Klasse f�r die Erzeugung des Webserviceendpunktes.
 */
@WebService(portName = "DynOffWSPort", serviceName = "DynOffWebservice", targetNamespace = "http://dynoffws/", endpointInterface = "de.uniba.wiai.ktr.mg.dynoff.webservice.DynOffWS")
public class WebServiceAccess implements DynOffWS {

	/**
	 * Logger
	 */
	Logger logger = Logger.getLogger(WebServiceAccess.class.getName());

	@EJB(name = "ejb/Actorenvironment")
	private Actorenvironment actorenv;

	/*
	 * (non-Javadoc)
	 * 
	 * @see webservice.DynOffWS#generateActorFromProps(byte[])
	 */
	@Override
	public String generateActorFromProps(byte[] props) throws ServerFault {
		try {
			Props tmp = (Props) SerializationHelper.deserialize(props);
			String actor = actorenv.generateActorFromProps(tmp);
			logger.info("Actor created from Props: " + actor);
			return actor;

		} catch (Exception e) {
			logger.warning("Direct actor generation failed! Error: "
					+ e.getMessage());
			throw new ServerFault(e.getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see webservice.DynOffWS#sendMessage(webservice.messages.JobMessage)
	 */
	@Override
	public byte[] sendMessage(JobMessage msg) throws ServerFault {
		String actorid = msg.getActorid();
		int waittime = msg.getWaittime();
		byte[] content = msg.getMsg();
		logger.info("sendMessage to actor: " + actorid);
		try {

			Object msgobj = (Object) SerializationHelper.deserialize(content);
			Object respobj = actorenv.sendMessage(actorid, msgobj, waittime);
			return SerializationHelper.serialize(respobj);
		} catch (Exception e) {
			logger.warning("sendMessage failed! Error: " + e.getMessage());
			throw new ServerFault(e.getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * webservice.DynOffWS#dispatchAsyncJob(webservice.messages.JobMessageAsync)
	 */
	@Override
	public String dispatchAsyncJob(JobMessageAsync msg) throws ServerFault {

		try {
			return actorenv.dispatchAsyncJob(msg.getActorid(),
					SerializationHelper.deserialize(msg.getMsg()));
		} catch (Exception e) {
			logger.warning("dispatchAsyncJob failed! Error: " + e.getMessage());
			throw new ServerFault(e.getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see webservice.DynOffWS#getAsyncJobresult(java.lang.String)
	 */
	@Override
	public byte[] getAsyncJobresult(String jobid) throws ServerFault {
		Object tmp = actorenv.getAsyncJobResult(jobid);
		try {
			if (tmp != null) {
				return SerializationHelper.serialize(tmp);
			} else {
				throw new ServerFault("No message");
			}
		} catch (Exception e) {
			logger.warning("getAsyncJobresult failed! Error: " + e.getMessage());
			throw new ServerFault(e.getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see webservice.DynOffWS#getPreAvailableProps()
	 */
	@Override
	public List<PropsPreAvailableMessage> getPreAvailableProps() {
		List<PropsPreAvailableMessage> tmp = new ArrayList<>();
		for (PropsPreAvailableWrapper wrap : actorenv.getActorPreTable()
				.values()) {
			tmp.add(new PropsPreAvailableMessage(wrap.getActorName(), wrap
					.getDescription()));
		}
		return tmp;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see webservice.DynOffWS#generatePreAvailableActor(java.lang.String)
	 */
	@Override
	public String generatePreAvailableActor(String propsid) throws ServerFault {
		try {
			String tmp = actorenv.generateActorFromPreProps(propsid);
			logger.info("Preavailable actor created: " + tmp);
			return tmp;
		} catch (Exception e) {
			logger.warning("generatePreAvailableActor failed! Error: "
					+ e.getMessage());
			throw new ServerFault(e.getMessage());
		}
	}

}
