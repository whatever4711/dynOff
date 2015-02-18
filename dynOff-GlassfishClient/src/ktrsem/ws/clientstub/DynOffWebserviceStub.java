package ktrsem.ws.clientstub;

import java.net.URL;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.xml.ws.Action;
import javax.xml.ws.FaultAction;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

import ktrsem.ws.clientstub.generated.DynOffWS;
import ktrsem.ws.clientstub.generated.DynOffWebservice;
import ktrsem.ws.clientstub.generated.JobMessage;
import ktrsem.ws.clientstub.generated.JobMessageAsync;
import ktrsem.ws.clientstub.generated.PropsPreAvailableMessage;
import ktrsem.ws.clientstub.generated.ServerFault_Exception;

public class DynOffWebserviceStub implements DynOffWebservice {

	private DynOffWS service;
	private DynOffWebservice proxy;

	public DynOffWebserviceStub() {
		super();
		service = new DynOffWS();
		proxy = service.getWebServiceAccessPort();
	}

	public DynOffWebserviceStub(URL wsdlLocation) {
		service = new DynOffWS(wsdlLocation);
		proxy = service.getWebServiceAccessPort();
	}

	@Override
	@WebMethod
	@WebResult(name = "actorid", targetNamespace = "")
	@RequestWrapper(localName = "generateActorFromProps", targetNamespace = "http://dynoffws/", className = "ktrsem.ws.clientstub.generated.GenerateActorFromProps")
	@ResponseWrapper(localName = "generateActorFromPropsResponse", targetNamespace = "http://dynoffws/", className = "ktrsem.ws.clientstub.generated.GenerateActorFromPropsResponse")
	@Action(input = "http://dynoffws/WebServiceAccess/generateActorFromPropsRequest", output = "http://dynoffws/WebServiceAccess/generateActorFromPropsResponse", fault = @FaultAction(className = ServerFault_Exception.class, value = "http://dynoffws/WebServiceAccess/generateActorFromProps/Fault/ServerFault"))
	public String generateActorFromProps(
			@WebParam(name = "props", targetNamespace = "") byte[] props)
			throws ServerFault_Exception {
		return proxy.generateActorFromProps(props);
	}

	@Override
	@WebMethod
	@WebResult(name = "jobid", targetNamespace = "")
	@RequestWrapper(localName = "dispatchAsyncJob", targetNamespace = "http://dynoffws/", className = "ktrsem.ws.clientstub.generated.DispatchAsyncJob")
	@ResponseWrapper(localName = "dispatchAsyncJobResponse", targetNamespace = "http://dynoffws/", className = "ktrsem.ws.clientstub.generated.DispatchAsyncJobResponse")
	@Action(input = "http://dynoffws/WebServiceAccess/dispatchAsyncJobRequest", output = "http://dynoffws/WebServiceAccess/dispatchAsyncJobResponse", fault = @FaultAction(className = ServerFault_Exception.class, value = "http://dynoffws/WebServiceAccess/dispatchAsyncJob/Fault/ServerFault"))
	public String dispatchAsyncJob(
			@WebParam(name = "jobmessageasync", targetNamespace = "") JobMessageAsync jobmessageasync)
			throws ServerFault_Exception {
		return proxy.dispatchAsyncJob(jobmessageasync);
	}

	@Override
	@WebMethod
	@WebResult(name = "resultmessage", targetNamespace = "")
	@RequestWrapper(localName = "getAsyncJobresult", targetNamespace = "http://dynoffws/", className = "ktrsem.ws.clientstub.generated.GetAsyncJobresult")
	@ResponseWrapper(localName = "getAsyncJobresultResponse", targetNamespace = "http://dynoffws/", className = "ktrsem.ws.clientstub.generated.GetAsyncJobresultResponse")
	@Action(input = "http://dynoffws/WebServiceAccess/getAsyncJobresultRequest", output = "http://dynoffws/WebServiceAccess/getAsyncJobresultResponse", fault = @FaultAction(className = ServerFault_Exception.class, value = "http://dynoffws/WebServiceAccess/getAsyncJobresult/Fault/ServerFault"))
	public byte[] getAsyncJobresult(
			@WebParam(name = "jobid", targetNamespace = "") String jobid)
			throws ServerFault_Exception {
		return proxy.getAsyncJobresult(jobid);
	}

	@Override
	@WebMethod
	@WebResult(name = "preavailable", targetNamespace = "")
	@RequestWrapper(localName = "getPreAvailableProps", targetNamespace = "http://dynoffws/", className = "ktrsem.ws.clientstub.generated.GetPreAvailableProps")
	@ResponseWrapper(localName = "getPreAvailablePropsResponse", targetNamespace = "http://dynoffws/", className = "ktrsem.ws.clientstub.generated.GetPreAvailablePropsResponse")
	@Action(input = "http://dynoffws/WebServiceAccess/getPreAvailablePropsRequest", output = "http://dynoffws/WebServiceAccess/getPreAvailablePropsResponse")
	public List<PropsPreAvailableMessage> getPreAvailableProps() {
		return proxy.getPreAvailableProps();
	}

	@Override
	@WebMethod
	@WebResult(name = "actorid", targetNamespace = "")
	@RequestWrapper(localName = "generatePreAvailableActor", targetNamespace = "http://dynoffws/", className = "ktrsem.ws.clientstub.generated.GeneratePreAvailableActor")
	@ResponseWrapper(localName = "generatePreAvailableActorResponse", targetNamespace = "http://dynoffws/", className = "ktrsem.ws.clientstub.generated.GeneratePreAvailableActorResponse")
	@Action(input = "http://dynoffws/WebServiceAccess/generatePreAvailableActorRequest", output = "http://dynoffws/WebServiceAccess/generatePreAvailableActorResponse", fault = @FaultAction(className = ServerFault_Exception.class, value = "http://dynoffws/WebServiceAccess/generatePreAvailableActor/Fault/ServerFault"))
	public String generatePreAvailableActor(
			@WebParam(name = "propsid", targetNamespace = "") String propsid)
			throws ServerFault_Exception {
		return proxy.generatePreAvailableActor(propsid);
	}

	@Override
	@WebMethod
	@WebResult(name = "resultmessage", targetNamespace = "")
	@RequestWrapper(localName = "sendMessage", targetNamespace = "http://dynoffws/", className = "ktrsem.ws.clientstub.generated.SendMessage")
	@ResponseWrapper(localName = "sendMessageResponse", targetNamespace = "http://dynoffws/", className = "ktrsem.ws.clientstub.generated.SendMessageResponse")
	@Action(input = "http://dynoffws/WebServiceAccess/sendMessageRequest", output = "http://dynoffws/WebServiceAccess/sendMessageResponse", fault = @FaultAction(className = ServerFault_Exception.class, value = "http://dynoffws/WebServiceAccess/sendMessage/Fault/ServerFault"))
	public byte[] sendMessage(
			@WebParam(name = "jobmessage", targetNamespace = "") JobMessage jobmessage)
			throws ServerFault_Exception {
		byte[] tmp = proxy.sendMessage(jobmessage);
		return tmp;
	}

}
