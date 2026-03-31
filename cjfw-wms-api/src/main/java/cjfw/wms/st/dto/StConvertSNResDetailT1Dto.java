package cjfw.wms.st.dto;

import java.math.BigDecimal;

import cjfw.wms.common.extend.CommonDto;
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
 * @description : 재고 > 재고조정 > 상품이력번호변경 재고현황_탭 Response DTO Class
 * @issues :
 *
 *         <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.09.11 KimDongHan (liop0123@cj.net) 생성
 *         </pre>
 */
@Schema(description = "재고 > 재고조정 > 상품이력번호변경 재고현황_탭 Response DTO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StConvertSNResDetailT1Dto  extends CommonDto {
	/** 커스텀 엑스트라 체크박스 */
	@Schema(description = "커스텀 엑스트라 체크박스", example = "N")
	private String customRowCheckYn = "N";

	
	/* 01 : 물류센터 */
	@Schema(description = "01 : 물류센터")
	private String dccode;
	
	/* 01 : 물류센터명 */
	@Schema(description = "01 : 물류센터명")
	private String dcname;

	/* 02 : 창고 */
	@Schema(description = "02 : 창고")
	private String organize;
	
	/* 02 : 창고 */
	@Schema(description = "02 : 창고")
	private String organizeNm;

	/* 03 : 재고위치 */
	@Schema(description = "03 : 재고위치")
	private String stocktype;

	/* 04 : 재고속성 */
	@Schema(description = "04 : 재고속성")
	private String stockgrade;

	/* 05 : 피킹존 */
	@Schema(description = "05 : 피킹존")
	private String zone;

	/* 06 : 로케이션 */
	@Schema(description = "06 : 로케이션")
	private String loc;

	/* 07 : 상품코드 */
	@Schema(description = "07 : 상품코드")
	private String sku;

	/* 08 : 상품명칭 */
	@Schema(description = "08 : 상품명칭")
	private String skuname;

	/* 09 : 저장조건 */
	@Schema(description = "09 : 저장조건")
	private String storagetype;

	/* 10 : 단위 */
	@Schema(description = "10 : 단위")
	private String uom;

	/* 11 : 현재고수량 */
	@Schema(description = "11 : 현재고수량")
	private BigDecimal qty;

	/* 12 : 가용재고수량 */
	@Schema(description = "12 : 가용재고수량")
	private BigDecimal openqty;

	/* 13 : 재고할당수량 */
	@Schema(description = "13 : 재고할당수량")
	private BigDecimal qtyallocated;

	/* 14 : 피킹재고 */
	@Schema(description = "14 : 피킹재고")
	private BigDecimal qtypicked;

	/* 15 : 유통기한임박여부 */
	@Schema(description = "15 : 유통기한임박여부")
	private String neardurationyn;

	/* 16 : 기준일(유통,제조) */
	@Schema(description = "16 : 기준일(유통,제조)")
	private String lottable01;
	
	/* 16 : 기준일(제조) */
	@Schema(description = "16 : 기준일(제조)")
	private String lotManufacture;
	 
	/* 16 : 기준일(소비) */
	@Schema(description = "16 : 기준일(소비)")
	private String lotExpire;		

	/* 17 : 유통기간(잔여/전체) */
	@Schema(description = "17 : 유통기간(잔여/전체)")
	private String durationTerm;

	/* 18 : 이력번호 */
	@Schema(description = "18 : 이력번호")
	private String serialno;

	/* 19 : 바코드 */
	@Schema(description = "19 : 바코드")
	private String barcode;

	/* 20 : B/L 번호 */
	@Schema(description = "20 : B/L 번호")
	private String convserialno;

	/* 21 : 도축일자 */
	@Schema(description = "21 : 도축일자")
	private String butcherydt;

	/* 22 : 도축장 */
	@Schema(description = "22 : 도축장")
	private String factoryname;

	/* 23 : 계약유형 */
	@Schema(description = "23 : 계약유형")
	private String contracttype;

	/* 24 : 계약업체 */
	@Schema(description = "24 : 계약업체")
	private String contractcompany;

	/* 25 : 계약업체명 */
	@Schema(description = "25 : 계약업체명")
	private String contractcompanyname;

	/* 26 : 유효일자(FROM) */
	@Schema(description = "26 : 유효일자(FROM)")
	private String fromvaliddt;

	/* 27 : 유효일자(TO) */
	@Schema(description = "27 : 유효일자(TO)")
	private String tovaliddt;

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
