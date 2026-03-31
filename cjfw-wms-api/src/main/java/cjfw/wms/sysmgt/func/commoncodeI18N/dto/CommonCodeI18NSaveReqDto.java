package cjfw.wms.sysmgt.func.commoncodeI18N.dto;

import java.util.List;
import java.util.Map;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * I18 공통코드 저장 Request DTO
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommonCodeI18NSaveReqDto {
	@Schema(description = "다국어 공통코드 목록(언어코드에 따른 Map객체 사용)", example= " ", nullable = false)
	@NotNull(message = "i18nCodeDtls Map Object는 필수 값입니다.")
    private List<Map> i18nCodeDtls; // 언어코드에 따른 동적 Key로인한 Map객체 사용

    /** API 샘플 예시
     {
     "i18nCodeDtls": [
         // 수정
         {
             "comCd": "a1",
             "comGrpCd": "b",
             "ko_kr": "test1",
             "en_us": "en_test1",
             ],
         },
         {
             "comCd": "aaaa",
             "comGrpCd": "b",
             "ko_kr": "test2",
             "en_us": "en_test2",
         }
         ]
     }
     */

    /**
     * MPA 샘플
     *
     * //수정
     * COM_CD: "d-1"
     * COM_GRP_CD: "d"
     * EN_US: "en_usd-111"
     * KO_KR: "d-111-999"
     * __rowStatus: "U"
     *
     *
     */
}
