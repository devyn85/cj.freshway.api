package cjfw.wms.kp.dto;

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
 * @description : 지표 > 재고 운영 > 물류센터 Capa 스캔 현황 센터별 상세_탭 Response DTO Class
 * @issues :
 *
 *         <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.09.09 KimDongHan (liop0123@cj.net) 생성
 *         </pre>
 */
@Schema(description = "지표 > 재고 운영 > 물류센터 Capa 스캔 현황 센터별 상세_탭 Response DTO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class KpLocationCapaScanResT2Dto  extends CommonProcedureDto {
	
	/* 01. 일자 */
	@Schema(description = "01. 일자")
	private String confirmDate;

	/* 02. 물류센터 */
	@Schema(description = "02. 물류센터")
	private String dccode;
	
	/* 02. 물류센터명 */
	@Schema(description = "02. 물류센터명")
	private String dcname;

	/* 03. 피킹존 */
	@Schema(description = "03. 피킹존")
	private String locZone;

	/* 04. 로케이션 */
	@Schema(description = "04. 로케이션")
	private String loc;

	/* 05. 상태 */
	@Schema(description = "05. 상태")
	private String status;

	/* 06. 상태명 '0' 이면 빈랙 아니면 사용랙 */
	@Schema(description = "06. 상태명 '0' 이면 빈랙 아니면 사용랙")
	private String statusNm;

	/* 07. 저장조건 */
	@Schema(description = "07. 저장조건")
	private String storagetype;

	/* 08. 확정자 */
	//@MaskingId
	@Schema(description = "08. 확정자")
	private String confirmUser;

	/* 09. 확정일시 */
	@Schema(description = "09. 확정일시")
	private String confirmDate2;

}
