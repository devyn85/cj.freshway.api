package cjfw.wms.wd.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author 		: YeoSeungCheol (pw6375@cj.net) 
 * @date 		: 2025.11.13
 * @description : 광역출고현황 - 출고이력정보 목록 조회 요청 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.11.13 YeoSeungCheol (pw6375@cj.net) 생성 </pre>
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "광역출고현황 - 출고이력정보 목록 조회 요청 DTO")
public class WdInplanSTOResTab3Dto {
	@Schema(description="품목번호")
	private String docline;

	@Schema(description="상품코드")
	private String sku;

	@Schema(description="상품명칭")
	private String skuname;

	@Schema(description="로케이션")
	private String loc;

	@Schema(description="기준일(유통,제조)")
	private String lottable01;

	@Schema(description="단위")
	private String uom;

	@Schema(description="진행수량")
	private String processqty;

	@Schema(description="현장작업량")
	private String workqty;

	@Schema(description="검품수량")
	private String inspectqty;

	@Schema(description="확정수량")
	private String confirmqty;
}
