/**
 * WebServiceAccess.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package webservice;

public interface WebServiceAccess extends java.rmi.Remote {
    public java.lang.String generateActorFromProps(byte[] props) throws java.rmi.RemoteException, webservice.ServerFault;
    public byte[] sendMessage(webservice.JobMessage jobmessage) throws java.rmi.RemoteException, webservice.ServerFault;
    public java.lang.String dispatchAsyncJob(webservice.JobMessageAsync jobmessageasync) throws java.rmi.RemoteException, webservice.ServerFault;
    public byte[] getAsyncJobresult(java.lang.String jobid) throws java.rmi.RemoteException, webservice.ServerFault;
    public webservice.PropsPreAvailableMessage[] getPreAvailableProps() throws java.rmi.RemoteException;
    public java.lang.String generatePreAvailableActor(java.lang.String propsid) throws java.rmi.RemoteException, webservice.ServerFault;
}
