package cjfw.wms.wd.dto;

import java.math.BigDecimal;

import cjfw.wms.common.extend.CommonProcedureDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JiHoPark
 * @date : 2025.09.11
 * @description : 외부비축재고조정 response dto
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.09.11 JiHoPark  생성 </pre>
 */
@Data
@Schema(description = "외부센터매각출고처리 response dto")
public class WdShipmentETCEXDCResDto extends CommonProcedureDto {
	/**물류센터*/
	@Schema(description = "물류센터")
	private String dccode;

	/**고객사*/
	@Schema(description = "고객사")
	private String storerkey;

	/**창고*/
	@Schema(description = "창고")
	private String organize;

	/** 창고명 */
	@Schema(description = "창고명")
	private String organizename;

	/**지역*/
	@Schema(description = "지역")
	private String area;

	/**재고위치명*/
	@Schema(description = "재고위치명")
	private String stocktypenm;

	/**재고위치*/
	@Schema(description = "재고위치")
	private String stocktype;

	/**재고속성*/
	@Schema(description = "재고속성")
	private String stockgrade;

	/**재고속성명*/
	@Schema(description = "재고속성명")
	private String stockgradename;

	/**피킹존*/
	@Schema(description = "피킹존")
	private String zone;

	/**로케이션*/
	@Schema(description = "로케이션")
	private String loc;

	/**상품코드*/
	@Schema(description = "상품코드")
	private String sku;

	/**상품명칭*/
	@Schema(description = "상품명칭")
	private String skuname;

	/**LOT*/
	@Schema(description = "LOT")
	private String lot;

	/**단위*/
	@Schema(description = "단위")
	private String uom;

	/**현재고수량*/
	@Schema(description = "현재고수량")
	private BigDecimal qty;

	/**현재고 박스수량*/
	@Schema(description = "현재고 박스수량")
	private BigDecimal boxqty;

	/**가용재고수량*/
	@Schema(description = "가용재고수량")
	private BigDecimal openqty;

	/**재고분배량*/
	@Schema(description = "재고분배량")
	private BigDecimal qtyallocated;

	/**피킹재고*/
	@Schema(description = "피킹재고")
	private BigDecimal qtypicked;

	/**처리수량*/
	@Schema(description = "처리수량")
	private BigDecimal etcqty;

	/** 기타수량 */
	@Schema(description = "기타수량")
	private BigDecimal etcqty2;

	/**처리유형*/
	@Schema(description = "처리유형")
	private String potype;

	/**처리유형명*/
	@Schema(description = "처리유형명")
	private String potypename;

	/**처리사유*/
	@Schema(description = "처리사유")
	private String reasoncode;

	/**세부사유*/
	@Schema(description = "세부사유")
	private String reasonmsg;

	/**처리주체*/
	@Schema(description = "처리주체")
	private String other01;

	/** other03 */
	@Schema(description = "other03")
	private String other03;

	/**귀속부서*/
	@Schema(description = "귀속부서")
	private String costcd;

	/**귀속부서명*/
	@Schema(description = "귀속부서명")
	private String costcdname;

	/**거래처*/
	@Schema(description = "거래처")
	private String custkey;

	/**처래처명*/
	@Schema(description = "처래처명")
	private String custname;

	/**물류귀책배부*/
	@Schema(description = "물류귀책배부")
	private String other05;

	/**유통기한임박여부*/
	@Schema(description = "유통기한임박여부")
	private String neardurationyn;

	/**유통기한*/
	@Schema(description = "유통기한")
	private String lottable01;

	/**유통기간(잔여/전체)*/
	@Schema(description = "유통기간(잔여/전체)")
	private String durationTerm;

	/**개체식별/유통이력*/
	@Schema(description = "개체식별/유통이력")
	private String stockid;

	/**이력번호*/
	@Schema(description = "이력번호")
	private String serialno;

	/**B/L번호*/
	@Schema(description = "B/L번호")
	private String convserialno;

	/**등급*/
	@Schema(description = "등급")
	private String seriallevel;

	/**규격*/
	@Schema(description = "규격")
	private String serialtype;

	/**도축장*/
	@Schema(description = "도축장")
	private String factoryname;

	/**부위명*/
	@Schema(description = "부위명")
	private String colordescr;

	/**원산지*/
	@Schema(description = "원산지")
	private String placeoforigin;

	/**주문유형*/
	@Schema(description = "주문유형")
	private String ordertype;

	/**유통기한*/
	@Schema(description = "유통기한")
	private String duration;

	/**유통기한관리방법*/
	@Schema(description = "유통기한관리방법")
	private String durationtype;

	/**도축일자*/
	@Schema(description = "도축일자")
	private String butcherydt;

	/**계약업체*/
	@Schema(description = "계약업체")
	private String contractcompany;

	/**contractcompanyname*/
	@Schema(description = "contractcompanyname")
	private String contractcompanyname;

	/**fromvaliddt*/
	@Schema(description = "fromvaliddt")
	private String fromvaliddt;

	/**tovaliddt*/
	@Schema(description = "tovaliddt")
	private String tovaliddt;

	/**contracttype*/
	@Schema(description = "contracttype")
	private String contracttype;

	/**barcode*/
	@Schema(description = "barcode")
	private String barcode;

	/**평균중량*/
	@Schema(description = "평균중량")
	private BigDecimal avgweight;

	/**환산박스*/
	@Schema(description = "환산박스")
	private BigDecimal calbox;

	/**실박스예정*/
	@Schema(description = "실박스예정")
	private BigDecimal realorderbox;

	/**실박스확정*/
	@Schema(description = "실박스확정")
	private BigDecimal realcfmbox;

	/**작업박스수량*/
	@Schema(description = "작업박스수량")
	private BigDecimal tranbox;

	/**boxflag*/
	@Schema(description = "boxflag")
	private String boxflag;

	/**처리여부*/
    @Schema(description = "처리여부")
    private String processflag;

    /**처리메시지*/
    @Schema(description = "처리메시지")
    private String processmsg;



    /**문서유형*/
    @Schema(description = "문서유형")
    private String doctype;

    /**문서일자*/
    @Schema(description = "문서일자")
    private String docdt;

    /**문서번호장*/
    @Schema(description = "문서번호장")
    private String docno;

    /**문서라인*/
    @Schema(description = "문서라인")
    private String docline;

    /**조정일자*/
    @Schema(description = "조정일자")
    private String slipdt;

    /**전표번호*/
    @Schema(description = "전표번호")
    private String slipno;

    /**전표라인*/
    @Schema(description = "전표라인")
    private String slipline;

    /**재고 IN,OUT,INOUT*/
    @Schema(description = "재고 IN,OUT,INOUT")
    private String iotype;

    /**전표일자*/
    @Schema(description = "전표일자")
    private String sliptype;

    /**조정수량*/
    @Schema(description = "조정수량")
    private BigDecimal tranqty;

    /**처리유형*/
    @Schema(description = "처리유형")
    private String processtype;

    /**발생사유*/
    @Schema(description = "발생사유")
    private String processtypename;

    /**발생사유*/
    @Schema(description = "처리사유")
    private String reasoncodename;

    /**귀책*/
    @Schema(description = "귀책")
    private String imputetype;

    /**귀책*/
    @Schema(description = "귀책")
    private String imputetypename;

    /**물류귀책배부*/
    @Schema(description = "물류귀책배부")
    private String processmain;

    /**결재진행상태*/
    @Schema(description = "결재진행상태")
    private String approvalstatus;

    /**결재진행상태*/
    @Schema(description = "결재진행상태")
    private String approvalstatusname;

    /**전자문서시간*/
    @Schema(description = "전자문서시간")
    private String approvaldate;

    /**품의요청번호*/
    @Schema(description = "품의요청번호")
    private String approvalreqno;

    /**전자문서번호*/
    @Schema(description = "전자문서번호")
    private String approvalno;

    /**결재상신일자*/
    @Schema(description = "결재상신일자")
    private String approvaldt;

    /**chkflag*/
    @Schema(description = "chkflag")
    private String chkflag;

    /**처리상태*/
    @Schema(description = "처리상태")
    private String statusAj;

    /**비고*/
    @Schema(description = "비고")
    private String reference01;

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

    /** 금액 */
	@Schema(description = "금액")
	private BigDecimal stockamt;

	/** 수량 */
	@Schema(description = "수량")
	private BigDecimal orderqty;

	/** 기타정보 2 */
	@Schema(description = "기타정보 2")
	private String other02;

	/** 기타정보 4 */
	@Schema(description = "기타정보 4")
	private String other04;

	/** 평균중량 */
	@Schema(description = "평균중량")
	private BigDecimal weight;

	/**등록일시*/
	@Schema(description = "등록일시")
	private String adddate;

	/**등록자*/
	@Schema(description = "등록자")
	private String addwho;

	/** 커스텀 엑스트라 체크박스 */
	@Schema(description = "커스텀 엑스트라 체크박스", example = "0")
	private String chk = "0";



















	/** decreasetype */
	@Schema(description = "decreasetype")
	private String decreasetype;


	/** poline */
	@Schema(description = "poline")
	private String poline;

	/** 박스당 낱개수 */
	@Schema(description = "박스당 낱개수")
	private BigDecimal qtyperbox;

	/** chkamt */
	@Schema(description = "chkamt")
	private String chkamt;

	/** 유형 */
	@Schema(description = "유형")
	private String inquirytypename;

	/** 저장조건 */
	@Schema(description = "저장조건")
	private String storagetype;

	/** price */
	@Schema(description = "price")
	private BigDecimal price;

	/** 귀속부서명 */
	@Schema(description = "귀속부서명")
	private String costcentername;

	/** 전자결재유형 */
	@Schema(description = "전자결재유형")
	private String approvalreasonname;

	/** 이력번호 */
	@Schema(description = "이력번호")
	private String serialnoOrg;

	/** storageloc */
	@Schema(description = "storageloc")
	private String storageloc;

	/** 사유 */
	@Schema(description = "사유")
	private String stockamtmsg;

	/** 감모유형 */
	@Schema(description = "감모유형")
	private String decreasetypename;

	 /**자동조정 command*/
    @Schema(description = "자동조정 command")
    private String execCommand;









    /**진행수량*/
    @Schema(description = "진행수량")
    private BigDecimal processqty;

    /**현장작업량*/
    @Schema(description = "현장작업량")
    private BigDecimal workqty;

    /**확정수량*/
    @Schema(description = "확정수량")
    private BigDecimal confirmqty;

    /**합포장번호*/
    @Schema(description = "합포장번호")
    private String mixboxkey;

    /**작업지시번호*/
    @Schema(description = "작업지시번호")
    private String taskkey;

    /**작업키*/
    @Schema(description = "작업키")
    private String wavekey;

    /**인보이스번호*/
    @Schema(description = "인보이스번호")
    private String invoiceno;

    /**기타수량1*/
    @Schema(description = "기타수량1")
    private BigDecimal etcqty1;

    /**유통기간(잔여/전체)*/
    @Schema(description = "유통기간(잔여/전체)")
    private String duration_term;

    /**재고위치설명*/
    @Schema(description = "재고위치설명")
    private String stocktypedesc;

    /**재고속성설명*/
    @Schema(description = "재고속성설명")
    private String stockgradedesc;

    /**부족수량*/
    @Schema(description = "부족수량")
    private BigDecimal shotage_qty;

    /**발주번호*/
    @Schema(description = "발주번호")
    private String pokey;

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
