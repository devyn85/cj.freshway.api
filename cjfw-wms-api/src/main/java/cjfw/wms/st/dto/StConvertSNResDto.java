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
 * @date : 2025.09.11
 * @description : 재고 > 재고조정 > 상품이력번호변경 Response DTO Class
 * @issues :
 *
 *         <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.09.11 KimDongHan (liop0123@cj.net) 생성
 *         </pre>
 */
@Schema(description = "재고 > 재고조정 > 상품이력번호변경 Response DTO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StConvertSNResDto extends CommonProcedureDto {

	/* 01 : 상품분류 */
	@Schema(description = "01 : 상품분류")
	private String skugroup;

	/* 02 : 상품코드 */
	@Schema(description = "02 : 상품코드")
	private String sku;

	/* 03 : 상품명칭 */
	@Schema(description = "03 : 상품명칭")
	private String skuname;

	/* 04 : 기준일(유통,제조) */
	@Schema(description = "04 : 기준일(유통,제조)")
	private String lottable01;
	
	/* 04 : 기준일(제조) */
	@Schema(description = "04 : 기준일(제조)")
	private String lotManufacture;
	 
	/* 04 : 기준일(소비) */
	@Schema(description = "04 : 기준일(소비)")
	private String lotExpire;	

	/* 05 : 유통기간(잔여/전체) */
	@Schema(description = "05 : 유통기간(잔여/전체)")
	private String durationTerm;

	/* 06 : 이력변경사유 */
	@Schema(description = "06 : 이력변경사유")
	private String reasoncode;

	/* 07 : 이력변경사유명 */
	@Schema(description = "07 : 이력변경사유명")
	private String reasonmsg;

	/* 08 : 이력번호 */
	@Schema(description = "08 : 이력번호")
	private String serialno;

	/* 09 : B/L 번호 */
	@Schema(description = "09 : B/L 번호")
	private String convserialno;

	/* 10 : 도축장 */
	@Schema(description = "10 : 도축장")
	private String factoryname;

	/* SERIALKEY */
	@Schema(description = "SERIALKEY")
	private String serialkey;

	/* STORERKEY */
	@Schema(description = "STORERKEY")
	private String storerkey;

	/* PRE_SERIALNO */
	@Schema(description = "PRE_SERIALNO")
	private String preSerialno;

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

	/* DURATIONTYPE */
	@Schema(description = "DURATIONTYPE")
	private String durationtype;

	/* DURATION */
	@Schema(description = "DURATION")
	private String duration;

		/** 커스텀 엑스트라 체크박스 */
	@Schema(description = "커스텀 엑스트라 체크박스", example = "N")
	private String customRowCheckYn = "N";
}
