
package cjfw.batch.scm0110.soap;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

import java.util.ArrayList;
import java.util.List;


/**
 * <p>DT_SCM0110_SCM_response complex type�� ���� Java Ŭ�����Դϴ�.</p>
 * 
 * <p>���� ��Ű�� ������ �� Ŭ������ ���ԵǴ� �ʿ��� �������� �����մϴ�.</p>
 * 
 * <pre>{@code
 * <complexType name="DT_SCM0110_SCM_response">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="T_RETURN" maxOccurs="unbounded" minOccurs="0">
 *           <complexType>
 *             <complexContent>
 *               <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 <sequence>
 *                   <element name="POKEY" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
@XmlType(name = "DT_SCM0110_SCM_response", propOrder = {
    "treturn"
})
public class DTSCM0110SCMResponse {

    @XmlElement(name = "T_RETURN")
    protected List<TRETURN> treturn;

    /**
     * Gets the value of the treturn property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the treturn property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getTRETURN().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TRETURN }
     * </p>
     * 
     * 
     * @return
     *     The value of the treturn property.
     */
    public List<TRETURN> getTRETURN() {
        if (treturn == null) {
            treturn = new ArrayList<>();
        }
        return this.treturn;
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
     *         <element name="POKEY" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
        "pokey",
        "ifflag",
        "ifmemo"
    })
    public static class TRETURN {

        @XmlElement(name = "POKEY", required = true)
        protected String pokey;
        @XmlElement(name = "IF_FLAG", required = true)
        protected String ifflag;
        @XmlElement(name = "IF_MEMO", required = true)
        protected String ifmemo;

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
