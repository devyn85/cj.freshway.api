package cjfw.wms.webservice.elecTax;

/**
 * DT_SCM0500_SCMIB_EXPENSE.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

public class DT_SCM0500_SCMIB_EXPENSE  implements java.io.Serializable {
    private java.lang.String STORERKEY;

    private java.lang.String BUPLA;

    private java.lang.String ISSUEDATE_FROM;

    private java.lang.String ISSUEDATE_TO;

    private java.lang.String CUSTTYPE;

    private java.lang.String VATNO;

    private java.lang.String CUSTNAME;

    private java.lang.String EMAIL1;

    private java.lang.String DOC_FLAG;

    public DT_SCM0500_SCMIB_EXPENSE() {
    }

    public DT_SCM0500_SCMIB_EXPENSE(
           java.lang.String STORERKEY,
           java.lang.String BUPLA,
           java.lang.String ISSUEDATE_FROM,
           java.lang.String ISSUEDATE_TO,
           java.lang.String CUSTTYPE,
           java.lang.String VATNO,
           java.lang.String CUSTNAME,
           java.lang.String EMAIL1,
           java.lang.String DOC_FLAG) {
           this.STORERKEY = STORERKEY;
           this.BUPLA = BUPLA;
           this.ISSUEDATE_FROM = ISSUEDATE_FROM;
           this.ISSUEDATE_TO = ISSUEDATE_TO;
           this.CUSTTYPE = CUSTTYPE;
           this.VATNO = VATNO;
           this.CUSTNAME = CUSTNAME;
           this.EMAIL1 = EMAIL1;
           this.DOC_FLAG = DOC_FLAG;
    }


    /**
     * Gets the STORERKEY value for this DT_SCM0500_SCMIB_EXPENSE.
     * 
     * @return STORERKEY
     */
    public java.lang.String getSTORERKEY() {
        return STORERKEY;
    }


    /**
     * Sets the STORERKEY value for this DT_SCM0500_SCMIB_EXPENSE.
     * 
     * @param STORERKEY
     */
    public void setSTORERKEY(java.lang.String STORERKEY) {
        this.STORERKEY = STORERKEY;
    }


    /**
     * Gets the BUPLA value for this DT_SCM0500_SCMIB_EXPENSE.
     * 
     * @return BUPLA
     */
    public java.lang.String getBUPLA() {
        return BUPLA;
    }


    /**
     * Sets the BUPLA value for this DT_SCM0500_SCMIB_EXPENSE.
     * 
     * @param BUPLA
     */
    public void setBUPLA(java.lang.String BUPLA) {
        this.BUPLA = BUPLA;
    }


    /**
     * Gets the ISSUEDATE_FROM value for this DT_SCM0500_SCMIB_EXPENSE.
     * 
     * @return ISSUEDATE_FROM
     */
    public java.lang.String getISSUEDATE_FROM() {
        return ISSUEDATE_FROM;
    }


    /**
     * Sets the ISSUEDATE_FROM value for this DT_SCM0500_SCMIB_EXPENSE.
     * 
     * @param ISSUEDATE_FROM
     */
    public void setISSUEDATE_FROM(java.lang.String ISSUEDATE_FROM) {
        this.ISSUEDATE_FROM = ISSUEDATE_FROM;
    }


    /**
     * Gets the ISSUEDATE_TO value for this DT_SCM0500_SCMIB_EXPENSE.
     * 
     * @return ISSUEDATE_TO
     */
    public java.lang.String getISSUEDATE_TO() {
        return ISSUEDATE_TO;
    }


    /**
     * Sets the ISSUEDATE_TO value for this DT_SCM0500_SCMIB_EXPENSE.
     * 
     * @param ISSUEDATE_TO
     */
    public void setISSUEDATE_TO(java.lang.String ISSUEDATE_TO) {
        this.ISSUEDATE_TO = ISSUEDATE_TO;
    }


    /**
     * Gets the CUSTTYPE value for this DT_SCM0500_SCMIB_EXPENSE.
     * 
     * @return CUSTTYPE
     */
    public java.lang.String getCUSTTYPE() {
        return CUSTTYPE;
    }


    /**
     * Sets the CUSTTYPE value for this DT_SCM0500_SCMIB_EXPENSE.
     * 
     * @param CUSTTYPE
     */
    public void setCUSTTYPE(java.lang.String CUSTTYPE) {
        this.CUSTTYPE = CUSTTYPE;
    }


    /**
     * Gets the VATNO value for this DT_SCM0500_SCMIB_EXPENSE.
     * 
     * @return VATNO
     */
    public java.lang.String getVATNO() {
        return VATNO;
    }


    /**
     * Sets the VATNO value for this DT_SCM0500_SCMIB_EXPENSE.
     * 
     * @param VATNO
     */
    public void setVATNO(java.lang.String VATNO) {
        this.VATNO = VATNO;
    }


    /**
     * Gets the CUSTNAME value for this DT_SCM0500_SCMIB_EXPENSE.
     * 
     * @return CUSTNAME
     */
    public java.lang.String getCUSTNAME() {
        return CUSTNAME;
    }


    /**
     * Sets the CUSTNAME value for this DT_SCM0500_SCMIB_EXPENSE.
     * 
     * @param CUSTNAME
     */
    public void setCUSTNAME(java.lang.String CUSTNAME) {
        this.CUSTNAME = CUSTNAME;
    }


    /**
     * Gets the EMAIL1 value for this DT_SCM0500_SCMIB_EXPENSE.
     * 
     * @return EMAIL1
     */
    public java.lang.String getEMAIL1() {
        return EMAIL1;
    }


    /**
     * Sets the EMAIL1 value for this DT_SCM0500_SCMIB_EXPENSE.
     * 
     * @param EMAIL1
     */
    public void setEMAIL1(java.lang.String EMAIL1) {
        this.EMAIL1 = EMAIL1;
    }


    /**
     * Gets the DOC_FLAG value for this DT_SCM0500_SCMIB_EXPENSE.
     * 
     * @return DOC_FLAG
     */
    public java.lang.String getDOC_FLAG() {
        return DOC_FLAG;
    }


    /**
     * Sets the DOC_FLAG value for this DT_SCM0500_SCMIB_EXPENSE.
     * 
     * @param DOC_FLAG
     */
    public void setDOC_FLAG(java.lang.String DOC_FLAG) {
        this.DOC_FLAG = DOC_FLAG;
    }

    private static java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DT_SCM0500_SCMIB_EXPENSE)) return false;
        DT_SCM0500_SCMIB_EXPENSE other = (DT_SCM0500_SCMIB_EXPENSE) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc.equals(obj));
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.STORERKEY==null && other.getSTORERKEY()==null) || 
             (this.STORERKEY!=null &&
              this.STORERKEY.equals(other.getSTORERKEY()))) &&
            ((this.BUPLA==null && other.getBUPLA()==null) || 
             (this.BUPLA!=null &&
              this.BUPLA.equals(other.getBUPLA()))) &&
            ((this.ISSUEDATE_FROM==null && other.getISSUEDATE_FROM()==null) || 
             (this.ISSUEDATE_FROM!=null &&
              this.ISSUEDATE_FROM.equals(other.getISSUEDATE_FROM()))) &&
            ((this.ISSUEDATE_TO==null && other.getISSUEDATE_TO()==null) || 
             (this.ISSUEDATE_TO!=null &&
              this.ISSUEDATE_TO.equals(other.getISSUEDATE_TO()))) &&
            ((this.CUSTTYPE==null && other.getCUSTTYPE()==null) || 
             (this.CUSTTYPE!=null &&
              this.CUSTTYPE.equals(other.getCUSTTYPE()))) &&
            ((this.VATNO==null && other.getVATNO()==null) || 
             (this.VATNO!=null &&
              this.VATNO.equals(other.getVATNO()))) &&
            ((this.CUSTNAME==null && other.getCUSTNAME()==null) || 
             (this.CUSTNAME!=null &&
              this.CUSTNAME.equals(other.getCUSTNAME()))) &&
            ((this.EMAIL1==null && other.getEMAIL1()==null) || 
             (this.EMAIL1!=null &&
              this.EMAIL1.equals(other.getEMAIL1()))) &&
            ((this.DOC_FLAG==null && other.getDOC_FLAG()==null) || 
             (this.DOC_FLAG!=null &&
              this.DOC_FLAG.equals(other.getDOC_FLAG())));
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
        if (getSTORERKEY() != null) {
            _hashCode += getSTORERKEY().hashCode();
        }
        if (getBUPLA() != null) {
            _hashCode += getBUPLA().hashCode();
        }
        if (getISSUEDATE_FROM() != null) {
            _hashCode += getISSUEDATE_FROM().hashCode();
        }
        if (getISSUEDATE_TO() != null) {
            _hashCode += getISSUEDATE_TO().hashCode();
        }
        if (getCUSTTYPE() != null) {
            _hashCode += getCUSTTYPE().hashCode();
        }
        if (getVATNO() != null) {
            _hashCode += getVATNO().hashCode();
        }
        if (getCUSTNAME() != null) {
            _hashCode += getCUSTNAME().hashCode();
        }
        if (getEMAIL1() != null) {
            _hashCode += getEMAIL1().hashCode();
        }
        if (getDOC_FLAG() != null) {
            _hashCode += getDOC_FLAG().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DT_SCM0500_SCMIB_EXPENSE.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn://scm.cjfreshway.co.kr/FI", ">DT_SCM0500_SCM>IB_EXPENSE"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("STORERKEY");
        elemField.setXmlName(new javax.xml.namespace.QName("", "STORERKEY"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("BUPLA");
        elemField.setXmlName(new javax.xml.namespace.QName("", "BUPLA"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ISSUEDATE_FROM");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ISSUEDATE_FROM"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ISSUEDATE_TO");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ISSUEDATE_TO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CUSTTYPE");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CUSTTYPE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("VATNO");
        elemField.setXmlName(new javax.xml.namespace.QName("", "VATNO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CUSTNAME");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CUSTNAME"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("EMAIL1");
        elemField.setXmlName(new javax.xml.namespace.QName("", "EMAIL1"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("DOC_FLAG");
        elemField.setXmlName(new javax.xml.namespace.QName("", "DOC_FLAG"));
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
