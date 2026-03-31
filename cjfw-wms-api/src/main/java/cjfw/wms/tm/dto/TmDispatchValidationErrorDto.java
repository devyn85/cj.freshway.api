package cjfw.wms.tm.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : han@wemeetmobility.com
 * @date : 2025.11.28
 * @description : TM 배차 검증 오류 DTO
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "TM 배차 검증 오류 DTO")
public class TmDispatchValidationErrorDto {

    /** no 컬럼의 값 */
    @Schema(description = "no 컬럼의 값", example = "1")
    private Integer no;

    /** 컬럼명 */
    @Schema(description = "컬럼명", example = "차량번호")
    private String columnName;

    /** 오류 메시지 */
    @Schema(description = "오류 메시지", example = "필수 입력 항목입니다")
    private String errorMessage;

    /** 입력값 */
    @Schema(description = "입력값", example = "99가9999")
    private String inputValue;
}
