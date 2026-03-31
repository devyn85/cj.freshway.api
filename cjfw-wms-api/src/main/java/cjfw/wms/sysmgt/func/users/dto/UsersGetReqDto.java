package cjfw.wms.sysmgt.func.users.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 사용자 조회 요청 DTO
 */
@Data
public class UsersGetReqDto {
	@Schema(description = "사용자ID", example = "test1", nullable = true)
    private String userId;
	@Schema(description = "사용자명", example = " ", nullable = true)
    private String userNm;
    @Schema(description = "계정활성여부", example = "1", allowableValues = {"0", "1"})
    private String userEnable; // 사용여부
}
