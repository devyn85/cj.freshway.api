package cjfw.wms.webservice.ficlose;
/**
 * DT_SCM0080_SCMT_PC.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

public class DT_SCM0080_SCMT_PC  implements java.io.Serializable {
    private java.lang.String STORERKEY;

    private java.lang.String DOCDT;

    private java.lang.String CLOSECD;

    private java.lang.String CLOSESTATUS;

    private java.lang.String EDITWHO;

    public DT_SCM0080_SCMT_PC() {
    }

    public DT_SCM0080_SCMT_PC(
           java.lang.String STORERKEY,
           java.lang.String DOCDT,
           java.lang.String CLOSECD,
           java.lang.String CLOSESTATUS,
           java.lang.String EDITWHO) {
           this.STORERKEY = STORERKEY;
           this.DOCDT = DOCDT;
           this.CLOSECD = CLOSECD;
           this.CLOSESTATUS = CLOSESTATUS;
           this.EDITWHO = EDITWHO;
    }


    /**
     * Gets the STORERKEY value for this DT_SCM0080_SCMT_PC.
     * 
     * @return STORERKEY
     */
    public java.lang.String getSTORERKEY() {
        return STORERKEY;
    }


    /**
     * Sets the STORERKEY value for this DT_SCM0080_SCMT_PC.
     * 
     * @param STORERKEY
     */
    public void setSTORERKEY(java.lang.String STORERKEY) {
        this.STORERKEY = STORERKEY;
    }


    /**
     * Gets the DOCDT value for this DT_SCM0080_SCMT_PC.
     * 
     * @return DOCDT
     */
    public java.lang.String getDOCDT() {
        return DOCDT;
    }


    /**
     * Sets the DOCDT value for this DT_SCM0080_SCMT_PC.
     * 
     * @param DOCDT
     */
    public void setDOCDT(java.lang.String DOCDT) {
        this.DOCDT = DOCDT;
    }


    /**
     * Gets the CLOSECD value for this DT_SCM0080_SCMT_PC.
     * 
     * @return CLOSECD
     */
    public java.lang.String getCLOSECD() {
        return CLOSECD;
    }


    /**
     * Sets the CLOSECD value for this DT_SCM0080_SCMT_PC.
     * 
     * @param CLOSECD
     */
    public void setCLOSECD(java.lang.String CLOSECD) {
        this.CLOSECD = CLOSECD;
    }


    /**
     * Gets the CLOSESTATUS value for this DT_SCM0080_SCMT_PC.
     * 
     * @return CLOSESTATUS
     */
    public java.lang.String getCLOSESTATUS() {
        return CLOSESTATUS;
    }


    /**
     * Sets the CLOSESTATUS value for this DT_SCM0080_SCMT_PC.
     * 
     * @param CLOSESTATUS
     */
    public void setCLOSESTATUS(java.lang.String CLOSESTATUS) {
        this.CLOSESTATUS = CLOSESTATUS;
    }


    /**
     * Gets the EDITWHO value for this DT_SCM0080_SCMT_PC.
     * 
     * @return EDITWHO
     */
    public java.lang.String getEDITWHO() {
        return EDITWHO;
    }


    /**
     * Sets the EDITWHO value for this DT_SCM0080_SCMT_PC.
     * 
     * @param EDITWHO
     */
    public void setEDITWHO(java.lang.String EDITWHO) {
        this.EDITWHO = EDITWHO;
    }

    private static java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DT_SCM0080_SCMT_PC)) return false;
        DT_SCM0080_SCMT_PC other = (DT_SCM0080_SCMT_PC) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            //return (__equalsCalc == obj);
        	return (__equalsCalc.equals(obj));
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.STORERKEY==null && other.getSTORERKEY()==null) || 
             (this.STORERKEY!=null &&
              this.STORERKEY.equals(other.getSTORERKEY()))) &&
            ((this.DOCDT==null && other.getDOCDT()==null) || 
             (this.DOCDT!=null &&
              this.DOCDT.equals(other.getDOCDT()))) &&
            ((this.CLOSECD==null && other.getCLOSECD()==null) || 
             (this.CLOSECD!=null &&
              this.CLOSECD.equals(other.getCLOSECD()))) &&
            ((this.CLOSESTATUS==null && other.getCLOSESTATUS()==null) || 
             (this.CLOSESTATUS!=null &&
              this.CLOSESTATUS.equals(other.getCLOSESTATUS()))) &&
            ((this.EDITWHO==null && other.getEDITWHO()==null) || 
             (this.EDITWHO!=null &&
              this.EDITWHO.equals(other.getEDITWHO())));
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
        if (getSTORERKEY() != null) {
            _hashCode += getSTORERKEY().hashCode();
        }
        if (getDOCDT() != null) {
            _hashCode += getDOCDT().hashCode();
        }
        if (getCLOSECD() != null) {
            _hashCode += getCLOSECD().hashCode();
        }
        if (getCLOSESTATUS() != null) {
            _hashCode += getCLOSESTATUS().hashCode();
        }
        if (getEDITWHO() != null) {
            _hashCode += getEDITWHO().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DT_SCM0080_SCMT_PC.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn://scm.cjfreshway.co.kr/FI", ">DT_SCM0080_SCM>T_PC"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("STORERKEY");
        elemField.setXmlName(new javax.xml.namespace.QName("", "STORERKEY"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("DOCDT");
        elemField.setXmlName(new javax.xml.namespace.QName("", "DOCDT"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CLOSECD");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CLOSECD"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CLOSESTATUS");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CLOSESTATUS"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("EDITWHO");
        elemField.setXmlName(new javax.xml.namespace.QName("", "EDITWHO"));
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
