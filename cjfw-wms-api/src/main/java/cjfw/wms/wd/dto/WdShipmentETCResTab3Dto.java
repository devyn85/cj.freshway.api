package cjfw.wms.wd.dto;

import java.math.BigDecimal;

import cjfw.wms.common.extend.CommonProcedureDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;


/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : 고혜미
 * @date : 2025.10.17
 * @description : 매각내역 목록 요청 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.10.17 고혜미 생성 </pre>
 */
@Data
@Schema(description = "매각내역 목록 요청") 
public class WdShipmentETCResTab3Dto extends CommonProcedureDto {
	
	/** 물류센터 */
	@Schema(description = "물류센터")
	private String dccode;
	
	/** 매각등록일 */
	@Schema(description = "매각등록일")
	private String disposeDate;

	/** 재고속성 */
	@Schema(description = "재고속성")
	private String stockgrade;
	
	/** 재고속성 */
	@Schema(description = "재고속성")
	private String stockgradename;
	
	/** 조직코드 */
	@Schema(description = "조직코드")
	private String organize;

	/** 로케이션 */
	@Schema(description = "로케이션")
	private String loc;

	/** 상품코드 */
	@Schema(description = "상품코드")
	private String sku;

	/** 상품명칭 */
	@Schema(description = "상품명칭")
	private String skuname;

	/** 단위 */
	@Schema(description = "단위")
	private String uom;

	/** 재고 구분 LOT */
	@Schema(description = "재고 구분 LOT")
	private String lot;
	
	/** 매각수량 */
	@Schema(description = "매각수량")
	private BigDecimal disposeQty;

	/** 매각금액 */
	@Schema(description = "매각금액")
	private BigDecimal disposeAmount;
	
	/** 비고 */
	@Schema(description = "비고")
	private String rmk;
	
    /** 등록자 */
    @Schema(description = "등록자")
    private String addWho;
    
    /** 등록자명 */
    @Schema(description = "등록자명")
    private String addWhoNm;
    
    /** 등록일시 */
    @Schema(description = "등록일시")
    private String addDate;
    
    /** 수정자 */
    @Schema(description = "수정자")
    private String editWho;
    
    /** 수정자명 */
    @Schema(description = "수정자명")
    private String editWhoNm;
    
    /** 수정일시 */
    @Schema(description = "수정일시")
    private String editDate;

}
