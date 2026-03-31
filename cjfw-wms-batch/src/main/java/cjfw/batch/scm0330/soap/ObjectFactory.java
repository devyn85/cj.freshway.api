
package cjfw.batch.scm0330.soap;

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

    private static final QName _MTSCM0330SCMResponse_QNAME = new QName("urn://scm.cjfreshway.co.kr/MM", "MT_SCM0330_SCM_response");
    private static final QName _MTSCM0330SCM_QNAME = new QName("urn://scm.cjfreshway.co.kr/MM", "MT_SCM0330_SCM");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: kr.co.cjfreshway.scm.mm
     * 
     */
    public ObjectFactory() {
    	log.info("ObjectFactory created for SCM0330");
    }

    /**
     * Create an instance of {@link DTSCM0330SCM }
     * 
     * @return
     *     the new instance of {@link DTSCM0330SCM }
     */
    public DTSCM0330SCM createDTSCM0330SCM() {
        return new DTSCM0330SCM();
    }

    /**
     * Create an instance of {@link DTSCM0330SCMResponse }
     * 
     * @return
     *     the new instance of {@link DTSCM0330SCMResponse }
     */
    public DTSCM0330SCMResponse createDTSCM0330SCMResponse() {
        return new DTSCM0330SCMResponse();
    }

    /**
     * Create an instance of {@link DTSCM0330SCM.IFDMSENDDATAH }
     * 
     * @return
     *     the new instance of {@link DTSCM0330SCM.IFDMSENDDATAH }
     */
    public DTSCM0330SCM.IFDMSENDDATAH createDTSCM0330SCMIFDMSENDDATAH() {
        return new DTSCM0330SCM.IFDMSENDDATAH();
    }

    /**
     * Create an instance of {@link DTSCM0330SCM.IFDMSENDDATAD }
     * 
     * @return
     *     the new instance of {@link DTSCM0330SCM.IFDMSENDDATAD }
     */
    public DTSCM0330SCM.IFDMSENDDATAD createDTSCM0330SCMIFDMSENDDATAD() {
        return new DTSCM0330SCM.IFDMSENDDATAD();
    }

    /**
     * Create an instance of {@link DTSCM0330SCM.IFSTSTOCKSERIALINFOS }
     * 
     * @return
     *     the new instance of {@link DTSCM0330SCM.IFSTSTOCKSERIALINFOS }
     */
    public DTSCM0330SCM.IFSTSTOCKSERIALINFOS createDTSCM0330SCMIFSTSTOCKSERIALINFOS() {
        return new DTSCM0330SCM.IFSTSTOCKSERIALINFOS();
    }

    /**
     * Create an instance of {@link DTSCM0330SCMResponse.TRETURN }
     * 
     * @return
     *     the new instance of {@link DTSCM0330SCMResponse.TRETURN }
     */
    public DTSCM0330SCMResponse.TRETURN createDTSCM0330SCMResponseTRETURN() {
        return new DTSCM0330SCMResponse.TRETURN();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DTSCM0330SCMResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link DTSCM0330SCMResponse }{@code >}
     */
    @XmlElementDecl(namespace = "urn://scm.cjfreshway.co.kr/MM", name = "MT_SCM0330_SCM_response")
    public JAXBElement<DTSCM0330SCMResponse> createMTSCM0330SCMResponse(DTSCM0330SCMResponse value) {
        return new JAXBElement<>(_MTSCM0330SCMResponse_QNAME, DTSCM0330SCMResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DTSCM0330SCM }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link DTSCM0330SCM }{@code >}
     */
    @XmlElementDecl(namespace = "urn://scm.cjfreshway.co.kr/MM", name = "MT_SCM0330_SCM")
    public JAXBElement<DTSCM0330SCM> createMTSCM0330SCM(DTSCM0330SCM value) {
        return new JAXBElement<>(_MTSCM0330SCM_QNAME, DTSCM0330SCM.class, null, value);
    }

}
