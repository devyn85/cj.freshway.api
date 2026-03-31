package cjfw.wms.wd.dto;

import java.util.List;

import cjfw.wms.common.annotation.MultiSearch;
import cjfw.wms.common.extend.CommonProcedureDto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : 공두경 (medstorm@cj.net) 
 * @date : 2025.05.09 
 * @description : 출고진행현황 목록 요청 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.05.09 공두경 (medstorm@cj.net) 생성 </pre>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "출고진행현황 목록 요청") 
public class WdInplanReqDto extends CommonProcedureDto {
	
	/** 메인그리드 저장 리스트 */
	List<WdInplanResDto> saveList;
	
    /** 물류센터 */
    @NotBlank(message = "물류센터 필수 입력 값입니다.")
    @Schema(description = "물류센터")
    private String fixdccode;		
    
    /** 창고-다중선택 */
	@Schema(description = "창고-다중선택", nullable = false, example = "4900,8774")
	private String organize;
	
    /** 창고(다중검색) */
	@MultiSearch
    @Schema(description = "창고-다중OR검색")
    private List<String> organizeMulti; 
	
	
	/** 출고일자FROM */
	@Schema(description = "출고일자FROM", nullable = false, example = "")
	private String fromSlipdt;
	
	/** 출고일자TO */
	@Schema(description = "출고일자TO", nullable = false, example = "")
	private String toSlipdt;
	
	/** fromDocdt */
	@Schema(description = "fromDocdt", nullable = false, example = "")
	private String fromDocdt;
	
	/** toDocdt */
	@Schema(description = "toDocdt", nullable = false, example = "")
	private String toDocdt;
	
	/** 주문번호-다중선택 */
	@Schema(description = "주문번호-다중선택", nullable = false, example = "4900,8774")
	private String docno;
	
    /** 주문번호(다중검색) */
	@MultiSearch
    @Schema(description = "주문번호-다중OR검색")
    private List<String> docnoMulti; 
	
	
	/** multiDocnoList  */	
	//private String[] multiDocnoList;
	
	
	/** 삭제여부 */
	@Schema(description = "삭제여부", nullable = false, example = "Y")
	private String delYn;
	
	/** 관리처코드 */
	@Schema(description = "관리처코드", nullable = false, example = "")
	private String toCustkey;
	
    /** 관리처코드(다중OR검색) */
	@MultiSearch
    @Schema(description = "관리처코드-다중OR검색")
    private List<List<String>> toCustkeyMulti; 
	
	
	
	/** multiToCustkeyList  */	
	//private String[] multiToCustkeyList;
	
	
	/** 상품코드 */
	@Schema(description = "상품코드", nullable = false, example = "")
	private String sku;
	
    /** 상품(다중OR검색) */
	@MultiSearch
    @Schema(description = "상품-다중OR검색")
    private List<List<String>> skuMulti;    	
	
	/** multiSku  */	
	//private String[] multiSku;
	
	/** 통합배송 */
	@Schema(description = "통합배송", nullable = false, example = "")
	private String tpltype;
	
	/** 주문사유 */
	@Schema(description = "주문사유", nullable = false, example = "")
	private String ordertype;
	
	/** 영업사원 */
	@Schema(description = "영업사원", nullable = false, example = "")
	private String toEmpname1;
	
	/** 사업장 */
	@Schema(description = "사업장", nullable = false, example = "")
	private String saledepartment;
	
	/** 진행상태 */
	@Schema(description = "진행상태", nullable = false, example = "")
	private String status;
	
	/** 저장유무 */
	@Schema(description = "저장유무", nullable = false, example = "")
	private String channel;
	
	/** 저장조건 */
	@Schema(description = "저장조건", nullable = false, example = "")
	private String storagetype;
	
	/** 마감여부 */
	@Schema(description = "마감여부", nullable = false, example = "")
	private String closeyn;
	
	/** 전주문조정의뢰여부 */
	@Schema(description = "전주문조정의뢰여부", nullable = false, example = "")
	private String beforeshotage;
	
	/** 차량번호 */
	@Schema(description = "차량번호", nullable = false, example = "")
	private String carno;	
	
    /** 차량번호(다중검색) */
	@MultiSearch
    @Schema(description = "차량번호-다중OR검색")
    private List<String> carnoMulti; 	

	/** multiCarno  */	
	//private String[] multiCarno;
	
	/** 주문그룹 */
	@Schema(description = "주문그룹", nullable = false, example = "")
	private String ordergrp;
	
	
	/** dccode */
	@Schema(description = "dccode", nullable = false, example = "")
	private String dccode;
	
	/** storerkey */
	@Schema(description = "storerkey", nullable = false, example = "")
	private String storerkey;
	
	/** area */
	@Schema(description = "area", nullable = false, example = "")
	private String area;
	
	
	/** multiDccode */
	@Schema(description = "multiDccode", nullable = false, example = "")
	private String multiDccode;
	
	/** multiStorerkey */
	@Schema(description = "multiStorerkey", nullable = false, example = "")
	private String multiStorerkey;
	
	/** multiArea */
	@Schema(description = "multiArea", nullable = false, example = "")
	private String multiArea;
	
	/** multiOrganize */
	@Schema(description = "multiOrganize", nullable = false, example = "")
	private String multiOrganize;
	
	
	
	/** toVatno */
	@Schema(description = "toVatno", nullable = false, example = "")
	private String toVatno;
	
	/** multiCourier */
	@Schema(description = "multiCourier", nullable = false, example = "")
	private String multiCourier;
	
	/** sotype */
	@Schema(description = "sotype", nullable = false, example = "")
	private String sotype;
	

	/** vendor */
	@Schema(description = "vendor", nullable = false, example = "")
	private String vendor;
	
    /** vendor(다중OR검색) */
	@MultiSearch
    @Schema(description = "vendor-다중OR검색")
    private List<List<String>> vendorMulti; 	
	
	/** multiVendorList  */	
	//private String[] multiVendorList;
	

	/** fromDeliverydt */
	@Schema(description = "fromDeliverydt", nullable = false, example = "")
	private String fromDeliverydt;
	
	/** toDeliverydt */
	@Schema(description = "toDeliverydt", nullable = false, example = "")
	private String toDeliverydt;
	
	/** 유통이력 */
	@Schema(description = "유통이력", nullable = false, example = "")
	private String serialtype;
}
