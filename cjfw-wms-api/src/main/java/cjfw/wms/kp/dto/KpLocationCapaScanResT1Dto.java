package cjfw.wms.kp.dto;

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
 * @date : 2025.09.09
 * @description : 지표 > 재고 운영 > 물류센터 Capa 스캔 현황 요약_탭 Response DTO Class
 * @issues :
 *
 *         <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.09.09 KimDongHan (liop0123@cj.net) 생성
 *         </pre>
 */
@Schema(description = "지표 > 재고 운영 > 물류센터 Capa 스캔 현황 요약_탭 Response DTO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class KpLocationCapaScanResT1Dto extends CommonProcedureDto {
	
	/* 01. 물류센터 */
	@Schema(description = "01. 물류센터")
	private String dccode;
	
	/* 02. 물류센터명 */
	@Schema(description = "02. 물류센터명")
	private String dcname;

	/* 03. 저장조건 */
	@Schema(description = "03. 저장조건")
	private String storagetype;

	/* 04. 피킹존 */
	@Schema(description = "04. 피킹존")
	private String zoneNm;

	/* 05. ZONE세부 */
	@Schema(description = "05. ZONE세부")
	private String locZone;

	/* 06. 피킹/보관 */
	@Schema(description = "06. 피킹/보관")
	private String locType;

	/* 07. 전체 CAPA */
	@Schema(description = "07. 전체 CAPA")
	private BigDecimal totCnt;

	/* 08. 잔여 CAPA(스캔내역) */
	@Schema(description = "08. 잔여 CAPA(스캔내역)")
	private BigDecimal statusCnt;

	/* 09. 현재보관 */
	@Schema(description = "09. 현재보관")
	private BigDecimal keepCnt;
	
	/* 피킹존 코드명 */
	@Schema(description = "피킹존 코드명")
	private String cdNm;
	
	/* 피킹존 코드 */
	@Schema(description = "피킹존 코드")
	private String comCd;


}
