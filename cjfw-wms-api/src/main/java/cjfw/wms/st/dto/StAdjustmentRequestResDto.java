package cjfw.wms.st.dto;

import java.math.BigDecimal;

import cjfw.wms.common.extend.CommonProcedureDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JiHoPark
 * @date : 2025.10.10
 * @description : 재고조정 요청/처리 request dto
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.10.10 JiHoPark  생성 </pre>
 */
@Data
@Schema(description = "재고조정 요청/처리 response dto")
public class StAdjustmentRequestResDto extends CommonProcedureDto {
	/** 커스텀 엑스트라 체크박스 */
	@Schema(description = "커스텀 엑스트라 체크박스", example = "N")
	private String customRowCheckYn = "N";

	/** 물류센터 */
	@Schema(description = "물류센터")
	private String dccode;

	/** 고객사 */
	@Schema(description = "고객사")
	private String storerkey;

	/** 창고 */
	@Schema(description = "창고")
	private String organize;

	/** 작업구역 */
	@Schema(description = "작업구역")
	private String area;

	/** 재고위치명칭 */
	@Schema(description = "재고위치명칭")
	private String stocktypenm;

	/** 재고위치코드 */
	@Schema(description = "재고위치코드")
	private String stocktype;

	/** 재고속성코드 */
	@Schema(description = "재고속성코드")
	private String stockgrade;

	/** 재고속성명칭 */
	@Schema(description = "재고속성명칭")
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

	/** 상품명칭 */
	@Schema(description = "상품명칭")
	private String skuname;

	/** 로트 */
	@Schema(description = "로트")
	private String lot;

	/** 단위 */
	@Schema(description = "단위")
	private String uom;

	/** 현재고수량 */
	@Schema(description = "현재고수량")
	private BigDecimal qty;

	/** 가용재고수량 */
	@Schema(description = "가용재고수량")
	private BigDecimal openqty;

	/** 재고할당수량 */
	@Schema(description = "재고할당수량")
	private BigDecimal qtyallocated;

	/** 피킹재고 */
	@Schema(description = "피킹재고")
	private BigDecimal qtypicked;

	/** 조정수량 */
	@Schema(description = "조정수량")
	private BigDecimal tranqty;

	/** 발생사유 */
	@Schema(description = "발생사유")
	private String reasoncode;

	/** reasonmsg */
	@Schema(description = "reasonmsg")
	private String reasonmsg;

	/** decreasetype */
	@Schema(description = "decreasetype")
	private String decreasetype;

	/** imputetype */
	@Schema(description = "imputetype")
	private String imputetype;

	/** 물류귀책배부 */
	@Schema(description = "물류귀책배부")
	private String processmain;

	/** costcd */
	@Schema(description = "costcd")
	private String costcd;

	/** costcdname */
	@Schema(description = "costcdname")
	private String costcdname;

	/** custkey */
	@Schema(description = "custkey")
	private String custkey;

	/** custname */
	@Schema(description = "custname")
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

	/** B/L번호 */
	@Schema(description = "B/L번호")
	private String convserialno;

	/** seriallevel */
	@Schema(description = "seriallevel")
	private String seriallevel;

	/** serialtype */
	@Schema(description = "serialtype")
	private String serialtype;

	/** 도축장 */
	@Schema(description = "도축장")
	private String factoryname;

	/** 컬러명 */
	@Schema(description = "컬러명")
	private String colordescr;

	/** placeoforigin */
	@Schema(description = "placeoforigin")
	private String placeoforigin;

	/** 주문유형 */
	@Schema(description = "주문유형")
	private String ordertype;

	/** duration */
	@Schema(description = "duration")
	private String duration;

	/** durationtype */
	@Schema(description = "durationtype")
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

	/** poline */
	@Schema(description = "poline")
	private String poline;

	/** other03 */
	@Schema(description = "other03")
	private String other03;

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

	/** boxflag */
	@Schema(description = "boxflag")
	private String boxflag;

	/** 팩당 낱개수 */
	@Schema(description = "팩당 낱개수")
	private BigDecimal qtyperbox;

	/** 비고(사유) */
	@Schema(description = "비고(사유)")
	private String other05;

	/** processflag */
	@Schema(description = "processflag")
	private String processflag;

	/** processmsg */
	@Schema(description = "processmsg")
	private String processmsg;

	/** 작업박스수량 */
	@Schema(description = "작업박스수량")
	private BigDecimal tranbox;

	/** 문서유형 */
	@Schema(description = "문서유형")
	private String doctype;

	/** 문서일자 */
	@Schema(description = "문서일자")
	private String docdt;

	/** 문서번호 */
	@Schema(description = "문서번호")
	private String docno;

	/** 재고조정문서라인 */
	@Schema(description = "재고조정문서라인")
	private String docline;

	/** 전표일자 */
	@Schema(description = "전표일자")
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

	/** sliptype */
	@Schema(description = "sliptype")
	private String sliptype;

	/** 감모유형 */
	@Schema(description = "감모유형")
	private String decreasetypename;

	/** 발생사유 */
	@Schema(description = "발생사유")
	private String reasoncodename;

	/** 귀책 */
	@Schema(description = "귀책")
	private String imputetypename;

	/** 결재진행상태 */
	@Schema(description = "결재진행상태")
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

	/** 결재상신일자 */
	@Schema(description = "결재상신일자")
	private String approvaldt;

	/** chkflag */
	@Schema(description = "chkflag")
	private String chkflag;

	/** 처리상태 */
	@Schema(description = "처리상태")
	private String statusAj;

	/** 환산박스 */
	@Schema(description = "최초등록시간")
	private String adddate;

	/** 환산박스 */
	@Schema(description = "최초등록자")
	private String addwho;

	/** 제조일자 */
	@Schema(description = "제조일자")
	private String manufacturedt;

	/** 소비일자 */
	@Schema(description = "소비일자")
	private String expiredt;
	
	/** DEL_YN */
	@Schema(description = "DEL_YN")
	private String delYn;

    /** SAP 단가 */
    @Schema(description = "SAP 단가", example = "")
    private BigDecimal purchaseprice;
}
