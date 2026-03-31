package cjfw.wms.webservice.exDCExpenseStatus;

/**
 * DT_SCM0520_SCMT_MASTER.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

public class DT_SCM0520_SCMT_MASTER  implements java.io.Serializable {
    private java.lang.String POKEY;

    private java.lang.String POLINE;

    private java.lang.String SKU;

    private java.lang.String CONVSERIALNO;

    private java.lang.String CUSTKEY;

    private java.lang.String STANDARD_DATE;

    public DT_SCM0520_SCMT_MASTER() {
    }

    public DT_SCM0520_SCMT_MASTER(
           java.lang.String POKEY,
           java.lang.String POLINE,
           java.lang.String SKU,
           java.lang.String CONVSERIALNO,
           java.lang.String CUSTKEY,
           java.lang.String STANDARD_DATE) {
           this.POKEY = POKEY;
           this.POLINE = POLINE;
           this.SKU = SKU;
           this.CONVSERIALNO = CONVSERIALNO;
           this.CUSTKEY = CUSTKEY;
           this.STANDARD_DATE = STANDARD_DATE;
    }


    /**
     * Gets the POKEY value for this DT_SCM0520_SCMT_MASTER.
     * 
     * @return POKEY
     */
    public java.lang.String getPOKEY() {
        return POKEY;
    }


    /**
     * Sets the POKEY value for this DT_SCM0520_SCMT_MASTER.
     * 
     * @param POKEY
     */
    public void setPOKEY(java.lang.String POKEY) {
        this.POKEY = POKEY;
    }


    /**
     * Gets the POLINE value for this DT_SCM0520_SCMT_MASTER.
     * 
     * @return POLINE
     */
    public java.lang.String getPOLINE() {
        return POLINE;
    }


    /**
     * Sets the POLINE value for this DT_SCM0520_SCMT_MASTER.
     * 
     * @param POLINE
     */
    public void setPOLINE(java.lang.String POLINE) {
        this.POLINE = POLINE;
    }


    /**
     * Gets the SKU value for this DT_SCM0520_SCMT_MASTER.
     * 
     * @return SKU
     */
    public java.lang.String getSKU() {
        return SKU;
    }


    /**
     * Sets the SKU value for this DT_SCM0520_SCMT_MASTER.
     * 
     * @param SKU
     */
    public void setSKU(java.lang.String SKU) {
        this.SKU = SKU;
    }


    /**
     * Gets the CONVSERIALNO value for this DT_SCM0520_SCMT_MASTER.
     * 
     * @return CONVSERIALNO
     */
    public java.lang.String getCONVSERIALNO() {
        return CONVSERIALNO;
    }


    /**
     * Sets the CONVSERIALNO value for this DT_SCM0520_SCMT_MASTER.
     * 
     * @param CONVSERIALNO
     */
    public void setCONVSERIALNO(java.lang.String CONVSERIALNO) {
        this.CONVSERIALNO = CONVSERIALNO;
    }


    /**
     * Gets the CUSTKEY value for this DT_SCM0520_SCMT_MASTER.
     * 
     * @return CUSTKEY
     */
    public java.lang.String getCUSTKEY() {
        return CUSTKEY;
    }


    /**
     * Sets the CUSTKEY value for this DT_SCM0520_SCMT_MASTER.
     * 
     * @param CUSTKEY
     */
    public void setCUSTKEY(java.lang.String CUSTKEY) {
        this.CUSTKEY = CUSTKEY;
    }


    /**
     * Gets the STANDARD_DATE value for this DT_SCM0520_SCMT_MASTER.
     * 
     * @return STANDARD_DATE
     */
    public java.lang.String getSTANDARD_DATE() {
        return STANDARD_DATE;
    }


    /**
     * Sets the STANDARD_DATE value for this DT_SCM0520_SCMT_MASTER.
     * 
     * @param STANDARD_DATE
     */
    public void setSTANDARD_DATE(java.lang.String STANDARD_DATE) {
        this.STANDARD_DATE = STANDARD_DATE;
    }

    private static java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DT_SCM0520_SCMT_MASTER)) return false;
        DT_SCM0520_SCMT_MASTER other = (DT_SCM0520_SCMT_MASTER) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.POKEY==null && other.getPOKEY()==null) || 
             (this.POKEY!=null &&
              this.POKEY.equals(other.getPOKEY()))) &&
            ((this.POLINE==null && other.getPOLINE()==null) || 
             (this.POLINE!=null &&
              this.POLINE.equals(other.getPOLINE()))) &&
            ((this.SKU==null && other.getSKU()==null) || 
             (this.SKU!=null &&
              this.SKU.equals(other.getSKU()))) &&
            ((this.CONVSERIALNO==null && other.getCONVSERIALNO()==null) || 
             (this.CONVSERIALNO!=null &&
              this.CONVSERIALNO.equals(other.getCONVSERIALNO()))) &&
            ((this.CUSTKEY==null && other.getCUSTKEY()==null) || 
             (this.CUSTKEY!=null &&
              this.CUSTKEY.equals(other.getCUSTKEY()))) &&
            ((this.STANDARD_DATE==null && other.getSTANDARD_DATE()==null) || 
             (this.STANDARD_DATE!=null &&
              this.STANDARD_DATE.equals(other.getSTANDARD_DATE())));
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
        if (getPOKEY() != null) {
            _hashCode += getPOKEY().hashCode();
        }
        if (getPOLINE() != null) {
            _hashCode += getPOLINE().hashCode();
        }
        if (getSKU() != null) {
            _hashCode += getSKU().hashCode();
        }
        if (getCONVSERIALNO() != null) {
            _hashCode += getCONVSERIALNO().hashCode();
        }
        if (getCUSTKEY() != null) {
            _hashCode += getCUSTKEY().hashCode();
        }
        if (getSTANDARD_DATE() != null) {
            _hashCode += getSTANDARD_DATE().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DT_SCM0520_SCMT_MASTER.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn://scm.cjfreshway.co.kr/SRMIE", ">DT_SCM0520_SCM>T_MASTER"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("POKEY");
        elemField.setXmlName(new javax.xml.namespace.QName("", "POKEY"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("POLINE");
        elemField.setXmlName(new javax.xml.namespace.QName("", "POLINE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("SKU");
        elemField.setXmlName(new javax.xml.namespace.QName("", "SKU"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CONVSERIALNO");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CONVSERIALNO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CUSTKEY");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CUSTKEY"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("STANDARD_DATE");
        elemField.setXmlName(new javax.xml.namespace.QName("", "STANDARD_DATE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
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
