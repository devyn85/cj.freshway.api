package cjfw.wms.webservice.exDCMonthlyFee;

/**
 * SI_SCM0470_SCM_SOService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

public interface SI_SCM0470_SCM_SOService extends javax.xml.rpc.Service {
    public java.lang.String getHTTPS_PortAddress();

    public SI_SCM0470_SCM_SO getHTTPS_Port() throws javax.xml.rpc.ServiceException;

    public SI_SCM0470_SCM_SO getHTTPS_Port(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
    public java.lang.String getHTTP_PortAddress();

    public SI_SCM0470_SCM_SO getHTTP_Port() throws javax.xml.rpc.ServiceException;

    public SI_SCM0470_SCM_SO getHTTP_Port(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
