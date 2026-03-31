
package cjfw.batch.scm0320.saop;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


/**
 * <p>DT_SCM0320_SCM complex type�� ���� Java Ŭ�����Դϴ�.</p>
 * 
 * <p>���� ��Ű�� ������ �� Ŭ������ ���ԵǴ� �ʿ��� �������� �����մϴ�.</p>
 * 
 * <pre>{@code
 * <complexType name="DT_SCM0320_SCM">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="XROWS" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         <element name="XSYS" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         <element name="XDATS" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         <element name="XTIMS" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         <element name="ITEM" maxOccurs="unbounded" minOccurs="0">
 *           <complexType>
 *             <complexContent>
 *               <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 <sequence>
 *                   <element name="IF_TIMESTAMP" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   <element name="DOCNO" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   <element name="DOCLINE" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   <element name="PLANT" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   <element name="STOCKTRANSTYPE" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   <element name="EFFECTIVEDATE" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   <element name="POSTINGDATE" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   <element name="STORAGELOC" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   <element name="SKU" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   <element name="CONFIRMQTY" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   <element name="UOM" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   <element name="REASONCODE2" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   <element name="OTHER03" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   <element name="REFERENCE07" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   <element name="POKEY" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   <element name="POLINE" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   <element name="REFERENCE08" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   <element name="SLIPNO" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   <element name="CUSTKEY" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   <element name="SLIPLINE" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   <element name="CLOSECD" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   <element name="REFERENCE05" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   <element name="STOCK_AMOUNT" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   <element name="ETC_AMOUNT" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
@XmlType(name = "DT_SCM0320_SCM", propOrder = {
    "xrows",
    "xsys",
    "xdats",
    "xtims",
    "item"
})
public class DTSCM0320SCM {

    @XmlElement(name = "XROWS", required = true)
    protected String xrows;
    @XmlElement(name = "XSYS", required = true)
    protected String xsys;
    @XmlElement(name = "XDATS", required = true)
    protected String xdats;
    @XmlElement(name = "XTIMS", required = true)
    protected String xtims;
    @Setter
    @XmlElement(name = "ITEM")
    protected List<ITEM> item;

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
     * Gets the value of the item property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the item property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getITEM().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ITEM }
     * </p>
     * 
     * 
     * @return
     *     The value of the item property.
     */
    public List<ITEM> getITEM() {
        if (item == null) {
            item = new ArrayList<>();
        }
        return this.item;
    }


    /**
     * <p>anonymous complex type�� ���� Java Ŭ�����Դϴ�.</p>
     * 
     * <p>���� ��Ű�� ������ �� Ŭ������ ���ԵǴ� �ʿ��� �������� �����մϴ�.</p>
     * 
     * <pre>{@code
     * <complexType>
     *   <complexContent>
     *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       <sequence>
     *         <element name="IF_TIMESTAMP" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         <element name="DOCNO" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         <element name="DOCLINE" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         <element name="PLANT" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         <element name="STOCKTRANSTYPE" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         <element name="EFFECTIVEDATE" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         <element name="POSTINGDATE" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         <element name="STORAGELOC" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         <element name="SKU" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         <element name="CONFIRMQTY" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         <element name="UOM" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         <element name="REASONCODE2" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         <element name="OTHER03" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         <element name="REFERENCE07" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         <element name="POKEY" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         <element name="POLINE" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         <element name="REFERENCE08" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         <element name="SLIPNO" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         <element name="CUSTKEY" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         <element name="SLIPLINE" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         <element name="CLOSECD" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         <element name="REFERENCE05" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         <element name="STOCK_AMOUNT" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         <element name="ETC_AMOUNT" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
        "iftimestamp",
        "docno",
        "docline",
        "plant",
        "stocktranstype",
        "effectivedate",
        "postingdate",
        "storageloc",
        "sku",
        "confirmqty",
        "uom",
        "reasoncode2",
        "other03",
        "reference07",
        "pokey",
        "poline",
        "reference08",
        "slipno",
        "custkey",
        "slipline",
        "closecd",
        "reference05",
        "stockamount",
        "etcamount"
    })
    public static class ITEM {

        @XmlElement(name = "IF_TIMESTAMP", required = true)
        protected String iftimestamp;
        @XmlElement(name = "DOCNO", required = true)
        protected String docno;
        @XmlElement(name = "DOCLINE", required = true)
        protected String docline;
        @XmlElement(name = "PLANT", required = true)
        protected String plant;
        @XmlElement(name = "STOCKTRANSTYPE", required = true)
        protected String stocktranstype;
        @XmlElement(name = "EFFECTIVEDATE", required = true)
        protected String effectivedate;
        @XmlElement(name = "POSTINGDATE", required = true)
        protected String postingdate;
        @XmlElement(name = "STORAGELOC", required = true)
        protected String storageloc;
        @XmlElement(name = "SKU", required = true)
        protected String sku;
        @XmlElement(name = "CONFIRMQTY", required = true)
        protected String confirmqty;
        @XmlElement(name = "UOM", required = true)
        protected String uom;
        @XmlElement(name = "REASONCODE2", required = true)
        protected String reasoncode2;
        @XmlElement(name = "OTHER03", required = true)
        protected String other03;
        @XmlElement(name = "REFERENCE07", required = true)
        protected String reference07;
        @XmlElement(name = "POKEY", required = true)
        protected String pokey;
        @XmlElement(name = "POLINE", required = true)
        protected String poline;
        @XmlElement(name = "REFERENCE08", required = true)
        protected String reference08;
        @XmlElement(name = "SLIPNO", required = true)
        protected String slipno;
        @XmlElement(name = "CUSTKEY", required = true)
        protected String custkey;
        @XmlElement(name = "SLIPLINE", required = true)
        protected String slipline;
        @XmlElement(name = "CLOSECD", required = true)
        protected String closecd;
        @XmlElement(name = "REFERENCE05", required = true)
        protected String reference05;
        @XmlElement(name = "STOCK_AMOUNT", required = true)
        protected String stockamount;
        @XmlElement(name = "ETC_AMOUNT", required = true)
        protected String etcamount;

        /**
         * iftimestamp �Ӽ��� ���� �����ɴϴ�.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getIFTIMESTAMP() {
            return iftimestamp;
        }

        /**
         * iftimestamp �Ӽ��� ���� �����մϴ�.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setIFTIMESTAMP(String value) {
            this.iftimestamp = value;
        }

        /**
         * docno �Ӽ��� ���� �����ɴϴ�.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getDOCNO() {
            return docno;
        }

        /**
         * docno �Ӽ��� ���� �����մϴ�.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setDOCNO(String value) {
            this.docno = value;
        }

        /**
         * docline �Ӽ��� ���� �����ɴϴ�.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getDOCLINE() {
            return docline;
        }

        /**
         * docline �Ӽ��� ���� �����մϴ�.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setDOCLINE(String value) {
            this.docline = value;
        }

        /**
         * plant �Ӽ��� ���� �����ɴϴ�.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getPLANT() {
            return plant;
        }

        /**
         * plant �Ӽ��� ���� �����մϴ�.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setPLANT(String value) {
            this.plant = value;
        }

        /**
         * stocktranstype �Ӽ��� ���� �����ɴϴ�.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getSTOCKTRANSTYPE() {
            return stocktranstype;
        }

        /**
         * stocktranstype �Ӽ��� ���� �����մϴ�.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setSTOCKTRANSTYPE(String value) {
            this.stocktranstype = value;
        }

        /**
         * effectivedate �Ӽ��� ���� �����ɴϴ�.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getEFFECTIVEDATE() {
            return effectivedate;
        }

        /**
         * effectivedate �Ӽ��� ���� �����մϴ�.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setEFFECTIVEDATE(String value) {
            this.effectivedate = value;
        }

        /**
         * postingdate �Ӽ��� ���� �����ɴϴ�.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getPOSTINGDATE() {
            return postingdate;
        }

        /**
         * postingdate �Ӽ��� ���� �����մϴ�.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setPOSTINGDATE(String value) {
            this.postingdate = value;
        }

        /**
         * storageloc �Ӽ��� ���� �����ɴϴ�.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getSTORAGELOC() {
            return storageloc;
        }

        /**
         * storageloc �Ӽ��� ���� �����մϴ�.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setSTORAGELOC(String value) {
            this.storageloc = value;
        }

        /**
         * sku �Ӽ��� ���� �����ɴϴ�.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getSKU() {
            return sku;
        }

        /**
         * sku �Ӽ��� ���� �����մϴ�.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setSKU(String value) {
            this.sku = value;
        }

        /**
         * confirmqty �Ӽ��� ���� �����ɴϴ�.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCONFIRMQTY() {
            return confirmqty;
        }

        /**
         * confirmqty �Ӽ��� ���� �����մϴ�.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCONFIRMQTY(String value) {
            this.confirmqty = value;
        }

        /**
         * uom �Ӽ��� ���� �����ɴϴ�.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getUOM() {
            return uom;
        }

        /**
         * uom �Ӽ��� ���� �����մϴ�.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setUOM(String value) {
            this.uom = value;
        }

        /**
         * reasoncode2 �Ӽ��� ���� �����ɴϴ�.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getREASONCODE2() {
            return reasoncode2;
        }

        /**
         * reasoncode2 �Ӽ��� ���� �����մϴ�.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setREASONCODE2(String value) {
            this.reasoncode2 = value;
        }

        /**
         * other03 �Ӽ��� ���� �����ɴϴ�.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getOTHER03() {
            return other03;
        }

        /**
         * other03 �Ӽ��� ���� �����մϴ�.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setOTHER03(String value) {
            this.other03 = value;
        }

        /**
         * reference07 �Ӽ��� ���� �����ɴϴ�.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getREFERENCE07() {
            return reference07;
        }

        /**
         * reference07 �Ӽ��� ���� �����մϴ�.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setREFERENCE07(String value) {
            this.reference07 = value;
        }

        /**
         * pokey �Ӽ��� ���� �����ɴϴ�.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getPOKEY() {
            return pokey;
        }

        /**
         * pokey �Ӽ��� ���� �����մϴ�.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setPOKEY(String value) {
            this.pokey = value;
        }

        /**
         * poline �Ӽ��� ���� �����ɴϴ�.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getPOLINE() {
            return poline;
        }

        /**
         * poline �Ӽ��� ���� �����մϴ�.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setPOLINE(String value) {
            this.poline = value;
        }

        /**
         * reference08 �Ӽ��� ���� �����ɴϴ�.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getREFERENCE08() {
            return reference08;
        }

        /**
         * reference08 �Ӽ��� ���� �����մϴ�.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setREFERENCE08(String value) {
            this.reference08 = value;
        }

        /**
         * slipno �Ӽ��� ���� �����ɴϴ�.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getSLIPNO() {
            return slipno;
        }

        /**
         * slipno �Ӽ��� ���� �����մϴ�.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setSLIPNO(String value) {
            this.slipno = value;
        }

        /**
         * custkey �Ӽ��� ���� �����ɴϴ�.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCUSTKEY() {
            return custkey;
        }

        /**
         * custkey �Ӽ��� ���� �����մϴ�.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCUSTKEY(String value) {
            this.custkey = value;
        }

        /**
         * slipline �Ӽ��� ���� �����ɴϴ�.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getSLIPLINE() {
            return slipline;
        }

        /**
         * slipline �Ӽ��� ���� �����մϴ�.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setSLIPLINE(String value) {
            this.slipline = value;
        }

        /**
         * closecd �Ӽ��� ���� �����ɴϴ�.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCLOSECD() {
            return closecd;
        }

        /**
         * closecd �Ӽ��� ���� �����մϴ�.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCLOSECD(String value) {
            this.closecd = value;
        }

        /**
         * reference05 �Ӽ��� ���� �����ɴϴ�.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getREFERENCE05() {
            return reference05;
        }

        /**
         * reference05 �Ӽ��� ���� �����մϴ�.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setREFERENCE05(String value) {
            this.reference05 = value;
        }

        /**
         * stockamount �Ӽ��� ���� �����ɴϴ�.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getSTOCKAMOUNT() {
            return stockamount;
        }

        /**
         * stockamount �Ӽ��� ���� �����մϴ�.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setSTOCKAMOUNT(String value) {
            this.stockamount = value;
        }

        /**
         * etcamount �Ӽ��� ���� �����ɴϴ�.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getETCAMOUNT() {
            return etcamount;
        }

        /**
         * etcamount �Ӽ��� ���� �����մϴ�.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setETCAMOUNT(String value) {
            this.etcamount = value;
        }

    }

}
