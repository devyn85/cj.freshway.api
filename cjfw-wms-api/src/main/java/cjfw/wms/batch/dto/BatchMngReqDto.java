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
 * @date : 2025.07.04
 * @description : 배치 정보 요청 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.07.04 yewon.kim (yewon.kim9@cj.net) 생성 </pre>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class BatchMngReqDto extends CommonDto {
	/** JOB 구분 */
	@Schema(description = "JOB 구분", example = "Y")
	private String jobGubun;

	/** JOB 이름 */
	@Schema(description = "JOB 이름", example = "")
	private String jobName;

	/** JOB 이름/설명 */
	@Schema(description = "JOB 이름/설명", example = "")
	private String jobNameDesc;
	
	/** 작업 주기 */
	@Schema(description = "작업주기", example = "")
	private String jobInterval;

	/** 작업 상태 */
	@Schema(description = "작업 결과", example = "")
	private String jobResult;

	/** 사용 여부 */
	@Schema(description = "사용 여부", example = "Y")
	private String useYn;

	/** 작업 스케쥴(Quartz) */
	@Schema(description = "작업 스케쥴(Quartz)", example = "")
	private String jobSchedule;

	/** JOB 설명 */
	@Schema(description = "JOB 설명", example = "")
	private String jobDesc;

	/** 타임아웃 설정(초) */
	@Schema(description = "타임아웃 설정(초)", example = "")
	private String timeoutValue;

	/** 소스 경로 */
	@Schema(description = "소스 경로", example = "")
	private String jobClassName;

	/** 파라미터 이름 */
	@Schema(description = "파라미터 이름", example = "")
	private String paramName;

	/** 파라미터 설명 */
	@Schema(description = "파라미터 설명", example = "")
	private String paramDesc;

	/** 파라미터 타입 */
	@Schema(description = "파라미터 타입", example = "")
	private String paramType;

	/** 파라미터 값 */
	@Schema(description = "파라미터 값", example = "")
	private String paramValue;

}
