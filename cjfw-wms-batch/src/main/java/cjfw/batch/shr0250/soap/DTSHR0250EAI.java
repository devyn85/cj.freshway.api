
package cjfw.batch.shr0250.soap;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>DT_SHR0250_EAI complex type�� ���� Java Ŭ�����Դϴ�.</p>
 * 
 * <p>���� ��Ű�� ������ �� Ŭ������ ���ԵǴ� �ʿ��� �������� �����մϴ�.</p>
 * 
 * <pre>{@code
 * <complexType name="DT_SHR0250_EAI">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="XSYS" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="XDATS" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="XTIMS" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="T_PARAM" minOccurs="0">
 *           <complexType>
 *             <complexContent>
 *               <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 <sequence>
 *                   <element name="CUID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   <element name="MSG" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   <element name="EXT1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   <element name="EXT2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   <element name="EXT3" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   <element name="EXT4" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   <element name="MID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   <element name="SEQ" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   <element name="SENDDATE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
@XmlType(name = "DT_SHR0250_EAI", propOrder = {
    "xsys",
    "xdats",
    "xtims",
    "tparam"
})
public class DTSHR0250EAI {

    @XmlElement(name = "XSYS")
    protected String xsys;
    @XmlElement(name = "XDATS")
    protected String xdats;
    @XmlElement(name = "XTIMS")
    protected String xtims;
    @XmlElement(name = "T_PARAM")
    protected TPARAM tparam;

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
     * tparam �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link TPARAM }
     *     
     */
    public TPARAM getTPARAM() {
        return tparam;
    }

    /**
     * tparam �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link TPARAM }
     *     
     */
    public void setTPARAM(TPARAM value) {
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
     *         <element name="CUID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         <element name="MSG" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         <element name="EXT1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         <element name="EXT2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         <element name="EXT3" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         <element name="EXT4" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         <element name="MID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         <element name="SEQ" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         <element name="SENDDATE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
        "cuid",
        "msg",
        "ext1",
        "ext2",
        "ext3",
        "ext4",
        "mid",
        "seq",
        "senddate"
    })
    public static class TPARAM {

        @XmlElement(name = "CUID")
        protected String cuid;
        @XmlElement(name = "MSG")
        protected String msg;
        @XmlElement(name = "EXT1")
        protected String ext1;
        @XmlElement(name = "EXT2")
        protected String ext2;
        @XmlElement(name = "EXT3")
        protected String ext3;
        @XmlElement(name = "EXT4")
        protected String ext4;
        @XmlElement(name = "MID")
        protected String mid;
        @XmlElement(name = "SEQ")
        protected String seq;
        @XmlElement(name = "SENDDATE")
        protected String senddate;

        /**
         * cuid �Ӽ��� ���� �����ɴϴ�.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCUID() {
            return cuid;
        }

        /**
         * cuid �Ӽ��� ���� �����մϴ�.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCUID(String value) {
            this.cuid = value;
        }

        /**
         * msg �Ӽ��� ���� �����ɴϴ�.
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
         */
        public void setMSG(String value) {
            this.msg = value;
        }

        /**
         * ext1 �Ӽ��� ���� �����ɴϴ�.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getEXT1() {
            return ext1;
        }

        /**
         * ext1 �Ӽ��� ���� �����մϴ�.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setEXT1(String value) {
            this.ext1 = value;
        }

        /**
         * ext2 �Ӽ��� ���� �����ɴϴ�.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getEXT2() {
            return ext2;
        }

        /**
         * ext2 �Ӽ��� ���� �����մϴ�.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setEXT2(String value) {
            this.ext2 = value;
        }

        /**
         * ext3 �Ӽ��� ���� �����ɴϴ�.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getEXT3() {
            return ext3;
        }

        /**
         * ext3 �Ӽ��� ���� �����մϴ�.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setEXT3(String value) {
            this.ext3 = value;
        }

        /**
         * ext4 �Ӽ��� ���� �����ɴϴ�.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getEXT4() {
            return ext4;
        }

        /**
         * ext4 �Ӽ��� ���� �����մϴ�.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setEXT4(String value) {
            this.ext4 = value;
        }

        /**
         * mid �Ӽ��� ���� �����ɴϴ�.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getMID() {
            return mid;
        }

        /**
         * mid �Ӽ��� ���� �����մϴ�.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setMID(String value) {
            this.mid = value;
        }

        /**
         * seq �Ӽ��� ���� �����ɴϴ�.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getSEQ() {
            return seq;
        }

        /**
         * seq �Ӽ��� ���� �����մϴ�.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setSEQ(String value) {
            this.seq = value;
        }

        /**
         * senddate �Ӽ��� ���� �����ɴϴ�.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getSENDDATE() {
            return senddate;
        }

        /**
         * senddate �Ӽ��� ���� �����մϴ�.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setSENDDATE(String value) {
            this.senddate = value;
        }

    }

}
