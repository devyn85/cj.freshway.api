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
 * @description : 정산 > 3PL물류대행수수료정산 > 위탁 물류 처리 - 수수료 정산 요청 DTO
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
public class IbConsignMastReqDto extends CommonProcedureDto {
	
    @Schema(description = "멀티 물류센터 코드")
    private String multiDcCode[];
    
    @Schema(description = "물류센터 코드")
    private String dccode;

    /** 협력사코드 */
    @Schema(description = "협력사코드")   
    private String custkey;
    
    /** 정산월 */
    @Schema(description = "정산월")   
    private String month;
    
    /** 상품코드 */
    @Schema(description = "상품코드")
    private String sku;
    
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
    
    /** 입고수수료(율) */
    @Schema(description = "입고수수료(율)")
    private BigDecimal grFeeRt;
    
    /** 출고수수료(율) */
    @Schema(description = "출고수수료(율)")
    private BigDecimal giFeeRt; 
    
    /** 창고수수료(율) */
    @Schema(description = "창고수수료(율)")
    private BigDecimal stockFeeRt;
    
    /** 계근수수료(율) */
    @Schema(description = "계근수수료(율)")
    private BigDecimal wghFeeRt;
    
    /** 작업수수료(율) */
    @Schema(description = "작업수수료(율)")
    private BigDecimal workFeeRt;
    
    /** 운송수수료(율) */
    @Schema(description = "운송수수료(율)")
    private BigDecimal courierFeeRt;

	
}
