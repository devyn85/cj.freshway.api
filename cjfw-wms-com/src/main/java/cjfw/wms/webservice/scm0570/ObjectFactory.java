package cjfw.wms.webservice.scm0570;

import javax.xml.namespace.QName;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlElementDecl;
import jakarta.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.example.wsclient package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _MTSCM0570SCMResponse_QNAME = new QName("urn://scm.cjfreshway.co.kr/TCS", "MT_SCM0570_SCM_response");
    private final static QName _MTSCM0570SCM_QNAME = new QName("urn://scm.cjfreshway.co.kr/TCS", "MT_SCM0570_SCM");
    private int initVal;

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.example.wsclient
     * 
     */
    public ObjectFactory() {
        initVal = 0;
    }

    /**
     * Create an instance of {@link DTSCM0570SCM }
     * 
     * @return
     *     the new instance of {@link DTSCM0570SCM }
     */
    public DTSCM0570SCM createDTSCM0570SCM() {
        return new DTSCM0570SCM();
    }

    /**
     * Create an instance of {@link DTSCM0570SCMResponse }
     * 
     * @return
     *     the new instance of {@link DTSCM0570SCMResponse }
     */
    public DTSCM0570SCMResponse createDTSCM0570SCMResponse() {
        return new DTSCM0570SCMResponse();
    }

    /**
     * Create an instance of {@link DTSCM0570SCM.TPARAM }
     * 
     * @return
     *     the new instance of {@link DTSCM0570SCM.TPARAM }
     */
    public DTSCM0570SCM.TPARAM createDTSCM0570SCMTPARAM() {
        return new DTSCM0570SCM.TPARAM();
    }

    /**
     * Create an instance of {@link DTSCM0570SCMResponse.TRETURN }
     * 
     * @return
     *     the new instance of {@link DTSCM0570SCMResponse.TRETURN }
     */
    public DTSCM0570SCMResponse.TRETURN createDTSCM0570SCMResponseTRETURN() {
        return new DTSCM0570SCMResponse.TRETURN();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DTSCM0570SCMResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link DTSCM0570SCMResponse }{@code >}
     */
    @XmlElementDecl(namespace = "urn://scm.cjfreshway.co.kr/TCS", name = "MT_SCM0570_SCM_response")
    public JAXBElement<DTSCM0570SCMResponse> createMTSCM0570SCMResponse(DTSCM0570SCMResponse value) {
        return new JAXBElement<>(_MTSCM0570SCMResponse_QNAME, DTSCM0570SCMResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DTSCM0570SCM }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link DTSCM0570SCM }{@code >}
     */
    @XmlElementDecl(namespace = "urn://scm.cjfreshway.co.kr/TCS", name = "MT_SCM0570_SCM")
    public JAXBElement<DTSCM0570SCM> createMTSCM0570SCM(DTSCM0570SCM value) {
        return new JAXBElement<>(_MTSCM0570SCM_QNAME, DTSCM0570SCM.class, null, value);
    }

}
