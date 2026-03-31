package cjfw.wms.wd.dto;

import java.math.BigDecimal;

import cjfw.wms.common.extend.CommonProcedureDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : KimSunHo(sunhokim6229@cj.net) 
 * @date : 2025.11.13 
 * @description : 외부비축출고처리 운임단가 조회 결과 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE          AUTHOR                 MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.11.13    KimSunHo(sunhokim6229@cj.net)   생성
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "외부비축출고처리 운임단가 조회 결과") 
public class WdShipmentExDCPricePopupResDto extends CommonProcedureDto {
    
    /** 그룹 데이터번호 */
    @Schema(description = "그룹 데이터번호", nullable = true, example = "")
    private Long serialkeyGroup;
    
    /** 데이터번호 */
    @Schema(description = "데이터번호", nullable = true, example = "")
    private Long serialkey;
    
    /** 운송비일련번호(TM_CARRIER_PRICE-SERIALKEY) */
    @Schema(description = "운송비일련번호(TM_CARRIER_PRICE-SERIALKEY)", nullable = true, example = "")
    private Long tcrSerialkey;
    
    /** 노선아이디 */
    @Schema(description = "노선아이디", nullable = true, example = "")
    private BigDecimal routeId;

    /** 노선명 */
    @Schema(description = "노선명", nullable = true, example = "")
    private String routeNm;

    /** 운송사 */
    @Schema(description = "운송사", nullable = true, example = "")
    private String courier;
    
    /** 운송사명 */
    @Schema(description = "운송사명", nullable = true, example = "")
    private String courierNm;

    /** 톤급 */
    @Schema(description = "톤급", nullable = true, example = "")
    private String ton;

    /** 톤급명 */
    @Schema(description = "톤급명", nullable = true, example = "")
    private String tonNm;
    
    /** 저장조건 */
    @Schema(description = "저장조건", nullable = true, example = "")
    private String storagetype;

    /** 저장조건명 */
    @Schema(description = "저장조건명", nullable = true, example = "")
    private String storagetypeNm;
    
    /** 금액(단가) */
    @Schema(description = "금액(단가)", nullable = true, example = "")
    private BigDecimal price;

    /** 차량대수 */
    @Schema(description = "차량대수", nullable = true, example = "")
    private int carcnt;
    
    /** 운송료 */
    @Schema(description = "운송료", nullable = true, example = "")
    private BigDecimal shipFee;
    
    /** 추가비용 */
    @Schema(description = "추가비용", nullable = true, example = "")
    private BigDecimal addFee;
    
    /** 비고 */
    @Schema(description = "비고", nullable = true, example = "")
    private String rmk;
    
    /** 채번 번호 */
    @Schema(description = "채번 번호", nullable = true, example = "")
    private Long transactionSn;

}
