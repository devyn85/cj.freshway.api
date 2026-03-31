package cjfw.wms.st.dto;

import java.math.BigDecimal;

import cjfw.wms.common.extend.CommonProcedureDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author : sss (kduimux@cj.net)
 * @date : 2025.08.25
 * @description : 재고폐기요청/처리 - 전자결재 Request DTO Class
 * @issues :
 *
 *         <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.08.25 sss (kduimux@cj.net) 생성
 *         </pre>
 */
@Schema(description = "재고폐기요청/처리 - 전자결재 Response DTO")
@Data
public class StDisuseRequestCenterResApprovalDto extends CommonProcedureDto {
	/** 커스텀 엑스트라 체크박스 */
	@Schema(description = "커스텀 엑스트라 체크박스", example = "N")
	private String customRowCheckYn = "N";

    /** 요청월 */
    @Schema(description = "요청월")
    private String requestMm;	

    /** 센터코드 */
    @Schema(description = "센터코드")
    private String dccode;

    /** 화주코드 */
    @Schema(description = "화주코드")
    private String storerkey;

    /** 창고 */
    @Schema(description = "창고")
    private String organize;

    /** 구역 */
    @Schema(description = "구역")
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

    /** 가용수량 */
    @Schema(description = "가용수량")
    private BigDecimal openqty;

    /** 조정수량 */
    @Schema(description = "조정수량")
    private BigDecimal tranqty;

    /** 오더유형 */
    @Schema(description = "오더유형")
    private String ordertype;

    /** 계획오더 */
    @Schema(description = "계획오더")
    private String planorder;

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

    /** 유통기한/기간 */
    @Schema(description = "유통기한/기간")
    private String durationTerm;

    /** 저장조건 */
    @Schema(description = "저장조건")
    private String storagetype;

    /** 유통기한기간 */
    @Schema(description = "유통기한기간")
    private String duration;

    /** 유통기한구분 */
    @Schema(description = "유통기한구분")
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

    /** 사유코드 */
    @Schema(description = "사유코드")
    private String reasoncode;

    /** 기타4 */
    @Schema(description = "기타4")
    private String other04;

    /** 비용센터명 */
    @Schema(description = "비용센터명")
    private String costcentername;

    /** 기타2 */
    @Schema(description = "기타2")
    private String other02;

    /** 승인사유명 */
    @Schema(description = "승인사유명")
    private String approvalreasonname;

    /** 시리얼번호 */
    @Schema(description = "시리얼번호")
    private String serialno;

    /** 원시리얼번호 */
    @Schema(description = "원시리얼번호")
    private String serialnoOrg;

    /** B/L번호 */
    @Schema(description = "B/L번호")
    private String convserialno;

    /** PO라인 */
    @Schema(description = "PO라인")
    private String poline;

    /** 도축일자 */
    @Schema(description = "도축일자")
    private String butcherydt;

    /** 계약거래처 */
    @Schema(description = "계약거래처")
    private String contractcompany;

    /** 계약거래처명 */
    @Schema(description = "계약거래처명")
    private String contractcompanyname;

    /** 공장명 */
    @Schema(description = "공장명")
    private String factoryname;

    /** 유효일자 */
    @Schema(description = "유효일자")
    private String fromvaliddt;

    /** 유효일자2 */
    @Schema(description = "유효일자2")
    private String tovaliddt;

    /** 계약유형 */
    @Schema(description = "계약유형")
    private String contracttype;

    /** 바코드 */
    @Schema(description = "바코드")
    private String barcode;

    /** 재고위치명 */
    @Schema(description = "재고위치명")
    private String storageloc;

    /** 수량 */
    @Schema(description = "수량")
    private BigDecimal qty;

    /** 기타3 */
    @Schema(description = "기타3")
    private String other03;

    /** 로케이션 */
    @Schema(description = "로케이션")
    private String loc;

    /** 로트 */
    @Schema(description = "로트")
    private String lot;

    /** 재고등급 */
    @Schema(description = "재고등급")
    private String stockgrade;

    /** 재고ID */
    @Schema(description = "재고ID")
    private String stockid;

    /** 재고금액메시지 */
    @Schema(description = "재고금액메시지")
    private String stockamtmsg;

    /** 시리얼타입 */
    @Schema(description = "시리얼타입")
    private String serialtype;

    /** 키트상품코드 */
    @Schema(description = "키트상품코드")
    private String kitSku;

    /** 메모2 */
    @Schema(description = "메모2")
    private String memo2;
    
    /** 제조일자 */
    @Schema(description = "제조일자")
    private String manufacturedt;    
    
    /** 소비일자 */
    @Schema(description = "소비일자")
    private String expiredt;    
    
    /** 소비기한잔여율 */
    @Schema(description = "소비기한잔여율")
    private BigDecimal usebydatefreert;    
    
    /** 상태(00:등록,30:요청,50:결재중,90:완료) */
    @Schema(description = "상태(00:등록,30:요청,50:결재중,90:완료)")
    private String status; 
    
    /** 상태명 */
    @Schema(description = "상태명")
    private String statusnm;   
    
    /** 폐기구분 */
    @Schema(description = "폐기구분")
    private String disuseDivReq;
    
    /** 폐기구분 */
    @Schema(description = "폐기구분")
    private String disuseDiv;
    
    /** 폐기구분명 */
    @Schema(description = "폐기구분명")
    private String disuseDivNm;
    
    /** 귀속부서코드 */
    @Schema(description = "귀속부서코드")
    private String respDeptCd;
    
    /** seq */
    @Schema(description = "seq")
    private BigDecimal seq;
    
    /** 거래처코드 */
    @Schema(description = "거래처코드")
    private String custkey;
    
    /** 시리얼키 */
    @Schema(description = "시리얼키")
    private String serialkey;

	/** chkamt */
	@Schema(description = "chkamt")
	private String chkamt;
	
	/** 비고 */
	@Schema(description = "비고")
	private String rmk;
	
	@Schema(description = "변경귀책주체코드")
	private String toRespPartyCd;

	@Schema(description = "변경귀속부서코드")
	private String toRespDeptCd;

	@Schema(description = "변경거래처코드")
	private String chgCustkey;

    /** SAP 단가  */
    @Schema(description = "SAP 단가 ")
    private BigDecimal purchaseprice;
}
