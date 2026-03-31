package cjfw.wms.webservice.scm0560;

import java.util.ArrayList;
import java.util.List;

import cjfw.wms.webservice.scm0550.DTSCM0550SCM;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>DT_SCM0560_SCM complex type�� ���� Java Ŭ�����Դϴ�.
 * 
 * <p>���� ��Ű�� ������ �� Ŭ������ ���ԵǴ� �ʿ��� �������� �����մϴ�.
 * 
 * <pre>{@code
 * <complexType name="DT_SCM0560_SCM">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="XROWS" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         <element name="XSYS" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         <element name="XDATS" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         <element name="XTIMS" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         <element name="T_PARAM" maxOccurs="unbounded" minOccurs="0">
 *           <complexType>
 *             <complexContent>
 *               <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 <sequence>
 *                   <element name="KEY_NO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   <element name="SUPPLIER_NAME" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   <element name="BUSINESS_TYPE_NAME" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   <element name="ERP_PO_ID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   <element name="HBL_NO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   <element name="ISSUE_DATE_FR" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   <element name="ISSUE_DATE_TO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   <element name="SHIPPING_DATE_FR" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   <element name="SHIPPING_DATE_TO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   <element name="EXPIRY_DATE_FR" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   <element name="EXPIRY_DATE_TO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   <element name="ARRIVAL_DATE_FR" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   <element name="ARRIVAL_DATE_TO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   <element name="REG_DATE_FR" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   <element name="REG_DATE_TO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   <element name="MOD_DATE_FR" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   <element name="MOD_DATE_TO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                 </sequence>
 *               </restriction>
 *             </complexContent>
 *           </complexType>
 *         </element>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DT_SCM0560_SCM", propOrder = {
    "xrows",
    "xsys",
    "xdats",
    "xtims",
    "tparam"
})
public class DTSCM0560SCM {

    @XmlElement(name = "XROWS", required = true)
    protected String xrows;
    @XmlElement(name = "XSYS", required = true)
    protected String xsys;
    @XmlElement(name = "XDATS", required = true)
    protected String xdats;
    @XmlElement(name = "XTIMS", required = true)
    protected String xtims;
    @XmlElement(name = "T_PARAM")
    protected List<DTSCM0560SCM.TPARAM> tparam;

    /**
     * xrows �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getXROWS() {
        return xrows;
    }

    /**
     * xrows �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setXROWS(String value) {
        this.xrows = value;
    }

    /**
     * xsys �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getXSYS() {
        return xsys;
    }

    /**
     * xsys �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setXSYS(String value) {
        this.xsys = value;
    }

    /**
     * xdats �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getXDATS() {
        return xdats;
    }

    /**
     * xdats �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setXDATS(String value) {
        this.xdats = value;
    }

    /**
     * xtims �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getXTIMS() {
        return xtims;
    }

    /**
     * xtims �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setXTIMS(String value) {
        this.xtims = value;
    }

    /**
     * Gets the value of the tparam property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the tparam property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTPARAM().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DTSCM0560SCM.TPARAM }
     * 
     * 
     * @return
     *     The value of the tparam property.
     */
    public List<DTSCM0560SCM.TPARAM> getTPARAM() {
        if (tparam == null) {
            tparam = new ArrayList<>();
        }
        return this.tparam;
    }
    
    public void setTPARAM(List<DTSCM0560SCM.TPARAM> value) {
        this.tparam = value;
    }


    /**
     * <p>anonymous complex type�� ���� Java Ŭ�����Դϴ�.
     * 
     * <p>���� ��Ű�� ������ �� Ŭ������ ���ԵǴ� �ʿ��� �������� �����մϴ�.
     * 
     * <pre>{@code
     * <complexType>
     *   <complexContent>
     *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       <sequence>
     *         <element name="KEY_NO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         <element name="SUPPLIER_NAME" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         <element name="BUSINESS_TYPE_NAME" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         <element name="ERP_PO_ID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         <element name="HBL_NO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         <element name="ISSUE_DATE_FR" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         <element name="ISSUE_DATE_TO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         <element name="SHIPPING_DATE_FR" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         <element name="SHIPPING_DATE_TO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         <element name="EXPIRY_DATE_FR" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         <element name="EXPIRY_DATE_TO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         <element name="ARRIVAL_DATE_FR" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         <element name="ARRIVAL_DATE_TO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         <element name="REG_DATE_FR" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         <element name="REG_DATE_TO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         <element name="MOD_DATE_FR" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         <element name="MOD_DATE_TO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *       </sequence>
     *     </restriction>
     *   </complexContent>
     * </complexType>
     * }</pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "keyno",
        "suppliername",
        "businesstypename",
        "erppoid",
        "hblno",
        "issuedatefr",
        "issuedateto",
        "shippingdatefr",
        "shippingdateto",
        "expirydatefr",
        "expirydateto",
        "arrivaldatefr",
        "arrivaldateto",
        "regdatefr",
        "regdateto",
        "moddatefr",
        "moddateto"
    })
    public static class TPARAM {

        @XmlElement(name = "KEY_NO")
        protected String keyno;
        @XmlElement(name = "SUPPLIER_NAME")
        protected String suppliername;
        @XmlElement(name = "BUSINESS_TYPE_NAME")
        protected String businesstypename;
        @XmlElement(name = "ERP_PO_ID")
        protected String erppoid;
        @XmlElement(name = "HBL_NO")
        protected String hblno;
        @XmlElement(name = "ISSUE_DATE_FR")
        protected String issuedatefr;
        @XmlElement(name = "ISSUE_DATE_TO")
        protected String issuedateto;
        @XmlElement(name = "SHIPPING_DATE_FR")
        protected String shippingdatefr;
        @XmlElement(name = "SHIPPING_DATE_TO")
        protected String shippingdateto;
        @XmlElement(name = "EXPIRY_DATE_FR")
        protected String expirydatefr;
        @XmlElement(name = "EXPIRY_DATE_TO")
        protected String expirydateto;
        @XmlElement(name = "ARRIVAL_DATE_FR")
        protected String arrivaldatefr;
        @XmlElement(name = "ARRIVAL_DATE_TO")
        protected String arrivaldateto;
        @XmlElement(name = "REG_DATE_FR")
        protected String regdatefr;
        @XmlElement(name = "REG_DATE_TO")
        protected String regdateto;
        @XmlElement(name = "MOD_DATE_FR")
        protected String moddatefr;
        @XmlElement(name = "MOD_DATE_TO")
        protected String moddateto;

        /**
         * keyno �Ӽ��� ���� �����ɴϴ�.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getKEYNO() {
            return keyno;
        }

        /**
         * keyno �Ӽ��� ���� �����մϴ�.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setKEYNO(String value) {
            this.keyno = value;
        }

        /**
         * suppliername �Ӽ��� ���� �����ɴϴ�.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getSUPPLIERNAME() {
            return suppliername;
        }

        /**
         * suppliername �Ӽ��� ���� �����մϴ�.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setSUPPLIERNAME(String value) {
            this.suppliername = value;
        }

        /**
         * businesstypename �Ӽ��� ���� �����ɴϴ�.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getBUSINESSTYPENAME() {
            return businesstypename;
        }

        /**
         * businesstypename �Ӽ��� ���� �����մϴ�.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setBUSINESSTYPENAME(String value) {
            this.businesstypename = value;
        }

        /**
         * erppoid �Ӽ��� ���� �����ɴϴ�.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getERPPOID() {
            return erppoid;
        }

        /**
         * erppoid �Ӽ��� ���� �����մϴ�.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setERPPOID(String value) {
            this.erppoid = value;
        }

        /**
         * hblno �Ӽ��� ���� �����ɴϴ�.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getHBLNO() {
            return hblno;
        }

        /**
         * hblno �Ӽ��� ���� �����մϴ�.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setHBLNO(String value) {
            this.hblno = value;
        }

        /**
         * issuedatefr �Ӽ��� ���� �����ɴϴ�.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getISSUEDATEFR() {
            return issuedatefr;
        }

        /**
         * issuedatefr �Ӽ��� ���� �����մϴ�.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setISSUEDATEFR(String value) {
            this.issuedatefr = value;
        }

        /**
         * issuedateto �Ӽ��� ���� �����ɴϴ�.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getISSUEDATETO() {
            return issuedateto;
        }

        /**
         * issuedateto �Ӽ��� ���� �����մϴ�.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setISSUEDATETO(String value) {
            this.issuedateto = value;
        }

        /**
         * shippingdatefr �Ӽ��� ���� �����ɴϴ�.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getSHIPPINGDATEFR() {
            return shippingdatefr;
        }

        /**
         * shippingdatefr �Ӽ��� ���� �����մϴ�.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setSHIPPINGDATEFR(String value) {
            this.shippingdatefr = value;
        }

        /**
         * shippingdateto �Ӽ��� ���� �����ɴϴ�.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getSHIPPINGDATETO() {
            return shippingdateto;
        }

        /**
         * shippingdateto �Ӽ��� ���� �����մϴ�.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setSHIPPINGDATETO(String value) {
            this.shippingdateto = value;
        }

        /**
         * expirydatefr �Ӽ��� ���� �����ɴϴ�.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getEXPIRYDATEFR() {
            return expirydatefr;
        }

        /**
         * expirydatefr �Ӽ��� ���� �����մϴ�.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setEXPIRYDATEFR(String value) {
            this.expirydatefr = value;
        }

        /**
         * expirydateto �Ӽ��� ���� �����ɴϴ�.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getEXPIRYDATETO() {
            return expirydateto;
        }

        /**
         * expirydateto �Ӽ��� ���� �����մϴ�.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setEXPIRYDATETO(String value) {
            this.expirydateto = value;
        }

        /**
         * arrivaldatefr �Ӽ��� ���� �����ɴϴ�.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getARRIVALDATEFR() {
            return arrivaldatefr;
        }

        /**
         * arrivaldatefr �Ӽ��� ���� �����մϴ�.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setARRIVALDATEFR(String value) {
            this.arrivaldatefr = value;
        }

        /**
         * arrivaldateto �Ӽ��� ���� �����ɴϴ�.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getARRIVALDATETO() {
            return arrivaldateto;
        }

        /**
         * arrivaldateto �Ӽ��� ���� �����մϴ�.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setARRIVALDATETO(String value) {
            this.arrivaldateto = value;
        }

        /**
         * regdatefr �Ӽ��� ���� �����ɴϴ�.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getREGDATEFR() {
            return regdatefr;
        }

        /**
         * regdatefr �Ӽ��� ���� �����մϴ�.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setREGDATEFR(String value) {
            this.regdatefr = value;
        }

        /**
         * regdateto �Ӽ��� ���� �����ɴϴ�.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getREGDATETO() {
            return regdateto;
        }

        /**
         * regdateto �Ӽ��� ���� �����մϴ�.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setREGDATETO(String value) {
            this.regdateto = value;
        }

        /**
         * moddatefr �Ӽ��� ���� �����ɴϴ�.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getMODDATEFR() {
            return moddatefr;
        }

        /**
         * moddatefr �Ӽ��� ���� �����մϴ�.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setMODDATEFR(String value) {
            this.moddatefr = value;
        }

        /**
         * moddateto �Ӽ��� ���� �����ɴϴ�.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getMODDATETO() {
            return moddateto;
        }

        /**
         * moddateto �Ӽ��� ���� �����մϴ�.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setMODDATETO(String value) {
            this.moddateto = value;
        }

    }

}
