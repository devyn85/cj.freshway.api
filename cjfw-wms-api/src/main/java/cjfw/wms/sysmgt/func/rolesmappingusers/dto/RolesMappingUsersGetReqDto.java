package cjfw.wms.sysmgt.func.rolesmappingusers.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

/**
 * 권한사용자 조회 Request DTO
 */
@Data
public class RolesMappingUsersGetReqDto {
	@Schema(description = "권한코드", example = "ROLE_USER", nullable = false)
	@NotEmpty(message = "권한코드는 필수 값입니다.")
    private String authority; // 권한코드
	@Schema(description = "사용자명", example = "테스트", nullable = false)
    private String userNm; // 사용자명
	@Schema(description = "사용자ID", example = "테스트")
	private String userId; // 사용자ID
	@Schema(description = "포함여부", example = "1", allowableValues = {"0", "1"})
    private String include;  // 권한 포함 여부(1 or 0)
	@Schema(description = "페이지 크기", example = "20")
	private int listCount;  // 권한 포함 여부(1 or 0)
	@Schema(description = "시작 행", example = "0")
	private int startRow;
	@Schema(description = "스킵여부", example = "false")
	private boolean skipCount;
	@Schema(description = "사용여부", example = "1", allowableValues = {"0", "1"})
	private String userEnable;  // 권한 포함 여부(1 or 0)

}
