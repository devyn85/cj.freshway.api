package cjfw.wms.sysmgt.func.rolesmappingmenu.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

/**
 * 권한메뉴 COPY Request DTO
 */
@Data
public class RolesMappingMenuCopyReqDto {
	@Schema(description = "copy 대상 권한코드", example = "ROLE_USER", nullable = false)
    @NotEmpty
    private String authority; // copy 대상 권한
	@Schema(description = "신규 생성 권한코드", example = "TMP_TEST", nullable = false)
    @NotEmpty
    private String authCd; // 신규 생성 권한코드
	@Schema(description = "신규 생성 권한명", example = "임시테스트", nullable = false)
    @NotEmpty
    private String authNm; // 신규 생성 권한명
}

/**
 * [API 샘플 예시]
 * {
 *     "authority": "BBS_ADMIN",
 *     "authCd": "TMP_TEST",
 *     "authNm": "임시테스트"
 * }
 */
