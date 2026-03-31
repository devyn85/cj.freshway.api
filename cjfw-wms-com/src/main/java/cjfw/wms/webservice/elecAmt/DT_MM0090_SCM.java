package cjfw.wms.webservice.elecAmt;

/**
 * DT_MM0090_SCM.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

public class DT_MM0090_SCM  implements java.io.Serializable {
    private java.lang.String XROWS;

    private java.lang.String XSYS;

    private java.lang.String XDATS;

    private java.lang.String XTIMS;

    private DT_MM0090_SCMIF_ST_STOCKAMT[] IF_ST_STOCKAMT;

    public DT_MM0090_SCM() {
    }

    public DT_MM0090_SCM(
           java.lang.String XROWS,
           java.lang.String XSYS,
           java.lang.String XDATS,
           java.lang.String XTIMS,
           DT_MM0090_SCMIF_ST_STOCKAMT[] IF_ST_STOCKAMT) {
           this.XROWS = XROWS;
           this.XSYS = XSYS;
           this.XDATS = XDATS;
           this.XTIMS = XTIMS;
           this.IF_ST_STOCKAMT = IF_ST_STOCKAMT;
    }


    /**
     * Gets the XROWS value for this DT_MM0090_SCM.
     * 
     * @return XROWS
     */
    public java.lang.String getXROWS() {
        return XROWS;
    }


    /**
     * Sets the XROWS value for this DT_MM0090_SCM.
     * 
     * @param XROWS
     */
    public void setXROWS(java.lang.String XROWS) {
        this.XROWS = XROWS;
    }


    /**
     * Gets the XSYS value for this DT_MM0090_SCM.
     * 
     * @return XSYS
     */
    public java.lang.String getXSYS() {
        return XSYS;
    }


    /**
     * Sets the XSYS value for this DT_MM0090_SCM.
     * 
     * @param XSYS
     */
    public void setXSYS(java.lang.String XSYS) {
        this.XSYS = XSYS;
    }


    /**
     * Gets the XDATS value for this DT_MM0090_SCM.
     * 
     * @return XDATS
     */
    public java.lang.String getXDATS() {
        return XDATS;
    }


    /**
     * Sets the XDATS value for this DT_MM0090_SCM.
     * 
     * @param XDATS
     */
    public void setXDATS(java.lang.String XDATS) {
        this.XDATS = XDATS;
    }


    /**
     * Gets the XTIMS value for this DT_MM0090_SCM.
     * 
     * @return XTIMS
     */
    public java.lang.String getXTIMS() {
        return XTIMS;
    }


    /**
     * Sets the XTIMS value for this DT_MM0090_SCM.
     * 
     * @param XTIMS
     */
    public void setXTIMS(java.lang.String XTIMS) {
        this.XTIMS = XTIMS;
    }


    /**
     * Gets the IF_ST_STOCKAMT value for this DT_MM0090_SCM.
     * 
     * @return IF_ST_STOCKAMT
     */
    public DT_MM0090_SCMIF_ST_STOCKAMT[] getIF_ST_STOCKAMT() {
        return IF_ST_STOCKAMT;
    }


    /**
     * Sets the IF_ST_STOCKAMT value for this DT_MM0090_SCM.
     * 
     * @param IF_ST_STOCKAMT
     */
    public void setIF_ST_STOCKAMT(DT_MM0090_SCMIF_ST_STOCKAMT[] IF_ST_STOCKAMT) {
        this.IF_ST_STOCKAMT = IF_ST_STOCKAMT;
    }

    public DT_MM0090_SCMIF_ST_STOCKAMT getIF_ST_STOCKAMT(int i) {
        return this.IF_ST_STOCKAMT[i];
    }

    public void setIF_ST_STOCKAMT(int i, DT_MM0090_SCMIF_ST_STOCKAMT _value) {
        this.IF_ST_STOCKAMT[i] = _value;
    }

    private static java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DT_MM0090_SCM)) return false;
        DT_MM0090_SCM other = (DT_MM0090_SCM) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            //return (__equalsCalc == obj);
            return (__equalsCalc.equals(obj));
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.XROWS==null && other.getXROWS()==null) || 
             (this.XROWS!=null &&
              this.XROWS.equals(other.getXROWS()))) &&
            ((this.XSYS==null && other.getXSYS()==null) || 
             (this.XSYS!=null &&
              this.XSYS.equals(other.getXSYS()))) &&
            ((this.XDATS==null && other.getXDATS()==null) || 
             (this.XDATS!=null &&
              this.XDATS.equals(other.getXDATS()))) &&
            ((this.XTIMS==null && other.getXTIMS()==null) || 
             (this.XTIMS!=null &&
              this.XTIMS.equals(other.getXTIMS()))) &&
            ((this.IF_ST_STOCKAMT==null && other.getIF_ST_STOCKAMT()==null) || 
             (this.IF_ST_STOCKAMT!=null &&
              java.util.Arrays.equals(this.IF_ST_STOCKAMT, other.getIF_ST_STOCKAMT())));
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
        if (getXROWS() != null) {
            _hashCode += getXROWS().hashCode();
        }
        if (getXSYS() != null) {
            _hashCode += getXSYS().hashCode();
        }
        if (getXDATS() != null) {
            _hashCode += getXDATS().hashCode();
        }
        if (getXTIMS() != null) {
            _hashCode += getXTIMS().hashCode();
        }
        if (getIF_ST_STOCKAMT() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getIF_ST_STOCKAMT());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getIF_ST_STOCKAMT(), i);
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
        new org.apache.axis.description.TypeDesc(DT_MM0090_SCM.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn://mm.cjfreshway.co.kr/SCM", "DT_MM0090_SCM"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("XROWS");
        elemField.setXmlName(new javax.xml.namespace.QName("", "XROWS"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("XSYS");
        elemField.setXmlName(new javax.xml.namespace.QName("", "XSYS"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("XDATS");
        elemField.setXmlName(new javax.xml.namespace.QName("", "XDATS"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("XTIMS");
        elemField.setXmlName(new javax.xml.namespace.QName("", "XTIMS"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("IF_ST_STOCKAMT");
        elemField.setXmlName(new javax.xml.namespace.QName("", "IF_ST_STOCKAMT"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn://mm.cjfreshway.co.kr/SCM", ">DT_MM0090_SCM>IF_ST_STOCKAMT"));
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
