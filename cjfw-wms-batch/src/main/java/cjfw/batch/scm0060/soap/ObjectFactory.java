
package cjfw.batch.scm0060.soap;

import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlElementDecl;
import jakarta.xml.bind.annotation.XmlRegistry;
import lombok.extern.slf4j.Slf4j;

import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the kr.co.cjfreshway.scm.sd package. 
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

    private static final QName _MTSCM0060SCM_QNAME = new QName("urn://scm.cjfreshway.co.kr/SD", "MT_SCM0060_SCM");
    private static final QName _MTSCM0060SCMResponse_QNAME = new QName("urn://scm.cjfreshway.co.kr/SD", "MT_SCM0060_SCM_response");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: kr.co.cjfreshway.scm.sd
     * 
     */
    public ObjectFactory() {
    	log.info("ObjectFactory created for SCM0060 SOAP");
    }

    /**
     * Create an instance of {@link DTSCM0060SCM }
     * 
     * @return
     *     the new instance of {@link DTSCM0060SCM }
     */
    public DTSCM0060SCM createDTSCM0060SCM() {
        return new DTSCM0060SCM();
    }

    /**
     * Create an instance of {@link DTSCM0060SCMResponse }
     * 
     * @return
     *     the new instance of {@link DTSCM0060SCMResponse }
     */
    public DTSCM0060SCMResponse createDTSCM0060SCMResponse() {
        return new DTSCM0060SCMResponse();
    }

    /**
     * Create an instance of {@link DTSCM0060SCM.IFDMSENDDATA }
     * 
     * @return
     *     the new instance of {@link DTSCM0060SCM.IFDMSENDDATA }
     */
    public DTSCM0060SCM.IFDMSENDDATA createDTSCM0060SCMIFDMSENDDATA() {
        return new DTSCM0060SCM.IFDMSENDDATA();
    }

    /**
     * Create an instance of {@link DTSCM0060SCM.IFSTSTOCKSERIALINFOS }
     * 
     * @return
     *     the new instance of {@link DTSCM0060SCM.IFSTSTOCKSERIALINFOS }
     */
    public DTSCM0060SCM.IFSTSTOCKSERIALINFOS createDTSCM0060SCMIFSTSTOCKSERIALINFOS() {
        return new DTSCM0060SCM.IFSTSTOCKSERIALINFOS();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DTSCM0060SCM }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link DTSCM0060SCM }{@code >}
     */
    @XmlElementDecl(namespace = "urn://scm.cjfreshway.co.kr/SD", name = "MT_SCM0060_SCM")
    public JAXBElement<DTSCM0060SCM> createMTSCM0060SCM(DTSCM0060SCM value) {
        return new JAXBElement<>(_MTSCM0060SCM_QNAME, DTSCM0060SCM.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DTSCM0060SCMResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link DTSCM0060SCMResponse }{@code >}
     */
    @XmlElementDecl(namespace = "urn://scm.cjfreshway.co.kr/SD", name = "MT_SCM0060_SCM_response")
    public JAXBElement<DTSCM0060SCMResponse> createMTSCM0060SCMResponse(DTSCM0060SCMResponse value) {
        return new JAXBElement<>(_MTSCM0060SCMResponse_QNAME, DTSCM0060SCMResponse.class, null, value);
    }

}
