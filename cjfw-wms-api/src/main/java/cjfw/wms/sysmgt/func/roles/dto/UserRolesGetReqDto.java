package cjfw.wms.sysmgt.func.roles.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

/**
 * 사용자 권한 조회 Request DTO
 */
@Data
public class UserRolesGetReqDto {
	@Schema(description = "권한코드", example = " ", nullable = true)
	private String authority; // 권한코드
	@Schema(description = "권한명", example = " ", nullable = true)
    private String roleNm; // 권한명
	@Schema(description = "사용자ID", example = "devadmin01", nullable = false)
    @NotEmpty(message = "사용자ID는 필수 값입니다.")
    private String userId; // 사용자Id
	@Schema(description = "사용자명", example = " ", nullable = true)
	private String userNm; // 사용자명
	@Schema(description = "포함여부", example = "1", allowableValues = {"0", "1"})
    private String include;//포함여부
}
