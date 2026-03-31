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
 * @description : 광역출고현황 - 주문현황 목록 조회 요청 DTO
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
@Schema(description = "광역출고현황 - 주문현황 목록 조회 요청 DTO")
public class WdInplanSTOResTab1Dto {
	@Schema(description = "품목번호")
	private String docline;
	
	@Schema(description = "상품코드")
	private String sku;
	
	@Schema(description = "상품명칭")
	private String skuname;
	
	@Schema(description = "플랜트")
	private String plant;
	
	@Schema(description = "저장유무")
	private String channelDmd;
	
	@Schema(description = "저장조건")
	private String storagetype;
	
	@Schema(description = "이체단위")
	private String uomSto;
	
	@Schema(description = "주문수량")
	private String orderqtyWd;
	
	@Schema(description = "분배량")
	private String distributeqtyWd;
	
	@Schema(description = "피킹량")
	private String workqtyWd;
	
	@Schema(description = "검수량 - 출고")
	private String inspectqty;
	
	@Schema(description = "검수량 - 입고")
	private String tostoInspectqty;
	
	@Schema(description = "확정수량 - 출고")
	private String confirmqty;
	
	@Schema(description = "확정수량 - 입고")
	private String tostoConfirmqty;
	
	@Schema(description = "중량 - 출고")
	private String confirmweight;
	
	@Schema(description = "중량 - 입고")
	private String tostoConfirmweight;
	
	@Schema(description = "진행상태 - 출고")
	private String status;
	
	@Schema(description = "진행상태 - 입고")
	private String tostoStatus;
	
	@Schema(description = "삭제여부")
	private String delYn;
}
