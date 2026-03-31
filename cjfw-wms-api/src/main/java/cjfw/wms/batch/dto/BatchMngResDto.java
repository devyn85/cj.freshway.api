package cjfw.wms.batch.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : yewon.kim (yewon.kim9@cj.net)
 * @date : 2025.07.04
 * @description : 배치 정보 응답 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.07.04 yewon.kim (yewon.kim9@cj.net) 생성 </pre>
 */
@Data
public class BatchMngResDto {
	/** JOB 구분 */
	@Schema(description = "JOB 구분", example = "619")
	private String jobGubun;
	
	/** JOB 이름 */
	@Schema(description = "JOB 이름", example = "2690")
	private String jobName;
		
	/** JOB 설명 */
	@Schema(description = "JOB 설명", example = "L05")
	private String jobDesc;
	
	/** 작업 주기 */
	@Schema(description = "작업 주기", example = "일반 랙(보관/피킹)")
	private String jobInterval;
		
	/** 작업 스케쥴(Quartz) */
	@Schema(description = "작업 스케쥴(Quartz)", example = "STD")
	private String jobSchedule;
	
	/** 자연어로 표시 */
	@Schema(description = "자연어로표시", example = "STD")
	private String jobScheduleDesc;
		
	/** 인수 설정 */
	@Schema(description = "인수 설정", example = "00")
	private String paramDesc;
	
	/** 타임아웃 설정(초) */
	@Schema(description = "타임아웃 설정(초)", example = "")
	private String timeoutValue;
	
	/** 최근 수행 시간(초) */
	@Schema(description = "최근 수행 시간(초)", example = "")
	private String lastRunTime;

	/** 실행 시작시간 */
	@Schema(description = "실행 시작시간", example = "")
	private String lastRunStartTime;

	/** 실행 종료시간 */
	@Schema(description = "실행 종료시간", example = "")
	private String lastRunEndTime;

	/** 작업상태 */
	@Schema(description = "작업 결과", example = "")
	private String jobResult;

	/** 소스 경로 */
	@Schema(description = "소스 경로", example = "")
	private String jobClassName;

	/** 사용 여부 */
	@Schema(description = "사용 여부", example = "")
	private String useYn;

	/** 파라미터 이름 */
	@Schema(description = "파라미터 이름", example = "")
	private String paramName;

	/** 파라미터 타입 */
	@Schema(description = "파라미터 타입", example = "")
	private String paramType;

	/** 파라미터 값 */
	@Schema(description = "파라미터 값", example = "")
	private String paramValue;

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

	/** job 존재여부 */
	@Schema(description = "job 존재여부", example = "")
	private String jobDetailYn ;

    /** job RMK - ASIS 내용 검색 */
    @Schema(description = "job RMK", example = "")
    private String jobRmk ;
}