package cjfw.wms.webservice.mmStoIf;
/**
 * DT_SCM0160_SCMIF_DM_DOCUMENT_H.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

public class DT_SCM0160_SCMIF_DM_DOCUMENT_H  implements java.io.Serializable {
    private java.lang.String DOCNO;

    private java.lang.String ORDERTYPE;

    private java.lang.String DCCODE;

    private java.lang.String FROM_BILLTOKEY;

    private java.lang.String STORERKEY;

    private java.lang.String SHOPPINGMALL;

    public DT_SCM0160_SCMIF_DM_DOCUMENT_H() {
    }

    public DT_SCM0160_SCMIF_DM_DOCUMENT_H(
           java.lang.String DOCNO,
           java.lang.String ORDERTYPE,
           java.lang.String DCCODE,
           java.lang.String FROM_BILLTOKEY,
           java.lang.String STORERKEY,
           java.lang.String SHOPPINGMALL) {
           this.DOCNO = DOCNO;
           this.ORDERTYPE = ORDERTYPE;
           this.DCCODE = DCCODE;
           this.FROM_BILLTOKEY = FROM_BILLTOKEY;
           this.STORERKEY = STORERKEY;
           this.SHOPPINGMALL = SHOPPINGMALL;
    }


    /**
     * Gets the DOCNO value for this DT_SCM0160_SCMIF_DM_DOCUMENT_H.
     * 
     * @return DOCNO
     */
    public java.lang.String getDOCNO() {
        return DOCNO;
    }


    /**
     * Sets the DOCNO value for this DT_SCM0160_SCMIF_DM_DOCUMENT_H.
     * 
     * @param DOCNO
     */
    public void setDOCNO(java.lang.String DOCNO) {
        this.DOCNO = DOCNO;
    }


    /**
     * Gets the ORDERTYPE value for this DT_SCM0160_SCMIF_DM_DOCUMENT_H.
     * 
     * @return ORDERTYPE
     */
    public java.lang.String getORDERTYPE() {
        return ORDERTYPE;
    }


    /**
     * Sets the ORDERTYPE value for this DT_SCM0160_SCMIF_DM_DOCUMENT_H.
     * 
     * @param ORDERTYPE
     */
    public void setORDERTYPE(java.lang.String ORDERTYPE) {
        this.ORDERTYPE = ORDERTYPE;
    }


    /**
     * Gets the DCCODE value for this DT_SCM0160_SCMIF_DM_DOCUMENT_H.
     * 
     * @return DCCODE
     */
    public java.lang.String getDCCODE() {
        return DCCODE;
    }


    /**
     * Sets the DCCODE value for this DT_SCM0160_SCMIF_DM_DOCUMENT_H.
     * 
     * @param DCCODE
     */
    public void setDCCODE(java.lang.String DCCODE) {
        this.DCCODE = DCCODE;
    }


    /**
     * Gets the FROM_BILLTOKEY value for this DT_SCM0160_SCMIF_DM_DOCUMENT_H.
     * 
     * @return FROM_BILLTOKEY
     */
    public java.lang.String getFROM_BILLTOKEY() {
        return FROM_BILLTOKEY;
    }


    /**
     * Sets the FROM_BILLTOKEY value for this DT_SCM0160_SCMIF_DM_DOCUMENT_H.
     * 
     * @param FROM_BILLTOKEY
     */
    public void setFROM_BILLTOKEY(java.lang.String FROM_BILLTOKEY) {
        this.FROM_BILLTOKEY = FROM_BILLTOKEY;
    }


    /**
     * Gets the STORERKEY value for this DT_SCM0160_SCMIF_DM_DOCUMENT_H.
     * 
     * @return STORERKEY
     */
    public java.lang.String getSTORERKEY() {
        return STORERKEY;
    }


    /**
     * Sets the STORERKEY value for this DT_SCM0160_SCMIF_DM_DOCUMENT_H.
     * 
     * @param STORERKEY
     */
    public void setSTORERKEY(java.lang.String STORERKEY) {
        this.STORERKEY = STORERKEY;
    }


    /**
     * Gets the SHOPPINGMALL value for this DT_SCM0160_SCMIF_DM_DOCUMENT_H.
     * 
     * @return SHOPPINGMALL
     */
    public java.lang.String getSHOPPINGMALL() {
        return SHOPPINGMALL;
    }


    /**
     * Sets the SHOPPINGMALL value for this DT_SCM0160_SCMIF_DM_DOCUMENT_H.
     * 
     * @param SHOPPINGMALL
     */
    public void setSHOPPINGMALL(java.lang.String SHOPPINGMALL) {
        this.SHOPPINGMALL = SHOPPINGMALL;
    }

    private static java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DT_SCM0160_SCMIF_DM_DOCUMENT_H)) return false;
        DT_SCM0160_SCMIF_DM_DOCUMENT_H other = (DT_SCM0160_SCMIF_DM_DOCUMENT_H) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            //return (__equalsCalc == obj);
            return (__equalsCalc.equals(obj));
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.DOCNO==null && other.getDOCNO()==null) || 
             (this.DOCNO!=null &&
              this.DOCNO.equals(other.getDOCNO()))) &&
            ((this.ORDERTYPE==null && other.getORDERTYPE()==null) || 
             (this.ORDERTYPE!=null &&
              this.ORDERTYPE.equals(other.getORDERTYPE()))) &&
            ((this.DCCODE==null && other.getDCCODE()==null) || 
             (this.DCCODE!=null &&
              this.DCCODE.equals(other.getDCCODE()))) &&
            ((this.FROM_BILLTOKEY==null && other.getFROM_BILLTOKEY()==null) || 
             (this.FROM_BILLTOKEY!=null &&
              this.FROM_BILLTOKEY.equals(other.getFROM_BILLTOKEY()))) &&
            ((this.STORERKEY==null && other.getSTORERKEY()==null) || 
             (this.STORERKEY!=null &&
              this.STORERKEY.equals(other.getSTORERKEY()))) &&
            ((this.SHOPPINGMALL==null && other.getSHOPPINGMALL()==null) || 
             (this.SHOPPINGMALL!=null &&
              this.SHOPPINGMALL.equals(other.getSHOPPINGMALL())));
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
        if (getDOCNO() != null) {
            _hashCode += getDOCNO().hashCode();
        }
        if (getORDERTYPE() != null) {
            _hashCode += getORDERTYPE().hashCode();
        }
        if (getDCCODE() != null) {
            _hashCode += getDCCODE().hashCode();
        }
        if (getFROM_BILLTOKEY() != null) {
            _hashCode += getFROM_BILLTOKEY().hashCode();
        }
        if (getSTORERKEY() != null) {
            _hashCode += getSTORERKEY().hashCode();
        }
        if (getSHOPPINGMALL() != null) {
            _hashCode += getSHOPPINGMALL().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DT_SCM0160_SCMIF_DM_DOCUMENT_H.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn://scm.cjfreshway.co.kr/MM", ">DT_SCM0160_SCM>IF_DM_DOCUMENT_H"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("DOCNO");
        elemField.setXmlName(new javax.xml.namespace.QName("", "DOCNO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ORDERTYPE");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ORDERTYPE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("DCCODE");
        elemField.setXmlName(new javax.xml.namespace.QName("", "DCCODE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("FROM_BILLTOKEY");
        elemField.setXmlName(new javax.xml.namespace.QName("", "FROM_BILLTOKEY"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("STORERKEY");
        elemField.setXmlName(new javax.xml.namespace.QName("", "STORERKEY"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("SHOPPINGMALL");
        elemField.setXmlName(new javax.xml.namespace.QName("", "SHOPPINGMALL"));
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
