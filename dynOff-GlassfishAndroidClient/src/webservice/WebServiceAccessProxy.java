package webservice;

public class WebServiceAccessProxy implements webservice.WebServiceAccess {
  private String _endpoint = null;
  private webservice.WebServiceAccess webServiceAccess = null;
  
  public WebServiceAccessProxy() {
    _initWebServiceAccessProxy();
  }
  
  public WebServiceAccessProxy(String endpoint) {
    _endpoint = endpoint;
    _initWebServiceAccessProxy();
  }
  
  private void _initWebServiceAccessProxy() {
    try {
      webServiceAccess = (new webservice.WebServiceAccessServiceLocator()).getWebServiceAccessPort();
      if (webServiceAccess != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)webServiceAccess)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)webServiceAccess)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (webServiceAccess != null)
      ((javax.xml.rpc.Stub)webServiceAccess)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public webservice.WebServiceAccess getWebServiceAccess() {
    if (webServiceAccess == null)
      _initWebServiceAccessProxy();
    return webServiceAccess;
  }
  
  public java.lang.String generateActorFromProps(byte[] props) throws java.rmi.RemoteException, webservice.ServerFault{
    if (webServiceAccess == null)
      _initWebServiceAccessProxy();
    return webServiceAccess.generateActorFromProps(props);
  }
  
  public byte[] sendMessage(webservice.JobMessage jobmessage) throws java.rmi.RemoteException, webservice.ServerFault{
    if (webServiceAccess == null)
      _initWebServiceAccessProxy();
    return webServiceAccess.sendMessage(jobmessage);
  }
  
  public java.lang.String dispatchAsyncJob(webservice.JobMessageAsync jobmessageasync) throws java.rmi.RemoteException, webservice.ServerFault{
    if (webServiceAccess == null)
      _initWebServiceAccessProxy();
    return webServiceAccess.dispatchAsyncJob(jobmessageasync);
  }
  
  public byte[] getAsyncJobresult(java.lang.String jobid) throws java.rmi.RemoteException, webservice.ServerFault{
    if (webServiceAccess == null)
      _initWebServiceAccessProxy();
    return webServiceAccess.getAsyncJobresult(jobid);
  }
  
  public webservice.PropsPreAvailableMessage[] getPreAvailableProps() throws java.rmi.RemoteException{
    if (webServiceAccess == null)
      _initWebServiceAccessProxy();
    return webServiceAccess.getPreAvailableProps();
  }
  
  public java.lang.String generatePreAvailableActor(java.lang.String propsid) throws java.rmi.RemoteException, webservice.ServerFault{
    if (webServiceAccess == null)
      _initWebServiceAccessProxy();
    return webServiceAccess.generatePreAvailableActor(propsid);
  }
  
  
}