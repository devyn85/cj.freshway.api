package cjfw.wms.webservice.sso.webservice;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cjfw.wms.webservice.sso.webservice.xsd.SSO_NON_SAP_REQUEST;
import cjfw.wms.webservice.sso.webservice.xsd.SSO_NON_SAP_RESPONSE;


public class SSO_NON_SAP_EAIPortTypeProxy implements SSO_NON_SAP_EAIPortType {
  public static final Log LOG = LogFactory.getLog(SSO_NON_SAP_EAIPortTypeProxy.class);
  private String _endpoint = null;
  private SSO_NON_SAP_EAIPortType sSO_NON_SAP_EAIPortType = null;

  public SSO_NON_SAP_EAIPortTypeProxy() {
    _initSSO_NON_SAP_EAIPortTypeProxy();
  }

  public SSO_NON_SAP_EAIPortTypeProxy(String endpoint) {
    _endpoint = endpoint;
    _initSSO_NON_SAP_EAIPortTypeProxy();
  }

  private void _initSSO_NON_SAP_EAIPortTypeProxy() {
    try {
      sSO_NON_SAP_EAIPortType = (new SSO_NON_SAP_EAILocator()).getSSO_NON_SAP_EAIHttpSoap11Endpoint();
      if (sSO_NON_SAP_EAIPortType != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)sSO_NON_SAP_EAIPortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)sSO_NON_SAP_EAIPortType)._getProperty("javax.xml.rpc.service.endpoint.address");
      }

    }
    catch (javax.xml.rpc.ServiceException serviceException) {
    	LOG.error("Exception", serviceException);
    }
  }

  public String getEndpoint() {
    return _endpoint;
  }

  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (sSO_NON_SAP_EAIPortType != null)
      ((javax.xml.rpc.Stub)sSO_NON_SAP_EAIPortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);

  }

  public SSO_NON_SAP_EAIPortType getSSO_NON_SAP_EAIPortType() {
    if (sSO_NON_SAP_EAIPortType == null)
      _initSSO_NON_SAP_EAIPortTypeProxy();
    return sSO_NON_SAP_EAIPortType;
  }

  public SSO_NON_SAP_RESPONSE GET_SSO_TICKET(SSO_NON_SAP_REQUEST NON_SAP_REQ) throws java.rmi.RemoteException{
    if (sSO_NON_SAP_EAIPortType == null)
      _initSSO_NON_SAP_EAIPortTypeProxy();
    return sSO_NON_SAP_EAIPortType.GET_SSO_TICKET(NON_SAP_REQ);
  }


}