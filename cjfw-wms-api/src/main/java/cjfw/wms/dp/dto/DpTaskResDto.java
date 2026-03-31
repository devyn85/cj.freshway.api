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
 * @date : 2025.07.31
 * @description : 입고검수지정 Master Response DTO Class
 * @issues :
 *
 *         <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.07.31 KimDongHyeon (tirran123@cj.net) 생성
 *         </pre>
 */
@Schema(description = "입고검수지정 Master Response DTO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DpTaskResDto {
	/** * 체크여부 */
	@Schema(description = "* 체크여부")
	private String checkyn;

	/** * 센터코드 */
	@Schema(description = "* 센터코드")
	private String dccode;

	/** * 화주코드 */
	@Schema(description = "* 화주코드")
	private String storerkey;

	/** * 검수시작일 */
	@Schema(description = "* 검수시작일")
	private String fromtaskdt;

	/** * 검수종료일 */
	@Schema(description = "* 검수종료일")
	private String totaskdt;

	/** * 거래처코드 */
	@Schema(description = "* 거래처코드")
	private String custkey;

	/** * 거래처명 */
	@Schema(description = "* 거래처명")
	private String custname;

	/** * 거래처여부 */
	@Schema(description = "* 거래처여부")
	private String custyn;

	/** * 삭제여부 */
	@Schema(description = "* 삭제여부")
	private String delYn;

	/** * 계획유형 */
	@Schema(description = "* 계획유형")
	private String plantype;

	/** * 발행유형 */
	@Schema(description = "* 발행유형")
	private String issuetype;

	/** VAL */
	@Schema(description = "VAL")
	private String val;

	/** 상품코드 */
	@Schema(description = "* 상품코드")
	private String sku;

		/** 커스텀 엑스트라 체크박스 */
	@Schema(description = "커스텀 엑스트라 체크박스", example = "N")
	private String customRowCheckYn = "N";
}
