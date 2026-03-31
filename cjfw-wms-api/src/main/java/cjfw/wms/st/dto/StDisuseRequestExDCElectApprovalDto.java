package cjfw.wms.st.dto;

import java.math.BigDecimal;

import cjfw.wms.common.extend.CommonProcedureDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JiHoPark
 * @date : 2025.12.31
 * @description :  외부비축재고폐기처리 전자결재 dto
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.12.31 JiHoPark  생성 </pre>
 */
@Data
@Schema(description = "외부비축재고폐기처리 전자결재 dto")
public class StDisuseRequestExDCElectApprovalDto extends CommonProcedureDto {
	/** 커스텀 엑스트라 체크박스 */
	@Schema(description = "커스텀 엑스트라 체크박스", example = "N")
	private String customRowCheckYn = "N";

	/** 물류센터 */
	@Schema(description = "물류센터")
	private String dccode;

	/** 회사 */
	@Schema(description = "회사")
	private String storerkey;

	/** 창고 */
	@Schema(description = "창고")
	private String organize;

	/** 창고명 */
	@Schema(description = "창고명")
	private String organizename;

	/** 지역 */
	@Schema(description = "지역")
	private String area;

	/** 재고속성 */
	@Schema(description = "재고속성")
	private String stockgrade;

	/** 로케이션 */
	@Schema(description = "로케이션")
	private String loc;

	/** 상품코드 */
	@Schema(description = "상품코드")
	private String sku;

	/** 상품명 */
	@Schema(description = "상품명")
	private String skuname;

	/** lot */
	@Schema(description = "lot")
	private String lot;

	/** 단위 */
	@Schema(description = "단위")
	private String uom;

	/** 현재재고수량 */
	@Schema(description = "현재재고수량")
	private BigDecimal qty;

	/** 가용재고수량 */
	@Schema(description = "가용재고수량")
	private BigDecimal openqty;

	/** 조정수량 */
	@Schema(description = "조정수량")
	private BigDecimal tranqty;

	/** 발생사유 */
	@Schema(description = "발생사유")
	private String reasoncode;
	
	/** 발생사유 */
	@Schema(description = "폐기사유")
	private String reasonMsg;


	/** 유통기한임박여부 */
	@Schema(description = "유통기한임박여부")
	private String neardurationyn;

	/** 기준일(유통,제조) */
	@Schema(description = "기준일(유통,제조)")
	private String lottable01;

	/** 유통기간(잔여/전체) */
	@Schema(description = "유통기간(잔여/전체)")
	private String durationTerm;

	/** 재고 구분 ID */
	@Schema(description = "재고 구분 ID")
	private String stockid;

	/** 이력번호 */
	@Schema(description = "이력번호")
	private String serialno;

	/** B/L 번호 */
	@Schema(description = "B/L 번호")
	private String convserialno;

	/** serialtype */
	@Schema(description = "serialtype")
	private String serialtype;

	/** 도축장 */
	@Schema(description = "도축장")
	private String factoryname;

	/** ordertype */
	@Schema(description = "ordertype")
	private String ordertype;

	/** 유통기간 */
	@Schema(description = "유통기간")
	private String duration;

	/** 유통기한관리방법 */
	@Schema(description = "유통기한관리방법")
	private String durationtype;

	/** 도축일자 */
	@Schema(description = "도축일자")
	private String butcherydt;

	/** 계약업체 */
	@Schema(description = "계약업체")
	private String contractcompany;

	/** 계약업체명 */
	@Schema(description = "계약업체명")
	private String contractcompanyname;

	/** 유효일자(FROM) */
	@Schema(description = "유효일자(FROM)")
	private String fromvaliddt;

	/** 유효일자(TO) */
	@Schema(description = "유효일자(TO)")
	private String tovaliddt;

	/** 계약유형 */
	@Schema(description = "계약유형")
	private String contracttype;

	/** 바코드 */
	@Schema(description = "바코드")
	private String barcode;

	/** 평균중량 */
	@Schema(description = "평균중량")
	private BigDecimal avgweight;

	/** 환산박스 */
	@Schema(description = "환산박스")
	private BigDecimal calbox ;

	/** 실박스예정 */
	@Schema(description = "실박스예정")
	private BigDecimal realorderbox ;

	/** 실박스확정 */
	@Schema(description = "실박스확정")
	private BigDecimal realcfmbox;

	/** 작업박스수량 */
	@Schema(description = "작업박스수량")
	private BigDecimal tranbox;

	/** boxflag */
	@Schema(description = "boxflag")
	private String boxflag;

	/** 발생사유 */
	@Schema(description = "발생사유")
	private String reasoncodename;

	/** 문서일자 */
	@Schema(description = "문서일자")
	private String docdt;

	/** 문서번호 */
	@Schema(description = "문서번호")
	private String docno;

	/** 재고조정문서라인 */
	@Schema(description = "재고조정문서라인")
	private String docline;

	/** 조정일자 */
	@Schema(description = "조정일자")
	private String slipdt;

	/** 전표번호 */
	@Schema(description = "전표번호")
	private String slipno;

	/** 전표라인번호 */
	@Schema(description = "전표라인번호")
	private String slipline;

	/** 입출고타입 */
	@Schema(description = "입출고타입")
	private String iotype;

	/** 전표유형 */
	@Schema(description = "전표유형")
	private String sliptype;

	/** 등록자 */
	@Schema(description = "등록자")
	private String addwho;

	/** 등록일자 */
	@Schema(description = "등록일자")
	private String adddate;

	/** 수량 */
	@Schema(description = "수량")
	private BigDecimal orderqty;

	/** 유형 */
	@Schema(description = "유형")
	private String inquirytypename;

	/** 저장조건 */
	@Schema(description = "저장조건")
	private String storagetype;

	/** 금액 */
	@Schema(description = "금액")
	private BigDecimal stockamt;

	/** 단가 */
	@Schema(description = "단가")
	private BigDecimal price;

	/** 출고조정중량 */
	@Schema(description = "출고조정중량")
	private BigDecimal weight;

	/** 기타정보4 */
	@Schema(description = "기타정보4")
	private String other04;

	/** 귀속부서명 */
	@Schema(description = "귀속부서명")
	private String costcentername;

	/** 기타정보2 */
	@Schema(description = "기타정보2")
	private String other02;

	/** 전자결재유형 */
	@Schema(description = "전자결재유형")
	private String approvalreasonname;

	/** 이력번호 */
	@Schema(description = "이력번호")
	private String serialnoOrg;

	/** PO라인번호 */
	@Schema(description = "PO라인번호")
	private String poline;

	/** 저장위치 */
	@Schema(description = "저장위치")
	private String storageloc;

	/** 기타정보3 */
	@Schema(description = "기타정보3")
	private String other03;

	/** 사유 */
	@Schema(description = "사유")
	private String stockamtmsg;

	/** chkamt */
	@Schema(description = "chkamt")
	private String chkamt;

	/** 제조일자 */
	@Schema(description = "제조일자")
	private String manufacturedt;

	/** 소비일자 */
	@Schema(description = "소비일자")
	private String expiredt;

}
