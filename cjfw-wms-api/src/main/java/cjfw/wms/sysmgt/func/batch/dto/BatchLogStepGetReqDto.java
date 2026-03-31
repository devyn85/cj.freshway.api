package cjfw.wms.sysmgt.func.batch.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 배치이력 Step 조회 Request DTO
 */
@Data
public class BatchLogStepGetReqDto {
	@Schema(description = "job 실행 id", example = "1264", nullable = false)
	@NotNull(message = "job 실행 id는 필수 값입니다.")
    private Long jobExecutionId;
	@Schema(description = "step 실행 id", example = "15", nullable = true)
    private Long stepExecutionId;	
}
