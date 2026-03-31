package cjfw.wms.kp.dto;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;


/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JangGwangSeok (breaker3317@cj.net) 
 * @date : 2025.12.02 
 * @description : 신규 마스터 정보 요청 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.12.02 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
 */
@Data
@Schema(description = "신규 마스터 정보 요청")
public class KpDcMonitoringReqDto extends CommonDto {

	/** 구분 */
	@Schema(description = "구분", example = "")
	private String gubun;
	
	/** 구분타입 */
	@Schema(description = "구분타입", example = "")
	private String gubunType;
	
	/** 이전 여부 */
	@Schema(description = "이전 여부", example = "")
	private String preYn;
	
	/** 센터코드 */
	@Schema(description = "센터코드", example = "")
	private String fixdccode;

}
