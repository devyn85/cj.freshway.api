package cjfw.wms.portal.common.main.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 로그인 이력 응답 DTO
 */
@Data
public class LoginHistoryGetResDto {
	
	@Schema(description = "IP 주소", nullable = false, example = "127.0.0.1")
    private String ipAddr;
	
	@Schema(description = "접속시간", nullable = false, example = "2022-07-26 16:18:12")
    private String logDtm;
	
	@Schema(description = "로그인유형", nullable = false, example = "Login", allowableValues = {"Login", "Logout"})
    private String logType;
	
	@Schema(description = "로그인시간", nullable = false, example = "2022-07-26 16:18:12")
    private String loginDt;
	
	@Schema(description = "로그아웃시간", nullable = false, example = "2022-07-26 16:18:12")
    private String lotDt;
	
	@Schema(description = "IP 주소", nullable = false, example = "127.0.0.1")
    private String loginIp;
	
	@Schema(description = "성공여부", nullable = false, example = "success")
    private String loginSuccYn;
}