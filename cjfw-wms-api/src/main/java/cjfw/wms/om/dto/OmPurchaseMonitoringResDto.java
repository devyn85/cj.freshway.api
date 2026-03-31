package cjfw.wms.om.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JeongHyeongCheol (wjdgudcjf@cj.net) 
 * @date : 2025.10.14 
 * @description : 저장품발주현황 응답 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.10.14 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
 */
@Data
public class OmPurchaseMonitoringResDto {
	
	/** 행번호 */
    @Schema(description = "행번호", example = "")
    private String id;
	
	/** 요청일 */
    @Schema(description = "요청일", example = "")
    private String requestdt;
    
    /** 센터코드 */
    @Schema(description = "센터코드", example = "")
    private String dccode;

    /** 수급담당 키 */
    @Schema(description = "수급담당 키", example = "")
    private String buyerkey;

    /** 수급담당 이름 */
    @Schema(description = "수급담당 이름", example = "")
    private String buyername;

    /** 협력사코드 */
    @Schema(description = "협력사코드", example = "")
    private String custkey;

    /** 협력사명 */
    @Schema(description = "협력사명", example = "")
    private String custkeyname;

    /** 입고업체수 */
    @Schema(description = "입고업체수", example = "")
    private String custkeyCnt;

    /** 상품 코드 */
    @Schema(description = "상품 코드", example = "")
    private String sku;

    /** 상품명 */
    @Schema(description = "상품명", example = "")
    private String skuname;
    
    /** 대물량발주내역 */
    @Schema(description = "대물량발주내역", example = "")
    private String bulkPoMemo;

    /** 상품 수 */
    @Schema(description = "상품 수", example = "")
    private String skuCnt;

    /** 주문 수량 */
    @Schema(description = "주문 수량", example = "")
    private String orderQty;

    /** 중량 (kg) */
    @Schema(description = "중량 (kg)", example = "")
    private String weight;

    /** 중량2 (kg) */
    @Schema(description = "중량2 (kg)", example = "")
    private String weight2;

    /** 배송 거래처 수 */
    @Schema(description = "배송 거래처 수", example = "")
    private String dpCustkeyCnt;

    /** 배송 상품 수 */
    @Schema(description = "배송 상품 수", example = "")
    private String dpSkuCnt;

    /** 배송 중량 (kg) */
    @Schema(description = "배송 중량 (kg)", example = "")
    private String dpWeight;

    /** 배송 중량2 (kg) */
    @Schema(description = "배송 중량2 (kg)", example = "")
    private String dpWeight2;

    /** 팔레트 수 */
    @Schema(description = "팔레트 수", example = "")
    private String plt;

    /** 박스 수 */
    @Schema(description = "박스 수", example = "")
    private String box;

    /** 그룹핑 레벨 */
    @Schema(description = "그룹핑 레벨", example = "")
    private String lev;

    /** 레벨 텍스트 */
    @Schema(description = "레벨 텍스트", example = "")
    private String levText;

    /** 트리 레벨 */
    @Schema(description = "트리 레벨", example = "")
    private String tlevel;

    /** 펼침/닫힘 상태 */
    @Schema(description = "펼침/닫힘 상태", example = "")
    private String blPlus;

    /** 표시 여부 */
    @Schema(description = "표시 여부", example = "")
    private String blShow;
    
	/** 수정자 */
	@Schema(description = "수정자", example = "")
	private String addwho;

	/** 최초등록시간 */
	@Schema(description = "최초등록시간", example = "")
	private String adddate;

	/** 수정자 */
	@Schema(description = "수정자", example = "")
	private String editwho;

	/** 최종변경시간 */
	@Schema(description = "최종변경시간", example = "")
	private String editdate;
	
	/** 등록자명 */
    @Schema(description = "등록자명", example = "")
    private String regNm;

    /** 수정자명 */
    @Schema(description = "수정자명", example = "")
    private String updNm;
		
}