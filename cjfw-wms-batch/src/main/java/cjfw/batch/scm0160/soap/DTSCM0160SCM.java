
package cjfw.batch.scm0160.soap;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

import java.util.ArrayList;
import java.util.List;


/**
 * <p>DT_SCM0160_SCM complex type�� ���� Java Ŭ�����Դϴ�.</p>
 * 
 * <p>���� ��Ű�� ������ �� Ŭ������ ���ԵǴ� �ʿ��� �������� �����մϴ�.</p>
 * 
 * <pre>{@code
 * <complexType name="DT_SCM0160_SCM">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="XROWS" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="XSYS" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="XDATS" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="XTIMS" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="IF_DM_DOCUMENT_H" minOccurs="0">
 *           <complexType>
 *             <complexContent>
 *               <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 <sequence>
 *                   <element name="DOCNO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   <element name="ORDERTYPE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   <element name="DCCODE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   <element name="FROM_BILLTOKEY" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   <element name="STORERKEY" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   <element name="SHOPPINGMALL" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                 </sequence>
 *               </restriction>
 *             </complexContent>
 *           </complexType>
 *         </element>
 *         <element name="IF_DM_DOCUMENT_D" maxOccurs="unbounded" minOccurs="0">
 *           <complexType>
 *             <complexContent>
 *               <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 <sequence>
 *                   <element name="DOCNO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   <element name="DOCLINE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   <element name="DEL_YN" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   <element name="PLANT" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   <element name="STORAGELOC" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   <element name="SKU" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   <element name="STORERORDERQTY" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   <element name="STORERUOM" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   <element name="DELIVERYDATE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   <element name="OTHER01" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   <element name="OTHER02" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
@XmlType(name = "DT_SCM0160_SCM", propOrder = {
        "xrows",
        "xsys",
        "xdats",
        "xtims",
        "ifdmdocumenth",
        "ifdmdocumentd"
})
public class DTSCM0160SCM {

    @XmlElement(name = "XROWS")
    protected String xrows;
    @XmlElement(name = "XSYS")
    protected String xsys;
    @XmlElement(name = "XDATS")
    protected String xdats;
    @XmlElement(name = "XTIMS")
    protected String xtims;
    @XmlElement(name = "IF_DM_DOCUMENT_H")
    protected DTSCM0160SCM.IFDMDOCUMENTH ifdmdocumenth;
    @XmlElement(name = "IF_DM_DOCUMENT_D")
    protected List<DTSCM0160SCM.IFDMDOCUMENTD> ifdmdocumentd;

    /**
     * xrows 속성의 값을 가져옵니다.
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
     * xrows 속성의 값을 설정합니다.
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
     * xsys 속성의 값을 가져옵니다.
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
     * xsys 속성의 값을 설정합니다.
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
     * xdats 속성의 값을 가져옵니다.
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
     * xdats 속성의 값을 설정합니다.
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
     * xtims 속성의 값을 가져옵니다.
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
     * xtims 속성의 값을 설정합니다.
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
     * ifdmdocumenth 속성의 값을 가져옵니다.
     *
     * @return
     *     possible object is
     *     {@link DTSCM0160SCM.IFDMDOCUMENTH }
     *
     */
    public DTSCM0160SCM.IFDMDOCUMENTH getIFDMDOCUMENTH() {
        return ifdmdocumenth;
    }

    /**
     * ifdmdocumenth 속성의 값을 설정합니다.
     *
     * @param value
     *     allowed object is
     *     {@link DTSCM0160SCM.IFDMDOCUMENTH }
     *
     */
    public void setIFDMDOCUMENTH(DTSCM0160SCM.IFDMDOCUMENTH value) {
        this.ifdmdocumenth = value;
    }

    /**
     * ifdmdocumentd �Ӽ��� ���� �����մϴ�.
     *
     * @param value
     *     allowed object is
     *     {@link List<IFDMDOCUMENTD> }
     *
     */
    public void setIFDMDOCUMENTD(List<IFDMDOCUMENTD> value) {
        this.ifdmdocumentd = value;
    }

    /**
     * Gets the value of the ifdmdocumentd property.
     *
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the ifdmdocumentd property.</p>
     *
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getIFDMDOCUMENTD().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DTSCM0160SCM.IFDMDOCUMENTD }
     * </p>
     *
     *
     * @return
     *     The value of the ifdmdocumentd property.
     */
    public List<DTSCM0160SCM.IFDMDOCUMENTD> getIFDMDOCUMENTD() {
        if (ifdmdocumentd == null) {
            ifdmdocumentd = new ArrayList<>();
        }
        return this.ifdmdocumentd;
    }


    /**
     * <p>anonymous complex type에 대한 Java 클래스입니다.</p>
     *
     * <p>다음 스키마 단편이 이 클래스에 포함되는 필요한 콘텐츠를 지정합니다.</p>


     *











     * <pre>{@code
     * <complexType>
     *   <complexContent>
     *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       <sequence>
     *         <element name="DOCNO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         <element name="DOCLINE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         <element name="DEL_YN" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         <element name="PLANT" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         <element name="STORAGELOC" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         <element name="SKU" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         <element name="STORERORDERQTY" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         <element name="STORERUOM" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         <element name="DELIVERYDATE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         <element name="OTHER01" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         <element name="OTHER02" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
            "docno",
            "docline",
            "delyn",
            "plant",
            "storageloc",
            "sku",
            "storerorderqty",
            "storeruom",
            "deliverydate",
            "other01",
            "other02"
    })
    public static class IFDMDOCUMENTD {

        @XmlElement(name = "DOCNO")
        protected String docno;
        @XmlElement(name = "DOCLINE")
        protected String docline;
        @XmlElement(name = "DEL_YN")
        protected String delyn;
        @XmlElement(name = "PLANT")
        protected String plant;
        @XmlElement(name = "STORAGELOC")
        protected String storageloc;
        @XmlElement(name = "SKU")
        protected String sku;
        @XmlElement(name = "STORERORDERQTY")
        protected String storerorderqty;
        @XmlElement(name = "STORERUOM")
        protected String storeruom;
        @XmlElement(name = "DELIVERYDATE")
        protected String deliverydate;
        @XmlElement(name = "OTHER01")
        protected String other01;
        @XmlElement(name = "OTHER02")
        protected String other02;

        /**
         * docno 속성의 값을 가져옵니다.
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
         * docno 속성의 값을 설정합니다.
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
         * docline 속성의 값을 가져옵니다.
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
         * docline 속성의 값을 설정합니다.
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
         * delyn 속성의 값을 가져옵니다.
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
         * delyn 속성의 값을 설정합니다.
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
         * plant 속성의 값을 가져옵니다.
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
         * plant 속성의 값을 설정합니다.
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
         * storageloc 속성의 값을 가져옵니다.
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
         * storageloc 속성의 값을 설정합니다.
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
         * sku 속성의 값을 가져옵니다.
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
         * sku 속성의 값을 설정합니다.
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
         * storerorderqty 속성의 값을 가져옵니다.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getSTORERORDERQTY() {
            return storerorderqty;
        }

        /**
         * storerorderqty 속성의 값을 설정합니다.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setSTORERORDERQTY(String value) {
            this.storerorderqty = value;
        }

        /**
         * storeruom 속성의 값을 가져옵니다.
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
         * storeruom 속성의 값을 설정합니다.
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
         * deliverydate 속성의 값을 가져옵니다.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getDELIVERYDATE() {
            return deliverydate;
        }

        /**
         * deliverydate 속성의 값을 설정합니다.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setDELIVERYDATE(String value) {
            this.deliverydate = value;
        }

        /**
         * other01 속성의 값을 가져옵니다.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getOTHER01() {
            return other01;
        }

        /**
         * other01 속성의 값을 설정합니다.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setOTHER01(String value) {
            this.other01 = value;
        }

        /**
         * other02 속성의 값을 가져옵니다.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getOTHER02() {
            return other02;
        }

        /**
         * other02 속성의 값을 설정합니다.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setOTHER02(String value) {
            this.other02 = value;
        }

    }


    /**
     * <p>anonymous complex type에 대한 Java 클래스입니다.</p>
     *
     * <p>다음 스키마 단편이 이 클래스에 포함되는 필요한 콘텐츠를 지정합니다.</p>
     *
     * <pre>{@code
     * <complexType>
     *   <complexContent>
     *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       <sequence>
     *         <element name="DOCNO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         <element name="ORDERTYPE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         <element name="DCCODE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         <element name="FROM_BILLTOKEY" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         <element name="STORERKEY" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         <element name="SHOPPINGMALL" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
            "docno",
            "ordertype",
            "dccode",
            "frombilltokey",
            "storerkey",
            "shoppingmall"
    })
    public static class IFDMDOCUMENTH {

        @XmlElement(name = "DOCNO")
        protected String docno;
        @XmlElement(name = "ORDERTYPE")
        protected String ordertype;
        @XmlElement(name = "DCCODE")
        protected String dccode;
        @XmlElement(name = "FROM_BILLTOKEY")
        protected String frombilltokey;
        @XmlElement(name = "STORERKEY")
        protected String storerkey;
        @XmlElement(name = "SHOPPINGMALL")
        protected String shoppingmall;

        /**
         * docno 속성의 값을 가져옵니다.
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
         * docno 속성의 값을 설정합니다.
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
         * ordertype 속성의 값을 가져옵니다.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getORDERTYPE() {
            return ordertype;
        }

        /**
         * ordertype 속성의 값을 설정합니다.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setORDERTYPE(String value) {
            this.ordertype = value;
        }

        /**
         * dccode 속성의 값을 가져옵니다.
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
         * dccode 속성의 값을 설정합니다.
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
         * frombilltokey 속성의 값을 가져옵니다.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getFROMBILLTOKEY() {
            return frombilltokey;
        }

        /**
         * frombilltokey 속성의 값을 설정합니다.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setFROMBILLTOKEY(String value) {
            this.frombilltokey = value;
        }

        /**
         * storerkey 속성의 값을 가져옵니다.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getSTORERKEY() {
            return storerkey;
        }

        /**
         * storerkey 속성의 값을 설정합니다.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setSTORERKEY(String value) {
            this.storerkey = value;
        }

        /**
         * shoppingmall 속성의 값을 가져옵니다.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getSHOPPINGMALL() {
            return shoppingmall;
        }

        /**
         * shoppingmall 속성의 값을 설정합니다.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setSHOPPINGMALL(String value) {
            this.shoppingmall = value;
        }

    }
}
