package cjfw.wms.webservice.push;

/**
 * DT_SHR0250_EAIT_PARAM.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

public class DT_SHR0250_EAIT_PARAM  implements java.io.Serializable {
    private java.lang.String CUID;

    private java.lang.String MSG;

    private java.lang.String EXT1;

    private java.lang.String EXT2;

    private java.lang.String EXT3;

    private java.lang.String EXT4;

    private java.lang.String MID;

    private java.lang.String SEQ;

    private java.lang.String SENDDATE;

    public DT_SHR0250_EAIT_PARAM() {
    }

    public DT_SHR0250_EAIT_PARAM(
           java.lang.String CUID,
           java.lang.String MSG,
           java.lang.String EXT1,
           java.lang.String EXT2,
           java.lang.String EXT3,
           java.lang.String EXT4,
           java.lang.String MID,
           java.lang.String SEQ,
           java.lang.String SENDDATE) {
           this.CUID = CUID;
           this.MSG = MSG;
           this.EXT1 = EXT1;
           this.EXT2 = EXT2;
           this.EXT3 = EXT3;
           this.EXT4 = EXT4;
           this.MID = MID;
           this.SEQ = SEQ;
           this.SENDDATE = SENDDATE;
    }


    /**
     * Gets the CUID value for this DT_SHR0250_EAIT_PARAM.
     * 
     * @return CUID
     */
    public java.lang.String getCUID() {
        return CUID;
    }


    /**
     * Sets the CUID value for this DT_SHR0250_EAIT_PARAM.
     * 
     * @param CUID
     */
    public void setCUID(java.lang.String CUID) {
        this.CUID = CUID;
    }


    /**
     * Gets the MSG value for this DT_SHR0250_EAIT_PARAM.
     * 
     * @return MSG
     */
    public java.lang.String getMSG() {
        return MSG;
    }


    /**
     * Sets the MSG value for this DT_SHR0250_EAIT_PARAM.
     * 
     * @param MSG
     */
    public void setMSG(java.lang.String MSG) {
        this.MSG = MSG;
    }


    /**
     * Gets the EXT1 value for this DT_SHR0250_EAIT_PARAM.
     * 
     * @return EXT1
     */
    public java.lang.String getEXT1() {
        return EXT1;
    }


    /**
     * Sets the EXT1 value for this DT_SHR0250_EAIT_PARAM.
     * 
     * @param EXT1
     */
    public void setEXT1(java.lang.String EXT1) {
        this.EXT1 = EXT1;
    }


    /**
     * Gets the EXT2 value for this DT_SHR0250_EAIT_PARAM.
     * 
     * @return EXT2
     */
    public java.lang.String getEXT2() {
        return EXT2;
    }


    /**
     * Sets the EXT2 value for this DT_SHR0250_EAIT_PARAM.
     * 
     * @param EXT2
     */
    public void setEXT2(java.lang.String EXT2) {
        this.EXT2 = EXT2;
    }


    /**
     * Gets the EXT3 value for this DT_SHR0250_EAIT_PARAM.
     * 
     * @return EXT3
     */
    public java.lang.String getEXT3() {
        return EXT3;
    }


    /**
     * Sets the EXT3 value for this DT_SHR0250_EAIT_PARAM.
     * 
     * @param EXT3
     */
    public void setEXT3(java.lang.String EXT3) {
        this.EXT3 = EXT3;
    }


    /**
     * Gets the EXT4 value for this DT_SHR0250_EAIT_PARAM.
     * 
     * @return EXT4
     */
    public java.lang.String getEXT4() {
        return EXT4;
    }


    /**
     * Sets the EXT4 value for this DT_SHR0250_EAIT_PARAM.
     * 
     * @param EXT4
     */
    public void setEXT4(java.lang.String EXT4) {
        this.EXT4 = EXT4;
    }


    /**
     * Gets the MID value for this DT_SHR0250_EAIT_PARAM.
     * 
     * @return MID
     */
    public java.lang.String getMID() {
        return MID;
    }


    /**
     * Sets the MID value for this DT_SHR0250_EAIT_PARAM.
     * 
     * @param MID
     */
    public void setMID(java.lang.String MID) {
        this.MID = MID;
    }


    /**
     * Gets the SEQ value for this DT_SHR0250_EAIT_PARAM.
     * 
     * @return SEQ
     */
    public java.lang.String getSEQ() {
        return SEQ;
    }


    /**
     * Sets the SEQ value for this DT_SHR0250_EAIT_PARAM.
     * 
     * @param SEQ
     */
    public void setSEQ(java.lang.String SEQ) {
        this.SEQ = SEQ;
    }


    /**
     * Gets the SENDDATE value for this DT_SHR0250_EAIT_PARAM.
     * 
     * @return SENDDATE
     */
    public java.lang.String getSENDDATE() {
        return SENDDATE;
    }


    /**
     * Sets the SENDDATE value for this DT_SHR0250_EAIT_PARAM.
     * 
     * @param SENDDATE
     */
    public void setSENDDATE(java.lang.String SENDDATE) {
        this.SENDDATE = SENDDATE;
    }

    private static java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DT_SHR0250_EAIT_PARAM)) return false;
        DT_SHR0250_EAIT_PARAM other = (DT_SHR0250_EAIT_PARAM) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc.equals(obj));
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.CUID==null && other.getCUID()==null) || 
             (this.CUID!=null &&
              this.CUID.equals(other.getCUID()))) &&
            ((this.MSG==null && other.getMSG()==null) || 
             (this.MSG!=null &&
              this.MSG.equals(other.getMSG()))) &&
            ((this.EXT1==null && other.getEXT1()==null) || 
             (this.EXT1!=null &&
              this.EXT1.equals(other.getEXT1()))) &&
            ((this.EXT2==null && other.getEXT2()==null) || 
             (this.EXT2!=null &&
              this.EXT2.equals(other.getEXT2()))) &&
            ((this.EXT3==null && other.getEXT3()==null) || 
             (this.EXT3!=null &&
              this.EXT3.equals(other.getEXT3()))) &&
            ((this.EXT4==null && other.getEXT4()==null) || 
             (this.EXT4!=null &&
              this.EXT4.equals(other.getEXT4()))) &&
            ((this.MID==null && other.getMID()==null) || 
             (this.MID!=null &&
              this.MID.equals(other.getMID()))) &&
            ((this.SEQ==null && other.getSEQ()==null) || 
             (this.SEQ!=null &&
              this.SEQ.equals(other.getSEQ()))) &&
            ((this.SENDDATE==null && other.getSENDDATE()==null) || 
             (this.SENDDATE!=null &&
              this.SENDDATE.equals(other.getSENDDATE())));
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
        if (getCUID() != null) {
            _hashCode += getCUID().hashCode();
        }
        if (getMSG() != null) {
            _hashCode += getMSG().hashCode();
        }
        if (getEXT1() != null) {
            _hashCode += getEXT1().hashCode();
        }
        if (getEXT2() != null) {
            _hashCode += getEXT2().hashCode();
        }
        if (getEXT3() != null) {
            _hashCode += getEXT3().hashCode();
        }
        if (getEXT4() != null) {
            _hashCode += getEXT4().hashCode();
        }
        if (getMID() != null) {
            _hashCode += getMID().hashCode();
        }
        if (getSEQ() != null) {
            _hashCode += getSEQ().hashCode();
        }
        if (getSENDDATE() != null) {
            _hashCode += getSENDDATE().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DT_SHR0250_EAIT_PARAM.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn://shr.cjfreshway.co.kr/COMM", ">DT_SHR0250_EAI>T_PARAM"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CUID");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CUID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("MSG");
        elemField.setXmlName(new javax.xml.namespace.QName("", "MSG"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("EXT1");
        elemField.setXmlName(new javax.xml.namespace.QName("", "EXT1"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("EXT2");
        elemField.setXmlName(new javax.xml.namespace.QName("", "EXT2"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("EXT3");
        elemField.setXmlName(new javax.xml.namespace.QName("", "EXT3"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("EXT4");
        elemField.setXmlName(new javax.xml.namespace.QName("", "EXT4"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("MID");
        elemField.setXmlName(new javax.xml.namespace.QName("", "MID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("SEQ");
        elemField.setXmlName(new javax.xml.namespace.QName("", "SEQ"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("SENDDATE");
        elemField.setXmlName(new javax.xml.namespace.QName("", "SENDDATE"));
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
