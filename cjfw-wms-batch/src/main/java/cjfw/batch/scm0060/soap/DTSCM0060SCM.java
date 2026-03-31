
package cjfw.batch.scm0060.soap;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

import java.util.ArrayList;
import java.util.List;


/**
 * <p>DT_SCM0060_SCM complex type�� ���� Java Ŭ�����Դϴ�.</p>
 * 
 * <p>���� ��Ű�� ������ �� Ŭ������ ���ԵǴ� �ʿ��� �������� �����մϴ�.</p>
 * 
 * <pre>{@code
 * <complexType name="DT_SCM0060_SCM">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="XROWS" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="XSYS" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="XDATS" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="XTIMS" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="IF_DM_SENDDATA" maxOccurs="unbounded" minOccurs="0">
 *           <complexType>
 *             <complexContent>
 *               <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 <sequence>
 *                   <element name="SERIALKEY" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   <element name="SLIPDT" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   <element name="SLIPNO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   <element name="SLIPLINE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   <element name="DOCDT" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   <element name="DOCTYPE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   <element name="DOCNO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   <element name="DOCLINE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   <element name="DCCODE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   <element name="POKEY" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   <element name="POLINE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   <element name="SITE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   <element name="PLANT" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   <element name="STORAGELOC" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   <element name="SKU" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   <element name="STOREROPENQTY" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   <element name="STORERCONFIRMQTY" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   <element name="STORERCANCELQTY" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   <element name="STORERUOM" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   <element name="UOM" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   <element name="CONFIRMQTY" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   <element name="STOCKGRADE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   <element name="CHANNEL" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   <element name="REASONCODE2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   <element name="REASONMSG2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   <element name="CONFIRMWEIGHT" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   <element name="DEL_YN" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   <element name="EDITDATE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   <element name="EDITWHO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   <element name="CLOSECD" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
 *                   <element name="SERIALKEY" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   <element name="WD_DOCNO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   <element name="WD_DOCLINE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   <element name="WD_DOCDT" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   <element name="SERIALNO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   <element name="CONVSERIALNO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   <element name="SHIPPEDQTY" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   <element name="POKEY" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   <element name="POLINE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   <element name="EDITDATE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   <element name="EDITWHO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   <element name="ETC_QTY" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   <element name="STORAGE_AMOUNT" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   <element name="ETC_AMOUNT" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
@XmlType(name = "DT_SCM0060_SCM", propOrder = {
    "xrows",
    "xsys",
    "xdats",
    "xtims",
    "ifdmsenddata",
    "ifststockserialinfos"
})
public class DTSCM0060SCM {

    @XmlElement(name = "XROWS")
    protected String xrows;
    @XmlElement(name = "XSYS")
    protected String xsys;
    @XmlElement(name = "XDATS")
    protected String xdats;
    @XmlElement(name = "XTIMS")
    protected String xtims;
    @XmlElement(name = "IF_DM_SENDDATA")
    protected List<IFDMSENDDATA> ifdmsenddata;
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
     * Gets the value of the ifdmsenddata property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the ifdmsenddata property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getIFDMSENDDATA().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link IFDMSENDDATA }
     * </p>
     * 
     * 
     * @return
     *     The value of the ifdmsenddata property.
     */
    public List<IFDMSENDDATA> getIFDMSENDDATA() {
        if (ifdmsenddata == null) {
            ifdmsenddata = new ArrayList<>();
        }
        return this.ifdmsenddata;
    }

    public void setIFDMSENDDATA(List<IFDMSENDDATA> value) {
        this.ifdmsenddata = value;
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

    public void setIFSTSTOCKSERIALINFOS(List<IFSTSTOCKSERIALINFOS> value) {
        this.ifststockserialinfos = value;
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
     *         <element name="SERIALKEY" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         <element name="SLIPDT" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         <element name="SLIPNO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         <element name="SLIPLINE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         <element name="DOCDT" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         <element name="DOCTYPE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         <element name="DOCNO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         <element name="DOCLINE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         <element name="DCCODE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         <element name="POKEY" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         <element name="POLINE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         <element name="SITE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         <element name="PLANT" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         <element name="STORAGELOC" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         <element name="SKU" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         <element name="STOREROPENQTY" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         <element name="STORERCONFIRMQTY" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         <element name="STORERCANCELQTY" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         <element name="STORERUOM" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         <element name="UOM" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         <element name="CONFIRMQTY" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         <element name="STOCKGRADE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         <element name="CHANNEL" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         <element name="REASONCODE2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         <element name="REASONMSG2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         <element name="CONFIRMWEIGHT" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         <element name="DEL_YN" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         <element name="EDITDATE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         <element name="EDITWHO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
        "serialkey",
        "slipdt",
        "slipno",
        "slipline",
        "docdt",
        "doctype",
        "docno",
        "docline",
        "dccode",
        "pokey",
        "poline",
        "site",
        "plant",
        "storageloc",
        "sku",
        "storeropenqty",
        "storerconfirmqty",
        "storercancelqty",
        "storeruom",
        "uom",
        "confirmqty",
        "stockgrade",
        "channel",
        "reasoncode2",
        "reasonmsg2",
        "confirmweight",
        "delyn",
        "editdate",
        "editwho",
        "closecd"
    })
    public static class IFDMSENDDATA {

        @XmlElement(name = "SERIALKEY")
        protected String serialkey;
        @XmlElement(name = "SLIPDT")
        protected String slipdt;
        @XmlElement(name = "SLIPNO")
        protected String slipno;
        @XmlElement(name = "SLIPLINE")
        protected String slipline;
        @XmlElement(name = "DOCDT")
        protected String docdt;
        @XmlElement(name = "DOCTYPE")
        protected String doctype;
        @XmlElement(name = "DOCNO")
        protected String docno;
        @XmlElement(name = "DOCLINE")
        protected String docline;
        @XmlElement(name = "DCCODE")
        protected String dccode;
        @XmlElement(name = "POKEY")
        protected String pokey;
        @XmlElement(name = "POLINE")
        protected String poline;
        @XmlElement(name = "SITE")
        protected String site;
        @XmlElement(name = "PLANT")
        protected String plant;
        @XmlElement(name = "STORAGELOC")
        protected String storageloc;
        @XmlElement(name = "SKU")
        protected String sku;
        @XmlElement(name = "STOREROPENQTY")
        protected String storeropenqty;
        @XmlElement(name = "STORERCONFIRMQTY")
        protected String storerconfirmqty;
        @XmlElement(name = "STORERCANCELQTY")
        protected String storercancelqty;
        @XmlElement(name = "STORERUOM")
        protected String storeruom;
        @XmlElement(name = "UOM")
        protected String uom;
        @XmlElement(name = "CONFIRMQTY")
        protected String confirmqty;
        @XmlElement(name = "STOCKGRADE")
        protected String stockgrade;
        @XmlElement(name = "CHANNEL")
        protected String channel;
        @XmlElement(name = "REASONCODE2")
        protected String reasoncode2;
        @XmlElement(name = "REASONMSG2")
        protected String reasonmsg2;
        @XmlElement(name = "CONFIRMWEIGHT")
        protected String confirmweight;
        @XmlElement(name = "DEL_YN")
        protected String delyn;
        @XmlElement(name = "EDITDATE")
        protected String editdate;
        @XmlElement(name = "EDITWHO")
        protected String editwho;
        @XmlElement(name = "CLOSECD")
        protected String closecd;

        /**
         * serialkey �Ӽ��� ���� �����ɴϴ�.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getSERIALKEY() {
            return serialkey;
        }

        /**
         * serialkey �Ӽ��� ���� �����մϴ�.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setSERIALKEY(String value) {
            this.serialkey = value;
        }

        /**
         * slipdt �Ӽ��� ���� �����ɴϴ�.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getSLIPDT() {
            return slipdt;
        }

        /**
         * slipdt �Ӽ��� ���� �����մϴ�.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setSLIPDT(String value) {
            this.slipdt = value;
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
         * docdt �Ӽ��� ���� �����ɴϴ�.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getDOCDT() {
            return docdt;
        }

        /**
         * docdt �Ӽ��� ���� �����մϴ�.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setDOCDT(String value) {
            this.docdt = value;
        }

        /**
         * doctype �Ӽ��� ���� �����ɴϴ�.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getDOCTYPE() {
            return doctype;
        }

        /**
         * doctype �Ӽ��� ���� �����մϴ�.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setDOCTYPE(String value) {
            this.doctype = value;
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
         * dccode �Ӽ��� ���� �����ɴϴ�.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getDCCODE() {
            return dccode;
        }

        /**
         * dccode �Ӽ��� ���� �����մϴ�.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setDCCODE(String value) {
            this.dccode = value;
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
         * site �Ӽ��� ���� �����ɴϴ�.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getSITE() {
            return site;
        }

        /**
         * site �Ӽ��� ���� �����մϴ�.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setSITE(String value) {
            this.site = value;
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
         * storeropenqty �Ӽ��� ���� �����ɴϴ�.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getSTOREROPENQTY() {
            return storeropenqty;
        }

        /**
         * storeropenqty �Ӽ��� ���� �����մϴ�.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setSTOREROPENQTY(String value) {
            this.storeropenqty = value;
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
         * storercancelqty �Ӽ��� ���� �����ɴϴ�.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getSTORERCANCELQTY() {
            return storercancelqty;
        }

        /**
         * storercancelqty �Ӽ��� ���� �����մϴ�.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setSTORERCANCELQTY(String value) {
            this.storercancelqty = value;
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
         * stockgrade �Ӽ��� ���� �����ɴϴ�.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getSTOCKGRADE() {
            return stockgrade;
        }

        /**
         * stockgrade �Ӽ��� ���� �����մϴ�.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setSTOCKGRADE(String value) {
            this.stockgrade = value;
        }

        /**
         * channel �Ӽ��� ���� �����ɴϴ�.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCHANNEL() {
            return channel;
        }

        /**
         * channel �Ӽ��� ���� �����մϴ�.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCHANNEL(String value) {
            this.channel = value;
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
         * reasonmsg2 �Ӽ��� ���� �����ɴϴ�.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getREASONMSG2() {
            return reasonmsg2;
        }

        /**
         * reasonmsg2 �Ӽ��� ���� �����մϴ�.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setREASONMSG2(String value) {
            this.reasonmsg2 = value;
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
         * delyn �Ӽ��� ���� �����ɴϴ�.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getDELYN() {
            return delyn;
        }

        /**
         * delyn �Ӽ��� ���� �����մϴ�.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setDELYN(String value) {
            this.delyn = value;
        }

        /**
         * editdate �Ӽ��� ���� �����ɴϴ�.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getEDITDATE() {
            return editdate;
        }

        /**
         * editdate �Ӽ��� ���� �����մϴ�.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setEDITDATE(String value) {
            this.editdate = value;
        }

        /**
         * editwho �Ӽ��� ���� �����ɴϴ�.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getEDITWHO() {
            return editwho;
        }

        /**
         * editwho �Ӽ��� ���� �����մϴ�.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setEDITWHO(String value) {
            this.editwho = value;
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
     *         <element name="SERIALKEY" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         <element name="WD_DOCNO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         <element name="WD_DOCLINE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         <element name="WD_DOCDT" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         <element name="SERIALNO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         <element name="CONVSERIALNO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         <element name="SHIPPEDQTY" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         <element name="POKEY" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         <element name="POLINE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         <element name="EDITDATE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         <element name="EDITWHO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         <element name="ETC_QTY" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         <element name="STORAGE_AMOUNT" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         <element name="ETC_AMOUNT" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
        "serialkey",
        "wddocno",
        "wddocline",
        "wddocdt",
        "serialno",
        "convserialno",
        "shippedqty",
        "pokey",
        "poline",
        "editdate",
        "editwho",
        "etcqty",
        "storageamount",
        "etcamount"
    })
    public static class IFSTSTOCKSERIALINFOS {

        @XmlElement(name = "SERIALKEY")
        protected String serialkey;
        @XmlElement(name = "WD_DOCNO")
        protected String wddocno;
        @XmlElement(name = "WD_DOCLINE")
        protected String wddocline;
        @XmlElement(name = "WD_DOCDT")
        protected String wddocdt;
        @XmlElement(name = "SERIALNO")
        protected String serialno;
        @XmlElement(name = "CONVSERIALNO")
        protected String convserialno;
        @XmlElement(name = "SHIPPEDQTY")
        protected String shippedqty;
        @XmlElement(name = "POKEY")
        protected String pokey;
        @XmlElement(name = "POLINE")
        protected String poline;
        @XmlElement(name = "EDITDATE")
        protected String editdate;
        @XmlElement(name = "EDITWHO")
        protected String editwho;
        @XmlElement(name = "ETC_QTY")
        protected String etcqty;
        @XmlElement(name = "STORAGE_AMOUNT")
        protected String storageamount;
        @XmlElement(name = "ETC_AMOUNT")
        protected String etcamount;

        /**
         * serialkey �Ӽ��� ���� �����ɴϴ�.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getSERIALKEY() {
            return serialkey;
        }

        /**
         * serialkey �Ӽ��� ���� �����մϴ�.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setSERIALKEY(String value) {
            this.serialkey = value;
        }

        /**
         * wddocno �Ӽ��� ���� �����ɴϴ�.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getWDDOCNO() {
            return wddocno;
        }

        /**
         * wddocno �Ӽ��� ���� �����մϴ�.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setWDDOCNO(String value) {
            this.wddocno = value;
        }

        /**
         * wddocline �Ӽ��� ���� �����ɴϴ�.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getWDDOCLINE() {
            return wddocline;
        }

        /**
         * wddocline �Ӽ��� ���� �����մϴ�.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setWDDOCLINE(String value) {
            this.wddocline = value;
        }

        /**
         * wddocdt �Ӽ��� ���� �����ɴϴ�.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getWDDOCDT() {
            return wddocdt;
        }

        /**
         * wddocdt �Ӽ��� ���� �����մϴ�.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setWDDOCDT(String value) {
            this.wddocdt = value;
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
         * shippedqty �Ӽ��� ���� �����ɴϴ�.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getSHIPPEDQTY() {
            return shippedqty;
        }

        /**
         * shippedqty �Ӽ��� ���� �����մϴ�.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setSHIPPEDQTY(String value) {
            this.shippedqty = value;
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
         * editdate �Ӽ��� ���� �����ɴϴ�.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getEDITDATE() {
            return editdate;
        }

        /**
         * editdate �Ӽ��� ���� �����մϴ�.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setEDITDATE(String value) {
            this.editdate = value;
        }

        /**
         * editwho �Ӽ��� ���� �����ɴϴ�.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getEDITWHO() {
            return editwho;
        }

        /**
         * editwho �Ӽ��� ���� �����մϴ�.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setEDITWHO(String value) {
            this.editwho = value;
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

        /**
         * storageamount �Ӽ��� ���� �����ɴϴ�.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getSTORAGEAMOUNT() {
            return storageamount;
        }

        /**
         * storageamount �Ӽ��� ���� �����մϴ�.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setSTORAGEAMOUNT(String value) {
            this.storageamount = value;
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
