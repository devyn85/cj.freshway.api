package cjfw.wms.ib.dto;

import java.math.BigDecimal;

import cjfw.wms.common.extend.CommonDto;
import cjfw.wms.common.extend.CommonProcedureDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : ParkJinWoo 
 * @date : 2025.08.29 
 * @description : 보관료마감처리 현황 res 기능을 구현한 DTO Class
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE AUTHOR MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.08.29 ParkJinWoo 생성
 */
@Schema(description = "보관료마감처리 res DTO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IbCloseStoragefeeStatusResDto extends CommonProcedureDto {
	  /** 창고코드 */
    @Schema(description = "창고코드")
    private String organize;

    /** 재고월 */
    @Schema(description = "재고월")
    private String yyyymm;

    /** 창고명 */
    @Schema(description = "창고명")
    private String organizeNm;

    /** 보관료 */
    @Schema(description = "보관료")
    private BigDecimal totalAmount;

    /** 문서관리번호 */
    @Schema(description = "문서관리번호")
    private String keyNo;

    /** 송장번호 */
    @Schema(description = "송장번호")
    private String invNo;

    /** 인터페이스상태 */
    @Schema(description = "인터페이스상태")
    private String ifStatus;

    /** 상태 */
    @Schema(description = "상태")
    private String status;

    /** 전표번호 */
    @Schema(description = "전표번호")
    private String slipNo;

    /** 정산여부 */
    @Schema(description = "정산여부")
    private String expenseResult;
    
    /** 로케이션 */
    @Schema(description = "로케이션")
    private String storageLocation;
    
    /** 물류센터 */
    @Schema(description = "물류센터")
    private String dcCode;
    
    
}
