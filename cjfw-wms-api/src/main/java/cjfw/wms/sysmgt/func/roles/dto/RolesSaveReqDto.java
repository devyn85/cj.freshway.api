package cjfw.wms.sysmgt.func.roles.dto;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

/**
 * 권한 저장 Request DTO
 */
@Data
public class RolesSaveReqDto {

	@Schema(description = "권한관리 리스트", example = "", nullable = false)
    @Valid
    @NotEmpty
    private List<RolesSaveReqDto.Role> roles;

    @Data
    public static class Role{
    	@Schema(description = "권한코드", example = "ROLE_USER", nullable = false)
    	@NotEmpty(message = "권한코드는 필수 값입니다.")
        private String authority; // 권한코드
        @Schema(description = "권한명", example = "사용자", nullable = true)
        private String roleNm; // 권한명
        @Schema(description = "설명", example = "사용자", nullable = true)
        private String description; // 설명
        @Schema(description = "GridRow 저장 구분", example = "U", allowableValues = {"I", "U", "D"})
        @NotEmpty(message = "GridRow 저장 구분은 필수 값입니다.")
        private String rowStatus; // "I"(등록), "U"(수정), "D"(삭제)
    }

}

/** API 샘플
 {
 "roles":[
     // 등록
     // {
     //     "authority": "TMP_TEST_1",
     //     "roleNm": "임시 테스트 1",
     //     "description": "테스트용 권한",
     //     "rowStatus": "I"
     // }

     // 수정
     // {
     //     "authority": "TMP_TEST_1",
     //     "roleNm": "임시 테스트 1_수정",
     //     "description": "테스트용 권한_수정",
     //     "rowStatus": "U"
     // }

     // 삭제
     // {
     //     "authority": "TMP_TEST_1",
     //     "rowStatus": "D"
     // }
 ]
 }

 */

/** MPA 참조
 AUTHORITY: "TEST1"
 DESCRIPTION: "임시 테스트11_1"
 REG_DT: "2022-05-23"
 REG_ID: "devadmin01"
 ROLE_NM: "임시 테스트1_1"
 __rowStatus: "U"

 */