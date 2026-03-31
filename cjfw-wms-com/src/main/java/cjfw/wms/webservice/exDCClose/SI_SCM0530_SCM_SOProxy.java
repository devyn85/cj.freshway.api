package cjfw.wms.webservice.exDCClose;


public class SI_SCM0530_SCM_SOProxy implements SI_SCM0530_SCM_SO {
  private String _endpoint = null;
  private SI_SCM0530_SCM_SO sI_SCM0530_SCM_SO = null;
  
  public SI_SCM0530_SCM_SOProxy() {
    _initSI_SCM0530_SCM_SOProxy();
  }
  
  public SI_SCM0530_SCM_SOProxy(String endpoint) {
    _endpoint = endpoint;
    _initSI_SCM0530_SCM_SOProxy();
  }
  
  private void _initSI_SCM0530_SCM_SOProxy() {
    try {
      sI_SCM0530_SCM_SO = (new SI_SCM0530_SCM_SOServiceLocator()).getHTTP_Port();
      if (sI_SCM0530_SCM_SO != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)sI_SCM0530_SCM_SO)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)sI_SCM0530_SCM_SO)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (sI_SCM0530_SCM_SO != null)
      ((javax.xml.rpc.Stub)sI_SCM0530_SCM_SO)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public SI_SCM0530_SCM_SO getSI_SCM0530_SCM_SO() {
    if (sI_SCM0530_SCM_SO == null)
      _initSI_SCM0530_SCM_SOProxy();
    return sI_SCM0530_SCM_SO;
  }
  
  public DT_SCM0530_SCM_response sI_SCM0530_SCM_SO(DT_SCM0530_SCM MT_SCM0530_SCM) throws java.rmi.RemoteException{
    if (sI_SCM0530_SCM_SO == null)
      _initSI_SCM0530_SCM_SOProxy();
    return sI_SCM0530_SCM_SO.sI_SCM0530_SCM_SO(MT_SCM0530_SCM);
  }
  
  
}