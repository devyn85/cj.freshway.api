
package cjfw.batch.shr0130.soap;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

import java.util.ArrayList;
import java.util.List;


/**
 * <p>DT_SHR0130_EAI complex type�� ���� Java Ŭ�����Դϴ�.</p>
 * 
 * <p>���� ��Ű�� ������ �� Ŭ������ ���ԵǴ� �ʿ��� �������� �����մϴ�.</p>
 * 
 * <pre>{@code
 * <complexType name="DT_SHR0130_EAI">
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
 *                   <element name="ID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   <element name="PHONE" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   <element name="CALLBACK" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   <element name="DATE" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   <element name="MSG" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
@XmlType(name = "DT_SHR0130_EAI", propOrder = {
    "xrows",
    "xsys",
    "xdats",
    "xtims",
    "tparam"
})
public class DTSHR0130EAI {

    @XmlElement(name = "XROWS", required = true)
    protected String xrows;
    @XmlElement(name = "XSYS", required = true)
    protected String xsys;
    @XmlElement(name = "XDATS", required = true)
    protected String xdats;
    @XmlElement(name = "XTIMS", required = true)
    protected String xtims;
    @XmlElement(name = "T_PARAM")
    protected List<TPARAM> tparam;

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
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the tparam property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getTPARAM().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TPARAM }
     * </p>
     * 
     * 
     * @return
     *     The value of the tparam property.
     */
    public List<TPARAM> getTPARAM() {
        if (tparam == null) {
            tparam = new ArrayList<>();
        }
        return this.tparam;
    }

    public void setTPARAM(List<TPARAM> value) {
        this.tparam = value;
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
     *         <element name="ID" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         <element name="PHONE" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         <element name="CALLBACK" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         <element name="DATE" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         <element name="MSG" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
        "id",
        "phone",
        "callback",
        "date",
        "msg"
    })
    public static class TPARAM {

        /**
         * ���� �߱��� SubID
         * 
         */
        @XmlElement(name = "ID", required = true)
        protected String id;
        /**
         * ������ �ڵ��� ��ȣ
         * 
         */
        @XmlElement(name = "PHONE", required = true)
        protected String phone;
        /**
         * �۽��� ��ȭ��ȣ
         * 
         */
        @XmlElement(name = "CALLBACK", required = true)
        protected String callback;
        /**
         * �޽����� ������ �ð� , �̷� �ð��� ������ ���� �߼۵�
         * 
         */
        @XmlElement(name = "DATE", required = true)
        protected String date;
        /**
         * ������ �޽���
         * 
         */
        @XmlElement(name = "MSG", required = true)
        protected String msg;

        /**
         * ���� �߱��� SubID
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getID() {
            return id;
        }

        /**
         * id �Ӽ��� ���� �����մϴ�.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         * @see #getID()
         */
        public void setID(String value) {
            this.id = value;
        }

        /**
         * ������ �ڵ��� ��ȣ
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getPHONE() {
            return phone;
        }

        /**
         * phone �Ӽ��� ���� �����մϴ�.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         * @see #getPHONE()
         */
        public void setPHONE(String value) {
            this.phone = value;
        }

        /**
         * �۽��� ��ȭ��ȣ
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCALLBACK() {
            return callback;
        }

        /**
         * callback �Ӽ��� ���� �����մϴ�.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         * @see #getCALLBACK()
         */
        public void setCALLBACK(String value) {
            this.callback = value;
        }

        /**
         * �޽����� ������ �ð� , �̷� �ð��� ������ ���� �߼۵�
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getDATE() {
            return date;
        }

        /**
         * date �Ӽ��� ���� �����մϴ�.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         * @see #getDATE()
         */
        public void setDATE(String value) {
            this.date = value;
        }

        /**
         * ������ �޽���
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getMSG() {
            return msg;
        }

        /**
         * msg �Ӽ��� ���� �����մϴ�.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         * @see #getMSG()
         */
        public void setMSG(String value) {
            this.msg = value;
        }

    }

}
