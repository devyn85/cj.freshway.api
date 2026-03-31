package cjfw.wms.dp.dto;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author : KimDongHyeon (tirran123@cj.net)
 * @date : 2025.09.08
 * @description : 입고확정처리(수원3층) Request DTO Class
 * @issues :
 *
 *         <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.09.08 KimDongHyeon (tirran123@cj.net) 생성
 *         </pre>
 */
@Schema(description = "입고확정처리(수원3층) Detail Response DTO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DpReceiptBoxResExcelDto {
	/** 커스텀 엑스트라 체크박스 */
	@Schema(description = "커스텀 엑스트라 체크박스", example = "N")
	private String customRowCheckYn = "N";

	/** * 체크여부 */
	@Schema(description = "* 체크여부")
	private String checkyn;

	/** * 센터코드 */
	@Schema(description = "* 센터코드")
	private String dccode;

	/** * 화주코드 */
	@Schema(description = "* 화주코드")
	private String storerkey;

	/** * 창고코드 */
	@Schema(description = "* 창고코드")
	private String organize;

	/** * 문서일자 */
	@Schema(description = "* 문서일자")
	private String docdt;

	/** * 문서번호 */
	@Schema(description = "* 문서번호")
	private String docno;

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

	/** * 발주유형 */
	@Schema(description = "* 발주유형")
	private String potype;

	/** POTYPENAME */
	@Schema(description = "POTYPENAME")
	private String potypename;

	/** ORGANIZENAME */
	@Schema(description = "ORGANIZENAME")
	private String organizename;

	/** * 주문유형 */
	@Schema(description = "* 주문유형")
	private String ordertype;

	/** ORDERTYPE_NAME */
	@Schema(description = "ORDERTYPE_NAME")
	private String ordertypeName;

	/** * 협력사코드 */
	@Schema(description = "* 협력사코드")
	private String fromCustkey;

	/** * 협력사명 */
	@Schema(description = "* 협력사명")
	private String fromCustname;

	/** * 상품코드 */
	@Schema(description = "* 상품코드")
	private String sku;

	/** * 상품명 */
	@Schema(description = "* 상품명")
	private String skuname;

	/** * UOM */
	@Schema(description = "* UOM")
	private String uom;

	/** * 분자 */
	@Schema(description = "* 분자")
	private String bunja;

	/** * 분모 */
	@Schema(description = "* 분모")
	private String bunmo;

	/** ORDERQTY */
	@Schema(description = "ORDERQTY")
	private BigDecimal orderqty;

	/** ORDERQTY_BOX */
	@Schema(description = "ORDERQTY_BOX")
	private BigDecimal orderqtyBox;

	/** ORDERQTY_EA */
	@Schema(description = "ORDERQTY_EA")
	private BigDecimal orderqtyEa;

	/** INSPECTQTY */
	@Schema(description = "INSPECTQTY")
	private BigDecimal inspectqty;

	/** INSPECTQTY_BOX */
	@Schema(description = "INSPECTQTY_BOX")
	private BigDecimal inspectqtyBox;

	/** INSPECTQTY_EA */
	@Schema(description = "INSPECTQTY_EA")
	private BigDecimal inspectqtyEa;

	/** CONFIRMQTY */
	@Schema(description = "CONFIRMQTY")
	private String confirmqty;

	/** CONFIRMQTY_BOX */
	@Schema(description = "CONFIRMQTY_BOX")
	private BigDecimal confirmqtyBox;

	/** CONFIRMQTY_EA */
	@Schema(description = "CONFIRMQTY_EA")
	private BigDecimal confirmqtyEa;

	/** SHORTAGEQTY */
	@Schema(description = "SHORTAGEQTY")
	private BigDecimal shortageqty;

	/** SHORTAGEQTY_BOX */
	@Schema(description = "SHORTAGEQTY_BOX")
	private BigDecimal shortageqtyBox;

	/** SHORTAGEQTY_EA */
	@Schema(description = "SHORTAGEQTY_EA")
	private BigDecimal shortageqtyEa;

	/** * 결품이동수량 */
	@Schema(description = "* 결품이동수량")
	private BigDecimal shortagetranqty;

	/** * 결품이동수량(BOX) */
	@Schema(description = "* 결품이동수량(BOX)")
	private BigDecimal shortagetranqtyBox;

	/** * 결품이동수량(EA) */
	@Schema(description = "* 결품이동수량(EA)")
	private BigDecimal shortagetranqtyEa;

	/** * 처리수량 */
	@Schema(description = "* 처리수량")
	private BigDecimal tranqty;

	/** * 처리수량(BOX) */
	@Schema(description = "* 처리수량(BOX)")
	private BigDecimal tranqtyBox;

	/** * 처리수량(EA) */
	@Schema(description = "* 처리수량(EA)")
	private BigDecimal tranqtyEa;

	/** TOLOC */
	@Schema(description = "TOLOC")
	private String toloc;

	/** * 유통기한 */
	@Schema(description = "* 유통기한")
	private String lottable01;

	/** * 잔여기간/총기간 */
	@Schema(description = "* 잔여기간/총기간")
	private String durationTerm;

	/** * 색상/규격 */
	@Schema(description = "* 색상/규격")
	private String colordescr;

	/** * 참조2 */
	@Schema(description = "* 참조2")
	private String reference02;

	/** * 사유코드 */
	@Schema(description = "* 사유코드")
	private String reasoncode;

	/** * 사유메시지 */
	@Schema(description = "* 사유메시지")
	private String reasonmsg;

	/** * 유통임박여부 */
	@Schema(description = "* 유통임박여부")
	private String neardurationyn;

	/** * 상태 */
	@Schema(description = "* 상태")
	private String status;

	/** * 상태명 */
	@Schema(description = "* 상태명")
	private String statusname;

	/** * 입수 */
	@Schema(description = "* 입수")
	private String qtyperbox;

	/** * 저장유형 */
	@Schema(description = "* 저장유형")
	private String channel;

	/** * 유통기한기간 */
	@Schema(description = "* 유통기한기간")
	private String duration;

	/** * 유통기한구분 */
	@Schema(description = "* 유통기한구분")
	private String durationtype;

	/** * 최종유통기한(미사용) */
	@Schema(description = "* 최종유통기한(미사용)")
	private String lastlottable01;

	/** * 재고ID */
	@Schema(description = "* 재고ID")
	private String stockid;

	/** * 재고등급 */
	@Schema(description = "* 재고등급")
	private String stockgrade;

	/** * 플랜트 */
	@Schema(description = "* 플랜트")
	private String plant;

	/** * 플랜트명 */
	@Schema(description = "* 플랜트명")
	private String plantDescr;

	/** * 적치유형명 */
	@Schema(description = "* 적치유형명")
	private String channelName;

	/** * 보관유형명 */
	@Schema(description = "* 보관유형명")
	private String storagetypename;

	/** BOX_INFO */
	@Schema(description = "BOX_INFO")
	private String boxInfo;

	/** * 팔렛ID */
	@Schema(description = "* 팔렛ID")
	private String pltid;

	/** * 발주단위 */
	@Schema(description = "* 발주단위")
	private String dpUom;
}
