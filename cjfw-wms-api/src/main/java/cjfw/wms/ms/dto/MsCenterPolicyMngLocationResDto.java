package cjfw.wms.ms.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JeongHyeongCheol (wjdgudcjf@cj.net) 
 * @date : 2025.10.20 
 * @description : 센터정책관리 응답 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.10.20 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
 */
@Data
public class MsCenterPolicyMngLocationResDto {
	
	/** serialkey */
	@Schema(description = "serialkey", example = "")
	private String serialkey;
	
	/** 센터유형 */
	@Schema(description = "센터유형", example = "")
	private String centerType;
	
	/** 물류센터코드 */
	@Schema(description = "물류센터코드", example = "")
	private String dccode;
	
	/** 물류센터명 */
	@Schema(description = "물류센터명", example = "")
	private String dcname;
	
	/** 로케이션 */
	@Schema(description = "로케이션", example = "")
	private String loc;
	
	/** 창고층 */
	@Schema(description = "창고층", example = "90")
	private String whareafloor;

	/** 멀티적용 */
	@Schema(description = "멀티적용", example = "N")
	private String multiLocYn;
	
}