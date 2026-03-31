package cjfw.wms.sysmgt.func.ipallow.dto;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

/**
 * IP허용예외 저장 Request DTO
 */
@Data
@Schema(description = "IP허용예외 저장 요청")
public class IpAllowSaveReqDto {

    @Schema(description = "IP허용예외 저장 리스트")
    @Valid
    @NotEmpty
    private List<IpAllowSaveReqDto.IpAllow> ipAllows;

    @Data
    public static class IpAllow{
        @Schema(description = "외부허용IP", example = "127.0.0.1")
        @NotEmpty
        private String ipAddr;

        @Schema(description = "사용자ID", example = "devadmin01")
        @NotEmpty
        private String userId;

        @Schema(description = "사유", example = "테스트")
        private String reason; // 사유

        @Schema(description = "GridRow 저장 구분", example = "U", allowableValues = {"I", "U", "D"})
        @NotEmpty
        private String rowStatus; // "I"(등록), "U"(수정), "D"(삭제)
    }
}


/**
 [SPA API 샘플 예시]
 {
     "ipAllows":[
         // 등록
          {
              "ipAddr": "10.1.1.1",
              "userId": "devadmin01",
              "reason": "test",
              "rowStatus": "I"
          },
         // 수정
          {
              "ipAddr": "10.1.1.1",
              "userId": "devadmin01",
              "reason": "test_수정",
              "rowStatus": "U"
          },
         // 삭제
         {
             "ipAddr": "10.1.1.1",
             "userId": "devadmin01",
             "rowStatus": "D"
         }
     ]
 }
 */