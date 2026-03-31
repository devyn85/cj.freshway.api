
package cjfw.batch.scm0060.soap;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>DT_SCM0060_SCM_response complex type�� ���� Java Ŭ�����Դϴ�.</p>
 * 
 * <p>���� ��Ű�� ������ �� Ŭ������ ���ԵǴ� �ʿ��� �������� �����մϴ�.</p>
 * 
 * <pre>{@code
 * <complexType name="DT_SCM0060_SCM_response">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="XSTAT" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         <element name="XMSGS" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DT_SCM0060_SCM_response", propOrder = {
    "xstat",
    "xmsgs"
})
public class DTSCM0060SCMResponse {

    @XmlElement(name = "XSTAT", required = true)
    protected String xstat;
    @XmlElement(name = "XMSGS", required = true)
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
