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
public class BatchParamSetEntity extends CommonDto {
	/** JOB 이름 */
	@Schema(description = "JOB 이름", example = "sampleJob")
	private String jobName;

	/** JOB 이름 */
	@Schema(description = "파라미터 이름", example = "sampleJob")
	private String paramName;

	/** JOB 이름 */
	@Schema(description = "파라미터 설명", example = "sampleJob")
	private String paramDesc;

	/** JOB 이름 */
	@Schema(description = "파라미터 값", example = "sampleJob")
	private String paramValue;

	/** JOB 이름 */
	@Schema(description = "파라미터 타입", example = "sampleJob")
	private String paramType;

	/** 사용 여부 */
	@Schema(description = "사용 여부", example = "sampleJob")
	private String useYn;

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