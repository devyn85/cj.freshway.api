package cjfw.wms.ms.dto;

import java.util.List;

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
public class MsCenterPolicyMngResDto {
	
	/** 센터코드 */
	@Schema(description = "센터코드", example = "")
	private String dccode;

	/** 정책코드 */
	@Schema(description = "정책코드", example = "")
	private String plcycode;
		
	/** 정책명 */
	@Schema(description = "정책명", example = "")
	private String plcyNm;
	
	/** 적용여부 */
    @Schema(description = "적용여부")
    private String applyYn;
    
    /** 정책설명 */
    @Schema(description = "정책설명")
    private String plcyDesc;
    
    /** 기타설정 */
    @Schema(description = "기타설정")
    private List<MsCenterPolicyMngResDto> subDetail;
    
}