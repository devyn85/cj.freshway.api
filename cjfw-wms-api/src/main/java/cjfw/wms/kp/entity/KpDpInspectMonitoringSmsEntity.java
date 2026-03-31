package cjfw.wms.kp.entity;

import cjfw.wms.common.extend.CommonProcedureDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author      : JiSooKim (jskim14@cj.net)
 * @date        : 2025.12.23
 * @description : 미검수알림 SMS (저장) 엔티티
 * @issues      :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.12.23 JiSooKim (jskim14@cj.net)  생성 </pre>
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "미검수알림 SMS (저장) 엔티티")
public class KpDpInspectMonitoringSmsEntity extends CommonProcedureDto {
    @Schema(description = "발신그룹")
    private String sendgroup;

    @Schema(description = "발신전화번호")
    private String sendphone;

    @Schema(description = "발신자명")
    private String sendname;

    @Schema(description = "발신제목")
    private String sendtitle;

    @Schema(description = "발신메세지")
    private String sendmessage;

    @Schema(description = "수신그룹")
    private String receivegroup;

    @Schema(description = "수신전화번호")
    private String receivephone;

    @Schema(description = "수신자명")
    private String receivename;
    
    @Schema(description = "")
    private String cnts;
    
    
   
}
