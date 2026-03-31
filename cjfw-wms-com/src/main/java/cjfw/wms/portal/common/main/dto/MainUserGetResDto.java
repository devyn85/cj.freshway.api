package cjfw.wms.portal.common.main.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Main 사용자정보 조회 응답 DTO
 */
@Data
@Schema(description = "로그인 사용자 조회 응답")
public class MainUserGetResDto {
    @Schema(description = "사용자ID", nullable = false, example = "devadmin01")
    private String userId;

    @Schema(description = "사용자명", nullable = false, example = "개발자관리자")
    private String userNm;

    @Schema(description = "메일주소", example = "test@cj.net")
    private String mailAddr;

    @Schema(description = "사번", example = "999999")
    private String empNo;

    @Schema(description = "회사코드", nullable = false, example = "1000")
    private String comCd;
}