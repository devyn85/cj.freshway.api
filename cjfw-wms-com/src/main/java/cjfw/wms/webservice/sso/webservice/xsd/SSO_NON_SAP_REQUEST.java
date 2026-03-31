/**
 * SSO_NON_SAP_REQUEST.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cjfw.wms.webservice.sso.webservice.xsd;

public class SSO_NON_SAP_REQUEST  implements java.io.Serializable {
    private java.lang.String INT_SVC_NO;

    private java.lang.String XDATS;

    private java.lang.String XROWS;

    private java.lang.String XSYS;

    private java.lang.String XTIMS;

    private java.lang.String ZSYST_FR;

    private java.lang.String ZSYST_TO;

    public SSO_NON_SAP_REQUEST() {
    }

    public SSO_NON_SAP_REQUEST(
           java.lang.String INT_SVC_NO,
           java.lang.String XDATS,
           java.lang.String XROWS,
           java.lang.String XSYS,
           java.lang.String XTIMS,
           java.lang.String ZSYST_FR,
           java.lang.String ZSYST_TO) {
           this.INT_SVC_NO = INT_SVC_NO;
           this.XDATS = XDATS;
           this.XROWS = XROWS;
           this.XSYS = XSYS;
           this.XTIMS = XTIMS;
           this.ZSYST_FR = ZSYST_FR;
           this.ZSYST_TO = ZSYST_TO;
    }


    /**
     * Gets the INT_SVC_NO value for this SSO_NON_SAP_REQUEST.
     *
     * @return INT_SVC_NO
     */
    public java.lang.String getINT_SVC_NO() {
        return INT_SVC_NO;
    }


    /**
     * Sets the INT_SVC_NO value for this SSO_NON_SAP_REQUEST.
     *
     * @param INT_SVC_NO
     */
    public void setINT_SVC_NO(java.lang.String INT_SVC_NO) {
        this.INT_SVC_NO = INT_SVC_NO;
    }


    /**
     * Gets the XDATS value for this SSO_NON_SAP_REQUEST.
     *
     * @return XDATS
     */
    public java.lang.String getXDATS() {
        return XDATS;
    }


    /**
     * Sets the XDATS value for this SSO_NON_SAP_REQUEST.
     *
     * @param XDATS
     */
    public void setXDATS(java.lang.String XDATS) {
        this.XDATS = XDATS;
    }


    /**
     * Gets the XROWS value for this SSO_NON_SAP_REQUEST.
     *
     * @return XROWS
     */
    public java.lang.String getXROWS() {
        return XROWS;
    }


    /**
     * Sets the XROWS value for this SSO_NON_SAP_REQUEST.
     *
     * @param XROWS
     */
    public void setXROWS(java.lang.String XROWS) {
        this.XROWS = XROWS;
    }


    /**
     * Gets the XSYS value for this SSO_NON_SAP_REQUEST.
     *
     * @return XSYS
     */
    public java.lang.String getXSYS() {
        return XSYS;
    }


    /**
     * Sets the XSYS value for this SSO_NON_SAP_REQUEST.
     *
     * @param XSYS
     */
    public void setXSYS(java.lang.String XSYS) {
        this.XSYS = XSYS;
    }


    /**
     * Gets the XTIMS value for this SSO_NON_SAP_REQUEST.
     *
     * @return XTIMS
     */
    public java.lang.String getXTIMS() {
        return XTIMS;
    }


    /**
     * Sets the XTIMS value for this SSO_NON_SAP_REQUEST.
     *
     * @param XTIMS
     */
    public void setXTIMS(java.lang.String XTIMS) {
        this.XTIMS = XTIMS;
    }


    /**
     * Gets the ZSYST_FR value for this SSO_NON_SAP_REQUEST.
     *
     * @return ZSYST_FR
     */
    public java.lang.String getZSYST_FR() {
        return ZSYST_FR;
    }


    /**
     * Sets the ZSYST_FR value for this SSO_NON_SAP_REQUEST.
     *
     * @param ZSYST_FR
     */
    public void setZSYST_FR(java.lang.String ZSYST_FR) {
        this.ZSYST_FR = ZSYST_FR;
    }


    /**
     * Gets the ZSYST_TO value for this SSO_NON_SAP_REQUEST.
     *
     * @return ZSYST_TO
     */
    public java.lang.String getZSYST_TO() {
        return ZSYST_TO;
    }


    /**
     * Sets the ZSYST_TO value for this SSO_NON_SAP_REQUEST.
     *
     * @param ZSYST_TO
     */
    public void setZSYST_TO(java.lang.String ZSYST_TO) {
        this.ZSYST_TO = ZSYST_TO;
    }

    private static java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SSO_NON_SAP_REQUEST)) return false;
        SSO_NON_SAP_REQUEST other = (SSO_NON_SAP_REQUEST) obj;
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
            ((this.XDATS==null && other.getXDATS()==null) ||
             (this.XDATS!=null &&
              this.XDATS.equals(other.getXDATS()))) &&
            ((this.XROWS==null && other.getXROWS()==null) ||
             (this.XROWS!=null &&
              this.XROWS.equals(other.getXROWS()))) &&
            ((this.XSYS==null && other.getXSYS()==null) ||
             (this.XSYS!=null &&
              this.XSYS.equals(other.getXSYS()))) &&
            ((this.XTIMS==null && other.getXTIMS()==null) ||
             (this.XTIMS!=null &&
              this.XTIMS.equals(other.getXTIMS()))) &&
            ((this.ZSYST_FR==null && other.getZSYST_FR()==null) ||
             (this.ZSYST_FR!=null &&
              this.ZSYST_FR.equals(other.getZSYST_FR()))) &&
            ((this.ZSYST_TO==null && other.getZSYST_TO()==null) ||
             (this.ZSYST_TO!=null &&
              this.ZSYST_TO.equals(other.getZSYST_TO())));
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
        if (getXDATS() != null) {
            _hashCode += getXDATS().hashCode();
        }
        if (getXROWS() != null) {
            _hashCode += getXROWS().hashCode();
        }
        if (getXSYS() != null) {
            _hashCode += getXSYS().hashCode();
        }
        if (getXTIMS() != null) {
            _hashCode += getXTIMS().hashCode();
        }
        if (getZSYST_FR() != null) {
            _hashCode += getZSYST_FR().hashCode();
        }
        if (getZSYST_TO() != null) {
            _hashCode += getZSYST_TO().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SSO_NON_SAP_REQUEST.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://webservice.sso.jwork/xsd", "SSO_NON_SAP_REQUEST"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("INT_SVC_NO");
        elemField.setXmlName(new javax.xml.namespace.QName("http://webservice.sso.jwork/xsd", "INT_SVC_NO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("XDATS");
        elemField.setXmlName(new javax.xml.namespace.QName("http://webservice.sso.jwork/xsd", "XDATS"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("XROWS");
        elemField.setXmlName(new javax.xml.namespace.QName("http://webservice.sso.jwork/xsd", "XROWS"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("XSYS");
        elemField.setXmlName(new javax.xml.namespace.QName("http://webservice.sso.jwork/xsd", "XSYS"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("XTIMS");
        elemField.setXmlName(new javax.xml.namespace.QName("http://webservice.sso.jwork/xsd", "XTIMS"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ZSYST_FR");
        elemField.setXmlName(new javax.xml.namespace.QName("http://webservice.sso.jwork/xsd", "ZSYST_FR"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ZSYST_TO");
        elemField.setXmlName(new javax.xml.namespace.QName("http://webservice.sso.jwork/xsd", "ZSYST_TO"));
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
