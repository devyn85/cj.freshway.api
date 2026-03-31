package cjfw.wms.dp.dto;

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
@Schema(description = "외부비축입고처리 요청")
public class DpReceiptExDCReqDto extends CommonProcedureDto {
    
    /** 저장 리스트 */
    List<DpReceiptExDCDetailResDto> saveList;

    /** 물류센터 */
    @Schema(description = "물류센터", nullable = false, example = "")
    private String dccode;
    
    /** 물류센터 */
    @Schema(description = "물류센터", nullable = false, example = "")
    private String fixdccode;
    
    /** 조회시작일자 */
    @Schema(description = "조회시작일자", nullable = false, example = "")
    private String slipdtFrom;
    
    /** 조회종료일자 */
    @Schema(description = "조회종료일자", nullable = false, example = "")
    private String slipdtTo;
    
    /** 조회시작일자 */
    @Schema(description = "조회시작일자", nullable = false, example = "")
    private String docdtFrom;
    
    /** 조회종료일자 */
    @Schema(description = "조회종료일자", nullable = false, example = "")
    private String docdtTo;

    /** 창고코드 */
    @Schema(description = "창고코드", nullable = false, example = "")
    private String organize;
    
    /** 창고코드 */
    @MultiSearch 
    @Schema(description = "창고코드", nullable = false, example = "")
    private List<String> organizeMulti;
    
    /** AREA */
    @Schema(description = "AREA", nullable = false, example = "")
    private String area;
    
    /** 상태 */
    @Schema(description = "상태", nullable = false, example = "")
    private String status;
    
    /** 저장유무 */
    @Schema(description = "저장유무", nullable = false, example = "")
    private String channel;
    
    /** 저장조건 */
    @Schema(description = "저장조건", nullable = false, example = "")
    private String storagetype;
    
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

    /** 승인번호 */
    @Schema(description = "승인번호", nullable = false, example = "")
    private String mapkeyNo;
    
    /** 승인번호라인 */
    @Schema(description = "승인번호라인", nullable = false, example = "")
    private String mapkeyLine;
    
    /** 재고ID */
    @Schema(description = "재고ID", nullable = false, example = "")
    private String stockid;
    
    /** B/L번호 */
    @Schema(description = "B/L번호", nullable = false, example = "")
    private String blno;
    
    /** BL 번호 */
    @MultiSearch
    @Schema(description = "BL 번호(다중 – 콤마/개행 구분)", nullable = false, example = "")
    private List<String> blnoMulti;
    
    /** 이력번호 */
    @Schema(description = "이력번호", nullable = false, example = "")
    private String serialno;

    /** 계약업체코드 */
    @Schema(description = "계약업체코드", nullable = false, example = "")
    private String dpCustkey;
    
    /** 계약업체코드 */
    @MultiSearch
    @Schema(description = "계약업체코드", nullable = false, example = "")
    private List<String> dpCustkeyMulti;
    
    /** 계약유형 */
    @Schema(description = "계약유형", nullable = false, example = "")
    private String contracttype;

    /** 배송사코드 */
    @Schema(description = "배송사코드", nullable = false, example = "")
    private String courier;

    /** 구매유형 */
    @Schema(description = "구매유형", nullable = false, example = "")
    private String ordertype;

    /** 가중량여부 */
    @Schema(description = "가중량여부", nullable = false, example = "")
    private String tempYn;
    
    /** 가/진오더구분 */
    @Schema(description = "가/진오더구분", nullable = false, example = "")
    private String realYn;
    
    /** 진오더생성여부 */
    @Schema(description = "진오더생성여부", nullable = false, example = "")
    private String sokeyYn;

    /** 문서번호*/
    @Schema(description = "문서번호", nullable = false, example = "")
    private String docno;
    
    /** 문서번호*/
    @MultiSearch    
    @Schema(description = "문서번호", nullable = false, example = "")
    private List<String> docnoMulti;

    /** 진행상태 */
    @Schema(description = "진행상태", nullable = false, example = "")
    private String serialinfoCfmYn;
    
    /** 전량결품여부 */
    @Schema(description = "전량결품여부", nullable = false, example = "")
    private String allshortageYn;

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
    
    /** 전표일자 */
    @Schema(description = "전표일자", nullable = false, example = "")
    private String slipdt;
    
    /** 전표번호 */
    @Schema(description = "전표번호", nullable = false, example = "")
    private String slipno;
    
    /** 전표번호 */
    @MultiSearch
    @Schema(description = "전표번호", nullable = false, example = "")
    private List<String> slipnoMulti;
    
    /** 전표번호라인 */
    @Schema(description = "전표번호라인", nullable = false, example = "")
    private String slipline;
    
    /** 조정주문번호 */
    @Schema(description = "조정주문번호", nullable = false, example = "")
    private String dpSourcekey;
    
    /** 속도를 위한 추가 키값 */
    @Schema(description = "속도를 위한 추가 키값", nullable = false, example = "")
    private String tmpDpkey;

    /** 헤더/상세 구분 */
    @Schema(description = "헤더/상세 구분", nullable = false, example = "")
    private String divType;
    
    /** 확정/결품 구분 */
    @Schema(description = "확정/결품 구분", nullable = false, example = "")
    private String cfmType;


}
