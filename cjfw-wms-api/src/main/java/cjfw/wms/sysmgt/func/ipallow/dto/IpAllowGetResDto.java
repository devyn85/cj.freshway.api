package cjfw.wms.sysmgt.func.ipallow.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * IP허용예외 조회 Response DTO
 */
@Data
@Schema(description = "IP허용예외 조회 응답")
public class IpAllowGetResDto{
    @Schema(description = "외부허용IP", example = "127.0.0.1")
    private String ipAddr;

    @Schema(description = "사용자ID", example = "devadmin01")
    private String userId;

    @Schema(description = "사유", example = "null")
    private String reason;

    @Schema(description = "등록자ID", example = "SYSTEM")
    private String regId;

    @Schema(description = "등록일자", example = "2022-06-09")
    private String regDt;
}

/**
  [SPA API 샘플 예시]
 {
 "statusCode": 0,
 "statusMessage": "",
 "data": [
     {
         "ipAddr": "127.0.0.1",
         "userId": "devadmin01",
         "reason": null,
         "regId": "SYSTEM",
         "regDt": "2022-05-30"
     }
 ]
 }
 */