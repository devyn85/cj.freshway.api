
package cjfw.batch.shr0160.soap;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>DT_SHR0160_EAI complex type�� ���� Java Ŭ�����Դϴ�.</p>
 * 
 * <p>���� ��Ű�� ������ �� Ŭ������ ���ԵǴ� �ʿ��� �������� �����մϴ�.</p>
 * 
 * <pre>{@code
 * <complexType name="DT_SHR0160_EAI">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="XSYS" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         <element name="XDATS" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         <element name="XTIMS" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         <element name="SUBJECT" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="FROM" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="TO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="REPLY_TO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="CONTENT_TYPE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="CONTENT" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DT_SHR0160_EAI", propOrder = {
    "xsys",
    "xdats",
    "xtims",
    "subject",
    "from",
    "to",
    "replyto",
    "contenttype",
    "content"
})
public class DTSHR0160EAI {

    @XmlElement(name = "XSYS", required = true)
    protected String xsys;
    @XmlElement(name = "XDATS", required = true)
    protected String xdats;
    @XmlElement(name = "XTIMS", required = true)
    protected String xtims;
    @XmlElement(name = "SUBJECT")
    protected String subject;
    @XmlElement(name = "FROM")
    protected String from;
    @XmlElement(name = "TO")
    protected String to;
    @XmlElement(name = "REPLY_TO")
    protected String replyto;
    @XmlElement(name = "CONTENT_TYPE")
    protected String contenttype;
    @XmlElement(name = "CONTENT")
    protected String content;

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
     * subject �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSUBJECT() {
        return subject;
    }

    /**
     * subject �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSUBJECT(String value) {
        this.subject = value;
    }

    /**
     * from �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFROM() {
        return from;
    }

    /**
     * from �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFROM(String value) {
        this.from = value;
    }

    /**
     * to �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTO() {
        return to;
    }

    /**
     * to �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTO(String value) {
        this.to = value;
    }

    /**
     * replyto �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getREPLYTO() {
        return replyto;
    }

    /**
     * replyto �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setREPLYTO(String value) {
        this.replyto = value;
    }

    /**
     * contenttype �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCONTENTTYPE() {
        return contenttype;
    }

    /**
     * contenttype �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCONTENTTYPE(String value) {
        this.contenttype = value;
    }

    /**
     * content �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCONTENT() {
        return content;
    }

    /**
     * content �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCONTENT(String value) {
        this.content = value;
    }

}
