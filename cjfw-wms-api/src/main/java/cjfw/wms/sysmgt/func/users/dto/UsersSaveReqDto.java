package cjfw.wms.sysmgt.func.users.dto;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

/**
 * 사용자 저장 Request DTO
 */
@Data
public class UsersSaveReqDto {
	@Schema(description = "사용자관리 리스트", example = "", nullable = false)
    @Valid
    @NotEmpty
    private List<User> users;

    @Data
    public static class User{
    	@Schema(description = "사용자ID", example = "test1", nullable = false)
    	@NotEmpty(message = "사용자ID는 필수 값입니다.")
        private String userId;
    	@Schema(description = "사용자명", example = "테스트사용자", nullable = false)
        @NotEmpty(message = "사용자명은 필수 값입니다.")
        private String userNm;
    	@Schema(description = "상태코드", example = "01", allowableValues = {"01", "02", "03", "99"})
    	@NotEmpty(message = "상태코드는 필수 값입니다.")
        private String userStatus; // "01"(사용), "02"(잠금상태), "03"(비밀번호초기화 요청), "99"(사용안함)
    	@Schema(description = "계정활성여부", example = "1", allowableValues = {"0", "1"})
    	@NotEmpty(message = "계정활성여부는 필수 값입니다.")
        private String userEnable;
    	@Schema(description = "이메일주소", example = "seokjun.song@cj.net", nullable = true)
    	private String mailAddr;
    	@Schema(description = "GridRow 저장 구분", example = "U", allowableValues = {"I", "U", "D"})
    	@NotEmpty(message = "GridRow 저장 구분는 필수 값입니다.")
        private String rowStatus; // "I"(등록), "U"(수정), "D"(삭제)
    }

    /**
     {
     "users":[
     // 등록
     // {
     //     "userId": "spa-test1",
     //     "userNm": "spatest1",
     //     "userStatus": "01",
     //     "userEnable": "1",
     //     "empNo": "111111",
     //     "mailAddr": "spa1@cj.net",
     //     "rowStatus": "I"
     // }

     // 수정
     {
     "userId": "spa-test1",
     "userNm": "spatest1",
     "userStatus": "01",
     "userEnable": "1",
     "empNo": "111111",
     "mailAddr": "spa1@cj.net",
     "rowStatus": "U"
     }

     // 삭제
     // {
     //     "userId": "spa-test1",
     //     "rowStatus": "D"
     // }

     ]
     }
     */

    /**
     *
     * MAP 데이터 참조
     * 0: {
     *   // 수정
         * EMP_NO: "22222"
         * MAIL_ADDR: "ttt1@test"
         * USER_ENABLE: "1"
         * USER_ID: "tt1"
         * USER_NM: "테스트"
         * USER_STATUS: "01"
         * __rowStatus: "U"
     * },
     * {  // 등록
         * EMP_NO: "1111"
         * MAIL_ADDR: "test@cj.net"
         * USER_ENABLE: 1
         * USER_ID: "tmpTest1"
         * USER_NM: "임시테스트"
         * USER_STATUS: "01"
         * __rowStatus: "I"
     * }
     */
}
