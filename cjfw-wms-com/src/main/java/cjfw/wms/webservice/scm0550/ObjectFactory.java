package cjfw.wms.webservice.scm0550;

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

    private final static QName _MTSCM0550SCMResponse_QNAME = new QName("urn://scm.cjfreshway.co.kr/TCS", "MT_SCM0550_SCM_response");
    private final static QName _MTSCM0550SCM_QNAME = new QName("urn://scm.cjfreshway.co.kr/TCS", "MT_SCM0550_SCM");
    private int initVal;

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.example.wsclient
     * 
     */
    public ObjectFactory() {
        initVal = 0;
    }

    /**
     * Create an instance of {@link DTSCM0550SCM }
     * 
     * @return
     *     the new instance of {@link DTSCM0550SCM }
     */
    public DTSCM0550SCM createDTSCM0550SCM() {
        return new DTSCM0550SCM();
    }

    /**
     * Create an instance of {@link DTSCM0550SCMResponse }
     * 
     * @return
     *     the new instance of {@link DTSCM0550SCMResponse }
     */
    public DTSCM0550SCMResponse createDTSCM0550SCMResponse() {
        return new DTSCM0550SCMResponse();
    }

    /**
     * Create an instance of {@link DTSCM0550SCM.TPARAM }
     * 
     * @return
     *     the new instance of {@link DTSCM0550SCM.TPARAM }
     */
    public DTSCM0550SCM.TPARAM createDTSCM0550SCMTPARAM() {
        return new DTSCM0550SCM.TPARAM();
    }

    /**
     * Create an instance of {@link DTSCM0550SCMResponse.TRETURN }
     * 
     * @return
     *     the new instance of {@link DTSCM0550SCMResponse.TRETURN }
     */
    public DTSCM0550SCMResponse.TRETURN createDTSCM0550SCMResponseTRETURN() {
        return new DTSCM0550SCMResponse.TRETURN();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DTSCM0550SCMResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link DTSCM0550SCMResponse }{@code >}
     */
    @XmlElementDecl(namespace = "urn://scm.cjfreshway.co.kr/TCS", name = "MT_SCM0550_SCM_response")
    public JAXBElement<DTSCM0550SCMResponse> createMTSCM0550SCMResponse(DTSCM0550SCMResponse value) {
        return new JAXBElement<>(_MTSCM0550SCMResponse_QNAME, DTSCM0550SCMResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DTSCM0550SCM }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link DTSCM0550SCM }{@code >}
     */
    @XmlElementDecl(namespace = "urn://scm.cjfreshway.co.kr/TCS", name = "MT_SCM0550_SCM")
    public JAXBElement<DTSCM0550SCM> createMTSCM0550SCM(DTSCM0550SCM value) {
        return new JAXBElement<>(_MTSCM0550SCM_QNAME, DTSCM0550SCM.class, null, value);
    }

}
