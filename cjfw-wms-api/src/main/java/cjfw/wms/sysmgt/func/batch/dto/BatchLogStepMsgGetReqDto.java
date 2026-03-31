package cjfw.wms.sysmgt.func.batch.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 배치이력 StepResultMessage 조회 Request DTO
 */
@Data
public class BatchLogStepMsgGetReqDto {
	@Schema(description = "job 실행 id", example = "4104", nullable = false)
    @NotNull(message = "job 실행 id는 필수 값입니다.")
    private Long jobExecutionId;
	
	@Schema(description = "step 실행 id", example = "4244", nullable = false)
    @NotNull(message = "step 실행 id는 필수 값입니다.")
    private Long stepExecutionId;
}

/**
 [MPA 샘플 예시]
JOB_EXECUTION_ID: 4104
STEP_EXECUTION_ID: 4244

 JOB_EXECUTION_ID: 4223
 STEP_EXECUTION_ID: 4424
 */