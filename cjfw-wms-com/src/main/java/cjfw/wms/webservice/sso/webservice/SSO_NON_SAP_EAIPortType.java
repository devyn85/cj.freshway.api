/**
 * SSO_NON_SAP_EAIPortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cjfw.wms.webservice.sso.webservice;

import cjfw.wms.webservice.sso.webservice.xsd.SSO_NON_SAP_REQUEST;
import cjfw.wms.webservice.sso.webservice.xsd.SSO_NON_SAP_RESPONSE;

public interface SSO_NON_SAP_EAIPortType extends java.rmi.Remote {
    public SSO_NON_SAP_RESPONSE GET_SSO_TICKET(SSO_NON_SAP_REQUEST NON_SAP_REQ) throws java.rmi.RemoteException;
}
