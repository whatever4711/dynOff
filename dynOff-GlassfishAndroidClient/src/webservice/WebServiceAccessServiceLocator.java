/**
 * WebServiceAccessServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package webservice;

public class WebServiceAccessServiceLocator extends org.apache.axis.client.Service implements webservice.WebServiceAccessService {

    public WebServiceAccessServiceLocator() {
    }


    public WebServiceAccessServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public WebServiceAccessServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for WebServiceAccessPort
    private java.lang.String WebServiceAccessPort_address = "http://incrediblepc:8080/dynOff-GlassfishWeb/WebServiceAccessService";

    public java.lang.String getWebServiceAccessPortAddress() {
        return WebServiceAccessPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String WebServiceAccessPortWSDDServiceName = "WebServiceAccessPort";

    public java.lang.String getWebServiceAccessPortWSDDServiceName() {
        return WebServiceAccessPortWSDDServiceName;
    }

    public void setWebServiceAccessPortWSDDServiceName(java.lang.String name) {
        WebServiceAccessPortWSDDServiceName = name;
    }

    public webservice.WebServiceAccess getWebServiceAccessPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(WebServiceAccessPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getWebServiceAccessPort(endpoint);
    }

    public webservice.WebServiceAccess getWebServiceAccessPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            webservice.WebServiceAccessPortBindingStub _stub = new webservice.WebServiceAccessPortBindingStub(portAddress, this);
            _stub.setPortName(getWebServiceAccessPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setWebServiceAccessPortEndpointAddress(java.lang.String address) {
        WebServiceAccessPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (webservice.WebServiceAccess.class.isAssignableFrom(serviceEndpointInterface)) {
                webservice.WebServiceAccessPortBindingStub _stub = new webservice.WebServiceAccessPortBindingStub(new java.net.URL(WebServiceAccessPort_address), this);
                _stub.setPortName(getWebServiceAccessPortWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("WebServiceAccessPort".equals(inputPortName)) {
            return getWebServiceAccessPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://webservice/", "WebServiceAccessService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://webservice/", "WebServiceAccessPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("WebServiceAccessPort".equals(portName)) {
            setWebServiceAccessPortEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
