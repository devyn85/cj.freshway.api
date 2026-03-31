package cjfw.batch.exdcSkuAmount.soap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class SI_MM0090_SCM_SOProxy implements SI_MM0090_SCM_SO {
  public static final Log LOG = LogFactory.getLog(SI_MM0090_SCM_SOProxy.class);
  private String _endpoint = null;
  private SI_MM0090_SCM_SO sI_MM0090_SCM_SO = null;
  
  public SI_MM0090_SCM_SOProxy() {
    _initSI_MM0090_SCM_SOProxy();
  }
  
  public SI_MM0090_SCM_SOProxy(String endpoint) {
    _endpoint = endpoint;
    _initSI_MM0090_SCM_SOProxy();
  }
  
  private void _initSI_MM0090_SCM_SOProxy() {
    try {
      sI_MM0090_SCM_SO = (new SI_MM0090_SCM_SOServiceLocator()).getHTTP_Port();
      if (sI_MM0090_SCM_SO != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)sI_MM0090_SCM_SO)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)sI_MM0090_SCM_SO)._getProperty("javax.xml.rpc.service.endpoint.address");
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
    if (sI_MM0090_SCM_SO != null)
      ((javax.xml.rpc.Stub)sI_MM0090_SCM_SO)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public SI_MM0090_SCM_SO getSI_MM0090_SCM_SO() {
    if (sI_MM0090_SCM_SO == null)
      _initSI_MM0090_SCM_SOProxy();
    return sI_MM0090_SCM_SO;
  }
  
  public DT_MM0090_SCM_responseIF_ST_STOCKAMT_RET[] si_mm0090_scm_so(DT_MM0090_SCM MT_MM0090_SCM) throws java.rmi.RemoteException{
    if (sI_MM0090_SCM_SO == null)
      _initSI_MM0090_SCM_SOProxy();
    return sI_MM0090_SCM_SO.si_mm0090_scm_so(MT_MM0090_SCM);
  }
  
  
}