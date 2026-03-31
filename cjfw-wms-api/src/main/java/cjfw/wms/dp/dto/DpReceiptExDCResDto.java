package cjfw.wms.dp.dto;

import cjfw.wms.common.extend.CommonProcedureDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved. 
 *
 * @author : KimSunHo(sunhokim6229@cj.net) 
 * @date : 2025.07.14
 * @description : 외부비축입고처리 조회 결과 DTO 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE          AUTHOR                  MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.07.14    KimSunHo(sunhokim6229@cj.net)   생성
 */
@Data
@Schema(description = "외부비축입고처리 조회 결과")
public class DpReceiptExDCResDto extends CommonProcedureDto {
	/** 커스텀 엑스트라 체크박스 */
	@Schema(description = "커스텀 엑스트라 체크박스", example = "N")
	private String customRowCheckYn = "N";


    /** 물류센터 */
    @Schema(description = "물류센터", nullable = false, example = "")
    private String dccode;

    /** 문서번호 */
    @Schema(description = "문서번호", nullable = false, example = "")
    private String docno;
    
    /** 문서번호라인 */
    @Schema(description = "문서번호라인", nullable = false, example = "")
    private String docline;

    /** 고객사 */
    @Schema(description = "고객사", nullable = false, example = "")
    private String storerkey;

    /** 문서유형 */
    @Schema(description = "문서유형", nullable = false, example = "")
    private String doctype;

    /** 구매유형 */
    @Schema(description = "구매유형", nullable = false, example = "")
    private String potype;

    /** 구매유형 */
    @Schema(description = "구매유형", nullable = false, example = "")
    private String potypename;

    /** 창고 */
    @Schema(description = "창고", nullable = false, example = "")
    private String organize;

    /** 창고명 */
    @Schema(description = "창고명", nullable = false, example = "")
    private String organizename;

    /** 주문유형 */
    @Schema(description = "주문유형", nullable = false, example = "")
    private String ordertype;

    /** 주문유형명 */
    @Schema(description = "주문유형명", nullable = false, example = "")
    private String ordertypename;
    
    /** 상품코드 */
    @Schema(description = "상품코드", nullable = false, example = "")
    private String sku;

    /** 상품명 */
    @Schema(description = "상품명", nullable = false, example = "")
    private String skuname;

    /** 상태 */
    @Schema(description = "상태", nullable = false, example = "")
    private String status;

    /** 상태명 */
    @Schema(description = "상태명", nullable = false, example = "")
    private String statusname;

    /** 문서생성일자 */
    @Schema(description = "문서생성일자", nullable = false, example = "")
    private String docdt;

    /** 협력사코드 */
    @Schema(description = "협력사코드", nullable = false, example = "")
    private String fromCustkey;

    /** 협력사명 */
    @Schema(description = "협력사명", nullable = false, example = "")
    private String fromCustname;

    /** 전표일자 */
    @Schema(description = "전표일자", nullable = false, example = "")
    private String slipdt;

    /** 전표번호 */
    @Schema(description = "전표번호", nullable = false, example = "")
    private String slipno;

    /** ALL_CANCEL_STATUS */
    @Schema(description = "ALL_CANCEL_STATUS", nullable = false, example = "")
    private String allCancelStatus;

    /** PLANT */
    @Schema(description = "PLANT", nullable = false, example = "")
    private String plant;

    /** 확정자 */
    @Schema(description = "확정자", nullable = false, example = "")
    private String editwho;

    /** REAL_YN */
    @Schema(description = "REAL_YN", nullable = false, example = "")
    private String realYn;
    
    /** REAL_YN */
    @Schema(description = "REAL_YN", nullable = false, example = "")
    private String realYnNm;

    /** 이력정보확정자 */
    @Schema(description = "이력정보확정자", nullable = false, example = "")
    private String lastwho;
    
    /** 요율등록여부 */
    @Schema(description = "요율등록여부", nullable = false, example = "")
    private String exdcrateYn;
    
    /** 구매요청번호 */
    @Schema(description = "구매요청번호", nullable = false, example = "")
    private String mapkeyNo;
    
    /** 저장유무 */
    @Schema(description = "저장유무", nullable = false, example = "")
    private String channelname;
    

}
