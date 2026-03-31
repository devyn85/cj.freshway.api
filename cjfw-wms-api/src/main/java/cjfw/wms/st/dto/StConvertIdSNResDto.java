package cjfw.wms.st.dto;

import cjfw.wms.common.extend.CommonProcedureDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author : KimDongHan (liop0123@cj.net)
 * @date : 2025.09.16
 * @description : 재고 > 재고조정 > 이력상품바코드변경 Master Response DTO Class
 * @issues :
 *
 *         <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.09.16 KimDongHan (liop0123@cj.net) 생성
 *         </pre>
 */
@Schema(description = "재고 > 재고조정 > 이력상품바코드변경 Master Response DTO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StConvertIdSNResDto extends CommonProcedureDto {

	/* 01 : 물류센터 */
	@Schema(description = "01 : 물류센터")
	private String dccode;

	/* 02 : 창고 */
	@Schema(description = "02 : 창고")
	private String organize;

	/* 03 : 재고속성 */
	@Schema(description = "03 : 재고속성")
	private String stockgradename;

	/* 04 : 상품분류 */
	@Schema(description = "04 : 상품분류")
	private String skugroup;

	/* 05 : 상품코드 */
	@Schema(description = "05 : 상품코드")
	private String sku;

	/* 06 : 상품명칭 */
	@Schema(description = "06 : 상품명칭")
	private String skuname;

	/* 07 : 로케이션 */
	@Schema(description = "07 : 로케이션")
	private String fromLoc;

	/* 08 : 단위 */
	@Schema(description = "08 : 단위")
	private String uom;

	/* 09 : 현재고수량 */
	@Schema(description = "09 : 현재고수량")
	private String qty;

	/* 10 : 가용재고수량 */
	@Schema(description = "10 : 가용재고수량")
	private String openqty;

	/* 11 : 재고할당수량 */
	@Schema(description = "11 : 재고할당수량")
	private String qtyallocated;

	/* 12 : 피킹재고 */
	@Schema(description = "12 : 피킹재고")
	private String qtypicked;

	/* 13 : 작업수량 */
	@Schema(description = "13 : 작업수량")
	private String tranqty;

	/* 14 : 기준일(유통,제조) */
	@Schema(description = "14 : 기준일(유통,제조)")
	private String lottable01;

	/* 14 : 기준일(제조) */
	@Schema(description = "14 : 기준일(제조)")
	private String lotManufacture;

	/* 14 : 기준일(소비) */
	@Schema(description = "14 : 기준일(소비)")
	private String lotExpire;

	/* 15 : 유통기간(잔여/전체) */
	@Schema(description = "15 : 유통기간(잔여/전체)")
	private String durationTerm;

	/* 16 : 이력번호 */
	@Schema(description = "16 : 이력번호")
	private String serialno;

	/* 17 : B/L 번호 */
	@Schema(description = "17 : B/L 번호")
	private String convserialno;

	/* 18 : PO번호 */
	@Schema(description = "18 : PO번호")
	private String dpDocno;

	/* 19 : 도축일자 */
	@Schema(description = "19 : 도축일자")
	private String butcherydt;

	/* 20 : 도축장 */
	@Schema(description = "20 : 도축장")
	private String factoryname;

	/* 21 : 계약유형 */
	@Schema(description = "21 : 계약유형")
	private String contracttype;

	/* 22 : 계약업체 */
	@Schema(description = "22 : 계약업체")
	private String contractcompany;

	/* 23 : 계약업체명 */
	@Schema(description = "23 : 계약업체명")
	private String contractcompanyname;

	/* 24 : 유효일자(FROM) */
	@Schema(description = "24 : 유효일자(FROM)")
	private String fromvaliddt;

	/* 25 : 유효일자(TO) */
	@Schema(description = "25 : 유효일자(TO)")
	private String tovaliddt;

	/* 26 : 재고ID */
	@Schema(description = "26 : 재고ID")
	private String fromStockid;

	/* DURATION */
	@Schema(description = "DURATION")
	private String duration;

	/* DURATIONTYPE */
	@Schema(description = "DURATIONTYPE")
	private String durationtype;

	/* STORERKEY */
	@Schema(description = "STORERKEY")
	private String storerkey;

	/* AREA */
	@Schema(description = "AREA")
	private String area;

	/* AREANAME */
	@Schema(description = "AREANAME")
	private String areaname;

	/* FROM_STOCKGRADE */
	@Schema(description = "FROM_STOCKGRADE")
	private String fromStockgrade;

	/* FROM_LOT */
	@Schema(description = "FROM_LOT")
	private String fromLot;

	/* STOCKTYPE */
	@Schema(description = "STOCKTYPE")
	private String stocktype;

	/* STOCKTYPEDESCR */
	@Schema(description = "STOCKTYPEDESCR")
	private String stocktypedescr;

	/* SERIALLEVEL */
	@Schema(description = "SERIALLEVEL")
	private String seriallevel;

	/* SERIALTYPE */
	@Schema(description = "SERIALTYPE")
	private String serialtype;

	/* COLORDESCR */
	@Schema(description = "COLORDESCR")
	private String colordescr;

	/* PLACEOFORIGIN */
	@Schema(description = "PLACEOFORIGIN")
	private String placeoforigin;

		/** 커스텀 엑스트라 체크박스 */
	@Schema(description = "커스텀 엑스트라 체크박스", example = "N")
	private String customRowCheckYn = "N";
}
