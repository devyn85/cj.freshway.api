package cjfw.wms.webservice.scm0560;

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

    private final static QName _MTSCM0560SCMResponse_QNAME = new QName("urn://scm.cjfreshway.co.kr/TCS", "MT_SCM0560_SCM_response");
    private final static QName _MTSCM0560SCM_QNAME = new QName("urn://scm.cjfreshway.co.kr/TCS", "MT_SCM0560_SCM");
    private int initVal;

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.example.wsclient
     * 
     */
    public ObjectFactory() {
        initVal = 0; 
    }

    /**
     * Create an instance of {@link DTSCM0560SCM }
     * 
     * @return
     *     the new instance of {@link DTSCM0560SCM }
     */
    public DTSCM0560SCM createDTSCM0560SCM() {
        return new DTSCM0560SCM();
    }

    /**
     * Create an instance of {@link DTSCM0560SCMResponse }
     * 
     * @return
     *     the new instance of {@link DTSCM0560SCMResponse }
     */
    public DTSCM0560SCMResponse createDTSCM0560SCMResponse() {
        return new DTSCM0560SCMResponse();
    }

    /**
     * Create an instance of {@link DTSCM0560SCM.TPARAM }
     * 
     * @return
     *     the new instance of {@link DTSCM0560SCM.TPARAM }
     */
    public DTSCM0560SCM.TPARAM createDTSCM0560SCMTPARAM() {
        return new DTSCM0560SCM.TPARAM();
    }

    /**
     * Create an instance of {@link DTSCM0560SCMResponse.TRETURN }
     * 
     * @return
     *     the new instance of {@link DTSCM0560SCMResponse.TRETURN }
     */
    public DTSCM0560SCMResponse.TRETURN createDTSCM0560SCMResponseTRETURN() {
        return new DTSCM0560SCMResponse.TRETURN();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DTSCM0560SCMResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link DTSCM0560SCMResponse }{@code >}
     */
    @XmlElementDecl(namespace = "urn://scm.cjfreshway.co.kr/TCS", name = "MT_SCM0560_SCM_response")
    public JAXBElement<DTSCM0560SCMResponse> createMTSCM0560SCMResponse(DTSCM0560SCMResponse value) {
        return new JAXBElement<>(_MTSCM0560SCMResponse_QNAME, DTSCM0560SCMResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DTSCM0560SCM }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link DTSCM0560SCM }{@code >}
     */
    @XmlElementDecl(namespace = "urn://scm.cjfreshway.co.kr/TCS", name = "MT_SCM0560_SCM")
    public JAXBElement<DTSCM0560SCM> createMTSCM0560SCM(DTSCM0560SCM value) {
        return new JAXBElement<>(_MTSCM0560SCM_QNAME, DTSCM0560SCM.class, null, value);
    }

}
