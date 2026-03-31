package cjfw.wms.wd.dto;

import java.util.List;

import cjfw.wms.common.annotation.MultiSearch;
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
 * @date : 2025.06.30
 * @description : 외부비축출고처리 요청 DTO 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE          AUTHOR                  MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.06.30    KimSunHo(sunhokim6229@cj.net)   생성
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "외부비축출고처리 요청") 
public class WdShipmentExDCReqDto extends CommonProcedureDto {	
    /** 저장 리스트 */
    List<WdShipmentExDCResDto> saveList;
    
    /** 저장 리스트 */
    List<WdShipmentExDCPricePopupResDto > savePriceList;
    
	/** 물류센터 */
	@Schema(description = "물류센터", nullable = false, example = "")
	private String dccode;
	
	/** 물류센터 */
    @Schema(description = "물류센터", nullable = false, example = "")
    private String fixdccode;
	
	/** 창고 */
    @Schema(description = "창고", nullable = false, example = "")
    private String organize;
    
    /** 창고 */
    @MultiSearch 
    @Schema(description = "창고", nullable = false, example = "")
    private List<String> organizeMulti;
	
	/** area */
    @Schema(description = "area", nullable = false, example = "")
    private String area;

    /** 시작일자 */
    @Schema(description = "시작일자", nullable = false, example = "")
    private String fromSlipdt;
    
    /** 종료일자 */
    @Schema(description = "종료일자", nullable = false, example = "")
    private String toSlipdt;
    
    /** BL번호 */
    @Schema(description = "BL번호", nullable = false, example = "")
    private String blno;
    
    /** BL 번호 */
    @MultiSearch
    @Schema(description = "BL 번호(다중 – 콤마/개행 구분)", nullable = false, example = "")
    private List<String> blnoMulti;
    
    /** 주문번호 */
    @Schema(description = "주문번호", nullable = false, example = "")
    private String docno;
    
    /** 주문번호 */
    @MultiSearch
    @Schema(description = "주문번호", nullable = false, example = "")
    private List<String> docnoMulti;
    
    /** 진행상태 */
    @Schema(description = "진행상태", nullable = false, example = "")
    private String status;
    
    /** 저장유무 */
    @Schema(description = "저장유무", nullable = false, example = "")
    private String channel;
    
    /** 전량결품여부 */
    @Schema(description = "전량결품여부", nullable = false, example = "")
    private String allshortageYn;
    
    /** 저장조건 */
    @Schema(description = "저장조건", nullable = false, example = "")
    private String storagetype;
    
    /** 상품코드 */
    @Schema(description = "상품코드", nullable = false, example = "")
    private String sku;
    
    /** 상품코드 */
    @MultiSearch
    @Schema(description = "상품코드", nullable = false, example = "")
    private List<List<String>> skuMulti;
    
    /** 주문유형 */
    @Schema(description = "주문유형", nullable = false, example = "")
    private String ordertype;
    
    /** 이력번호 */
    @Schema(description = "이력번호", nullable = false, example = "")
    private String serialno;
    
    /** 계약유형 */
    @Schema(description = "계약유형", nullable = false, example = "")
    private String contracttypeSn;
    
    /** courier */
    @Schema(description = "courier", nullable = false, example = "")
    private String courier;
    
    /** 관리처 코드 */
    @Schema(description = "관리처 코드", nullable = false, example = "")
    private String fromCustkey;
    
    /** 관리처 코드 */
    @MultiSearch
    @Schema(description = "관리처 코드", nullable = false, example = "")
    private List<String> fromCustkeyMulti;
    
    /** 계약업체 코드 */
    @Schema(description = "계약업체 코드", nullable = false, example = "")
    private String wdCustkey;
    
    /** 계약업체 코드 */
    @MultiSearch
    @Schema(description = "계약업체 코드", nullable = false, example = "")
    private List<String> wdCustkeyMulti;
    
    /** 대상확정 처리유형 */
    @Schema(description = "대상확정 처리유형", nullable = false, example = "")
    private String confirmPrcType;
    
    /** 사유코드 */
    @Schema(description = "사유코드", nullable = false, example = "")
    private String inReasoncode;    
    
    /** 사유 내용 */
    @Schema(description = "사유 내용", nullable = false, example = "")
    private String inReasonmsg;
    
    /** 관리처 코드 */
    @Schema(description = "관리처 코드", nullable = false, example = "")
    private String toCustkey;
    
    /** 출고일자 시작일 */
    @Schema(description = "출고일자 시작일", nullable = false, example = "")
    private String fromdt;
    
    /** 출고일자 종료일 */
    @Schema(description = "출고일자 종료일", nullable = false, example = "")
    private String todt;
    
    /** 그룹 데이터번호 */
    @Schema(description = "그룹 데이터번호", nullable = true, example = "")
    private Long serialkeyGroup;
    
    /** STO오더만 조회 */
    @Schema(description = "STO오더만 조회", nullable = true, example = "")
    private Long stoYn;
    
    /** SCM 담당자 */
    @Schema(description = "SCM 담당자", nullable = false, example = "")
    private String scmUser;
    
    /** 전표일자 */
    @Schema(description = "전표일자", nullable = false, example = "")
    private String slipdt;
    
    /** 재고구분 */
    @Schema(description = "재고구분", nullable = false, example = "")
    private String stockid;
   
}
