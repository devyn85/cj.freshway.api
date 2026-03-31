package cjfw.wms.sysmgt.func.ipallow.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * IP허용예외 조회 Request DTO
 */
@Data
@Schema(description = "IP허용예외 조회 요청")
public class IpAllowGetReqDto {
    @Schema(description = "외부허용IP", example = " ", nullable = true)
    private String ipAddr;

    @Schema(description = "사용자ID", example = " ", nullable = true)
    private String userId;
}
