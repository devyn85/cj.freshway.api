package cjfw.wms.st.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author : sss (kduimux@cj.net)
 * @date : 2025.07.31
 * @description : 수불현황 Request DTO Class
 * @issues :
 *
 *         <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.07.31 sss (kduimux@cj.net) 생성
 *         </pre>
 */
@Schema(description = "수불현황 Detail Response DTO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StInoutResultResDetailDto {
	/** 커스텀 엑스트라 체크박스 */
	@Schema(description = "커스텀 엑스트라 체크박스", example = "N")
	private String customRowCheckYn = "N";

	@Schema(description = "일련번호")
	private String serialkey;

	@Schema(description = "처리일자")
	private String trandate;

	@Schema(description = "센터코드")
	private String dccode;

	@Schema(description = "거래유형")
	private String trantype;

	@Schema(description = "거래유형명")
	private String trantypename;

	@Schema(description = "화주코드")
	private String storerkey;

	@Schema(description = "조직(부서)")
	private String organize;

	@Schema(description = "시리얼여부")
	private String serialyn;

	@Schema(description = "시리얼여부명")
	private String serialynname;

	@Schema(description = "작업구역")
	private String area;

	@Schema(description = "상품코드")
	private String sku;

	@Schema(description = "상품명")
	private String skuname;

	@Schema(description = "단위")
	private String uom;

	@Schema(description = "이전 재고수량")
	private String fromStockqty;

	@Schema(description = "이후 재고수량")
	private String toStockqty;

	@Schema(description = "수량")
	private String qty;

	@Schema(description = "이전 로케이션")
	private String fromLoc;

	@Schema(description = "이전 LOT")
	private String fromLot;

	@Schema(description = "이전 LOT 상세1")
	private String fromLottable01;

	@Schema(description = "이전 재고ID")
	private String fromStockid;

	@Schema(description = "이전 재고유형")
	private String fromStocktype;

	@Schema(description = "이전 재고등급")
	private String fromStockgrade;

	@Schema(description = "이후 로케이션")
	private String toLoc;

	@Schema(description = "이후 LOT")
	private String toLot;

	@Schema(description = "이후 LOT 상세1")
	private String toLottable01;

	@Schema(description = "이후 재고ID")
	private String toStockid;

	@Schema(description = "이후 재고유형")
	private String toStocktype;

	@Schema(description = "이후 재고등급")
	private String toStockgrade;

	@Schema(description = "등록일시")
	private String adddate;

	@Schema(description = "등록자")
	private String addwho;

	@Schema(description = "등록자명")
	private String username;

	@Schema(description = "이전 시리얼번호")
	private String fromSerialno;

	@Schema(description = "이전 BL번호")
	private String fromConvserialno;

	@Schema(description = "이전 생산공장명")
	private String fromFactoryname;

	@Schema(description = "이전 도축일")
	private String fromButcherydt;

	@Schema(description = "이전 계약회사")
	private String fromContractcompany;

	@Schema(description = "이전 계약회사명")
	private String fromContractcompanyname;

	@Schema(description = "이전 유효일자(From)")
	private String fromFromvaliddt;

	@Schema(description = "이전 유효일자(To)")
	private String fromTovaliddt;

	@Schema(description = "이전 계약유형")
	private String fromContracttype;

	@Schema(description = "이전 바코드")
	private String fromBarcode;

	@Schema(description = "이후 시리얼번호")
	private String toSerialno;

	@Schema(description = "이후 BL번호")
	private String toConvserialno;

	@Schema(description = "이후 생산공장명")
	private String toFactoryname;

	@Schema(description = "이후 도축일")
	private String toButcherydt;

	@Schema(description = "이후 계약회사")
	private String toContractcompany;

	@Schema(description = "이후 계약회사명")
	private String toContractcompanyname;

	@Schema(description = "이후 유효일자(From)")
	private String toFromvaliddt;

	@Schema(description = "이후 유효일자(To)")
	private String toTovaliddt;

	@Schema(description = "이후 계약유형")
	private String toContracttype;

	@Schema(description = "이후 바코드")
	private String toBarcode;
}
