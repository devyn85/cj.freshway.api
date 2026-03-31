package cjfw.wms.batch.dto;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : yewon.kim (yewon.kim9@cj.net)
 * @date : 2025.07.08
 * @description : 배치 이력 요청 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.07.08 yewon.kim (yewon.kim9@cj.net) 생성 </pre>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class BatchHistoryReqDto extends CommonDto {
	/** 실행 시작일자 */
	@Schema(description = "실행 시작일자", example = "Y")
	private String execStartDt;

	/** 실행 종료일자 */
	@Schema(description = "실행 종료일자", example = "Y")
	private String execEndDt;

	/** JOB 구분 */
	@Schema(description = "JOB 구분", example = "Y")
	private String jobGubun;

	/** JOB 이름 */
	@Schema(description = "JOB 이름", example = "")
	private String jobName;
	
	/** JOB 실행 ID */
	@Schema(description = "JOB 실행 ID", example = "")
	private String jobExecutionId;

	/** JOB 인스턴스 ID */
	@Schema(description = "JOB 인스턴스 ID", example = "")
	private String jobInstanceId;

	/** JOB 결과 */
	@Schema(description = "JOB 결과", example = "Y")
	private String jobResult;

	/** STEP 결과 */
	@Schema(description = "STEP 결과", example = "")
	private String stepResult;

    /** 오류발생여부 */
    @Schema(description = "오류발생여부", example = "")
    private String errorStatus;

    /** job id/명 as-is */
    @Schema(description = "job id/명 as-is", example = "")
    private String asisJobName;
}
