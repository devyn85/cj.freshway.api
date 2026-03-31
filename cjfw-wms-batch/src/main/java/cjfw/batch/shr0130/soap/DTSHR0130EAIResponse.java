
package cjfw.batch.shr0130.soap;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

import java.util.ArrayList;
import java.util.List;


/**
 * <p>DT_SHR0130_EAI_response complex type�� ���� Java Ŭ�����Դϴ�.</p>
 * 
 * <p>���� ��Ű�� ������ �� Ŭ������ ���ԵǴ� �ʿ��� �������� �����մϴ�.</p>
 * 
 * <pre>{@code
 * <complexType name="DT_SHR0130_EAI_response">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="XSTAT" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         <element name="XMSGS" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         <element name="stmt1_response" maxOccurs="unbounded" minOccurs="0">
 *           <complexType>
 *             <complexContent>
 *               <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 <sequence>
 *                   <element name="insert_count" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
@XmlType(name = "DT_SHR0130_EAI_response", propOrder = {
    "xstat",
    "xmsgs",
    "stmt1Response"
})
public class DTSHR0130EAIResponse {

    @XmlElement(name = "XSTAT", required = true)
    protected String xstat;
    @XmlElement(name = "XMSGS", required = true)
    protected String xmsgs;
    @XmlElement(name = "stmt1_response")
    protected List<Stmt1Response> stmt1Response;

    /**
     * xstat �Ӽ��� ���� �����ɴϴ�.
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
     * xstat �Ӽ��� ���� �����մϴ�.
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
     * xmsgs �Ӽ��� ���� �����ɴϴ�.
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
     * xmsgs �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setXMSGS(String value) {
        this.xmsgs = value;
    }

    /**
     * Gets the value of the stmt1Response property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the stmt1Response property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getStmt1Response().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Stmt1Response }
     * </p>
     * 
     * 
     * @return
     *     The value of the stmt1Response property.
     */
    public List<Stmt1Response> getStmt1Response() {
        if (stmt1Response == null) {
            stmt1Response = new ArrayList<>();
        }
        return this.stmt1Response;
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
     *         <element name="insert_count" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
        "insertCount"
    })
    public static class Stmt1Response {

        @XmlElement(name = "insert_count", required = true)
        protected String insertCount;

        /**
         * insertCount �Ӽ��� ���� �����ɴϴ�.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getInsertCount() {
            return insertCount;
        }

        /**
         * insertCount �Ӽ��� ���� �����մϴ�.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setInsertCount(String value) {
            this.insertCount = value;
        }

    }

}
