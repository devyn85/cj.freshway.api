package cjfw.wms.ms.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved. 
 *
 * @author : ParkJinWoo 
 * @date : 2025.06.04 
 * @description : 외부창고요율관리 Entity
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE AUTHOR MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.06.04 ParkJinWoo 생성
 */
@Data
public class MsExDcRateEntity extends CommonDto{
	 
	@Schema(description = "데이터번호")
	private String serialKey;

//	private String storerKey;
	private String storerkey;
	private String fromDate;
	private String toDate;
	private String dcCode;
	private String organize;
	private String specCode;
	private String sku;
	private String custKey;
	private String expenseType;

	private BigDecimal grPrice;
	private BigDecimal giPrice;
	private BigDecimal storagePrice;
	private BigDecimal stoGrPrice;
	private BigDecimal stoGiPrice;

	private String storageType;
	private String exDcRateRank;

	// (상·하위 요율 주석 구간은 그대로 두었습니다)
	private String fixDcCode;
	private String delYn;
	private String workPrice;
	private String addWho;
	private String editWho;
	private String rowStatus;
	private String state;
	private LocalDateTime addDate;
	private LocalDateTime editDate;
	
	/** 파렛트단가 */
    @Schema(description = "파렛트단가", nullable = true, example = "")
    private BigDecimal pltPrice;
	
	private String storageTypeSku;

	@Schema(description = "창고단가", nullable = true, example = "")
	private String areaPriceUom;
	    
	    @Schema(description = "창고단가", nullable = true, example = "")
	    private BigDecimal wghPrice;
		/** 프로시저 실행 성공여부 */
		@Schema(description = "프로시저 실행 성공여부", example = "")
		private Integer success;
		
		/** 프로시저 실행 에러코드 */
		@Schema(description = "프로시저 실행 에러코드", example = "")
		private Integer err;
		
		/** 프로시저 실행 에러메시지 */
		@Schema(description = "프로시저 실행 에러메시지", example = "")
		private String errmsg;
}