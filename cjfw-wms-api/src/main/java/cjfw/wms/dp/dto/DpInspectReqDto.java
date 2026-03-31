package cjfw.wms.dp.dto;

import java.math.BigDecimal;
import java.util.List;

import cjfw.wms.common.extend.CommonProcedureDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author      : JangJaeHyun (jhjang43@cj.net)
 * @date        : 2025.05.23
 * @description : 입고검수처리 조회 기능을 구현한 DTO Class
 * @issues      :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.05.23        JangJaeHyun (jhjang43@cj.net)       생성
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "입고검수처리 목록 요청") 
public class DpInspectReqDto extends CommonProcedureDto {
	
	/** 메인그리드 저장 리스트 */
	List<DpInspectTotalResDto> saveList;

	@Schema(description = "입고일자FROM", nullable = false, example = "")
	private String fromSlipdt;
	
	@Schema(description = "입고일자TO", nullable = false, example = "")
	private String toSlipdt;
	
	@Schema(description = "진행상태", nullable = false, example = "")
	private String status;
	
	@Schema(description = "협력사코드", nullable = false, example = "")
	private String partnerCode;
	
	@Schema(description = "상품코드", nullable = false, example = "")
	private String skuCode;
		
	@Schema(description = "미입고처리여부", nullable = false, example = "")
	private String undoneYn;
	
	@Schema(description = "미검수사유", nullable = false, example = "")
	private String reasonCode;
	
	@Schema(description = "저장유무", nullable = false, example = "")
	private String channel;
	
	@Schema(description = "전표번호", nullable = false, example = "")
	private String slipNo;
	
	@Schema(description = "입고일자", nullable = false, example = "")
	private String slipDt;
	
	@Schema(description = "협력사코드", nullable = false, example = "")
	private String fromCustKey;
	
	@Schema(description = "선택 여부", example = "0")
    private String checkYn;

    @Schema(description = "검수 유형", example = "CUSTKEY")
    private String inspectType;

    @Schema(description = "물류센터 코드", example = "2600")
    private String dcCode;

    @Schema(description = "고객사 코드", example = "FW00")
    private String storerKey;

    @Schema(description = "조직 코드", example = "2600")
    private String organize;

    @Schema(description = "고객사 키 (FROM_CUSTKEY)", example = "1000017")
    private String custKey;

    @Schema(description = "문서 일자", example = "20220523")
    private String docDt;

    @Schema(description = "문서 번호", example = "DOC001")
    private String docNo;

    @Schema(description = "상품 코드", example = "SKU001")
    private String sku;

    @Schema(description = "상품명")
    private String skuName;

    @Schema(description = "채널명")
    private String channelName;

    @Schema(description = "주문 수량")
    private BigDecimal orderQty;

    @Schema(description = "검수 수량")
    private BigDecimal inspectQty;

    @Schema(description = "부족 수량")
    private BigDecimal shortageQty;

    @Schema(description = "단위 (UOM)")
    private String uom;

    @Schema(description = "상태명")
    private String statusName;

    @Schema(description = "이송 수량")
    private BigDecimal tranQty;

    @Schema(description = "LOTTABLE01")
    private String lotTable01;

    @Schema(description = "재고 ID")
    private String stockId;

    @Schema(description = "사유 메시지 (문서 D)")
    private String reasonMsg;

    @Schema(description = "참조 02")
    private String reference02;

    @Schema(description = "유효 기간 타입")
    private String durationType;

    @Schema(description = "검수 사유 코드")
    private String inspectReason;

    @Schema(description = "검수 메시지")
    private String inspectMsg;

    @Schema(description = "유효 기간")
    private String duration;

    @Schema(description = "박스당 수량")
    private BigDecimal qtyPerBox;

    @Schema(description = "수정자")
    private String editWho;

    @Schema(description = "크로스 로케이션")
    private String crossLoc;

    @Schema(description = "플랜트 코드")
    private String plant;

    @Schema(description = "플랜트 설명")
    private String plantDescr;
}
