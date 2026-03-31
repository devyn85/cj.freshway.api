package cjfw.wms.tm.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : OhEunbeom
 * @date : 2025.11.10
 * @description : 차량 운행 기록 조회 로그 저장 DTO
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.11.10 OhEunbeom      생성 </pre>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "차량 운행 기록 조회 로그 저장 DTO")
public class TmCarTrackQryLogDto {

    /** 물류센터코드 */
    @Schema(description = "물류센터코드")
    private String dccode;

    /** 배송일자 */
    @Schema(description = "배송일자")
    private String deliverydt;

    /** 사용자ID */
    @Schema(description = "사용자ID")
    private String userId;

    /** 사용자명 */
    @Schema(description = "사용자명")
    private String username;

    /** 접속IP */
    @Schema(description = "접속IP")
    private String ipAddress;

    /** 접속 디바이스 */
    @Schema(description = "접속 디바이스 (Mobile, Tablet, Web)")
    private String device;

    /** 차량번호 */
    @Schema(description = "차량번호")
    private String carno;

}

