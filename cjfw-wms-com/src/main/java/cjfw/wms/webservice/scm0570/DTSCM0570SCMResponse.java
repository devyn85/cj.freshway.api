package cjfw.wms.webservice.scm0570;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>DT_SCM0570_SCM_response complex type�� ���� Java Ŭ�����Դϴ�.
 * 
 * <p>���� ��Ű�� ������ �� Ŭ������ ���ԵǴ� �ʿ��� �������� �����մϴ�.
 * 
 * <pre>{@code
 * <complexType name="DT_SCM0570_SCM_response">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="T_RETURN" maxOccurs="unbounded" minOccurs="0">
 *           <complexType>
 *             <complexContent>
 *               <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 <sequence>
 *                   <element name="STATUS" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   <element name="LG_FLAG" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   <element name="ATTRIBUTES1" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   <element name="PLACE_OF_RECEIPT_CODE" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   <element name="KEY_NO" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   <element name="HOUSE_BL_NO" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   <element name="MASTER_BL_NO" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   <element name="PO_NO" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   <element name="PUR_PERSON" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   <element name="PAYMENT_TYPE" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   <element name="SHIPPER_CODE" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   <element name="SHIPPER_NAME" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   <element name="TOTAL_AMOUNT" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   <element name="TOTAL_AMOUNT_UNIT" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   <element name="ONBOARD_DATE" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   <element name="ARRIVAL_DATE" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   <element name="ISSUE_DATE" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   <element name="CUSTOMS_FLAG" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   <element name="CARRIER_NAME" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   <element name="SHIPPING_PORT_NAME" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   <element name="ARRIVAL_PORT_NAME" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   <element name="PRICE_TERMS_CODE" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   <element name="PRICE_TERMS_NAME" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   <element name="TRANSPORT_KIND" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   <element name="TOTAL_PACKING" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   <element name="TOTAL_GROSS_WEIGHT" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   <element name="MEASUREMENT" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   <element name="RECEIPT_DATE" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   <element name="PLACE_OF_RECEIPT_NAME" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   <element name="REG_ID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   <element name="REG_DATE" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   <element name="MOD_ID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   <element name="MOD_DATE" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   <element name="SN" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   <element name="TOTAL_QUANTITY" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   <element name="TOTAL_QUANTITY_UNIT" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
@XmlType(name = "DT_SCM0570_SCM_response", propOrder = {
    "treturn"
})
public class DTSCM0570SCMResponse {

    @XmlElement(name = "T_RETURN")
    protected List<DTSCM0570SCMResponse.TRETURN> treturn;

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
     * {@link DTSCM0570SCMResponse.TRETURN }
     * 
     * 
     * @return
     *     The value of the treturn property.
     */
    public List<DTSCM0570SCMResponse.TRETURN> getTRETURN() {
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
     *         <element name="STATUS" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         <element name="LG_FLAG" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         <element name="ATTRIBUTES1" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         <element name="PLACE_OF_RECEIPT_CODE" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         <element name="KEY_NO" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         <element name="HOUSE_BL_NO" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         <element name="MASTER_BL_NO" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         <element name="PO_NO" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         <element name="PUR_PERSON" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         <element name="PAYMENT_TYPE" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         <element name="SHIPPER_CODE" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         <element name="SHIPPER_NAME" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         <element name="TOTAL_AMOUNT" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         <element name="TOTAL_AMOUNT_UNIT" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         <element name="ONBOARD_DATE" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         <element name="ARRIVAL_DATE" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         <element name="ISSUE_DATE" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         <element name="CUSTOMS_FLAG" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         <element name="CARRIER_NAME" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         <element name="SHIPPING_PORT_NAME" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         <element name="ARRIVAL_PORT_NAME" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         <element name="PRICE_TERMS_CODE" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         <element name="PRICE_TERMS_NAME" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         <element name="TRANSPORT_KIND" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         <element name="TOTAL_PACKING" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         <element name="TOTAL_GROSS_WEIGHT" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         <element name="MEASUREMENT" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         <element name="RECEIPT_DATE" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         <element name="PLACE_OF_RECEIPT_NAME" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         <element name="REG_ID" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         <element name="REG_DATE" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         <element name="MOD_ID" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         <element name="MOD_DATE" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         <element name="SN" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         <element name="TOTAL_QUANTITY" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         <element name="TOTAL_QUANTITY_UNIT" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
        "status",
        "lgflag",
        "attributes1",
        "placeofreceiptcode",
        "keyno",
        "houseblno",
        "masterblno",
        "pono",
        "purperson",
        "paymenttype",
        "shippercode",
        "shippername",
        "totalamount",
        "totalamountunit",
        "onboarddate",
        "arrivaldate",
        "issuedate",
        "customsflag",
        "carriername",
        "shippingportname",
        "arrivalportname",
        "pricetermscode",
        "pricetermsname",
        "transportkind",
        "totalpacking",
        "totalgrossweight",
        "measurement",
        "receiptdate",
        "placeofreceiptname",
        "regid",
        "regdate",
        "modid",
        "moddate",
        "sn",
        "totalquantity",
        "totalquantityunit",
        "erppoid"
    })
    public static class TRETURN {

        @XmlElement(name = "STATUS", required = true)
        protected String status;
        @XmlElement(name = "LG_FLAG", required = true)
        protected String lgflag;
        @XmlElement(name = "ATTRIBUTES1", required = true)
        protected String attributes1;
        @XmlElement(name = "PLACE_OF_RECEIPT_CODE", required = true)
        protected String placeofreceiptcode;
        @XmlElement(name = "KEY_NO", required = true)
        protected String keyno;
        @XmlElement(name = "HOUSE_BL_NO", required = true)
        protected String houseblno;
        @XmlElement(name = "MASTER_BL_NO", required = true)
        protected String masterblno;
        @XmlElement(name = "PO_NO", required = true)
        protected String pono;
        @XmlElement(name = "PUR_PERSON", required = true)
        protected String purperson;
        @XmlElement(name = "PAYMENT_TYPE", required = true)
        protected String paymenttype;
        @XmlElement(name = "SHIPPER_CODE", required = true)
        protected String shippercode;
        @XmlElement(name = "SHIPPER_NAME", required = true)
        protected String shippername;
        @XmlElement(name = "TOTAL_AMOUNT", required = true)
        protected String totalamount;
        @XmlElement(name = "TOTAL_AMOUNT_UNIT", required = true)
        protected String totalamountunit;
        @XmlElement(name = "ONBOARD_DATE", required = true)
        protected String onboarddate;
        @XmlElement(name = "ARRIVAL_DATE", required = true)
        protected String arrivaldate;
        @XmlElement(name = "ISSUE_DATE", required = true)
        protected String issuedate;
        @XmlElement(name = "CUSTOMS_FLAG", required = true)
        protected String customsflag;
        @XmlElement(name = "CARRIER_NAME", required = true)
        protected String carriername;
        @XmlElement(name = "SHIPPING_PORT_NAME", required = true)
        protected String shippingportname;
        @XmlElement(name = "ARRIVAL_PORT_NAME", required = true)
        protected String arrivalportname;
        @XmlElement(name = "PRICE_TERMS_CODE", required = true)
        protected String pricetermscode;
        @XmlElement(name = "PRICE_TERMS_NAME", required = true)
        protected String pricetermsname;
        @XmlElement(name = "TRANSPORT_KIND", required = true)
        protected String transportkind;
        @XmlElement(name = "TOTAL_PACKING", required = true)
        protected String totalpacking;
        @XmlElement(name = "TOTAL_GROSS_WEIGHT", required = true)
        protected String totalgrossweight;
        @XmlElement(name = "MEASUREMENT", required = true)
        protected String measurement;
        @XmlElement(name = "RECEIPT_DATE", required = true)
        protected String receiptdate;
        @XmlElement(name = "PLACE_OF_RECEIPT_NAME", required = true)
        protected String placeofreceiptname;
        @XmlElement(name = "REG_ID", required = true)
        protected String regid;
        @XmlElement(name = "REG_DATE", required = true)
        protected String regdate;
        @XmlElement(name = "MOD_ID", required = true)
        protected String modid;
        @XmlElement(name = "MOD_DATE", required = true)
        protected String moddate;
        @XmlElement(name = "SN", required = true)
        protected String sn;
        @XmlElement(name = "TOTAL_QUANTITY", required = true)
        protected String totalquantity;
        @XmlElement(name = "TOTAL_QUANTITY_UNIT", required = true)
        protected String totalquantityunit;
        @XmlElement(name = "ERP_PO_ID", required = true)
        protected String erppoid;

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
         * lgflag �Ӽ��� ���� �����ɴϴ�.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getLGFLAG() {
            return lgflag;
        }

        /**
         * lgflag �Ӽ��� ���� �����մϴ�.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setLGFLAG(String value) {
            this.lgflag = value;
        }

        /**
         * attributes1 �Ӽ��� ���� �����ɴϴ�.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getATTRIBUTES1() {
            return attributes1;
        }

        /**
         * attributes1 �Ӽ��� ���� �����մϴ�.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setATTRIBUTES1(String value) {
            this.attributes1 = value;
        }

        /**
         * placeofreceiptcode �Ӽ��� ���� �����ɴϴ�.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getPLACEOFRECEIPTCODE() {
            return placeofreceiptcode;
        }

        /**
         * placeofreceiptcode �Ӽ��� ���� �����մϴ�.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setPLACEOFRECEIPTCODE(String value) {
            this.placeofreceiptcode = value;
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
         * houseblno �Ӽ��� ���� �����ɴϴ�.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getHOUSEBLNO() {
            return houseblno;
        }

        /**
         * houseblno �Ӽ��� ���� �����մϴ�.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setHOUSEBLNO(String value) {
            this.houseblno = value;
        }

        /**
         * masterblno �Ӽ��� ���� �����ɴϴ�.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getMASTERBLNO() {
            return masterblno;
        }

        /**
         * masterblno �Ӽ��� ���� �����մϴ�.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setMASTERBLNO(String value) {
            this.masterblno = value;
        }

        /**
         * pono �Ӽ��� ���� �����ɴϴ�.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getPONO() {
            return pono;
        }

        /**
         * pono �Ӽ��� ���� �����մϴ�.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setPONO(String value) {
            this.pono = value;
        }

        /**
         * purperson �Ӽ��� ���� �����ɴϴ�.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getPURPERSON() {
            return purperson;
        }

        /**
         * purperson �Ӽ��� ���� �����մϴ�.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setPURPERSON(String value) {
            this.purperson = value;
        }

        /**
         * paymenttype �Ӽ��� ���� �����ɴϴ�.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getPAYMENTTYPE() {
            return paymenttype;
        }

        /**
         * paymenttype �Ӽ��� ���� �����մϴ�.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setPAYMENTTYPE(String value) {
            this.paymenttype = value;
        }

        /**
         * shippercode �Ӽ��� ���� �����ɴϴ�.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getSHIPPERCODE() {
            return shippercode;
        }

        /**
         * shippercode �Ӽ��� ���� �����մϴ�.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setSHIPPERCODE(String value) {
            this.shippercode = value;
        }

        /**
         * shippername �Ӽ��� ���� �����ɴϴ�.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getSHIPPERNAME() {
            return shippername;
        }

        /**
         * shippername �Ӽ��� ���� �����մϴ�.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setSHIPPERNAME(String value) {
            this.shippername = value;
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
         * onboarddate �Ӽ��� ���� �����ɴϴ�.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getONBOARDDATE() {
            return onboarddate;
        }

        /**
         * onboarddate �Ӽ��� ���� �����մϴ�.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setONBOARDDATE(String value) {
            this.onboarddate = value;
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
         * customsflag �Ӽ��� ���� �����ɴϴ�.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCUSTOMSFLAG() {
            return customsflag;
        }

        /**
         * customsflag �Ӽ��� ���� �����մϴ�.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCUSTOMSFLAG(String value) {
            this.customsflag = value;
        }

        /**
         * carriername �Ӽ��� ���� �����ɴϴ�.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCARRIERNAME() {
            return carriername;
        }

        /**
         * carriername �Ӽ��� ���� �����մϴ�.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCARRIERNAME(String value) {
            this.carriername = value;
        }

        /**
         * shippingportname �Ӽ��� ���� �����ɴϴ�.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getSHIPPINGPORTNAME() {
            return shippingportname;
        }

        /**
         * shippingportname �Ӽ��� ���� �����մϴ�.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setSHIPPINGPORTNAME(String value) {
            this.shippingportname = value;
        }

        /**
         * arrivalportname �Ӽ��� ���� �����ɴϴ�.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getARRIVALPORTNAME() {
            return arrivalportname;
        }

        /**
         * arrivalportname �Ӽ��� ���� �����մϴ�.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setARRIVALPORTNAME(String value) {
            this.arrivalportname = value;
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
         * transportkind �Ӽ��� ���� �����ɴϴ�.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getTRANSPORTKIND() {
            return transportkind;
        }

        /**
         * transportkind �Ӽ��� ���� �����մϴ�.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setTRANSPORTKIND(String value) {
            this.transportkind = value;
        }

        /**
         * totalpacking �Ӽ��� ���� �����ɴϴ�.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getTOTALPACKING() {
            return totalpacking;
        }

        /**
         * totalpacking �Ӽ��� ���� �����մϴ�.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setTOTALPACKING(String value) {
            this.totalpacking = value;
        }

        /**
         * totalgrossweight �Ӽ��� ���� �����ɴϴ�.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getTOTALGROSSWEIGHT() {
            return totalgrossweight;
        }

        /**
         * totalgrossweight �Ӽ��� ���� �����մϴ�.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setTOTALGROSSWEIGHT(String value) {
            this.totalgrossweight = value;
        }

        /**
         * measurement �Ӽ��� ���� �����ɴϴ�.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getMEASUREMENT() {
            return measurement;
        }

        /**
         * measurement �Ӽ��� ���� �����մϴ�.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setMEASUREMENT(String value) {
            this.measurement = value;
        }

        /**
         * receiptdate �Ӽ��� ���� �����ɴϴ�.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getRECEIPTDATE() {
            return receiptdate;
        }

        /**
         * receiptdate �Ӽ��� ���� �����մϴ�.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setRECEIPTDATE(String value) {
            this.receiptdate = value;
        }

        /**
         * placeofreceiptname �Ӽ��� ���� �����ɴϴ�.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getPLACEOFRECEIPTNAME() {
            return placeofreceiptname;
        }

        /**
         * placeofreceiptname �Ӽ��� ���� �����մϴ�.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setPLACEOFRECEIPTNAME(String value) {
            this.placeofreceiptname = value;
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
         * totalquantity �Ӽ��� ���� �����ɴϴ�.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getTOTALQUANTITY() {
            return totalquantity;
        }

        /**
         * totalquantity �Ӽ��� ���� �����մϴ�.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setTOTALQUANTITY(String value) {
            this.totalquantity = value;
        }

        /**
         * totalquantityunit �Ӽ��� ���� �����ɴϴ�.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getTOTALQUANTITYUNIT() {
            return totalquantityunit;
        }

        /**
         * totalquantityunit �Ӽ��� ���� �����մϴ�.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setTOTALQUANTITYUNIT(String value) {
            this.totalquantityunit = value;
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
