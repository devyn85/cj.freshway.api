package cjfw.wms.sysmgt.func.batch.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 배치이력 Job 조회 Request DTO
 */
@Data
public class BatchLogJobGetReqDto {
	@Schema(description = "시작일자", example = "20200901", nullable = false)
    private String fromDt; // job 시작일자
	@Schema(description = "종료일자", example = "20200930", nullable = false)
	private String thruDt; // job 종료일자
	@Schema(description = "결과", example = " ", allowableValues = {"COMPLETED", "FAILED"})
	private String status; // job 결과(ex. COMPLETED, FAILED)
	@Schema(description = "job 인스턴스 id", example = " ", nullable = true)
	private Long jobInstanceId;
	@Schema(description = "job명", example = " ", nullable = true)
	private String jobName;
	@Schema(description = "job 실행 id", example = " ", nullable = true)
    private Long jobExecutionId;
}
