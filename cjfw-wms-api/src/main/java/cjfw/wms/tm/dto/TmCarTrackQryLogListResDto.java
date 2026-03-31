package cjfw.wms.tm.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : OhEunbeom
 * @date : 2025.01.XX
 * @description : 차량 이력 조회 응답 DTO
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.01.XX OhEunbeom      생성 </pre>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "차량 이력 조회 응답 DTO")
public class TmCarTrackQryLogListResDto {

    /** 이력일시 */
    @Schema(description = "이력일시")
    private String logTimestamp;

    /** 물류센터 코드 */
    @Schema(description = "물류센터 코드")
    private String dccode;

    /** 물류센터명 */
    @Schema(description = "물류센터명")
    private String dcname;

    /** 배송일자 */
    @Schema(description = "배송일자")
    private String deliverydt;

    /** 차량번호 */
    @Schema(description = "차량번호")
    private String carno;

    /** 사용자ID (마스킹 처리) */
    @Schema(description = "사용자ID (첫 네자리 이후 마스킹)")
    private String userId;

    /** 사용자명 (마스킹 처리) */
    @Schema(description = "사용자명 (성과 이름의 마지막 자리 제외 마스킹)")
    private String username;

    /** 디바이스 */
    @Schema(description = "디바이스")
    private String device;

    /** IP주소 (마스킹 처리) */
    @Schema(description = "IP주소 (17~24비트 마스킹)")
    private String ipAddress;

}

