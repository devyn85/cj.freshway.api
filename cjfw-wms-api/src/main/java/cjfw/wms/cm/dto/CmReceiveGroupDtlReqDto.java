package cjfw.wms.cm.dto;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author      : JiSooKim (jskim14@cj.net)
 * @date        : 2025.09.11
 * @description : 수신그룹 조회 응답 DTO
 * @issues      :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.09.11 JiSooKim (jskim14@cj.net) 생성 </pre>
 */
@Data
@Schema(description = "수신그룹 조회 응답")
public class CmReceiveGroupDtlReqDto extends CommonDto {
    /** 수신그룹ID */
    @Schema(description = "수신그룹ID", example = "1")
    private Long recvGroupId;

    /** 수신그룹명 */
    @Schema(description = "수신그룹명", example = "센터관리자")
    private String recvGroupNm;

    /** 메모 */
    @Schema(description = "메모", example = "전체 센터관리자 대상")
    private String memo;

    /** 사용자여부 */
    @Schema(description = "사용자여부", example = "Y")
    private String useYn;

    /** 등록일자 */
    @Schema(description = "등록일자", example = "2025-09-01")
    private String addDate;

    /** 수정일자 */
    @Schema(description = "수정일자", example = "2025-09-01")
    private String editDate;

    /** 등록자 */
    @Schema(description = "등록자", example = "SYSTEM")
    private String addWho;

    /** 수정자 */
    @Schema(description = "수정자", example = "SYSTEM")
    private String editWho;
    
    /** 사용자ID */
    @Schema(description = "사용자ID", example = "user01")
    private String userId;
    
    /** GridRow 저장 구분 */
	@Schema(description = "GridRow 저장 구분", example = "U", allowableValues = {"I", "U", "D"})
    private String rowStatus;
}
