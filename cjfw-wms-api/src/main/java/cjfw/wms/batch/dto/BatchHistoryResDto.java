package cjfw.wms.batch.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : yewon.kim (yewon.kim9@cj.net)
 * @date : 2025.07.08
 * @description : 배치 이력 응답 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.07.08 yewon.kim (yewon.kim9@cj.net) 생성 </pre>
 */
@Data
public class BatchHistoryResDto {

	/************************************************
	 * JOB 이력
	 ************************************************/
	/** JOB 구분 */
	@Schema(description = "JOB 구분", example = "619")
	private String jobGubun;
	
	/** JOB 이름 */
	@Schema(description = "JOB 이름", example = "emailSendJob")
	private String jobName;

    /** JOB 설명 */
    @Schema(description = "JOB 설명", example = "Email 송신")
    private String jobDesc;
		
	/** JOB 실행 ID */
	@Schema(description = "JOB 실행 ID", example = "L05")
	private String jobExecutionId;

	/** JOB 인스턴스 ID */
	@Schema(description = "JOB 인스턴스 ID", example = "L05")
	private String jobInstanceId;

	/** 실행 시작시간 */
	@Schema(description = "실행 시작시간", example = "")
	private String startTime;

	/** 실행 종료시간 */
	@Schema(description = "실행 종료시간", example = "")
	private String endTime;

	/** 작업소요시간*/
	@Schema(description = "작업소요시간", example = "")
	private String runTime;

	/** 작업 결과 */
	@Schema(description = "작업 결과", example = "")
	private String resultStatus;

	/** 메시지 */
	@Schema(description = "작업 결과 메시지", example = "")
	private String resultMessage;

    /** job id/명 as-is */
    @Schema(description = "job id/명 as-is", example = "")
    private String asisJobName;


	/************************************************
	 * Step 이력
	 ************************************************/
	/** Step 실행 ID */
	@Schema(description = "Step 실행 ID", example = "")
	private String stepExecutionId;

	/** Step 이름 */
	@Schema(description = "Step 이름", example = "")
	private String StepName;

	/** Read 건수 */
	@Schema(description = "Read 건수", example = "")
	private String readCount;

	/** Commit 건수 */
	@Schema(description = "Commit 건수", example = "")
	private String commitCount;

	/** Filter 건수 */
	@Schema(description = "Filter 건수", example = "")
	private String filterCount;

	/** Skip 건수 */
	@Schema(description = "Skip 건수", example = "")
	private String skipCount;

	/** Rollback 건수 */
	@Schema(description = "Rollback 건수", example = "")
	private String rollbackCount;


	/************************************************
	 * JOB PARAM
	 ************************************************/
	/** 파라미터 이름 */
	@Schema(description = "파라미터 이름", example = "")
	private String paramName;

	/** 파라미터 타입 */
	@Schema(description = "파라미터 타입", example = "")
	private String paramType;

	/** 파라미터 값 */
	@Schema(description = "파라미터 값", example = "")
	private String paramValue;


    /************************************************
     * JOB 실행 상세 내역
     ************************************************/
    @Schema(description = "JOB_EXECUTION_SEQ", example = "JOB 실행순번")
    private String jobExecutionSeq;

    @Schema(description = "JOB_DIV", example = "JOB 구분")
    private String jobDiv;

    @Schema(description = "NODE_LEVEL", example = "노드레벨")
    private String nodeLevel;

    @Schema(description = "JOB_STATUS", example = "상태")
    private String jobStatus;

    @Schema(description = "COMMAND", example = "수행명령어")
    private String command;

    @Schema(description = "LINE_NO", example = "라인번호")
    private String lineNo;

    @Schema(description = "RESULT_CODE", example = "결과코드")
    private String resultCode;

    @Schema(description = "RESULT_MSG", example = "결과내용")
    private String resultMsg;

    @Schema(description = "ADD_TIMESTAMP", example = "등록시간")
    private String addTimestamp;

    @Schema(description = "ERROR_STATUS", example = "오류발생여부")
    private String errorStatus;

}