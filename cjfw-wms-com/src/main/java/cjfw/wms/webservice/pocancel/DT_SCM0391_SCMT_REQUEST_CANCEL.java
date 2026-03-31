package cjfw.wms.webservice.pocancel;
/**
 * DT_SCM0391_SCMT_REQUEST_CANCEL.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

public class DT_SCM0391_SCMT_REQUEST_CANCEL  implements java.io.Serializable {
    private java.lang.String REQUESTNO;

    private java.lang.String REQUESTLINE;

    private java.lang.String REASONMSG;

    private java.lang.String ADDWHO;

    public DT_SCM0391_SCMT_REQUEST_CANCEL() {
    }

    public DT_SCM0391_SCMT_REQUEST_CANCEL(
           java.lang.String REQUESTNO,
           java.lang.String REQUESTLINE,
           java.lang.String REASONMSG,
           java.lang.String ADDWHO) {
           this.REQUESTNO = REQUESTNO;
           this.REQUESTLINE = REQUESTLINE;
           this.REASONMSG = REASONMSG;
           this.ADDWHO = ADDWHO;
    }


    /**
     * Gets the REQUESTNO value for this DT_SCM0391_SCMT_REQUEST_CANCEL.
     * 
     * @return REQUESTNO
     */
    public java.lang.String getREQUESTNO() {
        return REQUESTNO;
    }


    /**
     * Sets the REQUESTNO value for this DT_SCM0391_SCMT_REQUEST_CANCEL.
     * 
     * @param REQUESTNO
     */
    public void setREQUESTNO(java.lang.String REQUESTNO) {
        this.REQUESTNO = REQUESTNO;
    }


    /**
     * Gets the REQUESTLINE value for this DT_SCM0391_SCMT_REQUEST_CANCEL.
     * 
     * @return REQUESTLINE
     */
    public java.lang.String getREQUESTLINE() {
        return REQUESTLINE;
    }


    /**
     * Sets the REQUESTLINE value for this DT_SCM0391_SCMT_REQUEST_CANCEL.
     * 
     * @param REQUESTLINE
     */
    public void setREQUESTLINE(java.lang.String REQUESTLINE) {
        this.REQUESTLINE = REQUESTLINE;
    }


    /**
     * Gets the REASONMSG value for this DT_SCM0391_SCMT_REQUEST_CANCEL.
     * 
     * @return REASONMSG
     */
    public java.lang.String getREASONMSG() {
        return REASONMSG;
    }


    /**
     * Sets the REASONMSG value for this DT_SCM0391_SCMT_REQUEST_CANCEL.
     * 
     * @param REASONMSG
     */
    public void setREASONMSG(java.lang.String REASONMSG) {
        this.REASONMSG = REASONMSG;
    }


    /**
     * Gets the ADDWHO value for this DT_SCM0391_SCMT_REQUEST_CANCEL.
     * 
     * @return ADDWHO
     */
    public java.lang.String getADDWHO() {
        return ADDWHO;
    }


    /**
     * Sets the ADDWHO value for this DT_SCM0391_SCMT_REQUEST_CANCEL.
     * 
     * @param ADDWHO
     */
    public void setADDWHO(java.lang.String ADDWHO) {
        this.ADDWHO = ADDWHO;
    }

    private static java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DT_SCM0391_SCMT_REQUEST_CANCEL)) return false;
        DT_SCM0391_SCMT_REQUEST_CANCEL other = (DT_SCM0391_SCMT_REQUEST_CANCEL) obj;
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
            ((this.REASONMSG==null && other.getREASONMSG()==null) || 
             (this.REASONMSG!=null &&
              this.REASONMSG.equals(other.getREASONMSG()))) &&
            ((this.ADDWHO==null && other.getADDWHO()==null) || 
             (this.ADDWHO!=null &&
              this.ADDWHO.equals(other.getADDWHO())));
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
        if (getREASONMSG() != null) {
            _hashCode += getREASONMSG().hashCode();
        }
        if (getADDWHO() != null) {
            _hashCode += getADDWHO().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DT_SCM0391_SCMT_REQUEST_CANCEL.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn://scm.cjfreshway.co.kr/MM", ">DT_SCM0391_SCM>T_REQUEST_CANCEL"));
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
        elemField.setFieldName("REASONMSG");
        elemField.setXmlName(new javax.xml.namespace.QName("", "REASONMSG"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ADDWHO");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ADDWHO"));
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
