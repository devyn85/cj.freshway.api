package cjfw.wms.st.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : ParkJinWoo 
 * @date : 2025.07.10 
 * @description : 외부비축상품별수불현황 기능 reqDTO
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE AUTHOR MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.07.10 ParkJinWoo 생성
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StDailyInoutExDcDetailResDto {
	/* ───── 기본 정보 ───────────────────────────────────── */
	/** 상품코드 */
	@Schema(description = "상품코드", nullable = true, example = "")
	private String sku;

	/** 문서유형(DP · WD · RT · AJ 등) */
	@Schema(description = "문서유형(DP · WD · RT · AJ 등)", nullable = true, example = "")
	private String docType;

	/** Delivery Type(H 헤더의 DELIVERYTYPE) */
	@Schema(description = "Delivery Type(H 헤더의 DELIVERYTYPE)", nullable = true, example = "")
	private String deliveryType;

	/** 처리일자(Delivery Date) */
	@Schema(description = "처리일자(Delivery Date)", nullable = true, example = "")
	private String deliveryDate;

	/* ───── 수량 / BOX ──────────────────────────────────── */
	/** 확정수량 */
	@Schema(description = "확정수량", nullable = true, example = "")
	private double confirmQty;

	/** 확정 BOX 수(ETCQTY2) */
	@Schema(description = "확정 BOX 수(ETCQTY2)", nullable = true, example = "")
	private double confirmBox;

	/* ───── 거래처 정보 ─────────────────────────────────── */
	/** 거래처코드(출/입고 방향 따라 FROM/TO) */
	@Schema(description = "거래처코드(출/입고 방향 따라 FROM/TO)", nullable = true, example = "")
	private String custkey;

	/** 거래처명 */
	@Schema(description = "거래처명", nullable = true, example = "")
	private String custName;

	/* ───── BL / 계약 정보 ─────────────────────────────── */
	/** BL 번호(CONVSERIALNO) */
	@Schema(description = "BL 번호(CONVSERIALNO)", nullable = true, example = "")
	private String convSerialNo;

	/** 시리얼번호 */
	@Schema(description = "시리얼번호", nullable = true, example = "")
	private String serialNo;

	/** 계약유형 코드 */
	@Schema(description = "계약유형 코드", nullable = true, example = "")
	private String contractType;

	/** 계약처 코드 */
	@Schema(description = "계약처 코드", nullable = true, example = "")
	private String contractCustKey;

	/** 계약처 명 */
	@Schema(description = "계약처 명", nullable = true, example = "")
	private String contractCustName;

	/* ───── PO 정보 ────────────────────────────────────── */
	/** PO Key */
	@Schema(description = "PO Key", nullable = true, example = "")
	private String poKey;

	/** PO Line */
	@Schema(description = "PO Line", nullable = true, example = "")
	private String poLine;

	/* ───── 등록 / 수정 정보 ───────────────────────────── */
	/** 최초 등록자(이름 대체) */
	@Schema(description = "최초 등록자(이름 대체)", nullable = true, example = "")
	private String addWho;

	/** 최종 수정자(이름 대체) */
	@Schema(description = "최종 수정자(이름 대체)", nullable = true, example = "")
	private String editWho;
	/* ───── 등록 / 수정 정보 ───────────────────────────── */
	/** 최초 등록자(이름 대체) */
	@Schema(description = "최초 등록자(이름 대체)", nullable = true, example = "")
	private String addWhoId;

	/** 최종 수정자(이름 대체) */
	@Schema(description = "최종 수정자(이름 대체)", nullable = true, example = "")
	private String editWhoId;

	/** 등록 일시(yyyy-MM-dd HH:mm:ss) */
	@Schema(description = "등록 일시(yyyy-MM-dd HH:mm:ss)", nullable = true, example = "")
	private String addDate;
	
	/** 수정 일시(yyyy-MM-dd HH:mm:ss) */
	@Schema(description = "수정 일시(yyyy-MM-dd HH:mm:ss)", nullable = true, example = "")
	private String editDate;

		/** 커스텀 엑스트라 체크박스 */
	@Schema(description = "커스텀 엑스트라 체크박스", example = "N")
	private String customRowCheckYn = "N";
}
