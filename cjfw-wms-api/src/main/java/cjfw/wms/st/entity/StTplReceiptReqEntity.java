package cjfw.wms.st.entity;

import java.math.BigDecimal;

import cjfw.wms.common.extend.CommonProcedureDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved. 
 *
 * @author : KimSunHo(sunhokim6229@cj.net) 
 * @date : 2025.07.11 
 * @description : 중계영업확정처리 Entity
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE          AUTHOR                  MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.07.11    KimSunHo(sunhokim6229@cj.net)   생성
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "중계영업확정처리 Entity") 
public class StTplReceiptReqEntity extends CommonProcedureDto {
    
	  /** 인도예정일자 */
    @Schema(description = "인도예정일자")
    private String deliveryDate;

    /** 요청일자 */
    @Schema(description = "요청일자")
    private String docdt;

    /** 문서번호 */
    @Schema(description = "문서번호")
    private String docNo;

    /** 문서라인번호 */
    @Schema(description = "문서라인번호")
    private Integer docLine;

    /** 창고코드 */
    @Schema(description = "창고코드")
    private String organize;

    /** 상품코드 */
    @Schema(description = "상품코드")
    private String sku;

    /** 상품명 */
    @Schema(description = "상품명")
    private String skuName;

    /** 기준단위 */
    @Schema(description = "기준단위")
    private String baseUom;

    /** 주문수량 */
    @Schema(description = "주문수량")
    private BigDecimal orderQty;

    /** BOX 수량 */
    @Schema(description = "BOX 수량")
    private BigDecimal boxQty;

    /** PLT 수량 */
    @Schema(description = "PLT 수량")
    private BigDecimal pltQty;

    /** 입고예정일자 */
    @Schema(description = "입고예정일자")
    private String slipDt;

    /** 소비기한(남은/총기간 표기) */
    @Schema(description = "소비기한 표기(남은/총기간)")
    private String durationTerm;

    /** 소비기한 잔여율(%) */
    @Schema(description = "소비기한 잔여율(%)")
    private String durationRate;

    /** 기준일자(LOT 기준) */
    @Schema(description = "기준일자(LOT 기준)")
    private String lottable01;

    /** 문서유형 */
    @Schema(description = "문서유형")
    private String docType;

    /** 입출구분 */
    @Schema(description = "입출구분")
    private String ioType;

    /** 주문유형 */
    @Schema(description = "주문유형")
    private String orderType;

    /** 작업프로세스코드 */
    @Schema(description = "작업프로세스코드")
    private String workProcessCode;

    /** 화주코드 */
    @Schema(description = "화주코드")
    private String storerKey;

    /** 수신거래처코드 */
    @Schema(description = "수신거래처코드")
    private String toCustKey;

    /** 수신거래처명 */
    @Schema(description = "수신거래처명")
    private String toCustName;

    /** 발신거래처코드 */
    @Schema(description = "발신거래처코드")
    private String fromCustKey;

    /** 발신거래처명 */
    @Schema(description = "발신거래처명")
    private String fromCustName;

    /** 재고ID */
    @Schema(description = "재고ID")
    private String stockId;

    /** 이력번호(시리얼) */
    @Schema(description = "이력번호(시리얼)")
    private String serialNo;

    /** B/L 번호(컨버전) */
    @Schema(description = "B/L 번호(컨버전)")
    private String convSerialNo;
    /** 비고 */
    @Schema(description = "비고 (dataField: rmk)")
    private String rmk;
    
    private String custType;
    private String dcCode;
    private String ifId;
    private String tplType;
    private String tplBcnrId;
    private String otcoGrpcd;
    private String tplDtlType;
    private String openTime;
    private String ifDestination;
    private String custKey;
    /** 행 상태 */
    @Schema(description = "행상태")
    private String rowStatus;
    
}
