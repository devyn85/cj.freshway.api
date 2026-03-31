package cjfw.wms.webservice.exDCExpenseStatus;

/**
 * DT_SCM0520_SCM_response.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

public class DT_SCM0520_SCM_response  implements java.io.Serializable {
    private java.lang.String XSTAT;

    private java.lang.String XMSGS;

    private DT_SCM0520_SCM_responseT_MASTER[] t_MASTER;

    private DT_SCM0520_SCM_responseT_DETAIL[] t_DETAIL;

    public DT_SCM0520_SCM_response() {
    }

    public DT_SCM0520_SCM_response(
           java.lang.String XSTAT,
           java.lang.String XMSGS,
           DT_SCM0520_SCM_responseT_MASTER[] t_MASTER,
           DT_SCM0520_SCM_responseT_DETAIL[] t_DETAIL) {
           this.XSTAT = XSTAT;
           this.XMSGS = XMSGS;
           this.t_MASTER = t_MASTER;
           this.t_DETAIL = t_DETAIL;
    }


    /**
     * Gets the XSTAT value for this DT_SCM0520_SCM_response.
     * 
     * @return XSTAT
     */
    public java.lang.String getXSTAT() {
        return XSTAT;
    }


    /**
     * Sets the XSTAT value for this DT_SCM0520_SCM_response.
     * 
     * @param XSTAT
     */
    public void setXSTAT(java.lang.String XSTAT) {
        this.XSTAT = XSTAT;
    }


    /**
     * Gets the XMSGS value for this DT_SCM0520_SCM_response.
     * 
     * @return XMSGS
     */
    public java.lang.String getXMSGS() {
        return XMSGS;
    }


    /**
     * Sets the XMSGS value for this DT_SCM0520_SCM_response.
     * 
     * @param XMSGS
     */
    public void setXMSGS(java.lang.String XMSGS) {
        this.XMSGS = XMSGS;
    }


    /**
     * Gets the t_MASTER value for this DT_SCM0520_SCM_response.
     * 
     * @return t_MASTER
     */
    public DT_SCM0520_SCM_responseT_MASTER[] getT_MASTER() {
        return t_MASTER;
    }


    /**
     * Sets the t_MASTER value for this DT_SCM0520_SCM_response.
     * 
     * @param t_MASTER
     */
    public void setT_MASTER(DT_SCM0520_SCM_responseT_MASTER[] t_MASTER) {
        this.t_MASTER = t_MASTER;
    }

    public DT_SCM0520_SCM_responseT_MASTER getT_MASTER(int i) {
        return this.t_MASTER[i];
    }

    public void setT_MASTER(int i, DT_SCM0520_SCM_responseT_MASTER _value) {
        this.t_MASTER[i] = _value;
    }


    /**
     * Gets the t_DETAIL value for this DT_SCM0520_SCM_response.
     * 
     * @return t_DETAIL
     */
    public DT_SCM0520_SCM_responseT_DETAIL[] getT_DETAIL() {
        return t_DETAIL;
    }


    /**
     * Sets the t_DETAIL value for this DT_SCM0520_SCM_response.
     * 
     * @param t_DETAIL
     */
    public void setT_DETAIL(DT_SCM0520_SCM_responseT_DETAIL[] t_DETAIL) {
        this.t_DETAIL = t_DETAIL;
    }

    public DT_SCM0520_SCM_responseT_DETAIL getT_DETAIL(int i) {
        return this.t_DETAIL[i];
    }

    public void setT_DETAIL(int i, DT_SCM0520_SCM_responseT_DETAIL _value) {
        this.t_DETAIL[i] = _value;
    }

    private static java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DT_SCM0520_SCM_response)) return false;
        DT_SCM0520_SCM_response other = (DT_SCM0520_SCM_response) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.XSTAT==null && other.getXSTAT()==null) || 
             (this.XSTAT!=null &&
              this.XSTAT.equals(other.getXSTAT()))) &&
            ((this.XMSGS==null && other.getXMSGS()==null) || 
             (this.XMSGS!=null &&
              this.XMSGS.equals(other.getXMSGS()))) &&
            ((this.t_MASTER==null && other.getT_MASTER()==null) || 
             (this.t_MASTER!=null &&
              java.util.Arrays.equals(this.t_MASTER, other.getT_MASTER()))) &&
            ((this.t_DETAIL==null && other.getT_DETAIL()==null) || 
             (this.t_DETAIL!=null &&
              java.util.Arrays.equals(this.t_DETAIL, other.getT_DETAIL())));
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
        if (getXSTAT() != null) {
            _hashCode += getXSTAT().hashCode();
        }
        if (getXMSGS() != null) {
            _hashCode += getXMSGS().hashCode();
        }
        if (getT_MASTER() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getT_MASTER());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getT_MASTER(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getT_DETAIL() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getT_DETAIL());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getT_DETAIL(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DT_SCM0520_SCM_response.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn://scm.cjfreshway.co.kr/SRMIE", "DT_SCM0520_SCM_response"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("XSTAT");
        elemField.setXmlName(new javax.xml.namespace.QName("", "XSTAT"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("XMSGS");
        elemField.setXmlName(new javax.xml.namespace.QName("", "XMSGS"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("t_MASTER");
        elemField.setXmlName(new javax.xml.namespace.QName("", "T_MASTER"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn://scm.cjfreshway.co.kr/SRMIE", ">DT_SCM0520_SCM_response>T_MASTER"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("t_DETAIL");
        elemField.setXmlName(new javax.xml.namespace.QName("", "T_DETAIL"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn://scm.cjfreshway.co.kr/SRMIE", ">DT_SCM0520_SCM_response>T_DETAIL"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
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
