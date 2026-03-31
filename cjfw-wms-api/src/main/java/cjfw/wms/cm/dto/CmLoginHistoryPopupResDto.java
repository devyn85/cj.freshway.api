package cjfw.wms.cm.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JiSooKim (jskim14@cj.net) 
 * @date : 2025.10.24 
 * @description : 접속이력 조회 응답 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.10.24 JiSooKim (jskim14@cj.net) 생성 </pre>
 */
@Data
@Schema(description = "로그인 이력 팝업 조회 응답")
public class CmLoginHistoryPopupResDto {
    /** 로그인일시 (YYYY-MM-DD HH24:MI:SS) */
    @Schema(description = "로그인일시", example = "2025-10-24 13:45:12")
    private String loginDt;

    /** 로그아웃일시 (YYYY-MM-DD HH24:MI:SS) */
    @Schema(description = "로그아웃일시", example = "2025-10-24 14:00:00")
    private String lotDt;

    /** 로그인 성공 여부 (성공/실패) */
    @Schema(description = "로그인 성공 여부", example = "성공")
    private String loginSuccYn;

    /** 로그인 IP */
    @Schema(description = "로그인 IP", example = "192.168.0.1")
    private String loginIp;
}
