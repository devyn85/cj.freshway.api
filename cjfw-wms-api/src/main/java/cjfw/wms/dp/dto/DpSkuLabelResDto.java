package cjfw.wms.dp.dto;

import java.util.List;

import cjfw.wms.common.annotation.MultiSearch;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author : KimDongHyeon (tirran123@cj.net)
 * @date : 2025.08.07
 * @description : 입고라벨출력 Master Response DTO Class
 * @issues :
 *
 *         <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.08.07 KimDongHyeon (tirran123@cj.net) 생성
 *         </pre>
 */
@Schema(description = "입고라벨출력 Master Response DTO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DpSkuLabelResDto {
	/** * 체크여부 */
	@Schema(description = "* 체크여부")
	private String checkyn;

	/** * 물류센터코드 */
	@Schema(description = "* 물류센터코드")
	private String dccode;

	/** * 창고코드 */
	@Schema(description = "* 창고코드")
	private String organize;

	/** * 주문유형 */
	@Schema(description = "* 주문유형")
	private String ordertype;

	/** * 주문유형명 */
	@Schema(description = "* 주문유형명")
	private String ordertypename;

	/** * 협력사코드 */
	@Schema(description = "* 협력사코드")
	private String fromCustkey;

	/** * 협력사명 */
	@Schema(description = "* 협력사명")
	private String fromCustname;

	/** * 상태 */
	@Schema(description = "* 상태")
	private String status;

	/** * 상태명 */
	@Schema(description = "* 상태명")
	private String statusname;

	/** * 화주코드 */
	@Schema(description = "* 화주코드")
	private String storerkey;

	/** * 문서유형 */
	@Schema(description = "* 문서유형")
	private String doctype;

	/** * 전표일자 */
	@Schema(description = "* 전표일자")
	private String slipdt;

	/** * 전표번호 */
	@Schema(description = "* 전표번호")
	private String slipno;

	/** * 보관유형 */
	@Schema(description = "* 보관유형")
	private String storagetype;

	/** * 구역코드 */
	@Schema(description = "* 구역코드")
	private String area;

		/** 커스텀 엑스트라 체크박스 */
	@Schema(description = "커스텀 엑스트라 체크박스", example = "N")
	private String customRowCheckYn = "N";
	
    /** 문서번호(다중검색) */
	@MultiSearch
    @Schema(description = "문서번호-다중검색")
    private List<String> docnoMulti;
}
