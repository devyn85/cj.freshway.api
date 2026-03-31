package cjfw.wms.ib.dto;

import java.math.BigDecimal;

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
 * @date : 2025.09.25
 * @description : 정산 > 3PL 수수료 > 일배 물류대행 파트너사 수수료 정산_검수관리_탭 Response DTO Class
 * @issues :
 *         <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.09.25 KimDongHan (liop0123@cj.net) 생성
 *         </pre>
 */
@Schema(description = "정산 > 3PL 수수료 > 일배 물류대행 파트너사 수수료 정산_검수관리_탭 Response DTO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IbTirdPartyMastResT3Dto extends CommonProcedureDto {
	
	/* 01. 일자 */
	@Schema(description = "01. 일자")
	private String deliverydate;

	/* 02. 협력사코드 */
	@Schema(description = "02. 협력사코드")
	private String fromcustkey;

	/* 03. 협력사명 */
	@Schema(description = "03. 협력사명")
	private String fromcustnm;

	/* 04. 일배구분 */
	@Schema(description = "04. 일배구분")
	private String deliverytype;

	/* 05. 검수예정건수 */
	@Schema(description = "05. 검수예정건수")
	private BigDecimal barCnt;

	/* 06. 검수완료건수 */
	@Schema(description = "06. 검수완료건수")
	private BigDecimal scanCnt;

	/* 07. 확정건수 */
	@Schema(description = "07. 확정건수")
	private BigDecimal cfcnt;

	/* 08. 미완료건수 */
	@Schema(description = "08. 미완료건수")
	private BigDecimal ncfcnt;

	/* 09. 미입고건수 */
	@Schema(description = "09. 미입고건수")
	private BigDecimal ndpCnt;

	/* 10. 검수완료율(%) */
	@Schema(description = "10. 검수완료율(%)")
	private String rate;
	
	/* 11. 진행상태 */
	@Schema(description = "11. 진행상태")
	private String status;
	
	/* DCCODE */
	@Schema(description = "DCCODE")
	private String dccode;
	
    /** 커스텀 엑스트라 체크박스 */
    @Schema(description = "커스텀 엑스트라 체크박스", example = "N")
    private String customRowCheckYn = "N";
}
