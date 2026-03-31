package cjfw.batch.common.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BatchLogParamsDto {

    @Schema(description = "JOB 실행ID")
    private String jobExecutionId;          // JOB_EXECUTION_ID
    @Schema(description = "JOB NAME")
    private String jobName;                 // JOB_NAME
    @Schema(description = "JOB 구분")
    private String jobDiv;                  // JOB_DIV (JAVA, PKG, SP)
    @Schema(description = "노드레벨")
    private Integer nodeLevel;              // NODE_LEVEL
    @Schema(description = "상태")
    private String jobStatus;               // JOB_STATUS (START, END, INFO)
    @Schema(description = "수행명령어")
    private String command;                 // COMMAND
    @Schema(description = "라인번호")
    private String lineNo;                  // LINE_NO
    @Schema(description = "결과코드")
    private String resultCode;              // RESULT_CODE
    @Schema(description = "결과내용")
    private String resultMsg;               // RESULT_MSG

}
