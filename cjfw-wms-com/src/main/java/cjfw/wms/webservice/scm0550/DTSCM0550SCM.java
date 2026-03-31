package cjfw.wms.webservice.scm0550;

import java.util.ArrayList;
import java.util.List;

import cjfw.wms.webservice.exDCTCS.DTSCM0540SCM;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>DT_SCM0550_SCM complex type�� ���� Java Ŭ�����Դϴ�.
 * 
 * <p>���� ��Ű�� ������ �� Ŭ������ ���ԵǴ� �ʿ��� �������� �����մϴ�.
 * 
 * <pre>{@code
 * <complexType name="DT_SCM0550_SCM">
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
 *                   <element name="ITEM_CODE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   <element name="ITEM_DESCRIPTION" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   <element name="ERP_PO_ID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   <element name="ISSUE_DATE_FR" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   <element name="ISSUE_DATE_TO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
@XmlType(name = "DT_SCM0550_SCM", propOrder = {
    "xrows",
    "xsys",
    "xdats",
    "xtims",
    "tparam"
})
public class DTSCM0550SCM {

    @XmlElement(name = "XROWS", required = true)
    protected String xrows;
    @XmlElement(name = "XSYS", required = true)
    protected String xsys;
    @XmlElement(name = "XDATS", required = true)
    protected String xdats;
    @XmlElement(name = "XTIMS", required = true)
    protected String xtims;
    @XmlElement(name = "T_PARAM")
    protected List<DTSCM0550SCM.TPARAM> tparam;

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
     * {@link DTSCM0550SCM.TPARAM }
     * 
     * 
     * @return
     *     The value of the tparam property.
     */
    public List<DTSCM0550SCM.TPARAM> getTPARAM() {
        if (tparam == null) {
            tparam = new ArrayList<>();
        }
        return this.tparam;
    }
    
    public void setTPARAM(List<DTSCM0550SCM.TPARAM> value) {
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
     *         <element name="ITEM_CODE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         <element name="ITEM_DESCRIPTION" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         <element name="ERP_PO_ID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         <element name="ISSUE_DATE_FR" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         <element name="ISSUE_DATE_TO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
        "itemcode",
        "itemdescription",
        "erppoid",
        "issuedatefr",
        "issuedateto"
    })
    public static class TPARAM {

        @XmlElement(name = "ITEM_CODE")
        protected String itemcode;
        @XmlElement(name = "ITEM_DESCRIPTION")
        protected String itemdescription;
        @XmlElement(name = "ERP_PO_ID")
        protected String erppoid;
        @XmlElement(name = "ISSUE_DATE_FR")
        protected String issuedatefr;
        @XmlElement(name = "ISSUE_DATE_TO")
        protected String issuedateto;

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

    }

}
