package cjfw.wms.ms.dto;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author      : JangJaeHyun (jhjang43@cj.net)
 * @date        : 2025.06.11
 * @description : 상품정보 조회 결과 DTO
 * @issues      :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.06.11        JangJaeHyun (jhjang43@cj.net)       생성
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
@Schema(description = "상품정보 조회 결과 DTO")
public class MsSkuResDto {
	@Schema(description = "체크 여부", example = "0")
    private String checkYn;

    @Schema(description = "데이터 번호 (Serial Key)")
    private String serialKey;

    @Schema(description = "물류센터 코드", example = "2600")
    private String dcCode;

    @Schema(description = "고객사 코드", example = "FW00")
    private String storerKey;

    @Schema(description = "상품 코드", example = "SKU001")
    private String sku;

    @Schema(description = "기본 제품명 (없을 경우 SKU 코드)")
    private String description;

    @Schema(description = "저장 타입 (대분류, 예: 1-저온, 2-상온 등)")
    private String storageType;

    @Schema(description = "유통 기한 관리 방법")
    private String durationType;

    @Schema(description = "유통 기간")
    private String duration;

    @Schema(description = "박스당 낱개 수")
    private BigDecimal qtyPerBox;

    @Schema(description = "개당 실 중량 (Kg)")
    private BigDecimal netWeight;

    @Schema(description = "기본 단위 (UOM)")
    private String baseUom;

    @Schema(description = "구매 단위 (UOM)")
    private String purchaseUom;

    @Schema(description = "회수 단위 (UOM)")
    private String returnUom;

    @Schema(description = "상품 유형 (제품-FERT, 상품-HAWA 등)")
    private String skuType;

    @Schema(description = "상품 클래스")
    private String skuClass;

    @Schema(description = "팔레트당 박스 수")
    private BigDecimal boxPerPlt;

    @Schema(description = "팔레트당 적재 단수")
    private BigDecimal layerPerPlt;

    @Schema(description = "적재 단당 박스 수")
    private BigDecimal boxPerLayer;

    @Schema(description = "적치 유형")
    private String putawayType;

    @Schema(description = "시리얼 관리 여부")
    private String serialYn;

    @Schema(description = "제품 생산 라인1")
    private String line01;

    @Schema(description = "거래처 유형")
    private String custType;

    @Schema(description = "거래처 코드", example = "CUST001")
    private String custKey;

    @Schema(description = "거래처명")
    private String custName;

    @Schema(description = "판매 담당명")
    private String somdName;

    @Schema(description = "라벨 타입")
    private String labelType;
    
    @Schema(description = "생성자")
    private String addWho;
    
    @Schema(description = "생성일자")
    private String addDate;
    
    @Schema(description = "최종변경자")
    private String editWho;
    
    @Schema(description = "최종변경일자")
    private String editDate;
    
    @Schema(description = "시리얼 타입")
    private String serialType;
    
    @Schema(description = "최종 변경자")
    private String editWhoNm;
    
    @Schema(description = "최초 등록자")
    private String addWhoNm;
    
    @Schema(description = "커스텀 엑스트라 체크박스(없으면 전체 선택 해제 안 됨)", example = "N")
    private String customRowCheckYn = "N";
    
    @Schema(description = "3PL업체")
    private String reference01;
    
    /** 체크결과 */
	@Schema(description = "체크결과", example = "N")
	private String processYn;
	
	/** 체크메세지 */
	@Schema(description = "체크메세지", example = "존재하지 않는 거래처유형 코드입니다.")
	private String processMsg;
}
