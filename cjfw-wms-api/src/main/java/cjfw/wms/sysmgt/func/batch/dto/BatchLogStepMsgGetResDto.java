package cjfw.wms.sysmgt.func.batch.dto;

import lombok.Data;

/**
 * 배치이력 StepResultMessage 조회 Response DTO
 */
@Data
public class BatchLogStepMsgGetResDto {
    private Long jobExecutionId;
    private String getLog;  // StepResultMessage
}

/**
 [SPA API 샘플 예시]
 {
     "statusCode": 0,
     "statusMessage": "",
     "data": {
             "jobExecutionId": 4104,
             "getLog": "java.lang.ArrayIndexOutOfBoundsException: Index ..."
     }
 }
 */