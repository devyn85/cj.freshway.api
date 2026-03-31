
package cjfw.wms.webservice.exDCTCS;

import javax.xml.namespace.QName;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlElementDecl;
import jakarta.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the kr.co.cjfreshway.scm.tcs package. 
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

    private static final QName _MTSCM0540SCMResponse_QNAME = new QName("urn://scm.cjfreshway.co.kr/TCS", "MT_SCM0540_SCM_response");
    private static final QName _MTSCM0540SCM_QNAME = new QName("urn://scm.cjfreshway.co.kr/TCS", "MT_SCM0540_SCM");
    private int initVal;

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: kr.co.cjfreshway.scm.tcs
     * 
     */
    public ObjectFactory() {
        initVal = 0;
    }

    /**
     * Create an instance of {@link DTSCM0540SCM }
     * 
     * @return
     *     the new instance of {@link DTSCM0540SCM }
     */
    public DTSCM0540SCM createDTSCM0540SCM() {
        return new DTSCM0540SCM();
    }

    /**
     * Create an instance of {@link DTSCM0540SCMResponse }
     * 
     * @return
     *     the new instance of {@link DTSCM0540SCMResponse }
     */
    public DTSCM0540SCMResponse createDTSCM0540SCMResponse() {
        return new DTSCM0540SCMResponse();
    }

    /**
     * Create an instance of {@link DTSCM0540SCM.TPARAM }
     * 
     * @return
     *     the new instance of {@link DTSCM0540SCM.TPARAM }
     */
    public DTSCM0540SCM.TPARAM createDTSCM0540SCMTPARAM() {
        return new DTSCM0540SCM.TPARAM();
    }

    /**
     * Create an instance of {@link DTSCM0540SCMResponse.GTUNRECEIVEDSTOCK }
     * 
     * @return
     *     the new instance of {@link DTSCM0540SCMResponse.GTUNRECEIVEDSTOCK }
     */
    public DTSCM0540SCMResponse.GTUNRECEIVEDSTOCK createDTSCM0540SCMResponseGTUNRECEIVEDSTOCK() {
        return new DTSCM0540SCMResponse.GTUNRECEIVEDSTOCK();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DTSCM0540SCMResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link DTSCM0540SCMResponse }{@code >}
     */
    @XmlElementDecl(namespace = "urn://scm.cjfreshway.co.kr/TCS", name = "MT_SCM0540_SCM_response")
    public JAXBElement<DTSCM0540SCMResponse> createMTSCM0540SCMResponse(DTSCM0540SCMResponse value) {
        return new JAXBElement<>(_MTSCM0540SCMResponse_QNAME, DTSCM0540SCMResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DTSCM0540SCM }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link DTSCM0540SCM }{@code >}
     */
    @XmlElementDecl(namespace = "urn://scm.cjfreshway.co.kr/TCS", name = "MT_SCM0540_SCM")
    public JAXBElement<DTSCM0540SCM> createMTSCM0540SCM(DTSCM0540SCM value) {
        return new JAXBElement<>(_MTSCM0540SCM_QNAME, DTSCM0540SCM.class, null, value);
    }

}
