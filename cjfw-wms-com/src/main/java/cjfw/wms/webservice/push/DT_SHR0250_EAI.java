package cjfw.wms.webservice.push;

/**
 * DT_SHR0250_EAI.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

public class DT_SHR0250_EAI  implements java.io.Serializable {
    private java.lang.String XSYS;

    private java.lang.String XDATS;

    private java.lang.String XTIMS;

    private DT_SHR0250_EAIT_PARAM t_PARAM;

    public DT_SHR0250_EAI() {
    }

    public DT_SHR0250_EAI(
           java.lang.String XSYS,
           java.lang.String XDATS,
           java.lang.String XTIMS,
           DT_SHR0250_EAIT_PARAM t_PARAM) {
           this.XSYS = XSYS;
           this.XDATS = XDATS;
           this.XTIMS = XTIMS;
           this.t_PARAM = t_PARAM;
    }


    /**
     * Gets the XSYS value for this DT_SHR0250_EAI.
     * 
     * @return XSYS
     */
    public java.lang.String getXSYS() {
        return XSYS;
    }


    /**
     * Sets the XSYS value for this DT_SHR0250_EAI.
     * 
     * @param XSYS
     */
    public void setXSYS(java.lang.String XSYS) {
        this.XSYS = XSYS;
    }


    /**
     * Gets the XDATS value for this DT_SHR0250_EAI.
     * 
     * @return XDATS
     */
    public java.lang.String getXDATS() {
        return XDATS;
    }


    /**
     * Sets the XDATS value for this DT_SHR0250_EAI.
     * 
     * @param XDATS
     */
    public void setXDATS(java.lang.String XDATS) {
        this.XDATS = XDATS;
    }


    /**
     * Gets the XTIMS value for this DT_SHR0250_EAI.
     * 
     * @return XTIMS
     */
    public java.lang.String getXTIMS() {
        return XTIMS;
    }


    /**
     * Sets the XTIMS value for this DT_SHR0250_EAI.
     * 
     * @param XTIMS
     */
    public void setXTIMS(java.lang.String XTIMS) {
        this.XTIMS = XTIMS;
    }


    /**
     * Gets the t_PARAM value for this DT_SHR0250_EAI.
     * 
     * @return t_PARAM
     */
    public DT_SHR0250_EAIT_PARAM getT_PARAM() {
        return t_PARAM;
    }


    /**
     * Sets the t_PARAM value for this DT_SHR0250_EAI.
     * 
     * @param t_PARAM
     */
    public void setT_PARAM(DT_SHR0250_EAIT_PARAM t_PARAM) {
        this.t_PARAM = t_PARAM;
    }

    private static java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DT_SHR0250_EAI)) return false;
        DT_SHR0250_EAI other = (DT_SHR0250_EAI) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc.equals(obj));
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.XSYS==null && other.getXSYS()==null) || 
             (this.XSYS!=null &&
              this.XSYS.equals(other.getXSYS()))) &&
            ((this.XDATS==null && other.getXDATS()==null) || 
             (this.XDATS!=null &&
              this.XDATS.equals(other.getXDATS()))) &&
            ((this.XTIMS==null && other.getXTIMS()==null) || 
             (this.XTIMS!=null &&
              this.XTIMS.equals(other.getXTIMS()))) &&
            ((this.t_PARAM==null && other.getT_PARAM()==null) || 
             (this.t_PARAM!=null &&
              this.t_PARAM.equals(other.getT_PARAM())));
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
        if (getXSYS() != null) {
            _hashCode += getXSYS().hashCode();
        }
        if (getXDATS() != null) {
            _hashCode += getXDATS().hashCode();
        }
        if (getXTIMS() != null) {
            _hashCode += getXTIMS().hashCode();
        }
        if (getT_PARAM() != null) {
            _hashCode += getT_PARAM().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DT_SHR0250_EAI.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn://shr.cjfreshway.co.kr/COMM", "DT_SHR0250_EAI"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("XSYS");
        elemField.setXmlName(new javax.xml.namespace.QName("", "XSYS"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("XDATS");
        elemField.setXmlName(new javax.xml.namespace.QName("", "XDATS"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("XTIMS");
        elemField.setXmlName(new javax.xml.namespace.QName("", "XTIMS"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("t_PARAM");
        elemField.setXmlName(new javax.xml.namespace.QName("", "T_PARAM"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn://shr.cjfreshway.co.kr/COMM", ">DT_SHR0250_EAI>T_PARAM"));
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
