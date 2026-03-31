package cjfw.wms.cm.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JangGwangSeok (breaker3317@cj.net) 
 * @date : 2025.05.08 
 * @description : 그룹 공통코드 목록 조회 응답 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.05.08 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
 */
@Data
@Schema(description = "그룹 공통코드 목록 조회 응답")
public class CmMainCodeListResDto {

	/** 공통그룹코드 */
    @Schema(description = "공통그룹코드", example = "TPL_TIMEZONE")
    private String comGrpCd;

    /** 공통코드 리스트 */
    @Schema(description = "공통코드 리스트")
    private List<CmMainCodeResDto> commCodes;

}
