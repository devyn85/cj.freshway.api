package cjfw.wms.webservice.scm0560;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>DT_SCM0560_SCM_response complex type�� ���� Java Ŭ�����Դϴ�.
 * 
 * <p>���� ��Ű�� ������ �� Ŭ������ ���ԵǴ� �ʿ��� �������� �����մϴ�.
 * 
 * <pre>{@code
 * <complexType name="DT_SCM0560_SCM_response">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="T_RETURN" maxOccurs="unbounded" minOccurs="0">
 *           <complexType>
 *             <complexContent>
 *               <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 <sequence>
 *                   <element name="SN" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   <element name="STATUS" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   <element name="INVOICE_FLAG" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   <element name="HBL_NO" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   <element name="DEPT" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   <element name="KEY_NO" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   <element name="LOV_ITEM" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   <element name="BUSINESS_TYPE_NAME" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   <element name="REVISION_NO" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   <element name="ISSUE_DATE" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   <element name="SHIPPING_DATE" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   <element name="EXPIRY_DATE" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   <element name="ARRIVAL_DATE" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   <element name="TRANSPORT" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   <element name="ATTRIBUTES4" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   <element name="SUPPLIER_NAME" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   <element name="PRICE_TERMS_CODE" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   <element name="PRICE_TERMS_NAME" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   <element name="PAYMENT_TYPE_CODE" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   <element name="PAYMENT_NAME" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   <element name="TOTAL_AMOUNT_UNIT" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   <element name="TOTAL_AMOUNT" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   <element name="REG_ID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   <element name="REG_DATE" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   <element name="MOD_ID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   <element name="MOD_DATE" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   <element name="ERP_PO_ID" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
@XmlType(name = "DT_SCM0560_SCM_response", propOrder = {
    "treturn"
})
public class DTSCM0560SCMResponse {

    @XmlElement(name = "T_RETURN")
    protected List<DTSCM0560SCMResponse.TRETURN> treturn;

    /**
     * Gets the value of the treturn property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the treturn property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTRETURN().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DTSCM0560SCMResponse.TRETURN }
     * 
     * 
     * @return
     *     The value of the treturn property.
     */
    public List<DTSCM0560SCMResponse.TRETURN> getTRETURN() {
        if (treturn == null) {
            treturn = new ArrayList<>();
        }
        return this.treturn;
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
     *         <element name="SN" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         <element name="STATUS" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         <element name="INVOICE_FLAG" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         <element name="HBL_NO" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         <element name="DEPT" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         <element name="KEY_NO" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         <element name="LOV_ITEM" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         <element name="BUSINESS_TYPE_NAME" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         <element name="REVISION_NO" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         <element name="ISSUE_DATE" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         <element name="SHIPPING_DATE" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         <element name="EXPIRY_DATE" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         <element name="ARRIVAL_DATE" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         <element name="TRANSPORT" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         <element name="ATTRIBUTES4" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         <element name="SUPPLIER_NAME" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         <element name="PRICE_TERMS_CODE" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         <element name="PRICE_TERMS_NAME" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         <element name="PAYMENT_TYPE_CODE" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         <element name="PAYMENT_NAME" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         <element name="TOTAL_AMOUNT_UNIT" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         <element name="TOTAL_AMOUNT" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         <element name="REG_ID" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         <element name="REG_DATE" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         <element name="MOD_ID" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         <element name="MOD_DATE" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         <element name="ERP_PO_ID" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
        "sn",
        "status",
        "invoiceflag",
        "hblno",
        "dept",
        "keyno",
        "lovitem",
        "businesstypename",
        "revisionno",
        "issuedate",
        "shippingdate",
        "expirydate",
        "arrivaldate",
        "transport",
        "attributes4",
        "suppliername",
        "pricetermscode",
        "pricetermsname",
        "paymenttypecode",
        "paymentname",
        "totalamountunit",
        "totalamount",
        "regid",
        "regdate",
        "modid",
        "moddate",
        "erppoid"
    })
    public static class TRETURN {

        @XmlElement(name = "SN", required = true)
        protected String sn;
        @XmlElement(name = "STATUS", required = true)
        protected String status;
        @XmlElement(name = "INVOICE_FLAG", required = true)
        protected String invoiceflag;
        @XmlElement(name = "HBL_NO", required = true)
        protected String hblno;
        @XmlElement(name = "DEPT", required = true)
        protected String dept;
        @XmlElement(name = "KEY_NO", required = true)
        protected String keyno;
        @XmlElement(name = "LOV_ITEM", required = true)
        protected String lovitem;
        @XmlElement(name = "BUSINESS_TYPE_NAME", required = true)
        protected String businesstypename;
        @XmlElement(name = "REVISION_NO", required = true)
        protected String revisionno;
        @XmlElement(name = "ISSUE_DATE", required = true)
        protected String issuedate;
        @XmlElement(name = "SHIPPING_DATE", required = true)
        protected String shippingdate;
        @XmlElement(name = "EXPIRY_DATE", required = true)
        protected String expirydate;
        @XmlElement(name = "ARRIVAL_DATE", required = true)
        protected String arrivaldate;
        @XmlElement(name = "TRANSPORT", required = true)
        protected String transport;
        @XmlElement(name = "ATTRIBUTES4", required = true)
        protected String attributes4;
        @XmlElement(name = "SUPPLIER_NAME", required = true)
        protected String suppliername;
        @XmlElement(name = "PRICE_TERMS_CODE", required = true)
        protected String pricetermscode;
        @XmlElement(name = "PRICE_TERMS_NAME", required = true)
        protected String pricetermsname;
        @XmlElement(name = "PAYMENT_TYPE_CODE", required = true)
        protected String paymenttypecode;
        @XmlElement(name = "PAYMENT_NAME", required = true)
        protected String paymentname;
        @XmlElement(name = "TOTAL_AMOUNT_UNIT", required = true)
        protected String totalamountunit;
        @XmlElement(name = "TOTAL_AMOUNT", required = true)
        protected String totalamount;
        @XmlElement(name = "REG_ID", required = true)
        protected String regid;
        @XmlElement(name = "REG_DATE", required = true)
        protected String regdate;
        @XmlElement(name = "MOD_ID", required = true)
        protected String modid;
        @XmlElement(name = "MOD_DATE", required = true)
        protected String moddate;
        @XmlElement(name = "ERP_PO_ID", required = true)
        protected String erppoid;

        /**
         * sn �Ӽ��� ���� �����ɴϴ�.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getSN() {
            return sn;
        }

        /**
         * sn �Ӽ��� ���� �����մϴ�.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setSN(String value) {
            this.sn = value;
        }

        /**
         * status �Ӽ��� ���� �����ɴϴ�.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getSTATUS() {
            return status;
        }

        /**
         * status �Ӽ��� ���� �����մϴ�.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setSTATUS(String value) {
            this.status = value;
        }

        /**
         * invoiceflag �Ӽ��� ���� �����ɴϴ�.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getINVOICEFLAG() {
            return invoiceflag;
        }

        /**
         * invoiceflag �Ӽ��� ���� �����մϴ�.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setINVOICEFLAG(String value) {
            this.invoiceflag = value;
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
         * dept �Ӽ��� ���� �����ɴϴ�.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getDEPT() {
            return dept;
        }

        /**
         * dept �Ӽ��� ���� �����մϴ�.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setDEPT(String value) {
            this.dept = value;
        }

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
         * lovitem �Ӽ��� ���� �����ɴϴ�.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getLOVITEM() {
            return lovitem;
        }

        /**
         * lovitem �Ӽ��� ���� �����մϴ�.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setLOVITEM(String value) {
            this.lovitem = value;
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
         * revisionno �Ӽ��� ���� �����ɴϴ�.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getREVISIONNO() {
            return revisionno;
        }

        /**
         * revisionno �Ӽ��� ���� �����մϴ�.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setREVISIONNO(String value) {
            this.revisionno = value;
        }

        /**
         * issuedate �Ӽ��� ���� �����ɴϴ�.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getISSUEDATE() {
            return issuedate;
        }

        /**
         * issuedate �Ӽ��� ���� �����մϴ�.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setISSUEDATE(String value) {
            this.issuedate = value;
        }

        /**
         * shippingdate �Ӽ��� ���� �����ɴϴ�.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getSHIPPINGDATE() {
            return shippingdate;
        }

        /**
         * shippingdate �Ӽ��� ���� �����մϴ�.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setSHIPPINGDATE(String value) {
            this.shippingdate = value;
        }

        /**
         * expirydate �Ӽ��� ���� �����ɴϴ�.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getEXPIRYDATE() {
            return expirydate;
        }

        /**
         * expirydate �Ӽ��� ���� �����մϴ�.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setEXPIRYDATE(String value) {
            this.expirydate = value;
        }

        /**
         * arrivaldate �Ӽ��� ���� �����ɴϴ�.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getARRIVALDATE() {
            return arrivaldate;
        }

        /**
         * arrivaldate �Ӽ��� ���� �����մϴ�.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setARRIVALDATE(String value) {
            this.arrivaldate = value;
        }

        /**
         * transport �Ӽ��� ���� �����ɴϴ�.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getTRANSPORT() {
            return transport;
        }

        /**
         * transport �Ӽ��� ���� �����մϴ�.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setTRANSPORT(String value) {
            this.transport = value;
        }

        /**
         * attributes4 �Ӽ��� ���� �����ɴϴ�.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getATTRIBUTES4() {
            return attributes4;
        }

        /**
         * attributes4 �Ӽ��� ���� �����մϴ�.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setATTRIBUTES4(String value) {
            this.attributes4 = value;
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
         * pricetermscode �Ӽ��� ���� �����ɴϴ�.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getPRICETERMSCODE() {
            return pricetermscode;
        }

        /**
         * pricetermscode �Ӽ��� ���� �����մϴ�.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setPRICETERMSCODE(String value) {
            this.pricetermscode = value;
        }

        /**
         * pricetermsname �Ӽ��� ���� �����ɴϴ�.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getPRICETERMSNAME() {
            return pricetermsname;
        }

        /**
         * pricetermsname �Ӽ��� ���� �����մϴ�.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setPRICETERMSNAME(String value) {
            this.pricetermsname = value;
        }

        /**
         * paymenttypecode �Ӽ��� ���� �����ɴϴ�.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getPAYMENTTYPECODE() {
            return paymenttypecode;
        }

        /**
         * paymenttypecode �Ӽ��� ���� �����մϴ�.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setPAYMENTTYPECODE(String value) {
            this.paymenttypecode = value;
        }

        /**
         * paymentname �Ӽ��� ���� �����ɴϴ�.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getPAYMENTNAME() {
            return paymentname;
        }

        /**
         * paymentname �Ӽ��� ���� �����մϴ�.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setPAYMENTNAME(String value) {
            this.paymentname = value;
        }

        /**
         * totalamountunit �Ӽ��� ���� �����ɴϴ�.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getTOTALAMOUNTUNIT() {
            return totalamountunit;
        }

        /**
         * totalamountunit �Ӽ��� ���� �����մϴ�.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setTOTALAMOUNTUNIT(String value) {
            this.totalamountunit = value;
        }

        /**
         * totalamount �Ӽ��� ���� �����ɴϴ�.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getTOTALAMOUNT() {
            return totalamount;
        }

        /**
         * totalamount �Ӽ��� ���� �����մϴ�.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setTOTALAMOUNT(String value) {
            this.totalamount = value;
        }

        /**
         * regid �Ӽ��� ���� �����ɴϴ�.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getREGID() {
            return regid;
        }

        /**
         * regid �Ӽ��� ���� �����մϴ�.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setREGID(String value) {
            this.regid = value;
        }

        /**
         * regdate �Ӽ��� ���� �����ɴϴ�.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getREGDATE() {
            return regdate;
        }

        /**
         * regdate �Ӽ��� ���� �����մϴ�.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setREGDATE(String value) {
            this.regdate = value;
        }

        /**
         * modid �Ӽ��� ���� �����ɴϴ�.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getMODID() {
            return modid;
        }

        /**
         * modid �Ӽ��� ���� �����մϴ�.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setMODID(String value) {
            this.modid = value;
        }

        /**
         * moddate �Ӽ��� ���� �����ɴϴ�.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getMODDATE() {
            return moddate;
        }

        /**
         * moddate �Ӽ��� ���� �����մϴ�.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setMODDATE(String value) {
            this.moddate = value;
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

    }

}
