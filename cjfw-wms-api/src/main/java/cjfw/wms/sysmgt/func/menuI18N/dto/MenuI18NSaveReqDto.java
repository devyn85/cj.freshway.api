package cjfw.wms.sysmgt.func.menuI18N.dto;

import java.util.List;
import java.util.Map;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 메뉴 다국어 저장 Request DTO
 */
@Data
public class MenuI18NSaveReqDto {
	@Schema(description = "다국어 메뉴 목록(언어코드에 따른 Map객체 사용)", example=" ", nullable = false)
	@NotNull(message = "menuI18Ns Map Object는 필수 값입니다.")
    private List<Map> menuI18Ns; // 언어코드에 따른 동적 Key로인한 Map객체 사용
}

/** API 샘플
 {
     "menuI18Ns":[
         // 수정
         {
             "menuId": "BIZ_SAMPLE_MENU_0102",
             "ko_kr": "임시테스트_수정",
             "en_us": "en_임시테스트_수정",
             "rowStatus": "U"
         }
     ]
 }

 */

/**
 * [MPA 참조]
 * EN_US: "en_us임시 테스트_수정"
 * KO_KR: "임시 테스트_수정"
 * MENU_ID: "BIZ_SAMPLE_MENU_0102"
 * REG_DT: "2022-05-30"
 * REG_ID: "devadmin01"
 * __rowStatus: "U"
 */