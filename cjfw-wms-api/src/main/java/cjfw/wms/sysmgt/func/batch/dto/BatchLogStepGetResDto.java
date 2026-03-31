package cjfw.wms.sysmgt.func.batch.dto;

import lombok.Data;

/**
 * 배치이력 Step 조회 Response DTO
 */
@Data
public class BatchLogStepGetResDto {
    private Long jobExecutionId;
    private Long stepExecutionId;
    private String stepName;
    private Long readCount;
    private Long writeCount;
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
             "jobExecutionId": 4481,
             "stepExecutionId": 4702,
             "stepName": "sampleTaskletStep2",
             "readCount": 0,
             "writeCount": 0,
             "startTime": "2022-05-27 14:58:48",
             "endTime": "2022-05-27  14:59:09",
             "status": "COMPLETED"
         },
    ...
 */

/**
  [MPA 샘플 예시]
 JOB_EXECUTION_ID: 4485
 STEP_EXECUTION_ID: 4710
 STEP_NAME: "sampleTaskletStep2"
 READ_COUNT: 0
 WRITE_COUNT: 0
 START_TIME: "2022-05-27 15:21:57"
 END_TIME: "2022-05-27  15:22:16"
 STATUS: "COMPLETED"
 */
