package cjfw.wms.cm.entity;

import cjfw.wms.common.extend.CommonTriggerDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author      : JiSooKim (jskim14@cj.net)
 * @date        : 2025.09.11
 * @description : 수신그룹 Entity
 * @issues      :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.09.11 JiSooKim (jskim14@cj.net) 생성 </pre>
 */
@Data
@Schema(description = "수신그룹 Entity")
public class CmReceiveGroupEntity extends CommonTriggerDto {
    /** 수신그룹ID */
    @Schema(description = "수신그룹ID", example = "10001")
    private Long recvGroupId;

    /** 수신그룹명 */
    @Schema(description = "수신그룹명", example = "관리자그룹")
    private String recvGroupNm;

    /** 메모 */
    @Schema(description = "메모", example = "관리자 전용 그룹")
    private String memo;

    /** 사용여부 */
    @Schema(description = "사용여부", example = "Y")
    private String useYn;
    
    /** 사용자ID */
    @Schema(description = "사용자ID", example = "user01")
    private String userId;

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
}
