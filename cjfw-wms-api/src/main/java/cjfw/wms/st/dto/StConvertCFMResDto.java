package cjfw.wms.st.dto;

import cjfw.wms.common.extend.CommonProcedureDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved. 
 *
 * @author : KimSunHo(sunhokim6229@cj.net) 
 * @date : 2025.07.11
 * @description : 중계영업확정처리 조회 결과 DTO 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE          AUTHOR                  MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.07.11    KimSunHo(sunhokim6229@cj.net)   생성
 */
@Data
@Schema(description = "중계영업확정처리 조회 결과")
public class StConvertCFMResDto extends CommonProcedureDto {
	/** 커스텀 엑스트라 체크박스 */
	@Schema(description = "커스텀 엑스트라 체크박스", example = "N")
	private String customRowCheckYn = "N";


    /** 물류센터 */
    @Schema(description = "물류센터", nullable = false, example = "")
    private String dccode;
    
    /** 승인번호 */
    @Schema(description = "승인번호", nullable = false, example = "")
    private String mapkeyNo;

    /** 정상주문번호 */
    @Schema(description = "정상주문번호", nullable = false, example = "")
    private String pokey;
    
    /** ADDPOLINE */
    @Schema(description = "ADDPOLINE", nullable = false, example = "")
    private String addpoline;

    /** 조정구매번호 */
    @Schema(description = "조정구매번호", nullable = false, example = "")
    private String dpSourcekey;

    /** 입고일자 */
    @Schema(description = "입고일자", nullable = false, example = "")
    private String deliverydate;

    /** 생성일자 */
    @Schema(description = "생성일자", nullable = false, example = "")
    private String docdt;

    /** FROM_CUSTKEY */
    @Schema(description = "FROM_CUSTKEY", nullable = false, example = "")
    private String fromCustkey;

    /** FROM_CUSTNAME */
    @Schema(description = "FROM_CUSTNAME", nullable = false, example = "")
    private String fromCustname;

    /** 창고코드 */
    @Schema(description = "창고코드", nullable = false, example = "")
    private String organize;

    /** 창고명 */
    @Schema(description = "창고명", nullable = false, example = "")
    private String organizename;

    /** 주문유형 */
    @Schema(description = "주문유형", nullable = false, example = "")
    private String ordertype;

    /** 진행상태 */
    @Schema(description = "진행상태", nullable = false, example = "")
    private String serialinfoCfmYn;

    /** ADD_YN */
    @Schema(description = "ADD_YN", nullable = false, example = "")
    private String addYn;

    /** 생성인 */
    @Schema(description = "생성인", nullable = false, example = "")
    private String createwho;
    
    /** 생성인명 */
    @Schema(description = "생성인명", nullable = false, example = "")
    private String createwhoNm;

    /** 등록인 */
    @Schema(description = "등록인", nullable = false, example = "")
    private String regwho;
    
    /** 등록인명 */
    @Schema(description = "등록인명", nullable = false, example = "")
    private String regwhoNm;

    /** 등록일시 */
    @Schema(description = "등록일시", nullable = false, example = "")
    private String regdate;

    /** 최종 수정자 */
    @Schema(description = "최종 수정자", nullable = false, example = "")
    private String lastwho;
    
    /** 최종 수정자명 */
    @Schema(description = "최종 수정자명", nullable = false, example = "")
    private String lastwhoNm;

    /** LASTDATE */
    @Schema(description = "LASTDATE", nullable = false, example = "")
    private String lastdate;

    /** FONTCOLOR */
    @Schema(description = "FONTCOLOR", nullable = false, example = "")
    private String fontcolor;

    /** BGCOLOR */
    @Schema(description = "BGCOLOR", nullable = false, example = "")
    private String bgcolor;
    
    /** 요율등록여부 */
    @Schema(description = "요율등록여부", nullable = false, example = "")
    private String exdcrateYn;

}
