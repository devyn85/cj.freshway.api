
package cjfw.batch.scm0160.soap;

import javax.xml.namespace.QName;

import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlElementDecl;
import jakarta.xml.bind.annotation.XmlRegistry;
import lombok.extern.slf4j.Slf4j;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the kr.co.cjfreshway.scm.mm package. 
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

    private static final QName _MTSCM0160SCM_QNAME = new QName("urn://scm.cjfreshway.co.kr/MM", "MT_SCM0160_SCM");
    private static final QName _MTSCM0160SCMResponse_QNAME = new QName("urn://scm.cjfreshway.co.kr/MM", "MT_SCM0160_SCM_response");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: kr.co.cjfreshway.scm.mm
     * 
     */
    public ObjectFactory() {
    	log.info("ObjectFactory created for SCM0160");
    }

    /**
     * Create an instance of {@link DTSCM0160SCMResponse }
     * 
     * @return
     *     the new instance of {@link DTSCM0160SCMResponse }
     */
    public DTSCM0160SCMResponse createDTSCM0160SCMResponse() {
        return new DTSCM0160SCMResponse();
    }

    /**
     * Create an instance of {@link DTSCM0160SCM }
     * 
     * @return
     *     the new instance of {@link DTSCM0160SCM }
     */
    public DTSCM0160SCM createDTSCM0160SCM() {
        return new DTSCM0160SCM();
    }

    /**
     * Create an instance of {@link DTSCM0160SCMResponse.TRETURN }
     * 
     * @return
     *     the new instance of {@link DTSCM0160SCMResponse.TRETURN }
     */
    public DTSCM0160SCMResponse.TRETURN createDTSCM0160SCMResponseTRETURN() {
        return new DTSCM0160SCMResponse.TRETURN();
    }

    /**
     * Create an instance of {@link DTSCM0160SCM.IFDMDOCUMENTH }
     * 
     * @return
     *     the new instance of {@link DTSCM0160SCM.IFDMDOCUMENTH }
     */
    public DTSCM0160SCM.IFDMDOCUMENTH createDTSCM0160SCMIFDMDOCUMENTH() {
        return new DTSCM0160SCM.IFDMDOCUMENTH();
    }

    /**
     * Create an instance of {@link DTSCM0160SCM.IFDMDOCUMENTD }
     * 
     * @return
     *     the new instance of {@link DTSCM0160SCM.IFDMDOCUMENTD }
     */
    public DTSCM0160SCM.IFDMDOCUMENTD createDTSCM0160SCMIFDMDOCUMENTD() {
        return new DTSCM0160SCM.IFDMDOCUMENTD();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DTSCM0160SCM }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link DTSCM0160SCM }{@code >}
     */
    @XmlElementDecl(namespace = "urn://scm.cjfreshway.co.kr/MM", name = "MT_SCM0160_SCM")
    public JAXBElement<DTSCM0160SCM> createMTSCM0160SCM(DTSCM0160SCM value) {
        return new JAXBElement<>(_MTSCM0160SCM_QNAME, DTSCM0160SCM.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DTSCM0160SCMResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link DTSCM0160SCMResponse }{@code >}
     */
    @XmlElementDecl(namespace = "urn://scm.cjfreshway.co.kr/MM", name = "MT_SCM0160_SCM_response")
    public JAXBElement<DTSCM0160SCMResponse> createMTSCM0160SCMResponse(DTSCM0160SCMResponse value) {
        return new JAXBElement<>(_MTSCM0160SCMResponse_QNAME, DTSCM0160SCMResponse.class, null, value);
    }

}
