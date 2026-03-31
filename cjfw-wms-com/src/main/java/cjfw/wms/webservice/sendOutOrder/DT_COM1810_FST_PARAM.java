/**
 * DT_COM1810_FST_PARAM.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cjfw.wms.webservice.sendOutOrder;

public class DT_COM1810_FST_PARAM  implements java.io.Serializable {
    private java.lang.String CO_ID;

    private java.lang.String PUR_REQ_NO;

    private java.lang.String GD_REQ_SEQ;

    private java.lang.String CUST_ID;

    private java.lang.String SALE_UNPRC;

    private java.lang.String JOB_DIV_CD;

    public DT_COM1810_FST_PARAM() {
    }

    public DT_COM1810_FST_PARAM(
           java.lang.String CO_ID,
           java.lang.String PUR_REQ_NO,
           java.lang.String GD_REQ_SEQ,
           java.lang.String CUST_ID,
           java.lang.String SALE_UNPRC,
           java.lang.String JOB_DIV_CD) {
           this.CO_ID = CO_ID;
           this.PUR_REQ_NO = PUR_REQ_NO;
           this.GD_REQ_SEQ = GD_REQ_SEQ;
           this.CUST_ID = CUST_ID;
           this.SALE_UNPRC = SALE_UNPRC;
           this.JOB_DIV_CD = JOB_DIV_CD;
    }


    /**
     * Gets the CO_ID value for this DT_COM1810_FST_PARAM.
     * 
     * @return CO_ID
     */
    public java.lang.String getCO_ID() {
        return CO_ID;
    }


    /**
     * Sets the CO_ID value for this DT_COM1810_FST_PARAM.
     * 
     * @param CO_ID
     */
    public void setCO_ID(java.lang.String CO_ID) {
        this.CO_ID = CO_ID;
    }


    /**
     * Gets the PUR_REQ_NO value for this DT_COM1810_FST_PARAM.
     * 
     * @return PUR_REQ_NO
     */
    public java.lang.String getPUR_REQ_NO() {
        return PUR_REQ_NO;
    }


    /**
     * Sets the PUR_REQ_NO value for this DT_COM1810_FST_PARAM.
     * 
     * @param PUR_REQ_NO
     */
    public void setPUR_REQ_NO(java.lang.String PUR_REQ_NO) {
        this.PUR_REQ_NO = PUR_REQ_NO;
    }


    /**
     * Gets the GD_REQ_SEQ value for this DT_COM1810_FST_PARAM.
     * 
     * @return GD_REQ_SEQ
     */
    public java.lang.String getGD_REQ_SEQ() {
        return GD_REQ_SEQ;
    }


    /**
     * Sets the GD_REQ_SEQ value for this DT_COM1810_FST_PARAM.
     * 
     * @param GD_REQ_SEQ
     */
    public void setGD_REQ_SEQ(java.lang.String GD_REQ_SEQ) {
        this.GD_REQ_SEQ = GD_REQ_SEQ;
    }


    /**
     * Gets the CUST_ID value for this DT_COM1810_FST_PARAM.
     * 
     * @return CUST_ID
     */
    public java.lang.String getCUST_ID() {
        return CUST_ID;
    }


    /**
     * Sets the CUST_ID value for this DT_COM1810_FST_PARAM.
     * 
     * @param CUST_ID
     */
    public void setCUST_ID(java.lang.String CUST_ID) {
        this.CUST_ID = CUST_ID;
    }


    /**
     * Gets the SALE_UNPRC value for this DT_COM1810_FST_PARAM.
     * 
     * @return SALE_UNPRC
     */
    public java.lang.String getSALE_UNPRC() {
        return SALE_UNPRC;
    }


    /**
     * Sets the SALE_UNPRC value for this DT_COM1810_FST_PARAM.
     * 
     * @param SALE_UNPRC
     */
    public void setSALE_UNPRC(java.lang.String SALE_UNPRC) {
        this.SALE_UNPRC = SALE_UNPRC;
    }


    /**
     * Gets the JOB_DIV_CD value for this DT_COM1810_FST_PARAM.
     * 
     * @return JOB_DIV_CD
     */
    public java.lang.String getJOB_DIV_CD() {
        return JOB_DIV_CD;
    }


    /**
     * Sets the JOB_DIV_CD value for this DT_COM1810_FST_PARAM.
     * 
     * @param JOB_DIV_CD
     */
    public void setJOB_DIV_CD(java.lang.String JOB_DIV_CD) {
        this.JOB_DIV_CD = JOB_DIV_CD;
    }

    private static java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DT_COM1810_FST_PARAM)) return false;
        DT_COM1810_FST_PARAM other = (DT_COM1810_FST_PARAM) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
        	//return (__equalsCalc == obj);
            return (__equalsCalc.equals(obj));
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.CO_ID==null && other.getCO_ID()==null) || 
             (this.CO_ID!=null &&
              this.CO_ID.equals(other.getCO_ID()))) &&
            ((this.PUR_REQ_NO==null && other.getPUR_REQ_NO()==null) || 
             (this.PUR_REQ_NO!=null &&
              this.PUR_REQ_NO.equals(other.getPUR_REQ_NO()))) &&
            ((this.GD_REQ_SEQ==null && other.getGD_REQ_SEQ()==null) || 
             (this.GD_REQ_SEQ!=null &&
              this.GD_REQ_SEQ.equals(other.getGD_REQ_SEQ()))) &&
            ((this.CUST_ID==null && other.getCUST_ID()==null) || 
             (this.CUST_ID!=null &&
              this.CUST_ID.equals(other.getCUST_ID()))) &&
            ((this.SALE_UNPRC==null && other.getSALE_UNPRC()==null) || 
             (this.SALE_UNPRC!=null &&
              this.SALE_UNPRC.equals(other.getSALE_UNPRC()))) &&
            ((this.JOB_DIV_CD==null && other.getJOB_DIV_CD()==null) || 
             (this.JOB_DIV_CD!=null &&
              this.JOB_DIV_CD.equals(other.getJOB_DIV_CD())));
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
        if (getCO_ID() != null) {
            _hashCode += getCO_ID().hashCode();
        }
        if (getPUR_REQ_NO() != null) {
            _hashCode += getPUR_REQ_NO().hashCode();
        }
        if (getGD_REQ_SEQ() != null) {
            _hashCode += getGD_REQ_SEQ().hashCode();
        }
        if (getCUST_ID() != null) {
            _hashCode += getCUST_ID().hashCode();
        }
        if (getSALE_UNPRC() != null) {
            _hashCode += getSALE_UNPRC().hashCode();
        }
        if (getJOB_DIV_CD() != null) {
            _hashCode += getJOB_DIV_CD().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DT_COM1810_FST_PARAM.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn://com.fs.cjfreshway.co.kr/SD", ">DT_COM1810_FS>T_PARAM"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CO_ID");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CO_ID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("PUR_REQ_NO");
        elemField.setXmlName(new javax.xml.namespace.QName("", "PUR_REQ_NO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("GD_REQ_SEQ");
        elemField.setXmlName(new javax.xml.namespace.QName("", "GD_REQ_SEQ"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CUST_ID");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CUST_ID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("SALE_UNPRC");
        elemField.setXmlName(new javax.xml.namespace.QName("", "SALE_UNPRC"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("JOB_DIV_CD");
        elemField.setXmlName(new javax.xml.namespace.QName("", "JOB_DIV_CD"));
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
