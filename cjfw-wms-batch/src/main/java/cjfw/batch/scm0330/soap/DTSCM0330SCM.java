
package cjfw.batch.scm0330.soap;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

import java.util.ArrayList;
import java.util.List;


/**
 * <p>DT_SCM0330_SCM complex type�� ���� Java Ŭ�����Դϴ�.</p>
 * 
 * <p>���� ��Ű�� ������ �� Ŭ������ ���ԵǴ� �ʿ��� �������� �����մϴ�.</p>
 * 
 * <pre>{@code
 * <complexType name="DT_SCM0330_SCM">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="XROWS" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="XSYS" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="XDATS" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="XTIMS" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="IF_DM_SENDDATA_H" maxOccurs="unbounded" minOccurs="0">
 *           <complexType>
 *             <complexContent>
 *               <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 <sequence>
 *                   <element name="POKEY" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   <element name="DOCNO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   <element name="EFFECTIVEDATE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   <element name="POSTINGDATE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   <element name="SLIPNO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   <element name="CLOSECD" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                 </sequence>
 *               </restriction>
 *             </complexContent>
 *           </complexType>
 *         </element>
 *         <element name="IF_DM_SENDDATA_D" maxOccurs="unbounded" minOccurs="0">
 *           <complexType>
 *             <complexContent>
 *               <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 <sequence>
 *                   <element name="POKEY" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   <element name="POLINE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   <element name="DOCNO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   <element name="DOCLINE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   <element name="SKU" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   <element name="PLANT" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   <element name="STORAGELOC" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   <element name="STORERCONFIRMQTY" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   <element name="STORERUOM" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   <element name="SLIPNO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   <element name="SLIPLINE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   <element name="CONFIRMWEIGHT" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   <element name="STOCKTRANSTYPE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                 </sequence>
 *               </restriction>
 *             </complexContent>
 *           </complexType>
 *         </element>
 *         <element name="IF_ST_STOCK_SERIALINFO_S" maxOccurs="unbounded" minOccurs="0">
 *           <complexType>
 *             <complexContent>
 *               <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 <sequence>
 *                   <element name="POKEY" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   <element name="POLINE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   <element name="DP_DOCNO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   <element name="DP_DOCLINE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   <element name="STOCKID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   <element name="SKU" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   <element name="PLANT" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   <element name="STORAGELOC" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   <element name="CONVSERIALNO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   <element name="SERIALNO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   <element name="CONFIRMQTY" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   <element name="CANCELQTY" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   <element name="UOM" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   <element name="DP_SLIPNO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   <element name="DP_SLIPLINE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   <element name="ETC_QTY" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
@XmlType(name = "DT_SCM0330_SCM", propOrder = {
    "xrows",
    "xsys",
    "xdats",
    "xtims",
    "ifdmsenddatah",
    "ifdmsenddatad",
    "ifststockserialinfos"
})
public class DTSCM0330SCM {

    @XmlElement(name = "XROWS")
    protected String xrows;
    @XmlElement(name = "XSYS")
    protected String xsys;
    @XmlElement(name = "XDATS")
    protected String xdats;
    @XmlElement(name = "XTIMS")
    protected String xtims;
    @XmlElement(name = "IF_DM_SENDDATA_H")
    protected List<IFDMSENDDATAH> ifdmsenddatah;
    @XmlElement(name = "IF_DM_SENDDATA_D")
    protected List<IFDMSENDDATAD> ifdmsenddatad;
    @XmlElement(name = "IF_ST_STOCK_SERIALINFO_S")
    protected List<IFSTSTOCKSERIALINFOS> ifststockserialinfos;

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
     * Gets the value of the ifdmsenddatah property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the ifdmsenddatah property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getIFDMSENDDATAH().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link IFDMSENDDATAH }
     * </p>
     * 
     * 
     * @return
     *     The value of the ifdmsenddatah property.
     */
    public List<IFDMSENDDATAH> getIFDMSENDDATAH() {
        if (ifdmsenddatah == null) {
            ifdmsenddatah = new ArrayList<>();
        }
        return this.ifdmsenddatah;
    }

    /**
     * Gets the value of the ifdmsenddatad property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the ifdmsenddatad property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getIFDMSENDDATAD().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link IFDMSENDDATAD }
     * </p>
     * 
     * 
     * @return
     *     The value of the ifdmsenddatad property.
     */
    public List<IFDMSENDDATAD> getIFDMSENDDATAD() {
        if (ifdmsenddatad == null) {
            ifdmsenddatad = new ArrayList<>();
        }
        return this.ifdmsenddatad;
    }

    /**
     * Gets the value of the ifststockserialinfos property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the ifststockserialinfos property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getIFSTSTOCKSERIALINFOS().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link IFSTSTOCKSERIALINFOS }
     * </p>
     * 
     * 
     * @return
     *     The value of the ifststockserialinfos property.
     */
    public List<IFSTSTOCKSERIALINFOS> getIFSTSTOCKSERIALINFOS() {
        if (ifststockserialinfos == null) {
            ifststockserialinfos = new ArrayList<>();
        }
        return this.ifststockserialinfos;
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
     *         <element name="POKEY" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         <element name="POLINE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         <element name="DOCNO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         <element name="DOCLINE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         <element name="SKU" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         <element name="PLANT" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         <element name="STORAGELOC" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         <element name="STORERCONFIRMQTY" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         <element name="STORERUOM" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         <element name="SLIPNO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         <element name="SLIPLINE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         <element name="CONFIRMWEIGHT" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         <element name="STOCKTRANSTYPE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
        "pokey",
        "poline",
        "docno",
        "docline",
        "sku",
        "plant",
        "storageloc",
        "storerconfirmqty",
        "storeruom",
        "slipno",
        "slipline",
        "confirmweight",
        "stocktranstype"
    })
    public static class IFDMSENDDATAD {

        @XmlElement(name = "POKEY")
        protected String pokey;
        @XmlElement(name = "POLINE")
        protected String poline;
        @XmlElement(name = "DOCNO")
        protected String docno;
        @XmlElement(name = "DOCLINE")
        protected String docline;
        @XmlElement(name = "SKU")
        protected String sku;
        @XmlElement(name = "PLANT")
        protected String plant;
        @XmlElement(name = "STORAGELOC")
        protected String storageloc;
        @XmlElement(name = "STORERCONFIRMQTY")
        protected String storerconfirmqty;
        @XmlElement(name = "STORERUOM")
        protected String storeruom;
        @XmlElement(name = "SLIPNO")
        protected String slipno;
        @XmlElement(name = "SLIPLINE")
        protected String slipline;
        @XmlElement(name = "CONFIRMWEIGHT")
        protected String confirmweight;
        @XmlElement(name = "STOCKTRANSTYPE")
        protected String stocktranstype;

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
         * storerconfirmqty �Ӽ��� ���� �����ɴϴ�.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getSTORERCONFIRMQTY() {
            return storerconfirmqty;
        }

        /**
         * storerconfirmqty �Ӽ��� ���� �����մϴ�.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setSTORERCONFIRMQTY(String value) {
            this.storerconfirmqty = value;
        }

        /**
         * storeruom �Ӽ��� ���� �����ɴϴ�.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getSTORERUOM() {
            return storeruom;
        }

        /**
         * storeruom �Ӽ��� ���� �����մϴ�.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setSTORERUOM(String value) {
            this.storeruom = value;
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
         * confirmweight �Ӽ��� ���� �����ɴϴ�.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCONFIRMWEIGHT() {
            return confirmweight;
        }

        /**
         * confirmweight �Ӽ��� ���� �����մϴ�.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCONFIRMWEIGHT(String value) {
            this.confirmweight = value;
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
     *         <element name="POKEY" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         <element name="DOCNO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         <element name="EFFECTIVEDATE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         <element name="POSTINGDATE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         <element name="SLIPNO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         <element name="CLOSECD" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
        "pokey",
        "docno",
        "effectivedate",
        "postingdate",
        "slipno",
        "closecd"
    })
    public static class IFDMSENDDATAH {

        @XmlElement(name = "POKEY")
        protected String pokey;
        @XmlElement(name = "DOCNO")
        protected String docno;
        @XmlElement(name = "EFFECTIVEDATE")
        protected String effectivedate;
        @XmlElement(name = "POSTINGDATE")
        protected String postingdate;
        @XmlElement(name = "SLIPNO")
        protected String slipno;
        @XmlElement(name = "CLOSECD")
        protected String closecd;

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
     *         <element name="POKEY" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         <element name="POLINE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         <element name="DP_DOCNO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         <element name="DP_DOCLINE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         <element name="STOCKID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         <element name="SKU" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         <element name="PLANT" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         <element name="STORAGELOC" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         <element name="CONVSERIALNO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         <element name="SERIALNO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         <element name="CONFIRMQTY" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         <element name="CANCELQTY" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         <element name="UOM" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         <element name="DP_SLIPNO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         <element name="DP_SLIPLINE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         <element name="ETC_QTY" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
        "pokey",
        "poline",
        "dpdocno",
        "dpdocline",
        "stockid",
        "sku",
        "plant",
        "storageloc",
        "convserialno",
        "serialno",
        "confirmqty",
        "cancelqty",
        "uom",
        "dpslipno",
        "dpslipline",
        "etcqty"
    })
    public static class IFSTSTOCKSERIALINFOS {

        @XmlElement(name = "POKEY")
        protected String pokey;
        @XmlElement(name = "POLINE")
        protected String poline;
        @XmlElement(name = "DP_DOCNO")
        protected String dpdocno;
        @XmlElement(name = "DP_DOCLINE")
        protected String dpdocline;
        @XmlElement(name = "STOCKID")
        protected String stockid;
        @XmlElement(name = "SKU")
        protected String sku;
        @XmlElement(name = "PLANT")
        protected String plant;
        @XmlElement(name = "STORAGELOC")
        protected String storageloc;
        @XmlElement(name = "CONVSERIALNO")
        protected String convserialno;
        @XmlElement(name = "SERIALNO")
        protected String serialno;
        @XmlElement(name = "CONFIRMQTY")
        protected String confirmqty;
        @XmlElement(name = "CANCELQTY")
        protected String cancelqty;
        @XmlElement(name = "UOM")
        protected String uom;
        @XmlElement(name = "DP_SLIPNO")
        protected String dpslipno;
        @XmlElement(name = "DP_SLIPLINE")
        protected String dpslipline;
        @XmlElement(name = "ETC_QTY")
        protected String etcqty;

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
         * dpdocno �Ӽ��� ���� �����ɴϴ�.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getDPDOCNO() {
            return dpdocno;
        }

        /**
         * dpdocno �Ӽ��� ���� �����մϴ�.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setDPDOCNO(String value) {
            this.dpdocno = value;
        }

        /**
         * dpdocline �Ӽ��� ���� �����ɴϴ�.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getDPDOCLINE() {
            return dpdocline;
        }

        /**
         * dpdocline �Ӽ��� ���� �����մϴ�.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setDPDOCLINE(String value) {
            this.dpdocline = value;
        }

        /**
         * stockid �Ӽ��� ���� �����ɴϴ�.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getSTOCKID() {
            return stockid;
        }

        /**
         * stockid �Ӽ��� ���� �����մϴ�.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setSTOCKID(String value) {
            this.stockid = value;
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
         * convserialno �Ӽ��� ���� �����ɴϴ�.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCONVSERIALNO() {
            return convserialno;
        }

        /**
         * convserialno �Ӽ��� ���� �����մϴ�.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCONVSERIALNO(String value) {
            this.convserialno = value;
        }

        /**
         * serialno �Ӽ��� ���� �����ɴϴ�.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getSERIALNO() {
            return serialno;
        }

        /**
         * serialno �Ӽ��� ���� �����մϴ�.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setSERIALNO(String value) {
            this.serialno = value;
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
         * cancelqty �Ӽ��� ���� �����ɴϴ�.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCANCELQTY() {
            return cancelqty;
        }

        /**
         * cancelqty �Ӽ��� ���� �����մϴ�.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCANCELQTY(String value) {
            this.cancelqty = value;
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
         * dpslipno �Ӽ��� ���� �����ɴϴ�.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getDPSLIPNO() {
            return dpslipno;
        }

        /**
         * dpslipno �Ӽ��� ���� �����մϴ�.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setDPSLIPNO(String value) {
            this.dpslipno = value;
        }

        /**
         * dpslipline �Ӽ��� ���� �����ɴϴ�.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getDPSLIPLINE() {
            return dpslipline;
        }

        /**
         * dpslipline �Ӽ��� ���� �����մϴ�.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setDPSLIPLINE(String value) {
            this.dpslipline = value;
        }

        /**
         * etcqty �Ӽ��� ���� �����ɴϴ�.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getETCQTY() {
            return etcqty;
        }

        /**
         * etcqty �Ӽ��� ���� �����մϴ�.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setETCQTY(String value) {
            this.etcqty = value;
        }

    }

}
