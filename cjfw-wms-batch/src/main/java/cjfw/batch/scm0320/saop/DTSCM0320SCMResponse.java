
package cjfw.batch.scm0320.saop;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

import java.util.ArrayList;
import java.util.List;


/**
 * <p>DT_SCM0320_SCM_response complex type�� ���� Java Ŭ�����Դϴ�.</p>
 * 
 * <p>���� ��Ű�� ������ �� Ŭ������ ���ԵǴ� �ʿ��� �������� �����մϴ�.</p>
 * 
 * <pre>{@code
 * <complexType name="DT_SCM0320_SCM_response">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="ITEM_RET" maxOccurs="unbounded" minOccurs="0">
 *           <complexType>
 *             <complexContent>
 *               <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 <sequence>
 *                   <element name="IF_TIMESTAMP" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   <element name="DOCNO" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   <element name="DOCLINE" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   <element name="PLANT" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   <element name="STOCKTRANSTYPE" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   <element name="IF_FLAG" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   <element name="IF_MEMO" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
@XmlType(name = "DT_SCM0320_SCM_response", propOrder = {
    "itemret"
})
public class DTSCM0320SCMResponse {

    @XmlElement(name = "ITEM_RET")
    protected List<ITEMRET> itemret;

    /**
     * Gets the value of the itemret property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the itemret property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getITEMRET().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ITEMRET }
     * </p>
     * 
     * 
     * @return
     *     The value of the itemret property.
     */
    public List<ITEMRET> getITEMRET() {
        if (itemret == null) {
            itemret = new ArrayList<>();
        }
        return this.itemret;
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
     *         <element name="IF_FLAG" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         <element name="IF_MEMO" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
        "ifflag",
        "ifmemo"
    })
    public static class ITEMRET {

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
        @XmlElement(name = "IF_FLAG", required = true)
        protected String ifflag;
        @XmlElement(name = "IF_MEMO", required = true)
        protected String ifmemo;

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
         * ifflag �Ӽ��� ���� �����ɴϴ�.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getIFFLAG() {
            return ifflag;
        }

        /**
         * ifflag �Ӽ��� ���� �����մϴ�.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setIFFLAG(String value) {
            this.ifflag = value;
        }

        /**
         * ifmemo �Ӽ��� ���� �����ɴϴ�.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getIFMEMO() {
            return ifmemo;
        }

        /**
         * ifmemo �Ӽ��� ���� �����մϴ�.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setIFMEMO(String value) {
            this.ifmemo = value;
        }

    }

}
