package de.uniba.wiai.ktr.mg.dynoff.client;

import java.net.URL;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.xml.ws.Action;
import javax.xml.ws.FaultAction;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

import de.uniba.wiai.ktr.mg.dynoff.generated.DynOffWS;
import de.uniba.wiai.ktr.mg.dynoff.generated.DynOffWebservice;
import de.uniba.wiai.ktr.mg.dynoff.generated.JobMessage;
import de.uniba.wiai.ktr.mg.dynoff.generated.JobMessageAsync;
import de.uniba.wiai.ktr.mg.dynoff.generated.PropsPreAvailableMessage;
import de.uniba.wiai.ktr.mg.dynoff.generated.ServerFault_Exception;

public class DynOffWebserviceStub implements DynOffWS {

	private DynOffWebservice service;
	private DynOffWS proxy;

	public DynOffWebserviceStub() {
		super();
		service = new DynOffWebservice();
		proxy = service.getDynOffWSPort();
	}

	public DynOffWebserviceStub(URL wsdlLocation) {
		service = new DynOffWebservice(wsdlLocation);
		proxy = service.getDynOffWSPort();
	}

	@Override
	@WebMethod
	@WebResult(name = "actorid", targetNamespace = "")
	@RequestWrapper(localName = "generateActorFromProps", targetNamespace = "http://dynoffws/", className = "de.uniba.wiai.ktr.mg.dynoff.generated.GenerateActorFromProps")
	@ResponseWrapper(localName = "generateActorFromPropsResponse", targetNamespace = "http://dynoffws/", className = "de.uniba.wiai.ktr.mg.dynoff.generated.GenerateActorFromPropsResponse")
	@Action(input = "http://dynoffws/WebServiceAccess/generateActorFromPropsRequest", output = "http://dynoffws/WebServiceAccess/generateActorFromPropsResponse", fault = @FaultAction(className = ServerFault_Exception.class, value = "http://dynoffws/WebServiceAccess/generateActorFromProps/Fault/ServerFault"))
	public String generateActorFromProps(
			@WebParam(name = "props", targetNamespace = "") byte[] props)
			throws ServerFault_Exception {
		return proxy.generateActorFromProps(props);
	}

	@Override
	@WebMethod
	@WebResult(name = "jobid", targetNamespace = "")
	@RequestWrapper(localName = "dispatchAsyncJob", targetNamespace = "http://dynoffws/", className = "de.uniba.wiai.ktr.mg.dynoff.generated.DispatchAsyncJob")
	@ResponseWrapper(localName = "dispatchAsyncJobResponse", targetNamespace = "http://dynoffws/", className = "de.uniba.wiai.ktr.mg.dynoff.generated.DispatchAsyncJobResponse")
	@Action(input = "http://dynoffws/WebServiceAccess/dispatchAsyncJobRequest", output = "http://dynoffws/WebServiceAccess/dispatchAsyncJobResponse", fault = @FaultAction(className = ServerFault_Exception.class, value = "http://dynoffws/WebServiceAccess/dispatchAsyncJob/Fault/ServerFault"))
	public String dispatchAsyncJob(
			@WebParam(name = "jobmessageasync", targetNamespace = "") JobMessageAsync jobmessageasync)
			throws ServerFault_Exception {
		return proxy.dispatchAsyncJob(jobmessageasync);
	}

	@Override
	@WebMethod
	@WebResult(name = "resultmessage", targetNamespace = "")
	@RequestWrapper(localName = "getAsyncJobresult", targetNamespace = "http://dynoffws/", className = "de.uniba.wiai.ktr.mg.dynoff.generated.GetAsyncJobresult")
	@ResponseWrapper(localName = "getAsyncJobresultResponse", targetNamespace = "http://dynoffws/", className = "de.uniba.wiai.ktr.mg.dynoff.generated.GetAsyncJobresultResponse")
	@Action(input = "http://dynoffws/WebServiceAccess/getAsyncJobresultRequest", output = "http://dynoffws/WebServiceAccess/getAsyncJobresultResponse", fault = @FaultAction(className = ServerFault_Exception.class, value = "http://dynoffws/WebServiceAccess/getAsyncJobresult/Fault/ServerFault"))
	public byte[] getAsyncJobresult(
			@WebParam(name = "jobid", targetNamespace = "") String jobid)
			throws ServerFault_Exception {
		return proxy.getAsyncJobresult(jobid);
	}

	@Override
	@WebMethod
	@WebResult(name = "preavailable", targetNamespace = "")
	@RequestWrapper(localName = "getPreAvailableProps", targetNamespace = "http://dynoffws/", className = "de.uniba.wiai.ktr.mg.dynoff.generated.GetPreAvailableProps")
	@ResponseWrapper(localName = "getPreAvailablePropsResponse", targetNamespace = "http://dynoffws/", className = "de.uniba.wiai.ktr.mg.dynoff.generated.GetPreAvailablePropsResponse")
	@Action(input = "http://dynoffws/WebServiceAccess/getPreAvailablePropsRequest", output = "http://dynoffws/WebServiceAccess/getPreAvailablePropsResponse")
	public List<PropsPreAvailableMessage> getPreAvailableProps() {
		return proxy.getPreAvailableProps();
	}

	@Override
	@WebMethod
	@WebResult(name = "actorid", targetNamespace = "")
	@RequestWrapper(localName = "generatePreAvailableActor", targetNamespace = "http://dynoffws/", className = "de.uniba.wiai.ktr.mg.dynoff.generated.GeneratePreAvailableActor")
	@ResponseWrapper(localName = "generatePreAvailableActorResponse", targetNamespace = "http://dynoffws/", className = "de.uniba.wiai.ktr.mg.dynoff.generated.GeneratePreAvailableActorResponse")
	@Action(input = "http://dynoffws/WebServiceAccess/generatePreAvailableActorRequest", output = "http://dynoffws/WebServiceAccess/generatePreAvailableActorResponse", fault = @FaultAction(className = ServerFault_Exception.class, value = "http://dynoffws/WebServiceAccess/generatePreAvailableActor/Fault/ServerFault"))
	public String generatePreAvailableActor(
			@WebParam(name = "propsid", targetNamespace = "") String propsid)
			throws ServerFault_Exception {
		return proxy.generatePreAvailableActor(propsid);
	}

	@Override
	@WebMethod
	@WebResult(name = "resultmessage", targetNamespace = "")
	@RequestWrapper(localName = "sendMessage", targetNamespace = "http://dynoffws/", className = "de.uniba.wiai.ktr.mg.dynoff.generated.SendMessage")
	@ResponseWrapper(localName = "sendMessageResponse", targetNamespace = "http://dynoffws/", className = "de.uniba.wiai.ktr.mg.dynoff.generated.SendMessageResponse")
	@Action(input = "http://dynoffws/WebServiceAccess/sendMessageRequest", output = "http://dynoffws/WebServiceAccess/sendMessageResponse", fault = @FaultAction(className = ServerFault_Exception.class, value = "http://dynoffws/WebServiceAccess/sendMessage/Fault/ServerFault"))
	public byte[] sendMessage(
			@WebParam(name = "jobmessage", targetNamespace = "") JobMessage jobmessage)
			throws ServerFault_Exception {
		byte[] tmp = proxy.sendMessage(jobmessage);
		return tmp;
	}

}
