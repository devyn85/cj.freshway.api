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
 * @date : 2025.09.08
 * @description : 입고확정처리(수원3층) Master Response DTO Class
 * @issues :
 *
 *         <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.09.08 KimDongHyeon (tirran123@cj.net) 생성
 *         </pre>
 */
@Schema(description = "입고확정처리(수원3층) Master Response DTO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DpReceiptBoxResDto {
	/** * 체크여부 */
	@Schema(description = "* 체크여부")
	private String checkyn;

	/** * 센터코드 */
	@Schema(description = "* 센터코드")
	private String dccode;

	/** * 문서번호 */
	@Schema(description = "* 문서번호")
	private String docno;

	/** * 화주코드 */
	@Schema(description = "* 화주코드")
	private String storerkey;

	/** * 문서유형 */
	@Schema(description = "* 문서유형")
	private String doctype;

	/** * 발주유형 */
	@Schema(description = "* 발주유형")
	private String potype;

	/** * 발주유형명 */
	@Schema(description = "* 발주유형명")
	private String potypename;

	/** * 창고코드 */
	@Schema(description = "* 창고코드")
	private String organize;

	/** * 창고명 */
	@Schema(description = "* 창고명")
	private String organizename;

	/** * 주문유형 */
	@Schema(description = "* 주문유형")
	private String ordertype;

	/** * 주문유형명 */
	@Schema(description = "* 주문유형명")
	private String ordertypeName;

	/** * 상태 */
	@Schema(description = "* 상태")
	private String status;

	/** * 상태명 */
	@Schema(description = "* 상태명")
	private String statusname;

	/** * 문서일자 */
	@Schema(description = "* 문서일자")
	private String docdt;

	/** * 협력사코드 */
	@Schema(description = "* 협력사코드")
	private String fromCustkey;

	/** * 협력사명 */
	@Schema(description = "* 협력사명")
	private String fromCustname;

	/** * 전표일자 */
	@Schema(description = "* 전표일자")
	private String slipdt;

	/** * 전표번호 */
	@Schema(description = "* 전표번호")
	private String slipno;

	/** * 플랜트 */
	@Schema(description = "* 플랜트")
	private String plant;

		/** 커스텀 엑스트라 체크박스 */
	@Schema(description = "커스텀 엑스트라 체크박스", example = "N")
	private String customRowCheckYn = "N";
}
