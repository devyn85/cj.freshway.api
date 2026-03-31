/**
 * DT_CJFO0060_SCM_responseOT_ORDQTY.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cjfw.wms.webservice.foOrdQty;

public class DT_CJFO0060_SCM_responseOT_ORDQTY  implements java.io.Serializable {
    private java.lang.String VDATU;

    private java.lang.String VBELN_FW;

    private java.lang.String POSNR;

    private java.lang.String MATNR;

    private java.lang.String MENGE_FW;

    private java.lang.String MEI;

    public DT_CJFO0060_SCM_responseOT_ORDQTY() {
    }

    public DT_CJFO0060_SCM_responseOT_ORDQTY(
           java.lang.String VDATU,
           java.lang.String VBELN_FW,
           java.lang.String POSNR,
           java.lang.String MATNR,
           java.lang.String MENGE_FW,
           java.lang.String MEI) {
           this.VDATU = VDATU;
           this.VBELN_FW = VBELN_FW;
           this.POSNR = POSNR;
           this.MATNR = MATNR;
           this.MENGE_FW = MENGE_FW;
           this.MEI = MEI;
    }


    /**
     * Gets the VDATU value for this DT_CJFO0060_SCM_responseOT_ORDQTY.
     * 
     * @return VDATU
     */
    public java.lang.String getVDATU() {
        return VDATU;
    }


    /**
     * Sets the VDATU value for this DT_CJFO0060_SCM_responseOT_ORDQTY.
     * 
     * @param VDATU
     */
    public void setVDATU(java.lang.String VDATU) {
        this.VDATU = VDATU;
    }


    /**
     * Gets the VBELN_FW value for this DT_CJFO0060_SCM_responseOT_ORDQTY.
     * 
     * @return VBELN_FW
     */
    public java.lang.String getVBELN_FW() {
        return VBELN_FW;
    }


    /**
     * Sets the VBELN_FW value for this DT_CJFO0060_SCM_responseOT_ORDQTY.
     * 
     * @param VBELN_FW
     */
    public void setVBELN_FW(java.lang.String VBELN_FW) {
        this.VBELN_FW = VBELN_FW;
    }


    /**
     * Gets the POSNR value for this DT_CJFO0060_SCM_responseOT_ORDQTY.
     * 
     * @return POSNR
     */
    public java.lang.String getPOSNR() {
        return POSNR;
    }


    /**
     * Sets the POSNR value for this DT_CJFO0060_SCM_responseOT_ORDQTY.
     * 
     * @param POSNR
     */
    public void setPOSNR(java.lang.String POSNR) {
        this.POSNR = POSNR;
    }


    /**
     * Gets the MATNR value for this DT_CJFO0060_SCM_responseOT_ORDQTY.
     * 
     * @return MATNR
     */
    public java.lang.String getMATNR() {
        return MATNR;
    }


    /**
     * Sets the MATNR value for this DT_CJFO0060_SCM_responseOT_ORDQTY.
     * 
     * @param MATNR
     */
    public void setMATNR(java.lang.String MATNR) {
        this.MATNR = MATNR;
    }


    /**
     * Gets the MENGE_FW value for this DT_CJFO0060_SCM_responseOT_ORDQTY.
     * 
     * @return MENGE_FW
     */
    public java.lang.String getMENGE_FW() {
        return MENGE_FW;
    }


    /**
     * Sets the MENGE_FW value for this DT_CJFO0060_SCM_responseOT_ORDQTY.
     * 
     * @param MENGE_FW
     */
    public void setMENGE_FW(java.lang.String MENGE_FW) {
        this.MENGE_FW = MENGE_FW;
    }


    /**
     * Gets the MEI value for this DT_CJFO0060_SCM_responseOT_ORDQTY.
     * 
     * @return MEI
     */
    public java.lang.String getMEI() {
        return MEI;
    }


    /**
     * Sets the MEI value for this DT_CJFO0060_SCM_responseOT_ORDQTY.
     * 
     * @param MEI
     */
    public void setMEI(java.lang.String MEI) {
        this.MEI = MEI;
    }

    private static java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DT_CJFO0060_SCM_responseOT_ORDQTY)) return false;
        DT_CJFO0060_SCM_responseOT_ORDQTY other = (DT_CJFO0060_SCM_responseOT_ORDQTY) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
        	//return (__equalsCalc == obj);
            return (__equalsCalc.equals(obj));
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.VDATU==null && other.getVDATU()==null) || 
             (this.VDATU!=null &&
              this.VDATU.equals(other.getVDATU()))) &&
            ((this.VBELN_FW==null && other.getVBELN_FW()==null) || 
             (this.VBELN_FW!=null &&
              this.VBELN_FW.equals(other.getVBELN_FW()))) &&
            ((this.POSNR==null && other.getPOSNR()==null) || 
             (this.POSNR!=null &&
              this.POSNR.equals(other.getPOSNR()))) &&
            ((this.MATNR==null && other.getMATNR()==null) || 
             (this.MATNR!=null &&
              this.MATNR.equals(other.getMATNR()))) &&
            ((this.MENGE_FW==null && other.getMENGE_FW()==null) || 
             (this.MENGE_FW!=null &&
              this.MENGE_FW.equals(other.getMENGE_FW()))) &&
            ((this.MEI==null && other.getMEI()==null) || 
             (this.MEI!=null &&
              this.MEI.equals(other.getMEI())));
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
        if (getVDATU() != null) {
            _hashCode += getVDATU().hashCode();
        }
        if (getVBELN_FW() != null) {
            _hashCode += getVBELN_FW().hashCode();
        }
        if (getPOSNR() != null) {
            _hashCode += getPOSNR().hashCode();
        }
        if (getMATNR() != null) {
            _hashCode += getMATNR().hashCode();
        }
        if (getMENGE_FW() != null) {
            _hashCode += getMENGE_FW().hashCode();
        }
        if (getMEI() != null) {
            _hashCode += getMEI().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DT_CJFO0060_SCM_responseOT_ORDQTY.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn://cjfo.cjfreshway.co.kr/SCM", ">DT_CJFO0060_SCM_response>OT_ORDQTY"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("VDATU");
        elemField.setXmlName(new javax.xml.namespace.QName("", "VDATU"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("VBELN_FW");
        elemField.setXmlName(new javax.xml.namespace.QName("", "VBELN_FW"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("POSNR");
        elemField.setXmlName(new javax.xml.namespace.QName("", "POSNR"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("MATNR");
        elemField.setXmlName(new javax.xml.namespace.QName("", "MATNR"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("MENGE_FW");
        elemField.setXmlName(new javax.xml.namespace.QName("", "MENGE_FW"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("MEI");
        elemField.setXmlName(new javax.xml.namespace.QName("", "MEI"));
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
