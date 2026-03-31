package cjfw.wms.st.dto;

import java.math.BigDecimal;

import cjfw.wms.common.extend.CommonProcedureDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : KwonJungYun (jungyun8667@cj.net)
 * @date : 2025.07.02
 * @description : 외부비축재고폐기요청결재
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.07.02 KwonJungYun (jungyun8667@cj.net) 생성 </pre>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "외부비축재고폐기요청결재 Response DTO")
public class StDisuseRequestApprovalResDto extends CommonProcedureDto {
	/** 커스텀 엑스트라 체크박스 */
	@Schema(description = "커스텀 엑스트라 체크박스", example = "N")
	private String customRowCheckYn = "N";


    /** 센터코드 */
    @Schema(description = "센터코드")
    private String dccode;

    /** 화주사 */
    @Schema(description = "화주사")
    private String storerkey;

    /** 조직 */
    @Schema(description = "조직")
    private String organize;

    /** 창고명 */
    @Schema(description = "창고명")
    private String organizename;

    /** 지역 */
    @Schema(description = "지역")
    private String area;

    /** 문서일자 */
    @Schema(description = "문서일자")
    private String docdt;

    /** 문서번호 */
    @Schema(description = "문서번호")
    private String docno;

    /** 문서라인 */
    @Schema(description = "문서라인")
    private String docline;

    /** 전표일자 */
    @Schema(description = "전표일자")
    private String slipdt;

    /** 전표번호 */
    @Schema(description = "전표번호")
    private String slipno;

    /** 전표라인 */
    @Schema(description = "전표라인")
    private String slipline;

    /** 전표유형 */
    @Schema(description = "전표유형")
    private String sliptype;

    /** 상품코드 */
    @Schema(description = "상품코드")
    private String sku;

    /** 상품명 */
    @Schema(description = "상품명")
    private String skuname;

    /** 단위 */
    @Schema(description = "단위")
    private String uom;

    /** 주문수량 */
    @Schema(description = "주문수량")
    private BigDecimal orderqty;

    /** 미확정수량 */
    @Schema(description = "미확정수량")
    private BigDecimal openqty;

    /** 처리수량 */
    @Schema(description = "처리수량")
    private BigDecimal tranqty;

    /** 주문유형 */
    @Schema(description = "주문유형")
    private String ordertype;

    /** 입출유형 */
    @Schema(description = "입출유형")
    private String iotype;

    /** 조회유형명 */
    @Schema(description = "조회유형명")
    private String inquirytypename;

    /** 유통기한임박여부 */
    @Schema(description = "유통기한임박여부")
    private String neardurationyn;

    /** 유통기한 */
    @Schema(description = "유통기한")
    private String lottable01;

    /** 유통기한 정보 */
    @Schema(description = "유통기한 정보")
    private String durationTerm;

    /** 저장조건 */
    @Schema(description = "저장조건")
    private String storagetype;

    /** 유통기한(일) */
    @Schema(description = "유통기한(일)")
    private String duration;

    /** 유통기한유형 */
    @Schema(description = "유통기한유형")
    private String durationtype;

    /** 재고금액 */
    @Schema(description = "재고금액")
    private BigDecimal stockamt;

    /** 단가 */
    @Schema(description = "단가")
    private BigDecimal price;

    /** 중량 */
    @Schema(description = "중량")
    private BigDecimal weight;

    /** 발생사유코드 */
    @Schema(description = "발생사유코드")
    private String reasoncode;

    /** 코스트센터 코드 */
    @Schema(description = "코스트센터 코드")
    private String other04;

    /** 코스트센터명 */
    @Schema(description = "코스트센터명")
    private String costcentername;

    /** 귀책코드 */
    @Schema(description = "귀책코드")
    private String other02;

    /** 귀책사유명 */
    @Schema(description = "귀책사유명")
    private String approvalreasonname;

    /** 시리얼번호 */
    @Schema(description = "시리얼번호")
    private String serialno;

    /** 원본시리얼번호 */
    @Schema(description = "원본시리얼번호")
    private String serialno_org;

    /** 변환시리얼번호 */
    @Schema(description = "변환시리얼번호")
    private String convserialno;

    /** 구매오더라인 */
    @Schema(description = "구매오더라인")
    private String poline;

    /** 도축일자 */
    @Schema(description = "도축일자")
    private String butcherydt;

    /** 계약처코드 */
    @Schema(description = "계약처코드")
    private String contractcompany;

    /** 계약처명 */
    @Schema(description = "계약처명")
    private String contractcompanyname;

    /** 공장명 */
    @Schema(description = "공장명")
    private String factoryname;

    /** 유효기간FROM */
    @Schema(description = "유효기간FROM")
    private String fromvaliddt;

    /** 유효기간TO */
    @Schema(description = "유효기간TO")
    private String tovaliddt;

    /** 계약구분 */
    @Schema(description = "계약구분")
    private String contracttype;

    /** 계약구분명 */
    @Schema(description = "계약구분명")
    private String contracttypename;

    /** 바코드 */
    @Schema(description = "바코드")
    private String barcode;

    /** 저장위치 */
    @Schema(description = "저장위치")
    private String storageloc;

    /** 수량 */
    @Schema(description = "수량")
    private BigDecimal qty;

    /** 기타 */
    @Schema(description = "기타")
    private String other03;

    /** 로케이션 */
    @Schema(description = "로케이션")
    private String loc;

    /** 로트 */
    @Schema(description = "로트")
    private String lot;

    /** 재고속성 */
    @Schema(description = "재고속성")
    private String stockgrade;

    /** 재고ID */
    @Schema(description = "재고ID")
    private String stockid;

    /** 재고금액 메시지 */
    @Schema(description = "재고금액 메시지")
    private String stockamtmsg;

    /** 평균중량 */
    @Schema(description = "평균중량")
    private String avgweight;

    /** 환산박스 */
    @Schema(description = "환산박스")
    private String calbox;

    /** 실박스예정 */
    @Schema(description = "실박스예정")
    private String realorderbox;

    /** 실박스확정 */
    @Schema(description = "실박스확정")
    private String realcfmbox;

    /** 작업박스 */
    @Schema(description = "작업박스")
    private String tranbox;

    /** 박스 플래그 */
    @Schema(description = "박스 플래그")
    private String boxflag;

    /** 시리얼 유형 */
    @Schema(description = "시리얼 유형")
    private String serialtype;

    /** 등록자명 */
    @Schema(description = "등록자명")
    private String addwho;

    /** 등록일시 */
    @Schema(description = "등록일시")
    private String adddate;

    /** chkamt */
    @Schema(description = "chkamt")
    private String chkamt;

    /** 발생사유 */
    @Schema(description = "발생사유")
    private String reasoncodename;
    
    /** seq */
    @Schema(description = "seq")
    private String seq;
    
	/** 비고 */
	@Schema(description = "비고")
	private String rmk;	    
	
	@Schema(description = "변경귀책주체코드")
	private String toRespPartyCd;

	@Schema(description = "변경귀속부서코드")
	private String toRespDeptCd;

	@Schema(description = "변경거래처코드")
	private String chgCustkey;	

}