package cjfw.wms.webservice.credit;

/**
 * DT_SCM0070_SCMIF_MS_CUSTAMT.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

public class DT_SCM0070_SCMIF_MS_CUSTAMT  implements java.io.Serializable {
    private java.lang.String GJAHR;

    private java.lang.String ZMONTH;

    private java.lang.String KUNNR;

    private java.lang.String DELIVERYDATE;

    public DT_SCM0070_SCMIF_MS_CUSTAMT() {
    }

    public DT_SCM0070_SCMIF_MS_CUSTAMT(
           java.lang.String GJAHR,
           java.lang.String ZMONTH,
           java.lang.String KUNNR,
           java.lang.String DELIVERYDATE) {
           this.GJAHR = GJAHR;
           this.ZMONTH = ZMONTH;
           this.KUNNR = KUNNR;
           this.DELIVERYDATE = DELIVERYDATE;
    }


    /**
     * Gets the GJAHR value for this DT_SCM0070_SCMIF_MS_CUSTAMT.
     * 
     * @return GJAHR
     */
    public java.lang.String getGJAHR() {
        return GJAHR;
    }


    /**
     * Sets the GJAHR value for this DT_SCM0070_SCMIF_MS_CUSTAMT.
     * 
     * @param GJAHR
     */
    public void setGJAHR(java.lang.String GJAHR) {
        this.GJAHR = GJAHR;
    }


    /**
     * Gets the ZMONTH value for this DT_SCM0070_SCMIF_MS_CUSTAMT.
     * 
     * @return ZMONTH
     */
    public java.lang.String getZMONTH() {
        return ZMONTH;
    }


    /**
     * Sets the ZMONTH value for this DT_SCM0070_SCMIF_MS_CUSTAMT.
     * 
     * @param ZMONTH
     */
    public void setZMONTH(java.lang.String ZMONTH) {
        this.ZMONTH = ZMONTH;
    }


    /**
     * Gets the KUNNR value for this DT_SCM0070_SCMIF_MS_CUSTAMT.
     * 
     * @return KUNNR
     */
    public java.lang.String getKUNNR() {
        return KUNNR;
    }


    /**
     * Sets the KUNNR value for this DT_SCM0070_SCMIF_MS_CUSTAMT.
     * 
     * @param KUNNR
     */
    public void setKUNNR(java.lang.String KUNNR) {
        this.KUNNR = KUNNR;
    }


    /**
     * Gets the DELIVERYDATE value for this DT_SCM0070_SCMIF_MS_CUSTAMT.
     * 
     * @return DELIVERYDATE
     */
    public java.lang.String getDELIVERYDATE() {
        return DELIVERYDATE;
    }


    /**
     * Sets the DELIVERYDATE value for this DT_SCM0070_SCMIF_MS_CUSTAMT.
     * 
     * @param DELIVERYDATE
     */
    public void setDELIVERYDATE(java.lang.String DELIVERYDATE) {
        this.DELIVERYDATE = DELIVERYDATE;
    }

    private static java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DT_SCM0070_SCMIF_MS_CUSTAMT)) return false;
        DT_SCM0070_SCMIF_MS_CUSTAMT other = (DT_SCM0070_SCMIF_MS_CUSTAMT) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc.equals(obj));
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.GJAHR==null && other.getGJAHR()==null) || 
             (this.GJAHR!=null &&
              this.GJAHR.equals(other.getGJAHR()))) &&
            ((this.ZMONTH==null && other.getZMONTH()==null) || 
             (this.ZMONTH!=null &&
              this.ZMONTH.equals(other.getZMONTH()))) &&
            ((this.KUNNR==null && other.getKUNNR()==null) || 
             (this.KUNNR!=null &&
              this.KUNNR.equals(other.getKUNNR()))) &&
            ((this.DELIVERYDATE==null && other.getDELIVERYDATE()==null) || 
             (this.DELIVERYDATE!=null &&
              this.DELIVERYDATE.equals(other.getDELIVERYDATE())));
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
        if (getGJAHR() != null) {
            _hashCode += getGJAHR().hashCode();
        }
        if (getZMONTH() != null) {
            _hashCode += getZMONTH().hashCode();
        }
        if (getKUNNR() != null) {
            _hashCode += getKUNNR().hashCode();
        }
        if (getDELIVERYDATE() != null) {
            _hashCode += getDELIVERYDATE().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DT_SCM0070_SCMIF_MS_CUSTAMT.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn://scm.cjfreshway.co.kr/SD", ">DT_SCM0070_SCM>IF_MS_CUSTAMT"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("GJAHR");
        elemField.setXmlName(new javax.xml.namespace.QName("", "GJAHR"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ZMONTH");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ZMONTH"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("KUNNR");
        elemField.setXmlName(new javax.xml.namespace.QName("", "KUNNR"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("DELIVERYDATE");
        elemField.setXmlName(new javax.xml.namespace.QName("", "DELIVERYDATE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
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
