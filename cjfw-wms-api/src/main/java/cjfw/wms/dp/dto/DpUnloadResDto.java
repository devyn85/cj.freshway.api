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
 * @date : 2025.07.28
 * @description : 입고하차관리 Master Response DTO Class
 * @issues :
 *
 *         <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.07.28 KimDongHyeon (tirran123@cj.net) 생성
 *         </pre>
 */
@Schema(description = "입고하차관리 Master Response DTO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DpUnloadResDto {

	/** 체크여부 */
	@Schema(description = "체크여부")
	private String checkyn;

	/** 출발센터코드 */
	@Schema(description = "출발센터코드")
	private String fromDccode;

	/** 출발조직 */
	@Schema(description = "출발조직")
	private String fromOrganize;

	/** 입고예정일 */
	@Schema(description = "입고예정일")
	private String slipdt;

	/** 문서일자 */
	@Schema(description = "문서일자")
	private String docdt;

	/** 문서번호 */
	@Schema(description = "문서번호")
	private String docno;

	/** 주문유형 */
	@Schema(description = "주문유형")
	private String ordertype;

	/** 출발센터명 */
	@Schema(description = "출발센터명")
	private String fromDcname;

	/** 출발거래처코드 */
	@Schema(description = "출발거래처코드")
	private String fromCustkey;

	/** 출발거래처명 */
	@Schema(description = "출발거래처명")
	private String fromCustname;

	/** 도착센터코드 */
	@Schema(description = "도착센터코드")
	private String toDccode;

	/** 도착센터명 */
	@Schema(description = "도착센터명")
	private String toDcname;

	/** 도착조직 */
	@Schema(description = "도착조직")
	private String toOrganize;

	/** 도착거래처코드 */
	@Schema(description = "도착거래처코드")
	private String toCustkey;

	/** 도착거래처명 */
	@Schema(description = "도착거래처명")
	private String toCustname;

	/** 상태 */
	@Schema(description = "상태")
	private String status;

	/** 센터코드 */
	@Schema(description = "센터코드")
	private String dccode;

	/** 화주코드 */
	@Schema(description = "화주코드")
	private String storerkey;

	/** 문서유형 */
	@Schema(description = "문서유형")
	private String doctype;

	/** 차량번호 */
	@Schema(description = "차량번호")
	private String carno;

	/** 출발예정일시 */
	@Schema(description = "출발예정일시")
	private String dcdeparturedt;

		/** 커스텀 엑스트라 체크박스 */
	@Schema(description = "커스텀 엑스트라 체크박스", example = "N")
	private String customRowCheckYn = "N";
}
