
package cjfw.batch.scm0320.saop;

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

    private static final QName _MTSCM0320SCM_QNAME = new QName("urn://scm.cjfreshway.co.kr/MM", "MT_SCM0320_SCM");
    private static final QName _MTSCM0320SCMResponse_QNAME = new QName("urn://scm.cjfreshway.co.kr/MM", "MT_SCM0320_SCM_response");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: kr.co.cjfreshway.scm.mm
     * 
     */
    public ObjectFactory() {
    	log.info("ObjectFactory created for SCM0320");
    }

    /**
     * Create an instance of {@link DTSCM0320SCMResponse }
     * 
     * @return
     *     the new instance of {@link DTSCM0320SCMResponse }
     */
    public DTSCM0320SCMResponse createDTSCM0320SCMResponse() {
        return new DTSCM0320SCMResponse();
    }

    /**
     * Create an instance of {@link DTSCM0320SCM }
     * 
     * @return
     *     the new instance of {@link DTSCM0320SCM }
     */
    public DTSCM0320SCM createDTSCM0320SCM() {
        return new DTSCM0320SCM();
    }

    /**
     * Create an instance of {@link DTSCM0320SCMResponse.ITEMRET }
     * 
     * @return
     *     the new instance of {@link DTSCM0320SCMResponse.ITEMRET }
     */
    public DTSCM0320SCMResponse.ITEMRET createDTSCM0320SCMResponseITEMRET() {
        return new DTSCM0320SCMResponse.ITEMRET();
    }

    /**
     * Create an instance of {@link DTSCM0320SCM.ITEM }
     * 
     * @return
     *     the new instance of {@link DTSCM0320SCM.ITEM }
     */
    public DTSCM0320SCM.ITEM createDTSCM0320SCMITEM() {
        return new DTSCM0320SCM.ITEM();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DTSCM0320SCM }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link DTSCM0320SCM }{@code >}
     */
    @XmlElementDecl(namespace = "urn://scm.cjfreshway.co.kr/MM", name = "MT_SCM0320_SCM")
    public JAXBElement<DTSCM0320SCM> createMTSCM0320SCM(DTSCM0320SCM value) {
        return new JAXBElement<>(_MTSCM0320SCM_QNAME, DTSCM0320SCM.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DTSCM0320SCMResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link DTSCM0320SCMResponse }{@code >}
     */
    @XmlElementDecl(namespace = "urn://scm.cjfreshway.co.kr/MM", name = "MT_SCM0320_SCM_response")
    public JAXBElement<DTSCM0320SCMResponse> createMTSCM0320SCMResponse(DTSCM0320SCMResponse value) {
        return new JAXBElement<>(_MTSCM0320SCMResponse_QNAME, DTSCM0320SCMResponse.class, null, value);
    }

}
