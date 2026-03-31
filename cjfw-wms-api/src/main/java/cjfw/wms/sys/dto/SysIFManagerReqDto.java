package cjfw.wms.sys.dto;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JiSooKim (jskim14@cj.net) 
 * @date : 2025.08.01 
 * @description : 인터페이스 상태관리 조회 요청 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.08.01 JiSooKim (jskim14@cj.net) 생성 </pre>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "실행로그 마스터 조회 요청")
public class SysIFManagerReqDto extends CommonDto {
	
	/** 인터페이스 ID */
    @Schema(description = "인터페이스 ID")
    private String ifId;

    /** EAI 관리 여부 */
    @Schema(description = "EAI 관리 여부")
    private Integer eaiMngChYn;
    
    @Schema(description = "삭제 여부")
    private String delYn;

    @Schema(description = "원본 삭제 여부")
    private String orgDelYn;

    @Schema(description = "상태")
    private String status;

    @Schema(description = "원본 상태")
    private String orgStatus;

}