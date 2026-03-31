/**
 * SSO_NON_SAP_EAILocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cjfw.wms.webservice.sso.webservice;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import cjfw.core.utility.ContextUtil;

public class SSO_NON_SAP_EAILocator extends org.apache.axis.client.Service implements SSO_NON_SAP_EAI {
	public static final Log LOG = LogFactory.getLog(SSO_NON_SAP_EAILocator.class);
    public SSO_NON_SAP_EAILocator() {
    	SSO_NON_SAP_EAIHttpSoap11Endpoint_address = ContextUtil.getProperty("cf.eai.iamUrl");

    }


    public SSO_NON_SAP_EAILocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public SSO_NON_SAP_EAILocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for SSO_NON_SAP_EAIHttpSoap11Endpoint
    private java.lang.String SSO_NON_SAP_EAIHttpSoap11Endpoint_address = ContextUtil.getProperty("cf.eai.iamUrl");

    public java.lang.String getSSO_NON_SAP_EAIHttpSoap11EndpointAddress() {
        return SSO_NON_SAP_EAIHttpSoap11Endpoint_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String SSO_NON_SAP_EAIHttpSoap11EndpointWSDDServiceName = "SSO_NON_SAP_EAIHttpSoap11Endpoint";

    public java.lang.String getSSO_NON_SAP_EAIHttpSoap11EndpointWSDDServiceName() {
        return SSO_NON_SAP_EAIHttpSoap11EndpointWSDDServiceName;
    }

    public void setSSO_NON_SAP_EAIHttpSoap11EndpointWSDDServiceName(java.lang.String name) {
        SSO_NON_SAP_EAIHttpSoap11EndpointWSDDServiceName = name;
    }

    public SSO_NON_SAP_EAIPortType getSSO_NON_SAP_EAIHttpSoap11Endpoint() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(getSSO_NON_SAP_EAIHttpSoap11EndpointAddress());
        }
        catch (java.net.MalformedURLException e) {
        	LOG.error("Exception", e);
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getSSO_NON_SAP_EAIHttpSoap11Endpoint(endpoint);
    }

    public SSO_NON_SAP_EAIPortType getSSO_NON_SAP_EAIHttpSoap11Endpoint(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
        	SSO_NON_SAP_EAISoap11BindingStub _stub = new SSO_NON_SAP_EAISoap11BindingStub(portAddress, this);
            _stub.setPortName(getSSO_NON_SAP_EAIHttpSoap11EndpointWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
        	LOG.error("Exception", e);
            return null;
        }
    }

    public void setSSO_NON_SAP_EAIHttpSoap11EndpointEndpointAddress(java.lang.String address) {
        SSO_NON_SAP_EAIHttpSoap11Endpoint_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (SSO_NON_SAP_EAIPortType.class.isAssignableFrom(serviceEndpointInterface)) {
            	SSO_NON_SAP_EAISoap11BindingStub _stub = new SSO_NON_SAP_EAISoap11BindingStub(new java.net.URL(SSO_NON_SAP_EAIHttpSoap11Endpoint_address), this);
                _stub.setPortName(getSSO_NON_SAP_EAIHttpSoap11EndpointWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
        	LOG.error("Exception", t);
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
        if ("SSO_NON_SAP_EAIHttpSoap11Endpoint".equals(inputPortName)) {
            return getSSO_NON_SAP_EAIHttpSoap11Endpoint();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://webservice.sso.jwork", "SSO_NON_SAP_EAI");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://webservice.sso.jwork", "SSO_NON_SAP_EAIHttpSoap11Endpoint"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {

if ("SSO_NON_SAP_EAIHttpSoap11Endpoint".equals(portName)) {
            setSSO_NON_SAP_EAIHttpSoap11EndpointEndpointAddress(address);
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
