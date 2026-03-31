package cjfw.wms.sysmgt.func.rolesmappingusers.dto;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

/**
 * 권한사용자 저장 Request DTO
 */
@Data
public class RolesMappingUsersSaveReqDto {
	@Schema(description = "권한별 사용자 리스트", example = "", nullable = false)
    @Valid
    @NotEmpty
    private List<RolesMappingUsersSaveReqDto.RoleUser> roleUsers;

    @Data
    public static class RoleUser{
    	@Schema(description = "권한코드", example = "ROLE_USER", nullable = false)
        @NotEmpty(message = "권한코드는 필수 값입니다.")
        private String authority; // 권한코드
    	@Schema(description = "사용자ID", example = "jws", nullable = false)
        @NotEmpty(message = "사용자ID는 필수 값입니다.")
        private String userId; // 사용자ID
    	@Schema(description = "포함여부", example = "1", allowableValues = {"0", "1"})
        @NotEmpty(message = "포함여부는 필수 값입니다.")
        private String include;  // 권한 포함 여부(1 or 0)
    }
}

/**
 * [API 샘플 예시]
 {
     "roleUsers":[
         // 등록
          {
              "authority": "ROLE_USER",
              "userId": "jws",
              "include": "1"
          },

         // 제거
         {
             "authority": "ROLE_USER",
             "userId": "jws",
             "include": "0"
         }
     ]
 }
 */