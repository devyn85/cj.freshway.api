package cjfw.wms.tm.dto;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : han@wemeetmobility.com
 * @date : 2025.11.28
 * @description : TM 배차 업로드 응답 DTO
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "TM 배차 업로드 응답 DTO")
public class TmDispatchUploadResDto {

    /** 총 건수 */
    @Schema(description = "총 건수", example = "10")
    private Integer totalCount;

    /** 성공 건수 */
    @Schema(description = "성공 건수", example = "8")
    private Integer successCount;

    /** 실패 건수 */
    @Schema(description = "실패 건수", example = "2")
    private Integer failCount;

    /** 검증 실패 목록 */
    @Schema(description = "검증 실패 목록")
    private List<TmDispatchValidationErrorDto> errors;
}
