package cjfw.wms.webservice.elecTax;

/**
 * DT_SCM0500_SCM.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

public class DT_SCM0500_SCM  implements java.io.Serializable {
    private java.lang.String XROWS;

    private java.lang.String XSYS;

    private java.lang.String XDATS;

    private java.lang.String XTIMS;

    private DT_SCM0500_SCMIB_EXPENSE[] IB_EXPENSE;

    public DT_SCM0500_SCM() {
    }

    public DT_SCM0500_SCM(
           java.lang.String XROWS,
           java.lang.String XSYS,
           java.lang.String XDATS,
           java.lang.String XTIMS,
           DT_SCM0500_SCMIB_EXPENSE[] IB_EXPENSE) {
           this.XROWS = XROWS;
           this.XSYS = XSYS;
           this.XDATS = XDATS;
           this.XTIMS = XTIMS;
           this.IB_EXPENSE = IB_EXPENSE;
    }


    /**
     * Gets the XROWS value for this DT_SCM0500_SCM.
     * 
     * @return XROWS
     */
    public java.lang.String getXROWS() {
        return XROWS;
    }


    /**
     * Sets the XROWS value for this DT_SCM0500_SCM.
     * 
     * @param XROWS
     */
    public void setXROWS(java.lang.String XROWS) {
        this.XROWS = XROWS;
    }


    /**
     * Gets the XSYS value for this DT_SCM0500_SCM.
     * 
     * @return XSYS
     */
    public java.lang.String getXSYS() {
        return XSYS;
    }


    /**
     * Sets the XSYS value for this DT_SCM0500_SCM.
     * 
     * @param XSYS
     */
    public void setXSYS(java.lang.String XSYS) {
        this.XSYS = XSYS;
    }


    /**
     * Gets the XDATS value for this DT_SCM0500_SCM.
     * 
     * @return XDATS
     */
    public java.lang.String getXDATS() {
        return XDATS;
    }


    /**
     * Sets the XDATS value for this DT_SCM0500_SCM.
     * 
     * @param XDATS
     */
    public void setXDATS(java.lang.String XDATS) {
        this.XDATS = XDATS;
    }


    /**
     * Gets the XTIMS value for this DT_SCM0500_SCM.
     * 
     * @return XTIMS
     */
    public java.lang.String getXTIMS() {
        return XTIMS;
    }


    /**
     * Sets the XTIMS value for this DT_SCM0500_SCM.
     * 
     * @param XTIMS
     */
    public void setXTIMS(java.lang.String XTIMS) {
        this.XTIMS = XTIMS;
    }


    /**
     * Gets the IB_EXPENSE value for this DT_SCM0500_SCM.
     * 
     * @return IB_EXPENSE
     */
    public DT_SCM0500_SCMIB_EXPENSE[] getIB_EXPENSE() {
        return IB_EXPENSE;
    }


    /**
     * Sets the IB_EXPENSE value for this DT_SCM0500_SCM.
     * 
     * @param IB_EXPENSE
     */
    public void setIB_EXPENSE(DT_SCM0500_SCMIB_EXPENSE[] IB_EXPENSE) {
        this.IB_EXPENSE = IB_EXPENSE;
    }

    public DT_SCM0500_SCMIB_EXPENSE getIB_EXPENSE(int i) {
        return this.IB_EXPENSE[i];
    }

    public void setIB_EXPENSE(int i, DT_SCM0500_SCMIB_EXPENSE _value) {
        this.IB_EXPENSE[i] = _value;
    }

    private static java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DT_SCM0500_SCM)) return false;
        DT_SCM0500_SCM other = (DT_SCM0500_SCM) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
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
            ((this.IB_EXPENSE==null && other.getIB_EXPENSE()==null) || 
             (this.IB_EXPENSE!=null &&
              java.util.Arrays.equals(this.IB_EXPENSE, other.getIB_EXPENSE())));
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
        if (getIB_EXPENSE() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getIB_EXPENSE());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getIB_EXPENSE(), i);
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
        new org.apache.axis.description.TypeDesc(DT_SCM0500_SCM.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn://scm.cjfreshway.co.kr/FI", "DT_SCM0500_SCM"));
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
        elemField.setFieldName("IB_EXPENSE");
        elemField.setXmlName(new javax.xml.namespace.QName("", "IB_EXPENSE"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn://scm.cjfreshway.co.kr/FI", ">DT_SCM0500_SCM>IB_EXPENSE"));
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
