package cjfw.wms.wd.dto;

import java.util.List;

import cjfw.wms.common.extend.CommonProcedureDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author      : JangJaeHyun (jhjang43@cj.net)
 * @date        : 2025.09.25
 * @description : 사전주문 조정의뢰 요청 DTO Class
 * @issues      :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.09.25        JangJaeHyun (jhjang43@cj.net)       생성
 */
@Schema(description = "출고 > 출고작업 > 사전주문 조정의뢰")
@Data
@NoArgsConstructor
public class WdBeforeOrderAdjustRequestReqDto extends CommonProcedureDto {
	
	/** 메인그리드 저장 리스트 */
	List<WdBeforeOrderAdjustRequestReqDto> saveList;
	
	@Schema(description = "센터코드")
    private String dcCode;
	
    @Schema(description = "문서일자")
    private String docDt;
    
    @Schema(description = "문서번호")
    private String docNo;
    
    @Schema(description = "영업라인")
    private String docLine;
    
    @Schema(description = "삭제여부")
    private String delYn;
    
    @Schema(description = "회사코드")
    private String storerKey;
    
    @Schema(description = "배송일자", example = "20250709")
    private String deliveryDate;
    
    @Schema(description = "주문조정구분")
    private String beforeorderYn;
    
    @Schema(description = "상품코드")
    private String multiSku;
    
    @Schema(description = "주문일자")
    private String slipDt;
    
    @Schema(description = "재고무관의뢰")
    private String stockYn;
    
    @Schema(description = "주문번호")
    private String multiDocno;
    
    @Schema(description = "관리처코드")
    private String multiToCustkey;
    
    @Schema(description = "배송그룹")
    private String gMultiCourier;
    
    @Schema(description = "상품코드")
    private String sku;
    
    @Schema(description = "상품명")
    private String skuName;
    
    @Schema(description = "주문수량")
    private String mdRmk;
    
    @Schema(description = "변경사유")
    private String text;
    
    @Schema(description = "대체상품코드")
    private String altSku;
    
    @Schema(description = "재입고일자", example = "20250709")
    private String reDeliveryDate;
    
    @Schema(description = "시작주문일자")
    private String fromSlipDt;
    
    @Schema(description = "종료주문일자")
    private String toSlipDt;
}
