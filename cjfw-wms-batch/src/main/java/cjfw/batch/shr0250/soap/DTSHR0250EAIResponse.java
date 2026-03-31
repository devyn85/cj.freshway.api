
package cjfw.batch.shr0250.soap;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>DT_SHR0250_EAI_response complex type�� ���� Java Ŭ�����Դϴ�.</p>
 * 
 * <p>���� ��Ű�� ������ �� Ŭ������ ���ԵǴ� �ʿ��� �������� �����մϴ�.</p>
 * 
 * <pre>{@code
 * <complexType name="DT_SHR0250_EAI_response">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
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
@XmlType(name = "DT_SHR0250_EAI_response", propOrder = {
    "xstat",
    "xmsgs"
})
public class DTSHR0250EAIResponse {

    @XmlElement(name = "XSTAT")
    protected String xstat;
    @XmlElement(name = "XMSGS")
    protected String xmsgs;

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

}
