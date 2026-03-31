package cjfw.wms.webservice.exDCEtcFee;
/**
 * DT_SCM0480_SCM.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

public class DT_SCM0480_SCM  implements java.io.Serializable {
    private DT_SCM0480_SCMIF_IB_STOCKETC_H[] IF_IB_STOCKETC_H;

    private DT_SCM0480_SCMIF_IB_STOCKETC_D[] IF_IB_STOCKETC_D;

    private DT_SCM0480_SCMIF_IB_STOCKETC_FILE[] IF_IB_STOCKETC_FILE;

    public DT_SCM0480_SCM() {
    }

    public DT_SCM0480_SCM(
           DT_SCM0480_SCMIF_IB_STOCKETC_H[] IF_IB_STOCKETC_H,
           DT_SCM0480_SCMIF_IB_STOCKETC_D[] IF_IB_STOCKETC_D,
           DT_SCM0480_SCMIF_IB_STOCKETC_FILE[] IF_IB_STOCKETC_FILE) {
           this.IF_IB_STOCKETC_H = IF_IB_STOCKETC_H;
           this.IF_IB_STOCKETC_D = IF_IB_STOCKETC_D;
           this.IF_IB_STOCKETC_FILE = IF_IB_STOCKETC_FILE;
    }


    /**
     * Gets the IF_IB_STOCKETC_H value for this DT_SCM0480_SCM.
     * 
     * @return IF_IB_STOCKETC_H
     */
    public DT_SCM0480_SCMIF_IB_STOCKETC_H[] getIF_IB_STOCKETC_H() {
        return IF_IB_STOCKETC_H;
    }


    /**
     * Sets the IF_IB_STOCKETC_H value for this DT_SCM0480_SCM.
     * 
     * @param IF_IB_STOCKETC_H
     */
    public void setIF_IB_STOCKETC_H(DT_SCM0480_SCMIF_IB_STOCKETC_H[] IF_IB_STOCKETC_H) {
        this.IF_IB_STOCKETC_H = IF_IB_STOCKETC_H;
    }

    public DT_SCM0480_SCMIF_IB_STOCKETC_H getIF_IB_STOCKETC_H(int i) {
        return this.IF_IB_STOCKETC_H[i];
    }

    public void setIF_IB_STOCKETC_H(int i, DT_SCM0480_SCMIF_IB_STOCKETC_H _value) {
        this.IF_IB_STOCKETC_H[i] = _value;
    }


    /**
     * Gets the IF_IB_STOCKETC_D value for this DT_SCM0480_SCM.
     * 
     * @return IF_IB_STOCKETC_D
     */
    public DT_SCM0480_SCMIF_IB_STOCKETC_D[] getIF_IB_STOCKETC_D() {
        return IF_IB_STOCKETC_D;
    }


    /**
     * Sets the IF_IB_STOCKETC_D value for this DT_SCM0480_SCM.
     * 
     * @param IF_IB_STOCKETC_D
     */
    public void setIF_IB_STOCKETC_D(DT_SCM0480_SCMIF_IB_STOCKETC_D[] IF_IB_STOCKETC_D) {
        this.IF_IB_STOCKETC_D = IF_IB_STOCKETC_D;
    }

    public DT_SCM0480_SCMIF_IB_STOCKETC_D getIF_IB_STOCKETC_D(int i) {
        return this.IF_IB_STOCKETC_D[i];
    }

    public void setIF_IB_STOCKETC_D(int i, DT_SCM0480_SCMIF_IB_STOCKETC_D _value) {
        this.IF_IB_STOCKETC_D[i] = _value;
    }


    /**
     * Gets the IF_IB_STOCKETC_FILE value for this DT_SCM0480_SCM.
     * 
     * @return IF_IB_STOCKETC_FILE
     */
    public DT_SCM0480_SCMIF_IB_STOCKETC_FILE[] getIF_IB_STOCKETC_FILE() {
        return IF_IB_STOCKETC_FILE;
    }


    /**
     * Sets the IF_IB_STOCKETC_FILE value for this DT_SCM0480_SCM.
     * 
     * @param IF_IB_STOCKETC_FILE
     */
    public void setIF_IB_STOCKETC_FILE(DT_SCM0480_SCMIF_IB_STOCKETC_FILE[] IF_IB_STOCKETC_FILE) {
        this.IF_IB_STOCKETC_FILE = IF_IB_STOCKETC_FILE;
    }

    public DT_SCM0480_SCMIF_IB_STOCKETC_FILE getIF_IB_STOCKETC_FILE(int i) {
        return this.IF_IB_STOCKETC_FILE[i];
    }

    public void setIF_IB_STOCKETC_FILE(int i, DT_SCM0480_SCMIF_IB_STOCKETC_FILE _value) {
        this.IF_IB_STOCKETC_FILE[i] = _value;
    }

    private static java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DT_SCM0480_SCM)) return false;
        DT_SCM0480_SCM other = (DT_SCM0480_SCM) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.IF_IB_STOCKETC_H==null && other.getIF_IB_STOCKETC_H()==null) || 
             (this.IF_IB_STOCKETC_H!=null &&
              java.util.Arrays.equals(this.IF_IB_STOCKETC_H, other.getIF_IB_STOCKETC_H()))) &&
            ((this.IF_IB_STOCKETC_D==null && other.getIF_IB_STOCKETC_D()==null) || 
             (this.IF_IB_STOCKETC_D!=null &&
              java.util.Arrays.equals(this.IF_IB_STOCKETC_D, other.getIF_IB_STOCKETC_D()))) &&
            ((this.IF_IB_STOCKETC_FILE==null && other.getIF_IB_STOCKETC_FILE()==null) || 
             (this.IF_IB_STOCKETC_FILE!=null &&
              java.util.Arrays.equals(this.IF_IB_STOCKETC_FILE, other.getIF_IB_STOCKETC_FILE())));
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
        if (getIF_IB_STOCKETC_H() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getIF_IB_STOCKETC_H());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getIF_IB_STOCKETC_H(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getIF_IB_STOCKETC_D() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getIF_IB_STOCKETC_D());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getIF_IB_STOCKETC_D(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getIF_IB_STOCKETC_FILE() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getIF_IB_STOCKETC_FILE());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getIF_IB_STOCKETC_FILE(), i);
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
        new org.apache.axis.description.TypeDesc(DT_SCM0480_SCM.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn://scm.cjfreshway.co.kr/MM", "DT_SCM0480_SCM"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("IF_IB_STOCKETC_H");
        elemField.setXmlName(new javax.xml.namespace.QName("", "IF_IB_STOCKETC_H"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn://scm.cjfreshway.co.kr/MM", ">DT_SCM0480_SCM>IF_IB_STOCKETC_H"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("IF_IB_STOCKETC_D");
        elemField.setXmlName(new javax.xml.namespace.QName("", "IF_IB_STOCKETC_D"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn://scm.cjfreshway.co.kr/MM", ">DT_SCM0480_SCM>IF_IB_STOCKETC_D"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("IF_IB_STOCKETC_FILE");
        elemField.setXmlName(new javax.xml.namespace.QName("", "IF_IB_STOCKETC_FILE"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn://scm.cjfreshway.co.kr/MM", ">DT_SCM0480_SCM>IF_IB_STOCKETC_FILE"));
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
