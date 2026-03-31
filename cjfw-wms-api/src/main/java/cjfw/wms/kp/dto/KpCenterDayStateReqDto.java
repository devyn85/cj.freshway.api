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
 * @date : 2025.09.03
 * @description : 지표 > 센터 운영 > 출고 유형별 물동 현황 Request DTO Class
 * @issues :
 *         <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.09.03 KimDongHan (liop0123@cj.net) 생성
 *         </pre>
 */
@Schema(description = "지표 > 센터 운영 > 출고 유형별 물동 현황 Request DTO")
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class KpCenterDayStateReqDto extends CommonProcedureDto {
	
	@Schema(description = "물류센터")
	private String dccode;
	
	@Schema(description = "조회일자")
	private String docdt;

	@Schema(description = "경로")
	private String course;
	
	@Schema(description = "저장유무")
	private String channel;
	
	@Schema(description = "차량종류")
	private String contracttype;
	
	@Schema(description = "차량톤수")
	private String carcapacity;
	
}
