package cjfw.wms.st.dto;

import java.math.BigDecimal;

import cjfw.wms.common.extend.CommonProcedureDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JiHoPark
 * @date : 2025.08.27
 * @description : 외부비축재고조정 response dto
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.08.27 JiHoPark  생성 </pre>
 */
@Data
@Schema(description = "외부비축재고조정 response dto")
public class StAdjustmentRequestExDCResDto extends CommonProcedureDto {
	/** 커스텀 엑스트라 체크박스 */
	@Schema(description = "커스텀 엑스트라 체크박스", example = "N")
	private String customRowCheckYn = "N";


	/** 물류센터 */
	@Schema(description = "물류센터")
	private String dccode;

	/** 고객사코드 */
	@Schema(description = "고객사코드")
	private String storerkey;

	/** 창고 */
	@Schema(description = "창고")
	private String organize;

	/** 창고명 */
	@Schema(description = "창고명")
	private String organizename;

	/** 창고코드 SAP 창고 혹은 별도의 창고 코드 */
	@Schema(description = "창고코드 SAP 창고 혹은 별도의 창고 코드")
	private String area;

	/** 재고유형 */
	@Schema(description = "재고유형")
	private String stocktype;

	/** 재고위치 */
	@Schema(description = "재고위치")
	private String stocktypenm;

	/** 재고 등급(ABC) */
	@Schema(description = "재고 등급(ABC)")
	private String stockgrade;

	/** 재고속성 */
	@Schema(description = "재고속성")
	private String stockgradename;

	/** 피킹존 */
	@Schema(description = "피킹존")
	private String zone;

	/** 로케이션 */
	@Schema(description = "로케이션")
	private String loc;

	/** 상품코드 */
	@Schema(description = "상품코드")
	private String sku;

	/** 기본 제품명 없을경우 SKU코드 */
	@Schema(description = "기본 제품명 없을경우 SKU코드")
	private String skuname;

	/** 재고 구분 LOT */
	@Schema(description = "재고 구분 LOT")
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




	/** 발생사유 */
	@Schema(description = "발생사유")
	private String reasoncode;

	/** reasonmsg */
	@Schema(description = "reasonmsg")
	private String reasonmsg;

	/** decreasetype */
	@Schema(description = "decreasetype")
	private String decreasetype;

	/** 귀책 */
	@Schema(description = "귀책")
	private String imputetype;

	/** 물류귀책배부 */
	@Schema(description = "물류귀책배부")
	private String processmain;

	/** 귀속부서 */
	@Schema(description = "귀속부서")
	private String costcd;

	/** 귀속부서명 */
	@Schema(description = "귀속부서명")
	private String costcdname;

	/** 거래처 */
	@Schema(description = "거래처")
	private String custkey;

	/** 거래처명*/
	@Schema(description = "거래처명")
	private String custname;

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

	/** 바코드 */
	@Schema(description = "바코드")
	private String barcode;

	/** B/L 번호 */
	@Schema(description = "B/L 번호")
	private String convserialno;

	/** 도축일자 */
	@Schema(description = "도축일자")
	private String butcherydt;

	/** 도축장 */
	@Schema(description = "도축장")
	private String factoryname;

	/** 계약유형 */
	@Schema(description = "계약유형")
	private String contracttype;

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

	/** seriallevel */
	@Schema(description = "seriallevel")
	private String seriallevel;

	/** serialtype */
	@Schema(description = "serialtype")
	private String serialtype;

	/** 컬러명 */
	@Schema(description = "컬러명")
	private String colordescr;

	/** placeoforigin */
	@Schema(description = "placeoforigin")
	private String placeoforigin;

	/** ordertype */
	@Schema(description = "ordertype")
	private String ordertype;

	/** 유통기간 */
	@Schema(description = "유통기간")
	private long duration;

	/** 유통기한관리방법 */
	@Schema(description = "유통기한관리방법")
	private String durationtype;

	/** poline */
	@Schema(description = "poline")
	private String poline;

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


	/** 박스처리유무 */
	@Schema(description = "박스처리유무")
	private String boxflag;

	/** 박스당 낱개수 */
	@Schema(description = "박스당 낱개수")
	private BigDecimal qtyperbox;

	/** other03 */
	@Schema(description = "other03")
	private String other03;

	/** other05 */
	@Schema(description = "other05")
	private String other05;



	/** chkamt */
	@Schema(description = "chkamt")
	private String chkamt;

	/** docdt */
	@Schema(description = "docdt")
	private String docdt;

	/** docno */
	@Schema(description = "docno")
	private String docno;

	/** docline */
	@Schema(description = "docline")
	private String docline;

	/** 조정일자 */
	@Schema(description = "조정일자")
	private String slipdt;

	/** slipno */
	@Schema(description = "slipno")
	private String slipno;

	/** slipline */
	@Schema(description = "slipline")
	private String slipline;

	/** sliptype */
	@Schema(description = "sliptype")
	private String sliptype;

	/** 수량 */
	@Schema(description = "수량")
	private BigDecimal orderqty;

	/** iotype */
	@Schema(description = "iotype")
	private String iotype;

	/** 유형 */
	@Schema(description = "유형")
	private String inquirytypename;

	/** 저장조건 */
	@Schema(description = "저장조건")
	private String storagetype;

	/** 금액 */
	@Schema(description = "금액")
	private BigDecimal stockamt;

	/** price */
	@Schema(description = "price")
	private BigDecimal price;

	/** weight */
	@Schema(description = "weight")
	private BigDecimal weight;

	/** other04 */
	@Schema(description = "other04")
	private String other04;

	/** 귀속부서명 */
	@Schema(description = "귀속부서명")
	private String costcentername;

	/** other02 */
	@Schema(description = "other02")
	private String other02;

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

	/** 생성인 */
	@Schema(description = "생성인")
	private String addwho;

	/** 등록일자 */
	@Schema(description = "등록일자")
	private String adddate;

	/** doctype */
	@Schema(description = "doctype")
	private String doctype;

	/** 감모유형 */
	@Schema(description = "감모유형")
	private String decreasetypename;

	/** 발생사유 */
	@Schema(description = "발생사유")
	private String reasoncodename;

	/** 귀책 */
	@Schema(description = "귀책")
	private String imputetypename;

	/** approvalstatus */
	@Schema(description = "approvalstatus")
	private String approvalstatus;

	/** 결재진행상태 */
	@Schema(description = "결재진행상태")
	private String approvalstatusname;

	/** 전자문서시간 */
	@Schema(description = "전자문서시간")
	private String approvaldate;

	/** 품의요청번호 */
	@Schema(description = "품의요청번호")
	private String approvalreqno;

	/** 전자문서번호 */
	@Schema(description = "전자문서번호")
	private String approvalno;

	/** approvaldt */
	@Schema(description = "approvaldt")
	private String approvaldt;

	/** chkflag */
	@Schema(description = "chkflag")
	private String chkflag;

	/** 처리상태 */
	@Schema(description = "처리상태")
	private String statusAj;

	/** 비고 */
	@Schema(description = "비고")
	private String reference01;

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

    /**재고할당수량*/
    @Schema(description = "재고할당수량")
    private BigDecimal qtyallocated;

    /**피킹수량*/
    @Schema(description = "피킹수량")
    private BigDecimal qtypicked;

    /**조정수량*/
    @Schema(description = "조정수량")
    private BigDecimal tranqty;

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

    /**기타정보1*/
    @Schema(description = "기타정보1")
    private String other01;



    /**기타수량1*/
    @Schema(description = "기타수량1")
    private BigDecimal etcqty1;


    /**작업박스수량*/
    @Schema(description = "작업박스수량")
    private BigDecimal tranbox;



    /**재고위치설명*/
    @Schema(description = "재고위치설명")
    private String stocktypedesc;

    /**재고속성설명*/
    @Schema(description = "재고속성설명")
    private String stockgradedesc;

    /**부족수량*/
    @Schema(description = "부족수량")
    private BigDecimal shotage_qty;

    /**처리여부*/
    @Schema(description = "처리여부")
    private String processflag;

    /**처리메시지*/
    @Schema(description = "처리메시지")
    private String processmsg;

    /**발주번호*/
    @Schema(description = "발주번호")
    private String pokey;

    /** 커스텀 엑스트라 체크박스 */
	@Schema(description = "커스텀 엑스트라 체크박스", example = "0")
	private String chk = "0";

	/** 제조일자 */
	@Schema(description = "제조일자")
	private String manufacturedt;

	/** 소비일자 */
	@Schema(description = "소비일자")
	private String expiredt;

}
