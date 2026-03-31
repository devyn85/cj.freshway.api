package cjfw.wms.portal.common.config;

import org.springdoc.core.customizers.OperationCustomizer;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.HandlerMethod;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.security.SecuritySchemes;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.media.StringSchema;
import io.swagger.v3.oas.models.parameters.Parameter;

@OpenAPIDefinition(
		info = @Info(title = "CJ 프레시웨이 WMS Back-Office API",
					 description = "CJ 프레시웨이 WMS 관리자용(back-office) API입니다.",
				     // contact = @Contact(email = "canalframe@cj.net", name="Canal Frame Tech Support", url="https://www.notion.so/canalframe/09364b9276764d2bbf5f84f2c507006e"),
					 version = "1.0"
		),
		security = {
                @SecurityRequirement(name = "CanalFrameAuth"),
        },
		servers = {
				@Server(url = "/",
						description = "(변경 가능) default : root path")
		}
)
@SecuritySchemes({
    @SecurityScheme(name = "CanalFrameAuth",
            type = SecuritySchemeType.HTTP,	// 유형 - "apiKey", "http", "oauth2", "openIdConnect"
            description = " Canal Frame의 기본 로그인을 통해 발급된 JWT Token을 활용한 인증",
            in = SecuritySchemeIn.HEADER,	// 인증키 입력 위치	
            scheme = "Bearer",				// 인증 방식 선택 - Basic, Bearer 등(Authorization header as defined in RFC 7235)
            bearerFormat = "JWT"
    ),
})
@Configuration
public class SwaggerConfig {

    @Bean // Common(Main) API 그룹
    public GroupedOpenApi commonApi(OperationCustomizer addGlobalHeader) {
        String[] paths = {"/common/**"};
        return GroupedOpenApi.builder()
                .group("Common API")
                .pathsToMatch(paths)
                .addOperationCustomizer(addGlobalHeader)
                .build();
    }


    @Bean // 시스템관리 API 그룹
    public GroupedOpenApi sysmgtApi(OperationCustomizer addGlobalHeader) {
        String[] paths = {"/sysmgt/**"};
        return GroupedOpenApi.builder()
                .group("시스템관리 API")
                .pathsToMatch(paths)
                .addOperationCustomizer(addGlobalHeader)
                .build();
    }


    @Bean // 공통기능 API 그룹
    public GroupedOpenApi comfuncApi(OperationCustomizer addGlobalHeader) {
        String[] paths = {"/comfunc/**"};
        return GroupedOpenApi.builder()
                .group("공통기능 API")
                .pathsToMatch(paths)
                .addOperationCustomizer(addGlobalHeader)
                .build();
    }
    
    @Bean // WMS 공통 API
    public GroupedOpenApi newCmApi(OperationCustomizer addGlobalHeader) {
        String[] paths = {"/api/cm/**"};
        return GroupedOpenApi.builder()
                .group("(WMS) 공통 API")
                .pathsToMatch(paths)
                .addOperationCustomizer(addGlobalHeader)
                .build();
    }

    @Bean // WMS 배치 API
    public GroupedOpenApi newBatchApi(OperationCustomizer addGlobalHeader) {
        String[] paths = {"/batch/api/**"};
        return GroupedOpenApi.builder()
                .group("(WMS) 배치 API")
                .pathsToMatch(paths)
                .addOperationCustomizer(addGlobalHeader)
                .build();
    }
    
    @Bean // WMS 시스템운영 API
    public GroupedOpenApi newSysApi(OperationCustomizer addGlobalHeader) {
        String[] paths = {"/api/sys/**"};
        return GroupedOpenApi.builder()
                .group("(WMS) 시스템운영 API")
                .pathsToMatch(paths)
                .addOperationCustomizer(addGlobalHeader)
                .build();
    }
    @Bean // WMS 출고 API
    public GroupedOpenApi newWdApi(OperationCustomizer addGlobalHeader) {
    	String[] paths = {"/api/wd/**"};
    	return GroupedOpenApi.builder()
    			.group("(WMS) 출고 API")
    			.pathsToMatch(paths)
    			.addOperationCustomizer(addGlobalHeader)
    			.build();
    }
    @Bean // WMS 재고 API
    public GroupedOpenApi newStApi(OperationCustomizer addGlobalHeader) {
    	String[] paths = {"/api/st/**"};
    	return GroupedOpenApi.builder()
    			.group("(WMS) 재고 API")
    			.pathsToMatch(paths)
    			.addOperationCustomizer(addGlobalHeader)
    			.build();
    }
    @Bean // WMS 기준정보 API
    public GroupedOpenApi newMsApi(OperationCustomizer addGlobalHeader) {
    	String[] paths = {"/api/ms/**"};
    	return GroupedOpenApi.builder()
    			.group("(WMS) 기준정보 API")
    			.pathsToMatch(paths)
    			.addOperationCustomizer(addGlobalHeader)
    			.build();
    }
    @Bean // 중계 API
    public GroupedOpenApi newGwmsApi(OperationCustomizer addGlobalHeader) {
        String[] paths = {"/api/adapterGwms/**"};
        return GroupedOpenApi.builder()
                .group("(WMS) 중계 API")
                .pathsToMatch(paths)
                .addOperationCustomizer(addGlobalHeader)
                .build();
    }
    @Bean // CRM API
    public GroupedOpenApi newCrmApi(OperationCustomizer addGlobalHeader) {
        String[] paths = {"/api/crm/**"};
        return GroupedOpenApi.builder()
                .group("(WMS) CRM API")
                .pathsToMatch(paths)
                .addOperationCustomizer(addGlobalHeader)
                .build();
    }
    @Bean // OFN API
    public GroupedOpenApi newOfnApi(OperationCustomizer addGlobalHeader) {
        String[] paths = {"/api/ofn/**"};
        return GroupedOpenApi.builder()
                .group("(WMS) OFN API")
                .pathsToMatch(paths)
                .addOperationCustomizer(addGlobalHeader)
                .build();
    }
    @Bean // WMS 발주 API
    public GroupedOpenApi newOmApi(OperationCustomizer addGlobalHeader) {
    	String[] paths = {"/api/om/**"};
    	return GroupedOpenApi.builder()
    			.group("(WMS) om API")
    			.pathsToMatch(paths)
    			.addOperationCustomizer(addGlobalHeader)
    			.build();
    }
    
    @Bean // WMS 게시판 API
    public GroupedOpenApi newCbApi(OperationCustomizer addGlobalHeader) {
        String[] paths = { "/api/tm/tmLocationMonitorSummary/**" };
        return GroupedOpenApi.builder()
                .group("(WMS) cb API")
                .pathsToMatch(paths)
                .addOperationCustomizer(addGlobalHeader)
                .build();
    }
    
    @Bean // TM 모듈 API
    public GroupedOpenApi tmApi(OperationCustomizer addGlobalHeader) {
        String[] paths = {"/api/tm/**"};
        return GroupedOpenApi.builder()
                .group("(WMS) tm API")
                .pathsToMatch(paths)
                .packagesToScan("cjfw.wms.tm")
                .addOperationCustomizer(addGlobalHeader)
                .build();
    }
    
    @Bean // WMS KP API
    public GroupedOpenApi newKpApi(OperationCustomizer addGlobalHeader) {
        String[] paths = { "/api/kp/**" };
        return GroupedOpenApi.builder()
                .group("(WMS) kp API")
                .pathsToMatch(paths)
                .addOperationCustomizer(addGlobalHeader)
                .build();
    }
    
    @Bean // WMS EXT API
    public GroupedOpenApi newExtApi(OperationCustomizer addGlobalHeader) {
        String[] paths = { "/api/ext/**" };
        return GroupedOpenApi.builder()
                .group("(WMS) ext API")
                .pathsToMatch(paths)
                .addOperationCustomizer(addGlobalHeader)
                .build();
    }

    @Bean // Gloabl Parameter 설정
    public OperationCustomizer addGlobalParameter() {
        return (Operation operation, HandlerMethod handlerMethod) -> {
            Parameter acceptLanguageHeader = new Parameter()
                    .in(ParameterIn.HEADER.toString())
                    .schema(new StringSchema()._default("ko-kr").name("Accept-Language")) // default값 설정
                    .name("Accept-Language")
                    .description("Accept Language(Default: ko-kr)")
                    .required(true);
            operation.addParametersItem(acceptLanguageHeader);
            return operation;
        };
    }


}
