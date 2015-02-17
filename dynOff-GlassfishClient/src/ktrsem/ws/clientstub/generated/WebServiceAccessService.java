
package ktrsem.ws.clientstub.generated;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.4-b01
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "WebServiceAccessService", targetNamespace = "http://webservice/", wsdlLocation = "http://IncrediblePC:8080/dynOff-GlassfishWeb/WebServiceAccessService?wsdl")
public class WebServiceAccessService
    extends Service
{

    private final static URL WEBSERVICEACCESSSERVICE_WSDL_LOCATION;
    private final static WebServiceException WEBSERVICEACCESSSERVICE_EXCEPTION;
    private final static QName WEBSERVICEACCESSSERVICE_QNAME = new QName("http://webservice/", "WebServiceAccessService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://IncrediblePC:8080/dynOff-GlassfishWeb/WebServiceAccessService?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        WEBSERVICEACCESSSERVICE_WSDL_LOCATION = url;
        WEBSERVICEACCESSSERVICE_EXCEPTION = e;
    }

    public WebServiceAccessService() {
        super(__getWsdlLocation(), WEBSERVICEACCESSSERVICE_QNAME);
    }

    public WebServiceAccessService(WebServiceFeature... features) {
        super(__getWsdlLocation(), WEBSERVICEACCESSSERVICE_QNAME, features);
    }

    public WebServiceAccessService(URL wsdlLocation) {
        super(wsdlLocation, WEBSERVICEACCESSSERVICE_QNAME);
    }

    public WebServiceAccessService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, WEBSERVICEACCESSSERVICE_QNAME, features);
    }

    public WebServiceAccessService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public WebServiceAccessService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns WebServiceAccess
     */
    @WebEndpoint(name = "WebServiceAccessPort")
    public WebServiceAccess getWebServiceAccessPort() {
        return super.getPort(new QName("http://webservice/", "WebServiceAccessPort"), WebServiceAccess.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns WebServiceAccess
     */
    @WebEndpoint(name = "WebServiceAccessPort")
    public WebServiceAccess getWebServiceAccessPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://webservice/", "WebServiceAccessPort"), WebServiceAccess.class, features);
    }

    private static URL __getWsdlLocation() {
        if (WEBSERVICEACCESSSERVICE_EXCEPTION!= null) {
            throw WEBSERVICEACCESSSERVICE_EXCEPTION;
        }
        return WEBSERVICEACCESSSERVICE_WSDL_LOCATION;
    }

}