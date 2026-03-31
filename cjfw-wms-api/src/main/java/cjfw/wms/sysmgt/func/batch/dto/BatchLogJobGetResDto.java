package cjfw.wms.sysmgt.func.batch.dto;

import lombok.Data;

/**
 * 배치이력 Job 조회 Response DTO
 */
@Data
public class BatchLogJobGetResDto {
    private Long jobExecutionId;
    private Long jobInstanceId;
    private String jobName;
    private String startTime;
    private String endTime;
    private String status;
}

/**
 [SPA API 샘플 예시]
 {
     "statusCode": 0,
     "statusMessage": "",
     "data": [
         {
             "jobExecutionId": 4485,
             "jobInstanceId": 4545,
             "jobName": "sampleTaskletJob",
             "startTime": "2022-05-27 15:21:57",
             "endTime": "2022-05-27  15:22:16",
             "status": "COMPLETED"
         },
     ...
 */

/**
 [MPA 샘플 예시]
 JOB_EXECUTION_ID: 4485
 JOB_INSTANCE_ID: 4545
 JOB_NAME: "sampleTaskletJob"
 START_TIME: "2022-05-27 15:21:57"
 END_TIME: "2022-05-27  15:22:16"
 STATUS: "COMPLETED"
 */
