/**
 * DT_CJFO0060_SCM_response.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cjfw.wms.webservice.foOrdQty;

public class DT_CJFO0060_SCM_response  implements java.io.Serializable {
    private cjfw.wms.webservice.foOrdQty.DT_CJFO0060_SCM_responseES_RETURN ES_RETURN;

    private cjfw.wms.webservice.foOrdQty.DT_CJFO0060_SCM_responseOT_ORDQTY[] OT_ORDQTY;

    public DT_CJFO0060_SCM_response() {
    }

    public DT_CJFO0060_SCM_response(
           cjfw.wms.webservice.foOrdQty.DT_CJFO0060_SCM_responseES_RETURN ES_RETURN,
           cjfw.wms.webservice.foOrdQty.DT_CJFO0060_SCM_responseOT_ORDQTY[] OT_ORDQTY) {
           this.ES_RETURN = ES_RETURN;
           this.OT_ORDQTY = OT_ORDQTY;
    }


    /**
     * Gets the ES_RETURN value for this DT_CJFO0060_SCM_response.
     * 
     * @return ES_RETURN
     */
    public cjfw.wms.webservice.foOrdQty.DT_CJFO0060_SCM_responseES_RETURN getES_RETURN() {
        return ES_RETURN;
    }


    /**
     * Sets the ES_RETURN value for this DT_CJFO0060_SCM_response.
     * 
     * @param ES_RETURN
     */
    public void setES_RETURN(cjfw.wms.webservice.foOrdQty.DT_CJFO0060_SCM_responseES_RETURN ES_RETURN) {
        this.ES_RETURN = ES_RETURN;
    }


    /**
     * Gets the OT_ORDQTY value for this DT_CJFO0060_SCM_response.
     * 
     * @return OT_ORDQTY
     */
    public cjfw.wms.webservice.foOrdQty.DT_CJFO0060_SCM_responseOT_ORDQTY[] getOT_ORDQTY() {
        return OT_ORDQTY;
    }


    /**
     * Sets the OT_ORDQTY value for this DT_CJFO0060_SCM_response.
     * 
     * @param OT_ORDQTY
     */
    public void setOT_ORDQTY(cjfw.wms.webservice.foOrdQty.DT_CJFO0060_SCM_responseOT_ORDQTY[] OT_ORDQTY) {
        this.OT_ORDQTY = OT_ORDQTY;
    }

    public cjfw.wms.webservice.foOrdQty.DT_CJFO0060_SCM_responseOT_ORDQTY getOT_ORDQTY(int i) {
        return this.OT_ORDQTY[i];
    }

    public void setOT_ORDQTY(int i, cjfw.wms.webservice.foOrdQty.DT_CJFO0060_SCM_responseOT_ORDQTY _value) {
        this.OT_ORDQTY[i] = _value;
    }

    private static java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DT_CJFO0060_SCM_response)) return false;
        DT_CJFO0060_SCM_response other = (DT_CJFO0060_SCM_response) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
        	//return (__equalsCalc == obj);
            return (__equalsCalc.equals(obj));
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.ES_RETURN==null && other.getES_RETURN()==null) || 
             (this.ES_RETURN!=null &&
              this.ES_RETURN.equals(other.getES_RETURN()))) &&
            ((this.OT_ORDQTY==null && other.getOT_ORDQTY()==null) || 
             (this.OT_ORDQTY!=null &&
              java.util.Arrays.equals(this.OT_ORDQTY, other.getOT_ORDQTY())));
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
        if (getES_RETURN() != null) {
            _hashCode += getES_RETURN().hashCode();
        }
        if (getOT_ORDQTY() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getOT_ORDQTY());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getOT_ORDQTY(), i);
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
        new org.apache.axis.description.TypeDesc(DT_CJFO0060_SCM_response.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn://cjfo.cjfreshway.co.kr/SCM", "DT_CJFO0060_SCM_response"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ES_RETURN");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ES_RETURN"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn://cjfo.cjfreshway.co.kr/SCM", ">DT_CJFO0060_SCM_response>ES_RETURN"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("OT_ORDQTY");
        elemField.setXmlName(new javax.xml.namespace.QName("", "OT_ORDQTY"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn://cjfo.cjfreshway.co.kr/SCM", ">DT_CJFO0060_SCM_response>OT_ORDQTY"));
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
