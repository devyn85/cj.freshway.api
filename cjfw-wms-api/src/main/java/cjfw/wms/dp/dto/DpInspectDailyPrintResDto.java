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
 * @date : 2025.07.10
 * @description : 일배입고검수출력 Master Response DTO Class
 * @issues :
 *
 *         <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.07.10 KimDongHyeon (tirran123@cj.net) 생성
 *         </pre>
 */
@Schema(description = "일배입고검수출력 Master Response DTO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DpInspectDailyPrintResDto {

	/** 체크여부 */
	@Schema(description = "체크여부")
	private String checkyn;

	/** 센터코드 */
	@Schema(description = "센터코드")
	private String dccode;

	/** 화주코드 */
	@Schema(description = "화주코드")
	private String storerkey;

	/** 문서유형 */
	@Schema(description = "문서유형")
	private String doctype;

	/** 조직 */
	@Schema(description = "조직")
	private String organize;

	/** 주문유형 */
	@Schema(description = "주문유형")
	private String ordertype;

	/** 주문유형명 */
	@Schema(description = "주문유형명")
	private String ordertypeName;

	/** 문서일자 */
	@Schema(description = "문서일자")
	private String docdt;

	/** 거래처코드 */
	@Schema(description = "거래처코드")
	private String custkey;

	/** 출발거래처코드 */
	@Schema(description = "출발거래처코드")
	private String fromCustkey;

	/** 웨이브키 */
	@Schema(description = "웨이브키")
	private String wavekey;

	/** 출발거래처명 */
	@Schema(description = "출발거래처명")
	private String fromCustname;

	/** 상태 */
	@Schema(description = "상태")
	private String status;

	/** 입고예정일 */
	@Schema(description = "입고예정일")
	private String slipdt;

	/** 입고예정번호 */
	@Schema(description = "입고예정번호")
	private String slipno;

	/** channel */
	@Schema(description = "channel")
	private String channel;
		/** 커스텀 엑스트라 체크박스 */
	@Schema(description = "커스텀 엑스트라 체크박스", example = "N")
	private String customRowCheckYn = "N";
}
