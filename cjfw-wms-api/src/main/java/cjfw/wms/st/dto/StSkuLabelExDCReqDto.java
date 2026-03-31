package cjfw.wms.st.dto;

import java.util.List;

import cjfw.wms.common.annotation.MultiSearch;
import cjfw.wms.common.extend.CommonProcedureDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author : baechan (c_bae@cj.net)
 * @date : 2025.09.03
 * @description : 상품이력번호등록(재고생성) Master Request DTO Class
 * @issues :
 *         <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.09.03 baechan (c_bae@cj.net) 생성
 *         </pre>
 */
@Schema(description = "상품이력번호등록(재고생성) Master Request DTO")
@Data
public class StSkuLabelExDCReqDto extends CommonProcedureDto {

	 /** 상품코드 */
    @Schema(description = "상품코드", nullable = false, example = "")
    private String sku;
    
    /** 상품코드 */
    @MultiSearch
    @Schema(description = "상품코드", nullable = false, example = "")
    private List<List<String>> skuMulti;
    
    /** 등록타입 */
    @Schema(description = "등록타입", nullable = false, example = "0")
    private String regtype;
    
    /** 협력사코드 */
    @Schema(description = "협력사코드")
    private String fromCustkey;
    
    /** ordertype */
	@Schema(description = "ordertype", nullable = false, example = "")
	private String ordertype;
	
	/** storagetype */
	@Schema(description = "storagetype", nullable = false, example = "")
	private String storagetype;
	
    /** Cust Key */
    @Schema(description = "Cust Key", example = "CUST001")
    private String custkey;
	
    /** slipdt */
	@Schema(description = "slipdt", nullable = false, example = "")
	private String slipdt;
	
	/** slipno */
	@Schema(description = "slipno", nullable = false, example = "")
	private String slipno;
	
	/** serialCheck */
	@Schema(description = "serialCheck", nullable = false, example = "")
	private String serialCheck;
	
	 /** 물류센터코드/명 */
    @Schema(description = "물류센터코드/명")
    private String dccode;
    
    /** 회사 id */
    @Schema(description = "회사 id")
    private String storerkey;
    
    /** 프로세스 타입 */
    @Schema(description = "프로세스 타입")
    private String processtype;

    /** 구역 */
    @Schema(description = "구역")
    private String area;

    /** 바코드 */
    @Schema(description = "바코드")
    private String barcode;

    /** 도축일자 */
    @Schema(description = "도축일자")
    private String butcherydt;

    /** 계약고객키 */
    @Schema(description = "계약고객키")
    private String contractcustkey;

    /** 계약타입 */
    @Schema(description = "계약타입")
    private String contracttype;

    /** 변환로트 */
    @Schema(description = "변환로트")
    private String convertlot;

    /** 변환시리얼번호 */
    @Schema(description = "변환시리얼번호")
    private String convserialno;

    /** DP 문서타입 */
    @Schema(description = "DP 문서타입")
    private String dpDoctype;

    /** DP 전표일자 */
    @Schema(description = "DP 전표일자")
    private String dpSlipdt;

    /** DP 전표라인 */
    @Schema(description = "DP 전표라인")
    private String dpSlipline;

    /** DP 전표번호 */
    @Schema(description = "DP 전표번호")
    private String dpSlipno;

    /** 공장명 */
    @Schema(description = "공장명")
    private String factoryname;

    /** 유효시작일자 */
    @Schema(description = "유효시작일자")
    private String fromvaliddt;

    /** 총중량 */
    @Schema(description = "총중량")
    private String grossweight;

    /** 로트테이블01 */
    @Schema(description = "로트테이블01")
    private String lottable01;

    /** 순중량 */
    @Schema(description = "순중량")
    private String netweight;

    /** 주문수량 */
    @Schema(description = "주문수량")
    private String orderqty;

    /** 조직 */
    @Schema(description = "조직")
    private String organize;

    /** 원산지 */
    @Schema(description = "원산지")
    private String placeoforigin;

    /** 로케이션 */
    @Schema(description = "로케이션", nullable = false, example = "")
    private String loc;
   
    /** PO키 */
    @Schema(description = "PO키")
    private String pokey;

    /** PO라인 */
    @Schema(description = "PO라인")
    private String poline;

    /** 인쇄수량 */
    @Schema(description = "인쇄수량")
    private String printedqty;

    /** 시리얼레벨 */
    @Schema(description = "시리얼레벨")
    private String seriallevel;

    /** 시리얼번호 */
    @Schema(description = "시리얼번호")
    private String serialno;

    /** 시리얼타입 */
    @Schema(description = "시리얼타입")
    private String serialtype;


    /** 유효종료일자 */
    @Schema(description = "유효종료일자")
    private String tovaliddt;

    /** 단위 */
    @Schema(description = "단위")
    private String uom;
  
	/** 지정취소 저장 리스트 */
	List<StSkuLabelExDCResDto> saveList;
	
	List<StSkuLabelExDCResDto> excelUploadList;
	
   
}
