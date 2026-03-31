package cjfw.wms.webservice.elecTax;

/**
 * DT_SCM0500_SCM_response.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

public class DT_SCM0500_SCM_response  implements java.io.Serializable {
    private java.lang.String XSTAT;

    private java.lang.String XMSGS;

    private DT_SCM0500_SCM_responseOUT_HEAD[] OUT_HEAD;

    private DT_SCM0500_SCM_responseOUT_ITEM[] OUT_ITEM;

    private DT_SCM0500_SCM_responseOUT_EXT[] OUT_EXT;

    private DT_SCM0500_SCM_responseOUT_EXT_D[] OUT_EXT_D;

    public DT_SCM0500_SCM_response() {
    }

    public DT_SCM0500_SCM_response(
           java.lang.String XSTAT,
           java.lang.String XMSGS,
           DT_SCM0500_SCM_responseOUT_HEAD[] OUT_HEAD,
           DT_SCM0500_SCM_responseOUT_ITEM[] OUT_ITEM,
           DT_SCM0500_SCM_responseOUT_EXT[] OUT_EXT,
           DT_SCM0500_SCM_responseOUT_EXT_D[] OUT_EXT_D) {
           this.XSTAT = XSTAT;
           this.XMSGS = XMSGS;
           this.OUT_HEAD = OUT_HEAD;
           this.OUT_ITEM = OUT_ITEM;
           this.OUT_EXT = OUT_EXT;
           this.OUT_EXT_D = OUT_EXT_D;
    }


    /**
     * Gets the XSTAT value for this DT_SCM0500_SCM_response.
     * 
     * @return XSTAT
     */
    public java.lang.String getXSTAT() {
        return XSTAT;
    }


    /**
     * Sets the XSTAT value for this DT_SCM0500_SCM_response.
     * 
     * @param XSTAT
     */
    public void setXSTAT(java.lang.String XSTAT) {
        this.XSTAT = XSTAT;
    }


    /**
     * Gets the XMSGS value for this DT_SCM0500_SCM_response.
     * 
     * @return XMSGS
     */
    public java.lang.String getXMSGS() {
        return XMSGS;
    }


    /**
     * Sets the XMSGS value for this DT_SCM0500_SCM_response.
     * 
     * @param XMSGS
     */
    public void setXMSGS(java.lang.String XMSGS) {
        this.XMSGS = XMSGS;
    }


    /**
     * Gets the OUT_HEAD value for this DT_SCM0500_SCM_response.
     * 
     * @return OUT_HEAD
     */
    public DT_SCM0500_SCM_responseOUT_HEAD[] getOUT_HEAD() {
        return OUT_HEAD;
    }


    /**
     * Sets the OUT_HEAD value for this DT_SCM0500_SCM_response.
     * 
     * @param OUT_HEAD
     */
    public void setOUT_HEAD(DT_SCM0500_SCM_responseOUT_HEAD[] OUT_HEAD) {
        this.OUT_HEAD = OUT_HEAD;
    }

    public DT_SCM0500_SCM_responseOUT_HEAD getOUT_HEAD(int i) {
        return this.OUT_HEAD[i];
    }

    public void setOUT_HEAD(int i, DT_SCM0500_SCM_responseOUT_HEAD _value) {
        this.OUT_HEAD[i] = _value;
    }


    /**
     * Gets the OUT_ITEM value for this DT_SCM0500_SCM_response.
     * 
     * @return OUT_ITEM
     */
    public DT_SCM0500_SCM_responseOUT_ITEM[] getOUT_ITEM() {
        return OUT_ITEM;
    }


    /**
     * Sets the OUT_ITEM value for this DT_SCM0500_SCM_response.
     * 
     * @param OUT_ITEM
     */
    public void setOUT_ITEM(DT_SCM0500_SCM_responseOUT_ITEM[] OUT_ITEM) {
        this.OUT_ITEM = OUT_ITEM;
    }

    public DT_SCM0500_SCM_responseOUT_ITEM getOUT_ITEM(int i) {
        return this.OUT_ITEM[i];
    }

    public void setOUT_ITEM(int i, DT_SCM0500_SCM_responseOUT_ITEM _value) {
        this.OUT_ITEM[i] = _value;
    }


    /**
     * Gets the OUT_EXT value for this DT_SCM0500_SCM_response.
     * 
     * @return OUT_EXT
     */
    public DT_SCM0500_SCM_responseOUT_EXT[] getOUT_EXT() {
        return OUT_EXT;
    }


    /**
     * Sets the OUT_EXT value for this DT_SCM0500_SCM_response.
     * 
     * @param OUT_EXT
     */
    public void setOUT_EXT(DT_SCM0500_SCM_responseOUT_EXT[] OUT_EXT) {
        this.OUT_EXT = OUT_EXT;
    }

    public DT_SCM0500_SCM_responseOUT_EXT getOUT_EXT(int i) {
        return this.OUT_EXT[i];
    }

    public void setOUT_EXT(int i, DT_SCM0500_SCM_responseOUT_EXT _value) {
        this.OUT_EXT[i] = _value;
    }


    /**
     * Gets the OUT_EXT_D value for this DT_SCM0500_SCM_response.
     * 
     * @return OUT_EXT_D
     */
    public DT_SCM0500_SCM_responseOUT_EXT_D[] getOUT_EXT_D() {
        return OUT_EXT_D;
    }


    /**
     * Sets the OUT_EXT_D value for this DT_SCM0500_SCM_response.
     * 
     * @param OUT_EXT_D
     */
    public void setOUT_EXT_D(DT_SCM0500_SCM_responseOUT_EXT_D[] OUT_EXT_D) {
        this.OUT_EXT_D = OUT_EXT_D;
    }

    public DT_SCM0500_SCM_responseOUT_EXT_D getOUT_EXT_D(int i) {
        return this.OUT_EXT_D[i];
    }

    public void setOUT_EXT_D(int i, DT_SCM0500_SCM_responseOUT_EXT_D _value) {
        this.OUT_EXT_D[i] = _value;
    }

    private static java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DT_SCM0500_SCM_response)) return false;
        DT_SCM0500_SCM_response other = (DT_SCM0500_SCM_response) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc.equals(obj));
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
            ((this.OUT_HEAD==null && other.getOUT_HEAD()==null) || 
             (this.OUT_HEAD!=null &&
              java.util.Arrays.equals(this.OUT_HEAD, other.getOUT_HEAD()))) &&
            ((this.OUT_ITEM==null && other.getOUT_ITEM()==null) || 
             (this.OUT_ITEM!=null &&
              java.util.Arrays.equals(this.OUT_ITEM, other.getOUT_ITEM()))) &&
            ((this.OUT_EXT==null && other.getOUT_EXT()==null) || 
             (this.OUT_EXT!=null &&
              java.util.Arrays.equals(this.OUT_EXT, other.getOUT_EXT()))) &&
            ((this.OUT_EXT_D==null && other.getOUT_EXT_D()==null) || 
             (this.OUT_EXT_D!=null &&
              java.util.Arrays.equals(this.OUT_EXT_D, other.getOUT_EXT_D())));
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
        if (getOUT_HEAD() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getOUT_HEAD());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getOUT_HEAD(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getOUT_ITEM() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getOUT_ITEM());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getOUT_ITEM(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getOUT_EXT() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getOUT_EXT());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getOUT_EXT(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getOUT_EXT_D() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getOUT_EXT_D());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getOUT_EXT_D(), i);
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
        new org.apache.axis.description.TypeDesc(DT_SCM0500_SCM_response.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn://scm.cjfreshway.co.kr/FI", "DT_SCM0500_SCM_response"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("XSTAT");
        elemField.setXmlName(new javax.xml.namespace.QName("", "XSTAT"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("XMSGS");
        elemField.setXmlName(new javax.xml.namespace.QName("", "XMSGS"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("OUT_HEAD");
        elemField.setXmlName(new javax.xml.namespace.QName("", "OUT_HEAD"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn://scm.cjfreshway.co.kr/FI", ">DT_SCM0500_SCM_response>OUT_HEAD"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("OUT_ITEM");
        elemField.setXmlName(new javax.xml.namespace.QName("", "OUT_ITEM"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn://scm.cjfreshway.co.kr/FI", ">DT_SCM0500_SCM_response>OUT_ITEM"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("OUT_EXT");
        elemField.setXmlName(new javax.xml.namespace.QName("", "OUT_EXT"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn://scm.cjfreshway.co.kr/FI", ">DT_SCM0500_SCM_response>OUT_EXT"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("OUT_EXT_D");
        elemField.setXmlName(new javax.xml.namespace.QName("", "OUT_EXT_D"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn://scm.cjfreshway.co.kr/FI", ">DT_SCM0500_SCM_response>OUT_EXT_D"));
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
