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
@Data
@Schema(description = "재고폐기요청/처리 - 전자결재 Response DTO")
public class StDisuseRequestCenterResProcessDto extends CommonProcedureDto {
    /** 화주코드 */
    @Schema(description = "화주코드")
    private String storerkey;  
    
	/** 커스텀 엑스트라 체크박스 */
	@Schema(description = "커스텀 엑스트라 체크박스", example = "N")
	private String customRowCheckYn = "N";


    /** 센터코드 */
    @Schema(description = "센터코드")
    private String dccode;


    /** 조직 */
    @Schema(description = "조직")
    private String organize;

    /** 구역 */
    @Schema(description = "구역")
    private String area;

    /** 문서유형 */
    @Schema(description = "문서유형")
    private String doctype;

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

    /** 오더유형 */
    @Schema(description = "오더유형")
    private String ordertype;

    /** 입출유형 */
    @Schema(description = "입출유형")
    private String iotype;

    /** 전표유형 */
    @Schema(description = "전표유형")
    private String sliptype;

    /** 재고유형명 */
    @Schema(description = "재고유형명")
    private String stocktypenm;

    /** 재고유형 */
    @Schema(description = "재고유형")
    private String stocktype;

    /** 재고등급 */
    @Schema(description = "재고등급")
    private String stockgrade;

    /** 재고등급명 */
    @Schema(description = "재고등급명")
    private String stockgradename;

    /** 존 */
    @Schema(description = "존")
    private String zone;

    /** 로케이션 */
    @Schema(description = "로케이션")
    private String loc;

    /** 상품코드 */
    @Schema(description = "상품코드")
    private String sku;

    /** 상품명 */
    @Schema(description = "상품명")
    private String skuname;

    /** 로트 */
    @Schema(description = "로트")
    private String lot;

    /** 단위 */
    @Schema(description = "단위")
    private String uom;

    /** 조정수량 */
    @Schema(description = "조정수량")
    private String tranqty;

    /** 폐기유형 */
    @Schema(description = "폐기유형")
    private String disusetype;

    /** 폐기유형명 */
    @Schema(description = "폐기유형명")
    private String disusetypename;

    /** 사유코드 */
    @Schema(description = "사유코드")
    private String reasoncode;

    /** 사유코드명 */
    @Schema(description = "사유코드명")
    private String reasoncodename;

    /** 귀책 */
    @Schema(description = "귀책")
    private String imputetype;

    /** 귀책명 */
    @Schema(description = "귀책명")
    private String imputetypename;

    /** 물류비귀책여부 */
    @Schema(description = "물류비귀책여부")
    private String processmain;

    /** 귀속부서 */
    @Schema(description = "귀속부서")
    private String costcd;

    /** 귀속부서명 */
    @Schema(description = "귀속부서명")
    private String costcdname;

    /** 거래처코드 */
    @Schema(description = "거래처코드")
    private String custkey;

    /** 거래처명 */
    @Schema(description = "거래처명")
    private String custname;

    /** 유통기한임박여부 */
    @Schema(description = "유통기한임박여부")
    private String neardurationyn;

    /** 유통기한 */
    @Schema(description = "유통기한")
    private String lottable01;

    /** 유통기간(잔여/전체) */
    @Schema(description = "유통기간(잔여/전체)")
    private String durationTerm;

    /** 재고ID */
    @Schema(description = "재고ID")
    private String stockid;

    /** 이력번호 */
    @Schema(description = "이력번호")
    private String serialno;

    /** B/L번호 */
    @Schema(description = "B/L번호")
    private String convserialno;

    /** 이력레벨 */
    @Schema(description = "이력레벨")
    private String seriallevel;

    /** 이력타입 */
    @Schema(description = "이력타입")
    private String serialtype;

    /** 공장명 */
    @Schema(description = "공장명")
    private String factoryname;

    /** 색상명 */
    @Schema(description = "색상명")
    private String colordescr;

    /** 원산지 */
    @Schema(description = "원산지")
    private String placeoforigin;

    /** 유통기한기간 */
    @Schema(description = "유통기한기간")
    private String duration;

    /** 유통기한구분 */
    @Schema(description = "유통기한구분")
    private String durationtype;

    /** 도축일자 */
    @Schema(description = "도축일자")
    private String butcherydt;

    /** 계약거래처 */
    @Schema(description = "계약거래처")
    private String contractcompany;

    /** 계약거래처명 */
    @Schema(description = "계약거래처명")
    private String contractcompanyname;

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

    /** 결재상태 */
    @Schema(description = "결재상태")
    private String approvalstatus;

    /** 결재상태명 */
    @Schema(description = "결재상태명")
    private String approvalstatusname;

    /** 결재요청번호 */
    @Schema(description = "결재요청번호")
    private String approvalreqno;

    /** 결재번호 */
    @Schema(description = "결재번호")
    private String approvalno;

    /** 결재일자 */
    @Schema(description = "결재일자")
    private String approvaldate;

    /** 체크플래그 */
    @Schema(description = "체크플래그")
    private String chkflag;

    /** 상태 */
    @Schema(description = "상태")
    private String statusAj;
    
    /** 상태명 */
    @Schema(description = "상태명")
    private String statusAjnm;

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

    /** 박스처리유무 */
    @Schema(description = "박스처리유무")
    private String boxflag;

    /** 보관료 */
    @Schema(description = "보관료")
    private String reference08;

    /** 보관료확정 */
    @Schema(description = "보관료확정")
    private String reference09;

    /** 운송료 */
    @Schema(description = "운송료")
    private String reference10;

    /** 등록자 */
    @Schema(description = "등록자")
    private String addwho;

    /** 기타(센터별) */
    @Schema(description = "기타(센터별)")
    private String reference01;
    
    /** 제조일자 */
    @Schema(description = "제조일자")
    private String manufacturedt;    
    
    /** 소비일자 */
    @Schema(description = "소비일자")
    private String expiredt;    
    
    /** 소비기한잔여율 */
    @Schema(description = "소비기한잔여율")
    private BigDecimal usebydatefreert;      
    
    /** 요청월 */
    @Schema(description = "요청월")
    private String requestMm;    
    
    /** 데이터번호 */
    @Schema(description = "데이터번호")
    private String serialkey;   
    
    /** AJ데이터번호 */
    @Schema(description = "AJ데이터번호")
    private String ajSerialkey;
    
    /** seq */
    @Schema(description = "seq")
    private BigDecimal seq;    
  
    /** 폐기구분 */
    @Schema(description = "폐기구분")
    private String disuseDiv;    
    
    /** 귀속부서코드 */
    @Schema(description = "귀속부서코드")
    private String respDeptCd;
    
	/** 비고 */
	@Schema(description = "비고")
	private String rmk;	    
	
    @Schema(description = "변경귀속부서명")
    private String toRespDeptNm;

    @Schema(description = "변경거래처명")
    private String chgCustname;
    
	
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
