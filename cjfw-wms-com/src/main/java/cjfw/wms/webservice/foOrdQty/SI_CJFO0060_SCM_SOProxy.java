package cjfw.wms.webservice.foOrdQty;

public class SI_CJFO0060_SCM_SOProxy implements cjfw.wms.webservice.foOrdQty.SI_CJFO0060_SCM_SO {
  private String _endpoint = null;
  private cjfw.wms.webservice.foOrdQty.SI_CJFO0060_SCM_SO sI_CJFO0060_SCM_SO = null;
  
  public SI_CJFO0060_SCM_SOProxy() {
    _initSI_CJFO0060_SCM_SOProxy();
  }
  
  public SI_CJFO0060_SCM_SOProxy(String endpoint) {
    _endpoint = endpoint;
    _initSI_CJFO0060_SCM_SOProxy();
  }
  
  private void _initSI_CJFO0060_SCM_SOProxy() {
    try {
      sI_CJFO0060_SCM_SO = (new cjfw.wms.webservice.foOrdQty.SI_CJFO0060_SCM_SOServiceLocator()).getHTTP_Port();
      if (sI_CJFO0060_SCM_SO != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)sI_CJFO0060_SCM_SO)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)sI_CJFO0060_SCM_SO)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (sI_CJFO0060_SCM_SO != null)
      ((javax.xml.rpc.Stub)sI_CJFO0060_SCM_SO)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public cjfw.wms.webservice.foOrdQty.SI_CJFO0060_SCM_SO getSI_CJFO0060_SCM_SO() {
    if (sI_CJFO0060_SCM_SO == null)
      _initSI_CJFO0060_SCM_SOProxy();
    return sI_CJFO0060_SCM_SO;
  }
  
  public cjfw.wms.webservice.foOrdQty.DT_CJFO0060_SCM_response SI_CJFO0060_SCM_SO(cjfw.wms.webservice.foOrdQty.DT_CJFO0060_SCMIT_ORDQTY[] MT_CJFO0060_SCM) throws java.rmi.RemoteException{
    if (sI_CJFO0060_SCM_SO == null)
      _initSI_CJFO0060_SCM_SOProxy();
    return sI_CJFO0060_SCM_SO.SI_CJFO0060_SCM_SO(MT_CJFO0060_SCM);
  }
  
  
}