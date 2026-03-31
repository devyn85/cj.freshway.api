package cjfw.wms.cm.entity;

import cjfw.wms.common.extend.CommonProcedureDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : KwonJungYun (jungyun8667@cj.net)
 * @date : 2025.07.09
 * @description : 시스템작업 임시 SN Entity
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.08.28 KimDongHan (liop0123@cj.net) 생성 </pre>
 */
@Data
@NoArgsConstructor
public class CmSyProcessTempSnEntity extends CommonProcedureDto {

	/** 데이터번호 */
	@Schema(description = "데이터번호")
	private String serialkey;

	/** 프로세스타입 */
	@Schema(description = "프로세스타입")
	private String processtype;

	/** 프로세스생성자 */
	@Schema(description = "프로세스생성자")
	private String processcreator;

	/** 배치고유DBID */
	@Schema(description = "배치고유DBID")
	private String spid;

	/** 배치처리여부 */
	@Schema(description = "배치처리여부")
	private String processflag;

	/** HEADERSERIALKEY */
	@Schema(description = "HEADERSERIALKEY")
	private String headerserialkey;

	/** DETAILSERIALKEY */
	@Schema(description = "DETAILSERIALKEY")
	private String detailserialkey;

	/** 센터코드 */
	@Schema(description = "센터코드")
	private String dccode;

	/** 고객사코드 */
	@Schema(description = "고객사코드")
	private String storerkey;

	/** 조직코드 */
	@Schema(description = "조직코드")
	private String organize;

	/** 창고코드 SAP 창고 혹은 별도의 창고 코드 */
	@Schema(description = "창고코드 SAP 창고 혹은 별도의 창고 코드")
	private String area;

	/** 상품코드 */
	@Schema(description = "상품코드")
	private String sku;

	/** TO 재고 구분 ID */
	@Schema(description = "TO 재고 구분 ID")
	private String stockid;

	/** BARCODE */
	@Schema(description = "BARCODE")
	private String barcode;

	/** EXTBARCODE1 */
	@Schema(description = "EXTBARCODE1")
	private String extbarcode1;

	/** EXTBARCODE2 */
	@Schema(description = "EXTBARCODE2")
	private String extbarcode2;

	/** SERIALNO */
	@Schema(description = "SERIALNO")
	private String serialno;

	/** CONVSERIALNO */
	@Schema(description = "CONVSERIALNO")
	private String convserialno;

	/** SERIALLEVEL */
	@Schema(description = "SERIALLEVEL")
	private String seriallevel;

	/** SERIALTYPE */
	@Schema(description = "SERIALTYPE")
	private String serialtype;

	/** FACTORYKEY */
	@Schema(description = "FACTORYKEY")
	private String factorykey;

	/** FACTORYNAME */
	@Schema(description = "FACTORYNAME")
	private String factoryname;

	/** PLACEOFORIGIN */
	@Schema(description = "PLACEOFORIGIN")
	private String placeoforigin;

	/** COUNTRYOFORIGIN */
	@Schema(description = "COUNTRYOFORIGIN")
	private String countryoforigin;

	/** PLANT */
	@Schema(description = "PLANT")
	private String plant;

	/** VENDOR */
	@Schema(description = "VENDOR")
	private String vendor;

	/** CONVERTLOT */
	@Schema(description = "CONVERTLOT")
	private String convertlot;

	/** MEMO */
	@Schema(description = "MEMO")
	private String memo;

	/** BLTYPE */
	@Schema(description = "BLTYPE")
	private String bltype;

	/** STORAGELOC */
	@Schema(description = "STORAGELOC")
	private String storageloc;

	/** CONVSTORAGETYPE */
	@Schema(description = "CONVSTORAGETYPE")
	private String convstoragetype;

	/** REGTYPE */
	@Schema(description = "REGTYPE")
	private String regtype;

	/** LOTTABLE01 */
	@Schema(description = "LOTTABLE01")
	private String lottable01;

	/** DURATION */
	@Schema(description = "DURATION")
	private String duration;

	/** 단위 */
	@Schema(description = "단위")
	private String uom;

	/** 주문수량 */
	@Schema(description = "주문수량")
	private String orderqty;

	/** 작업예정 */
	@Schema(description = "작업예정")
	private String openqty;

	/** DPINSPECTQTY */
	@Schema(description = "DPINSPECTQTY")
	private String dpinspectqty;

	/** 작업완료량 */
	@Schema(description = "작업완료량")
	private String confirmqty;

	/** OPENSTOCKQTY */
	@Schema(description = "OPENSTOCKQTY")
	private String openstockqty;

	/** ALLOCATEDQTY */
	@Schema(description = "ALLOCATEDQTY")
	private String allocatedqty;

	/** PICKEDQTY */
	@Schema(description = "PICKEDQTY")
	private String pickedqty;

	/** WDINSPECTQTY */
	@Schema(description = "WDINSPECTQTY")
	private String wdinspectqty;

	/** SHIPPEDQTY */
	@Schema(description = "SHIPPEDQTY")
	private String shippedqty;

	/** GROSSWEIGHT */
	@Schema(description = "GROSSWEIGHT")
	private String grossweight;

	/** NETWEIGHT */
	@Schema(description = "NETWEIGHT")
	private String netweight;

	/** 계약유형 */
	@Schema(description = "계약유형")
	private String contracttype;

	/** 도축일자(보세입고일) */
	@Schema(description = "도축일자(보세입고일)")
	private String butcherydt;

	/** 유효기간(FROM) */
	@Schema(description = "유효기간(FROM)")
	private String fromvaliddt;

	/** 유효기간(TO) */
	@Schema(description = "유효기간(TO)")
	private String tovaliddt;

	/** 현재보관로케이션 */
	@Schema(description = "현재보관로케이션")
	private String loc;

	/** 입고거래처 */
	@Schema(description = "입고거래처")
	private String dpCustkey;

	/** 입고문서유형 */
	@Schema(description = "입고문서유형")
	private String dpDoctype;

	/** 입고문서일자 */
	@Schema(description = "입고문서일자")
	private String dpDocdt;

	/** 입고문서번호 */
	@Schema(description = "입고문서번호")
	private String dpDocno;

	/** 입고문서라인 */
	@Schema(description = "입고문서라인")
	private String dpDocline;

	/** DP_SLIPDT */
	@Schema(description = "DP_SLIPDT")
	private String dpSlipdt;

	/** DP_SLIPNO */
	@Schema(description = "DP_SLIPNO")
	private String dpSlipno;

	/** DP_SLIPLINE */
	@Schema(description = "DP_SLIPLINE")
	private String dpSlipline;

	/** 출고거래처 */
	@Schema(description = "출고거래처")
	private String wdCustkey;

	/** 출고문서유형 */
	@Schema(description = "출고문서유형")
	private String wdDoctype;

	/** 출고문서일자 */
	@Schema(description = "출고문서일자")
	private String wdDocdt;

	/** 출고문서번호 */
	@Schema(description = "출고문서번호")
	private String wdDocno;

	/** 출고문서라인 */
	@Schema(description = "출고문서라인")
	private String wdDocline;

	/** 출고전표일자 */
	@Schema(description = "출고전표일자")
	private String wdSlipdt;

	/** 출고전표번호 */
	@Schema(description = "출고전표번호")
	private String wdSlipno;

	/** 출고전표라인 */
	@Schema(description = "출고전표라인")
	private String wdSlipline;

	/** PRINT_YN */
	@Schema(description = "PRINT_YN")
	private String printYn;

	/** 상태 */
	@Schema(description = "상태")
	private String status;

	/** 삭제여부 */
	@Schema(description = "삭제여부")
	private String delYn;

	/** 데이터흐름제어 */
	@Schema(description = "데이터흐름제어")
	private String trafficcop;

	/** 아카이브제어 */
	@Schema(description = "아카이브제어")
	private String archivecop;

	/** 배치처리메세지 */
	@Schema(description = "배치처리메세지")
	private String processmsg;

	/** PRINTEDQTY */
	@Schema(description = "PRINTEDQTY")
	private String printedqty;

	/** 계약거래처 */
	@Schema(description = "계약거래처")
	private String contractcustkey;

	/** PO번호 */
	@Schema(description = "PO번호")
	private String pokey;

	/** PO라인번호 */
	@Schema(description = "PO라인번호")
	private String poline;

	/** 최초등록시간 */
	@Schema(description = "최초등록시간")
	private String adddate;

	/** 최종변경시간 */
	@Schema(description = "최종변경시간")
	private String editdate;

	/** 최초등록자 */
	@Schema(description = "최초등록자")
	private String addwho;

	/** 최종변경자 */
	@Schema(description = "최종변경자")
	private String editwho;
}
