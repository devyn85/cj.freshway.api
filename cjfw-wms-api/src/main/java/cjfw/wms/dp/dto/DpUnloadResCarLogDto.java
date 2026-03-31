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
 * @description : 입고하차관리 Request DTO Class
 * @issues :
 *
 *         <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.07.28 KimDongHyeon (tirran123@cj.net) 생성
 *         </pre>
 */
@Schema(description = "입고하차관리 Detail Response DTO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DpUnloadResCarLogDto {
	/** 커스텀 엑스트라 체크박스 */
	@Schema(description = "커스텀 엑스트라 체크박스", example = "N")
	private String customRowCheckYn = "N";

	/** 체크여부 */
	@Schema(description = "체크여부")
	private String checkyn;

	/** 센터코드 */
	@Schema(description = "센터코드")
	private String dccode;

	/** 화주코드 */
	@Schema(description = "화주코드")
	private String storerkey;

	/** 입고예정일 */
	@Schema(description = "입고예정일")
	private String slipdt;

	/** 거래처코드 */
	@Schema(description = "거래처코드")
	private String custkey;

	/** 운송업체코드 */
	@Schema(description = "운송업체코드")
	private String carrierkey;

	/** 배송그룹 */
	@Schema(description = "배송그룹")
	private String deliverygroup;

	/** 온도1 */
	@Schema(description = "온도1")
	private String temperature1;

	/** 온도2 */
	@Schema(description = "온도2")
	private String temperature2;

	/** 온도적정성 */
	@Schema(description = "온도적정성")
	private String tempoptitype;

	/** 배송시간 */
	@Schema(description = "배송시간")
	private String deliverytime;

	/** 배송메모 */
	@Schema(description = "배송메모")
	private String deliverymemo;

	/** 입력수정구분 */
	@Schema(description = "입력수정구분")
	private String iuflag;

	/** 채널 */
	@Schema(description = "채널")
	private String channel;

	/** 검수시간 */
	@Schema(description = "검수시간")
	private String insptime;

	/** 검수시간 */
	@Schema(description = "검수시간")
	private String adddate;

	/** 인보이스번호 */
	@Schema(description = "인보이스번호")
	private String docno;

	/** pk */
	@Schema(description = "pk")
	private String serialkey;

	/** , TO_INTIME */
	@Schema(description = ", TO_INTIME")
	private String toIntime;

	/** 행 상태 (I:신규, U:수정, D:삭제) */
	@Schema(description = "행 상태 (I:신규, U:수정, D:삭제)", nullable = true, example = "I")
	private String rowStatus;
}
