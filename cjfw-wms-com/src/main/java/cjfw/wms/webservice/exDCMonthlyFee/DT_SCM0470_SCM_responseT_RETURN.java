package cjfw.wms.webservice.exDCMonthlyFee;
/**
 * DT_SCM0470_SCM_responseT_RETURN.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

public class DT_SCM0470_SCM_responseT_RETURN  implements java.io.Serializable {
    private java.lang.String XSTAT;

    private java.lang.String XMSGS;

    private java.lang.String ZINVOICE;

    private java.lang.String ZRETURN;

    private java.lang.String ZRE_BELNR;

    public DT_SCM0470_SCM_responseT_RETURN() {
    }

    public DT_SCM0470_SCM_responseT_RETURN(
           java.lang.String XSTAT,
           java.lang.String XMSGS,
           java.lang.String ZINVOICE,
           java.lang.String ZRETURN,
           java.lang.String ZRE_BELNR) {
           this.XSTAT = XSTAT;
           this.XMSGS = XMSGS;
           this.ZINVOICE = ZINVOICE;
           this.ZRETURN = ZRETURN;
           this.ZRE_BELNR = ZRE_BELNR;
    }


    /**
     * Gets the XSTAT value for this DT_SCM0470_SCM_responseT_RETURN.
     * 
     * @return XSTAT
     */
    public java.lang.String getXSTAT() {
        return XSTAT;
    }


    /**
     * Sets the XSTAT value for this DT_SCM0470_SCM_responseT_RETURN.
     * 
     * @param XSTAT
     */
    public void setXSTAT(java.lang.String XSTAT) {
        this.XSTAT = XSTAT;
    }


    /**
     * Gets the XMSGS value for this DT_SCM0470_SCM_responseT_RETURN.
     * 
     * @return XMSGS
     */
    public java.lang.String getXMSGS() {
        return XMSGS;
    }


    /**
     * Sets the XMSGS value for this DT_SCM0470_SCM_responseT_RETURN.
     * 
     * @param XMSGS
     */
    public void setXMSGS(java.lang.String XMSGS) {
        this.XMSGS = XMSGS;
    }


    /**
     * Gets the ZINVOICE value for this DT_SCM0470_SCM_responseT_RETURN.
     * 
     * @return ZINVOICE
     */
    public java.lang.String getZINVOICE() {
        return ZINVOICE;
    }


    /**
     * Sets the ZINVOICE value for this DT_SCM0470_SCM_responseT_RETURN.
     * 
     * @param ZINVOICE
     */
    public void setZINVOICE(java.lang.String ZINVOICE) {
        this.ZINVOICE = ZINVOICE;
    }


    /**
     * Gets the ZRETURN value for this DT_SCM0470_SCM_responseT_RETURN.
     * 
     * @return ZRETURN
     */
    public java.lang.String getZRETURN() {
        return ZRETURN;
    }


    /**
     * Sets the ZRETURN value for this DT_SCM0470_SCM_responseT_RETURN.
     * 
     * @param ZRETURN
     */
    public void setZRETURN(java.lang.String ZRETURN) {
        this.ZRETURN = ZRETURN;
    }


    /**
     * Gets the ZRE_BELNR value for this DT_SCM0470_SCM_responseT_RETURN.
     * 
     * @return ZRE_BELNR
     */
    public java.lang.String getZRE_BELNR() {
        return ZRE_BELNR;
    }


    /**
     * Sets the ZRE_BELNR value for this DT_SCM0470_SCM_responseT_RETURN.
     * 
     * @param ZRE_BELNR
     */
    public void setZRE_BELNR(java.lang.String ZRE_BELNR) {
        this.ZRE_BELNR = ZRE_BELNR;
    }

    private static java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DT_SCM0470_SCM_responseT_RETURN)) return false;
        DT_SCM0470_SCM_responseT_RETURN other = (DT_SCM0470_SCM_responseT_RETURN) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.XSTAT==null && other.getXSTAT()==null) || 
             (this.XSTAT!=null &&
              this.XSTAT.equals(other.getXSTAT()))) &&
            ((this.XMSGS==null && other.getXMSGS()==null) || 
             (this.XMSGS!=null &&
              this.XMSGS.equals(other.getXMSGS()))) &&
            ((this.ZINVOICE==null && other.getZINVOICE()==null) || 
             (this.ZINVOICE!=null &&
              this.ZINVOICE.equals(other.getZINVOICE()))) &&
            ((this.ZRETURN==null && other.getZRETURN()==null) || 
             (this.ZRETURN!=null &&
              this.ZRETURN.equals(other.getZRETURN()))) &&
            ((this.ZRE_BELNR==null && other.getZRE_BELNR()==null) || 
             (this.ZRE_BELNR!=null &&
              this.ZRE_BELNR.equals(other.getZRE_BELNR())));
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
        if (getXSTAT() != null) {
            _hashCode += getXSTAT().hashCode();
        }
        if (getXMSGS() != null) {
            _hashCode += getXMSGS().hashCode();
        }
        if (getZINVOICE() != null) {
            _hashCode += getZINVOICE().hashCode();
        }
        if (getZRETURN() != null) {
            _hashCode += getZRETURN().hashCode();
        }
        if (getZRE_BELNR() != null) {
            _hashCode += getZRE_BELNR().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DT_SCM0470_SCM_responseT_RETURN.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn://scm.cjfreshway.co.kr/MM", ">DT_SCM0470_SCM_response>T_RETURN"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("XSTAT");
        elemField.setXmlName(new javax.xml.namespace.QName("", "XSTAT"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("XMSGS");
        elemField.setXmlName(new javax.xml.namespace.QName("", "XMSGS"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ZINVOICE");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ZINVOICE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ZRETURN");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ZRETURN"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ZRE_BELNR");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ZRE_BELNR"));
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
