package cjfw.wms.tm.dto;

import cjfw.core.model.Page;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : Auto Generated
 * @date : 2025.01.20
 * @description : TM 배송 클레임 조회 요청 DTO
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.01.20 Auto Generated 생성 </pre>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Schema(description = "TM 배송 클레임 조회 요청 DTO")
public class TmClaimListReqDto extends Page<Object> {

    /** 배송일자 시작 (YYYYMMDD 형식, 필수) */
    @Schema(
        description = "배송일자 시작 (YYYYMMDD 형식)",
        example = "20250120",
        nullable = false,
        pattern = "^[0-9]{8}$"
    )
    private String deliverydtFrom;

    /** 배송일자 종료 (YYYYMMDD 형식, 필수) */
    @Schema(
        description = "배송일자 종료 (YYYYMMDD 형식)",
        example = "20250130",
        nullable = false,
        pattern = "^[0-9]{8}$"
    )
    private String deliverydtTo;

    /** 센터코드 */
    @Schema(
        description = "센터코드",
        example = "2600",
        nullable = true
    )
    private String dccode;

    /** 거래처코드 */
    @Schema(
        description = "거래처코드",
        example = "557401044",
        nullable = true
    )
    private String custkey;
}
