package cjfw.wms.webservice.ficlose;
/**
 * DT_SCM0080_SCM_responseT_RETURN.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

public class DT_SCM0080_SCM_responseT_RETURN  implements java.io.Serializable {
    private java.lang.String IF_FLAG;

    private java.lang.String IF_MEMO;

    public DT_SCM0080_SCM_responseT_RETURN() {
    }

    public DT_SCM0080_SCM_responseT_RETURN(
           java.lang.String IF_FLAG,
           java.lang.String IF_MEMO) {
           this.IF_FLAG = IF_FLAG;
           this.IF_MEMO = IF_MEMO;
    }


    /**
     * Gets the IF_FLAG value for this DT_SCM0080_SCM_responseT_RETURN.
     * 
     * @return IF_FLAG
     */
    public java.lang.String getIF_FLAG() {
        return IF_FLAG;
    }


    /**
     * Sets the IF_FLAG value for this DT_SCM0080_SCM_responseT_RETURN.
     * 
     * @param IF_FLAG
     */
    public void setIF_FLAG(java.lang.String IF_FLAG) {
        this.IF_FLAG = IF_FLAG;
    }


    /**
     * Gets the IF_MEMO value for this DT_SCM0080_SCM_responseT_RETURN.
     * 
     * @return IF_MEMO
     */
    public java.lang.String getIF_MEMO() {
        return IF_MEMO;
    }


    /**
     * Sets the IF_MEMO value for this DT_SCM0080_SCM_responseT_RETURN.
     * 
     * @param IF_MEMO
     */
    public void setIF_MEMO(java.lang.String IF_MEMO) {
        this.IF_MEMO = IF_MEMO;
    }

    private static java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DT_SCM0080_SCM_responseT_RETURN)) return false;
        DT_SCM0080_SCM_responseT_RETURN other = (DT_SCM0080_SCM_responseT_RETURN) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            //return (__equalsCalc == obj);
            return (__equalsCalc.equals(obj)); 
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.IF_FLAG==null && other.getIF_FLAG()==null) || 
             (this.IF_FLAG!=null &&
              this.IF_FLAG.equals(other.getIF_FLAG()))) &&
            ((this.IF_MEMO==null && other.getIF_MEMO()==null) || 
             (this.IF_MEMO!=null &&
              this.IF_MEMO.equals(other.getIF_MEMO())));
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
        if (getIF_FLAG() != null) {
            _hashCode += getIF_FLAG().hashCode();
        }
        if (getIF_MEMO() != null) {
            _hashCode += getIF_MEMO().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DT_SCM0080_SCM_responseT_RETURN.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn://scm.cjfreshway.co.kr/FI", ">DT_SCM0080_SCM_response>T_RETURN"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("IF_FLAG");
        elemField.setXmlName(new javax.xml.namespace.QName("", "IF_FLAG"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("IF_MEMO");
        elemField.setXmlName(new javax.xml.namespace.QName("", "IF_MEMO"));
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
