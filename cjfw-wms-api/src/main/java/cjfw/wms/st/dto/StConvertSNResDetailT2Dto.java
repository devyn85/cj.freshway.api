package cjfw.wms.st.dto;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author : KimDongHan (liop0123@cj.net)
 * @date : 2025.09.11
 * @description : 재고 > 재고조정 > 상품이력번호변경 입출이력_탭 Response DTO Class
 * @issues :
 *
 *         <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.09.11 KimDongHan (liop0123@cj.net) 생성
 *         </pre>
 */
@Schema(description = "재고 > 재고조정 > 상품이력번호변경 입출이력_탭 Response DTO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StConvertSNResDetailT2Dto {
	/** 커스텀 엑스트라 체크박스 */
	@Schema(description = "커스텀 엑스트라 체크박스", example = "N")
	private String customRowCheckYn = "N";

	
	/* 01 : 문서유형 */
	@Schema(description = "01 : 문서유형")
	private String doctype;

	/* 02 : 문서번호 */
	@Schema(description = "02 : 문서번호")
	private String docno;

	/* 03 : 품목번호 */
	@Schema(description = "03 : 품목번호")
	private String docline;

	/* 04 : 상품코드 */
	@Schema(description = "04 : 상품코드")
	private String sku;

	/* 05 : 상품명칭 */
	@Schema(description = "05 : 상품명칭")
	private String skuname;

	/* 06 : 단위 */
	@Schema(description = "06 : 단위")
	private String uom;

	/* 07 : 주문수량 */
	@Schema(description = "07 : 주문수량")
	private BigDecimal orderqty;

	/* 08 : 검수량 */
	@Schema(description = "08 : 검수량")
	private BigDecimal inspectqty;

	/* 09 : 확정수량 */
	@Schema(description = "09 : 확정수량")
	private BigDecimal confirmqty;

	/* 10 : 중량 */
	@Schema(description = "10 : 중량")
	private BigDecimal weight;

	/* 11 : 기준일(유통,제조) */
	@Schema(description = "11 : 기준일(유통,제조)")
	private String lottable01;

	/* 11 : 기준일(제조) */
	@Schema(description = "11 : 기준일(제조)")
	private String lotManufacture;
	 
	/* 11 : 기준일(소비) */
	@Schema(description = "11 : 기준일(소비)")
	private String lotExpire;		
	
	/* 12 : 유통기간(잔여/전체) */
	@Schema(description = "12 : 유통기간(잔여/전체)")
	private String durationTerm;

	/* 13 : 이력번호 */
	@Schema(description = "13 : 이력번호")
	private String serialno;

	/* 14 : 바코드 */
	@Schema(description = "14 : 바코드")
	private String barcode;

	/* 15 : B/L 번호 */
	@Schema(description = "15 : B/L 번호")
	private String convserialno;

	/* 16 : 도축일자 */
	@Schema(description = "16 : 도축일자")
	private String butcherydt;

	/* 17 : 도축장 */
	@Schema(description = "17 : 도축장")
	private String factoryname;

	/* 18 : 계약유형 */
	@Schema(description = "18 : 계약유형")
	private String contracttype;

	/* 19 : 계약업체 */
	@Schema(description = "19 : 계약업체")
	private String contractcompany;

	/* 20 : 계약업체명 */
	@Schema(description = "20 : 계약업체명")
	private String contractcompanyname;

	/* 21 : 유효일자(FROM) */
	@Schema(description = "21 : 유효일자(FROM)")
	private String fromvaliddt;

	/* 22 : 유효일자(TO) */
	@Schema(description = "22 : 유효일자(TO)")
	private String tovaliddt;

	/* DCCODE */
	@Schema(description = "DCCODE")
	private String dccode;

	/* STORERKEY */
	@Schema(description = "STORERKEY")
	private String storerkey;

	/* ORGANIZE */
	@Schema(description = "ORGANIZE")
	private String organize;

	/* AREA */
	@Schema(description = "AREA")
	private String area;

	/* STOCKID */
	@Schema(description = "STOCKID")
	private String stockid;

	/* DOCDT */
	@Schema(description = "DOCDT")
	private String docdt;

	/* DURATIONTYPE */
	@Schema(description = "DURATIONTYPE")
	private String durationtype;

	/* DURATION */
	@Schema(description = "DURATION")
	private String duration;

	/* SERIALLEVEL */
	@Schema(description = "SERIALLEVEL")
	private String seriallevel;

	/* SERIALTYPE */
	@Schema(description = "SERIALTYPE")
	private String serialtype;

	/* PLACEOFORIGIN */
	@Schema(description = "PLACEOFORIGIN")
	private String placeoforigin;

}
