package cjfw.wms.wd.dto;

import java.math.BigDecimal;

import cjfw.wms.common.extend.CommonProcedureDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JiHoPark
 * @date : 2025.09.19
 * @description : 외부센터매각출고처리 전자결재 response dto
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.09.19 JiHoPark  생성 </pre>
 */
@Data
@Schema(description = "외부센터매각출고처리 전자결재 response dto")
public class WdShipmentETCEXDCElectApprovalDto extends CommonProcedureDto {
	/** 물류센터 */
	@Schema(description = "물류센터")
	private String dccode;

	/** 고객사 */
	@Schema(description = "고객사")
	private String storerkey;

	/** 창고 */
	@Schema(description = "창고")
	private String organize;

	/** 지역 */
	@Schema(description = "area")
	private String area;

	/** 문서일자 */
	@Schema(description = "문서일자")
	private String docdt;

	/** 문서번호 */
	@Schema(description = "문서번호장")
	private String docno;

	/** 문서라인 */
	@Schema(description = "문서라인")
	private String docline;

	/** 전표일자 */
	@Schema(description = "전표일자")
	private String slipdt;

	/** 전표번호장 */
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

	/** 상품명칭 */
	@Schema(description = "상품명칭")
	private String skuname;

	/** 단위 */
	@Schema(description = "단위")
	private String uom;

	/** 수량 */
	@Schema(description = "수량")
	private BigDecimal orderqty;

	/** 예정량 */
	@Schema(description = "예정량")
	private BigDecimal openqty;

	/** tranqty */
	@Schema(description = "tranqty")
	private BigDecimal tranqty;

	/** 주문유형 */
	@Schema(description = "주문유형")
	private String ordertype;

	/** 재고 IN,OUT,INOUT */
	@Schema(description = "재고 IN,OUT,INOUT")
	private String iotype;

	/** 기타출고 */
	@Schema(description = "기타출고")
	private String inquirytypename;

	/** 유통기한임박여부 */
	@Schema(description = "유통기한임박여부")
	private String neardurationyn;

	/** 기준일(유통,제조) */
	@Schema(description = "기준일(유통,제조)")
	private String lottable01;

	/** 유통기간(잔여/전체) */
	@Schema(description = "유통기간(잔여/전체)")
	private String durationTerm;

	/** 저장조건 */
	@Schema(description = "저장조건")
	private String storagetype;

	/** 유통기간 */
	@Schema(description = "유통기간")
	private String duration;

	/** 유통기한관리방법 */
	@Schema(description = "유통기한관리방법")
	private String durationtype;

	/** chkamt */
	@Schema(description = "chkamt")
	private String chkamt;

	/** 금액 */
	@Schema(description = "금액")
	private BigDecimal stockamt;

	/** price */
	@Schema(description = "price")
	private BigDecimal price;

	/** 평균중량 */
	@Schema(description = "평균중량")
	private BigDecimal weight;

	/** 사유코드 */
	@Schema(description = "사유코드")
	private String reasoncode;

	/** 조정사유 */
	@Schema(description = "조정사유")
	private String reasoncodename;

	/** 기타정보 4 */
	@Schema(description = "기타정보 4")
	private String other04;

	/** 귀속부서명 */
	@Schema(description = "귀속부서명")
	private String costcentername;

	/** 기타정보 2 */
	@Schema(description = "기타정보 2")
	private String other02;

	/** 전자결재유형 */
	@Schema(description = "전자결재유형")
	private String approvalreasonname;

	/** 이력번호 */
	@Schema(description = "이력번호")
	private String serialno;

	/** 이력번호 */
	@Schema(description = "이력번호")
	private String serialnoOrg;

	/** B/L 번호 */
	@Schema(description = "B/L 번호")
	private String convserialno;

	/** poline */
	@Schema(description = "poline")
	private String poline;

	/** 도축일자 */
	@Schema(description = "도축일자")
	private String butcherydt;

	/** 계약업체 */
	@Schema(description = "계약업체")
	private String contractcompany;

	/** 계약업체명 */
	@Schema(description = "계약업체명")
	private String contractcompanyname;

	/** 도축장 */
	@Schema(description = "도축장")
	private String factoryname;

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

	/** storageloc */
	@Schema(description = "storageloc")
	private String storageloc;

	/** qty */
	@Schema(description = "qty")
	private BigDecimal qty;

	/** 기타3 */
	@Schema(description = "기타3")
	private String other03;

	/** 로케이션 */
	@Schema(description = "로케이션")
	private String loc;

	/** lot */
	@Schema(description = "lot")
	private String lot;

	/** 재고속성 */
	@Schema(description = "재고속성")
	private String stockgrade;

	/** 개체식별/유통이력 */
	@Schema(description = "개체식별/유통이력")
	private String stockid;

	/** 사유 */
	@Schema(description = "사유")
	private String stockamtmsg;

	/** 평균중량 */
	@Schema(description = "평균중량")
	private BigDecimal avgweight;

	/** 환산박스 */
	@Schema(description = "환산박스")
	private BigDecimal calbox;

	/** 실박스예정 */
	@Schema(description = "실박스예정")
	private BigDecimal realorderbox;

	/** 실박스확정 */
	@Schema(description = "실박스확정")
	private BigDecimal realcfmbox;

	/** 작업박스수량 */
	@Schema(description = "작업박스수량")
	private BigDecimal tranbox;

	/** boxflag */
	@Schema(description = "boxflag")
	private String boxflag;

	/** 규격 */
	@Schema(description = "규격")
	private String serialtype;

	/** 생성인 */
	@Schema(description = "생성인")
	private String addwho;

	/** 등록일자 */
	@Schema(description = "등록일자")
	private String adddate;

	/**보상주체*/
    @Schema(description = "보상주체")
    private String compPartyType;

    /**입금액*/
    @Schema(description = "입금액")
    private BigDecimal depositAmount;

    /**단가*/
    @Schema(description = "단가")
    private BigDecimal factoryprice;

    /**전표번호*/
    @Schema(description = "전표번호")
    private String manualSlipno;

    /**완료여부*/
    @Schema(description = "완료여부")
    private String completeYn;

    /** 커스텀 엑스트라 체크박스 */
	@Schema(description = "커스텀 엑스트라 체크박스", example = "0")
	private String chk = "0";

	/**처리유형*/
	@Schema(description = "처리유형")
	private String potype;

	/**처리유형명*/
	@Schema(description = "처리유형명")
	private String potypename;

	/**차이금액*/
    @Schema(description = "차이금액")
    private BigDecimal wrbtr;

    /** 제조일자 */
	@Schema(description = "제조일자")
	private String manufacturedt;

	/** 소비일자 */
	@Schema(description = "소비일자")
	private String expiredt;
}
