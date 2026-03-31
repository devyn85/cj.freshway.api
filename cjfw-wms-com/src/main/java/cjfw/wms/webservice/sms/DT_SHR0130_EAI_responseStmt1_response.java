package cjfw.wms.webservice.sms;

/**
 * DT_SHR0130_EAI_responseStmt1_response.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

public class DT_SHR0130_EAI_responseStmt1_response  implements java.io.Serializable {
    private java.lang.String insert_count;

    public DT_SHR0130_EAI_responseStmt1_response() {
    }

    public DT_SHR0130_EAI_responseStmt1_response(
           java.lang.String insert_count) {
           this.insert_count = insert_count;
    }


    /**
     * Gets the insert_count value for this DT_SHR0130_EAI_responseStmt1_response.
     * 
     * @return insert_count
     */
    public java.lang.String getInsert_count() {
        return insert_count;
    }


    /**
     * Sets the insert_count value for this DT_SHR0130_EAI_responseStmt1_response.
     * 
     * @param insert_count
     */
    public void setInsert_count(java.lang.String insert_count) {
        this.insert_count = insert_count;
    }

    private static java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DT_SHR0130_EAI_responseStmt1_response)) return false;
        DT_SHR0130_EAI_responseStmt1_response other = (DT_SHR0130_EAI_responseStmt1_response) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            //return (__equalsCalc == obj);
            return (__equalsCalc.equals(obj));
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.insert_count==null && other.getInsert_count()==null) || 
             (this.insert_count!=null &&
              this.insert_count.equals(other.getInsert_count())));
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
        if (getInsert_count() != null) {
            _hashCode += getInsert_count().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DT_SHR0130_EAI_responseStmt1_response.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn://shr.cjfreshway.co.kr/COMM", ">DT_SHR0130_EAI_response>stmt1_response"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("insert_count");
        elemField.setXmlName(new javax.xml.namespace.QName("", "insert_count"));
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
