package cjfw.wms.webservice.sendOutOrder;

public class SI_COM1810_FS_SOProxy implements cjfw.wms.webservice.sendOutOrder.SI_COM1810_FS_SO {
  private String _endpoint = null;
  private cjfw.wms.webservice.sendOutOrder.SI_COM1810_FS_SO sI_COM1810_FS_SO = null;
  
  public SI_COM1810_FS_SOProxy() {
    _initSI_COM1810_FS_SOProxy();
  }
  
  public SI_COM1810_FS_SOProxy(String endpoint) {
    _endpoint = endpoint;
    _initSI_COM1810_FS_SOProxy();
  }
  
  private void _initSI_COM1810_FS_SOProxy() {
    try {
      sI_COM1810_FS_SO = (new cjfw.wms.webservice.sendOutOrder.SI_COM1810_FS_SOServiceLocator()).getHTTP_Port();
      if (sI_COM1810_FS_SO != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)sI_COM1810_FS_SO)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)sI_COM1810_FS_SO)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (sI_COM1810_FS_SO != null)
      ((javax.xml.rpc.Stub)sI_COM1810_FS_SO)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public cjfw.wms.webservice.sendOutOrder.SI_COM1810_FS_SO getSI_COM1810_FS_SO() {
    if (sI_COM1810_FS_SO == null)
      _initSI_COM1810_FS_SOProxy();
    return sI_COM1810_FS_SO;
  }
  
  public cjfw.wms.webservice.sendOutOrder.DT_COM1810_FS_response SI_COM1810_FS_SO(cjfw.wms.webservice.sendOutOrder.DT_COM1810_FS MT_COM1810_FS) throws java.rmi.RemoteException{
    if (sI_COM1810_FS_SO == null)
      _initSI_COM1810_FS_SOProxy();
    return sI_COM1810_FS_SO.SI_COM1810_FS_SO(MT_COM1810_FS);
  }
  
  
}