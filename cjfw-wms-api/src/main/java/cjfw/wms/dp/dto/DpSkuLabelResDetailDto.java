package cjfw.wms.dp.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author : KimDongHyeon (tirran123@cj.net)
 * @date : 2025.08.07
 * @description : 입고라벨출력 Request DTO Class
 * @issues :
 *
 *         <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.08.07 KimDongHyeon (tirran123@cj.net) 생성
 *         </pre>
 */
@Schema(description = "입고라벨출력 Detail Response DTO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DpSkuLabelResDetailDto {
	/** 커스텀 엑스트라 체크박스 */
	@Schema(description = "커스텀 엑스트라 체크박스", example = "N")
	private String customRowCheckYn = "N";

	/** * 체크여부 */
	@Schema(description = "* 체크여부")
	private String checkyn;

	/** * 문서일자 */
	@Schema(description = "* 문서일자")
	private String docdt;

	/** * 문서라인 */
	@Schema(description = "* 문서라인")
	private String docline;

	/** * 전표일자 */
	@Schema(description = "* 전표일자")
	private String slipdt;

	/** * 전표번호 */
	@Schema(description = "* 전표번호")
	private String slipno;

	/** * 전표라인 */
	@Schema(description = "* 전표라인")
	private String slipline;
	private String sliplineSeq;

	/** * 입고전표일자 */
	@Schema(description = "* 입고전표일자")
	private String dpSlipdt;

	/** * 입고전표번호 */
	@Schema(description = "* 입고전표번호")
	private String dpSlipno;

	/** * 입고전표라인 */
	@Schema(description = "* 입고전표라인")
	private String dpSlipline;

	/** * 상태 */
	@Schema(description = "* 상태")
	private String status;

	/** * 상태명 */
	@Schema(description = "* 상태명")
	private String statusname;

	/** * 협력사명 */
	@Schema(description = "* 협력사명")
	private String custname;

	/** * 상품코드 */
	@Schema(description = "* 상품코드")
	private String sku;

	/** * 상품명 */
	@Schema(description = "* 상품명")
	private String skuname;

	/** * 인쇄여부 */
	@Schema(description = "* 인쇄여부")
	private String printyn;

	/** * 인쇄수량 */
	@Schema(description = "* 인쇄수량")
	private String printedqty;

	/** QTYPERBOX */
	@Schema(description = "QTYPERBOX")
	private String qtyperbox;

	/** * 라벨박스당수량 */
	@Schema(description = "* 라벨박스당수량")
	private String lblQtyperbox;

	/** * 단위 */
	@Schema(description = "* 단위")
	private String uom;

	/** * 주문수량 */
	@Schema(description = "* 주문수량")
	private String orderqty;

	/** * 로트 */
	@Schema(description = "* 로트")
	private String lot;

	/** LOTTABLE01 */
	@Schema(description = "LOTTABLE01")
	private String lottable01;

	/** * 유통기한 */
	@Schema(description = "* 유통기한")
	private String durationTerm;

	/** * 입고위치 */
	@Schema(description = "* 입고위치")
	private String toloc;

	/** * 라벨상품코드 */
	@Schema(description = "* 라벨상품코드")
	private String lblSku;

	/** * 라벨전표일자 */
	@Schema(description = "* 라벨전표일자")
	private String lblSlipdt;

	/** * 라벨원산지 */
	@Schema(description = "* 라벨원산지")
	private String lblPlaceoforigin;

	/** * 라벨상품명1 */
	@Schema(description = "* 라벨상품명1")
	private String lblSkuname1;

	/** * 라벨상품명2 */
	@Schema(description = "* 라벨상품명2")
	private String lblSkuname2;

	/** * 라벨협력사코드 */
	@Schema(description = "* 라벨협력사코드")
	private String lblCustkey;

	/** * 라벨협력사명 */
	@Schema(description = "* 라벨협력사명")
	private String lblCustname;

	/** * 라벨바코드 */
	@Schema(description = "* 라벨바코드")
	private String lblBarcode;

	/** * 라벨로트테이블01 */
	@Schema(description = "* 라벨로트테이블01")
	private String lblLottable01;

	/** * 라벨바코드텍스트 */
	@Schema(description = "* 라벨바코드텍스트")
	private String lblBarcodetext;

	/** * 라벨제목 */
	@Schema(description = "* 라벨제목")
	private String lblTitle;

	/** * 변환박스수량 */
	@Schema(description = "* 변환박스수량")
	private String convBoxqty;

	/** * 유통기한유형 */
	@Schema(description = "* 유통기한유형")
	private String durationtype;

	/** * 유통기한 */
	@Schema(description = "* 유통기한")
	private String duration;

	/** * 유통기한비율 */
	@Schema(description = "* 유통기한비율")
	private String durationRate;

	/** * 최종로트테이블01 */
	@Schema(description = "* 최종로트테이블01")
	private String lastlottable01;

	/** * 채널 */
	@Schema(description = "* 채널")
	private String channel;

	/** * 재고로트테이블01 */
	@Schema(description = "* 재고로트테이블01")
	private String stockLottable01;

	/** * 보관유형설명 */
	@Schema(description = "* 보관유형설명")
	private String storagetypedescr;

	/** * 물류센터코드 */
	@Schema(description = "* 물류센터코드")
	private String dccode;

	/** * 화주코드 */
	@Schema(description = "* 화주코드")
	private String storerkey;

	/** * 창고코드 */
	@Schema(description = "* 창고코드")
	private String organize;

	/** * zone */
	@Schema(description = "* zone")
	private String zone;

	/** * 구역코드 */
	@Schema(description = "* 구역코드")
	private String area;

	/** * 시리얼번호 */
	@Schema(description = "* 시리얼번호")
	private String serialno;

	/** * 변환시리얼번호 */
	@Schema(description = "* 변환시리얼번호")
	private String convserialno;

	/** * 변환로트 */
	@Schema(description = "* 변환로트")
	private String convertlot;

	/** * 시리얼레벨 */
	@Schema(description = "* 시리얼레벨")
	private String seriallevel;

	/** * 시리얼유형 */
	@Schema(description = "* 시리얼유형")
	private String serialtype;

	/** * 공장명 */
	@Schema(description = "* 공장명")
	private String factoryname;

	/** * 색상설명 */
	@Schema(description = "* 색상설명")
	private String colordescr;

	/** * 원산지 */
	@Schema(description = "* 원산지")
	private String placeoforigin;

	/** * 총중량 */
	@Schema(description = "* 총중량")
	private String grossweight;

	/** * 순중량 */
	@Schema(description = "* 순중량")
	private String netweight;

	/** * 입고문서유형 */
	@Schema(description = "* 입고문서유형")
	private String dpDoctype;

	/** * 바코드 */
	@Schema(description = "* 바코드")
	private String barcode;

	/** * 계약고객코드 */
	@Schema(description = "* 계약고객코드")
	private String contractcustkey;

	/** * 계약고객명 */
	@Schema(description = "* 계약고객명")
	private String wdCustname;

	/** * 계약유형 */
	@Schema(description = "* 계약유형")
	private String contracttype;

	/** * 도축일자 */
	@Schema(description = "* 도축일자")
	private String butcherydt;

	/** * 유효시작일자 */
	@Schema(description = "* 유효시작일자")
	private String fromvaliddt;

	/** * 유효종료일자 */
	@Schema(description = "* 유효종료일자")
	private String tovaliddt;

	/** * 오픈수량 */
	@Schema(description = "* 오픈수량")
	private String openqty;

	/** * 시리얼검사수량 */
	@Schema(description = "* 시리얼검사수량")
	private String serialinspectqty;

	/** * 시리얼스캔중량 */
	@Schema(description = "* 시리얼스캔중량")
	private String serialscanweight;

	/** * 공급업체 */
	@Schema(description = "* 공급업체")
	private String vendor;

	/** * 입고예외여부 */
	@Schema(description = "* 입고예외여부")
	private String excptYn;
	private String excptYnOri;

	/** * 기안번호 */
	@Schema(description = "* 기안번호")
	private String draftNo;

	/** * 최종변경자 */
	@Schema(description = "* 최종변경자")
	private String editwho;

	/** * 최종변경자 이름 */
	@Schema(description = "* 최종변경자 이름")
	private String editwhoName;

	/** * 제조 */
	@Schema(description = "* 제조")
	private String lotManufacture;

	/** * 유통 */
	@Schema(description = "* 유통")
	private String lotExpire;

	/** * 라벨시리얼번호 */
	@Schema(description = "* 라벨시리얼번호")
	private String lblSerialno;

	/** * 라벨중량 */
	@Schema(description = "* 라벨중량")
	private String lblWeight;

	/** * 라벨변환로트 */
	@Schema(description = "* 라벨변환로트")
	private String lblConvertlot;

	/** * 보관유형 */
	@Schema(description = "* 보관유형")
	private String storagetype;

	/** * 보관유형 */
	@Schema(description = "* 보관유형")
	private String storagetypeCode;

	/** * 방수 */
	@Schema(description = "* 방수")
	private String boxperlayer;

	/** * 단수 */
	@Schema(description = "* 단수")
	private String layerperplt;

	/** * loc */
	@Schema(description = "* loc")
	private String loc;

	/** * rowStatus */
	@Schema(description = "* rowStatus")
	private String rowStatus;
}
