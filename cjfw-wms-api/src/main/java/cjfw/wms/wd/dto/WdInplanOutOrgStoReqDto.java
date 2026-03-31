package cjfw.wms.wd.dto;

import cjfw.wms.common.extend.CommonProcedureDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : ParkJinWoo 
 * @date : 2025.11.17 
 * @description : 외부창고운송비현황 기능을 구현한 Controller Class 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE AUTHOR MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.11.17 ParkJinWoo 생성
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "외부창고운송비현황 요청") 
public class WdInplanOutOrgStoReqDto extends CommonProcedureDto {
	/** 출고일자 FROM */
    @Schema(description = "출고일자 FROM")
    private String fromDate;

    /** 출고일자 TO */
    @Schema(description = "출고일자 TO")
    private String toDate;

    /** 관리처코드 */
    @Schema(description = "관리처코드")
    private String custKey;

    /** 창고코드(ORGANIZE) */
    @Schema(description = "창고코드(ORGANIZE)")
    private String organize;

    /** 오더번호(SLIPNO) */
    @Schema(description = "오더번호(SLIPNO)")
    private String slipNo;

    /** 상품코드 */
    @Schema(description = "상품코드")
    private String sku;

    /** BL번호(CONVSERIALNO) */
    @Schema(description = "BL번호(CONVSERIALNO)")
    private String convSerialNo;

    /** 이력번호(SERIALNO) */
    @Schema(description = "이력번호(SERIALNO)")
    private String serialNo;

    /** 계약업체코드(CONTRACTCUSTKEY) */
    @Schema(description = "계약업체코드(CONTRACTCUSTKEY)")
    private String contractCustKey;

    /** 계약유형(CONTRACTTYPE) */
    @Schema(description = "계약유형(CONTRACTTYPE)")
    private String contractType;
    
    private String dcCode;
    
    private String storagetype;
    private String carcapacity;
	
}
