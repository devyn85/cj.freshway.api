package cjfw.wms.dp.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author : YangChangHwan (iamai@cj.net)
 * @date : 2025.06.24
 * @description : 입고라벨출력(검수) Response DTO Class
 * @issues :
 *
 *         <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.06.24 YangChangHwan (iamai@cj.net) 생성
 *         </pre>
 */
@Schema(description = "입고라벨출력(검수) Response DTO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DpSkuLabelInspectResDto {

	/** 체크여부 */
	@Schema(description = "체크여부")
	private String checkyn;

	/** 물류센터코드/명 */
	@Schema(description = "물류센터코드/명")
	private String dccode;

	/** 조직 */
	@Schema(description = "조직")
	private String organize;

	/** 주문유형 */
	@Schema(description = "주문유형")
	private String ordertype;

	/** 주문유형명 */
	@Schema(description = "주문유형명")
	private String ordertypename;

	/** 협력사코드 */
	@Schema(description = "협력사코드")
	private String fromCustkey;

	/** 협력사명 */
	@Schema(description = "협력사명")
	private String fromCustname;

	/** 진행상태 */
	@Schema(description = "진행상태")
	private String status;

	/** 진행상태명 */
	@Schema(description = "진행상태명")
	private String statusname;

	/** 회사 id */
	@Schema(description = "회사 id")
	private String storerkey;

	/** 문서유형 */
	@Schema(description = "문서유형")
	private String doctype;

	/** 전표일자 */
	@Schema(description = "전표일자")
	private String slipdt;

	/** 전표번호 */
	@Schema(description = "전표번호")
	private String slipno;

	/** 조정조건 */
	@Schema(description = "조정조건")
	private String storagetype;

		/** 커스텀 엑스트라 체크박스 */
	@Schema(description = "커스텀 엑스트라 체크박스", example = "N")
	private String customRowCheckYn = "N";
}
