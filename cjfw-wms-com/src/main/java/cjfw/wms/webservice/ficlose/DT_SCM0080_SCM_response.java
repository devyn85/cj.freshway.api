package cjfw.wms.webservice.ficlose;
/**
 * DT_SCM0080_SCM_response.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

public class DT_SCM0080_SCM_response  implements java.io.Serializable {
    private DT_SCM0080_SCM_responseT_RETURN t_RETURN;

    public DT_SCM0080_SCM_response() {
    }

    public DT_SCM0080_SCM_response(
           DT_SCM0080_SCM_responseT_RETURN t_RETURN) {
           this.t_RETURN = t_RETURN;
    }


    /**
     * Gets the t_RETURN value for this DT_SCM0080_SCM_response.
     * 
     * @return t_RETURN
     */
    public DT_SCM0080_SCM_responseT_RETURN getT_RETURN() {
        return t_RETURN;
    }


    /**
     * Sets the t_RETURN value for this DT_SCM0080_SCM_response.
     * 
     * @param t_RETURN
     */
    public void setT_RETURN(DT_SCM0080_SCM_responseT_RETURN t_RETURN) {
        this.t_RETURN = t_RETURN;
    }

    private static java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DT_SCM0080_SCM_response)) return false;
        DT_SCM0080_SCM_response other = (DT_SCM0080_SCM_response) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            //return (__equalsCalc == obj);
            return (__equalsCalc.equals(obj));
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.t_RETURN==null && other.getT_RETURN()==null) || 
             (this.t_RETURN!=null &&
              this.t_RETURN.equals(other.getT_RETURN())));
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
        if (getT_RETURN() != null) {
            _hashCode += getT_RETURN().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DT_SCM0080_SCM_response.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn://scm.cjfreshway.co.kr/FI", "DT_SCM0080_SCM_response"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("t_RETURN");
        elemField.setXmlName(new javax.xml.namespace.QName("", "T_RETURN"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn://scm.cjfreshway.co.kr/FI", ">DT_SCM0080_SCM_response>T_RETURN"));
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
