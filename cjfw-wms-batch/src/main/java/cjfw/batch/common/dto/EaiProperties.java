package cjfw.batch.common.dto;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * EAI 연동 설정을 바인딩하는 프로퍼티 클래스
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "cf.soap-api")
public class EaiProperties {
    /** Basic Auth 사용자 ID */
    private String userId;
    /** Basic Auth 비밀번호 */
    private String password;
    /** API 호출 기본 URL (호스트:포트 포함) */
    private String url;
    /** EAI 모드 (예: SCM_QAS) */
    private String mode;
    /** SMS 모드 (예: EAI_QAS) */
    private String smsMode;
    /** SSO 서버 엔드포인트 URL */
    private String iamUrl;
    /** SSO 운영 모드 (예: staging, production) */
    private String iamOpMode;
    /** 인터페이스별 상세 설정 매핑 (키: interface ID) */
    private Map<String, Interface> interfaces = new HashMap<>();
    /** 필요시 http/https 토글 */
    private Boolean useHttps;
    private String iface;
    private String smsIface;
    private String scmIfaceNamespace;
    private String shrIfaceNamespace;

    /**
     * 각 인터페이스별 엔드포인트·라우팅 정보
     */
    @Data
    public static class Interface {
        /** 호출할 전체 endpoint URL (호스트/포트/경로) */
        private String endpoint;
        /** SOAP 헤더 SenderParty 값 */
        private String senderParty;
        /** SOAP 헤더 SenderService 값 */
        private String senderService;
        /** SOAP 헤더 ReceiverParty 값 */
        private String receiverParty;
        /** SOAP 헤더 ReceiverService 값 */
        private String receiverService;
        /** SOAP 헤더 InterfaceNamespace 값 */
        private String interfaceNamespace;
        /** 연결/응답 타임아웃(ms) */
        private int timeout;
    }
}
