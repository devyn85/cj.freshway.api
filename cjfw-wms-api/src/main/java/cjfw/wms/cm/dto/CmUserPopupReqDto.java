package cjfw.wms.cm.dto;

import java.util.List;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author      : JiSooKim (jskim14@cj.net) 
 * @date        : 2025.09.11 
 * @description : 사용자 팝업 조회 요청 DTO
 * @issues      :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.09.11 JiSooKim (jskim14@cj.net)  생성 </pre>
 */
@Data
@Schema(description = "사용자 팝업 조회 요청")
public class CmUserPopupReqDto extends CommonDto {
    /** 사용자ID */
    @Schema(description = "사용자ID", example = "user01")
    private String userId;

    /** 사용자명 */
    @Schema(description = "사용자명", example = "홍길동")
    private String userNm;
    
    /** 소속구분 */
    @Schema(description = "소속구분", example = "01")
    private String empType;
    
    /** 부서명 */
    @Schema(description = "부서명", example = "편성기획팀")
    private String depthrNm;
    
    /** 사용자명 */
    @Schema(description = "사용자명", example = "홍길동")
    private String name;
    
	@Schema(description = "999개 청크 목록(OR IN 생성용)")
	private List<List<String>> codeGroups;
}
