package cjfw.wms.webservice.sms;

/**
 * DT_SHR0130_EAIT_PARAM.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

public class DT_SHR0130_EAIT_PARAM  implements java.io.Serializable {
    /* 고객이 발급한 SubID */
    private java.lang.String ID;

    /* 수신할 핸드폰 번호 */
    private java.lang.String PHONE;

    /* 송신자 전화번호 */
    private java.lang.String CALLBACK;

    /* 메시지를 전송할 시간 , 미래 시간을 넣으면 예약 발송됨 */
    private java.lang.String DATE;

    /* 전송할 메시지 */
    private java.lang.String MSG;

    public DT_SHR0130_EAIT_PARAM() {
    }

    public DT_SHR0130_EAIT_PARAM(
           java.lang.String ID,
           java.lang.String PHONE,
           java.lang.String CALLBACK,
           java.lang.String DATE,
           java.lang.String MSG) {
           this.ID = ID;
           this.PHONE = PHONE;
           this.CALLBACK = CALLBACK;
           this.DATE = DATE;
           this.MSG = MSG;
    }


    /**
     * Gets the ID value for this DT_SHR0130_EAIT_PARAM.
     * 
     * @return ID   * 고객이 발급한 SubID
     */
    public java.lang.String getID() {
        return ID;
    }


    /**
     * Sets the ID value for this DT_SHR0130_EAIT_PARAM.
     * 
     * @param ID   * 고객이 발급한 SubID
     */
    public void setID(java.lang.String ID) {
        this.ID = ID;
    }


    /**
     * Gets the PHONE value for this DT_SHR0130_EAIT_PARAM.
     * 
     * @return PHONE   * 수신할 핸드폰 번호
     */
    public java.lang.String getPHONE() {
        return PHONE;
    }


    /**
     * Sets the PHONE value for this DT_SHR0130_EAIT_PARAM.
     * 
     * @param PHONE   * 수신할 핸드폰 번호
     */
    public void setPHONE(java.lang.String PHONE) {
        this.PHONE = PHONE;
    }


    /**
     * Gets the CALLBACK value for this DT_SHR0130_EAIT_PARAM.
     * 
     * @return CALLBACK   * 송신자 전화번호
     */
    public java.lang.String getCALLBACK() {
        return CALLBACK;
    }


    /**
     * Sets the CALLBACK value for this DT_SHR0130_EAIT_PARAM.
     * 
     * @param CALLBACK   * 송신자 전화번호
     */
    public void setCALLBACK(java.lang.String CALLBACK) {
        this.CALLBACK = CALLBACK;
    }


    /**
     * Gets the DATE value for this DT_SHR0130_EAIT_PARAM.
     * 
     * @return DATE   * 메시지를 전송할 시간 , 미래 시간을 넣으면 예약 발송됨
     */
    public java.lang.String getDATE() {
        return DATE;
    }


    /**
     * Sets the DATE value for this DT_SHR0130_EAIT_PARAM.
     * 
     * @param DATE   * 메시지를 전송할 시간 , 미래 시간을 넣으면 예약 발송됨
     */
    public void setDATE(java.lang.String DATE) {
        this.DATE = DATE;
    }


    /**
     * Gets the MSG value for this DT_SHR0130_EAIT_PARAM.
     * 
     * @return MSG   * 전송할 메시지
     */
    public java.lang.String getMSG() {
        return MSG;
    }


    /**
     * Sets the MSG value for this DT_SHR0130_EAIT_PARAM.
     * 
     * @param MSG   * 전송할 메시지
     */
    public void setMSG(java.lang.String MSG) {
        this.MSG = MSG;
    }

    private static java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DT_SHR0130_EAIT_PARAM)) return false;
        DT_SHR0130_EAIT_PARAM other = (DT_SHR0130_EAIT_PARAM) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            //return (__equalsCalc == obj);
            return (__equalsCalc.equals(obj));
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.ID==null && other.getID()==null) || 
             (this.ID!=null &&
              this.ID.equals(other.getID()))) &&
            ((this.PHONE==null && other.getPHONE()==null) || 
             (this.PHONE!=null &&
              this.PHONE.equals(other.getPHONE()))) &&
            ((this.CALLBACK==null && other.getCALLBACK()==null) || 
             (this.CALLBACK!=null &&
              this.CALLBACK.equals(other.getCALLBACK()))) &&
            ((this.DATE==null && other.getDATE()==null) || 
             (this.DATE!=null &&
              this.DATE.equals(other.getDATE()))) &&
            ((this.MSG==null && other.getMSG()==null) || 
             (this.MSG!=null &&
              this.MSG.equals(other.getMSG())));
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
        if (getID() != null) {
            _hashCode += getID().hashCode();
        }
        if (getPHONE() != null) {
            _hashCode += getPHONE().hashCode();
        }
        if (getCALLBACK() != null) {
            _hashCode += getCALLBACK().hashCode();
        }
        if (getDATE() != null) {
            _hashCode += getDATE().hashCode();
        }
        if (getMSG() != null) {
            _hashCode += getMSG().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DT_SHR0130_EAIT_PARAM.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn://shr.cjfreshway.co.kr/COMM", ">DT_SHR0130_EAI>T_PARAM"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ID");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("PHONE");
        elemField.setXmlName(new javax.xml.namespace.QName("", "PHONE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CALLBACK");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CALLBACK"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("DATE");
        elemField.setXmlName(new javax.xml.namespace.QName("", "DATE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("MSG");
        elemField.setXmlName(new javax.xml.namespace.QName("", "MSG"));
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
