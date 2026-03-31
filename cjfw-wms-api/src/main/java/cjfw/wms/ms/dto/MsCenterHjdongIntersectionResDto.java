package cjfw.wms.ms.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2026. CJ Freshway Co. all rights reserved.
 * @author : ChoiHwarang
 * @date : 2026.02.23
 * @description : 센터 행정동 교집합 날짜 응답 DTO
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2026.02.23 ChoiHwarang 생성 </pre>
 */
@Data
@NoArgsConstructor
@Schema(description = "센터 행정동 교집합 날짜 응답 DTO")
public class MsCenterHjdongIntersectionResDto {

	@Schema(description = "행정동 코드")
	private String hjdongCd;

	@Schema(description = "행정동명")
	private String hjdongNm;

	@Schema(description = "시도명")
	private String ctpKorNm;

	@Schema(description = "시군구명")
	private String sigKorNm;

	@Schema(description = "교집합 시작일자")
	private String fromDate;

	@Schema(description = "교집합 종료일자")
	private String toDate;
}
