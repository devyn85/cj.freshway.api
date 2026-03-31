
package cjfw.batch.scm0110.soap;

import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlElementDecl;
import jakarta.xml.bind.annotation.XmlRegistry;
import lombok.extern.slf4j.Slf4j;

import javax.xml.namespace.QName;


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

    private static final QName _MTSCM0110SCM_QNAME = new QName("urn://scm.cjfreshway.co.kr/MM", "MT_SCM0110_SCM");
    private static final QName _MTSCM0110SCMResponse_QNAME = new QName("urn://scm.cjfreshway.co.kr/MM", "MT_SCM0110_SCM_response");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: kr.co.cjfreshway.scm.mm
     * 
     */
    public ObjectFactory() {
    	log.info("ObjectFactory created for SCM0110");
    }

    /**
     * Create an instance of {@link DTSCM0110SCMResponse }
     * 
     * @return
     *     the new instance of {@link DTSCM0110SCMResponse }
     */
    public DTSCM0110SCMResponse createDTSCM0110SCMResponse() {
        return new DTSCM0110SCMResponse();
    }

    /**
     * Create an instance of {@link DTSCM0110SCM }
     * 
     * @return
     *     the new instance of {@link DTSCM0110SCM }
     */
    public DTSCM0110SCM createDTSCM0110SCM() {
        return new DTSCM0110SCM();
    }

    /**
     * Create an instance of {@link DTSCM0110SCMResponse.TRETURN }
     * 
     * @return
     *     the new instance of {@link DTSCM0110SCMResponse.TRETURN }
     */
    public DTSCM0110SCMResponse.TRETURN createDTSCM0110SCMResponseTRETURN() {
        return new DTSCM0110SCMResponse.TRETURN();
    }

    /**
     * Create an instance of {@link DTSCM0110SCM.IFDMSENDDATAH }
     * 
     * @return
     *     the new instance of {@link DTSCM0110SCM.IFDMSENDDATAH }
     */
    public DTSCM0110SCM.IFDMSENDDATAH createDTSCM0110SCMIFDMSENDDATAH() {
        return new DTSCM0110SCM.IFDMSENDDATAH();
    }

    /**
     * Create an instance of {@link DTSCM0110SCM.IFDMSENDDATAD }
     * 
     * @return
     *     the new instance of {@link DTSCM0110SCM.IFDMSENDDATAD }
     */
    public DTSCM0110SCM.IFDMSENDDATAD createDTSCM0110SCMIFDMSENDDATAD() {
        return new DTSCM0110SCM.IFDMSENDDATAD();
    }

    /**
     * Create an instance of {@link DTSCM0110SCM.IFSTSTOCKSERIALINFOS }
     * 
     * @return
     *     the new instance of {@link DTSCM0110SCM.IFSTSTOCKSERIALINFOS }
     */
    public DTSCM0110SCM.IFSTSTOCKSERIALINFOS createDTSCM0110SCMIFSTSTOCKSERIALINFOS() {
        return new DTSCM0110SCM.IFSTSTOCKSERIALINFOS();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DTSCM0110SCM }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link DTSCM0110SCM }{@code >}
     */
    @XmlElementDecl(namespace = "urn://scm.cjfreshway.co.kr/MM", name = "MT_SCM0110_SCM")
    public JAXBElement<DTSCM0110SCM> createMTSCM0110SCM(DTSCM0110SCM value) {
        return new JAXBElement<>(_MTSCM0110SCM_QNAME, DTSCM0110SCM.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DTSCM0110SCMResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link DTSCM0110SCMResponse }{@code >}
     */
    @XmlElementDecl(namespace = "urn://scm.cjfreshway.co.kr/MM", name = "MT_SCM0110_SCM_response")
    public JAXBElement<DTSCM0110SCMResponse> createMTSCM0110SCMResponse(DTSCM0110SCMResponse value) {
        return new JAXBElement<>(_MTSCM0110SCMResponse_QNAME, DTSCM0110SCMResponse.class, null, value);
    }

}
