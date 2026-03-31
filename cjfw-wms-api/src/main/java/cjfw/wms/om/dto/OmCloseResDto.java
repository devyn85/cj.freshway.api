package cjfw.wms.om.dto;

import java.math.BigDecimal;

import cjfw.wms.common.extend.CommonProcedureDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author      : JangJaeHyun (jhjang43@cj.net)
 * @date        : 2025.09.19
 * @description : 주문마감현황 기능을 구현한 Res DTO Class
 * @issues      :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.09.19        JangJaeHyun (jhjang43@cj.net)       생성
 */
@Data
@Schema(description = "주문마감현황 결과")
public class OmCloseResDto extends CommonProcedureDto {
	@Schema(description = "물류센터 코드", example = "DC001")
    private String dcCode;

    @Schema(description = "고객사 코드", example = "STORER001")
    private String storerKey;

    @Schema(description = "배송일자", example = "20250725")
    private String deliveryDt;

    @Schema(description = "문서 유형", example = "WD")
    private String docType;

    @Schema(description = "문서명")
    private String docName;

    @Schema(description = "작업 프로세스 코드", example = "PO")
    private String workProcessCode;

    @Schema(description = "작업 프로세스명")
    private String workProcessName;

    @Schema(description = "마감 여부", example = "마감완료/진행중")
    private String closeYn;
    
    @Schema(description = "플랜트 코드", example = "PLANT01")
    private String plant;

    @Schema(description = "전표 일자", example = "20250919")
    private String slipDt;

    @Schema(description = "마감 키", example = "CLOSEKEY01")
    private String closeKey;

    @Schema(description = "마감명")
    private String closeName;

    @Schema(description = "마감일", example = "2025-09-19 10:00:00")
    private String closeDate;
    
    @Schema(description = "문서 번호", example = "DOC001")
    private String docNo;

    @Schema(description = "오더 유형")
    private String orderType;

    @Schema(description = "PO/SO 유형")
    private String sopoType;

    @Schema(description = "고객사 키", example = "CUST001")
    private String custKey;

    @Schema(description = "고객사명")
    private String custName;

    @Schema(description = "상품 코드", example = "SKU001")
    private String sku;

    @Schema(description = "상품명")
    private String skuName;

    @Schema(description = "원산국")
    private String countryOfOrigin;

    @Schema(description = "단위 (UOM)")
    private String uom;

    @Schema(description = "주문 수량", example = "100")
    private BigDecimal orderCnt;
    
    @Schema(description = "삭제 수량", example = "100")
    private BigDecimal delCnt;
    
    @Schema(description = "무게", example = "100")
    private BigDecimal weight;
    
    @Schema(description = "확정 수량")
    private BigDecimal confirmQty;
    
    @Schema(description = "오픈 건수")
    private BigDecimal openCnt;

    @Schema(description = "주문 수량")
    private BigDecimal orderQty;
}
