package cjfw.wms.webservice.scm0550;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>DT_SCM0550_SCM_response complex type�� ���� Java Ŭ�����Դϴ�.
 * 
 * <p>���� ��Ű�� ������ �� Ŭ������ ���ԵǴ� �ʿ��� �������� �����մϴ�.
 * 
 * <pre>{@code
 * <complexType name="DT_SCM0550_SCM_response">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="T_RETURN" maxOccurs="unbounded" minOccurs="0">
 *           <complexType>
 *             <complexContent>
 *               <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 <sequence>
 *                   <element name="STATUS" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   <element name="INVOICE_FLAG" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   <element name="KEY_NO" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   <element name="ISSUE_DATE" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   <element name="SUPPLIER_NAME" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   <element name="SN" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   <element name="PO_SN" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   <element name="LINE_NO" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   <element name="ITEM_CODE" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   <element name="ITEM_DESCRIPTION" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   <element name="HS_CODE" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   <element name="QUANTITY" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   <element name="QUANTITY_UNIT" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   <element name="UNIT_PRICE" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   <element name="AMOUNT" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   <element name="AMOUNT_UNIT" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   <element name="NET_WEIGHT" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   <element name="NET_WEIGHT_UNIT" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   <element name="GROSS_WEIGHT" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   <element name="GROSS_WEIGHT_UNIT" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   <element name="VOLUME" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   <element name="ERP_PO_ID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   <element name="SALE_CUSTOMER_CODE" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   <element name="SALE_CUSTOMER_NAME" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   <element name="HOUSE_BL_NO" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
@XmlType(name = "DT_SCM0550_SCM_response", propOrder = {
    "treturn"
})
public class DTSCM0550SCMResponse {

    @XmlElement(name = "T_RETURN")
    protected List<DTSCM0550SCMResponse.TRETURN> treturn;

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
     * {@link DTSCM0550SCMResponse.TRETURN }
     * 
     * 
     * @return
     *     The value of the treturn property.
     */
    public List<DTSCM0550SCMResponse.TRETURN> getTRETURN() {
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
     *         <element name="INVOICE_FLAG" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         <element name="KEY_NO" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         <element name="ISSUE_DATE" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         <element name="SUPPLIER_NAME" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         <element name="SN" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         <element name="PO_SN" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         <element name="LINE_NO" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         <element name="ITEM_CODE" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         <element name="ITEM_DESCRIPTION" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         <element name="HS_CODE" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         <element name="QUANTITY" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         <element name="QUANTITY_UNIT" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         <element name="UNIT_PRICE" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         <element name="AMOUNT" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         <element name="AMOUNT_UNIT" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         <element name="NET_WEIGHT" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         <element name="NET_WEIGHT_UNIT" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         <element name="GROSS_WEIGHT" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         <element name="GROSS_WEIGHT_UNIT" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         <element name="VOLUME" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         <element name="ERP_PO_ID" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         <element name="SALE_CUSTOMER_CODE" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         <element name="SALE_CUSTOMER_NAME" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         <element name="HOUSE_BL_NO" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
        "invoiceflag",
        "keyno",
        "issuedate",
        "suppliername",
        "sn",
        "posn",
        "lineno",
        "itemcode",
        "itemdescription",
        "hscode",
        "quantity",
        "quantityunit",
        "unitprice",
        "amount",
        "amountunit",
        "netweight",
        "netweightunit",
        "grossweight",
        "grossweightunit",
        "volume",
        "erppoid",
        "salecustomercode",
        "salecustomername",
        "houseblno"
    })
    public static class TRETURN {

        @XmlElement(name = "STATUS", required = true)
        protected String status;
        @XmlElement(name = "INVOICE_FLAG", required = true)
        protected String invoiceflag;
        @XmlElement(name = "KEY_NO", required = true)
        protected String keyno;
        @XmlElement(name = "ISSUE_DATE", required = true)
        protected String issuedate;
        @XmlElement(name = "SUPPLIER_NAME", required = true)
        protected String suppliername;
        @XmlElement(name = "SN", required = true)
        protected String sn;
        @XmlElement(name = "PO_SN", required = true)
        protected String posn;
        @XmlElement(name = "LINE_NO", required = true)
        protected String lineno;
        @XmlElement(name = "ITEM_CODE", required = true)
        protected String itemcode;
        @XmlElement(name = "ITEM_DESCRIPTION", required = true)
        protected String itemdescription;
        @XmlElement(name = "HS_CODE", required = true)
        protected String hscode;
        @XmlElement(name = "QUANTITY", required = true)
        protected String quantity;
        @XmlElement(name = "QUANTITY_UNIT", required = true)
        protected String quantityunit;
        @XmlElement(name = "UNIT_PRICE", required = true)
        protected String unitprice;
        @XmlElement(name = "AMOUNT", required = true)
        protected String amount;
        @XmlElement(name = "AMOUNT_UNIT", required = true)
        protected String amountunit;
        @XmlElement(name = "NET_WEIGHT", required = true)
        protected String netweight;
        @XmlElement(name = "NET_WEIGHT_UNIT", required = true)
        protected String netweightunit;
        @XmlElement(name = "GROSS_WEIGHT", required = true)
        protected String grossweight;
        @XmlElement(name = "GROSS_WEIGHT_UNIT", required = true)
        protected String grossweightunit;
        @XmlElement(name = "VOLUME", required = true)
        protected String volume;
        @XmlElement(name = "ERP_PO_ID", required = true)
        protected String erppoid;
        @XmlElement(name = "SALE_CUSTOMER_CODE", required = true)
        protected String salecustomercode;
        @XmlElement(name = "SALE_CUSTOMER_NAME", required = true)
        protected String salecustomername;
        @XmlElement(name = "HOUSE_BL_NO", required = true)
        protected String houseblno;

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
         * posn �Ӽ��� ���� �����ɴϴ�.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getPOSN() {
            return posn;
        }

        /**
         * posn �Ӽ��� ���� �����մϴ�.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setPOSN(String value) {
            this.posn = value;
        }

        /**
         * lineno �Ӽ��� ���� �����ɴϴ�.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getLINENO() {
            return lineno;
        }

        /**
         * lineno �Ӽ��� ���� �����մϴ�.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setLINENO(String value) {
            this.lineno = value;
        }

        /**
         * itemcode �Ӽ��� ���� �����ɴϴ�.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getITEMCODE() {
            return itemcode;
        }

        /**
         * itemcode �Ӽ��� ���� �����մϴ�.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setITEMCODE(String value) {
            this.itemcode = value;
        }

        /**
         * itemdescription �Ӽ��� ���� �����ɴϴ�.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getITEMDESCRIPTION() {
            return itemdescription;
        }

        /**
         * itemdescription �Ӽ��� ���� �����մϴ�.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setITEMDESCRIPTION(String value) {
            this.itemdescription = value;
        }

        /**
         * hscode �Ӽ��� ���� �����ɴϴ�.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getHSCODE() {
            return hscode;
        }

        /**
         * hscode �Ӽ��� ���� �����մϴ�.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setHSCODE(String value) {
            this.hscode = value;
        }

        /**
         * quantity �Ӽ��� ���� �����ɴϴ�.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getQUANTITY() {
            return quantity;
        }

        /**
         * quantity �Ӽ��� ���� �����մϴ�.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setQUANTITY(String value) {
            this.quantity = value;
        }

        /**
         * quantityunit �Ӽ��� ���� �����ɴϴ�.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getQUANTITYUNIT() {
            return quantityunit;
        }

        /**
         * quantityunit �Ӽ��� ���� �����մϴ�.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setQUANTITYUNIT(String value) {
            this.quantityunit = value;
        }

        /**
         * unitprice �Ӽ��� ���� �����ɴϴ�.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getUNITPRICE() {
            return unitprice;
        }

        /**
         * unitprice �Ӽ��� ���� �����մϴ�.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setUNITPRICE(String value) {
            this.unitprice = value;
        }

        /**
         * amount �Ӽ��� ���� �����ɴϴ�.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getAMOUNT() {
            return amount;
        }

        /**
         * amount �Ӽ��� ���� �����մϴ�.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setAMOUNT(String value) {
            this.amount = value;
        }

        /**
         * amountunit �Ӽ��� ���� �����ɴϴ�.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getAMOUNTUNIT() {
            return amountunit;
        }

        /**
         * amountunit �Ӽ��� ���� �����մϴ�.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setAMOUNTUNIT(String value) {
            this.amountunit = value;
        }

        /**
         * netweight �Ӽ��� ���� �����ɴϴ�.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getNETWEIGHT() {
            return netweight;
        }

        /**
         * netweight �Ӽ��� ���� �����մϴ�.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setNETWEIGHT(String value) {
            this.netweight = value;
        }

        /**
         * netweightunit �Ӽ��� ���� �����ɴϴ�.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getNETWEIGHTUNIT() {
            return netweightunit;
        }

        /**
         * netweightunit �Ӽ��� ���� �����մϴ�.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setNETWEIGHTUNIT(String value) {
            this.netweightunit = value;
        }

        /**
         * grossweight �Ӽ��� ���� �����ɴϴ�.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getGROSSWEIGHT() {
            return grossweight;
        }

        /**
         * grossweight �Ӽ��� ���� �����մϴ�.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setGROSSWEIGHT(String value) {
            this.grossweight = value;
        }

        /**
         * grossweightunit �Ӽ��� ���� �����ɴϴ�.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getGROSSWEIGHTUNIT() {
            return grossweightunit;
        }

        /**
         * grossweightunit �Ӽ��� ���� �����մϴ�.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setGROSSWEIGHTUNIT(String value) {
            this.grossweightunit = value;
        }

        /**
         * volume �Ӽ��� ���� �����ɴϴ�.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getVOLUME() {
            return volume;
        }

        /**
         * volume �Ӽ��� ���� �����մϴ�.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setVOLUME(String value) {
            this.volume = value;
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
         * salecustomercode �Ӽ��� ���� �����ɴϴ�.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getSALECUSTOMERCODE() {
            return salecustomercode;
        }

        /**
         * salecustomercode �Ӽ��� ���� �����մϴ�.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setSALECUSTOMERCODE(String value) {
            this.salecustomercode = value;
        }

        /**
         * salecustomername �Ӽ��� ���� �����ɴϴ�.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getSALECUSTOMERNAME() {
            return salecustomername;
        }

        /**
         * salecustomername �Ӽ��� ���� �����մϴ�.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setSALECUSTOMERNAME(String value) {
            this.salecustomername = value;
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

    }

}
