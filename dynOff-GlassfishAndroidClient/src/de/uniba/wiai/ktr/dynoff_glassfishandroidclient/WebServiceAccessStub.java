package de.uniba.wiai.ktr.dynoff_glassfishandroidclient;

import java.net.URL;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.xml.namespace.QName;
import javax.xml.ws.Action;
import javax.xml.ws.FaultAction;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

import de.uniba.wiai.ktr.dynoff_wsdlstub.JobMessage;
import de.uniba.wiai.ktr.dynoff_wsdlstub.JobMessageAsync;
import de.uniba.wiai.ktr.dynoff_wsdlstub.PropsPreAvailableMessage;
import de.uniba.wiai.ktr.dynoff_wsdlstub.ServerFault_Exception;
import de.uniba.wiai.ktr.dynoff_wsdlstub.WebServiceAccess;
import de.uniba.wiai.ktr.dynoff_wsdlstub.WebServiceAccessService;


public class WebServiceAccessStub implements WebServiceAccess {

	private WebServiceAccessService service;
	private WebServiceAccess proxy;
	private final static QName WEBSERVICEACCESSSERVICE_QNAME = new QName("http://webservice/", "WebServiceAccessService");

	public WebServiceAccessStub() {
		super();
		service = new WebServiceAccessService();
		proxy = service.getWebServiceAccessPort();
	}

	public WebServiceAccessStub(URL wsdlLocation) {
		service = new WebServiceAccessService(wsdlLocation, WEBSERVICEACCESSSERVICE_QNAME);
		proxy = service.getWebServiceAccessPort();
	}

	@Override
	@WebMethod
	@WebResult(name = "actorid", targetNamespace = "")
	@RequestWrapper(localName = "generateActorFromProps", targetNamespace = "http://webservice/", className = "ktrsem.ws.clientstub.generated.GenerateActorFromProps")
	@ResponseWrapper(localName = "generateActorFromPropsResponse", targetNamespace = "http://webservice/", className = "ktrsem.ws.clientstub.generated.GenerateActorFromPropsResponse")
	@Action(input = "http://webservice/WebServiceAccess/generateActorFromPropsRequest", output = "http://webservice/WebServiceAccess/generateActorFromPropsResponse", fault = @FaultAction(className = ServerFault_Exception.class, value = "http://webservice/WebServiceAccess/generateActorFromProps/Fault/ServerFault"))
	public String generateActorFromProps(
			@WebParam(name = "props", targetNamespace = "") byte[] props)
			throws ServerFault_Exception {
		return proxy.generateActorFromProps(props);
	}

	@Override
	@WebMethod
	@WebResult(name = "jobid", targetNamespace = "")
	@RequestWrapper(localName = "dispatchAsyncJob", targetNamespace = "http://webservice/", className = "ktrsem.ws.clientstub.generated.DispatchAsyncJob")
	@ResponseWrapper(localName = "dispatchAsyncJobResponse", targetNamespace = "http://webservice/", className = "ktrsem.ws.clientstub.generated.DispatchAsyncJobResponse")
	@Action(input = "http://webservice/WebServiceAccess/dispatchAsyncJobRequest", output = "http://webservice/WebServiceAccess/dispatchAsyncJobResponse", fault = @FaultAction(className = ServerFault_Exception.class, value = "http://webservice/WebServiceAccess/dispatchAsyncJob/Fault/ServerFault"))
	public String dispatchAsyncJob(
			@WebParam(name = "jobmessageasync", targetNamespace = "") JobMessageAsync jobmessageasync)
			throws ServerFault_Exception {
		return proxy.dispatchAsyncJob(jobmessageasync);
	}

	@Override
	@WebMethod
	@WebResult(name = "resultmessage", targetNamespace = "")
	@RequestWrapper(localName = "getAsyncJobresult", targetNamespace = "http://webservice/", className = "ktrsem.ws.clientstub.generated.GetAsyncJobresult")
	@ResponseWrapper(localName = "getAsyncJobresultResponse", targetNamespace = "http://webservice/", className = "ktrsem.ws.clientstub.generated.GetAsyncJobresultResponse")
	@Action(input = "http://webservice/WebServiceAccess/getAsyncJobresultRequest", output = "http://webservice/WebServiceAccess/getAsyncJobresultResponse", fault = @FaultAction(className = ServerFault_Exception.class, value = "http://webservice/WebServiceAccess/getAsyncJobresult/Fault/ServerFault"))
	public byte[] getAsyncJobresult(
			@WebParam(name = "jobid", targetNamespace = "") String jobid)
			throws ServerFault_Exception {
		return proxy.getAsyncJobresult(jobid);
	}

	@Override
	@WebMethod
	@WebResult(name = "preavailable", targetNamespace = "")
	@RequestWrapper(localName = "getPreAvailableProps", targetNamespace = "http://webservice/", className = "ktrsem.ws.clientstub.generated.GetPreAvailableProps")
	@ResponseWrapper(localName = "getPreAvailablePropsResponse", targetNamespace = "http://webservice/", className = "ktrsem.ws.clientstub.generated.GetPreAvailablePropsResponse")
	@Action(input = "http://webservice/WebServiceAccess/getPreAvailablePropsRequest", output = "http://webservice/WebServiceAccess/getPreAvailablePropsResponse")
	public List<PropsPreAvailableMessage> getPreAvailableProps() {
		return proxy.getPreAvailableProps();
	}

	@Override
	@WebMethod
	@WebResult(name = "actorid", targetNamespace = "")
	@RequestWrapper(localName = "generatePreAvailableActor", targetNamespace = "http://webservice/", className = "ktrsem.ws.clientstub.generated.GeneratePreAvailableActor")
	@ResponseWrapper(localName = "generatePreAvailableActorResponse", targetNamespace = "http://webservice/", className = "ktrsem.ws.clientstub.generated.GeneratePreAvailableActorResponse")
	@Action(input = "http://webservice/WebServiceAccess/generatePreAvailableActorRequest", output = "http://webservice/WebServiceAccess/generatePreAvailableActorResponse", fault = @FaultAction(className = ServerFault_Exception.class, value = "http://webservice/WebServiceAccess/generatePreAvailableActor/Fault/ServerFault"))
	public String generatePreAvailableActor(
			@WebParam(name = "propsid", targetNamespace = "") String propsid)
			throws ServerFault_Exception {
		return proxy.generatePreAvailableActor(propsid);
	}

	@Override
	@WebMethod
	@WebResult(name = "resultmessage", targetNamespace = "")
	@RequestWrapper(localName = "sendMessage", targetNamespace = "http://webservice/", className = "ktrsem.ws.clientstub.generated.SendMessage")
	@ResponseWrapper(localName = "sendMessageResponse", targetNamespace = "http://webservice/", className = "ktrsem.ws.clientstub.generated.SendMessageResponse")
	@Action(input = "http://webservice/WebServiceAccess/sendMessageRequest", output = "http://webservice/WebServiceAccess/sendMessageResponse", fault = @FaultAction(className = ServerFault_Exception.class, value = "http://webservice/WebServiceAccess/sendMessage/Fault/ServerFault"))
	public byte[] sendMessage(
			@WebParam(name = "jobmessage", targetNamespace = "") JobMessage jobmessage)
			throws ServerFault_Exception {
		byte[] tmp = proxy.sendMessage(jobmessage);
		return tmp;
	}

}
