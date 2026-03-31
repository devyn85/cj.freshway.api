package cjfw.wms.webservice.pocancel;
/**
 * DT_SCM0390_SCM_responseT_RETURN.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

public class DT_SCM0390_SCM_responseT_RETURN  implements java.io.Serializable {
    private java.lang.String REQUESTNO;

    private java.lang.String REQUESTLINE;

    private java.lang.String XSTAT;

    private java.lang.String XMSGS;

    public DT_SCM0390_SCM_responseT_RETURN() {
    }

    public DT_SCM0390_SCM_responseT_RETURN(
           java.lang.String REQUESTNO,
           java.lang.String REQUESTLINE,
           java.lang.String XSTAT,
           java.lang.String XMSGS) {
           this.REQUESTNO = REQUESTNO;
           this.REQUESTLINE = REQUESTLINE;
           this.XSTAT = XSTAT;
           this.XMSGS = XMSGS;
    }


    /**
     * Gets the REQUESTNO value for this DT_SCM0390_SCM_responseT_RETURN.
     * 
     * @return REQUESTNO
     */
    public java.lang.String getREQUESTNO() {
        return REQUESTNO;
    }


    /**
     * Sets the REQUESTNO value for this DT_SCM0390_SCM_responseT_RETURN.
     * 
     * @param REQUESTNO
     */
    public void setREQUESTNO(java.lang.String REQUESTNO) {
        this.REQUESTNO = REQUESTNO;
    }


    /**
     * Gets the REQUESTLINE value for this DT_SCM0390_SCM_responseT_RETURN.
     * 
     * @return REQUESTLINE
     */
    public java.lang.String getREQUESTLINE() {
        return REQUESTLINE;
    }


    /**
     * Sets the REQUESTLINE value for this DT_SCM0390_SCM_responseT_RETURN.
     * 
     * @param REQUESTLINE
     */
    public void setREQUESTLINE(java.lang.String REQUESTLINE) {
        this.REQUESTLINE = REQUESTLINE;
    }


    /**
     * Gets the XSTAT value for this DT_SCM0390_SCM_responseT_RETURN.
     * 
     * @return XSTAT
     */
    public java.lang.String getXSTAT() {
        return XSTAT;
    }


    /**
     * Sets the XSTAT value for this DT_SCM0390_SCM_responseT_RETURN.
     * 
     * @param XSTAT
     */
    public void setXSTAT(java.lang.String XSTAT) {
        this.XSTAT = XSTAT;
    }


    /**
     * Gets the XMSGS value for this DT_SCM0390_SCM_responseT_RETURN.
     * 
     * @return XMSGS
     */
    public java.lang.String getXMSGS() {
        return XMSGS;
    }


    /**
     * Sets the XMSGS value for this DT_SCM0390_SCM_responseT_RETURN.
     * 
     * @param XMSGS
     */
    public void setXMSGS(java.lang.String XMSGS) {
        this.XMSGS = XMSGS;
    }

    private static java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DT_SCM0390_SCM_responseT_RETURN)) return false;
        DT_SCM0390_SCM_responseT_RETURN other = (DT_SCM0390_SCM_responseT_RETURN) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
        	//return (__equalsCalc == obj);
            return (__equalsCalc.equals(obj));
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.REQUESTNO==null && other.getREQUESTNO()==null) || 
             (this.REQUESTNO!=null &&
              this.REQUESTNO.equals(other.getREQUESTNO()))) &&
            ((this.REQUESTLINE==null && other.getREQUESTLINE()==null) || 
             (this.REQUESTLINE!=null &&
              this.REQUESTLINE.equals(other.getREQUESTLINE()))) &&
            ((this.XSTAT==null && other.getXSTAT()==null) || 
             (this.XSTAT!=null &&
              this.XSTAT.equals(other.getXSTAT()))) &&
            ((this.XMSGS==null && other.getXMSGS()==null) || 
             (this.XMSGS!=null &&
              this.XMSGS.equals(other.getXMSGS())));
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
        if (getREQUESTNO() != null) {
            _hashCode += getREQUESTNO().hashCode();
        }
        if (getREQUESTLINE() != null) {
            _hashCode += getREQUESTLINE().hashCode();
        }
        if (getXSTAT() != null) {
            _hashCode += getXSTAT().hashCode();
        }
        if (getXMSGS() != null) {
            _hashCode += getXMSGS().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DT_SCM0390_SCM_responseT_RETURN.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn://scm.cjfreshway.co.kr/SRMPR", ">DT_SCM0390_SCM_response>T_RETURN"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("REQUESTNO");
        elemField.setXmlName(new javax.xml.namespace.QName("", "REQUESTNO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("REQUESTLINE");
        elemField.setXmlName(new javax.xml.namespace.QName("", "REQUESTLINE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("XSTAT");
        elemField.setXmlName(new javax.xml.namespace.QName("", "XSTAT"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("XMSGS");
        elemField.setXmlName(new javax.xml.namespace.QName("", "XMSGS"));
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
