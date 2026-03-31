
package cjfw.batch.scm0160.soap;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

import java.util.ArrayList;
import java.util.List;


/**
 * <p>DT_SCM0160_SCM_response complex type�� ���� Java Ŭ�����Դϴ�.</p>
 * 
 * <p>���� ��Ű�� ������ �� Ŭ������ ���ԵǴ� �ʿ��� �������� �����մϴ�.</p>
 * 
 * <pre>{@code
 * <complexType name="DT_SCM0160_SCM_response">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="T_RETURN" maxOccurs="unbounded" minOccurs="0">
 *           <complexType>
 *             <complexContent>
 *               <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 <sequence>
 *                   <element name="DOCNO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   <element name="XSTAT" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   <element name="XMSGS" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
@XmlType(name = "DT_SCM0160_SCM_response", propOrder = {
        "treturn"
})
public class DTSCM0160SCMResponse {

    @XmlElement(name = "T_RETURN")
    protected List<DTSCM0160SCMResponse.TRETURN> treturn;

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
     * {@link DTSCM0160SCMResponse.TRETURN }
     * </p>
     *
     *
     * @return
     *     The value of the treturn property.
     */
    public List<DTSCM0160SCMResponse.TRETURN> getTRETURN() {
        if (treturn == null) {
            treturn = new ArrayList<>();
        }
        return this.treturn;
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
     *         <element name="XSTAT" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         <element name="XMSGS" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
            "xstat",
            "xmsgs"
    })
    public static class TRETURN {

        @XmlElement(name = "DOCNO")
        protected String docno;
        @XmlElement(name = "XSTAT")
        protected String xstat;
        @XmlElement(name = "XMSGS")
        protected String xmsgs;

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
         * xstat 속성의 값을 가져옵니다.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getXSTAT() {
            return xstat;
        }

        /**
         * xstat 속성의 값을 설정합니다.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setXSTAT(String value) {
            this.xstat = value;
        }

        /**
         * xmsgs 속성의 값을 가져옵니다.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getXMSGS() {
            return xmsgs;
        }

        /**
         * xmsgs 속성의 값을 설정합니다.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setXMSGS(String value) {
            this.xmsgs = value;
        }

    }

}
