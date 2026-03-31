package cjfw.wms.cm.dto;

import java.util.List;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author      : JiSooKim (jskim14@cj.net)
 * @date        : 2025.09.11
 * @description : 수신그룹 조회 요청 DTO
 * @issues      :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.09.11 JiSooKim (jskim14@cj.net) 생성 </pre>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "수신그룹 조회 요청")
public class CmReceiveGroupReqDto extends CommonDto {
	
	/** 수신그룹ID */
    @Schema(description = "수신그룹ID", example = "1")
    private Long recvGroupId;
    
    /** 수신그룹명 */
    @Schema(description = "수신그룹명", example = "관리자그룹")
    private String recvGroupNm;

    /** 상세 리스트 */
    @Schema(description = "상세 리스트")
    private List<CmReceiveGroupDtlReqDto> dtlList;
}
