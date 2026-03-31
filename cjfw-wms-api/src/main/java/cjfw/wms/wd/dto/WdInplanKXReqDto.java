package cjfw.wms.wd.dto;

import java.util.List;

import cjfw.wms.common.annotation.MultiSearch;
import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author      : JiSooKim (jskim14@cj.net) 
 * @date        : 2025.11.28
 * @description : KX출고진행현황 통합 정보 조회 조건 DTO
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "KX출고진행현황 통합 정보 조회 조건 DTO")
public class WdInplanKXReqDto extends CommonDto {

    @Schema(description = "센터코드")
    private String fixdccode;

    @Schema(description = "납품처 사업자번호")
    private String toVatno;

    @Schema(description = "택배사(공통조회)")
    private String gMultiCourier;

    @Schema(description = "주문타입")
    private String sotype;

    @Schema(description = "납품처명")
    private String toEmpname1;

	/** 관리처코드 */
	@Schema(description = "관리처코드", nullable = false, example = "")
	private String toCustkey;
	
	/** 관리처코드(다중OR검색) */
	@MultiSearch
    @Schema(description = "관리처코드-다중OR검색")
    private List<List<String>> toCustkeyMulti;

    @Schema(description = "출고(배송)일자 From")
    private String fromSlipdt;

    @Schema(description = "출고(배송)일자 To")
    private String toSlipdt;

    /** 상품코드 */
	@Schema(description = "상품코드", nullable = false, example = "")
	private String sku;
	
	/** 상품(다중OR검색) */
	@MultiSearch
    @Schema(description = "상품-다중OR검색")
    private List<List<String>> skuMulti; 

	/** vendor */
	@Schema(description = "vendor", nullable = false, example = "")
	private String vendor;
	
	/** vendor(다중검색) */
	@MultiSearch
    @Schema(description = "vendor-다중OR검색")
    private List<String> vendorMulti; 

    @Schema(description = "저장조건")
    private String storagetype;

    @Schema(description = "사업장")
    private String saledepartment;

	/** carno */
	@Schema(description = "carno", nullable = false, example = "")
	private String carno;
	
	/** carno(다중검색) */
	@MultiSearch
    @Schema(description = "carno-다중OR검색")
    private List<String> carnoMulti; 

    @Schema(description = "주문그룹")
    private String ordergrp;

    @Schema(description = "주문유형")
    private String ordertype;

    @Schema(description = "문서일자 From")
    private String fromDocdt;

    @Schema(description = "문서일자 To")
    private String toDocdt;

    @Schema(description = "납품일자 From")
    private String fromDeliverydt;

    @Schema(description = "납품일자 To")
    private String toDeliverydt;

	/** docno */
	@Schema(description = "docno", nullable = false, example = "")
	private String docno;
	
	/** 주문번호(다중검색) */
	@MultiSearch
    @Schema(description = "주문번호-다중OR검색")
    private List<String> docnoMulti; 

    @Schema(description = "저장유무")
    private String channel;

    @Schema(description = "진행상태")
    private String status;

    @Schema(description = "마감여부(OMS_FLAG)")
    private String closeyn;

    @Schema(description = "삭제여부")
    private String delYn;

    @Schema(description = "사전주문조정의뢰여부")
    private String beforeshotage;

}