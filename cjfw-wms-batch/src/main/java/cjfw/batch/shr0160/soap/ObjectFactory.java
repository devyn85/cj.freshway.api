
package cjfw.batch.shr0160.soap;

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

    private static final QName _MTSHR0160EAI_QNAME = new QName("urn://shr.cjfreshway.co.kr/COMM", "MT_SHR0160_EAI");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: kr.co.cjfreshway.shr.comm
     * 
     */
    public ObjectFactory() {
    	log.info("ObjectFactory created for SHR0160 EAI");
    }

    /**
     * Create an instance of {@link DTSHR0160EAI }
     * 
     * @return
     *     the new instance of {@link DTSHR0160EAI }
     */
    public DTSHR0160EAI createDTSHR0160EAI() {
        return new DTSHR0160EAI();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DTSHR0160EAI }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link DTSHR0160EAI }{@code >}
     */
    @XmlElementDecl(namespace = "urn://shr.cjfreshway.co.kr/COMM", name = "MT_SHR0160_EAI")
    public JAXBElement<DTSHR0160EAI> createMTSHR0160EAI(DTSHR0160EAI value) {
        return new JAXBElement<>(_MTSHR0160EAI_QNAME, DTSHR0160EAI.class, null, value);
    }

}
