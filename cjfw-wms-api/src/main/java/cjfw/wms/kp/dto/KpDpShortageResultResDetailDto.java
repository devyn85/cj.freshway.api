package cjfw.wms.kp.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author : KimDongHan (liop0123@cj.net)
 * @date : 2025.09.08
 * @description : 지표/모니터링결품실적 Request DTO Class
 * @issues :
 *
 *         <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.09.08 KimDongHan (liop0123@cj.net) 생성
 *         </pre>
 */
@Schema(description = "지표/모니터링결품실적 Detail Response DTO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class KpDpShortageResultResDetailDto {
	/** * 체크여부 */
	@Schema(description = "* 체크여부")
	private String checkyn;

	/** * 센터코드 */
	@Schema(description = "* 센터코드")
	private String dccode;

	/** * 화주코드 */
	@Schema(description = "* 화주코드")
	private String storerkey;

	/** * 거래처코드 */
	@Schema(description = "* 거래처코드")
	private String custkey;

	/** * 상품코드 */
	@Schema(description = "* 상품코드")
	private String sku;

	/** * 상품명 */
	@Schema(description = "* 상품명")
	private String skuname;

	/** * 보관유형 */
	@Schema(description = "* 보관유형")
	private String putawaytype;

	/** * 상품그룹1 */
	@Schema(description = "* 상품그룹1")
	private String skugroup1;

	/** * 상품그룹2 */
	@Schema(description = "* 상품그룹2")
	private String skugroup2;

	/** * 상품여부 */
	@Schema(description = "* 상품여부")
	private String skuyn;

	/** * 검수시작일 */
	@Schema(description = "* 검수시작일")
	private String fromtaskdt;

	/** * 검수종료일 */
	@Schema(description = "* 검수종료일")
	private String totaskdt;

	/** * 삭제여부 */
	@Schema(description = "* 삭제여부")
	private String delYn;

	/** * 계획유형 */
	@Schema(description = "* 계획유형")
	private String plantype;

	/** VAL */
	@Schema(description = "VAL")
	private String val;

}
