package cjfw.wms.webservice.sms;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class SI_SHR0130_EAI_SOProxy implements SI_SHR0130_EAI_SO {
	public static final Log LOG = LogFactory.getLog(SI_SHR0130_EAI_SOProxy.class);
  private String _endpoint = null;
  private SI_SHR0130_EAI_SO sI_SHR0130_EAI_SO = null;
  
  public SI_SHR0130_EAI_SOProxy() {
    _initSI_SHR0130_EAI_SOProxy();
  }
  
  public SI_SHR0130_EAI_SOProxy(String endpoint) {
    _endpoint = endpoint;
    _initSI_SHR0130_EAI_SOProxy();
  }
  
  private void _initSI_SHR0130_EAI_SOProxy() {
    try {
      sI_SHR0130_EAI_SO = (new SI_SHR0130_EAI_SOServiceLocator()).getHTTP_Port();
      if (sI_SHR0130_EAI_SO != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)sI_SHR0130_EAI_SO)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)sI_SHR0130_EAI_SO)._getProperty("javax.xml.rpc.service.endpoint.address");
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
    if (sI_SHR0130_EAI_SO != null)
      ((javax.xml.rpc.Stub)sI_SHR0130_EAI_SO)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public SI_SHR0130_EAI_SO getSI_SHR0130_EAI_SO() {
    if (sI_SHR0130_EAI_SO == null)
      _initSI_SHR0130_EAI_SOProxy();
    return sI_SHR0130_EAI_SO;
  }
  
  public DT_SHR0130_EAI_response si_shr0130_eai_so(DT_SHR0130_EAI MT_SHR0130_EAI) throws java.rmi.RemoteException{
    if (sI_SHR0130_EAI_SO == null)
      _initSI_SHR0130_EAI_SOProxy();
    return sI_SHR0130_EAI_SO.si_shr0130_eai_so(MT_SHR0130_EAI);
  }
  
  
}