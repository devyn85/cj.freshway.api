/**
 * SSO_NON_SAP_RESPONSE.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cjfw.wms.webservice.sso.webservice.xsd;

public class SSO_NON_SAP_RESPONSE  implements java.io.Serializable {
    private java.lang.String INT_SVC_NO;

    private java.lang.String SSO_TICKET;

    private java.lang.String XMSGS;

    private java.lang.String XSTAT;

    public SSO_NON_SAP_RESPONSE() {
    }

    public SSO_NON_SAP_RESPONSE(
           java.lang.String INT_SVC_NO,
           java.lang.String SSO_TICKET,
           java.lang.String XMSGS,
           java.lang.String XSTAT) {
           this.INT_SVC_NO = INT_SVC_NO;
           this.SSO_TICKET = SSO_TICKET;
           this.XMSGS = XMSGS;
           this.XSTAT = XSTAT;
    }


    /**
     * Gets the INT_SVC_NO value for this SSO_NON_SAP_RESPONSE.
     *
     * @return INT_SVC_NO
     */
    public java.lang.String getINT_SVC_NO() {
        return INT_SVC_NO;
    }


    /**
     * Sets the INT_SVC_NO value for this SSO_NON_SAP_RESPONSE.
     *
     * @param INT_SVC_NO
     */
    public void setINT_SVC_NO(java.lang.String INT_SVC_NO) {
        this.INT_SVC_NO = INT_SVC_NO;
    }


    /**
     * Gets the SSO_TICKET value for this SSO_NON_SAP_RESPONSE.
     *
     * @return SSO_TICKET
     */
    public java.lang.String getSSO_TICKET() {
        return SSO_TICKET;
    }


    /**
     * Sets the SSO_TICKET value for this SSO_NON_SAP_RESPONSE.
     *
     * @param SSO_TICKET
     */
    public void setSSO_TICKET(java.lang.String SSO_TICKET) {
        this.SSO_TICKET = SSO_TICKET;
    }


    /**
     * Gets the XMSGS value for this SSO_NON_SAP_RESPONSE.
     *
     * @return XMSGS
     */
    public java.lang.String getXMSGS() {
        return XMSGS;
    }


    /**
     * Sets the XMSGS value for this SSO_NON_SAP_RESPONSE.
     *
     * @param XMSGS
     */
    public void setXMSGS(java.lang.String XMSGS) {
        this.XMSGS = XMSGS;
    }


    /**
     * Gets the XSTAT value for this SSO_NON_SAP_RESPONSE.
     *
     * @return XSTAT
     */
    public java.lang.String getXSTAT() {
        return XSTAT;
    }


    /**
     * Sets the XSTAT value for this SSO_NON_SAP_RESPONSE.
     *
     * @param XSTAT
     */
    public void setXSTAT(java.lang.String XSTAT) {
        this.XSTAT = XSTAT;
    }

    private static java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SSO_NON_SAP_RESPONSE)) return false;
        SSO_NON_SAP_RESPONSE other = (SSO_NON_SAP_RESPONSE) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            //return (__equalsCalc == obj);
            return (__equalsCalc.equals(obj));
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true &&
            ((this.INT_SVC_NO==null && other.getINT_SVC_NO()==null) ||
             (this.INT_SVC_NO!=null &&
              this.INT_SVC_NO.equals(other.getINT_SVC_NO()))) &&
            ((this.SSO_TICKET==null && other.getSSO_TICKET()==null) ||
             (this.SSO_TICKET!=null &&
              this.SSO_TICKET.equals(other.getSSO_TICKET()))) &&
            ((this.XMSGS==null && other.getXMSGS()==null) ||
             (this.XMSGS!=null &&
              this.XMSGS.equals(other.getXMSGS()))) &&
            ((this.XSTAT==null && other.getXSTAT()==null) ||
             (this.XSTAT!=null &&
              this.XSTAT.equals(other.getXSTAT())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getINT_SVC_NO() != null) {
            _hashCode += getINT_SVC_NO().hashCode();
        }
        if (getSSO_TICKET() != null) {
            _hashCode += getSSO_TICKET().hashCode();
        }
        if (getXMSGS() != null) {
            _hashCode += getXMSGS().hashCode();
        }
        if (getXSTAT() != null) {
            _hashCode += getXSTAT().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SSO_NON_SAP_RESPONSE.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://webservice.sso.jwork/xsd", "SSO_NON_SAP_RESPONSE"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("INT_SVC_NO");
        elemField.setXmlName(new javax.xml.namespace.QName("http://webservice.sso.jwork/xsd", "INT_SVC_NO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("SSO_TICKET");
        elemField.setXmlName(new javax.xml.namespace.QName("http://webservice.sso.jwork/xsd", "SSO_TICKET"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("XMSGS");
        elemField.setXmlName(new javax.xml.namespace.QName("http://webservice.sso.jwork/xsd", "XMSGS"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("XSTAT");
        elemField.setXmlName(new javax.xml.namespace.QName("http://webservice.sso.jwork/xsd", "XSTAT"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType,
           java.lang.Class _javaType,
           javax.xml.namespace.QName _xmlType) {
        return
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType,
           java.lang.Class _javaType,
           javax.xml.namespace.QName _xmlType) {
        return
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
