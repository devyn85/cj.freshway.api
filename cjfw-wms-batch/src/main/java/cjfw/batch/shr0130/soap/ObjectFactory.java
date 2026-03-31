
package cjfw.batch.shr0130.soap;

import javax.xml.namespace.QName;

import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlElementDecl;
import jakarta.xml.bind.annotation.XmlRegistry;
import lombok.extern.slf4j.Slf4j;


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

    private static final QName _MTSHR0130EAIResponse_QNAME = new QName("urn://shr.cjfreshway.co.kr/COMM", "MT_SHR0130_EAI_response");
    private static final QName _MTSHR0130EAI_QNAME = new QName("urn://shr.cjfreshway.co.kr/COMM", "MT_SHR0130_EAI");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: kr.co.cjfreshway.shr.comm
     * 
     */
    public ObjectFactory() {
    	log.info("ObjectFactory 생성자 호출");
    }

    /**
     * Create an instance of {@link DTSHR0130EAI }
     * 
     * @return
     *     the new instance of {@link DTSHR0130EAI }
     */
    public DTSHR0130EAI createDTSHR0130EAI() {
        return new DTSHR0130EAI();
    }

    /**
     * Create an instance of {@link DTSHR0130EAIResponse }
     * 
     * @return
     *     the new instance of {@link DTSHR0130EAIResponse }
     */
    public DTSHR0130EAIResponse createDTSHR0130EAIResponse() {
        return new DTSHR0130EAIResponse();
    }

    /**
     * Create an instance of {@link DTSHR0130EAI.TPARAM }
     * 
     * @return
     *     the new instance of {@link DTSHR0130EAI.TPARAM }
     */
    public DTSHR0130EAI.TPARAM createDTSHR0130EAITPARAM() {
        return new DTSHR0130EAI.TPARAM();
    }

    /**
     * Create an instance of {@link DTSHR0130EAIResponse.Stmt1Response }
     * 
     * @return
     *     the new instance of {@link DTSHR0130EAIResponse.Stmt1Response }
     */
    public DTSHR0130EAIResponse.Stmt1Response createDTSHR0130EAIResponseStmt1Response() {
        return new DTSHR0130EAIResponse.Stmt1Response();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DTSHR0130EAIResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link DTSHR0130EAIResponse }{@code >}
     */
    @XmlElementDecl(namespace = "urn://shr.cjfreshway.co.kr/COMM", name = "MT_SHR0130_EAI_response")
    public JAXBElement<DTSHR0130EAIResponse> createMTSHR0130EAIResponse(DTSHR0130EAIResponse value) {
        return new JAXBElement<>(_MTSHR0130EAIResponse_QNAME, DTSHR0130EAIResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DTSHR0130EAI }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link DTSHR0130EAI }{@code >}
     */
    @XmlElementDecl(namespace = "urn://shr.cjfreshway.co.kr/COMM", name = "MT_SHR0130_EAI")
    public JAXBElement<DTSHR0130EAI> createMTSHR0130EAI(DTSHR0130EAI value) {
        return new JAXBElement<>(_MTSHR0130EAI_QNAME, DTSHR0130EAI.class, null, value);
    }

}
