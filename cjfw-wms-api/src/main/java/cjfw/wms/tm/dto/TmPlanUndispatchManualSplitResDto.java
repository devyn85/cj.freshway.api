package cjfw.wms.tm.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : JuSungbin (wntjdqls9818@cj.net) 
 * @date : 2025.11.07 
 * @description : 수동 분할배차 응답 DTO 
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.11.07 JuSungbin (wntjdqls9818@cj.net) 생성 </pre>
 */
@Data
@Schema(description = "추천 POP 조회 결과")
public class TmPlanUndispatchManualSplitResDto {
	
	@Schema(description = "센터코드")
	private String dccode;
	
	@Schema(description = "거래처코드")
	private String custkey;

	@Schema(description = "거래처명")
	private String custname;

	@Schema(description = "실착지코드")
	private String truthCustkey;

	@Schema(description = "실착지명")
	private String truthCustname;
	
	@Schema(description = "상품명")
	private String skuDescr;
	
	@Schema(description = "전표일자")
	private String slipdt;
	
	@Schema(description = "전표번호")
	private String slipno;
	
	@Schema(description = "전표라인")
	private String slipline;
	
	@Schema(description = "체적")
	private String cube;
	
	@Schema(description = "수량")
	private String orderQty;
	
	@Schema(description = "중량")
	private String weight;
	
	@Schema(description = "상품코드")
	private String sku;
	
	@Schema(description = "저장조건명")
	private String storagetypedesc;
	
	@Schema(description = "단위")
	private String uom;
	
}
