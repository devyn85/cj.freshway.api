
package cjfw.wms.webservice.exDCTCS;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>DT_SCM0540_SCM_response complex type�� ���� Java Ŭ�����Դϴ�.
 * 
 * <p>���� ��Ű�� ������ �� Ŭ������ ���ԵǴ� �ʿ��� �������� �����մϴ�.
 * 
 * <pre>{@code
 * <complexType name="DT_SCM0540_SCM_response">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="GT_UNRECEIVED_STOCK" maxOccurs="unbounded" minOccurs="0">
 *           <complexType>
 *             <complexContent>
 *               <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 <sequence>
 *                   <element name="DCCODE" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   <element name="DOCNO" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   <element name="DOCLINE" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   <element name="SKU" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   <element name="LGORT" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   <element name="CONTRACTTYPE" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   <element name="CUSTKEY" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   <element name="CONVSERIALNO" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   <element name="STOCK_QTY" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   <element name="BOND_QTY" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   <element name="BASEUOM" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   <element name="STOCK_BOX" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   <element name="BOND_CARRY_DATE" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   <element name="EST_NO" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   <element name="COUNTRYOFORIGIN" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   <element name="CONTAINERNO" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   <element name="EXPIRY_DAY" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   <element name="LC_NO" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
@XmlType(name = "DT_SCM0540_SCM_response", propOrder = {
       // "xstat",
       // "xmsgs",
        "gtunreceivedstock"
})
public class DTSCM0540SCMResponse {
    
//    @XmlElement(name = "XSTAT", required = true)
//    protected String xstat;
//    @XmlElement(name = "XMSGS", required = true)
//    protected String xmsgs;

    @XmlElement(name = "GT_UNRECEIVED_STOCK")
    protected List<GTUNRECEIVEDSTOCK> gtunreceivedstock;

    
    /**
     * xstat �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
//    public String getXSTAT() {
//        return xstat;
//    }

    /**
     * xstat �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
//    public void setXSTAT(String value) {
//        this.xstat = value;
//    }

    /**
     * xmsgs �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
//    public String getXMSGS() {
//        return xmsgs;
//    }

    /**
     * xmsgs �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
//    public void setXMSGS(String value) {
//        this.xmsgs = value;
//    }
    
    /**
     * Gets the value of the gtunreceivedstock property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the gtunreceivedstock property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getGTUNRECEIVEDSTOCK().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DTSCM0540SCMResponse.GTUNRECEIVEDSTOCK }
     * 
     * 
     * @return
     *     The value of the gtunreceivedstock property.
     */
    public List<GTUNRECEIVEDSTOCK> getGTUNRECEIVEDSTOCK() {
        if (gtunreceivedstock == null) {
            gtunreceivedstock = new ArrayList<>();
        }
        return this.gtunreceivedstock;
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
     *         <element name="DCCODE" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         <element name="DOCNO" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         <element name="DOCLINE" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         <element name="SKU" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         <element name="LGORT" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         <element name="CONTRACTTYPE" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         <element name="CUSTKEY" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         <element name="CONVSERIALNO" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         <element name="STOCK_QTY" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         <element name="BOND_QTY" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         <element name="BASEUOM" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         <element name="STOCK_BOX" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         <element name="BOND_CARRY_DATE" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         <element name="EST_NO" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         <element name="COUNTRYOFORIGIN" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         <element name="CONTAINERNO" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         <element name="EXPIRY_DAY" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         <element name="LC_NO" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
        "dccode",
        "docno",
        "docline",
        "sku",
        "lgort",
        "contracttype",
        "custkey",
        "convserialno",
        "stockqty",
        "bondqty",
        "baseuom",
        "stockbox",
        "bondcarrydate",
        "estno",
        "countryoforigin",
        "containerno",
        "expiryday",
        "lcno"
    })
    public static class GTUNRECEIVEDSTOCK {

        @XmlElement(name = "DCCODE", required = true)
        protected String dccode;
        @XmlElement(name = "DOCNO", required = true)
        protected String docno;
        @XmlElement(name = "DOCLINE", required = true)
        protected String docline;
        @XmlElement(name = "SKU", required = true)
        protected String sku;
        @XmlElement(name = "LGORT", required = true)
        protected String lgort;
        @XmlElement(name = "CONTRACTTYPE", required = true)
        protected String contracttype;
        @XmlElement(name = "CUSTKEY", required = true)
        protected String custkey;
        @XmlElement(name = "CONVSERIALNO", required = true)
        protected String convserialno;
        @XmlElement(name = "STOCK_QTY", required = true)
        protected String stockqty;
        @XmlElement(name = "BOND_QTY", required = true)
        protected String bondqty;
        @XmlElement(name = "BASEUOM", required = true)
        protected String baseuom;
        @XmlElement(name = "STOCK_BOX", required = true)
        protected String stockbox;
        @XmlElement(name = "BOND_CARRY_DATE", required = true)
        protected String bondcarrydate;
        @XmlElement(name = "EST_NO", required = true)
        protected String estno;
        @XmlElement(name = "COUNTRYOFORIGIN", required = true)
        protected String countryoforigin;
        @XmlElement(name = "CONTAINERNO", required = true)
        protected String containerno;
        @XmlElement(name = "EXPIRY_DAY", required = true)
        protected String expiryday;
        @XmlElement(name = "LC_NO", required = true)
        protected String lcno;

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
         * lgort �Ӽ��� ���� �����ɴϴ�.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getLGORT() {
            return lgort;
        }

        /**
         * lgort �Ӽ��� ���� �����մϴ�.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setLGORT(String value) {
            this.lgort = value;
        }

        /**
         * contracttype �Ӽ��� ���� �����ɴϴ�.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCONTRACTTYPE() {
            return contracttype;
        }

        /**
         * contracttype �Ӽ��� ���� �����մϴ�.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCONTRACTTYPE(String value) {
            this.contracttype = value;
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
         * stockqty �Ӽ��� ���� �����ɴϴ�.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getSTOCKQTY() {
            return stockqty;
        }

        /**
         * stockqty �Ӽ��� ���� �����մϴ�.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setSTOCKQTY(String value) {
            this.stockqty = value;
        }

        /**
         * bondqty �Ӽ��� ���� �����ɴϴ�.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getBONDQTY() {
            return bondqty;
        }

        /**
         * bondqty �Ӽ��� ���� �����մϴ�.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setBONDQTY(String value) {
            this.bondqty = value;
        }

        /**
         * baseuom �Ӽ��� ���� �����ɴϴ�.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getBASEUOM() {
            return baseuom;
        }

        /**
         * baseuom �Ӽ��� ���� �����մϴ�.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setBASEUOM(String value) {
            this.baseuom = value;
        }

        /**
         * stockbox �Ӽ��� ���� �����ɴϴ�.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getSTOCKBOX() {
            return stockbox;
        }

        /**
         * stockbox �Ӽ��� ���� �����մϴ�.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setSTOCKBOX(String value) {
            this.stockbox = value;
        }

        /**
         * bondcarrydate �Ӽ��� ���� �����ɴϴ�.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getBONDCARRYDATE() {
            return bondcarrydate;
        }

        /**
         * bondcarrydate �Ӽ��� ���� �����մϴ�.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setBONDCARRYDATE(String value) {
            this.bondcarrydate = value;
        }

        /**
         * estno �Ӽ��� ���� �����ɴϴ�.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getESTNO() {
            return estno;
        }

        /**
         * estno �Ӽ��� ���� �����մϴ�.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setESTNO(String value) {
            this.estno = value;
        }

        /**
         * countryoforigin �Ӽ��� ���� �����ɴϴ�.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCOUNTRYOFORIGIN() {
            return countryoforigin;
        }

        /**
         * countryoforigin �Ӽ��� ���� �����մϴ�.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCOUNTRYOFORIGIN(String value) {
            this.countryoforigin = value;
        }

        /**
         * containerno �Ӽ��� ���� �����ɴϴ�.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCONTAINERNO() {
            return containerno;
        }

        /**
         * containerno �Ӽ��� ���� �����մϴ�.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCONTAINERNO(String value) {
            this.containerno = value;
        }

        /**
         * expiryday �Ӽ��� ���� �����ɴϴ�.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getEXPIRYDAY() {
            return expiryday;
        }

        /**
         * expiryday �Ӽ��� ���� �����մϴ�.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setEXPIRYDAY(String value) {
            this.expiryday = value;
        }

        /**
         * lcno �Ӽ��� ���� �����ɴϴ�.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getLCNO() {
            return lcno;
        }

        /**
         * lcno �Ӽ��� ���� �����մϴ�.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setLCNO(String value) {
            this.lcno = value;
        }

    }

}
