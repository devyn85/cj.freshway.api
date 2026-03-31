package cjfw.wms.batch.entity;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : yewon.kim (yewon.kim9@cj.net)
 * @date : 2025.07.04
 * @description : 배치 정보 Entity
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.07.04 yewon.kim (yewon.kim9@cj.net) 생성 </pre>
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class BatchMngEntity extends CommonDto {
	/** JOB 이름 */
	@Schema(description = "JOB 이름", example = "sampleJob")
	private String jobName;
	
	/** 소스 경로 */
	@Schema(description = "소스 경로", example = "cjfw.wms.batch.job.SampleBatch")
	private String jobClassName;

	/** JOB 스케쥴 */
	@Schema(description = "JOB 스케쥴", example = "0 0/10 * * * ?")
	private String jobSchedule;

	/** JOB 구분 */
	@Schema(description = "JOB 구분", example = "sampleJob")
	private String jobGubun;

	/** JOB 설명 */
	@Schema(description = "JOB 설명", example = "")
	private String jobDesc;

	/** JOB 설명 */
	@Schema(description = "작업주기", example = "")
	private String jobInterval;
	
	/** 사용여부 */
	@Schema(description = "사용여부", example = "Y")
	private String useYn;

	/** 타임아웃 */
	@Schema(description = "타임아웃", example = "sampleJob")
	private String timeoutValue;

	/** 등록자 */
	@Schema(description = "등록자", example = "")
	private String addwho;
	
	/** 등록일시 */
	@Schema(description = "등록일시", example = "")
	private String adddate;
	
	/** 수정자 */
	@Schema(description = "수정자", example = "")
	private String editwho;
	
	/** 수정일시 */
	@Schema(description = "수정일시", example = "")
	private String editdate;

}