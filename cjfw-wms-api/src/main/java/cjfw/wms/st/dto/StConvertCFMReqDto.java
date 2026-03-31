package cjfw.wms.st.dto;

import java.math.BigDecimal;
import java.util.List;

import cjfw.wms.common.annotation.MultiSearch;
import cjfw.wms.common.extend.CommonProcedureDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved. 
 *
 * @author : KimSunHo(sunhokim6229@cj.net) 
 * @date : 2025.07.11
 * @description : 중계영업확정처리 요청 DTO 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE          AUTHOR                  MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.07.11    KimSunHo(sunhokim6229@cj.net)   생성
 */
@Data
@Schema(description = "중계영업확정처리 요청")
public class StConvertCFMReqDto extends CommonProcedureDto {
    
    /** 저장 리스트 */
    List<StConvertCFMDetailResDto> saveList;

    /** 물류센터 */
    @Schema(description = "물류센터", nullable = false, example = "")
    private String dccode;
    
    /** 물류센터 */
    @Schema(description = "물류센터", nullable = false, example = "")
    private String fixdccode;
    
    /** area */
    @Schema(description = "area", nullable = false, example = "")
    private String area;
    
    /** 조회시작일자 */
    @Schema(description = "조회시작일자", nullable = false, example = "")
    private String slipdtFrom;
    
    /** 조회종료일자 */
    @Schema(description = "조회종료일자", nullable = false, example = "")
    private String slipdtTo;

    /** 창고코드 */
    @Schema(description = "창고코드", nullable = false, example = "")
    private String organize;
    
    /** 창고코드 */
    @MultiSearch
    @Schema(description = "창고코드", nullable = false, example = "")
    private List<String> organizeMulti;

    /** 협력사코드 */
    @Schema(description = "협력사코드", nullable = false, example = "")
    private String fromCustkey;
    
    /** 협력사코드 */
    @MultiSearch
    @Schema(description = "협력사코드", nullable = false, example = "")
    private List<String> fromCustkeyMulti;

    /** 상품코드 */
    @Schema(description = "상품코드", nullable = false, example = "")
    private String sku;
    
    /** 상품코드 */
    @MultiSearch
    @Schema(description = "상품코드", nullable = false, example = "")
    private List<List<String>> skuMulti;

    /** 발주번호 */
    @Schema(description = "발주번호", nullable = false, example = "")
    private String pokey;
    
    /** 발주번호 */
    @MultiSearch
    @Schema(description = "발주번호", nullable = false, example = "")
    private List<String> pokeyMulti;

    /** 승인번호 */
    @Schema(description = "승인번호", nullable = false, example = "")
    private String mapkeyNo;
    
    /** 승인번호 */
    @MultiSearch
    @Schema(description = "승인번호", nullable = false, example = "")
    private List<String> mapkeyNoMulti;
    
    /** 승인번호라인 */
    @Schema(description = "승인번호라인", nullable = false, example = "")
    private String mapkeyLine;
    
    /** 재고ID */
    @Schema(description = "재고ID", nullable = false, example = "")
    private String stockid;
    
    /** 재고ID */
    @MultiSearch
    @Schema(description = "재고ID", nullable = false, example = "")
    private List<String> stockidMulti;
    
    /** B/L번호 */
    @Schema(description = "B/L번호", nullable = false, example = "")
    private String blno;
    
    /** B/L번호 */
    @MultiSearch
    @Schema(description = "B/L번호", nullable = false, example = "")
    private List<String> blnoMulti;
    
    /** 이력번호 */
    @Schema(description = "이력번호", nullable = false, example = "")
    private String serialno;
    
    /** 이력번호 */
    @MultiSearch
    @Schema(description = "이력번호", nullable = false, example = "")
    private List<String> serialnoMulti;

    /** 계약업체코드 */
    @Schema(description = "계약업체코드", nullable = false, example = "")
    private String contractcustkey;
    
    /** 계약업체코드 */
    @MultiSearch
    @Schema(description = "계약업체코드", nullable = false, example = "")
    private List<String> contractcustkeyMulti;
    
    /** 계약유형 */
    @Schema(description = "계약유형", nullable = false, example = "")
    private String contracttype;

    /** 이체여부 */
    @Schema(description = "이체여부", nullable = false, example = "")
    private String moveYn;

    /** 구매유형 */
    @Schema(description = "구매유형", nullable = false, example = "")
    private String ordertype;

    /** 가중량여부 */
    @Schema(description = "가중량여부", nullable = false, example = "")
    private String tempYn;
    
    /** 가/진오더구분 */
    @Schema(description = "가/진오더구분", nullable = false, example = "")
    private String realYn;

    /** 상품대분류*/
    @Schema(description = "상품대분류", nullable = false, example = "")
    private String skugroup;

    /** 진행상태 */
    @Schema(description = "진행상태", nullable = false, example = "")
    private String serialinfoCfmYn;

    /** 사유코드 */
    @Schema(description = "사유코드", nullable = false, example = "")
    private String reasoncode;
    
    /** 사유 */
    @Schema(description = "사유", nullable = false, example = "")
    private String reasonmsg;
    
    /** 사유 */
    @Schema(description = "사유", nullable = false, example = "")
    private String issueNo;
    
    /** 추가여부 */
    @Schema(description = "추가여부", nullable = false, example = "")
    private String addYn;
    
    /** 중량 */
    @Schema(description = "중량", nullable = false, example = "")
    private BigDecimal grossweight;
    
    /** 주문수량 */
    @Schema(description = "주문수량", nullable = false, example = "")
    private BigDecimal ordrQty;
    
    /** 조정주문번호 */
    @Schema(description = "조정주문번호", nullable = false, example = "")
    private String dpSourcekey;
    
    /** 속도를 위한 추가 키값 */
    @Schema(description = "속도를 위한 추가 키값", nullable = false, example = "")
    private String tmpDpkey;

    /** 속성 */
    @Schema(description = "속성", nullable = false, example = "")
    private String mapDiv;
    
    /** 확정/반려구분 */
    @Schema(description = "확정/반려구분", nullable = false, example = "")
    private String cfmType;
    
    /** 문서번호*/
    @Schema(description = "문서번호", nullable = false, example = "")
    private String docno;
    
    /** 문서번호*/
    @MultiSearch    
    @Schema(description = "문서번호", nullable = false, example = "")
    private List<String> docnoMulti;

}
