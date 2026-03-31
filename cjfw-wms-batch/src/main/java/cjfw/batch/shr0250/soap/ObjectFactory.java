
package cjfw.batch.shr0250.soap;

import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlElementDecl;
import jakarta.xml.bind.annotation.XmlRegistry;
import lombok.extern.slf4j.Slf4j;

import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the kr.co.cjfreshway.shr.comm package. 
 * <p>An ObjectFactory allows you to programmatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@Slf4j
@XmlRegistry
public class ObjectFactory {

    private static final QName _MTSHR0250EAIResponse_QNAME = new QName("urn://shr.cjfreshway.co.kr/COMM", "MT_SHR0250_EAI_response");
    private static final QName _MTSHR0250EAI_QNAME = new QName("urn://shr.cjfreshway.co.kr/COMM", "MT_SHR0250_EAI");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: kr.co.cjfreshway.shr.comm
     * 
     */
    public ObjectFactory() {
    	log.info("ObjectFactory created for cjfw.batch.shr0250.soap");
    }

    /**
     * Create an instance of {@link DTSHR0250EAI }
     * 
     * @return
     *     the new instance of {@link DTSHR0250EAI }
     */
    public DTSHR0250EAI createDTSHR0250EAI() {
        return new DTSHR0250EAI();
    }

    /**
     * Create an instance of {@link DTSHR0250EAIResponse }
     * 
     * @return
     *     the new instance of {@link DTSHR0250EAIResponse }
     */
    public DTSHR0250EAIResponse createDTSHR0250EAIResponse() {
        return new DTSHR0250EAIResponse();
    }

    /**
     * Create an instance of {@link DTSHR0250EAI.TPARAM }
     * 
     * @return
     *     the new instance of {@link DTSHR0250EAI.TPARAM }
     */
    public DTSHR0250EAI.TPARAM createDTSHR0250EAITPARAM() {
        return new DTSHR0250EAI.TPARAM();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DTSHR0250EAIResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link DTSHR0250EAIResponse }{@code >}
     */
    @XmlElementDecl(namespace = "urn://shr.cjfreshway.co.kr/COMM", name = "MT_SHR0250_EAI_response")
    public JAXBElement<DTSHR0250EAIResponse> createMTSHR0250EAIResponse(DTSHR0250EAIResponse value) {
        return new JAXBElement<>(_MTSHR0250EAIResponse_QNAME, DTSHR0250EAIResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DTSHR0250EAI }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link DTSHR0250EAI }{@code >}
     */
    @XmlElementDecl(namespace = "urn://shr.cjfreshway.co.kr/COMM", name = "MT_SHR0250_EAI")
    public JAXBElement<DTSHR0250EAI> createMTSHR0250EAI(DTSHR0250EAI value) {
        return new JAXBElement<>(_MTSHR0250EAI_QNAME, DTSHR0250EAI.class, null, value);
    }

}
