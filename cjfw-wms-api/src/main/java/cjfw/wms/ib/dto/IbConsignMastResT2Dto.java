package cjfw.wms.ib.dto;

import java.math.BigDecimal;

import cjfw.wms.common.extend.CommonProcedureDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author : 고혜미 (laksjd0606@cj.net)
 * @date : 2025.09.25
 * @description : 정산 > 3PL물류대행수수료정산 > 위탁 물류 처리 - 수수료 정산 결과 DTO
 * @issues :
 *
 *         <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.09.25 고혜미 (laksjd0606@cj.net) 생성
 *         </pre>
 */
@Schema(description = "정산 > 3PL물류대행수수료정산 > 위탁 물류 처리 - 수수료 정산 관리 Response DTO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IbConsignMastResT2Dto extends CommonProcedureDto {
	
	/** 커스텀 엑스트라 체크박스 */
	@Schema(description = "커스텀 엑스트라 체크박스", example = "N")
	private String customRowCheckYn = "N";
	
	/** =====================기준정보TAB===============================*/	
    /** 물류센터 */
    @Schema(description = "물류센터")
    private String dccode;
    
    /** 협력사코드 */
    @Schema(description = "협력사코드")
    private String custkey;    
    
    /** 협력사코드명 */
    @Schema(description = "협력사코드명")
    private String custName;      
    
    /** 상품코드 */
    @Schema(description = "상품코드")
    private String sku;
    
    /** 상품명 */
    @Schema(description = "상품명")
    private String skunm;
    
    /** 매출단위유형 */
    @Schema(description = "매출단위유형")
    private String salesUnitType;
    
    /** 단위 */
    @Schema(description = "단위")
    private String uom;

    /** 입고료(금액) */
    @Schema(description = "입고료(금액)")
    private BigDecimal grAmount;
    
    /** 출고료(금액) */
    @Schema(description = "출고료(금액)")
    private BigDecimal giAmount;
    
    /** 창고료(금액) */
    @Schema(description = "창고료(금액)")
    private BigDecimal stockAmount;
    
    /** 계근료(금액) */
    @Schema(description = "계근료(금액)")
    private BigDecimal wghAmount;
    
    /** 작업료(금액) */
    @Schema(description = "작업료(금액)")
    private BigDecimal workAmount;
    
    /** 운송료(금액) */
    @Schema(description = "운송료(금액)")
    private BigDecimal courierAmount;
    
    /** 입고정산료(율) */
    @Schema(description = "입고정산료(율)")
    private BigDecimal grFeeRt;
    
    /** 출고정산료(율) */
    @Schema(description = "출고정산료(율)")
    private BigDecimal giFeeRt; 
    
    /** 창고정산료(율) */
    @Schema(description = "창고정산료(율)")
    private BigDecimal stockFeeRt;
    
    /** 계근정산료(율) */
    @Schema(description = "계근정산료(율)")
    private BigDecimal wghFeeRt;
    
    /** 작업정산료(율) */
    @Schema(description = "작업정산료(율)")
    private BigDecimal workFeeRt;
    
    /** 운송정산료(율) */
    @Schema(description = "운송정산료(율)")
    private BigDecimal courierFeeRt;
 
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
