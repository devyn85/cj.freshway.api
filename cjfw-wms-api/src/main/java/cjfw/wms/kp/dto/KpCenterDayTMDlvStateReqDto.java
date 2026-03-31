package cjfw.wms.kp.dto;

import cjfw.wms.common.extend.CommonProcedureDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author : KimDongHan (liop0123@cj.net)
 * @date : 2025.09.01
 * @description : 지표 > 센터 운영 > 배송조별 출자 평균시간 현황 Request DTO Class
 * @issues :
 *         <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.09.01 KimDongHan (liop0123@cj.net) 생성
 *         </pre>
 */
@Schema(description = "지표 > 센터 운영 > 배송조별 출자 평균시간 현황 Request DTO")
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class KpCenterDayTMDlvStateReqDto extends CommonProcedureDto {
	
	/* 배송일자 */
	@Schema(description = "배송일자")
	private String deliverydt;

	/* 물류센터 */
	@Schema(description = "물류센터")
	private String dccode;
	
}
